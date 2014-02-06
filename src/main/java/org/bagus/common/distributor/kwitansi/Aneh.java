/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bagus.common.distributor.kwitansi;

/**
 *
 * @author bagus
 */
public class Aneh {
      public static String huruf="";   
      public String terbilang(long x){
        if(x==1){
            huruf=""+huruf+"satu ";
        }else if(x==2){
            huruf=""+huruf+"dua ";
        }else if(x==3){
            huruf=""+huruf+"tiga ";
        }else if(x==4){
            huruf=""+huruf+"empat ";
        }else if(x==5){
            huruf=""+huruf+"lima ";
        }else if(x==6){
            huruf=""+huruf+"enam ";
        }else if(x==7){
            huruf=""+huruf+"tujuh ";
        }else if(x==8){
            huruf=""+huruf+"delapan ";
        }else if(x==9){
            huruf=""+huruf+"sembilan ";
        }else if(x==10){
            huruf=""+huruf+"sepuluh ";
        }else if(x==11){
            huruf=""+huruf+"sebelas ";
        }else if(x>=12&&x<=19){
            terbilang(x%10);
            huruf=""+huruf+"belas ";
        }else if(x>=20&&x<=99){
            terbilang(x/10);
            huruf=""+huruf+"puluh ";
            terbilang(x%10);
        }else if(x>=100&&x<=199){
            huruf=""+huruf+"seratus ";
            terbilang(x-100);
        }else if(x>=200&&x<=999){
            terbilang(x/100);
            huruf=""+huruf+"ratus ";
            terbilang(x%100);
        }else if(x>=1000&&x<=1999){
            huruf=""+huruf+"seribu ";
            terbilang(x-100);
        }else if(x>=2000&&x<=999999){
            terbilang(x/1000);
            huruf=""+huruf+"ribu ";
            terbilang(x%1000);
        }else if(x>=1000000&&x<=999999999){
            terbilang(x/1000000);
            huruf=""+huruf+"juta ";
            terbilang(x%1000000);
        }else if(x>=1000000000&&x<=2147483647){
            terbilang(x/1000000000);
            huruf=""+huruf+"miliar ";
            terbilang(x%1000000000);
        }
        return huruf;
    }  
      public static void main(String [] args){
          Aneh t = new Aneh();
          
          System.out.println(t.terbilang(1250011001));
          System.out.println(t.terbilang(2001));
          
          
      }
}
