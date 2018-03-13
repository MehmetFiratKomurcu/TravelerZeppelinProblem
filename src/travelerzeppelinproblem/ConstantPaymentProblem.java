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
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author Ali Recep KARACA and Mehmet Firat KOMURCU
 */
public class ConstantPaymentProblem {
    public static long constantStartTime = System.currentTimeMillis();
    public void constantPayment(double[][] yolUzunluk,double[][] latLong,String[][] plakaSehir,double[][] grafKopya1) throws IOException{
        
        String yol=new String();
        int i,j;
        double[] kar=new double[46];
        double[][] kisiKarMatris=new double[46][2];
        for(i=0;i<46;i++){
            kisiKarMatris[i][1]=Double.NEGATIVE_INFINITY;
        }
        double km = 0,ucret=100;
        boolean flag=false;
        for(i=0;i<46;i++){
            double gider = 0;
            double gelir = 0;
            for(j=0;j<81;j++){
                if(yolUzunluk[i][j+1] > 9998){
                    flag=true;
                    break;
                }
                if(yolUzunluk[i][j+1] == 0){
                    km=yolUzunluk[i][j];
                    break;
                } 
            }
            if(flag==true){
                break;
            }
            gelir = (i+5)*ucret;
            gider = km*10;
            kar[i] = gelir - gider;
            kisiKarMatris[i][0]=i+5;
            kisiKarMatris[i][1]=kar[i];
        }
        double max = kar[0];
        int k;
        int kisi=5;
        for(k = 0;k < i;k++){
            if(kar[k] > max){
                max = kar[k];
                kisi = k+5;
            }
        }
        Image image=null;
        String pathStr=new String();
        double latCenterDouble=0;
        double longCenterDouble=0;
        double maxKm=0;
        String plakalar=new String();
        try{

            for(j=0;j<81;j++){
                if(yolUzunluk[kisi-5][j+1] ==0){
                    maxKm=yolUzunluk[kisi-5][j];
                    break;
                }
                double plaka=yolUzunluk[kisi-5][j];
                plakalar=plakalar.concat(Double.toString(plaka)+",");
                yol=yol.concat(plakaSehir[(int)plaka-1][1]+"<-");
                pathStr=pathStr.concat(Double.toString(latLong[(int)plaka-1][1])+","
                        +Double.toString(latLong[(int)plaka-1][2])+"|");
                latCenterDouble+=latLong[(int)plaka-1][1];
                longCenterDouble+=latLong[(int)plaka-1][2];
                
            }
            plakalar=plakalar.substring(0,plakalar.length()-1);
            pathStr=pathStr.substring(0,pathStr.length()-1);
            yol=yol.substring(0,yol.length()-2);
            String path="path=color:0xff0000ff|weight:2|"+pathStr;
            String latCenter=Double.toString(latCenterDouble/j);
            String longCenter=Double.toString(longCenterDouble/j);
            int zoom;
            if(maxKm>960){
                zoom=5;
            }else if(maxKm>400 && maxKm<=960){
                zoom=6;
            }else{
                zoom=7;
            }
            String urlString="https://maps.googleapis.com/maps/api/staticmap?markers=color:red%7Csize:small|"+
                    pathStr+"&center="+latCenter+"%2c%20"+longCenter+"&zoom="+zoom+"&size=450x250&scale=2&"+
                    path+"&key=AIzaSyCcb7yUObjftrHLptoGJMN_8wVfWyWOb2g";
            URL url=new URL(urlString);
            image=ImageIO.read(url);
        }catch(IOException e){
            e.printStackTrace();
        }
        
        String karYazi=new String();
        if(max<0){
            karYazi="Zarar edildi.En az zarar: ";
        }else if(maxKm>9998){
            karYazi="Belirlenen noktalar arası yol bulunamadı.";
        }
        
        JFrame frame=new JFrame("Sabit Ucret Problemi");
        JPanel board=new JPanel(null);
        frame.setSize(1000,680);
        JLabel label=new JLabel(new ImageIcon(image));
        Dimension size = label.getPreferredSize();
        label.setBounds(10, 10, size.width, size.height);
        JLabel labelYazi=new JLabel();
        JLabel kmYazi=new JLabel();
        JLabel kisiKar=new JLabel();
        labelYazi.setText(yol);
        labelYazi.setBounds(10, 290, size.width, size.height);
        labelYazi.setFont (labelYazi.getFont ().deriveFont (14.0f));
        if(maxKm>9998){
            kmYazi.setText("Uzaklık: Sonsuz.");
        }else{
            kmYazi.setText("Uzaklık: "+maxKm+" km.");
        }
        kmYazi.setBounds(10, 320, size.width, size.height);
        kmYazi.setFont (kmYazi.getFont ().deriveFont (20.0f));
        kisiKar.setText("Kişi sayisi:"+kisi+"   Kar: "+karYazi+max+" TL.");
        kisiKar.setBounds(10, 350, size.width, size.height);
        kisiKar.setFont (kisiKar.getFont ().deriveFont (20.0f));
        
        board.add(label);
        board.add(labelYazi);
        board.add(kmYazi);
        board.add(kisiKar);
        frame.add(board);
        frame.setVisible(true);

        
        for(i=0;i<46;i++){
            for(j=0;j<46;j++){
                if(kisiKarMatris[i][1]>kisiKarMatris[j][1]){
                    double bufferKar=kisiKarMatris[i][1];
                    double bufferKisi=kisiKarMatris[i][0];
                    kisiKarMatris[i][1]=kisiKarMatris[j][1];
                    kisiKarMatris[i][0]=kisiKarMatris[j][0];
                    kisiKarMatris[j][1]=bufferKar;
                    kisiKarMatris[j][0]=bufferKisi;
                }
            }
        }

        int kisiSayac=0;
        for (i = 0; i < 46; i++) {
            if(kisiKarMatris[i][1]!=Double.NEGATIVE_INFINITY){
                kisiSayac++;
            }
        }
        int tutulanJ=0;
        System.out.println("\n\nSabit ücret problemi: \n");
        for(i=0;i<kisiSayac;i++){
            System.out.print("Kişi sayısı: "+kisiKarMatris[i][0]+"  | ");
            System.out.print("Yollar: ");
            for(j=0;j<81;j++){
                if(yolUzunluk[(int)kisiKarMatris[i][0]-5][j+1]!=0){
                    System.out.print(yolUzunluk[(int)kisiKarMatris[i][0]-5][j]+" | ");
                    tutulanJ=j;
                }  
            }
            System.out.print("Toplam Km: "+yolUzunluk[(int)kisiKarMatris[i][0]-5][tutulanJ+1]+" | ");
            System.out.print("Kar: "+kisiKarMatris[i][1]+" TL.");
            System.out.println("");
        }
        if(kisiSayac==0){
            System.out.println("\n\nGirilen noktalar arasında yol bulunamadı.");
        }
        
        File dosya = new File("constant_payment.txt");
        if (!dosya.exists()) {
            dosya.createNewFile();
        }

        FileWriter fileWriter = new FileWriter(dosya, false);
        String[] plakalarParcalanan=plakalar.split(",");
        int sayac=0;
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
                bWriter.write(Double.toString(grafKopya1[(int)Double.parseDouble(plakalarParcalanan[i])-1][(int)Double.parseDouble(plakalarParcalanan[i+1])-1]));
                bWriter.write(" Km.");
                bWriter.newLine();
            }
        }
        
        //Programı durdurması için.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        constantStartTime-= System.currentTimeMillis();
        constantStartTime=Math.abs(constantStartTime);
    }
}
