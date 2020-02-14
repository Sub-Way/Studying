import java.util.Scanner;

public class Solution_SWEA_1223_정사각형방_D4_임진섭 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int t = sc.nextInt();

		for (int tc = 1; tc <= t; tc++) {
			int n = sc.nextInt();
			int[][] arr = new int[n][n];

			for (int i = 0; i < arr.length; i++) {
				for (int j = 0; j < arr[i].length; j++) {
					arr[i][j] = sc.nextInt();

				}
			} // end of input

			int num = Integer.MAX_VALUE;
			int max = 0;
			for (int i = 0; i < arr.length; i++) {
				for (int j = 0; j < arr[i].length; j++) {
					int r = i;
					int c = j;
					int cnt = 1;
					int temp = arr[i][j];
					while (true) {
						int flag = -1;
						if (r + 1 < n && arr[r + 1][c] - arr[r][c] == 1) {// 상
							r = r + 1;
							flag = 0;
							cnt++;
							// System.out.print(arr[r][c]);
						} else if (r - 1 >= 0 && arr[r - 1][c] - arr[r][c] == 1) { // 하
							r = r - 1;
							flag = 0;
							cnt++;
							// System.out.print(arr[r][c]);
						} else if (c - 1 >= 0 && arr[r][c - 1] - arr[r][c] == 1) {// 좌
							c = c - 1;
							flag = 0;
							cnt++;
							// System.out.print(arr[r][c]);
						} else if (c + 1 < n && arr[r][c + 1] - arr[r][c] == 1) {// 우
							c = c + 1;
							flag = 0;
							cnt++;
							// System.out.print(arr[r][c]);
						}
						if (flag == -1)
							break;
					} // end of while

					if (max < cnt && num > temp) {
						max = cnt;
						num = temp;
					}
				}
			} // end of test
			System.out.println("#" + tc + " " + num + " " + max);
		} // end of tc

	} // end of main

} // end of class
