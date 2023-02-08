package util;

import data.Data;
import model.Poids;

public class Autonomie {
    
    private static final Data data=new Data("Tesla");

    public static double puissanceConsommer(Double puissance,int vitesse,Poids poids) {
        int kilometre=data.getInt("kilometre");
        int consommation=data.getInt("consomation");
        double consomationNow=(consommation*vitesse)/kilometre;
        return puissance-(consomationNow*poids.getPourcentage());
    }

    public static double consomation(Double puissance,int vitesse,Poids poids){
        int kilometre=data.getInt("kilometre");
        int consommation=data.getInt("consomation");
        double consomationNow=(consommation*vitesse)/kilometre;
        return consomationNow*poids.getPourcentage();
    }

    public static double consomationSansP(Double puissance,int vitesse){
        int kilometre=data.getInt("kilometre");
        int consommation=data.getInt("consomation");
        double consomationNow=(consommation*vitesse)/kilometre;
        return consomationNow;
    }

    public static long nbrSeconde(Double puissance,int vitesse,Poids poids){
        int kilometre=data.getInt("kilometre");
        int consommation=data.getInt("consomation");
        double consomationNow=(consommation*vitesse)/kilometre;
        double second=puissance/consomationNow;
        return Math.round(second);
    }

    public static float resteKilometre(Double puissance,int vitesse,Poids poids){
        long resteSeconde=nbrSeconde(puissance, vitesse,poids);
        System.out.println("-------------\nVitesse :"+vitesse+"km/h\n-------------Puissance :"+puissance+"\n-------------Reste seconde :"+resteSeconde);
        float kilometreenSeconde=3600/vitesse;
        System.out.println("REste en se :"+kilometreenSeconde);
        float resteKm=(resteSeconde/kilometreenSeconde);
        System.out.println("reste :"+resteKm);
        return resteKm;
    }

}
