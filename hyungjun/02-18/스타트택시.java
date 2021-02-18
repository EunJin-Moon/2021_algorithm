import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 스타트택시 {
    static int N,M,G,sy,sx,del;
    static int [][]map, dep;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());
        del = 0;
        map = new int[N][N];
        dep = new int[M][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
         sy = Integer.parseInt(st.nextToken())-1;
         sx = Integer.parseInt(st.nextToken())-1;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
             int a = Integer.parseInt(st.nextToken())-1;
             int b = Integer.parseInt(st.nextToken())-1;
            int dy = Integer.parseInt(st.nextToken())-1;
            int dx = Integer.parseInt(st.nextToken())-1;
            map[a][b] = i+2;
            dep[i][0] = dy;
            dep[i][1] = dx;
        }
        while (del != M){
            bfs(sy,sx);
            if(G < 0){
                System.out.println(-1);
                return;
            }
        }
        System.out.println(G);

    }
    private static void bfs(int a, int b){
        int[] wy = {-1,0,0,1};
        int[] wx = {0,-1,1,0};

        boolean [][]visit = new boolean[N][N];
        Queue<Integer> q = new LinkedList<>();
        q.add(a);
        q.add(b);
        visit[a][b] = true;
        ArrayList<Integer> []ar = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            ar[i] = new ArrayList<Integer>();
        }

        if(map[a][b] !=0 ){
            godep(a,b);
            map[a][b] = 0;

            return;
        }

        while (!q.isEmpty()){
            int qsize = q.size();
            G--;
            if(G < 0){
                return;
            }
            while (qsize > 0){
                qsize -=2;
                int y = q.poll();
                int x = q.poll();
                for (int i = 0; i < 4; i++) {
                    int ny = y+wy[i];
                    int nx = x+wx[i];
                    if(ny>=0&& nx >=0 && ny < N &&nx < N && map[ny][nx] != 1 &&!visit[ny][nx]){
                        if(map[ny][nx] != 0){
                            ar[ny].add(nx);


                        }else{
                            q.add(ny);
                            q.add(nx);
                            visit[ny][nx] =true;
                        }
                    }
                }

            }

            for (int i = 0; i < N; i++) {
                if(ar[i].size() > 0){
                    int min = Integer.MAX_VALUE;
                    for (int j = 0; j < ar[i].size(); j++) {
                        min = Math.min(ar[i].get(j), min);
                    }
                    godep(i,min);
                    map[i][min] = 0;

                    return;
                }
            }

        }
        G = -1;
    }

    private static void godep(int a, int b){
        int[] wy = {-1,0,0,1};
        int[] wx = {0,-1,1,0};
        boolean [][]visit = new boolean[N][N];
        Queue<Integer> q = new LinkedList<>();
        int idx = map[a][b] - 2;
        int dy = dep[idx][0];
        int dx = dep[idx][1];
        q.add(a);
        q.add(b);

        visit[a][b] = true;
        int cnt = 0;

        while (!q.isEmpty()){
            int qsize = q.size();
            cnt++;
            G--;
            while (qsize >0){
                qsize -=2;
                if(G <0){
                    return;
                }
                int y = q.poll();
                int x = q.poll();
                for (int i = 0; i < 4; i++) {
                    int ny = y+wy[i];
                    int nx = x+wx[i];
                    if(ny ==dy && nx ==dx){
                        G += (cnt*2);
                        sy = ny;
                        sx = nx;
                        del++;
                        return;
                    }
                    if(ny>=0&& nx >=0 && ny < N &&nx < N && map[ny][nx] != 1 &&!visit[ny][nx]){
                        visit[ny][nx] = true;
                        q.add(ny);
                        q.add(nx);
                    }
                }

            }
        }
    }

}
