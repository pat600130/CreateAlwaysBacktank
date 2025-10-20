package com.example.alwaysbacktank;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.client.ConfigScreenHandler;

@Mod("alwaysbacktank")
public class AlwaysBacktankMod {

    public AlwaysBacktankMod() {
        // Load config at startup
        Config.load();

        // Register config screen for Mods menu
        // Using lambda ensures the screen is only created client-side
        ModLoadingContext.get().registerExtensionPoint(
            ConfigScreenHandler.ConfigScreenFactory.class,
            () -> new ConfigScreenHandler.ConfigScreenFactory(
                (mc, parent) -> AlwaysBacktankConfigScreen.create(parent)
            )
        );
    }
}
