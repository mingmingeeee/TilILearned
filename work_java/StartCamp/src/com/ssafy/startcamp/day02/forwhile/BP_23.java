package com.ssafy.startcamp.day02.forwhile;

import java.util.Random;

public class BP_23 {
    public static void main(String[] args) {
    	byFor();
    	byWhile();
    }
    
    private static void byFor() {
        int sum = 0;
        int cnt = 100;
        double avg = 0;
        Random rand = new Random();

        for (int i = 0; i < cnt; i++) {
            sum += rand.nextInt(6) + 1;
        }
        avg = 1.0 * sum / cnt;
            
        System.out.printf("sum: %d, avg: %f%n", sum, avg);

    }
    
    public static void byWhile() {
        int sum = 0;
        int cnt = 100;
        double avg = 0;
        Random rand = new Random();
        int i = 0;

        while (i < cnt) {
            sum += rand.nextInt(6) + 1;
            i++;
        }
        avg = 1.0 * sum / cnt;

        System.out.printf("sum: %d, avg: %f%n", sum, avg);
    }

}
