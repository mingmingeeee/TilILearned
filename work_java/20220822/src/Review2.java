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
		minEdge[0] = 0;

		for (int count = 0; count < N; count++) {
			
			int min = Integer.MAX_VALUE;
			int minVertex = 0;
			
			// 1. 먼저 신장트리에 연결되지 않은 정점 중에서 edge비용이 최소인 정점
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
			
			
			// 2. 이어서 신장트리에 포함되지 않은 정점들 중에서 최솟값이 나오면 업데이트
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
