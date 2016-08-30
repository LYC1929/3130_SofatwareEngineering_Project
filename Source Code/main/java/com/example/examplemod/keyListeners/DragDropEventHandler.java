/**
 * Event handler to called when the player drag and drop into the world from the inventory.
 * The action will be cancel and this will prevent the player from dropping the item.
 */

//The general idea is from http://www.minecraftforum.net/forums/mapping-and-modding/mapping-and-modding-tutorials/1571567-1-7-2-1-6-4-eventhandler-and

package com.example.examplemod.keyListeners;

import com.example.examplemod.InventoryMonitor;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.player.PlayerDropsEvent;

public class DragDropEventHandler {

	/**
	 * onItemTossEvent is used with handle ItemTossEvent events whenever a drag and
	 * drop occurs. It will cancel the action and the item will not be dropped.
	 * @param event
	 */
	@SubscribeEvent
	public void onItemTossEvent(ItemTossEvent event){

		//save item to be removed
		ItemStack tempStack = event.entityItem.getEntityItem();


		if(tempStack.getDisplayName().equals("Inventory Monitor"))
		{//cancel ItemTossEvent

			event.setCanceled(true);
		}
		//add item back to inventory
		if(event.player.inventory.getStackInSlot(0) == null){

			//send message to player
			event.player.addChatMessage(new ChatComponentTranslation("You are not allowed to drop this Item...HAHAHA...Keep Trying!"));
			event.player.inventory.setInventorySlotContents(0, tempStack);


		}


	}


}