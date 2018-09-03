package util;

import org.junit.Test;

import static org.junit.Assert.*;

public class BaseUtilTest {

    @Test
    public void numberTransform() {

        int i = BaseUtil.numberTransform("39", 10, 2);
        System.out.println(i);
        System.out.println(BaseUtil.numberTransform("000000100111", 2, 10));

    }

    @Test
    public void stringIsNumber() {

        System.out.println(BaseUtil.stringIsNumber("").toString());

        System.out.println("sdsf11111111".length() != 12);

        int i = 2;

        System.out.println(-i);

    }
}