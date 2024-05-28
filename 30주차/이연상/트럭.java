// [BOJ] 트럭

import java.util.*;
 
public class Main {
    public static void main(String[] args){
        //input
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int w = sc.nextInt();
        int L = sc.nextInt();      
        
        Queue<Integer> truck = new LinkedList<>();
        Queue<Integer> bridge = new LinkedList<>();
        
        for(int i = 0; i<n; i++) {
            truck.offer(sc.nextInt());
        }     
        
        sc.close();
        
        int answer = 0;
        int weight = 0;
        
        for(int i = 0; i<w; i++) {
            bridge.offer(0); 
        }
        
 
               
        //logic
        while ( !bridge.isEmpty()){
            answer ++;
            weight -= bridge.poll(); 
            if( !truck.isEmpty()) {  
                if(truck.peek() + weight <= L) {
                    weight += truck.peek();     
                    bridge.offer(truck.poll());      
                }else {
                    bridge.offer(0);             
                }
            }
        }
        System.out.print(answer);
     
    }
   
}
