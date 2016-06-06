import java.util.ArrayList;
import java.util.List;

public class Node 
{
	
	final  String location;
	String name;
	List<Node> adjacent = new ArrayList<Node>();
	public boolean hasVisted=false;
	
	public Node(int row, int col)
	{
	
		this.location = Integer.toString(row)+Integer.toString(col);
		this.name = this.location;
	}

	public Node(String name) 
	{
		this.name = name;
		location="";

	}

	@Override
	public String toString() {
		return "["+location + "," + name + ","+hasVisted+"]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Node other = (Node) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}
