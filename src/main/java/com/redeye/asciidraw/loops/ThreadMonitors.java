/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asciidraw.loops;

/**
 *
 * @author aziz
 */
public class ThreadMonitors
{
    private static final Object assets = new Object();
    private static final Object physics = new Object();
    private static final Object optics = new Object();
    
    public static final Object physics(){
        return physics;
    }
    
    public static final Object optics(){
        return optics;
    }
    
    public static final Object assets(){
        return assets;
    }
}
