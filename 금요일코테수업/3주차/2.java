import java.util.*;

class Solution {
    
    HashSet<Integer> list = new HashSet<>();
    
    public int solution(String numbers) {
        int answer = 0;
        boolean[] isPrime = makeList();
        permutation(0, numbers, new boolean[numbers.length()], 0);
        
        for(int i : list){
            if(isPrime[i]) answer++;
        }
        
        return answer;
    }
    
    
    public void permutation(int current, String numbers, boolean[] visited, int digit){
        if(digit == numbers.length()) return;
        
        for(int i=0; i<numbers.length(); i++){
            if(!visited[i]){
                visited[i] = true;
                int newNumber = current + (int)((numbers.charAt(i)-'0') * Math.pow(10, digit));
                list.add(newNumber);
                
                permutation(newNumber, numbers, visited, digit+1);
                
                visited[i] = false;
            }
        }
    }
    
    public boolean[] makeList(){
        int limit = 10_000_000;
        boolean[] list = new boolean [limit];
        Arrays.fill(list,true);
        list[0] = false; list [1] = false;
        for(int i=2; i*i< limit; i++){
            for(int j=i*i; j<limit; j+=i){
                list[j] = false;
            }
        }
        
        return list;
    }
    
}