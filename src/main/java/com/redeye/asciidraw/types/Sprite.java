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
public class Sprite
        extends AsciiArt
{

    private int[] sequence = new int[0];
    private final List<AsciiArt> frames;
    int frame = 0;

    public Sprite(int width,
                  int height)
    {
        super(width, height);
        frames = new ArrayList<>();
    }

    public void add(AsciiArt a)
    {
        a.background(background());
        frames.add(a);
    }

    public void sequence(int[] s)
    {
        sequence = s;
    }

    public void next()
    {
        frame++;
        if (frame >= frames.size())
        {
            frame = 0;
        }
    }

    public void previous()
    {
        if (!frames.isEmpty())
        {
            frame--;
            if (frame < 0)
            {
                frame = frames.size() - 1;
            }
        }
    }

    @Override
    public void draw(Ascii ascii)
    {
        ascii.fill(background());
//        ascii.pen(pen());
        int frameIndex = frame;
        if (frame < sequence.length)
        {
            frameIndex = sequence[frame];
        }
        if (frameIndex < frames.size())
        {
            frames.get(frameIndex).update();
            canvas().blit(frames.get(frameIndex).canvas().raw(), frames.get(frameIndex).x(), frames.get(frameIndex).y());

            frames.get(frameIndex).move(frames.get(frameIndex).x() + 1, frames.get(frameIndex).y() + 1);
        }
        next();
    }

}
