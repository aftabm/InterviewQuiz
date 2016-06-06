import java.util.LinkedList;
import java.util.Queue;

public class Graph 
{

	Node[][] nodes = null;
	static Node s;
	final static String X = "X";
	final static String S = "S";
	final static String E = "E";
	
	public static void main(String[] args) 
	{
		Graph graph = new Graph();				
		graph.init (4,4);
		
		System.out.print(graph.findPathBST(s, new Node("E")));
	}
	
	
	//breath first search
	private LinkedList<Node> findPathBST(Node start, Node end) 
	{		
		Queue<Node> wQueue = new LinkedList<Node>();//worker queue		
		LinkedList<Node> path=new LinkedList<Node>();
		
		wQueue.add(start);
		
		Node cNode=null; // current node
		
		while(!wQueue.isEmpty())
		{
			cNode = wQueue.poll();
			path.offer(cNode);
			
			cNode.hasVisted = true;	
									
			int beforeSize = wQueue.size();
			
			for (Node nNode:cNode.adjacent)
			{
				if(cNode.name.equals(end.name))
				{
					System.out.println("Path found");
					return path;
				}

				if (!nNode.name.equals(X) && !nNode.hasVisted && !wQueue.contains(nNode))
				{
					wQueue.offer(nNode);
				}								
			}
			
			//dead end
			if (beforeSize == wQueue.size())
			{
				path.removeLast();
			}
		}
		System.err.println("Path not found");
		return null;
	}

	public void init(int n, int m)
	{
		Node[][] nodes = new Node[n][m];
				
		for (int i=0; i < n; i++)
		{			
			for (int j=0; j < m; j++)
			{
				Node node = new Node(i,j);				
				nodes[i][j] = node;
				
				if(j!=0)
				{
					nodes[i][j-1].adjacent.add(node);
					node.adjacent.add(nodes[i][j-1]);					
				}
				if (i!=0)
				{
					nodes[i-1][j].adjacent.add(node);
					node.adjacent.add(nodes[i-1][j]);
				}
			}			
		}
		
		
		nodes[2][1].name=X;
		nodes[2][2].name=X;
		nodes[2][3].name=X;
		nodes[1][3].name=S;
		s = nodes[1][3];
		nodes[3][1].name=E;
		
		
	}

}
