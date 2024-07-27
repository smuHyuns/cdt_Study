class Solution {
    public int[] solution(String[][] places) {
        int[] answer = new int[5];
        for(int i=0; i<5; i++){
            if(dfs(places[i])) answer[i] = 1;
            else answer[i] = 0;
        }
        return answer;
    }

    //전체탐색
    public boolean dfs(String[] place){
        // 맨해튼거리라는건 x1,y1 / x2,y2가 있을때 x1-x2 절대값 + y1-y2 절대값 이 2이하면 안됨
        // 대신 근처에 파티션 있으면 괜찮(X)
        for(int i=0; i<5; i++){
            for(int j=0; j<5; j++){
                if(place[i].charAt(j) == 'P'){ //사람만났을때 조건 충족하는지 확인함
                    if(!isValid(place,j, i)) return false;
                }
            }
        }
        return true;
    }

    public boolean isValid(String[] place, int x, int y){
        //8가지방향 - 고려해야 할 상하좌우대각선
        int[] xrr = {1,1,1,0,0,-1,-1,-1};
        int[] yrr = {-1,0,1,-1,1,-1,0,1};

        for(int i=0; i<8; i++){ // 방향만큼 탐색
            int dx = x;
            int dy = y;
            for(int j=0; j<2; j++){ //x = +2 , y = +2 범위내로 해결하면됨 ( 그 이상의 거리가 되면 뭐가 나오든 괜찮으므로 )
                dx += xrr[i];
                dy += yrr[i];
                if(dx >= 0 && dx < 5 && dy >= 0 && dy < 5){ //범위를 확인해본다.
                    if(place[dy].charAt(dx)== 'X') break; //파티션을 만나면 거리가 안될 가능성이 없음
                    else if(place[dy].charAt(dx) == 'P' && Math.abs(dx-x) + Math.abs(dy-y) <= 2){ // 사람을 만나고, 거리가 충족이 안되었을때
                        if(place[y].charAt(dx) != 'X' || place[dy].charAt(x) != 'X') return false; // 사이에 파티션으로 막혀있다면 ok
                    }
                }
            }
        }
        return true;
    }

}