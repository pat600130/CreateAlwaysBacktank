//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package net.pat600.neoforge;

import com.google.common.hash.Hashing;
import java.util.function.UnaryOperator;
import javax.annotation.Nonnull;
//import net.createmod.catnip.utility.Couple;
import net.minecraft.network.chat.Style;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;
import org.joml.Vector3f;

public class ColorCopy {
    public static final net.pat600.neoforge.ColorCopy TRANSPARENT_BLACK = (new net.pat600.neoforge.ColorCopy(0, 0, 0, 0)).setImmutable();
    public static final net.pat600.neoforge.ColorCopy BLACK = (new net.pat600.neoforge.ColorCopy(0, 0, 0)).setImmutable();
    public static final net.pat600.neoforge.ColorCopy WHITE = (new net.pat600.neoforge.ColorCopy(255, 255, 255)).setImmutable();
    public static final net.pat600.neoforge.ColorCopy RED = (new net.pat600.neoforge.ColorCopy(255, 0, 0)).setImmutable();
    public static final net.pat600.neoforge.ColorCopy GREEN = (new net.pat600.neoforge.ColorCopy(0, 255, 0)).setImmutable();
    public static final net.pat600.neoforge.ColorCopy PURPLE = (new net.pat600.neoforge.ColorCopy(128, 0, 128)).setImmutable();
    public static final net.pat600.neoforge.ColorCopy SPRING_GREEN = (new net.pat600.neoforge.ColorCopy(0, 255, 187)).setImmutable();
    protected boolean mutable;
    protected int value;

    public ColorCopy(int r, int g, int b) {
        this(r, g, b, 255);
    }

    public ColorCopy(int r, int g, int b, int a) {
        this.mutable = true;
        this.value = (a & 255) << 24 | (r & 255) << 16 | (g & 255) << 8 | (b & 255) << 0;
    }

    public ColorCopy(float r, float g, float b, float a) {
        this((int)((double)0.5F + (double)(255.0F * Mth.clamp(r, 0.0F, 1.0F))), (int)((double)0.5F + (double)(255.0F * Mth.clamp(g, 0.0F, 1.0F))), (int)((double)0.5F + (double)(255.0F * Mth.clamp(b, 0.0F, 1.0F))), (int)((double)0.5F + (double)(255.0F * Mth.clamp(a, 0.0F, 1.0F))));
    }

    public ColorCopy(int argb) {
        this.mutable = true;
        this.value = argb;
    }

    public ColorCopy(int argb, boolean hasAlpha) {
        this.mutable = true;
        if (hasAlpha) {
            this.value = argb;
        } else {
            this.value = argb | -16777216;
        }

    }

    public net.pat600.neoforge.ColorCopy copy() {
        return this.copy(true);
    }

    public net.pat600.neoforge.ColorCopy copy(boolean mutable) {
        return mutable ? new net.pat600.neoforge.ColorCopy(this.value) : (new net.pat600.neoforge.ColorCopy(this.value)).setImmutable();
    }

    public net.pat600.neoforge.ColorCopy setImmutable() {
        this.mutable = false;
        return this;
    }

    public int getRed() {
        return this.getRGB() >> 16 & 255;
    }

    public int getGreen() {
        return this.getRGB() >> 8 & 255;
    }

    public int getBlue() {
        return this.getRGB() >> 0 & 255;
    }

    public int getAlpha() {
        return this.getRGB() >> 24 & 255;
    }

    public float getRedAsFloat() {
        return (float)this.getRed() / 255.0F;
    }

    public float getGreenAsFloat() {
        return (float)this.getGreen() / 255.0F;
    }

    public float getBlueAsFloat() {
        return (float)this.getBlue() / 255.0F;
    }

    public float getAlphaAsFloat() {
        return (float)this.getAlpha() / 255.0F;
    }

    public int getRGB() {
        return this.value;
    }

    public Vec3 asVector() {
        return new Vec3((double)this.getRedAsFloat(), (double)this.getGreenAsFloat(), (double)this.getBlueAsFloat());
    }

    public Vector3f asVectorF() {
        return new Vector3f(this.getRedAsFloat(), this.getGreenAsFloat(), this.getBlueAsFloat());
    }

    public Style asStyle() {
        return Style.EMPTY.withColor(this.value);
    }

    public net.pat600.neoforge.ColorCopy setRed(int r) {
        return this.ensureMutable().setRedUnchecked(r);
    }

    public net.pat600.neoforge.ColorCopy setGreen(int g) {
        return this.ensureMutable().setGreenUnchecked(g);
    }

    public net.pat600.neoforge.ColorCopy setBlue(int b) {
        return this.ensureMutable().setBlueUnchecked(b);
    }

    public net.pat600.neoforge.ColorCopy setAlpha(int a) {
        return this.ensureMutable().setAlphaUnchecked(a);
    }

    public net.pat600.neoforge.ColorCopy setRed(float r) {
        return this.ensureMutable().setRedUnchecked((int)(255.0F * Mth.clamp(r, 0.0F, 1.0F)));
    }

    public net.pat600.neoforge.ColorCopy setGreen(float g) {
        return this.ensureMutable().setGreenUnchecked((int)(255.0F * Mth.clamp(g, 0.0F, 1.0F)));
    }

    public net.pat600.neoforge.ColorCopy setBlue(float b) {
        return this.ensureMutable().setBlueUnchecked((int)(255.0F * Mth.clamp(b, 0.0F, 1.0F)));
    }

    public net.pat600.neoforge.ColorCopy setAlpha(float a) {
        return this.ensureMutable().setAlphaUnchecked((int)(255.0F * Mth.clamp(a, 0.0F, 1.0F)));
    }

    public net.pat600.neoforge.ColorCopy scaleAlpha(float factor) {
        return this.ensureMutable().setAlphaUnchecked((int)((float)this.getAlpha() * Mth.clamp(factor, 0.0F, 1.0F)));
    }

    public net.pat600.neoforge.ColorCopy scaleAlphaForText(float factor) {
        return this.ensureMutable().setAlphaUnchecked(Math.max(5, (int)((float)this.getAlpha() * Mth.clamp(factor, 0.0F, 1.0F))));
    }

    public net.pat600.neoforge.ColorCopy mixWith(net.pat600.neoforge.ColorCopy other, float weight) {
        return this.ensureMutable().setRedUnchecked((int)((float)this.getRed() + (float)(other.getRed() - this.getRed()) * weight)).setGreenUnchecked((int)((float)this.getGreen() + (float)(other.getGreen() - this.getGreen()) * weight)).setBlueUnchecked((int)((float)this.getBlue() + (float)(other.getBlue() - this.getBlue()) * weight)).setAlphaUnchecked((int)((float)this.getAlpha() + (float)(other.getAlpha() - this.getAlpha()) * weight));
    }

    public net.pat600.neoforge.ColorCopy darker() {
        int a = this.getAlpha();
        return this.ensureMutable().mixWith(BLACK, 0.25F).setAlphaUnchecked(a);
    }

    public net.pat600.neoforge.ColorCopy brighter() {
        int a = this.getAlpha();
        return this.ensureMutable().mixWith(WHITE, 0.25F).setAlphaUnchecked(a);
    }

    public net.pat600.neoforge.ColorCopy setValue(int value) {
        return this.ensureMutable().setValueUnchecked(value);
    }

    public net.pat600.neoforge.ColorCopy modifyValue(UnaryOperator<Integer> function) {
        int newValue = (Integer)function.apply(this.value);
        return newValue == this.value ? this : this.ensureMutable().setValueUnchecked(newValue);
    }

    public net.pat600.neoforge.ColorCopy ensureMutable() {
        return this.mutable ? this : new net.pat600.neoforge.ColorCopy(this.value);
    }

    protected net.pat600.neoforge.ColorCopy setRedUnchecked(int r) {
        this.value = this.value & -16711681 | (r & 255) << 16;
        return this;
    }

    protected net.pat600.neoforge.ColorCopy setGreenUnchecked(int g) {
        this.value = this.value & -65281 | (g & 255) << 8;
        return this;
    }

    protected net.pat600.neoforge.ColorCopy setBlueUnchecked(int b) {
        this.value = this.value & -256 | (b & 255) << 0;
        return this;
    }

    protected net.pat600.neoforge.ColorCopy setAlphaUnchecked(int a) {
        this.value = this.value & 16777215 | (a & 255) << 24;
        return this;
    }

    protected net.pat600.neoforge.ColorCopy setValueUnchecked(int value) {
        this.value = value;
        return this;
    }

    public static net.pat600.neoforge.ColorCopy mixColors(@Nonnull net.pat600.neoforge.ColorCopy c1, @Nonnull net.pat600.neoforge.ColorCopy c2, float w) {
        return new net.pat600.neoforge.ColorCopy((int)((float)c1.getRed() + (float)(c2.getRed() - c1.getRed()) * w), (int)((float)c1.getGreen() + (float)(c2.getGreen() - c1.getGreen()) * w), (int)((float)c1.getBlue() + (float)(c2.getBlue() - c1.getBlue()) * w), (int)((float)c1.getAlpha() + (float)(c2.getAlpha() - c1.getAlpha()) * w));
    }

    /*public static net.pat600.neoforge.ColorCopy mixColors(@Nonnull Couple<net.pat600.neoforge.ColorCopy> colors, float w) {
        return mixColors((net.pat600.neoforge.ColorCopy)colors.getFirst(), (net.pat600.neoforge.ColorCopy)colors.getSecond(), w);
    }*/

    public static int mixColors(int color1, int color2, float w) {
        int a1 = color1 >> 24;
        int r1 = color1 >> 16 & 255;
        int g1 = color1 >> 8 & 255;
        int b1 = color1 & 255;
        int a2 = color2 >> 24;
        int r2 = color2 >> 16 & 255;
        int g2 = color2 >> 8 & 255;
        int b2 = color2 & 255;
        return ((int)((float)a1 + (float)(a2 - a1) * w) << 24) + ((int)((float)r1 + (float)(r2 - r1) * w) << 16) + ((int)((float)g1 + (float)(g2 - g1) * w) << 8) + ((int)((float)b1 + (float)(b2 - b1) * w) << 0);
    }

    public static net.pat600.neoforge.ColorCopy rainbowColor(int timeStep) {
        int localTimeStep = Math.abs(timeStep) % 1536;
        int timeStepInPhase = localTimeStep % 256;
        int phaseBlue = localTimeStep / 256;
        int red = colorInPhase(phaseBlue + 4, timeStepInPhase);
        int green = colorInPhase(phaseBlue + 2, timeStepInPhase);
        int blue = colorInPhase(phaseBlue, timeStepInPhase);
        return new net.pat600.neoforge.ColorCopy(red, green, blue);
    }

    private static int colorInPhase(int phase, int progress) {
        phase %= 6;
        if (phase <= 1) {
            return 0;
        } else if (phase == 2) {
            return progress;
        } else {
            return phase <= 4 ? 255 : 255 - progress;
        }
    }

    public static net.pat600.neoforge.ColorCopy generateFromLong(long l) {
        return rainbowColor(Hashing.crc32().hashLong(l).asInt()).mixWith(WHITE, 0.5F);
    }
}
