import java.util.*;


//움직일수있는 경우의 수 (x,y)
// (1,1) (1,0) (1,-1) (0,1) (0,-1) (-1,1) (-1,0) (-1,-1)
class Solution {
    int[] xrr = {1,1,1,0,0,-1,-1,-1};
    int[] yrr = {1,0,-1,1,-1,1,0,-1};
    int answer = 0;

    public int solution(int n) {
        dfs(n, new boolean[n][n], 0);
        return answer;
    }

    public void dfs(int n,boolean[][] board, int row){
        if(row == n){
            answer++;
            return;
        }

        for(int col=0; col<n; col++){
            if(!board[row][col]){
                boolean[][] tempBoard = copy(board);
                move(col,row,tempBoard);
                dfs(n,tempBoard,row+1);
            }
        }
    }

    //boolean true이면 놓을수 없는것 false이면 놓을 수 있는것
    public void move(int x, int y, boolean[][]board){
        int n = board.length;
        board[x][y] = true;

        for(int i=0; i<8; i++){
            int dx = x;
            int dy = y;
            while(true){
                dx += xrr[i];
                dy += yrr[i];
                if(dx >= 0 && dx <n && dy >= 0 && dy <n){
                    board[dy][dx] = true;
                }
                else break;
            }
        }
    }


    public boolean[][] copy(boolean[][] board){
        int N = board.length;
        boolean[][] newBoard = new boolean[N][N];
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                newBoard[i][j] = board[i][j];
            }
        }
        return newBoard;
    }

}