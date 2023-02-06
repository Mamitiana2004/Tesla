package util;

import data.config.Data;

public class Autonomie {
    
    private static final Data data=new Data("Tesla");

    public static double puissanceConsommer(Double puissance,int vitesse) {
        int kilometre=data.getInt("kilometre");
        int consommation=data.getInt("consomation");
        double consomationNow=(consommation*vitesse)/kilometre;
        return puissance-consomationNow;
    }

    public static long nbrSeconde(Double puissance,int vitesse){
        int kilometre=data.getInt("kilometre");
        int consommation=data.getInt("consomation");
        double consomationNow=(consommation*vitesse)/kilometre;
        double second=puissance/consomationNow;
        return Math.round(second);
    }

}
