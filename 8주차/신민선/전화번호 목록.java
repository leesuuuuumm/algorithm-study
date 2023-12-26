/*
백준 5052
날짜 2023.12.26
*/
import java.io.*;
import java.util.*;

class Node_Beakjoon_5052{
	Map<Character,Node_Beakjoon_5052> childNodes=new HashMap<>();
	boolean end;
	Map<Character, Node_Beakjoon_5052> getChildNodes(){
		return this.childNodes;
	}
	
	boolean isLastChar() {
		return this.end;
	}
	void setIsLastChar(boolean isLastChar) {
		this.end=isLastChar;
		
	}
}
class Trie_Beakjoon_5052{
	Node_Beakjoon_5052 root;
	Trie_Beakjoon_5052(){
		root=new Node_Beakjoon_5052();
	}
	
	boolean insert(String word) {
		Node_Beakjoon_5052 thisNode=this.root;
		for(int i=0;i<word.length();i++) {//현재 노드의 자식 중에 해댱 문자가 없으면 자식을 생성해라
			char c=word.charAt(i);
			if(thisNode.getChildNodes().get(c)==null) {
				thisNode.getChildNodes().put(c,new Node_Beakjoon_5052());
			}
			thisNode=thisNode.getChildNodes().get(c);
			if(thisNode.end)
				return false;
		}
		if(thisNode.getChildNodes().size()!=0)
			return false;
		thisNode.setIsLastChar(true);//삽입이 끝나면 현재 노드가 마지막 노드라는 것을 체크
		return true;
	}
	
	boolean contains(String word) {
		Node_Beakjoon_5052 thisNode=this.root;
		for(int i=0;i<word.length();i++) {
			char c=word.charAt(i);//현재 문자
			Node_Beakjoon_5052 node=thisNode.getChildNodes().get(c);//현재 노드의 자식 중 c를 가져옴
			if(node==null) {//null이면 자식 중 c 없음
				return false;
			}
			thisNode=node;//자식 중 c 있어서 다음으로 넘어감
		}
		return thisNode.isLastChar();//문자가 다 끝났는데 trie에서 이게 마지막인지 확인.
	}

	
}
public class Beakjoon_5052 {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int t=sc.nextInt();
		
		for(int i=0;i<t;i++) {
			int n=sc.nextInt();
			Trie_Beakjoon_5052 trie=new Trie_Beakjoon_5052();
			boolean check=true;
			for(int j=0;j<n;j++) {
				String s=sc.next();
				if(!trie.insert(s)) {
					check=false;
				}
			}
			if(check)
				System.out.println("YES");
			else
				System.out.println("NO");
		}
		
	}
}
