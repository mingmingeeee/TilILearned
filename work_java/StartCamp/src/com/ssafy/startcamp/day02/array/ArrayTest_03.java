package com.ssafy.startcamp.day02.array;

import java.util.Arrays;
import java.util.Random;

public class ArrayTest_03 {
    public static void main(String[] args) {

        int N = 6;
        Random rand = new Random();
        // @@TODOBLOCK: 1~6까지의 random 정수 5개를 저장할 배열을 만들고 값을 저장하시오.
        int [] resultArray = new int [5];
        for (int i = 0; i < resultArray.length; i++) {
            resultArray[i] = rand.nextInt(N) + 1;
        }
        System.out.println(Arrays.toString(resultArray));
        // @@END:
        
        // @@TODOBLOCK: 위 배열에 저장된 요소 중 짝수만 더해서 합을 출력하시오.
        int sum = 0;
        for (int i = 0; i < resultArray.length; i++) {
            if (resultArray[i] % 2 == 0) {
                sum += resultArray[i];
            }
        }
        System.out.printf("총 합은: %d%n", sum);
        // @@END:
    }
}
