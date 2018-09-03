package util;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public class BaseUtil {

    //进制之间的转换
    public static int numberTransform(String s, int start, int end) {

        BigInteger bigInteger = new BigInteger(s, start);

        return Integer.parseInt(bigInteger.toString(end));

    }

    //判断字符是否为数字
    public static Boolean stringIsNumber(String s) {

        Pattern pattern = Pattern.compile("^[-+]?[\\d]*$");

        return !s.equals("") && pattern.matcher(s).matches();

    }

    //string to date
    public static Date stringToDate(String string, String flag) throws ParseException {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        if (flag.equals("start")) {
            string += " 00:00:00";
        } else {
            string += "23:59:59";
        }

        return (string.equals("") || string == null) ? null : simpleDateFormat.parse(string);

    }

}
