package com.ssafy.startcamp.day02.array;

public class ArrayTest_13 {
    public static void main(String[] args) {
        int[] intArray = {1, 3, 4, 7, 8, 10, 12, 15, 16, 17, 18};

        // @@TODOBLOCK: 1~20까지의 숫자 중 intArray에서 사용되지 않은 숫자를 출력하세요.
        int[] used = new int[21];
        for (int num : intArray) {
            used[num]++;
        }
        
        for (int i = 1; i < used.length; i++) {
            if (used[i] == 0) {
                System.out.print(i+" ");
            }
        }
        // @@END:
    }
}