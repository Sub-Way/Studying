import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class hwalgo0528_서울_11반_임진섭 {
	static int N, M, R, C, L, time;
	static int[][] map;
	static boolean[][] check;
	static boolean[][] visit;
	static int[][] dir = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken()); // 세로
			M = Integer.parseInt(st.nextToken()); // 가로
			R = Integer.parseInt(st.nextToken()); // 맨홀 세로
			C = Integer.parseInt(st.nextToken()); // 멘홀 가로
			L = Integer.parseInt(st.nextToken()); // 탈출 후 소요된 시간
			time = 0;
			map = new int[N][M];
			check = new boolean[N][M];
			visit = new boolean[N][M];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			dfs(R, C, 0);
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if(visit[i][j] == true)
						time++;
				}
			}
			
			System.out.println("#" + tc+ " " + time);
		} // end of tc

	} // end of main

	public static void dfs(int i, int j, int value) {
		if(L==1) {
			time = L;
			return;
		}

		if(value == L)
			return;
		
		visit[i][j] = true;
		int cur = map[i][j];
		
		 for (int d = 0; d < 4; d++) {
			 int next =0;
		        if (d == 0 && (cur == 3 || cur == 5 || cur == 6)) continue;
		        if (d == 1 && (cur == 2 || cur == 6 || cur == 7)) continue;
		        if (d == 2 && (cur == 3 || cur == 4 || cur == 7)) continue;
		        if (d == 3 && (cur == 2 || cur == 4 || cur == 5)) continue;
		 
		        int ni = i + dir[d][0];
		        int nj = j + dir[d][1];
		 
		        if (ni < 0 || nj < 0 || ni >= N || nj >= M)continue;
		        else if (map[ni][nj] == 0 || check[ni][nj]) continue;
		 
		        next = map[ni][nj];
		        if (d == 0 && (next == 3 || next == 4 || next == 7)) continue;
		        if (d == 1 && (next == 2 || next == 4 || next == 5)) continue;
		        if (d == 2 && (next == 3 || next == 5 || next == 6)) continue;
		        if (d == 3 && (next == 2 || next == 6 || next == 7)) continue;
		        
		        check[i][j] = true;
		        dfs(ni, nj, value+1);
		        check[i][j] = false;
		    }
	}

	public static boolean isin(int ni, int nj) {
		return 0 <= ni && ni < N && 0 <= nj && nj < M;
	}
} // end of class