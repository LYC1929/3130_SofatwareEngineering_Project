/**
 * Prints the the player's screen the approximate time in 24h style
 */
//This is the pocketWatch Class. It tells the current time of day on right click

package com.example.examplemod;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.World;

public class PocketWatch extends Item
{



	public PocketWatch()
	{

	}


	public ItemStack onItemRightClick(ItemStack ItemStack, World World, EntityPlayer EntityPlayer)
    {


		//There are 24000 ticks in a mine craft day starting at 6am. That is about 958 tickets per hour.

		int hour;

		hour=(int)(World.getWorldTime()/1000);
		hour=hour+6; //correcting for the 6 am day start

		if(hour>24)
			{

			hour=hour%24;

			}


		if(!World.isRemote)
		{	//hour=hour+6; //correcting for the 6 am time shift
		EntityPlayer.addChatMessage(new ChatComponentTranslation("BOING BOING CURRENT TIME: "+hour+"hours"));
		}

		return ItemStack;
    }

}