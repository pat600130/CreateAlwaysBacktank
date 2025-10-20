package com.example.alwaysbacktank.client;

import java.util.List;

import com.mojang.blaze3d.systems.RenderSystem;
import com.simibubi.create.content.equipment.armor.BacktankUtil;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import net.minecraft.world.level.GameType;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectUtil;

@Mod.EventBusSubscriber(value = Dist.CLIENT)
public class AirOverlay {

    private static final ResourceLocation OVERLAY =
            ResourceLocation.fromNamespaceAndPath("create", "textures/gui/remaining_air_overlay.png");

    @SubscribeEvent
    public static void onRenderOverlay(RenderGuiOverlayEvent.Post event) {
        Minecraft mc = Minecraft.getInstance();
        LocalPlayer player = mc.player;
        if (player == null || mc.options.hideGui) return;

        List<ItemStack> backtanks = BacktankUtil.getAllWithAir(player);
        if (backtanks.isEmpty()) return;

        int totalAir = 0;
        for (ItemStack tank : backtanks) {
            totalAir += BacktankUtil.getAir(tank);
        }


        if (totalAir <= 0) return;

        GuiGraphics guiGraphics = event.getGuiGraphics();

        RenderSystem.enableBlend();
        mc.getTextureManager().bindForSetup(OVERLAY);

        int width = mc.getWindow().getGuiScaledWidth();
        int height = mc.getWindow().getGuiScaledHeight();

        int x = width / 2 + com.example.alwaysbacktank.Config.overlayX;
        int y = height - com.example.alwaysbacktank.Config.overlayY;

        int seconds = totalAir-1;
        int minutes = seconds / 60;
        int secs = seconds % 60;
        int min = minutes % 60;
        int hours = minutes / 60;

        String timeString;
        if (hours > 0)
            timeString = String.format("%d:%02d:%02d", hours, min, secs); else
            timeString = String.format("%02d:%02d", min, secs);

        // Draw text next to overlay
        int textX = x + 20;
        int textY = y + 4;
        if (!backtankvis())
        guiGraphics.drawString(mc.font, Component.literal(timeString), textX, textY, 0xFFFFFF, true);

        RenderSystem.disableBlend();
    }

    public static boolean backtankvis(){
        Minecraft mc = Minecraft.getInstance();
        if (mc.options.hideGui || mc.gameMode.getPlayerMode() == GameType.SPECTATOR)
            return false;

        LocalPlayer player = mc.player;
        if (player == null)
            return false;
        if (player.isCreative())
            return false;
        if (!player.getPersistentData()
                .contains("VisualBacktankAir"))
            return false;
        boolean isAir = player.getEyeInFluidType().isAir() || player.level().getBlockState(BlockPos.containing(player.getX(), player.getEyeY(), player.getZ())).is(Blocks.BUBBLE_COLUMN);
        boolean canBreathe = !player.canDrownInFluidType(player.getEyeInFluidType()) || MobEffectUtil.hasWaterBreathing(player) || player.getAbilities().invulnerable;
        if ((isAir || canBreathe) && !player.isInLava())
            return false;
        return true;
    }
}
