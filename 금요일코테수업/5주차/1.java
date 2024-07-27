import java.util.*;

class Solution {

    public int solution(int[][] maps) {
        int N = maps.length;
        int M = maps[0].length;
        boolean[][] visited =  new boolean[N][M];
        int[][] distance = new int[N][M];
        bfs(maps,visited, distance, 0, 0);

        if(visited[N-1][M-1]) return distance[N-1][M-1];
        return -1;
    }

    public void bfs(int[][] maps, boolean[][] visited, int[][] distance, int x, int y){
        int[] xrr = {1,-1,0,0};
        int[] yrr = {0,0,1,-1};
        Deque<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{x, y});
        visited[y][y] = true;
        distance[y][x] = 1;

        while(!q.isEmpty()){
            int[] top = q.poll();
            for(int i=0; i<4; i++){
                int dx = top[0] + xrr[i];
                int dy = top[1] + yrr[i];
                if( dx >= 0 && dx < maps[0].length && dy < maps.length && dy >= 0 && !visited[dy][dx]
                        && maps[dy][dx] != 0){
                    visited[dy][dx] = true;
                    distance[dy][dx] = distance[top[1]][top[0]] + 1;
                    q.offer(new int[]{dx,dy});
                }
            }
        }


    }
}