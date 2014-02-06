/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bagus.common.distributor.kwitansi;

/**
 *
 * @author bagus
 */

public class TerbilangSalah {
    private String huruf;
    private String[] daftarAngka={"","satu","dua","tiga","empat","lima","enam","tujuh","delapan","sembilan"};
    public String terbilang(String nilai){
            String temp="";

            String temp2="";
            int hasilBagi,sisaBagi;
            int batas=3;//batas untuk ribuan
            int maxBagian = 5;/*untuk menentukan ukuran array, jumlahnya sesuaikan dengan jumlah anggota dari array gradeNilai[] */
            String[] gradeNilai={"","ribu","juta","milyar","triliun"};
            //cek apakah ada angka 0 didepan ==> 00098, harus diubah menjadi 98
            nilai = this.hapusNolDiDepan(nilai);
            String[] nilaiTemp = ubahStringKeArray(batas, maxBagian, nilai);
            //ubah menjadi bentuk TerbilangSalah
            int j = nilai.length();
            int banyakBagian = ((j % batas) == 0 ? (j / batas) :Math.round((j / batas) + 0.5f));//menentukan batas array
            int h=0;
            for(int i = banyakBagian - 1; i >=0; i-- ){
                int nilaiSementara = Integer.parseInt(nilaiTemp[h]);
                if (nilaiSementara == 1 && i == 1){ 
                    temp +="seribu ";}
                else {
                    temp +=this.ubahRatusanKeHuruf(nilaiTemp[h])+" ";
                    temp += gradeNilai[i]+" ";
                }
                h++;
            }
            return temp;
    }
    
    private String[] ubahStringKeArray(int batas, int maxBagian, String kata){
        String[] temp= new String[maxBagian];
        int j = kata.length();
        int k;
        int banyakBagian = ((j % batas) == 0 ? (j / batas) :Math.round((j / batas) + 0.5f));
        for(int i = banyakBagian - 1; i >=0  ; i--){
            k = j - batas;
            if(k < 0) k = 0;
            temp[i]=kata.substring(k,j);
            j = k ;
            if (j == 0) break;
        }
        return temp;
    }
    public String ubahRatusanKeHuruf(String nilai){
        //maksimal 3 karakter
        int batas = 2;//membagi string menjadi 2 bagian, misal 123 ==> 1 dan 23
        int maxBagian = 2;//untuk deklarasi panjang array
        String[] temp = this.ubahStringKeArray(batas, maxBagian, nilai);
        int j = nilai.length();
        String hasil="";
        int banyakBagian = ((j % batas) == 0 ? (j / batas) :Math.round((j / batas) + 0.5f));//menentukan batas array
        for(int i = 0; i < banyakBagian ;i++){
                //cek string yang memiliki panjang lebih dari satu ==> belasan atau puluhan
                if(temp[i].length() > 1){
                //cek untuk yang bernilai belasan ==> angka pertama 1 dan angka kedua 0 - 9, seperti 11,16 dst //
                if(temp[i].charAt(0) == '1'){
                    if(temp[i].charAt(1) == '1') {hasil += "sebelas";}
                    else if(temp[i].charAt(1) == '0') {hasil += "sepuluh";}
                    else hasil += daftarAngka[temp[i].charAt(1) - '0']+ " belas ";
                } else if(temp[i].charAt(0) == '0') {
                //cek untuk string dengan format angka  pertama 0 ==> 09,05 dst
                    hasil += daftarAngka[temp[i].charAt(1) - '0'] ;
                //cek string dengan format selain angka pertama 0 atau 1
                } else 
                    hasil += daftarAngka[temp[i].charAt(0) - '0']+ " puluh " +daftarAngka[temp[i].charAt(1) - '0'] ;
                }else {
            //cek string yang memiliki panjang = 1 dan berada pada posisi ratusan
                if(i == 0 && banyakBagian !=1){
                    if (temp[i].charAt(0) == '1') hasil+=" seratus ";
                    else hasil+= daftarAngka[Integer.parseInt(temp[i])]+" ratus ";
                } else 
                    //string dengan panjang satu dan tidak berada pada posisi ratusan ==> satuan
                    hasil+= daftarAngka[Integer.parseInt(temp[i])];
                }
        }
        return  hasil;
    }
    private String hapusNolDiDepan(String nilai){
        while(nilai.indexOf("0") == 0){
            nilai = nilai.substring(1, nilai.length());
        }
        return nilai;
    }
    public static void main(String[] args){
        System.out.println(new TerbilangSalah().terbilang("01250011001"));
    //akan menghasilkan satu juta dua ratus lima puluh ribu sebelas
    }
}

