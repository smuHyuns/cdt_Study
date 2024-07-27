class Solution {
    public int[] solution(String[][] places) {
        int[] answer = new int[5];
        for(int i=0; i<5; i++){
            if(dfs(places[i])) answer[i] = 1;
            else answer[i] = 0;
        }
        return answer;
    }

    public boolean dfs(String[] place){
        // 맨해튼거리라는건 x1,y1 / x2,y2가 있을때 x1-x2 절대값 + y1-y2 절대값 이 2이하면 안됨
        // 대신 근처에 파티션 있으면 괜찮(X)
        for(int i=0; i<5; i++){
            for(int j=0; j<5; j++){
                if(place[i].charAt(j) == 'P'){
                    if(!isValid(place,j, i)) return false;
                }
            }
        }
        return true;
    }

    public boolean isValid(String[] place, int x, int y){
        //8가지방향
        int[] xrr = {1,1,1,0,0,-1,-1,-1};
        int[] yrr = {-1,0,1,-1,1,-1,0,1};

        for(int i=0; i<8; i++){
            int dx = x;
            int dy = y;
            for(int j=0; j<2; j++){
                dx += xrr[i];
                dy += yrr[i];
                if(dx >= 0 && dx < 5 && dy >= 0 && dy < 5){
                    if(place[dy].charAt(dx)== 'X') break;
                    else if(place[dy].charAt(dx) == 'P' && Math.abs(dx-x) + Math.abs(dy-y) <= 2){
                        if(place[y].charAt(dx) != 'X' || place[dy].charAt(x) != 'X') return false;
                    }
                }
            }
        }
        return true;
    }

}