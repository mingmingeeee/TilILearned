package hwalgo15_부울경_04반_강민정;

import java.io.*;
import java.util.*;

public class Review2 {

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(in.readLine());

		int[][] adjMatrix = new int[N][N];
		int[] minEdge = new int[N];
		boolean[] visited = new boolean[N];

		for (int i = 0; i < N; i++) {
			String[] split = in.readLine().split(" ");
			for (int j = 0; j < N; j++) {
				adjMatrix[i][j] = Integer.parseInt(split[j]);
			}
			minEdge[i] = Integer.MAX_VALUE;
		}

		int result = 0;
		minEdge[0] = 0; // 출발점 자기 자신 -> 0

		for (int count = 0; count < N; count++) {
			
			int min = Integer.MAX_VALUE;
			int minVertex = 0;
			
			// 1. MST에 연결되지 않은 정점 중에서 edge비용이 최소인 정점
			for(int i=0; i<N; i++) {
				if(!visited[i] && min > minEdge[i]) {
					min = minEdge[i];
					minVertex = i;
				}
			}
			
			// 선택된 정점 -> 트리에 포함
			visited[minVertex] = true;
			result += min;
			System.out.println("##"+min);
			
			
			// 2. MST에 하나 추가하면, 신장트리에 포함되지 않은 정점들 중에서 
			//    MST에 새로 추가된 edge와 인접한 정점들 중 새로운 최솟값이 나오면 업데이트
			for(int i=0; i<N; i++) {
				if(!visited[i] && adjMatrix[minVertex][i]!=0 && minEdge[i] > adjMatrix[minVertex][i]) {
					minEdge[i] = adjMatrix[minVertex][i];
					System.out.println(i + " " + minEdge[i]);
				}
			}
			

		}
		System.out.println(result);

	}

}