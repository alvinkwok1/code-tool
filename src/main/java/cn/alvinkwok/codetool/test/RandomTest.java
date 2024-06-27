package cn.alvinkwok.codetool.test;

import java.util.Random;

/**
 * Description
 *
 * @author alvinkwok
 * @since 2024/5/8
 */
public class RandomTest {
    public static void main(String[] args) {
        Random random = new Random();
        System.out.println( random.nextInt(1000));
    }
}
