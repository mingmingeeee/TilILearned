package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_1991 {
	
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(in.readLine());
		
		Node root = null;
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			char data_m = st.nextToken().charAt(0);
			char data_left = st.nextToken().charAt(0);
			char data_right = st.nextToken().charAt(0);
			if(root == null) {
				root = new Node(data_m);
				
				if(data_left != '.') {
					root.left = new Node(data_left);
				}
				if(data_right != '.') {
					root.right = new Node(data_right);
				}
			} else {
				searchNode(root, data_m, data_left, data_right);
			}
		}
		
		pre_order(root);
		sb.append("\n");
		in_order(root);
		sb.append("\n");
		post_order(root);
		sb.append("\n");
		
		System.out.println(sb);
		
	}
	
	static void searchNode(Node root, char data_m, char data_left, char data_right) {
		if(root == null) {
			return;
		} else if(root.data == data_m) {
			if(data_left != '.') {
				root.left = new Node(data_left);
			}
			if(data_right != '.') {
				root.right = new Node(data_right);
			}
		} else {
			searchNode(root.left, data_m, data_left, data_right);
			searchNode(root.right, data_m, data_left, data_right);
		}
	}
	
	static void pre_order(Node root) {
		sb.append(root.data);
		if(root.left!=null)
			pre_order(root.left);
		if(root.right!=null)
			pre_order(root.right);
	}
	
	
	static void in_order(Node root) {
		if(root.left!=null)
			in_order(root.left);
		sb.append(root.data);
		if(root.right!=null)
			in_order(root.right);
	}

	static void post_order(Node root) {
		if(root.left!=null)
			post_order(root.left);
		if(root.right!=null)
			post_order(root.right);
		sb.append(root.data);
	}

}

class Node{
	char data;
	Node left;
	Node right;
	
	public Node(char data) {
		this.data = data;
	}
}
