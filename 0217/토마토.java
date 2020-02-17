import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int m = sc.nextInt();
		int n = sc.nextInt();

		int[][] arr = new int[n][m];
		Queue<Dot> q = new LinkedList<Dot>();
		int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
		
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				arr[i][j] = sc.nextInt();
				if (arr[i][j] == 1) {
					Dot d = new Dot(i, j, 0);
					q.add(d);
				}
			}
		} // end of input
		int count = 0;
		int next_y, next_x;
		while (!q.isEmpty()) {
			int cur_y = q.peek().r;
			int cur_x = q.peek().c;
			count = q.peek().count;
			q.poll();
			for (int i = 0; i < dir.length; i++) {
				next_y = cur_y + dir[i][0];
				next_x = cur_x + dir[i][1];
				if (0 <= next_y && next_y < n && 0 <= next_x && next_x < m && arr[next_y][next_x] == 0) {
					arr[next_y][next_x] = 1;
					Dot d = new Dot(next_y, next_x, count + 1);
					q.add(d);
				} // end of if
			} // end of for
		} // end of while
		
		boolean flag = true;
		for (int i = 0; i < arr.length && flag; i++) {
			for (int j = 0; j < arr[i].length && flag; j++) {
				if (arr[i][j] == 0) {
					count = -1;
					flag = false;
				}
			}
		}
		System.out.println(count);
	} // end of main

} // end of class

class Dot {
	int r;
	int c;
	int count;

	public Dot(int r, int c, int count) {
		this.r = r;
		this.c = c;
		this.count = count;
	}
}