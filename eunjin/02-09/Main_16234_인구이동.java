import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main{
	static int N, L, R;
	static int[][] dxy = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int ans = 0;
		boolean[][] visited;
		while (true) {
			visited = new boolean[N][N];
			isOk = false;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!visited[i][j]) {
						bfs(i, j, visited, map);
					}
				}
			}
			if (!isOk)
				break;
			ans++;

		}
		System.out.println(ans);

	}

	static boolean isOk;

	private static void bfs(int x, int y, boolean[][] visited, int[][] map) {
		visited[x][y] = true;
		Queue<int[]> que = new LinkedList<>();
		List<int[]> union = new LinkedList<>();
		que.add(new int[] { x, y });
		union.add(new int[] { x, y });
		boolean flag = false;
		while (!que.isEmpty()) {
			int[] cur = que.poll();
			for (int k = 0; k < 4; k++) {
				int nx = cur[0] + dxy[k][0];
				int ny = cur[1] + dxy[k][1];

				if (nx < 0 || ny < 0 || nx >= N || ny >= N || visited[nx][ny])
					continue;
				int diff = Math.abs(map[nx][ny] - map[cur[0]][cur[1]]);
				if (diff >= L && diff <= R) {
					visited[nx][ny] = true;
					que.add(new int[] { nx, ny });
					union.add(new int[] { nx, ny });
					flag = true;
					isOk = true;
				}
			}
		}
		if (flag) {
			int size = union.size();
			if (size == 1)
				return;
			int sum = 0;
			for (int i = 0; i < size; i++) {
				int[] cur = union.get(i);
				sum += map[cur[0]][cur[1]];
			}
			int result = sum / size;
			for (int i = 0; i < size; i++) {
				int[] cur = union.get(i);
				map[cur[0]][cur[1]] = result;

			}
		}

	}

}
