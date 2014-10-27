package io.cyb3rwarri0r8.commumod;


import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import io.cyb3rwarri0r8.commumod.blocks.ModBlocks;
import io.cyb3rwarri0r8.commumod.config.ConfigHandler;
import io.cyb3rwarri0r8.commumod.entity.EntityMiner;
import io.cyb3rwarri0r8.commumod.entity.ModEntities;
import io.cyb3rwarri0r8.commumod.event.ModEventHandler;
import io.cyb3rwarri0r8.commumod.help.ModBucketHandler;
import io.cyb3rwarri0r8.commumod.help.Reference;
import io.cyb3rwarri0r8.commumod.items.ModItems;
import io.cyb3rwarri0r8.commumod.proxy.proxyCommon;
import io.cyb3rwarri0r8.commumod.world.modWorld;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;


@Mod(modid = io.cyb3rwarri0r8.commumod.help.Reference.MODID, version = io.cyb3rwarri0r8.commumod.help.Reference.VERSION, guiFactory = Reference.GuiFactoryClass)



public class main
{
    @SidedProxy(clientSide="io.cyb3rwarri0r8.commumod.proxy.proxyClient", serverSide="io.cyb3rwarri0r8.commumod.proxy.proxyCommon")
    public static proxyCommon proxy;

    // Create a new creative tab
    public static CreativeTabs modTab = new CreativeTabsCommuMod("modTab");
    // Configuration file
    public static Configuration configFile;


    @Mod.Instance(Reference.MODID)
    public static main instance;


    @Mod.EventHandler
    public void preinit(FMLPreInitializationEvent event) {

        configFile = new Configuration(event.getSuggestedConfigurationFile());

        ModBlocks.loadBlocks();
        ModItems.loadItems();
        modWorld.initWorldGen();
        ModEntities.init();
        ConfigHandler.init(configFile.getConfigFile());



        proxy.registerRenderers();
        proxy.registerEntitySpawn();
        proxy.registerTileEntities();
        proxy.registerNetwork();
        proxy.preinit();


    }


    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {

//        NetworkRegistry.INSTANCE.registerGuiHandler(instance, proxy);
        LanguageRegistry.instance().addStringLocalization("itemGroup.modTab", "en_US", "CommuMod");
        EntityRegistry.addSpawn(EntityMiner.class, 2, 1, 3, EnumCreatureType.creature, new BiomeGenBase[]{BiomeGenBase.extremeHills});


    }

    @Mod.EventHandler
    public void load(FMLPostInitializationEvent event)
    {

        MinecraftForge.EVENT_BUS.register(new ModEventHandler());
        FMLCommonHandler.instance().bus().register(new ModEventHandler());

        ModBucketHandler.INSTANCE.buckets.put(ModBlocks.pureWaterBlock, ModItems.pureWaterBucket);
        MinecraftForge.EVENT_BUS.register(ModBucketHandler.INSTANCE);

    }


}