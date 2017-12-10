package asciidraw;

import com.redeye.asciidraw.Ascii;
import com.redeye.asciidraw.gfx.AsciiPanel;
import com.redeye.asciidraw.types.AsciiMap;
import com.redeye.asciidraw.types.Oval;
import com.redeye.asciidraw.types.Rect;
import com.redeye.asciidraw.types.Sprite;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * @see http://stackoverflow.com/a/17767350/230513
 */
public class Scene
{

    public static void main(String[] args)
    {
//        EventQueue.invokeLater(new Scene()::display);
        new Scene().display();
    }

    private void display()
    {
        JFrame frame = new JFrame("ASCII Draw Scene");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        AsciiPanel dp = new AsciiPanel(boxcross());
        frame.add(dp);

        JPanel p = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        p.add(new JButton(new AbstractAction("Next frame")
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                dp.updateUI();
            }
        }));

        p.add(new JButton(new AbstractAction("Print text")
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                dp.print();
            }
        }));
        frame.add(p, BorderLayout.SOUTH);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private Ascii boxcross()
    {
        Ascii ascii = new Ascii(32, 20);
        ascii.pen('o');
        ascii.line(0, 0, 32, 20);

        ascii.pen('#');
        ascii.box(8, 5, 24, 15);

        ascii.pen('-');
        ascii.box(6, 11, 18, 17);
        
//        ascii.pen('_');
//        ascii.path(new int[][]{{3,0},{5,12},{27,7}});
        ascii.print();
        return ascii;
    }

    private Ascii oval()
    {
        Ascii ascii = new Ascii(32, 20);
        Oval o1 = new Oval(9, 8);
        o1.background('v');
        o1.pen('-');
        ascii.add(o1);
        return ascii;
    }

    private Ascii ovalboxcross()
    {
        Ascii ascii = new Ascii(32, 20);
        
        ascii.pen('o');
        ascii.line(0, 0, 32, 20);

        ascii.pen('#');
        ascii.box(8, 5, 24, 15);

        ascii.pen('-');
        ascii.box(6, 11, 18, 17);
        
        
        Oval o1 = new Oval(9, 8);
        o1.background('v');
        o1.pen('-');
        ascii.add(o1);
//        ascii.pen('_');
//        ascii.path(new int[][]{{3,0},{5,12},{27,7}});
        ascii.print();
        return ascii;
    }

    private Ascii sprite()
    {
        Ascii ascii = new Ascii(32, 20);
        Sprite s = new Sprite(10, 10);
        Rect r1 = new Rect(4, 4);
        r1.pen('i');
        r1.background('r');
        s.add(r1);

        Oval o1 = new Oval(7,7);
        o1.background('v');
        o1.pen('-');
        s.add(o1);

        s.move(0, 0);
        ascii.add(s);
        return ascii;
    }

    private Ascii spritebitmap()
    {
        Ascii ascii = new Ascii(32, 20);
        Sprite s = new Sprite(10, 10);

        try
        {
            AsciiMap am = new AsciiMap(0, 0);
            am.load(new FileInputStream(new File("C:\\aziz\\workspaces\\netbeans\\Mti\\ascii.txt")));
            s.add(am);
            
            AsciiMap am2 = new AsciiMap(0, 0);
            am2.load(new FileInputStream(new File("C:\\aziz\\workspaces\\netbeans\\Mti\\kituu.txt")));
            s.add(am2);
        }
        catch (FileNotFoundException ex)
        {
            Logger.getLogger(Scene.class.getName()).log(Level.SEVERE, null, ex);
        }
        ascii.add(s);
        return ascii;
    }

    private Ascii bitmap()
    {
        Ascii ascii = new Ascii(32, 20);
        AsciiMap am = new AsciiMap(0, 0);
        try
        {
            am.load(new FileInputStream(new File("C:\\aziz\\workspaces\\netbeans\\Mti\\ascii.txt")));
            ascii.add(am);
        }
        catch (FileNotFoundException ex)
        {
            Logger.getLogger(Scene.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ascii;
    }
}
