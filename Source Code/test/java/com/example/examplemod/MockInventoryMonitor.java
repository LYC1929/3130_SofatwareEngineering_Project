package com.example.examplemod;

//This is a mock object to test the method payRent() from the InventoryMonitor class

import net.minecraft.util.ChatComponentTranslation;

public class MockInventoryMonitor {

	public int strikes=0;
	public int GAMEOVER=3;

	public int daysPassed = 0;//days since start of game, and number of penalty strikes
	public final int PAY_PERIOD=1;//How many days must pass before rent is due
	public final int DAY_LENGTH=200;
	public int worldTime = 7000;





	//modified version of the payRent method with return values to test for JUNIT
	//Testing the string for user.addChatMessage
	public String payRentMock1() //removes rent from inventory. If can't be paid, add a strike.
	{


		//user.addChatMessage(new ChatComponentTranslation("Your rent is due!"));
		return "Your rent is due!";


	}


	public String payRentMock2(String passIn) //Testing more of the payRent() method
	{


		try{

			//if((getUserInventory().getStackInSlot(0).getUnlocalizedName().equals("item.apple")))
			if(passIn.equals("apple"))

			{
				//Remove the item in inventory location x. Item MUST be there or null pointer exception!
		//	getUserInventory().consumeInventoryItem(getUserInventory().getStackInSlot(0).getItem()); //causing crash

			//user.addChatMessage(new ChatComponentTranslation("PAID!"));

				return "PAID!";
			//System.out.println("Removed one required item for rent from player's inventory");
			//System.out.println(strikes);
			}

			else // If there is anything other item in slot 0, then it counts as a penalty strike, but not payment
			{


				strikes++;

				System.out.println("TEST!!!! "+strikes);
				if(strikes==GAMEOVER) //checking if you have run out of chances to survive
				{

					System.out.println("Game over. You could not afford to live in this time period!");
				return "GAMEOVER";

				}

			}

			return ""+strikes; //returing strikes as a string for JUNIT test
		}
		catch(NullPointerException except)//This catches a null pointer exception if the item slot is empty
			{
			strikes++;						//Also increments the strikes if the slot is empty, since it doesn't contain the item for payment

			System.out.println("TEST!!!! "+strikes);
			if(strikes==GAMEOVER) //checking if you have run out of chances to survive
			{

				System.out.println("Game over. You could not afford to live in this time period!");

				return "GAMEOVER";
			}

			return ""+strikes;
			}

		//REMOVE CURRENCY FROM INVENTORY AND UPDATE STRIKES IF NECESSAIRY



	}

	//increment daysPassed
	public int isRentDueDaysPassed(){

		if((double)this.worldTime%(double)this.DAY_LENGTH == 0){
			daysPassed++;
		}
		else{
			daysPassed--;
		}

		return daysPassed;

	}

	//set worldTime value
	public void setWorldTime(int time){

		this.worldTime = time;
	}

	//set daysPassed value
	public void setDaysPassed(int days){

		this.daysPassed = days;
	}

	//check whether it is time to pay the rent
	public boolean isRentDue()
	 {


		 if(worldTime%DAY_LENGTH==0) //every day update daysPassed
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


}