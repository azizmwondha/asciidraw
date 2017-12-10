/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redeye.asciidraw.types;

/**
 *
 * @author amwon
 */
public class Canvas
{

    private char[][] canvas;
    private int clipTop, clipBottom, clipLeft, clipRight;

    public Canvas(int width,
                  int height)
    {
        canvas = new char[height][width];
        clipTop = 0;
        clipBottom = height;
        clipLeft = 0;
        clipRight = width;
    }

    public void resize(int width,
                       int height)
    {
        canvas = new char[height][width];
    }

    public void blit(char[][] raw,
                     int x,
                     int y)
    {
        blit(raw, x, y, raw[0].length, raw.length);
    }

    public void blit(char[][] raw,
                     int x,
                     int y,
                     int w,
                     int h)
    {
        // blit all raw chars to canvas
        for (int ry = 0; ry < h; ry++)
        {
            for (int rx = 0; rx < w; rx++)
            {
                if ((raw[ry][rx] > 0))
                {
                    if (withinBounds(rx + x, ry + y))
                    {
                        canvas[ry + y][rx + x] = raw[ry][rx];
                    }
                }
            }
        }
    }

    public void plot(int x,
                     int y,
                     char pen)
    {
        if (withinBounds(x, y))
        {
            canvas[y][x] = pen;
        }
    }

    public void fill(char pen)
    {
        fill(clipLeft, clipTop, clipRight, clipBottom, pen);
    }

    public void fill(
            int left,
            int top,
            int right,
            int bottom,
            char pen)
    {
        for (int y = top; y < bottom; y++)
        {
            for (int x = left; x < right; x++)
            {
                plot(x, y, pen);
            }
        }
    }

    public void clip(int left, int top,
                     int right,
                     int bottom)
    {
        clipTop = limitTo(top, 0, height() - 1);
        clipBottom = limitTo(height() - bottom, clipTop + 1, height());
        clipLeft = limitTo(left, 0, width() - 1);
        clipRight = limitTo(width() - right, clipLeft + 1, width());
    }

    public char[][] raw()
    {
        return canvas;
    }

    public void raw(char[][] raw)
    {
        canvas = raw;
    }

    private boolean withinBounds(int x,
                                 int y)
    {
        return (within(x, 0, width()) && within(y, 0, height()));
    }

    private int limitTo(int value,
                        int min,
                        int max)
    {
        if (value < min)
        {
            return min;
        }
        if (value > max)
        {
            return max;
        }
        return value;
    }

    private boolean within(int value,
                           int min,
                           int max)
    {
        return ((value >= min) && (value < max));
    }

    public void print()
    {
        for (int y = 0; y < canvas.length; y++)
        {
            System.out.println(new String(canvas[y]).replace('\0', ' '));
        }
        System.out.println("w/h " + width() + "/" + height() + " clip trbl " + clipTop + "," + clipRight + "," + clipBottom + "," + clipLeft);
    }

    public final int width()
    {
        return canvas[0].length;
    }

    public final int height()
    {
        return canvas.length;
    }
}
