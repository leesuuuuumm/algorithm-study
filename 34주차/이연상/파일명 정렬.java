// [PRG] 파일명 정렬

import java.util.*;

class Solution {
    
    public class File {
        public String head;
        public int number;
        public String fileName;
        
        File(String head, int number, String fileName) {
            this.head = head;
            this.number = number;
            this.fileName = fileName;
        }
    }
    
    public String[] solution(String[] files) {
        String[] answer = new String[files.length];

        
        File[] fileList = new File[files.length];
        
        for(int i = 0; i < files.length; i++) {
            String head = files[i].split("[0-9]")[0];
            String number = "";
            String temp = files[i].substring(head.length());
            for(char c : temp.toCharArray()) {
                if(Character.isDigit(c) && number.length() < 5)
                    number += c;
                else
                    break;
            }
            fileList[i] = new File(head.toLowerCase(), Integer.valueOf(number), files[i]);
        }
        
        Arrays.sort(fileList, new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                if ((o1.head).equals(o2.head)) 
                    return o1.number - o2.number;
                else
                    return (o1.head).compareTo(o2.head);
            }
        });
        
        for(int i = 0; i < files.length; i++) {
            answer[i] = fileList[i].fileName;
        }
        
        return answer;
    }

}