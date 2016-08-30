//A slightly modified version of the PocketWatch item. It has two return values to test if the if else
//statement is executing correctly

package com.example.examplemod;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.World;

public class PocketWatchMockObject 
{
	public PocketWatchMockObject()
	{

	}


	public int onItemRightClick(ItemStack ItemStack, Worldfake World, EntityPlayer EntityPlayer)
    {
		//There are 23000 ticks in a mine craft day starting at 6am. That is about 958 tickets per hour.
		if(World.getWorldTime()<6000||World.getWorldTime()>18000) //13000 is about 7pm
		{	

			System.out.println("Ticks"+(World.getWorldTime()));
		//EntityPlayer.addChatMessage(new ChatComponentTranslation("BOING BOING CURRENT TIME: "+(int)((World.getWorldTime()/958.33)+6)+"AM"));
		return 1;
		}
		else
		{	

			System.out.println("Ticks"+(World.getWorldTime()));
			//EntityPlayer.addChatMessage(new ChatComponentTranslation("BOING BOING CURRENT TIME: "+(int)((World.getWorldTime()/958.33)+6)+"PM"));
		return 2;
		}	
    
		//return ItemStack;
    }

}