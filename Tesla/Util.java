package util;

import java.util.Date;

public class Util {
    
    public static int getHeureNow(){
        Date date=new Date();
        return date.getHours();
    }

    public static void main(String[] args) {
        System.out.println(Util.getHeureNow());
    }

}
