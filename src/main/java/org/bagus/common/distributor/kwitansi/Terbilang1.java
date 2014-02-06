/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bagus.common.distributor.kwitansi;

import java.math.BigInteger;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author bagus
 */
public class Terbilang1 {
    
    public String KonversiKwitansi (double doubleAngka){
        String [] bil ={"","satu","dua","tiga","empat","lima","enam","tujuh","delapan","sembilan","sepuluh","sebelas"};
        String x=" ";

        int intAngka = (int) doubleAngka;
        if (doubleAngka<12){
            x = " " + bil[intAngka];
        } else if(doubleAngka<20){
            x = KonversiKwitansi(doubleAngka-10) + " belas";
        } else if(doubleAngka<100){
            x = KonversiKwitansi(doubleAngka/10) + " puluh" + KonversiKwitansi(doubleAngka%10);
        }  else if(doubleAngka<200){
            x = " seratus" + KonversiKwitansi(doubleAngka-100);
        }  else if(doubleAngka<1000){
            x = KonversiKwitansi(doubleAngka/100) + " ratus" + KonversiKwitansi(doubleAngka%100);
        } else if(doubleAngka<2000){
            x = " seribu"+ KonversiKwitansi(doubleAngka-1000);
        }  else if(doubleAngka<1000000){
            x = KonversiKwitansi(doubleAngka/1000) + " ribu" + KonversiKwitansi (doubleAngka%1000);
        } else if(doubleAngka<1000000000){
            x = KonversiKwitansi(doubleAngka/1000000)+ " juta" + KonversiKwitansi (doubleAngka%1000000);
        } else { 
            x = KonversiKwitansi(doubleAngka/1000000000) + " milyat" + KonversiKwitansi(doubleAngka%1000000000);
        }

        return x;
    }
    public String PerbaikiSpasi(double doubleAngka){
        String strAngka = KonversiKwitansi(doubleAngka);
        String angkaBaru = "";
        int JmlSpasi =0;
        for (int i=1;i<strAngka.length();i++){
            if (strAngka.charAt(i)==' ' && strAngka.charAt(i-1)==' ') {
                angkaBaru = angkaBaru.substring(1, i-1);
                System.out.println("Hello bos");
            } else {
                angkaBaru = angkaBaru + strAngka.charAt(i);
                JmlSpasi =0;
            }     
        }
        
        return strAngka.trim();
    }
public static void main(String [] args){
    Terbilang1 t = new Terbilang1();
    System.out.println(t.KonversiKwitansi(10100001));
    System.out.println(t.PerbaikiSpasi(11100001001.00).toUpperCase());    
}
}
