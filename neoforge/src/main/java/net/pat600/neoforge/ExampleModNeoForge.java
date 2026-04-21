package net.pat600.neoforge;

import net.neoforged.fml.common.Mod;

import net.pat600.ExampleMod;

@Mod(ExampleMod.MOD_ID)
public final class ExampleModNeoForge {
    public ExampleModNeoForge() {
        // Run our common setup.
        ExampleMod.init();
    }
}
