
public class Tree 
{
	

	public static void main(String[] args) 
	{
		Node root;
		
		Node node12 = new Node("12");
		Node node4 = new Node("4");
		Node node14 = new Node("14");
		
		Node node6 = new Node("6");
		Node node3 = new Node("3");
		Node node1 = new Node("1");
		
		Node node18 = new Node("18");
		
		root = node12;
		root.left = node4;
		root.right = node14;
		root.left.right = node4;
		root.left.right = node6;
		
		 int depth = depth(root);
	        
	        if (depth == 3)
	            System.out.println("OK: tree depth = 3");
	        else
	            System.err.println("Failed: incorrect tree depth");
		
	}
	
		
	public static int depth(Node node)
	{		
		if (node == null)
			return 0;
		
		int left = depth(node.left)+1;
		int right = depth(node.right)+1;
		
		if (left > right)
			return left;
		else
			return right;
	}
		
}
