/**
 * NewsPaperGUI extends GuiScreen which displays information about the farm such as
 * the current rent and status of the farm.
 * Taken from http://mcreator.pylo.si/forum/5700/correct-gui-alignment-custom-images-gui-not-so-advanced
 * 			  https://www.youtube.com/watch?v=2BSTpgRIMbs
 * 			  
 */

package com.example.examplemod;

import java.awt.image.BufferedImage;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ResourceLocation;

public class NewsPaperGUI extends GuiScreen{


	public static final ResourceLocation resource_newspaper_gui = new ResourceLocation("examplemod", "textures/items/NewspaperGui.png");


	/**
	 * no-args constructor
	 */
	public NewsPaperGUI(){

	}

	/**
	 * clears the buttons frames
	 */
	public void initGui(){

		this.buttonList.clear();
	}

	/**
	 * This makes sure the game does not pause when the player is reading the newspaper
	 */
	public boolean doesGuiPauseGame()
    {
        return false;
    }

	/**
	 * drawScreen display GUI interface representing the newspaper. It uses a custom textures 
	 * to represent the frame of the newspaper. 
	 */
	public void drawScreen(int i, int j, float f){

		this.drawDefaultBackground();

		//render the custom texture
		ITextureObject texture = this.mc.renderEngine.getTexture(resource_newspaper_gui);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture(resource_newspaper_gui);
		this.drawTexturedModalRect(width/3-50, -20, 0, 0, 256,256);

		//display information about the farm.
		this.fontRendererObj.drawString("Local rent is : " + InventoryMonitor.RENT, width/3-20, 80, 0x000000, false);

		 if(InventoryMonitor.strikes==0){

			this.fontRendererObj.drawString("Local farm thriving. ", width/3-20, 95, 0x000000, false);
		 	this.fontRendererObj.drawString("Number of strikes against", width/3-20, 110, 0x000000, false);
		 	this.fontRendererObj.drawString("local farm is : " + InventoryMonitor.strikes, width/3-20, 120, 0x000000, false);
		 }

		 if(InventoryMonitor.strikes==1){

			this.fontRendererObj.drawString("Local farm facing hardships.", width/3-20, 95, 0x000000, false);
		 	this.fontRendererObj.drawString("Number of strikes against", width/3-20, 110, 0x000000, false);
		 	this.fontRendererObj.drawString("local farm is : " + InventoryMonitor.strikes, width/3-20, 120, 0x000000, false);
		 }

		 if(InventoryMonitor.strikes==2){

			this.fontRendererObj.drawString("Local farm in disaster.", width/3-20, 95, 0x000000, false);
		 	this.fontRendererObj.drawString("Number of strikes against", width/3-20, 110, 0x000000, false);
		 	this.fontRendererObj.drawString("local farm is : " + InventoryMonitor.strikes, width/3-20, 120, 0x000000, false);
		 }

		 if(InventoryMonitor.strikes==3){

				this.fontRendererObj.drawString("Local farm went under and is being", width/3-20, 95, 0x000000, false);
			 	this.fontRendererObj.drawString("sold by property owner to cover", width/3-20, 105, 0x000000, false);
			 	this.fontRendererObj.drawString("costs.", width/3-20, 115, 0x000000, false);
			 	this.fontRendererObj.drawString("Number of strikes against", width/3-20, 130, 0x000000, false);
			 	this.fontRendererObj.drawString("local farm is : " + InventoryMonitor.strikes, width/3-20, 140, 0x000000, false);
		}

		super.drawScreen(i, i, f);







	}

}