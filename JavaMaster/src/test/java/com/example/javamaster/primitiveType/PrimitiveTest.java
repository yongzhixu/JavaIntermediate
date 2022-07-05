package com.example.javamaster.primitiveType;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class PrimitiveTest {

    //  https://www.geeksforgeeks.org/difference-between-an-integer-and-int-in-java/#:~:text=In%20Java%2C%20int%20is%20a,and%20manipulating%20an%20int%20data.
    @Test
    void test1() {

//        int is a java keyword, Integer is a java primitive datatype
//        Wrapper class              ||        Primitive Datatype
//        ===========================||==============================
//        java.lang.Byte;            ||        byte
//        java.lang.Integer;         ||        int
//        java.lang.Short;           ||        short
//        java.lang.Long;            ||        long
//        java.lang.Float;           ||        float
//        java.lang.Double;          ||        double
//        java.lang.Character;       ||        char
//        java.lang.Boolean;         ||        boolean


        String str = "sfd";
        str.chars().forEach(System.out::println);
        int a = Integer.parseInt("1");
        Byte num_byt = null;
        Short num_short = null;
        Integer num_int = null;
        Long num_long = null;
        Float num_float = null;
        Double num_double = null;
        Character character = null;
        Boolean checked = null;

        num_double = -12.4;
        log.info(String.format("Byte 的最小值：%d； Byte 的最大值：%d； Byte 的默認值：%d", Byte.MIN_VALUE, Byte.MAX_VALUE, num_byt));
        log.info(String.format("Integer 的最小值：%d； Integer 的最大值：%d； Integer 的默認值：%d", Integer.MIN_VALUE, Integer.MAX_VALUE, num_int));
        log.info(String.format("Short 的最小值：%d； Short 的最大值：%d； Short 的默認值：%d", Short.MIN_VALUE, Short.MAX_VALUE, num_short));
        log.info(String.format("Long 的最小值：%d； Long 的最大值：%d； Long 的默認值：%d", Long.MIN_VALUE, Long.MAX_VALUE, num_long));
        log.info(String.format("Integer Float：%f； Float 的最大值：%f； Float 的默認值：%f", Float.MIN_VALUE, Float.MAX_VALUE, num_float));
        log.info(String.format("Double 的最小值：%f； Double 的最大值：%f； Double 的默認值：%f", Double.MIN_VALUE, Double.MAX_VALUE, num_double));
        log.info("Character 无取值范围");
        log.info(String.format("Double 的最小值：%f； Double 的最大值：%f； Double 的默認值：%f", Double.MIN_VALUE, Double.MAX_VALUE, num_float));

    }

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void test2() {
    }

    @Test
    void test3() {
    }

    @Test
    void overflow() {

    }

    @Test
    void test4() {
    }

    @Test
    void testOverflow() {
    }

    @Test
    void initialization() {
/**
 * Local variables must be initialized before use,
 */

//        We can simply declare an int:
        int x = 424_242;
        int y;

//        Here's how we can create byte:
        byte b = 100;
        byte empty;

//        short is declared like this:
        short s = 20_020;
        short sg;

//        We can simply declare one long:
        long l = 1_234_567_890;
        long lg;

//        We declare floats the same as any other type:
        float f = 3.145f;
        float fg;

//        Declaring double is the same as other numeric types:
        double d = 3.13457599923384753929348D;
        double dg;

//        Here's how we declare boolean:
        boolean ba = true;
        boolean bg;

//        Let's now declare a char:
        char c = 'a';
        c = 65;
        char cg;
        char charUsingUnicode = '\u0044';
        System.out.println(charUsingUnicode);
        char character ='我';


        System.out.println(Character.getType(character));
        charUsingUnicode='\u1F60';
        System.out.println(charUsingUnicode);
        System.out.println("😀😂".contains("😂"));
        System.out.println("😀😂");

        char ch = '\u00CC';
        System.out.println("========="+ch);
        Character.UnicodeBlock block = Character.UnicodeBlock.of(ch);
        System.out.println(block);
        System.out.println(Character.UnicodeBlock.of(' '));
        System.out.println(Character.UnicodeBlock.of('\u21ac'));
        System.out.println(Character.UnicodeBlock.of(1565));
    }

    //  https://www.geeksforgeeks.org/difference-between-an-integer-and-int-in-java/#:~:text=In%20Java%2C%20int%20is%20a,and%20manipulating%20an%20int%20data.
    @Test
    void size_default() {

//        int is a java keyword, Integer is a java primitive datatype
//        Wrapper class              ||        Primitive Datatype
//        ===========================||==============================
//        java.lang.Byte;            ||        byte
//        java.lang.Integer;         ||        int
//        java.lang.Short;           ||        short
//        java.lang.Long;            ||        long
//        java.lang.Float;           ||        float
//        java.lang.Double;          ||        double
//        java.lang.Character;       ||        char
//        java.lang.Boolean;         ||        boolean


        String str = "sfd";
        str.chars().forEach(System.out::println);
        int a = Integer.parseInt("1");
        Byte num_byt = null;
        Short num_short = null;
        Integer num_int = null;
        Long num_long = null;
        Float num_float = null;
        Double num_double = null;
        Character character = null;
        Boolean checked = null;

//        byte primitive datatype;
//        String , dtatype, Class
        num_double = -12.4;
        log.info(String.format("Byte 的最小值：%d； Byte 的最大值：%d； Byte 的默認值：%d", Byte.MIN_VALUE, Byte.MAX_VALUE, num_byt));
        log.info(String.format("Integer 的最小值：%d； Integer 的最大值：%d； Integer 的默認值：%d", Integer.MIN_VALUE, Integer.MAX_VALUE, num_int));
        log.info(String.format("Short 的最小值：%d； Short 的最大值：%d； Short 的默認值：%d", Short.MIN_VALUE, Short.MAX_VALUE, num_short));
        log.info(String.format("Long 的最小值：%d； Long 的最大值：%d； Long 的默認值：%d", Long.MIN_VALUE, Long.MAX_VALUE, num_long));
        log.info(String.format("Float：%f； Float 的最大值：%f； Float 的默認值：%f", Float.MIN_VALUE, Float.MAX_VALUE, num_float));
        log.info("Float 的最小值："+ Float.MIN_VALUE);
        log.info("Float 的最大值："+ Float.MAX_VALUE);

        log.info(String.format("Double 的最小值：%400.20f； Double 的最大值：%400.20f； Double 的默認值：%400.20f", Double.MIN_VALUE, Double.MAX_VALUE, num_double));
        log.info("Double 的最小值："+ Double.MIN_VALUE);
        log.info("Double 的最大值："+ Double.MAX_VALUE);
        log.info("Character 无取值范围");
        log.info("Character 无连续取值范围");

    }

    @Test
    void testOverflow1() {
        int i = Integer.MAX_VALUE;
        int j = i + 1;
// j will roll over to -2_147_483_648

        double d = Double.MAX_VALUE;
        double o = d + 1;
// o will be Infinity
    }

    @Test
    void testInitialization() {
    }

    @Test
    void testSize_default() {
    }

    @Test
    void testOverflow2() {
    }

    @Test
    void testInitialization1() {
    }

    @Test
    void aotuboxing() {
//        Wrapper class enable you to do more operations over the primitive type
        int num_boxed = 1;
        int minValue = Integer.MIN_VALUE;
        int num = 0;
        Integer.toString(num_boxed);
//num.toString(); //NA
        Integer a = null;
//        int b=null; //NA
        assertNull(a);
    }
}