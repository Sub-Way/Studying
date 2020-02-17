import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static Scanner sc = new Scanner(System.in);
	static int n = sc.nextInt(); // 구역의 개수
	static int[][] arr = new int[n][n]; // 구역
	static int[] man = new int[n]; // 구역의 인구 배열
	static int minPeople = Integer.MAX_VALUE;

	public static void main(String[] args) {

		for (int i = 0; i < n; i++) {
			man[i] = sc.nextInt();
		} // 구역 마다의 인구 저장

		for (int i = 0; i < n; i++) {
			int cnt = sc.nextInt(); // 인접한 구역의 정보
			for (int j = 0; j < cnt; j++) {
				int near = sc.nextInt() - 1;
				arr[i][near] = 1;
				arr[near][i] = 1;
			}
		}

		// 구역을 두개로 나눈다. A, B 2^n-1
		int k = (int) Math.pow(2, n - 1); // 부분 집합의 개수

		// 비트마스크를 이용하여 2개의 구역 A,B로 나눔
		for (int i = 1; i < k; i++) {
			ArrayList<Integer> A = new ArrayList<Integer>(); // A 구역을 저장할 리스트
			ArrayList<Integer> B = new ArrayList<Integer>(); // B 구역을 저장할 리스트
			for (int j = 0; j < n; j++) { 
				int b = (int) Math.pow(2, j);
				if ((i & b) != 0) {
					A.add(j);
				} else {
					B.add(j);
				}
			}
			connected(A, B); // A, B 구역 각각의 연결상태를 확인
		}
		if (minPeople != Integer.MAX_VALUE)
			System.out.println(minPeople);
		else
			System.out.println("-1");
	} // end of main

	public static void minPeople(ArrayList<Integer> a, ArrayList<Integer> b) {
		int sumA = 0;
		int sumB = 0;
		for (int i = 0; i < a.size(); i++) {
			sumA += man[a.get(i)];
		}
		for (int i = 0; i < b.size(); i++) {
			sumB += man[b.get(i)];
		}
		minPeople = Math.min(minPeople, Math.abs(sumA - sumB));
	} // end of minPeople

	// 연결 상태를 체크 해본다.
	public static void connected(ArrayList<Integer> a, ArrayList<Integer> b) {
		boolean groupA = true;
		boolean groupB = true;

		if (a.size() != 1) { // 구역이 1개가 아니라면 검사
			groupA = bfs(a);
		}

		if (b.size() != 1) { // 구역이 1개가 아니라면 검사
			groupB = bfs(b);
		}

		if (groupA && groupB) // 두 구역 모두 연결 되어있다면
			minPeople(a, b);
	} // end of connected

	public static boolean bfs(ArrayList<Integer> a) {
		boolean flag = true; // 연결 여부를 리턴할 변수
		boolean[] checked = new boolean[n]; // 체크 배열
		Queue<Integer> q = new LinkedList<Integer>(); // BFS 탐색을 위한 큐 생성
		int s = a.get(0); // 맨 처음 시작점
		q.add(s);
		checked[s] = true; // 시작지점 true
		while (!q.isEmpty()) { // 큐 빌 때까지
			int cur = q.poll();
			for (int i = 0; i < n; i++) {
				for (int j = 1; j < a.size(); j++) { // 그룹에 속하는 구역만 확인하기 위해
					if (i == a.get(j) && !checked[i] && arr[cur][i] == 1) { // i값이 구역에 속하고 && 방문하지 않은 곳이고 && 연결된 곳인지
						q.add(i); // 연결 되었으면 q에 넣고
						checked[i] = true; // 체크 배열 값 true
					}
				}
			}
		}

		for (int i = 0; i < a.size(); i++) { // 그룹을 돌면서 확인
			if (!checked[a.get(i)]) { // 연결 안된것이 있다면
				flag = false; // 연결 여부 false 갱신
				break; // for문 빠져나옴
			}
		}

		if (flag == false) { // 연결이 안되어 있다면
			return false; // false 반환
		} else { // 연결이 되어 있다면
			return true; // true 반환

		}
	} // end of bfs
} // end of class
