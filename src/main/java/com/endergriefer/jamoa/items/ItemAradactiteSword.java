package com.endergriefer.jamoa.items;

import com.endergriefer.jamoa.help.Reference;
import com.endergriefer.jamoa.jamoa;
import net.minecraft.item.ItemSword;

/**
 * Created by noah on 5/18/14.
 */
public class ItemAradactiteSword extends ItemSword {
    public ItemAradactiteSword(ToolMaterial material) {
        super(material);
        setUnlocalizedName("aradactiteSword");
        setTextureName(Reference.MODID + ":" + getUnlocalizedName().substring(5));
        setCreativeTab(jamoa.jamoaTab);
    }
}
