/**
 * This represents a newspaper object in Minecraft that will contain
 * all information related to the status of player's farm
 */

package com.example.examplemod;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.World;

public class Newspaper extends Item{


	/**
	 * constructor to initialize the newspaper object.
	 */
	public Newspaper()
	{
	}

	/**
	 * Display player's farm status on right click
	 */
	public ItemStack onItemRightClick(ItemStack ItemStack, World World, EntityPlayer EntityPlayer)
    {

		Minecraft.getMinecraft().displayGuiScreen(new NewsPaperGUI());

		return ItemStack;
    }

}