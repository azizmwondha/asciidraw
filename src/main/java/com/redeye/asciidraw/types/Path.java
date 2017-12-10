/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redeye.asciidraw.types;

import com.redeye.asciidraw.Ascii;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author amwon
 */
public class Path
        extends AsciiArt
{
    private final List<int[]> vertices;
    int frame = 0;

    public Path(int width,
                int height)
    {
        super(width, height);
        vertices = new ArrayList<>();
    }

    public void add(int[] v)
    {
        vertices.add(v);
    }

    public void add(List<int[]> vs)
    {
        vertices.addAll(vs);
    }

    @Override
    public void draw(Ascii ascii)
    {
        ascii.fill(background());
        int[] previous = new int[]
        {
        };
        for (int[] vertice : vertices)
        {
            if (null != vertice)
            {
                if (previous.length > 1)
                {
                    ascii.line(previous[0], previous[1], vertice[0], vertice[1]);
                }
                previous = vertice;
            }
        }
    }
}
