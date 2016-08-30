//Code taken and modified from http://www.minecraftforge.net/wiki/Key_Binding 
////
/**
 * used to handle key listeners in game and modify game stats dynamically
 */
package com.example.examplemod.keyListeners;

import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.ChatComponentTranslation;

import org.lwjgl.input.Keyboard;

import com.example.examplemod.InventoryMonitor;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;

public class KeyInputHandler {

	public KeyBinding diffUp = new KeyBinding("key.difficultyUP", Keyboard.KEY_P, "key.categories.mymod");
	public KeyBinding diffDown = new KeyBinding("key.difficultyDOWN", Keyboard.KEY_L, "key.categories.mymod");

	/**
	 * Listens for Key Events and responds by changing the game difficulty
	 * @param event
	 */
	@SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
		ClientRegistry.registerKeyBinding(diffDown);
		ClientRegistry.registerKeyBinding(diffUp);

		if(diffUp.isPressed())
		{ 
			IncreaseDifficulty();
			InventoryMonitor.user.addChatMessage(new ChatComponentTranslation("Increasing Difficulty. You now owe $"+InventoryMonitor.RENT+" at the end of this pay period"));

		}	
		if(diffDown.isPressed())
		{ 
			DecreasingDifficulty();
			InventoryMonitor.user.addChatMessage(new ChatComponentTranslation("Decreasing Difficulty. You now owe $"+InventoryMonitor.RENT+" at the end of this pay period"));

		}

	}

/**
 * Increases the rent due/timeframe
 */
//decreases the game difficulty
	private void DecreasingDifficulty() {
		if(InventoryMonitor.RENT!=0) //don't want negative rent
		InventoryMonitor.RENT=InventoryMonitor.RENT-5; //then subtract 5 dollars from the current rent value

	}

/**
 * Decreases the rent due/timeframe
 */
//increases the game difficulty
	public void IncreaseDifficulty()
	{
		InventoryMonitor.RENT=InventoryMonitor.RENT+5;	//increase the rent value by 5 dollars	
	}

}