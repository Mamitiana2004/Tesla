package util;

import java.util.Date;

public class Helper {
    
    public static int getHeureNow(){
        Date date=new Date();
        return date.getHours();
    }

}
