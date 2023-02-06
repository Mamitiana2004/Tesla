package piece;
public class Batterie{

    Double puissance;
    public Double getPuissance() {
        return puissance;
    }

    public void setPuissance(Double puissance) {
        this.puissance = puissance;
    }

    public Batterie(double p){
        puissance=p;
    }

}