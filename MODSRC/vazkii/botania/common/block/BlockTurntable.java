/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * 
 * Botania is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 * 
 * File Created @ [Mar 16, 2014, 10:08:14 PM (GMT)]
 */
package vazkii.botania.common.block;

import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import vazkii.botania.api.IWandHUD;
import vazkii.botania.api.IWandable;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.common.block.tile.TileTurntable;
import vazkii.botania.common.lib.LibBlockIDs;
import vazkii.botania.common.lib.LibBlockNames;

public class BlockTurntable extends BlockModContainer implements IWandable, IWandHUD {

	Icon[] icons;
	
	protected BlockTurntable() {
		super(LibBlockIDs.idTurntable, Material.wood);
		setHardness(2.0F);
		setStepSound(soundWoodFootstep);
		setUnlocalizedName(LibBlockNames.TURNTABLE);
	}
	
	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		icons = new Icon[2];
		for(int i = 0; i < icons.length; i++)
			icons[i] = IconHelper.forBlock(par1IconRegister, this, i);
	}
	
	@Override
	public Icon getIcon(int par1, int par2) {
		return par1 == 1 ? icons[0] : icons[1];
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileTurntable();
	}

	@Override
	public void renderHUD(Minecraft mc, ScaledResolution res, World world, int x, int y, int z) {
		((TileTurntable) world.getBlockTileEntity(x, y, z)).renderHUD(mc, res);
	}

	@Override
	public boolean onUsedByWand(EntityPlayer player, ItemStack stack, World world, int x, int y, int z, int side) {
		((TileTurntable) world.getBlockTileEntity(x, y, z)).onWanded(player, stack);
		return true;
	}

}
