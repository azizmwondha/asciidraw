package asciidraw;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.redeye.asciidraw.Ascii;
import com.redeye.asciidraw.types.AsciiMap;
import com.redeye.asciidraw.types.Oval;
import com.redeye.asciidraw.types.Path;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;

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
//        ascii.line(0, 0, 24, 12);
//
//        ascii.pen('#');
//        ascii.box(2, 2, 22, 10);
//
//        Oval oval = new Oval(18, 6);
//        oval.move(3, 3);
//        oval.pen(' ');
//        ascii.add(oval);
        
        try
        {
            AsciiMap am = new AsciiMap(0, 0);
            am.load(new FileInputStream(new File("C:\\aziz\\workspaces\\netbeans\\Mti\\raff.txt")));
            ascii.add(am);
            
        }
        catch (FileNotFoundException ex)
        {
        }
        
        Path p = new Path(12, 12);
        p.add(new int[]{0,0});
        p.add(new int[]{8,11});
        p.add(new int[]{11,5});
        p.add(new int[]{0,0});
        
        ascii. add(p);

        ascii.update();
        System.out.println("time: " + (System.currentTimeMillis() - t) + " ms");

        ascii.print();

        System.out.println("time: " + (System.currentTimeMillis() - t) + " ms");
//        ascii.fill('+');
//        ascii.print();
//        ascii.update();ascii.print();
    }
}
