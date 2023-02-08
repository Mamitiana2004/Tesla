package piece;

import data.Data;

public class Volant {
    private static final Data data=new Data("Tesla");

    public static int distance(int taille){
        int tailleMoyenne=data.getInt("taille_moyenne");
        int distanceVolant=data.getInt("hauteur_volant");
        int value=(taille*distanceVolant)/tailleMoyenne;
        return value;
    }
}
