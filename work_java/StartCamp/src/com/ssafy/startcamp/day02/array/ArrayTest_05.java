package com.ssafy.startcamp.day02.array;

public class ArrayTest_05 {
    public static void main(String[] args) {
        String org = "1234567890";

        // @@TODOBLOCK: String "1234567890" 의 자리 별 수를 1차원 배열에 저장하고 배열을 순회해서 그 합을 출력하시오.
        char [] nums = org.toCharArray();

        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
        	sum += nums[i] - '0';
        }
        System.out.printf("sum: %d%n", sum);
        // @@END:
    }
}
