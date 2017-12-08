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
public class Oval
        extends AsciiArt {

    public Oval(int width,
            int height) {
        super(width, height);
    }

    @Override
    public void draw(Ascii ascii) {
        ascii.fill(background());
        ascii.pen(pen());
        
        double rx = (width() - 1) / 2.0d; // center x
        double ry = (height() - 1) / 2.0d; // center y
        double ox;
        double oy;

        double delta = 1 / Math.sqrt(Math.pow(width(), 2) + Math.pow(height(), 2));

        for (double theta = 0.0d; theta < (Math.PI * 2); theta += delta) {
            ox = rx + rx * Math.cos(theta);
            oy = ry - ry * Math.sin(theta);
            ascii.plot(ox, oy);
        }
    }
}
