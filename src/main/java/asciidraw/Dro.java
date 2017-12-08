/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asciidraw;

import com.redeye.asciidraw.Ascii;
import com.redeye.asciidraw.types.Oval;

/**
 *
 * @author amwon
 */
public class Dro
{

    public static void main(String[] args)
    {
        long t = System.currentTimeMillis();
        Ascii ascii = new Ascii(24, 12);
        ascii.fill('.');
        ascii.pen('\\');
        ascii.line(0, 0, 24, 12);

        ascii.pen('#');
        ascii.box(2, 2, 22, 10);

        Oval oval = new Oval(18, 6);
        oval.move(3, 3);
        oval.pen(' ');
        ascii.add(oval);

        ascii.update();
        System.out.println("time: " + (System.currentTimeMillis() - t) + " ms");

        ascii.print();

        System.out.println("time: " + (System.currentTimeMillis() - t) + " ms");
    }
}
