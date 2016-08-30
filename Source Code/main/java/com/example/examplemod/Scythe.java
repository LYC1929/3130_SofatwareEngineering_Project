/**
 * Scythe Class. Cuts a 3 x 3 grid of grass and eats some of the player's food bar
 */
//This is the Scythe class. On right click, it cuts a 3 x 3 area of grass and causes the player's food supply to decrease

package com.example.examplemod;

import cpw.mods.fml.common.ModClassLoader;
import cpw.mods.fml.common.eventhandler.Event.Result;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.UseHoeEvent;

public class Scythe extends Item{

//	public int xcoord,ycoord,zcoord;


	public Scythe(){

		this.maxStackSize = 1;
		this.setCreativeTab(CreativeTabs.tabTools);


	}

	//modified from the Hoe class
	/**
	 * on item right click cuts a 3 x 3 grid of grass
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

            Block block = par3World.getBlock(par4, par5, par6); //getting the block at the right click location

            if (par7 != 0 && par3World.getBlock(par4, par5 + 1, par6).isAir(par3World, par4, par5 + 1, par6) && (block == Blocks.hay_block || block == Blocks.wheat||block==Blocks.tallgrass))//modded
            {
                Block block1 = Blocks.air; //getting the replacement block
                par3World.playSoundEffect((double)((float)par4 + 0.5F), (double)((float)par5 + 0.5F), (double)((float)par6 + 0.5F), block1.stepSound.getStepResourcePath(), (block1.stepSound.getVolume() + 1.0F) / 2.0F, block1.stepSound.getPitch() * 0.8F);

                if (par3World.isRemote)
                {
                    return true;
                }
                else
                {
                	 // setting the block at the right click location to block1 type
                	par3World.setBlock(par4, par5, par6, block1); 
                    par1ItemStack.damageItem(1, par2EntityPlayer);
                
                	//Our modification Par 4-6 are x,y,z coords for blocks. 
                    //I am doing is setting the blocks next to the block1 to ALSO be set to air
                    
                    Block block2 = Blocks.air;
                    par3World.setBlock(par4+1, par5, par6, block2);
                    
                    
                    Block block3 = Blocks.air;
                    par3World.setBlock(par4-1, par5, par6, block3);
                    
                    
                    par3World.setBlock(par4, par5, par6+1, block3);
                   
                    
                    par3World.setBlock(par4, par5, par6-1, block3);
                   
                   
                    par3World.setBlock(par4+1, par5, par6+1, block3);
                   
                    
                    par3World.setBlock(par4+1, par5, par6-1, block3);
                   
                    
                    par3World.setBlock(par4-1, par5, par6+1, block3);
                    
                    //Block block9 = Blocks.air;
                    par3World.setBlock(par4-1, par5, par6-1, block3);
                
                   
                    //Using the right click ability uses 1 whole unit of food since it is such a large swing!
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


	/*
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World world, EntityPlayer player)
    {
        
		
		Block block = world.getBlock(player.chunkCoordZ,player.chunkCoordX,player.chunkCoordY);
		
		world.setBlock(player.chunkCoordZ,player.chunkCoordX,player.chunkCoordY, Blocks.bedrock); 
		System.out.println(""+player.serverPosX+","+player.serverPosY+","+player.serverPosZ);
		player.get
		
    	
    	return par1ItemStack;
        
        
    }
	*/

	public float func_150893_a(ItemStack p_150893_1_, Block p_150893_2_)
    {
        if (p_150893_2_ == Blocks.web)
        {
            return 15.0F;
        }
        else
        {
            Material material = p_150893_2_.getMaterial();
            return material != Material.plants && material != Material.vine && material != Material.coral && material != Material.leaves && material != Material.gourd ? 1.0F : 1.5F;
        }
        
    }



}