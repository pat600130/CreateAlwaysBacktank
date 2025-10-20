package com.example.alwaysbacktank;

import dev.isxander.yacl3.api.ConfigCategory;
import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.YetAnotherConfigLib;
import dev.isxander.yacl3.api.controller.IntegerSliderControllerBuilder;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class AlwaysBacktankConfigScreen {
    public static Screen create(Screen parent) {
        try {
            return YetAnotherConfigLib.createBuilder()
                    .title(Component.literal("AlwaysBacktank Config"))
                    .category(ConfigCategory.createBuilder()
                            .name(Component.literal("Overlay Position"))
                            .option(Option.<Integer>createBuilder()
                                    .name(Component.literal("X Offset"))
                                    .binding(0, () -> Config.overlayX, v -> Config.overlayX = v)
                                    .controller(opt -> IntegerSliderControllerBuilder.create(opt).range(-200, 200))
                                    .build())
                            .option(Option.<Integer>createBuilder()
                                    .name(Component.literal("Y Offset"))
                                    .binding(0, () -> Config.overlayY, v -> Config.overlayY = v)
                                    .controller(opt -> IntegerSliderControllerBuilder.create(opt).range(0, 200))
                                    .build())
                            .build())
                    .save(Config::save)
                    .build()
                    .generateScreen(parent);
        } catch (Exception e) {
            e.printStackTrace(); // log errors to see why screen failed
            return parent; // fallback
        }
    }
}
