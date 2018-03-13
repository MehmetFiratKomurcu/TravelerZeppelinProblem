/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travelerzeppelinproblem;

/**
 *
 * @author Ali Recep KARACA and Mehmet Firat KOMURCU
 */
public class Dijkstra {
    
    public static int boyut=81;
    public static int INFINITY=9999;
    
    public static double[] dijkstra(double graf[][],int ilSayisi,int baslangicNoktasi,int varisNoktasi){
        double[][] maliyet=new double[boyut][boyut];
        double[] uzaklik;
        int[] ugranan,onceki;
        uzaklik=new double[boyut];
        ugranan=new int[boyut];
        onceki=new int[boyut];
        double minUzaklik;
        int sayac,sonrakiDugum,i,j;
        sonrakiDugum=0;
        
        for(i=0;i<ilSayisi;i++)
        for(j=0;j<ilSayisi;j++)
            if(graf[i][j]==0)
                maliyet[i][j]=INFINITY;
            else
                maliyet[i][j]=graf[i][j];
        
        for(i=0;i<ilSayisi;i++){
            uzaklik[i]=maliyet[baslangicNoktasi][i];
            onceki[i]=baslangicNoktasi;
            ugranan[i]=0;
        }
        
        uzaklik[baslangicNoktasi]=0;
        ugranan[baslangicNoktasi]=1;
        sayac=1;
        
        while(sayac<ilSayisi-1)
        {
            minUzaklik=INFINITY;

            //sonrakidugum en ucuz maliyeti verir
            for(i=0;i<ilSayisi;i++)
                if(uzaklik[i]<minUzaklik&&ugranan[i]==0)
                {
                    minUzaklik=uzaklik[i];
                    sonrakiDugum=i;

                }

                //Daha iyi bir yol varsa
                ugranan[sonrakiDugum]=1;
                for(i=0;i<ilSayisi;i++)
                    if(ugranan[i]==0)
                        if(minUzaklik+maliyet[sonrakiDugum][i]<uzaklik[i])
                        {
                            uzaklik[i]=minUzaklik+maliyet[sonrakiDugum][i];
                            onceki[i]=sonrakiDugum;
                        }
            sayac++;
        }
        
        double[] yolUzaklik=new double[82];
        
        i=varisNoktasi;

        j=i;
        sayac=1;
        yolUzaklik[0]=varisNoktasi+1;
        do
        {
            j=onceki[j];
            yolUzaklik[sayac]=j+1;
            sayac++;
        }while(j!=baslangicNoktasi);
        yolUzaklik[sayac]=uzaklik[i];
        for(i=sayac+1;i<82;i++){
            yolUzaklik[i]=0;
        }

        return yolUzaklik;
    }
}
