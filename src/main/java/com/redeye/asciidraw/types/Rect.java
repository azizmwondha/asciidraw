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
public class Rect
        extends AsciiArt {

    public Rect(
            int width,
            int height) {
        super(width, height);
    }

    @Override
    public void draw(Ascii ascii) {
        ascii.fill(background());
        ascii.pen(pen());
        ascii.line(0, 0, width() - 1, 0);
        ascii.line(0, height() - 1, width() - 1, height() - 1);
        ascii.line(0, 0, 0, height() - 1);
        ascii.line(width() - 1, 0, width() - 1, height() - 1);
    }

}
