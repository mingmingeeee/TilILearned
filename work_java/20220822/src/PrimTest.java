
import java.io.*;
import java.util.*;

/**
 * 1. 먼저 신장트리에 연결되지 않은 정점 중에서 edge비용이 최소인 정점을 골라낸다.
 * 2. 이어서 신장트리에 포함되지 않은 정점들 중에서 최솟값이 나오면 업데이트 한다.
 */

public class PrimTest {

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(in.readLine()); // 2차원 배열 -> 길이

		// 인접 행렬
		int[][] adjMatrix = new int[N][N];
		int[] minEdge = new int[N]; // 타 정점에서 자신으로의 간선 비용 중 최소 비용 저장 배열 (예: 0번 정점에서 최소 비용, 1번 정점에서 최소 비용, ...)
		boolean[] visited = new boolean[N]; // 신장트리에 선택된 여부

		// input 파일에 주어진 인접 행렬 데이터를 adjMatrix에 담기
		for (int i = 0; i < N; i++) {
			String[] split = in.readLine().split(" ");
			for (int j = 0; j < N; j++) {
				adjMatrix[i][j] = Integer.parseInt(split[j]);
			}
			minEdge[i] = Integer.MAX_VALUE; // 최솟값 초기화
		}

		int result = 0; // MST 비용 (누적 시킬 비용)
		minEdge[0] = 0; // 임의의 시작점 비용 0 설정 (0번 정점부터 시작한다는 의미 -> 내가 나를 가리킬 때 가중치 0)

		// 모든 정점 수 만큼 반복 
		for (int count = 0; count < N; count++) {
			
			// 신장 트리에 연결되지 않은 정점 중 minEdge 비용이 최소인 정점
			int min = Integer.MAX_VALUE; // 초기 값으로 정수의 최대치를 주고 시작
			int minVertex = 0;

			// 1단계 -> 현재 정점으로 들어오는 간선들 중 제일 짧은 거 선택 
			for (int i = 0; i < N; i++) {
				if (!visited[i] && min > minEdge[i]) { 
					min = minEdge[i]; // 제일 짧은 값
					minVertex = i; // 제일 짧은 값 가지고 있는 간선 index
				}
			}
			
			// 선택된 정점을 신장 트리에 포함시킴
			visited[minVertex] = true;
			result += min;

			// 2단계: 선택된 정점 기준으로 신장 트리에 포함되지 않은 다른 정점으로의 간선비용을 따져보고
			// = minVertex 기준
			// 신장트리에 포함되지 않은 정점들 중에서 최소값이 나오면 업데이트
			for(int i=0; i<N; i++) {
				// adjMatrix[from][to] -> from에서 to로 이어진 가중치 
				// MST에 선택X && minVertex(선택된 정점)이랑 이어져 있고 && minEdge[i]보다 작을 때 
				if(!visited[i] && adjMatrix[minVertex][i] != 0 && minEdge[i] > adjMatrix[minVertex][i]) {
					minEdge[i] = adjMatrix[minVertex][i];
				}
			}
			
		}

		System.out.println(result);
		
	}

}
