
public class Node 
{
	public Node(String data) 
	{
		this.data = data;
	}
	public String data;
	public Node left;
	public Node right;
	@Override
	public String toString() {
		return "Node[" + data + "]";
	}


}
