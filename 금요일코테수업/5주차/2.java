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
                //시작점
                if(maps[i].charAt(j) == 'S'){
                    startX =  j;
                    startY =  i;
                }
                //래버 위치
                else if(maps[i].charAt(j) == 'L'){
                    leverX =  j;
                    leverY =  i;
                }
                //종료지점
                else if(maps[i].charAt(j) == 'E'){
                    targetX =  j;
                    targetY =  i;
                }
            }
        }

        //시작지점 -> 래버까지 찾기 위한 탐색
        bfs(maps, visited, route, startX, startY, 0); // 이당시 초기값은 0
        if(!visited[leverY][leverX]) return -1; // 래버를 방문하지 못했다면 = 래버까지 갈 수 없음 = -1을 리턴해준다
        visited = new boolean[N][M]; // 새로운 탐색을 위한 방문 초기화
        //래버 -> 종료지점 으로 가기 위한 탐색
        bfs(maps, visited, route, leverX, leverY, route[leverY][leverX]); // 초기값은 래버까지 도달하는데에 최단거리
        if(!visited[targetY][targetX]) return -1; // 타겟을 방문하지 못했다면 = 탈출 불가 = -1을 리턴한다.

        return route[targetY][targetX]; // 타겟을 방문했다면 거리를 리턴하여준다.
    }

    public void bfs(String[] maps, boolean[][] visited, int[][] route, int x, int y, int init){
        //상화좌우 탐색을 위한 배열 선언
        int[] xrr = {1,-1,0,0};
        int[] yrr = {0,0,1,-1};

        //가로세로길이정의
        int M = maps.length; //세로길이
        int N = maps[0].length(); //가로길이
        Deque<int[]> q = new ArrayDeque<>(); //최단거리 탐색을 위한 bfs를 사용할 것이므로 stack이 아닌 queue를 사용해야함

        q.offer(new int[]{x, y}); //초기값을 넣어주고
        visited[y][x] = true; // 방문표시
        route[y][x] = init; // 현재 위치를 전달받은 값으로 초기화시켜준다

        while(!q.isEmpty()){
            int[] top = q.poll(); // 큐 제일 맨 앞의 값 poll
            for(int i=0; i<4; i++){  // 상하좌우 탐색
                int dx = top[0] + xrr[i];
                int dy = top[1] + yrr[i];
                if( dx >= 0 && dx < N && dy < M && dy >= 0 && !visited[dy][dx] // 이동한 거리가 배열범위 안에 있고 방문하지 않았으며, 막혀있지 않다면
                        && maps[dy].charAt(dx) != 'X'){
                    visited[dy][dx] = true; //방문 표시
                    route[dy][dx] = route[top[1]][top[0]] + 1; //거리값 갱신, 한칸이동이므로 +1
                    q.offer(new int[]{dx,dy}); //큐에 넣어줌
                }
            }
        }
    }
}