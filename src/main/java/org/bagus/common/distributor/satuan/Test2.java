/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bagus.common.distributor.satuan;

/**
 *
 * @author bagus
 */
public class Test2 {
    public static void main(String [] args){
        KonversiSatuan k = new KonversiSatuan();
        System.out.println(k.getDotSeparatorFromOther("33/2222/333d", "/"));
        System.out.println(k.getSeparatorFromDot(k.getDotSeparatorFromOther("33/2222/333d", "/"), "/"));
    }
    
}
