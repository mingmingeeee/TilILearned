package com.ssafy.startcamp.day02.array;

import java.util.Arrays;

public class ArrayTest_12 {
    public static void main(String[] args) {
        int[] intArray = {3, 7, 2, 5, 7, 7, 9, 2, 8, 1, 1, 5, 3};

        // @@TODOBLOCK: 각 숫자가 몇 번 사용 되었는지 숫자별로 사용 횟수를 출력 하세요.
        // @@KEEP: 사용 안된 숫자는 0으로 출력 한다
        int[] used = new int[10];
        
        for (int num : intArray) {
            used[num]++;
        }

        System.out.println(Arrays.toString(used));
        
        // @@END:
    }
}
