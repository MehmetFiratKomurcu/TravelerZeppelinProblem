/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travelerzeppelinproblem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author Mehmet Firat KOMURCU and Ali Recep KARACA
 */

public class TravelerZeppelinProblem {
    
    public static int boyut=81;
    public static int INFINITY=9999;
    public static double zeplinYuksekligi=50;
    public static long travelerStartTime;
    public static long dijkstraStartTime;
    

    
    public static double[][] hesaplar(double[][] graf,double[][] latLongAltitude,int kisiSayisi,int baslangicNoktasi,int varisNoktasi){
        int i,j;
        double yukseklikFarki,sehirlerArasiAci;
        int donusAcisi=80;
        donusAcisi-=kisiSayisi;
        
        for(i=0;i<boyut;i++){
            for(j=0;j<boyut;j++){
                if(i==baslangicNoktasi-1 || j==baslangicNoktasi-1 || i==varisNoktasi-1 || j==varisNoktasi-1){
                    if(i==baslangicNoktasi-1 || i==varisNoktasi-1){
                        if(graf[i][j]>0){
                            yukseklikFarki=Math.abs(latLongAltitude[i][3]-(latLongAltitude[j][3]+zeplinYuksekligi));
                            sehirlerArasiAci=Math.abs(Math.atan(yukseklikFarki/graf[i][j]));
                            sehirlerArasiAci=Math.toDegrees(sehirlerArasiAci);

//                            System.out.println("\ni= "+(i+1)+" j= "+(j+1));
//                            System.out.println("Bu nokta başlangıç veya bitiş noktası.");
//                            System.out.println("i lat= "+latLongAltitude[i][1]+" long= "+latLongAltitude[i][2]+" altitute= "+latLongAltitude[i][3]);
//                            System.out.println("j lat= "+latLongAltitude[j][1]+" long= "+latLongAltitude[j][2]+" altitute= "+latLongAltitude[j][3]);
//                            System.out.println("Yuksekli farkı: "+yukseklikFarki);
//                            System.out.println("Uzaklik "+(i+1)+"->"+(j+1)+" = "+graf[i][j]);
//                            System.out.println("Açı: "+sehirlerArasiAci);

                            if(sehirlerArasiAci>donusAcisi){
                                graf[i][j]=0;
                                graf[j][i]=0;
                            }
                        }
                    }else if(j==baslangicNoktasi-1 || j==varisNoktasi-1){
                        if(graf[i][j]>0){
                            yukseklikFarki=Math.abs(latLongAltitude[j][3]-(latLongAltitude[i][3]+zeplinYuksekligi));
                            sehirlerArasiAci=Math.abs(Math.atan(yukseklikFarki/graf[i][j]));
                            sehirlerArasiAci=Math.toDegrees(sehirlerArasiAci);

//                            System.out.println("\ni= "+(i+1)+" j= "+(j+1));
//                            System.out.println("Bu nokta başlangıç veya bitiş noktası.");
//                            System.out.println("i lat= "+latLongAltitude[i][1]+" long= "+latLongAltitude[i][2]+" altitute= "+latLongAltitude[i][3]);
//                            System.out.println("j lat= "+latLongAltitude[j][1]+" long= "+latLongAltitude[j][2]+" altitute= "+latLongAltitude[j][3]);
//                            System.out.println("Yuksekli farkı: "+yukseklikFarki);
//                            System.out.println("Uzaklik "+(i+1)+"->"+(j+1)+" = "+graf[i][j]);
//                            System.out.println("Açı: "+sehirlerArasiAci);

                            if(sehirlerArasiAci>donusAcisi){
                                graf[i][j]=0;
                                graf[j][i]=0;
                            }
                        }
                    }
                    
                }else{
                    if(graf[i][j]>0){
                        yukseklikFarki=Math.abs(latLongAltitude[i][3]-latLongAltitude[j][3]);
                        sehirlerArasiAci=Math.abs(Math.atan(yukseklikFarki/graf[i][j]));
                        sehirlerArasiAci=Math.toDegrees(sehirlerArasiAci);
                        
//                        System.out.println("\ni= "+(i+1)+" j= "+(j+1));
//                        System.out.println("i lat= "+latLongAltitude[i][1]+" long= "+latLongAltitude[i][2]+" altitute= "+latLongAltitude[i][3]);
//                        System.out.println("j lat= "+latLongAltitude[j][1]+" long= "+latLongAltitude[j][2]+" altitute= "+latLongAltitude[j][3]);
//                        System.out.println("Yuksekli farkı: "+yukseklikFarki);
//                        System.out.println("Uzaklik "+(i+1)+"->"+(j+1)+" = "+graf[i][j]);
//                        System.out.println("Açı: "+sehirlerArasiAci);
                            
                        if(sehirlerArasiAci>donusAcisi){
                            graf[i][j]=0;
                            graf[j][i]=0;
                        }
                    }
                }  
            }
        }
        double islem;
        for(i=0;i<boyut;i++){
            for(j=0;j<boyut;j++){
                if(i==baslangicNoktasi-1 || j==baslangicNoktasi-1 || i==varisNoktasi-1 || j==varisNoktasi-1){
                    if(i==baslangicNoktasi-1 || i==varisNoktasi-1){
                        if(graf[i][j]>0){
                            yukseklikFarki=Math.abs(latLongAltitude[i][3]-(latLongAltitude[j][3]+zeplinYuksekligi));
                            islem=Math.sqrt(Math.pow(graf[i][j], 2)+Math.pow(yukseklikFarki*0.001,2));
                            graf[i][j]=islem;
//                            System.out.println("Yükseklik farkı "+yukseklikFarki);
//                            System.out.println("Uzaklik "+(i+1)+"->"+(j+1)+" = "+graf[i][j]);
                        }
                    }else if(j==baslangicNoktasi-1 || j==varisNoktasi-1){
                        if(graf[i][j]>0){
                            yukseklikFarki=Math.abs(latLongAltitude[j][3]-(latLongAltitude[i][3]+zeplinYuksekligi));
                            islem=Math.sqrt(Math.pow(graf[i][j], 2)+Math.pow(yukseklikFarki*0.001,2));
                            graf[i][j]=islem;
//                            System.out.println("Yükseklik farkı "+yukseklikFarki);
//                            System.out.println("Uzaklik "+(i+1)+"->"+(j+1)+" = "+graf[i][j]);
                        }
                    }
                    
                }else{
                    if(graf[i][j]>0){
                        yukseklikFarki=Math.abs(latLongAltitude[i][3]-latLongAltitude[j][3]);
                        islem=Math.sqrt(Math.pow(graf[i][j], 2)+Math.pow(yukseklikFarki*0.001,2));
                        graf[i][j]=islem;
//                        System.out.println("Yükseklik farkı "+yukseklikFarki);
//                        System.out.println("Uzaklik "+(i+1)+"->"+(j+1)+" = "+graf[i][j]);
                    }
                }  
            }
        }
//        System.out.println("\n-------------------\n");
        return graf;
    }
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        double[][] graf=new double[boyut][boyut];
        String[][] plakaSehir=new String[81][2];
        int i,j,ilSayisi=81,baslangicNoktasi=1,varisNoktasi=2;

        Scanner scan=new Scanner(System.in);
        
        do{
            if(baslangicNoktasi<1 || baslangicNoktasi>81 || varisNoktasi<1 || varisNoktasi>81){
                System.out.println("\nHatalı giriş yaptınız.Plaka numaraları 1 ile 81 aralığında olmalıdır.");
            }
            if(baslangicNoktasi==varisNoktasi){
                System.out.println("\nBaşlangıç ve varış şehri aynı olamaz.");
            }
            System.out.println("\nBaşlangıç şehrinin plaka kodunu giriniz:");
            baslangicNoktasi=scan.nextInt();
            System.out.println("\nVarış şehrinin plaka kodunu giriniz:");
            varisNoktasi=scan.nextInt();  
        }while(baslangicNoktasi<1 || baslangicNoktasi>81 || varisNoktasi<1 || varisNoktasi>81 || baslangicNoktasi==varisNoktasi);
        
        travelerStartTime = System.currentTimeMillis();
        
        File komsuluk=new File("komsuluk.txt");
        BufferedReader okuyucu;
        okuyucu = new BufferedReader(new FileReader(komsuluk));
        String satir = okuyucu.readLine();
        for(i=0;i<boyut;i++){
            for(j=0;j<boyut;j++){
                graf[i][j]=0;
            }
        } 
        
        int satirSayac=0;
        while (satir!=null) {
            satir = okuyucu.readLine();
            if(satir!=null){
                String parcalananKomsular[]=satir.split(",");
                int parcalananKomsularBoyut=parcalananKomsular.length;
                if(parcalananKomsularBoyut!=1){
                    for(i=0;i<parcalananKomsularBoyut;i++){
                        if(satirSayac==i){
                            graf[satirSayac][i]=0;
                        }else{
                            int komsu=Integer.parseInt(parcalananKomsular[i]);
                            graf[satirSayac][komsu-1]=1;
                        }
                    } 
                    satirSayac++;
                }
            }
        }
        okuyucu.close();
        
        double[][] latLongAltitude=new double[81][4];

        File lat_long_altitude=new File("lat_long_altitude.txt");
        BufferedReader okuyucu2;
        okuyucu2 = new BufferedReader(new FileReader(lat_long_altitude));
        satir = okuyucu2.readLine();
        
        satirSayac=0;
        while(satir!=null){
            satir = okuyucu2.readLine();
            if(satir!=null){
                String[] parcalananDegerler=satir.split(",");
                for(i=0;i<parcalananDegerler.length;i++){
                    latLongAltitude[satirSayac][0]=Double.parseDouble(parcalananDegerler[2]);
                    latLongAltitude[satirSayac][1]=Double.parseDouble(parcalananDegerler[0]);
                    latLongAltitude[satirSayac][2]=Double.parseDouble(parcalananDegerler[1]);
                    latLongAltitude[satirSayac][3]=Double.parseDouble(parcalananDegerler[3]);
                }
                satirSayac++;
            }
        }
        
        okuyucu2.close();
        
        File plaka_sehir=new File("plaka_sehir.txt");
        BufferedReader okuyucu3;
        okuyucu3=new BufferedReader(new FileReader(plaka_sehir));
        satir=okuyucu3.readLine();
        
        satirSayac=0;
        while(satir!=null){
            satir=okuyucu3.readLine();
            if(satir!=null){
                String[] parcalananPlakaSehir=satir.split(",");
                plakaSehir[satirSayac][0]=parcalananPlakaSehir[0];
                plakaSehir[satirSayac][1]=parcalananPlakaSehir[1];
                satirSayac++;
            }
        }
        
        double fi1,fi2,deltafi,deltalambda,a,c,d;
        final double r=6371e3;
        
        for(i=0;i<boyut;i++){
            for(j=0;j<boyut;j++){
                if(graf[i][j]==1){
                    fi1=Math.toRadians(latLongAltitude[i][1]);
                    fi2=Math.toRadians(latLongAltitude[j][1]);
                    deltafi=Math.toRadians(latLongAltitude[j][1]-latLongAltitude[i][1]);
                    deltalambda=Math.toRadians(latLongAltitude[j][2]-latLongAltitude[i][2]);
                    a=Math.sin(deltafi/2)*Math.sin(deltafi/2)+Math.cos(fi1)*Math.cos(fi2)*
                            Math.sin(deltalambda/2)*Math.sin(deltalambda/2);
                    c=2*Math.atan2(Math.sqrt(a),Math.sqrt(1-a));
                    d=r*c;
                    
                    graf[i][j]=d/1e3;
                }
            }
        } 
        
        int sayac=0;
        
        double[][] grafKopya=new double[boyut][boyut];

        for(i=0;i<boyut;i++){
            for(j=0;j<boyut;j++){
                grafKopya[i][j]=graf[i][j];
            }
        }
        double[][] yolUzunlukAna=new double[46][82];
        System.out.println("");

        for(i=5;i<=50;i++){
            grafKopya=hesaplar(grafKopya,latLongAltitude,i,baslangicNoktasi,varisNoktasi);
            dijkstraStartTime+=System.currentTimeMillis();
            yolUzunlukAna[sayac]=Dijkstra.dijkstra(grafKopya,ilSayisi,baslangicNoktasi-1,varisNoktasi-1);
            dijkstraStartTime-=System.currentTimeMillis();
            
            sayac++;
            for(j=0;j<boyut;j++){
                for(int m=0;m<boyut;m++){
                    grafKopya[j][m]=graf[j][m];
                }
            }
        }
        double[][] grafKopya1=new double[boyut][boyut];
        double[][] grafKopya2=new double[boyut][boyut];
        for(i=0;i<boyut;i++){
            for(j=0;j<boyut;j++){
                grafKopya1[i][j]=graf[i][j];
                grafKopya2[i][j]=graf[i][j];
            }
        }
        
        grafKopya1=hesaplar(grafKopya1,latLongAltitude,5,baslangicNoktasi,varisNoktasi);
        grafKopya2=hesaplar(grafKopya,latLongAltitude,5,baslangicNoktasi,varisNoktasi);

        
        ConstantPaymentProblem cpp=new ConstantPaymentProblem();
        cpp.constantPayment(yolUzunlukAna,latLongAltitude,plakaSehir,grafKopya1);
        
        FiftyPercentProfitProblem fppp=new FiftyPercentProfitProblem();
        fppp.fiftyPercent(yolUzunlukAna, latLongAltitude,plakaSehir,grafKopya2);
        
                        
        travelerStartTime-= System.currentTimeMillis();
        travelerStartTime=Math.abs(travelerStartTime);
        dijkstraStartTime=Math.abs(dijkstraStartTime);
        System.out.println();
        System.out.println("Toplam Çalışma Süresi: "+travelerStartTime/1000.0);
        System.out.println("Dijkstra Classı Çalışma Süresi: "+dijkstraStartTime/1000.0);
        System.out.println("ConstantPaymentProblem Classı Çalışma Süresi: "+ConstantPaymentProblem.constantStartTime/1000.0);
        System.out.println("FiftyPercentProfitProblem Classı Çalışma Süresi: "+FiftyPercentProfitProblem.fiftyStartTime/1000.0);
    }
    
}
