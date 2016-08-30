/** 
 * currency object used with the item Monitor class. Represents one dollar.
 */
//Iteration 2 FILE.
//This is our currency object. It will be auto withdrawn from the player's inventory every X days, where X is set in the item monitor class
package com.example.examplemod;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.World;

public class Dollar extends Item {


	/**
	 * This constructor is used to set the object and the texture name at the
	 * time of initialization.
	 */
	public Dollar()
	{
		super.setUnlocalizedName("Dollar");
		super.setTextureName("Dollar");
	}

}