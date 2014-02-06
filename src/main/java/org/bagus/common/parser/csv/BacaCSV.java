/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bagus.common.parser.csv;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author bagus
 */
public class BacaCSV {
    
    /**
     * @param args the command line arguments
     */
    
    public void BacaCSVAll(String filePath) throws FileNotFoundException, IOException{
        
        File theFile = new File(filePath);
        FileReader readerFile = new FileReader(theFile);
        BufferedReader bufReadFile = new BufferedReader(readerFile);
        
        String readByte;
        int  pencacah = 0;
        
        System.out.println("Hasil Baca File Membaca per baris");
        while ((readByte = bufReadFile.readLine()) != null) {
            pencacah++;
            String[] data = readByte.split(",");            
            // Tampilkan isi kolom 1, kolom 2, kolom 3, dsb..(sesuai kebutuhan)
           
            System.out.print(pencacah + ". ");
            for (int i=0;i<data.length;i++){
                System.out.print(data[i]);
            }
            System.out.println();
            
            if (pencacah==10){
                break;
            }
        }       
                       
    }
    
    public void BacaCSV(String filePath) throws FileNotFoundException, IOException{
        File theFile = new File(filePath);
        FileReader readerFile = new FileReader(theFile);
        BufferedReader bufReadFile = new BufferedReader(readerFile);
        
        String readByte;
        int i, pencacah = 0;
        System.out.println("Hasil Baca File Membaca per baris");
        while ((readByte = bufReadFile.readLine()) != null) {
            pencacah++;
            String[] data = readByte.split(",");            
      
            System.out.println(pencacah + ". " + data[0] + " " + data[1] + " " + data[2] + " " + data[3] );
           // System.out.println(data[0].substring(data[0].length()-1, data[0].length()));

            if (pencacah==10){
                break;
            }
        }       
            
    }
    
    
    public void FixCSV(String filePath, boolean isFixHeader) throws FileNotFoundException, IOException{
        FileManager fm = new FileManager();
        fm.copy(filePath, "c:/temp",true);
        
        File theFile = new File("c:/temp");
        FileReader readerFile = new FileReader(theFile);
        BufferedReader bufReadFile = new BufferedReader(readerFile);
      
        System.out.println("Hasil Baca File Membaca per baris");
              
        String readByte = null;
        FileWriter writer = new FileWriter(filePath );
        int  pencacah = 0;       
        while ((readByte = bufReadFile.readLine()) != null) {
            pencacah++;
            String[] data = readByte.split(",");         
            
            for (int i=0;i<data.length;i++){
                if (isFixHeader && pencacah==1){
                    if (data[i].substring(data[i].length()-1, data[i].length()).equals("\"")){
                        if (i==0){
                            data[i]= data[i].substring(2, data[i].length()-1);
                        } else {
                            data[i]= data[i].substring(1, data[i].length()-1);
                        }                        
                    }
                    
                }
                
                writer.append(data[i]);                
                writer.append(',');
            }
            
                writer.append('\n');
        }       
        
        writer.flush();
        writer.close();
        
        System.out.println("Jumlah Row File CSV : " + pencacah);
        System.out.println("Selesai");
    
    }
    
    public void FixCSV2(String filePath, boolean isFixHeader) throws FileNotFoundException, IOException{
        FileManager fm = new FileManager();
        fm.copy(filePath, "c:/temp",true);
        
        File theFile = new File("c:/temp");
        FileReader readerFile = new FileReader(theFile);
        BufferedReader bufReadFile = new BufferedReader(readerFile);
      
        System.out.println("Hasil Baca File Membaca per baris");
              
        String readByte = null;
        FileWriter writer = new FileWriter(filePath );
        int  pencacah = 0;       
        while ((readByte = bufReadFile.readLine()) != null) {
            pencacah++;
            String[] data = readByte.split(",");         
            
            for (int i=0;i<data.length;i++){
                if (i==6){
                    data[i] = data[3];
                }
                writer.append(data[i]);                
                
                writer.append(',');
            }
            
                writer.append('\n');
        }       
        
        writer.flush();
        writer.close();
        
        System.out.println("Jumlah Row File CSV : " + pencacah);
        System.out.println("Selesai");
    
    }

    public static void main(String[] args) {
        // TODO code application logic here
        BacaCSV f = new BacaCSV();
        try {
            f.BacaCSVAll("d:\\invhdr.csv");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(BacaCSV.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(BacaCSV.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    
}
