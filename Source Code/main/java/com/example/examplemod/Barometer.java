/**
 * Barometer object: Reads the time until rain and thunder provided by a world info object. Rain will hydrate farmland and increase the amount of fish in a pool of water
 */

package com.example.examplemod;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.World;

public class Barometer extends Item {

	//Minecraft MC = Minecraft.getMinecraft();

	public Barometer()
	{

	}

/**
 * Reads the world rain time and thunder value, converts it to hours, and tells the player how long until it will occur
 */
	public ItemStack onItemRightClick(ItemStack ItemStack, World World, EntityPlayer EntityPlayer)
    {
		float Raintime=(World.getWorldInfo().getRainTime()/1000);
		float ThunderTime=(World.getWorldInfo().getThunderTime()/1000);
		//System.out.println(World.isRemote);
		if(!World.isRemote)//stopping the double method call by only running code on the server side. World.isRemote will return true if it is the client calling the method, and false if it is the server. We want the server
			{

			//if the time is less than 1 day
			if(Raintime<24)
				{
					EntityPlayer.addChatMessage(new ChatComponentTranslation("Local weather calls for rain within the next 24 hours"));
				}
		//if the time is greater or equal to one day
			if(Raintime>=24)
				{
					EntityPlayer.addChatMessage(new ChatComponentTranslation("No chance of rain for the next 24 hours"));
				}

			if(ThunderTime<24)
				{
					EntityPlayer.addChatMessage(new ChatComponentTranslation("Local weather calls for Thunder within the next 24 hours"));
				}

			if(ThunderTime>=24)
				{
					EntityPlayer.addChatMessage(new ChatComponentTranslation("No chance of Thunder for the next 24 hours"));
				}

		System.out.println(Raintime);
		System.out.println(ThunderTime);
			}


		//System.out.println("Hello");



		return ItemStack;
    
    }





}