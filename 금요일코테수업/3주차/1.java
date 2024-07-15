import java.util.*;

class Solution {
    public int solution(int fatigue, int[][] dungeons) {
        int answer = findSol(fatigue, dungeons, 0, new boolean[dungeons.length]);     
        return answer;
    }
    
    public static int findSol(int fatigue, int[][] dungeons, int count, boolean[] visited){
        int max = count;
            
        for(int i=0; i<dungeons.length; i++){
            if(fatigue >= dungeons[i][0] && !visited[i]){
                visited[i] = true;
                max = Math.max(findSol(fatigue - dungeons[i][1], dungeons, count+1, visited), max);
                visited[i] = false;
            }
        }
        
        return max;
    }
    
}