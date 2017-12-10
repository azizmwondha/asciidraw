/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redeye.asciidraw.types;

import com.redeye.asciidraw.Ascii;
import java.io.InputStream;
import java.util.Scanner;

/**
 * Like a bitmap, but ASCII
 *
 * @author aziz
 */
public class AsciiMap
        extends AsciiArt
{

    public AsciiMap()
    {
        this(1, 1);
    }

    public AsciiMap(int width,
                    int height)
    {
        super(1, 1);
    }

    public void load(InputStream is)
    {
        char[][] buffer = new char[1024][1024];
        int w = 0;
        int h = 0;
        int xlimit = buffer[0].length;
        try (Scanner s = new Scanner(is))
        {
            s.useDelimiter("\n");
            while (s.hasNext() && (h < buffer.length))
            {
                String line = s.next();
                if (line.length()<2)
                {
                    // We don't support ASCII maps narrower
                    // than 2 pixels wide;
                    break;
                }
                if ((line.length() < xlimit))
                {
                    xlimit = line.length();
                }
                for (w = 0; w < xlimit; w++)
                {
                    buffer[h][w] = line.charAt(w);
                }
                h++;
            }
            w--; // beacause we're off by one after the for loop
        }
        resize(w, h);
        canvas().blit(buffer, 0, 0, w, h);
    }

    @Override
    public void draw(Ascii ascii)
    {
        ascii.draw(canvas());
    }
}
