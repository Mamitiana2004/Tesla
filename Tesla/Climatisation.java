package user;
public class Climatisation {
    
    int heure;

    double temperature;

    public int getHeure() {
        return heure;
    }
    public double getTemperature() {
        return temperature;
    }

    public void setHeure(int heure) {
        this.heure = heure;
    }
    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public Climatisation(double t,int h){
        temperature=t;
        heure=h;
    }

}
