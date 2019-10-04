package com.rsoft.guesser.util;

import java.util.Random;

public class RandomGenerator {
    private static Random rand = new Random();

    public static int getRandomNumberUpTo(int max) {
        return rand.nextInt(max + 1);
    }
}
