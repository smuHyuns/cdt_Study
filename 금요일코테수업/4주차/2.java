import java.util.*;

class Solution {
    public int solution(String begin, String target, String[] words) {
        return bfs(begin, target, words);
    }

    public int bfs(String begin, String target, String[] words){
        int ans = 0;
        Deque<String> q = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();
        q.offer(begin);

        while(!q.isEmpty()){
            ans++;
            int size = q.size();
            //최상단에서 꺼내기
            //분기라고 생각하자
            for(int i=0; i<size; i++){
                String cur = q.poll();
                for(String word : words){
                    if(!visited.contains(word) && check(cur,word)){
                        if(word.equals(target)) return ans;
                        q.offer(word);
                        visited.add(word);
                    }
                }
            }


        }

        return 0;
    }


    public boolean check(String str1, String str2){
        int cnt = 0;
        for(int i=0; i<str1.length(); i++){
            if(str1.charAt(i) != str2.charAt(i)){
                cnt++;
            }
        }
        if(cnt == 1) return true;
        return false;
    }
}