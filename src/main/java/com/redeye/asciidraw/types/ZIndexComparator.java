/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redeye.asciidraw.types;

import java.util.Comparator;

/**
 *
 * @author amwon
 */
public class ZIndexComparator implements Comparator {

    @Override
    public int compare(Object o1,
            Object o2) {
        if ((o1 instanceof AsciiArt) && (o2 instanceof AsciiArt)) {
            AsciiArt aa1 = (AsciiArt) o1;
            AsciiArt aa2 = (AsciiArt) o2;
            return ((aa1.z() < aa2.z()) ? -1 : 1);
        }
        return -1;
    }

}
