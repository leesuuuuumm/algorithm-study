import java.util.*;
import java.lang.*;

class User {
    // 구매하기 위한 최소 할인율
    int buyRate;
    // 이모티콘 구매를 취소하게 되는 최소 가격
    int cancelPrice;

    public User(int buyRate, int cancelPrice) {
        this.buyRate = buyRate;
        this.cancelPrice = cancelPrice;
    }

    // 구매한 가격
    public int pay(Emoticon[] emoticons) {
        int amount = 0;

        for (Emoticon e : emoticons) {
            // 할인율이 사용자가 원하는 비율이면 amount에 할인된 가격을 더한다.
            if (e.rate >= buyRate) amount += e.saleAmount();
        }

        return amount;
    }
    
    public boolean isOverCancelPrice (Emoticon[] emoticons) {
        return cancelPrice <= pay(emoticons);
    }
}

class Emoticon {
    int price;
    int rate;

    public Emoticon(int price) {
        this.price = price;
        this.rate = 0;
    }

    // 할인된 가격
    public int saleAmount() {
        return price * (100 - rate) / 100;
    }

    public void changeRate(int rate) {
        this.rate = rate;
    }
}

class Result implements Comparable<Result> {
    int subscriber;
    int totalAmount;

    public Result() {
        this.subscriber = 0;
        this.totalAmount = 0;
    }

    public int[] getResult() {
        return new int[]{subscriber, totalAmount};
    }

    @Override
    public int compareTo(Result o) {
        return subscriber == o.subscriber ? o.totalAmount - totalAmount : o.subscriber - subscriber;
    }
}

class Solution {
    
   
   
    public int[] solution(int[][] users, int[] emoticons) {
        User[] u = new User[users.length];
        Emoticon[] e = new Emoticon[emoticons.length];
        Queue<Result> queue = new PriorityQueue<Result>();
        
        for (int i = 0; i < users.length; i++) {
            u[i] = new User(users[i][0], users[i][1]);
        }
        
        for (int i = 0; i < emoticons.length; i++) {
            e[i] = new Emoticon(emoticons[i]);
        }
        
        permutation(0, new int[emoticons.length], e, u, queue);
        
        return queue.poll().getResult();
    }
    
    private void permutation(int cnt, int[] nums, Emoticon[] e, User[] u, Queue q) {
        if (cnt == e.length) {
            for (int i = 0; i < cnt; i++) {
                e[i].changeRate(nums[i] * 10);
            }
            
            Result r = new Result();
            
            for (int i = 0; i < u.length; i++) {  
                // 지불한 가격이 최소 금액을 넘어서면 이모티콘 플러스 구독자를 늘려줌
                if (u[i].isOverCancelPrice(e)) r.subscriber++;
                else r.totalAmount += u[i].pay(e);
            }
            
            q.offer(r);
            
            return;
        }
        
        for (int i = 1; i <= 4; i++) {
            nums[cnt] = i;
            permutation(cnt + 1, nums, e, u, q);
        }
    }
}