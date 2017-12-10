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
public abstract class AsciiArt
{

    private final Ascii ascii;
    private final Canvas canvas;
    private int x, y, z;
    private char pen;
    private char background;

    public AsciiArt(
            int width,
            int height)
    {
        canvas = new Canvas(width, height);
        ascii = new Ascii(canvas);
        pen = '+';
        background = '\0';
    }

    public void resize(int width,
                       int height)
    {
        canvas.resize(width, height);
        ascii.resize(width, height);
    }

    public void pen(char c)
    {
        pen = c;
    }

    public void background(char c)
    {
        background = c;
    }

    protected char pen()
    {
        return pen;
    }

    protected char background()
    {
        return background;
    }

    public final AsciiArt move(int x,
                               int y)
    {
        this.x = x;
        this.y = y;
        return this;
    }

    public final AsciiArt zIndex(int z)
    {
        this.z = z;
        return this;
    }

    public final Canvas canvas()
    {
        return canvas;
    }

    public abstract void draw(Ascii ascii);

    public final void update()
    {
        draw(ascii);
    }

    public final int x()
    {
        return x;
    }

    public final int y()
    {
        return y;
    }

    public final int z()
    {
        return z;
    }

    public final int width()
    {
        return canvas.width();
    }

    public final int height()
    {
        return canvas.height();
    }
}
