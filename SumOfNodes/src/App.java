import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class App {

	public static void main(String[] args) 
	{
		Node root;
		Node node = new Node("5");
		node.left = new Node("6");
		node.right = new Node ("7");
		root = node;
		
		node = root.left;
		node.left = new Node("4");
		node.right = new Node ("5");
		
		node = root.right;
		node.left = new Node("8");
		node.right = new Node ("9");
		
		
		List<String> numbers = new ArrayList<>();
		
		inorderpath(root,new Stack<Node>(), numbers);
		
		long sum=0;
		
		for(String number: numbers)
		{
			sum = sum + Long.parseLong(number);
		}
		
		System.out.println(sum);
	}
	
	
	
	static void inorderpath(Node root, Stack<Node> pathStack, List<String> numbers)
	{
		if(root==null)
			return;		
		
		pathStack.push(root);
		
		if(root.left==null && root.right ==null)
		{
			Node[] pathNodes = new Node[pathStack.size()];
			pathStack.toArray(pathNodes);
			StringBuffer sb = new StringBuffer();
			
			for(Node node:pathNodes)
				sb.append(node.data);
			
			numbers.add(sb.toString());
			
		}
			
		inorderpath(root.left, pathStack, numbers);
		inorderpath(root.right, pathStack, numbers);
		
		pathStack.pop();
		
	}

}


/*
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * */
