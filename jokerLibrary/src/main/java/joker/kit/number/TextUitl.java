package joker.kit.number;

import android.support.annotation.MainThread;

import java.util.logging.Logger;

public class TextUitl {

    /* 隐藏手机号码中间四位   182****9898      */
    public static String hiddenPhone(String phone) {
        return phone.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
    }

    /*隐藏名字后面    陈**            */
    public static String hiddenName(String name) {
        return name.replaceAll("([\\d\\D]{1})(.*)", "$1**");
    }

    /*数值 取万 取整    1230215   ->  123万*/
    public static String priceInteWan(String price) {


        return price != null && !price.equals("") ?


                //Integer.parseInt(price) / 10000

                // Long.parseLong(price)/10000

                (int) Double.parseDouble(price) / 10000

                        + "万" : "0万";


    }


    public static void main(String[] args) {
//        System.out.println(Double.parseDouble("1230000.00"));
        System.out.println(priceInteWan("1230215.231"));
    }

}
