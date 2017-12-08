/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redeye.asciidraw.types;

import com.redeye.asciidraw.Ascii;

/**
 *
 * @author aziz
 */
public class Circle
        extends AsciiArt {

    private int x, y, r;

    public Circle(int x, int y, int r,
            int width,
            int height) {
        super(width, height);
        this.x = x;
        this.y = y;
        this.r = r;
    }

    @Override
    public void draw(Ascii ascii) {
//        ascii.clear('\0');

        int d = (5 - r * 4) / 4;
        int cx = 0;
        int cy = r;

        while (cx < cy){
            ascii.plot(x + cx, y + cy);
            ascii.plot(x + cx, y - cy);
            ascii.plot(x - cx, y + cy);
            ascii.plot(x - cx, y - cy);
            ascii.plot(x + cy, y + cx);
            ascii.plot(x + cy, y - cx);
            ascii.plot(x - cy, y + cx);
            ascii.plot(x - cy, y - cx);
            if (d < 0) {
                d += 2 * cx + 1;
            } else {
                d += 2 * (cx - cy) + 1;
                cy--;
            }
            cx++;
        }
    }
}
