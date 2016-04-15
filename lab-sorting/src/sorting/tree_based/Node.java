package sorting.tree_based;

public class Node
{
	public Node left;
	public Node right;
	public int data;
	
	public Node(int data)
	{
		this.data = data;
	}
	

	public void insert(int data)
    {
		if (data < this.data)
		{
			if (this.left==null)
				this.left = new Node(data);
			else
				this.left.insert(data);
		}
		else
		{
			if (this.right==null)
				this.right=new Node(data);
			else
				this.right.insert(data);
		}
    }
	
	   public void printNode()      // display ourself
	      {
	      System.out.print('{');
	      System.out.print(data);
	      System.out.print(", ");
	      System.out.print("} ");
	      }	
}
