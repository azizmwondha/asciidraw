/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redeye.asciidraw.types;

import com.redeye.asciidraw.Ascii;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * AsciiDraw font super class.
 *
 * @author aziz
 */
public abstract class AsciiFont
        extends AsciiArt
{
    private final List<AsciiArt> glyphs;
    private String alphabet;

    public AsciiFont(int width,
                     int height)
    {
        super(width, height);

        glyphs = new ArrayList<>();
//        alphabet=new String();
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
            while (s.hasNext())
            {
                String line = s.next();
                if ((xlimit == buffer[0].length) && (line.length() < xlimit))
                {
                    xlimit = line.length();
                }
                if (h < buffer.length)
                {
                    for (w = 0; w < xlimit; w++)
                    {
                        buffer[h][w] = line.charAt(w);
                    }
                    h++;
                }
                else
                {
                    break;
                }
            }
        }
        canvas().resize(xlimit, h - 1);
        canvas().blit(buffer, 0, 0, xlimit, h - 1);
    }

    @Override
    public void draw(Ascii ascii)
    {
        ascii.draw(canvas());
    }

    public final void print(String s)
    {
        int xo = 0;
        int yo = 0;
        for (char c : s.toCharArray())
        {
            if (alphabet.contains(s))
            {
                int i = alphabet.indexOf(s);
                glyphs.get(i).update();
                canvas().blit(glyphs.get(i).canvas().raw(), x() + xo, y() + yo);
            }
        }
    }
}
