package com.example.alwaysbacktank.client;

import com.simibubi.create.content.equipment.armor.RemainingAirOverlay;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ClientSetup {
    public static void init(final FMLClientSetupEvent event) {
        // Remove Create’s overlay handler completely
        MinecraftForge.EVENT_BUS.unregister(RemainingAirOverlay.class);
    }
}

