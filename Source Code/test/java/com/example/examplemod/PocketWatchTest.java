package com.example.examplemod;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import junit.framework.TestCase;

public class PocketWatchTest extends TestCase {

	PocketWatchMockObject PocketWatch = new PocketWatchMockObject();
	Worldfake World1 = new Worldfake(0);
	Worldfake World2 = new Worldfake(7000);
	Worldfake World3 = new Worldfake(23000);
	Worldfake World4 = new Worldfake(8000);


	public void testOnItemRightClick() //Passing in different night and day times to see if the method returns the correct int value
	{
		assertTrue(1==PocketWatch.onItemRightClick(null,World1,null));
		assertTrue(2==PocketWatch.onItemRightClick(null,World2,null));
		assertTrue(1==PocketWatch.onItemRightClick(null, World3, null));
		assertFalse(2==PocketWatch.onItemRightClick(null,World3,null));
		assertFalse(1==PocketWatch.onItemRightClick(null,World4,null));
	}
}