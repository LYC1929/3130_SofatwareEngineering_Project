/**
 * Our mod class. All the items are sorted and implemented into the mod in this class. 
 */
package com.example.examplemod;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import com.example.examplemod.keyListeners.DragDropEventHandler;
import com.example.examplemod.keyListeners.KeyInputHandler;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;

import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.registry.GameData;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.*;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.player.PlayerDropsEvent;

@Mod(modid = ExampleMod.MODID, version = ExampleMod.VERSION)

public class ExampleMod {

	public static final String MODID="examplemod";
	public static final String VERSION = "1.0";


	//Item Declaration****************************************************************
	public static final Item PocketWatch = new PocketWatch().setUnlocalizedName("PocketWatch").setTextureName("PocketWatch");
	public static final Item Scythe = new Scythe().setUnlocalizedName("Scythe").setTextureName("Scythe").setMaxStackSize(1);
	public static final Item Plow = new PushPlow().setUnlocalizedName("Plow").setTextureName("Plow").setMaxStackSize(1);
	public static final Item reaperTool = new ItemReaperTool().setUnlocalizedName("reaperTool").setTextureName("Reaper Tool");
    public static final Item shovelTool = new CustomShovel().setUnlocalizedName("shovelTool").setTextureName("Shovel Tool");
    public static final Item hoeTool = new CustomHoe().setUnlocalizedName("hoeTool").setTextureName("Hoe Tool");
	public static final Item InventoryMonitor = new InventoryMonitor().setUnlocalizedName("Monitor").setTextureName("Monitor");
    public static final Item Dollar =new Dollar().setUnlocalizedName("Dollar").setTextureName("Dollar");
    public static final Item IrrigationTool = new IrrigationTool().setUnlocalizedName("Irrigation Tool").setTextureName("Irrigation Tool");
    public static final Item newspaper = new Newspaper().setUnlocalizedName("Newspaper").setTextureName("Newspaper");
    public static final Item Barometer = new Barometer().setUnlocalizedName("Barometer").setTextureName("Barometer");
    public KeyInputHandler KeyHandler = new KeyInputHandler();
    
    @EventHandler


	//Iteration 2 additions
    
	//Register Items**********************************************************************
	public void init(FMLInitializationEvent event){

    	

		//ITERATION 3*********************************************************************
    	
    	FMLCommonHandler.instance().bus().register(KeyHandler); //registering the keyhandler 
    	
    	
    	//register the event handler
    	MinecraftForge.EVENT_BUS.register(new DragDropEventHandler());
    	
    	//Newspaper****************************************************************
    	GameRegistry.registerItem(newspaper, newspaper.getUnlocalizedName());
    	newspaper.setMaxStackSize(1);
    	newspaper.setCreativeTab(CreativeTabs.tabTools);
    	newspaper.setTextureName(MODID + ":" + "Newspaper");
    	
    	GameRegistry.registerItem(Barometer,Barometer.getUnlocalizedName());
    	Barometer.setCreativeTab(CreativeTabs.tabTools);
    	Barometer.setTextureName(MODID + ":" + "Barometer");
    	//ITERATION 2 ********************************************************************
    	
    	
    	//InventoryMonitor****************************************************************
    	GameRegistry.registerItem(InventoryMonitor, InventoryMonitor.getUnlocalizedName());
    	InventoryMonitor.setMaxStackSize(1);
    	InventoryMonitor.setCreativeTab(CreativeTabs.tabTools);
    	InventoryMonitor.setTextureName(MODID + ":" + "InventoryMonitor");
    	
    	//Dollar *******************************************************
    	GameRegistry.registerItem(Dollar, Dollar.getUnlocalizedName());
    	Dollar.setCreativeTab(CreativeTabs.tabMisc);
    	Dollar.setMaxStackSize(1);
    	GameRegistry.addShapelessRecipe(new ItemStack(Dollar),Items.carrot,Items.carrot); //various recipes 
    	GameRegistry.addShapelessRecipe(new ItemStack(Dollar),Items.apple,Items.apple);
    	GameRegistry.addShapelessRecipe(new ItemStack(Dollar),Items.beef);
    	Dollar.setTextureName(MODID + ":" + "Dollar");

    	//****************************************************************
    	
    	
    	//Irrigation Tool*************************************************
    	GameRegistry.addShapelessRecipe(new ItemStack(IrrigationTool), Blocks.iron_bars, Blocks.iron_bars, Blocks.planks);
    	GameRegistry.registerItem(IrrigationTool, IrrigationTool.getUnlocalizedName());
        IrrigationTool.setTextureName("IrrigationTool");
        IrrigationTool.setTextureName(MODID + ":" + "irrigationTool");
        
    	
    	//****************************************************************
        
        
    	//Scythe**********************************************************
    	GameRegistry.addShapelessRecipe(new ItemStack(Scythe), Item.getItemById(280),Blocks.cobblestone);
    	GameRegistry.registerItem(Scythe, Scythe.getUnlocalizedName());
		Scythe.setCreativeTab(CreativeTabs.tabTools);
        Scythe.setTextureName(MODID + ":" + "Scythe");
    	//****************************************************************


		//Pocket Watch****************************************************
    	GameRegistry.addShapelessRecipe(new ItemStack(PocketWatch),Blocks.bedrock,Blocks.iron_bars,Blocks.diamond_block);
    	GameRegistry.registerItem(PocketWatch,PocketWatch.getUnlocalizedName());
        PocketWatch.setCreativeTab(CreativeTabs.tabTools);
        PocketWatch.setTextureName("Pocket Watch");
        PocketWatch.setTextureName(MODID + ":" + "PocketWatch");
        //******************************************************************

        //Push Plow****************************************************
        GameRegistry.addShapelessRecipe(new ItemStack(Plow),Blocks.iron_bars,Blocks.planks);
        GameRegistry.registerItem(Plow,Plow.getUnlocalizedName());//register item
        Plow.setCreativeTab(CreativeTabs.tabTools); //Where it appears
        Plow.setTextureName("Plow");
        Plow.setNoRepair(); //Plow can't be fixed
        Plow.setTextureName(MODID + ":" + "HandPlow"); 
        
        //Iteration 1: settings of the Reaper tool: registration, textures, recipes, name, tabs
    	GameRegistry.addShapedRecipe(new ItemStack(reaperTool), "xxy", "x y", "  y", 'x', Blocks.redstone_block, 'y', Item.getItemById(280));
    	GameRegistry.registerItem(reaperTool, reaperTool.getUnlocalizedName());
    	reaperTool.setCreativeTab(CreativeTabs.tabTools);
    	reaperTool.setTextureName(MODID + ":" + "Reaper");
    	
    	//Iteration 1: settings of the Shovel tool: registration, textures, recipes, name, tabs
    	GameRegistry.addShapedRecipe(new ItemStack(shovelTool), " x ", " y ", "yyy", 'x', Blocks.redstone_block, 'y', Item.getItemById(280));
    	GameRegistry.registerItem(shovelTool, shovelTool.getUnlocalizedName());
    	shovelTool.setCreativeTab(CreativeTabs.tabTools);
    	shovelTool.setTextureName(MODID + ":" + "CustomShovel");
    	
    	//Iteration 1: settings of the Hoe tool: registration, textures, recipes, name, tabs
    	GameRegistry.addShapedRecipe(new ItemStack(hoeTool), "xx ", " y ", " y ", 'x', Blocks.redstone_block, 'y', Item.getItemById(280));
    	GameRegistry.registerItem(hoeTool, hoeTool.getUnlocalizedName());
    	hoeTool.setCreativeTab(CreativeTabs.tabTools);
    	hoeTool.setTextureName(MODID + ":" + "CustomHoe");
        
	}
    



}