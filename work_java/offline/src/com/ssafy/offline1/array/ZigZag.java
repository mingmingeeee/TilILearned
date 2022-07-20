package com.ssafy.offline1.array;

public class ZigZag {
	public static void main(String[] args) {
		int[][] arr = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		
		// 1 2 3 ... 9
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) { // arr[i].length : 가로길이
//				System.out.print(arr[i][j] + " ");
			}
		}
		
		// 3 2 1 6 5 4 9 8 7
		for(int i=0; i<arr.length; i++) {
			for(int j = arr[i].length-1; j>=0;j--) {
//				System.out.print(arr[i][j] + " ");
			}
		}
		
		
		// 지그재그
		 for(int i =0;i<arr.length;i++) {
			 if(i%2==0) {
					for (int j = 0; j < arr[i].length; j++) { // arr[i].length : 가로길이
						System.out.print(arr[i][j] + " ");
					}
			 }
			 else {
				 for(int j = arr[i].length-1; j>=0;j--) {
						System.out.print(arr[i][j] + " ");
					}
			 }
				
		 }
		
		// 9 8 7 ... 1
//		for(int i=arr.length-1; i>=0;i--) {
//			for(int j = arr[i].length-1; j>=0;j--) {
//				System.out.print(arr[i][j] + " ");
//			}
//		}
	}
}
