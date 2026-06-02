
package net.pat600.neoforge;

public class ColorCopy {

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
}
