/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travelerzeppelinproblem;

import java.awt.Dimension;
import java.awt.Image;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Ali Recep KARACA and Mehmet Firat KOMURCU
 */
public class FiftyPercentProfitProblem {
    public static long fiftyStartTime = System.currentTimeMillis();
    
    public void fiftyPercent(double[][] yolUzunlukAna,double[][] latLong,String[][] plakaSehir,double[][] grafKopya2) throws MalformedURLException, IOException{
        
        double[][] kisiKmKar=new double[5][4];
        int i,j,sayac=0;

        for(i=5;i<=45;i+=10){
            for(j=0;j<81;j++){
                if(yolUzunlukAna[i][j+1]==0){
                    kisiKmKar[sayac][1]=yolUzunlukAna[i][j];
                    kisiKmKar[sayac][0]=i+5;
                    break;
                }
            }
            sayac++;
        }

        double gider;
        double toplamGelir;
        for (i=0;i<5;i++) {
            gider=kisiKmKar[i][1]*10;
            toplamGelir=(1.5*gider);
            kisiKmKar[i][2]=toplamGelir/kisiKmKar[i][0];
            kisiKmKar[i][3]=toplamGelir-gider;
        }
        for(i=0;i<5;i++){
            for(j=0;j<5;j++){
                if(kisiKmKar[i][1]<9998 && kisiKmKar[j][1]<9998){
                    if(kisiKmKar[i][3]>kisiKmKar[j][3]){
                        double tampon[] = kisiKmKar[i];
                        kisiKmKar[i] = kisiKmKar[j];
                        kisiKmKar[j] = tampon;
                    }
                }
            }
        }
        int kisiSayac=0;
        System.out.println("");
        for(i=0;i<5;i++){
            if(kisiKmKar[i][1]<9998){ 
                    kisiSayac++;
            }
        }
        int tutulanJ=0;
        System.out.println("\n\nYüzde elli kâr problemi:\n");
        for(i=0;i<kisiSayac;i++){
            System.out.print("Kişi sayısı: "+kisiKmKar[i][0]+"  "+"Yollar: ");
            
            for(j=0;j<81;j++){
                if(yolUzunlukAna[(int)kisiKmKar[i][0]-5][j+1]!=0){
                    System.out.print(yolUzunlukAna[(int)kisiKmKar[i][0]-5][j]+" | ");
                    tutulanJ=j;
                }
            }
            System.out.print("Toplam Km: "+yolUzunlukAna[(int)kisiKmKar[i][0]-5][tutulanJ+1]);
            System.out.print(" | Kar: "+kisiKmKar[i][3]);
            System.out.print(" | Kişi Başı Ücret: "+kisiKmKar[i][2]+" TL.");
            System.out.println("");
        }   
        String plakalar=new String();
        for(j=0;j<81;j++){
            if(yolUzunlukAna[(int)kisiKmKar[0][0]-5][j+1]!=0){
                plakalar=plakalar.concat(yolUzunlukAna[(int)kisiKmKar[0][0]-5][j]+",");
            }
        }
        plakalar=plakalar.substring(0,plakalar.length()-1);
        double latCenterDouble=0;
        double longCenterDouble=0;
        String pathStr=new String();
        String yol=new String();
        int toplamSehir=0;
        for(i=0;i<81;i++){
            if(yolUzunlukAna[(int)kisiKmKar[0][0]-5][i+1]!=0){
                double plaka=yolUzunlukAna[(int)kisiKmKar[0][0]-5][i];
                yol=yol.concat(plakaSehir[(int)plaka-1][1]+"<-");
                pathStr=pathStr.concat(Double.toString(latLong[(int)plaka-1][1])+","
                        +Double.toString(latLong[(int)plaka-1][2])+"|");
                latCenterDouble+=latLong[(int)plaka-1][1];
                longCenterDouble+=latLong[(int)plaka-1][2];
                toplamSehir++;
            }
        }
        yol=yol.substring(0,yol.length()-2);
        pathStr=pathStr.substring(0,pathStr.length()-1);
        String path="path=color:0xff0000ff|weight:2|"+pathStr;
        String latCenter=Double.toString(latCenterDouble/toplamSehir);
        String longCenter=Double.toString(longCenterDouble/toplamSehir);
        
        int zoom;
        if(kisiKmKar[0][1]>960){
            zoom=5;
        }else if(kisiKmKar[0][1]>400 && kisiKmKar[0][1]<=960){
            zoom=6;
        }else{
            zoom=7;
        }
        String urlString="https://maps.googleapis.com/maps/api/staticmap?markers=color:red%7Csize:small|"+
                    pathStr+"&center="+latCenter+"%2c%20"+longCenter+"&zoom="+zoom+"&size=450x250&scale=2&"+
                    path+"&key=AIzaSyCcb7yUObjftrHLptoGJMN_8wVfWyWOb2g";
        URL url=new URL(urlString);
        Image image=null;
        image=ImageIO.read(url);
        
        
        
        JFrame frame=new JFrame("Yuzde Elli Problemi");
        JPanel board=new JPanel(null);
        frame.setSize(1000,710);
        JLabel label=new JLabel(new ImageIcon(image));
        Dimension size = label.getPreferredSize();
        label.setBounds(10, 10, size.width, size.height);
        JLabel labelYazi=new JLabel();
        JLabel kmYazi=new JLabel();
        JLabel kisiKar=new JLabel();
        JLabel kisiBasiUcret=new JLabel();
        labelYazi.setText(yol);
        labelYazi.setBounds(10, 290, size.width, size.height);
        labelYazi.setFont (labelYazi.getFont ().deriveFont (14.0f));
        if(kisiKmKar[0][1]>9998){
            kmYazi.setText("Uzaklık: Sonsuz.");
        }else{
            kmYazi.setText("Uzaklık: "+kisiKmKar[0][1]+" km.");
        }
        kmYazi.setBounds(10, 320, size.width, size.height);
        kmYazi.setFont (kmYazi.getFont ().deriveFont (20.0f));
        String kisiKarYazi=new String();
        if(kisiKmKar[0][1]>9998){
            kisiKarYazi=" - ";
        }else{
            kisiKarYazi=Double.toString(kisiKmKar[0][3]);
        }
        kisiKar.setText("Kişi sayisi:"+kisiKmKar[0][0]+"   Kar: "+kisiKarYazi+" TL.");
        kisiKar.setBounds(10, 350, size.width, size.height);
        kisiKar.setFont (kisiKar.getFont ().deriveFont (20.0f));
        String kisiBasiUcretYazi=new String();
        if(kisiKmKar[0][1]>9998){
            kisiBasiUcretYazi=" - ";
        }else{
            kisiBasiUcretYazi=Double.toString(kisiKmKar[0][2]);
        }
        kisiBasiUcret.setText("Kişi başı ücret: "+kisiBasiUcretYazi);
        kisiBasiUcret.setBounds(10, 380, size.width, size.height);
        kisiBasiUcret.setFont (kisiBasiUcret.getFont ().deriveFont (20.0f));
        
        board.add(label);
        board.add(labelYazi);
        board.add(kmYazi);
        board.add(kisiKar);
        board.add(kisiBasiUcret);
        frame.add(board);
        frame.setVisible(true);
        
        File dosya = new File("fifty_percent.txt");
        if (!dosya.exists()) {
            dosya.createNewFile();
        }

        FileWriter fileWriter = new FileWriter(dosya, false);
        String[] plakalarParcalanan=plakalar.split(",");

        try (BufferedWriter bWriter = new BufferedWriter(fileWriter)) {
            for(i=0;i<plakalarParcalanan.length;i++){
                bWriter.write(plakaSehir[(int)Double.parseDouble(plakalarParcalanan[i])-1][1]+" Şehrinin Bilgileri: ");
                bWriter.newLine();
                bWriter.write("Lat: "+Double.toString(latLong[(int)Double.parseDouble(plakalarParcalanan[i])-1][1]));
                bWriter.write(" Long: "+Double.toString(latLong[(int)Double.parseDouble(plakalarParcalanan[i])-1][2]));
                bWriter.newLine();
            }
            bWriter.newLine();
            bWriter.newLine();
            bWriter.write("Şehirler Arası Uzaklıklar:");
            bWriter.newLine();
            for(i=0;i<plakalarParcalanan.length-1;i++){
                bWriter.write(plakaSehir[(int)Double.parseDouble(plakalarParcalanan[i])-1][1]+" ile "+
                        plakaSehir[(int)Double.parseDouble(plakalarParcalanan[i+1])-1][1]+" Arasındaki Uzaklık: ");
                bWriter.newLine();
                bWriter.write(Double.toString(grafKopya2[(int)Double.parseDouble(plakalarParcalanan[i])-1][(int)Double.parseDouble(plakalarParcalanan[i+1])-1]));
                bWriter.write(" Km.");
                bWriter.newLine();
            }
        }
        
        if(kisiSayac==0){
            System.out.println("\nGirilen noktalar arasında yol bulunamadı.");
        }
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fiftyStartTime-= System.currentTimeMillis();
        fiftyStartTime=Math.abs(fiftyStartTime);        
    }
}
