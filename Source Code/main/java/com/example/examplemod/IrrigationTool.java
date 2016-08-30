/**
 * Irrigation tool Class. Used to create ditches for water
 * Irrigation tool digs two blocks of dirt only on orthogonal paths
 */
package com.example.examplemod;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.UseHoeEvent;
import cpw.mods.fml.common.eventhandler.Event.Result;

public class IrrigationTool extends Item{

	public IrrigationTool(){

		this.maxStackSize = 1;
		this.setCreativeTab(CreativeTabs.tabTools);

	}


	/**
	 * Code re-used from the Scythe Class with Modification by Nasser AlMaadeed 
	 * The irrigation tool digs 2 blocks in front of the user upon item Use (right-click)
	 */
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10){
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
	                Block block1 = Blocks.air;
	                par3World.playSoundEffect((double)((float)par4 + 0.5F), (double)((float)par5 + 0.5F), (double)((float)par6 + 0.5F), block1.stepSound.getStepResourcePath(), (block1.stepSound.getVolume() + 1.0F) / 2.0F, block1.stepSound.getPitch() * 0.8F);

	                if (par3World.isRemote)
	                {
	                    return true;
	                }
	                else
	                {

	                	int yaw = (int)par2EntityPlayer.rotationYaw;

	                    if (yaw<0)      //due to the yaw running a -360 to positive 360
	                       yaw+=360;    

	                    yaw+=22;    // centers coordinates
	                    yaw%=360; 

	                    int facing = yaw/45;   //  360degrees divided by 45 == 8 zones, 0-7
//	                    System.out.println("Yaw is " + yaw + "facing is " + facing);

	                	if(facing == 0)//North
	                	{
							par3World.setBlock(par4, par5, par6, block1); 
		                    par1ItemStack.damageItem(1, par2EntityPlayer);

		                    Block block2 = Blocks.air;
		                    par3World.setBlock(par4, par5, par6+1, block2);

	                	}
			            else if(facing == 2)//East
			            {
							par3World.setBlock(par4, par5, par6, block1); 
		                    par1ItemStack.damageItem(1, par2EntityPlayer);

		                    Block block2 = Blocks.air;
		                    par3World.setBlock(par4-1, par5, par6, block2);

			            }
			            else if(facing == 4)//South
			            {
							par3World.setBlock(par4, par5, par6, block1); 
		                    par1ItemStack.damageItem(1, par2EntityPlayer);

		                    Block block2 = Blocks.air;
		                    par3World.setBlock(par4, par5, par6-1, block2);

			            }
						else if(facing == 6)//West
						{
							par3World.setBlock(par4, par5, par6, block1); 
		                    par1ItemStack.damageItem(1, par2EntityPlayer);

		                    Block block2 = Blocks.air;
		                    par3World.setBlock(par4+1, par5, par6, block2);

						}							
						else //print cannot use this at that direction
						{
							par2EntityPlayer.addChatMessage(new ChatComponentTranslation("I can't do that from where I'm facing!"));
							return true;

						}


	                    par2EntityPlayer.getFoodStats().setFoodLevel(par2EntityPlayer.getFoodStats().getFoodLevel()-2);
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