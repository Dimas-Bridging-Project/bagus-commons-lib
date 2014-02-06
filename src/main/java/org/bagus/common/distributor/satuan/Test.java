/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bagus.common.distributor.satuan;

/**
 *
 * @author bagus
 */
public class Test {
    public String HapusHuruf(String strQty){
        char [] data = TitikHanya2(strQty).toCharArray();
        //char [] data = strQty.toCharArray();
        String newStrQty = "";
        for (char c : data){
            if (Character.isDigit(c) || c == '.') {
                newStrQty = newStrQty + String.valueOf(c);
            }
        }
        return newStrQty;
    }
    
    public String TitikHanya2(String strQty){
        char [] data = strQty.toCharArray();
        String newStrQty = "";
        int JmlTitik = 0, PosTitik=0;
        int Pencacah=0;
        for (char c : data){
            if (c !=  '.') {
                newStrQty = newStrQty + String.valueOf(c);
            } else if (c==  '.'){
                    JmlTitik++;
                    PosTitik=Pencacah;
                    if (JmlTitik<3){ 
                        newStrQty = newStrQty + String.valueOf(c);
                    }
            }  
            Pencacah++;
            
        }
        
        //System.out.println(newStrQty);
        
            String newStrQty2="";
            //Jika Jumlah titik cuma 1 maka paksa titik harus 2
            if (JmlTitik==0){
                newStrQty2 = newStrQty + "." + ".";
            } else if (JmlTitik==1){
                for(int i=0;i < newStrQty.length();i++){
                    if (i==PosTitik){
                        newStrQty2 = newStrQty2 + ".";
                    }
                    newStrQty2= newStrQty2 + String.valueOf(newStrQty.charAt(i));
                 }            
                //Bisa jadi sebetulnya jumlah titik adalah 3 tapi sudah dibuat 2
            } else {
                newStrQty2 = newStrQty;
            }
            
        return newStrQty2;        
    }
    public String FormatToBigDotMedDotSmall(String strQty){
       strQty = strQty.trim();
       int firstDot = 0,  secondDot = 0;
         boolean firstDotFound=false, secondDotFound =false;
        for (int i=0;i<strQty.length();i++){
            if (String.valueOf(strQty.charAt(i)).equals(".")){
                if (firstDotFound==false){
                    firstDotFound=true;
                    firstDot=i;
                } else if (secondDotFound==false){
                    secondDot=i;
                    secondDotFound=true;
                }
                
            }
        }
       //Asumsi bahwa sudah mengandung 2 titik
        String newStrQty = strQty;
        int panjang = strQty.length();
        //1. Kemungkingan ..0
        if (firstDot==0 && secondDot==1 && panjang>(secondDot+1)){
            newStrQty = "0"+ "." + "0" + "." + strQty.substring(2, strQty.length()); 
        }
        //2. Kemungkinan .0.
        else if (firstDot==0 && secondDot>1 && panjang==(secondDot+1)){
            newStrQty = "0"+ strQty.substring(firstDot, secondDot) + "." + "0";   
        }
        //3. Kemungkingan 0..
        else if (firstDot>0 && secondDot==(firstDot+1) && panjang==(secondDot+1)) {
            newStrQty = strQty.substring(0, firstDot) + "." + "0" + "."+ "0";                 
        }
        //4. Kemungkinan 0.0.
        else if (firstDot>0 && secondDot > (firstDot+1) && panjang==(secondDot+1)){
            newStrQty = strQty.substring(0, secondDot) + "."+ "0"; 
        }
        //5. Kemungkinan .0.0
        else if (firstDot==0 && secondDot>1 && panjang>(secondDot+1)) {
            newStrQty = "0"+ "." + strQty.substring(1, strQty.length());
        }
        //6. Kemungkinan 0..0
        else if (firstDot>0 && secondDot==(firstDot+1) && panjang>(secondDot+1)){
            newStrQty = strQty.substring(0, secondDot) + "0" +strQty.substring(secondDot, strQty.length());
        }
        //7. Kemungkinan Benar 0.0.0
        return newStrQty;
    }
    
    public static void main(String [] args){
        Test t = new Test();
        //System.out.println(t.HapusHuruf(".hgh66"));
        System.out.println(t.TitikHanya2("3.3.6.65.3"));
        System.out.println(t.FormatToBigDotMedDotSmall("2.2.2"));
    }
    
}
