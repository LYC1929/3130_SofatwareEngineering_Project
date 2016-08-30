package com.example.examplemod.keyListeners;

//Code taken and modified from http://www.minecraftforge.net/wiki/Key_Binding 
//!!!!! For some reason this class wont register its key bindings to the registry??? So we are circumventing it for now and doing all 
//the listener events through the KeyInputHandler class


import org.lwjgl.input.Keyboard;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.client.settings.KeyBinding;

public class KeyBindings {

    // Declare two KeyBindings, ping and pong
    public static KeyBinding ping;
    public static KeyBinding pong;

    
    
    public static void init() {
        // Define the "ping" binding, with (unlocalized) name "key.ping" and
        // the category with (unlocalized) name "key.categories.mymod" and
        // key code 24 ("O", LWJGL constant: Keyboard.KEY_O)
        ping = new KeyBinding("key.ping", Keyboard.KEY_O, "key.categories.mymod");
        // Define the "pong" binding, with (unlocalized) name "key.pong" and
        // the category with (unlocalized) name "key.categories.mymod" and
        // key code 25 ("P", LWJGL constant: Keyboard.KEY_P)
        pong = new KeyBinding("key.pong", Keyboard.KEY_P, "key.categories.mymod");

        // Register both KeyBindings to the ClientRegistry
        ClientRegistry.registerKeyBinding(ping);
        ClientRegistry.registerKeyBinding(pong);
        
    }

}