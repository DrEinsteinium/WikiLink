package com.dreinsteinium.wikilink.gui;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import com.dreinsteinium.wikilink.WikiLink;
import com.dreinsteinium.wikilink.gui.util.WikiLinkContainer;
import com.dreinsteinium.wikilink.gui.widget.Widget;
import com.dreinsteinium.wikilink.gui.widget.WidgetFakeItem;
import com.dreinsteinium.wikilink.gui.widget.WidgetScrollPane;
import com.dreinsteinium.wikilink.gui.widget.WidgetShortenedString;

import cpw.mods.fml.client.FMLClientHandler;

public class GuiContainerMenu extends GuiContainer
{
	private static final TextureManager renderEngine = Minecraft.getMinecraft().renderEngine;
	private static final ResourceLocation textureLocation = new ResourceLocation("wikilink:gui/gui_menu.png");	

	private static WikiLinkContainer container;

	private String summary;
	private ItemStack item;

	public GuiContainerMenu(ItemStack item) 
	{
		super(container = new WikiLinkContainer());
		
		this.item = item;
		this.xSize = 256;
		this.ySize = 156;
	}

	@Override
	public void initGui()
	{
		super.initGui();
		
		int posX = (this.width - this.xSize) / 2;
		int posY = (this.height - this.ySize) / 2;
		
		GuiButton browserButton = new GuiButton(0, posX + 6, posY + 130, 78, 20, "Browser");
		GuiButton clipbrdButton = new GuiButton(1, posX + 88, posY + 130, 78, 20, "Clipboard");
		GuiButton summaryButton = new GuiButton(2, posX + 170, posY + 130, 78, 20, "Summarize");
		
		//if(this.summary == null)
		//	summaryButton.enabled = false;
		
		this.buttonList.add(browserButton);
		this.buttonList.add(clipbrdButton);
		this.buttonList.add(summaryButton);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) 
	{
		Minecraft.getMinecraft().getTextureManager().bindTexture(textureLocation);
	
		int posX = (this.width - this.xSize) / 2;
		int posY = (this.height - this.ySize) / 2;

		/** Draw the bg **/
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);	
		drawTexturedModalRect(posX, posY, 0, 0, this.xSize, this.ySize);

		/** Draw the widgets **/
		Widget itemic = new WidgetFakeItem(this.item, posX + 233, posY + 7, this.fontRenderer, this.renderEngine);
			itemic.draw();	
			
		Widget header = new WidgetShortenedString(this.item.getDisplayName(), posX + 61, posY + 8, 108, fontRenderer);		
			header.draw();
	}
	
	public void actionPerformed(GuiButton button)
	{
		action:switch(button.id)
		{
			case 0: //Browser
			{
				WikiLink.LogHelper.info("Browser Button");
				break action;
			}
			case 1: //Clipboard
			{
				WikiLink.LogHelper.info("Clipboard Button");
				break action;
			}
			case 2: //Summarize
			{
				WikiLink.LogHelper.info("Summarize Button");
				FMLClientHandler.instance().getClient().displayGuiScreen(new GuiContainerSummarize(this.item));
				break action;
			}
			default: // All other buttons(link selections)
			{
				
			}
		}
	}
}