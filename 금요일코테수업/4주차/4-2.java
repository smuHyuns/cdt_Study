import java.util.*;

class Solution {
    int answer = 0;

    public int solution(int n) {
        dfs(n, new ArrayList<>(), 0);
        return answer;
    }

    public void dfs(int n, List<int[]> board, int row) {
        if (row == n) {
            answer++;
            return;
        }

        for (int col = 0; col < n; col++) {
            if (isSafe(board, row, col)) {
                List<int[]> newBoard = new ArrayList<>(board);
                newBoard.add(new int[]{row, col});
                dfs(n, newBoard, row + 1);
            }
        }
    }

    public boolean isSafe(List<int[]> board, int row, int col) {
        for (int[] queen : board) {
            int qRow = queen[0];
            int qCol = queen[1];
            if (qCol == col || Math.abs(qRow - row) == Math.abs(qCol - col)) {
                return false;
            }
        }
        return true;
    }
}
