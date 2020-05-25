import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class hw_algo0522_서울_11반_임진섭 {

	private static int[] arr;
	private static int cnt;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= t; tc++) {

			int N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			arr = new int[N];

			for (int i = 0; i < arr.length; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}

			cnt = 0;
			perm(0, 0, 0);

			System.out.println("#" + tc + " " + cnt);

		}

	} // end of main

	public static void perm(int index, int l, int r) {
		if (index == arr.length) {
			cnt++;
		} else {
			for (int i = index; i < arr.length; i++) {
				int temp = arr[index];
				arr[index] = arr[i];
				arr[i] = temp;

				perm(index + 1, l + arr[index], r);
				if (l >= r + arr[index]) {
					perm(index + 1, l, r + arr[index]);
				}
				temp = arr[index];
				arr[index] = arr[i];
				arr[i] = temp;
			}
		}
	}

} // end of class