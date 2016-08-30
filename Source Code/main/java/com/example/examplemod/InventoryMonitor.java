//Iteration2 FILE
/**
 * used to monitor the condition of the player's inventory
 */
package com.example.examplemod;




import java.awt.event.ActionListener;

import javax.swing.Timer;


import cpw.mods.fml.common.Mod.EventHandler;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.item.ItemTossEvent;


public class InventoryMonitor extends Item {

	public static int RENT = 5; //amount due on rent day
	public static EntityPlayer user;
	public World currentWorld;
	//public int stackSize; //NEW*****************stack of the dollar item
	public static int daysPassed,strikes;//days since start of game, and number of penalty strikes
	public final int PAY_PERIOD=5;//How many days must pass before rent is due
	public final int DAY_LENGTH=24000; //24000 minecraft ticks in 1 minecraft day
	public long minecraftTick=0; //used only for the update method to prevent double calling
	public long minecraftTick2=0;// same as above
	public final int GAMEOVER=3; //number of strikes until you lose
	private int amount=0; //amount paid towards rent
	public final int rentIncreaseTicks=120000; //how many "ticks" pass before rent increases




	public InventoryMonitor()
	{
		strikes=0;
		daysPassed=0;


	}


	/**
	 * Checks if the rent is due and calls the appropriate payRent() method every PAY_PERIOD days
	 */
	//This method is called  to set the user variable to the user with the item in his/her inventory. Now
	//we can use the user variable to call inventory methods
	//also, this method checks when the rent is due and  calls the payRent() method
	 public void onUpdate(ItemStack par1ItemStack, World world, Entity entity, int par4, boolean par5) 
	 {

		 user=(EntityPlayer)entity; //Finding the user

		 currentWorld=world; //passing off the world object

		 //prevent monitor to be dropped in the word from the inventory******Iteration 3
		 ItemTossEvent dragdrop = new ItemTossEvent(new EntityItem(world, minecraftTick, minecraftTick, minecraftTick, par1ItemStack),user);
		 MinecraftForge.EVENT_BUS.post(dragdrop);


	 //This method gets called every tick(minecraft second).


		 if(isRentDue()&&minecraftTick!=world.getWorldTime())//checking if rent is due, if true call the payrent function
		 	{	amount=0;//setting the amount paid towards rent to $0
			 	payRent();
		 		minecraftTick=world.getWorldTime(); //Used to solve the double method call problem
		 	}


	 //Change the rent due to circumstances. In this basic case, it will change after X minecraft ticks weeks

		 if(currentWorld.getWorldTime()%rentIncreaseTicks==0&&minecraftTick2!=currentWorld.getWorldTime())
			 {
			 	rentChange(2,"Rack Renting");
			 	minecraftTick2=world.getWorldTime(); //again, used to solve the double method calling
			 }


	 //Starting game info display message

		 if(currentWorld.getWorldTime()==200)
		 {
			 user.addChatMessage(new ChatComponentTranslation("Welcome to 1850s farm simulator. You have "+PAY_PERIOD+"days to pay the rent on your farm of "+RENT+"dollars"));
				user.addChatMessage(new ChatComponentTranslation("Vegitables, fruit and meat can be converted to currency"));
				user.addChatMessage(new ChatComponentTranslation("You may wish to create a news paper to keep up with the current status of your farm"));

				System.out.println("TIMER!!!");
		 }

	 }


	 public InventoryPlayer getUserInventory()
	{



		return user.inventory; //returning the user's inventory
	}

	 public World getWorld()
	 {
		 return currentWorld;

	 }

/**
 * checking if the rent is due
 * @return
 */
	 public boolean isRentDue()
	 { 


		 if(currentWorld.getWorldTime()%DAY_LENGTH==0) //every day update daysPassed
		 {
			 daysPassed++;

			 if(daysPassed%PAY_PERIOD==0&&daysPassed!=0)//pay rent every PAY_PERIOD days
				 return true;
			 else
				 return false;

		 }
		 else
		 {
			 //System.out.println(currentWorld.getWorldTime());
			 return false;
		 }



	 }

	 /**
	  * pays the rent in dollar objects when called from the update() method
	  */
	private void payRent() //removes rent from inventory. If can't be paid, add a strike. If unpaid, rent is not carried forward to the next pay period
	{


		user.addChatMessage(new ChatComponentTranslation("Your rent is due!"));



		for(int i=0;i<35;i++) //going through all slots of player's inventory looking for money
		{
			if(amount==RENT) //leave the loop if rent has been paid
				{
					user.addChatMessage(new ChatComponentTranslation("Rent has been paid"));
					break;

				}
			try
			{


				if((getUserInventory().getStackInSlot(i).getUnlocalizedName().equals("item.Dollar")))
				{
					//NEW****************************
					//user.addChatMessage(new ChatComponentTranslation("The size is :" + getUserInventory().getStackInSlot(i).stackSize));

				//Remove the item in inventory location x. Item MUST be there or null pointer exception!
					getUserInventory().consumeInventoryItem(getUserInventory().getStackInSlot(i).getItem()); //causing crash


					System.out.println("Removed one required item for rent from player's inventory");
					amount++; //incrementing the dollars paid towards rent

				}

			}


			catch(NullPointerException except)//This catches a null pointer exception if the item slot is empty
			{

			}


		}

			if(amount<RENT) //after looping through the player's inventory if the rent is not paid, then get a penalty strike 
			{

				strikes++;
				user.addChatMessage(new ChatComponentTranslation("Strike: "+strikes));//prints the number of strikes for the user

				if(strikes==GAMEOVER) //checking if you have run out of chances to survive
				{
					gameOver();
				}

			}

		}

	//GameOver method, exits the game when the player fails to pay the rent for 3 times
	//it also force exits the game
	public void gameOver(){
		user.addChatMessage(new ChatComponentTranslation("GAME OVER, YOU COULD NOT AFFORD TO LIVE IN THIS TIME PERIOD!"));
		user.setDead(); //player dies, doesn't work on creative mode

		minecraftTick=getWorld().getWorldTime();
		int countdown = 5;

		//countdown from 5 to 0, game exits when the countdown gets to 0
		while(countdown != 0){
			//while the different between the worldTime and minecraftTick is larger than 50
			if(getWorld().getWorldTime()-minecraftTick >= 50){
				//print the countdown message
				user.addChatMessage(new ChatComponentTranslation("Exiting the game in "+countdown));
				minecraftTick=getWorld().getWorldTime();
				countdown--;
			}
		}

		System.exit(0);//GameOver, force exit the game

	}

//	public boolean onDroppedByPlayer(ItemStack item, EntityPlayer player)
//    {
//        return false;
//    }


	//a disaster will occur every X days and change the amount of rent due
	/**
	 * Allows this class to change the rent due per time frame
	 * @param increase amount to increase rent by (use negative integers for decrease)
	 * @param reason String explaining why the rent has changed
	 */
	public void rentChange(int increase,String reason)
	{

		if(RENT+increase<0) //Can't let the rent drop below 0 
			;
		else
			RENT=RENT+increase;

		user.addChatMessage(new ChatComponentTranslation("Due to "+ reason+", your rent will be increased to"+RENT));

	}


	/**
	 * Gives starting info on beginning rent at the start of the game
	 */
	public void gameStartInfo()
	{


	}





}