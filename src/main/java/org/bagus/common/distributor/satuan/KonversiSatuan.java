/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bagus.common.distributor.satuan;

/**
 *
 * @author bagus
 */
public class KonversiSatuan {
    private String theSeparator=".";
    
    public KonversiSatuan(){
    }

    public String getTheSeparator() {
        return theSeparator;
    }

    public void setTheSeparator(String theSeparator) {
        this.theSeparator = theSeparator.trim();
    }
    
    //merubah jika separatornya lain
    public String getDotSeparatorFromOther(String strValue, String theSeparatorOther){
        String dotString = "";
        for (int i=0;i<strValue.length();i++){
            if (strValue.substring(i, i+1).equals(theSeparatorOther.trim())) {
                dotString = dotString + ".";
            } else {
                dotString = dotString + strValue.substring(i, i+1);
            }
        }
        
        return dotString;
    }
    public String getSeparatorFromDot(String strValue, String theSeparatorOther){
        String otherString="";
        for (int i=0;i<strValue.length();i++){
            if (strValue.substring(i, i+1).equals(".")) {
                otherString = otherString + theSeparatorOther;
            } else {
                otherString = otherString + strValue.substring(i, i+1);
            }
        }
        
        return otherString;
        
    }
    //Main method
    public String KonvertAndFixBigMedSmallToBigMedSmallButFix(String badStrQty, int bigToSmall, int medToSmall){
       int thePcs;
       String theBigMedSmall;

       thePcs=FixAndKonvertBigMedSmallToPcs(badStrQty, bigToSmall, medToSmall);
        theBigMedSmall = KonvertPcsToBigMedSmall(thePcs, bigToSmall, medToSmall);
        
        return theBigMedSmall;
    }
    public int FixAndKonvertBigMedSmallToPcs(String badStrQty, int bigToSmall, int medToSmall) {
        int newPcs = 0;
        String newStrQty=badStrQty;
        newStrQty = HapusHuruf(newStrQty);
        newStrQty = TitikHanya2(newStrQty);
        newStrQty = FormatToBigDotMedDotSmall(newStrQty);
        newPcs = KonvertBigMedSmallToPcs(newStrQty, bigToSmall, medToSmall);
        return newPcs;
    }
    
    public int KonvertPcsToKrtUtuh(int qtyPcs, int bigToSmall, int medToSmall){
        int KrtUtuh;
        KrtUtuh = qtyPcs / bigToSmall;
        return KrtUtuh;
    }
    
    public String KonvertPcsToBigMedSmall(int qtyPcs, int bigToSmall, int medToSmall){
        int Big, Med = 0, Small;
        int sisaBig, sisaMed = 0;
        //1. Cari Besar dulu
        Big = qtyPcs/bigToSmall;
        sisaBig=qtyPcs % bigToSmall;
        //2. Cari Sedang jika Besar ada sisa dan medToSmall>1
        if (sisaBig != 0 && medToSmall>1){
            Med = sisaBig/medToSmall;
            sisaMed = sisaBig % medToSmall;
        }
        //Cari Kecil Jika masih ada sisa atau medToSmall==1
        if (medToSmall==1){
            Small = sisaBig;
        } else {
            Small = sisaMed;
        }
        return String.valueOf(Big) + getTheSeparator() + String.valueOf(Med) + getTheSeparator() + String.valueOf(Small);
    }
    public int KonvertBigMedSmallToPcs(String qtyString, int bigToSmall, int medToSmall){
        //Asumsi Format sudah benar
        int firstDot = 0, secondDot = 0;
        for (int i=0;i<qtyString.length();i++){
            if (String.valueOf(qtyString.charAt(i)).equals(getTheSeparator())){
                if (firstDot == 0) {
                    firstDot = i;
                    secondDot = i;
                } else {
                    secondDot = i;
                }
            }
        }
            int Big = Integer.parseInt(qtyString.substring(0, firstDot));
            int Med =  Integer.parseInt(qtyString.substring(firstDot+1, secondDot));
            int Small = Integer.parseInt(qtyString.substring(secondDot+1, qtyString.length()));
        return (Big*bigToSmall) + (Med*medToSmall) + Small;
    }

    public String FixFormatSatuan(String strQty, int bigToSmall, int medToSmall){
        String newStrQty = "";
        //Jika ada selain angka dan carakter dot ( . ) maka format menjadi 0.0.0
	boolean angkaTitikValid=true;
	if (! strQty.matches("[0-9,.]*")) {
	    angkaTitikValid=false;
	}        
            /*
        char[] data = strQty.toCharArray(); 
	boolean valid = true;
	for (char c : data) {
	    if (!Character.isDigit(c)) {
	        angkaTitikValid = false;
	        break;
	    }
	}  
        * 
        */
        if (angkaTitikValid==false){
            newStrQty = "0" + getTheSeparator() + "0" + getTheSeparator()+ "0";
        } else {
        //Jika pengguna menuliskan ..<angka>
            int firstDot = 0, secondDot = 0;
            for (int i=0;i<strQty.length();i++){
                if (String.valueOf(strQty.charAt(i)).equals(getTheSeparator())){
                    if (firstDot == 0) {
                        firstDot = i;
                        secondDot = i;
                    } else {
                        secondDot = i;
                    }
                }
            }
            //1. Jika firstDot=0 berarti hanya menuliskan angka tanpa titik
            if (firstDot==0){
                newStrQty = strQty;
            }
            //2. Jika firstDot=secondDot berarti ada 2 kemungkinan
            if (firstDot==secondDot) {
                //2.a Titiknya di depan (kiri)
                //2.b Titiknya di belakang (kanan)
                if (firstDot==0){
                    newStrQty = "0" + getTheSeparator() + "0" + getTheSeparator() +  strQty.substring(1, strQty.length());       
                    //System.out.println("hehehe");
                } else {
                    newStrQty = strQty.substring(0, strQty.length()-1) +  getTheSeparator() + "0" + getTheSeparator()+ "0";              
                    //System.out.println("halo");
                }
            } else {
            
            }
            
        } //**** End If
        return newStrQty;
    }
    public String HapusHuruf(String strQty){
        if (strQty.trim().equals("")){
            strQty = "0" + getTheSeparator() + "0" + getTheSeparator()+ "0";;
        }
        char [] data = TitikHanya2(strQty).toCharArray();
        //char [] data = strQty.toCharArray();
        String newStrQty = "";
        for (char c : data){
            if (Character.isDigit(c) || c == getTheSeparator().charAt(0)) {
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
            if (c !=  getTheSeparator().charAt(0)) {
                newStrQty = newStrQty + String.valueOf(c);
            } else if (c ==  getTheSeparator().charAt(0)){
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
                newStrQty2 = newStrQty + getTheSeparator() + getTheSeparator();
            } else if (JmlTitik==1){
                for(int i=0;i < newStrQty.length();i++){
                    if (i==PosTitik){
                        newStrQty2 = newStrQty2 + getTheSeparator();
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
            if (String.valueOf(strQty.charAt(i)).equals(getTheSeparator())){
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
            newStrQty = "0"+ getTheSeparator() + "0" + getTheSeparator() + strQty.substring(2, strQty.length()); 
        }
        //2. Kemungkinan .0.
        else if (firstDot==0 && secondDot>1 && panjang==(secondDot+1)){
            newStrQty = "0"+ strQty.substring(firstDot, secondDot) + getTheSeparator() + "0";   
        }
        //3. Kemungkingan 0..
        else if (firstDot>0 && secondDot==(firstDot+1) && panjang==(secondDot+1)) {
            newStrQty = strQty.substring(0, firstDot) + getTheSeparator() + "0" + getTheSeparator() + "0";                 
        }
        //4. Kemungkinan 0.0.
        else if (firstDot>0 && secondDot > (firstDot+1) && panjang==(secondDot+1)){
            newStrQty = strQty.substring(0, secondDot) + getTheSeparator() + "0"; 
        }
        //5. Kemungkinan .0.0
        else if (firstDot==0 && secondDot>1 && panjang>(secondDot+1)) {
            newStrQty = "0"+ getTheSeparator() + strQty.substring(1, strQty.length());
        }
        //6. Kemungkinan 0..0
        else if (firstDot>0 && secondDot==(firstDot+1) && panjang>(secondDot+1)){
            newStrQty = strQty.substring(0, secondDot) + "0" +strQty.substring(secondDot, strQty.length());
        }
        //7. Kemungkinan kosong
        else if (strQty.trim().equals("")){
            newStrQty = "0" + getTheSeparator() +"0" + getTheSeparator() +  "0";
        }
        //8. Kemungkinan Benar 0.0.0
        return newStrQty;
    }
   
    public static void main(String [] args){
        KonversiSatuan k = new KonversiSatuan();
        //System.out.println(k.KonvertPcsToBig(105, 10,2));
        //System.out.println(k.KonvertPcsToMed(105, 10,2));
        //System.out.println(k.KonvertPcsToSmall(105, 10,2));
        System.out.println(k.KonvertPcsToBigMedSmall(23, 5, 1));
        System.out.println(k.KonvertBigMedSmallToPcs("4.0.3", 5, 1));
        System.out.println(k.FixFormatSatuan("..2",2,2));
    }
    
}
