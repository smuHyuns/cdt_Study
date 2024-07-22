class Solution {
    boolean[] visited;
    public int solution(int n, int[][] computers) {
        int answer = 0;
        visited = new boolean[n];
        for(int i=0; i<n; i++){
            if(!visited[i]){
                answer++;
                dfs(i,computers);
            }
        }
        return answer;
    }

    public void dfs(int start, int[][] computers){
        visited[start] = true;

        for(int i=0; i<computers.length; i++){
            if(!visited[i] && computers[start][start] == computers[start][i])
                dfs(i, computers);
        }
    }
}