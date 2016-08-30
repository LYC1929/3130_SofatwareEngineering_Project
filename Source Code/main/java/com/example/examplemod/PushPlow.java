/**
 * Push Plow Class. It tills a 3 x 3 grid of soil on right click and eats some of the player's food bar
 */

//This is the code for the hand pushed plow. It tills multiple blocks of soil. The onItemUse method
// has been reused and modified from the native Hoe object
//The original Hoe object tills one block of soil that the user right clicks on. Mine uses the coors
//of the original tilled block and tills all its neighbors by using its x,y,z coords.

package com.example.examplemod;



import cpw.mods.fml.common.eventhandler.Event.Result;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.UseHoeEvent;

public class PushPlow extends Item {


   

	 public PushPlow( ) 
	    {

	        this.maxStackSize = 1;

	        this.setCreativeTab(CreativeTabs.tabTools);
	    }

	 /**
	  * On Right click tills a 3 x 3 grid of soil for farming
	  */
	 public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
	    {






		 	if (!par2EntityPlayer.canPlayerEdit(par4, par5, par6, par7, par1ItemStack))
	        {
	            return false;
	        }
	        else
	        {
	            UseHoeEvent event = new UseHoeEvent(par2EntityPlayer, par1ItemStack, par3World, par4, par5, par6);
	            if (MinecraftForge.EVENT_BUS.post(event))
	            {
	                return false;
	            }

	            if (event.getResult() == Result.ALLOW)
	            {
	                par1ItemStack.damageItem(1, par2EntityPlayer);
	                return true;
	            }

	            Block block = par3World.getBlock(par4, par5, par6);

	            if (par7 != 0 && par3World.getBlock(par4, par5 + 1, par6).isAir(par3World, par4, par5 + 1, par6) && (block == Blocks.grass || block == Blocks.dirt))
	            {
	                Block block1 = Blocks.farmland;
	                par3World.playSoundEffect((double)((float)par4 + 0.5F), (double)((float)par5 + 0.5F), (double)((float)par6 + 0.5F), block1.stepSound.getStepResourcePath(), (block1.stepSound.getVolume() + 1.0F) / 2.0F, block1.stepSound.getPitch() * 0.8F);

	                if (par3World.isRemote)
	                {
	                    return true;
	                }
	                else
	                {
	                	 // block1.get
	                	par3World.setBlock(par4, par5, par6, block1); 
	                    par1ItemStack.damageItem(1, par2EntityPlayer);

	                	//My modification (Robbie Mundle)Par 4-6 are x,y,z coords for blocks. Wat
	                    //I am doing is setting the blocks next to the block1 to ALSO be tilled farmland
	                    //9 blocks in total may be tilled
	                    Block block2 = Blocks.farmland;
	                    par3World.setBlock(par4+1, par5, par6, block2);


	                    par3World.setBlock(par4-1, par5, par6, block2);


	                    par3World.setBlock(par4, par5, par6+1, block2);


	                    par3World.setBlock(par4, par5, par6-1, block2);


	                    par3World.setBlock(par4+1, par5, par6+1, block2);


	                    par3World.setBlock(par4+1, par5, par6-1, block2);


	                    par3World.setBlock(par4-1, par5, par6+1, block2);


	                    par3World.setBlock(par4-1, par5, par6-1, block2);

	                  //Using the right click ability uses 1 whole unit of food since it is such a large swing!
	                    par2EntityPlayer.getFoodStats().setFoodLevel(par2EntityPlayer.getFoodStats().getFoodLevel()-1);

	                    return true;
	                }
	            }
	            else
	            {
	                return false;
	            }
	        }
	    }


}