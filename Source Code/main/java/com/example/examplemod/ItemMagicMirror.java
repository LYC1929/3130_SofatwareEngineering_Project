/*
package com.example.examplemod;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;

public class ItemMagicMirror extends Item{

	private boolean playNotes;
	private int start;
	private int uses = 50;
	private int cooldown = 60; //Cooldown: 60 ticks

	protected ItemMagicMirror(int i)
	{
		super();
		maxStackSize = 1;
	}
	public void onUpdate(ItemStack itemstack, World world, Entity entity, int i, boolean flag)
	{
		super.onUpdate(itemstack, world, entity, i, flag);
		if(cooldown < 60) //Modify this to match cooldown value
		{
			cooldown++;
		}
	}
	/**
	* Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
	*/



/*
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer)
	{
		if(cooldown < 60) //Modify this to match cooldown as well
		{
			return itemstack;
		}
		entityplayer.addChatMessage("You Gaze In The Magic Mirror And Suddenly Appear At Home!");
		ChunkCoordinates chunkcoordinates = entityplayer.getSpawnChunk();
		if (chunkcoordinates == null)
		{
			chunkcoordinates = world.getSpawnPoint();
		}
		int i;
		for (i = 126; i > 16 && !world.isBlockNormalCube(chunkcoordinates.posX, i, chunkcoordinates.posZ) && !world.isBlockNormalCube(chunkcoordinates.posX, i + 1, chunkcoordinates.posZ); i--) { }
		boolean flag = true;
			entityplayer.setPosition((double)chunkcoordinates.posX + 0.5D, i + 3, (double)chunkcoordinates.posZ + 0.5D);
		if (this.uses > 0)
		{
			itemstack.damageItem(1, entityplayer);
		}

		cooldown = 0; //Resets cooldown
		return itemstack;
	}
	public boolean hasEffect(ItemStack par1ItemStack)
	{
		return true ;
	}
}
*/