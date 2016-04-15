package sorting.tree_based;

import java.util.Stack;

public class BST
{
	private Node root=null;

	public Node find(Node root, int data)
	{
		if (root==null)
			return null;
		
		else if (data==root.data)
			return root;
		
		if (data < root.data)
			return find(root.left, data);		
		else 
			return find(root.right,data);
	}	
	
	public void insert(Node root,int data)
	{
		if (root==null)
		{
			root = new Node(data);
		}
		else if (data < root.data)
		{
			if (data < root.data)
			{
				if (root.left==null)
					root.left = new Node(data);
				else
					insert(root.left,data);
			}
			else
			{
				if (root.right==null)
					root.right=new Node(data);
				else
					insert(root.right,data);
			}
		}
	}
	
	public void delete(Node root, int data)
	{
		if (root.data==data)
		{
			if (root.left==null && root.right==null)
				root = null;
			else if (root.right==null)
				root = root.left;
			else 
			{
				Node deleted = deleteMin(root, root.right);
				root.data = deleted.data;
			}
		}
	}
	
	public Node deleteMin(Node parent, Node child)
	{
		if (child.left!=null)
			deleteMin(child, child.left);

		if (parent.left!=child)
			throw new IllegalStateException("parent.left != child");
		
		parent.left=null;
		
		return child;
	}
	
	
	//chapter 8 - tree.java
	// chap13 has dfs/bfs - dfs.java
	// tree234.java for balancing
	
	// -------------------------------------------------------------
	   public void traverse(int traverseType)
	      {
	      switch(traverseType)
	         {
	         case 1: System.out.print("\nPreorder traversal: ");
	                 preOrder(root);
	                 break;
	         case 2: System.out.print("\nInorder traversal:  ");
	                 inOrder(root);
	                 break;
	         case 3: System.out.print("\nPostorder traversal: ");
	                 postOrder(root);
	                 break;
	         }
	      System.out.println();
	      }
	// -------------------------------------------------------------
	   private void preOrder(Node localRoot)
	      {
	      if(localRoot != null)
	         {
	         System.out.print(localRoot.data + " ");
	         preOrder(localRoot.left);
	         preOrder(localRoot.right);
	         }
	      }
	// -------------------------------------------------------------
	   private void inOrder(Node localRoot)
	      {
	      if(localRoot != null)
	         {
	         inOrder(localRoot.left);
	         System.out.print(localRoot.data + " ");
	         inOrder(localRoot.right);
	         }
	      }
	// -------------------------------------------------------------
	   private void postOrder(Node localRoot)
	      {
	      if(localRoot != null)
	         {
	         postOrder(localRoot.left);
	         postOrder(localRoot.right);
	         System.out.print(localRoot.data + " ");
	         }
	      }
	// -------------------------------------------------------------
	   public void displayTree()
	      {
	      Stack globalStack = new Stack();
	      globalStack.push(root);
	      int nBlanks = 32;
	      boolean isRowEmpty = false;
	      System.out.println(
	      "......................................................");
	      while(isRowEmpty==false)
	         {
	         Stack localStack = new Stack();
	         isRowEmpty = true;

	         for(int j=0; j<nBlanks; j++)
	            System.out.print(' ');

	         while(globalStack.isEmpty()==false)
	            {
	            Node temp = (Node)globalStack.pop();
	            if(temp != null)
	               {
	               System.out.print(temp.data);
	               localStack.push(temp.left);
	               localStack.push(temp.right);

	               if(temp.left != null ||
	                                   temp.right != null)
	                  isRowEmpty = false;
	               }
	            else
	               {
	               System.out.print("--");
	               localStack.push(null);
	               localStack.push(null);
	               }
	            for(int j=0; j<nBlanks*2-2; j++)
	               System.out.print(' ');
	            }  // end while globalStack not empty
	         System.out.println();
	         nBlanks /= 2;
	         while(localStack.isEmpty()==false)
	            globalStack.push( localStack.pop() );
	         }  // end while isRowEmpty is false
	      System.out.println(
	      "......................................................");
	      }  // end displayTree()	

}
