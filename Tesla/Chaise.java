package piece;

import data.config.Data;

public class Chaise {
    
    private static final Data data=new Data("Tesla");

    public static int distance(int taille){
        int tailleMoyenne=data.getInt("taille_moyenne");
        int distanceVolant=data.getInt("distance_volant");

        int value=(taille*distanceVolant)/tailleMoyenne;
        
        return value;
    }

    public static void main(String[] args) {
        System.out.println(Chaise.distance(160));
    }

}
