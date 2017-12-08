/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redeye.asciidraw.gfx;

import com.redeye.asciidraw.Ascii;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author amwon
 */
public class AsciiPanel
        extends JPanel
{

    private final Font font;
    private final Ascii ascii;

    public AsciiPanel(Ascii a)
    {
        ascii = a;
        font = new Font(Font.MONOSPACED, Font.PLAIN, 12); // Font.getFont("courier");
    }

    @Override
    public void paintComponent(Graphics g)
    {
        g.setColor(Color.green);
        g.clearRect(0, 0, getWidth(), getHeight());
        g.setFont(font);
        g.setColor(Color.black);
        ascii.update();

        for (int y = 0; y < ascii.map().height(); y++)
        {
            g.drawString((y % 10) + "> " + new String(ascii.map().raw()[y]).replaceAll("\0", " "), 8, g.getFontMetrics().getHeight() * (1 + y) - y);
        }
//        ascii.print();
        System.out.println("----------------------------");
    }

    @Override
    public Dimension getPreferredSize()
    {
        return new Dimension(ascii.map().width() * 32, ascii.map().height() * 32);
    }

    public void print()
    {
//        ascii.update();
        ascii.print();
    }
}
