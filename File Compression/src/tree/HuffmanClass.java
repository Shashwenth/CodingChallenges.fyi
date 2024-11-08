package tree;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class HuffmanClass {
		
	
	class Node{
		long sum;
		Character c=null;
		Node left=null;
		Node right=null;
		boolean isLeaf=true;
		
		public Node(Character c, long sum) {
			this.sum=sum;
			this.c=c;
		}
		
		public Node(long sum) {
			this.sum=sum;
		}
	}
	
	
	public Node BuildTree(TreeMap<Character, Long> t) {
		
		PriorityQueue<Node> pq=new PriorityQueue<Node>((a,b)->Long.compare(a.sum, b.sum));
		
		for(Map.Entry<Character, Long> m:t.entrySet()) {
			pq.add(new Node(m.getKey(),m.getValue()));
		}
		
		Node ans=null;
		
		while(pq.size()>1) {
			Node x=pq.poll();
			Node y=pq.poll();
			Node temp=new Node(x.sum+y.sum);
			temp.left=x;
			temp.right=y;
			temp.isLeaf=false;
			pq.add(temp);
		}
		
		//System.out.println(pq.peek().sum);
		
		//System.out.println("Printing the Node");
		//printNode(pq.peek());
		
		return pq.peek();
		
	}
	
	public void TraverseNode(Node node, HashMap<Character, String> path, String str) {
		
		if(node.isLeaf) {
			path.put(node.c, str);
			return;
		}
		
		TraverseNode(node.left, path, str+"0");
		TraverseNode(node.right, path, str+"1");
		
	}
	
	public void printNode(Node node) {
		if(node==null) {
			return;
		}
		
		if(!node.isLeaf) {
			System.out.printf("This is a parent %d\n",node.sum);
		}
		else {
			System.out.printf("This is a Leaf %c %d\n",node.c,node.sum);
		}
		
		printNode(node.left);
		printNode(node.right);
		
	}
	
	
	public void  getEncoding(Node node, HashMap<Character,Long> h){
				
		if(node==null) {
			return;
		}
		
		if(node.isLeaf) {
			h.put(node.c, node.sum);
		}
		
		getEncoding(node.left, h);
		getEncoding(node.right, h);
		
	}
	
}
