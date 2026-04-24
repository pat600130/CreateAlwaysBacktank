package net.pat600.neoforge.mixin;


import com.mojang.blaze3d.vertex.PoseStack;
import net.createmod.catnip.gui.element.GuiGameElement;
import net.createmod.catnip.utility.theme.Color;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.LayeredDraw;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.util.StringUtil;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameType;

import org.spongepowered.asm.mixin.Mixin;

import static com.simibubi.create.content.equipment.armor.RemainingAirOverlay.getDisplayedBacktank;


@Mixin(com.simibubi.create.content.equipment.armor.RemainingAirOverlay.class)
public class MixinRemainingAirOverlay implements LayeredDraw.Layer {

    public void render(GuiGraphics guiGraphics, DeltaTracker deltaTracker) {
        Minecraft mc = Minecraft.getInstance();
        if (!mc.options.hideGui && mc.gameMode.getPlayerMode() != GameType.SPECTATOR) {
            LocalPlayer player = mc.player;
            if (player != null) {
                int timeLeft = player.getPersistentData().getInt("VisualBacktankAir");
                PoseStack poseStack = guiGraphics.pose();
                poseStack.pushPose();
                ItemStack backtank = getDisplayedBacktank(player);
                poseStack.translate((float)(guiGraphics.guiWidth() / 2 + 90), (float)(guiGraphics.guiHeight() - 53 + (backtank.has(DataComponents.FIRE_RESISTANT) ? 9 : 0)), 0.0F);
                Component text = Component.literal(StringUtil.formatTickDuration(Math.max(0, timeLeft - 1) * 20, mc.level.tickRateManager().tickrate()));
                GuiGameElement.of(backtank).at(0.0F, 0.0F).render(guiGraphics);
                int color = -1;
                if (timeLeft < 60 && timeLeft % 2 == 0) {
                    color = Color.mixColors(-65536, color, Math.max((float)timeLeft / 60.0F, 0.25F));
                }

                guiGraphics.drawString(mc.font, text, 16, 5, color);
                poseStack.popPose();
                }
            }
    }
}
