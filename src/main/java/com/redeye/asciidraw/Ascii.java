/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redeye.asciidraw;

import com.redeye.asciidraw.types.AsciiArt;
import com.redeye.asciidraw.types.Canvas;
import com.redeye.asciidraw.types.ZIndexComparator;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 *
 * @author aziz
 */
public class Ascii
{
    private Canvas canvas;
    private final ConcurrentSkipListSet<AsciiArt> children;
    private char pen = '+';
    private char background = '\0';

    public Ascii(int width,
                 int height)
    {
        this(new Canvas(width, height));
    }

    public Ascii(Canvas c)
    {
        canvas = c;
        children = new ConcurrentSkipListSet<>(new ZIndexComparator());
    }

    public void resize(int width,
                       int height)
    {
        canvas.resize(width, height);
    }

    public void add(AsciiArt art)
    {
        children.add(art);
    }

    private void art(AsciiArt art)
    {
        art.update();
        canvas.blit(art.canvas().raw(), art.x(), art.y());
    }

    public void box(int x1,
                    int y1,
                    int x2,
                    int y2)
    {
        fill(y1, x2, y2, x1);
    }

    public void path(int[][] vertices)
    {
        if (null != vertices)
        {
            int[] first = null;
            int[] previous = null;
            for (int[] vertice : vertices)
            {
                if (null != previous)
                {
                    line(previous[0], previous[1], vertice[0], vertice[1]);
                }
                else
                {
                    first = vertice;
                }
                previous = vertice;
            }
            if (null != previous)
            {
                line(previous[0], previous[1], first[0], first[1]);
            }
        }
    }

    public void line(int x1,
                     int y1,
                     int x2,
                     int y2)
    {
        float xdiff = x2 - x1;
        float ydiff = y2 - y1;
        float steps = Math.max(Math.abs(xdiff), Math.abs(ydiff));
        if (steps == 0)
        {
            plot(x1, y1);
            return;
        }

        float x = x1;
        float y = y1;
        float xstep = (float) (xdiff / steps);
        float ystep = (float) (ydiff / steps);

        for (; steps >= 0; steps--)
        {
            plot((int) x, (int) y);
            x += xstep;
            y += ystep;
        }
    }

    public void fill(char c)
    {
        canvas.fill(c);
    }

    private void fill(
            int top,
            int right,
            int bottom,
            int left)
    {
        canvas.fill(top, right, bottom, left, pen);
    }

    public void plot(double x,
                     double y)
    {
        plot((int) Math.round(x), (int) Math.round(y));
    }

    public void plot(int x,
                     int y)
    {
        canvas.plot(x, y, pen);
    }

    public void clip(int top,
                     int right,
                     int bottom,
                     int left)
    {
        canvas.clip(top, right, bottom, left);
    }

    public void pen(char c)
    {
        pen = c;
    }

    public void background(char c)
    {
        background = c;
    }

    public final void update()
    {
        if (!children.isEmpty())
        {
            for (AsciiArt art : children)
            {
                art(art);
            }
        }
    }

    public void draw(Canvas c)
    {
        canvas.blit(c.raw(), c.width(), c.height());
    }

    public final Canvas map()
    {
        return canvas;
    }

    public void print()
    {
        canvas.print();
    }
}
