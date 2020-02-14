import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class BAEKJOON_17471 {
    static int n, k, min, sum, partsum;
    static int [] peo;
    static int [][] link;
    static boolean [] check;
    static int [] temp;
    static boolean [] visited;
 
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        peo = new int [n+1];
        link = new int [n+1][n+1];
        
        String s = br.readLine();
        StringTokenizer st = new StringTokenizer(s);
 
        for(int i = 1; i<n+1; i++) {  
            peo[i] = Integer.parseInt(st.nextToken());
        }
        
        for(int x = 1; x<n+1; x++) { 
            s = br.readLine();
            st = new StringTokenizer(s);
            int each = Integer.parseInt(st.nextToken());
            for(int y = 0; y<each; y++) {
                int spot = Integer.parseInt(st.nextToken());
                link[x][spot] = 1;  
            }
        }
                
        min = Integer.MAX_VALUE;
        
        for(int i = 0; i<(n/2)+1; i++) { 
            k = i;
            check = new boolean[n+1];
            divide(1,0);
        }
        
        if(min == Integer.MAX_VALUE) {  
            System.out.println(-1);
        }
        else {
            System.out.println(min);
        }
    }
    
    public static void divide(int start, int cnt) {
        if(cnt == k) {
            sum = Integer.MAX_VALUE;
            temp = new int[n+1];
            for(int i = 1; i<n+1; i++) {
                if(check[i] == true) {
                    temp[i] = 1;
                }
                else {
                    temp[i] = 0;
                }
            }
            confirm();
            min = Math.min(min, sum);
            return;
        }
        for(int i = start; i<n+1; i++) {
            check[i]=true;
            divide(i+1,cnt+1);
            check[i]=false;
        }
    }
    
    public static void confirm() {
        visited = new boolean[n+1];
        int people1 = 0;
        int people2 = 0;
        for(int i = 1; i<n+1; i++) {
            if(temp[i] == 1 && !visited[i]) {  
                partsum = 0;
                check(i);
                people1 = partsum;
                break;
            }
        }
        
        for(int i = 1; i<n+1; i++) {
            if(temp[i] == 0 && !visited[i]) {  
                partsum = 0;
                check(i);
                people2 = partsum;
                break;
            }
        }
        
        for(int i = 1; i<n+1; i++) {
            if(!visited[i]) {
                return;
            }
        }
        sum = Math.abs(people2-people1);
    }
    
    public static void check(int x) {
        visited[x] = true;
        partsum = partsum + peo[x];
        for(int i = 1; i<n+1; i++) {
            if(!visited[i] && temp[i] == temp[x] && link[i][x] == 1) {
                check(i);
            }
        }
    }
 
}