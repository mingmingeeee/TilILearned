package SWEA;

import java.io.*;
import java.util.*;

public class S_5653_2 {
     
    static int T, N, M, K, time;
     
    static final int MAP_SIZE = 1000;
    static final int PADDING = 400;
     
    static boolean[][] visit;
    static Queue<Cell> qu;          // 비활성화된 줄기세포를 담을 큐
    static PriorityQueue<Cell> pq;  // 활성화된 줄기세포를 담을 우선순위 큐
     
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
     
    static class Cell implements Comparable<Cell> {
        int x, y, p, active, dead;
        Cell(int x, int y, int p, int active, int dead) {
            this.x = x;
            this.y = y;
            this.p = p;
            this.active = active;  // 활성화될 시간
            this.dead = dead;      // 죽을 시간
        }
         
        @Override
        public int compareTo(Cell o) {
            return o.p - this.p;   // 생명력 내림차순 정렬
        }
    }
 
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
         
        T = Integer.parseInt(br.readLine());
         
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
             
            visit = new boolean[MAP_SIZE][MAP_SIZE];
            qu = new ArrayDeque<>();
            pq = new PriorityQueue<>();
             
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    int p = Integer.parseInt(st.nextToken());
                    if(p != 0) {
                        visit[i+PADDING][j+PADDING] = true;
                        qu.offer(new Cell(i+PADDING, j+PADDING, p, p, p*2));
                    }
                }
            }
             
            time = 0;
            while(time++ < K) {
                check();  // qu에 있는 세포의 상태 확인
            }
             
            System.out.println("#" + t + " " + qu.size());
        }
    }
     
    static void check() {
        int size = qu.size();
         
        for (int i = 0; i < size; i++) {
            Cell c = qu.poll();
             
            if(time <= c.active) qu.offer(c); // 활성화되기 전이면 qu에 삽입
            else if(time == c.active + 1) pq.offer(c); // 활성화되면 pq에 삽입
            else if(c.active < time && time < c.dead) qu.offer(c); // 죽기 전이면 qu에 삽입
        }
        
        size = pq.size();
        
        for (int i = 0; i < size; i++) {
        	Cell c = pq.poll();
            
        	if(time < c.dead) qu.offer(c);
             
            for (int d = 0; d < 4; d++) {
                int tx = c.x + dx[d];
                int ty = c.y + dy[d];
                 
                if(visit[tx][ty]) continue;
                 
                visit[tx][ty] = true;
                qu.offer(new Cell(tx, ty, c.p, time + c.p, time + c.p*2));
            }
        }
    }
     
}