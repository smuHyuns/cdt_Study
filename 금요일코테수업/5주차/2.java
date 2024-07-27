import java.util.*;

class Solution {
    public int solution(String[] maps) {
        int answer = 0;
        int N = maps.length;
        int M = maps[0].length();
        boolean[][] visited = new boolean[N][M];
        int[][] route = new int[N][M]; // 최단거리 기록용 route
        int startX=0, startY=0, targetX=0, targetY=0, leverX=0, leverY =0;

        //시작점, 래버, 종료지점 찾기
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(maps[i].charAt(j) == 'S'){
                    startX =  j;
                    startY =  i;
                }
                else if(maps[i].charAt(j) == 'L'){
                    leverX =  j;
                    leverY =  i;
                }
                else if(maps[i].charAt(j) == 'E'){
                    targetX =  j;
                    targetY =  i;
                }
            }
        }

        //루트까지 찾기 위한 탐색
        bfs(maps, visited, route, startX, startY, 0);
        if(!visited[leverY][leverX]) return -1;
        visited = new boolean[N][M];
        bfs(maps, visited, route, leverX, leverY, route[leverY][leverX]);
        if(!visited[targetY][targetX]) return -1;

        return route[targetY][targetX];
    }

    public void bfs(String[] maps, boolean[][] visited, int[][] route, int x, int y, int init){
        int[] xrr = {1,-1,0,0};
        int[] yrr = {0,0,1,-1};

        int M = maps.length;
        int N = maps[0].length();
        Deque<int[]> q = new ArrayDeque<>();

        q.offer(new int[]{x, y});
        visited[y][x] = true;
        route[y][x] = init;

        while(!q.isEmpty()){
            int[] top = q.poll();
            for(int i=0; i<4; i++){
                int dx = top[0] + xrr[i];
                int dy = top[1] + yrr[i];
                if( dx >= 0 && dx < N && dy < M && dy >= 0 && !visited[dy][dx]
                        && maps[dy].charAt(dx) != 'X'){
                    visited[dy][dx] = true;
                    route[dy][dx] = route[top[1]][top[0]] + 1;
                    q.offer(new int[]{dx,dy});
                }
            }
        }
    }
}