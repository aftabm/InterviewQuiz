package mule.test.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

import mule.test.model.Edge;
import mule.test.model.Node;

/********************************************************
 * @author amahmood
 *
 *******************************************************/
public class GraphEngine 
{

	private List<Node> allNodes = null;

	public static final Node terminatorNode = new Node('0');
	public static final Edge terminatorEdge = new Edge(terminatorNode,terminatorNode,0);

	/********************************************************
	 * 
	 *******************************************************/
	public GraphEngine() 
	{
		allNodes = new ArrayList<Node>();
	}

	public void addEdge(char from, char to, int distance) 
	{
		Node fromNode = createNode(from);
		Node toNode = createNode(to);
		
		addEdge( fromNode,  toNode,  distance) ;
	}
	
	/********************************************************
	 * @param fromNode
	 * @param toNode
	 * @param distance
	 *******************************************************/
	private void addEdge(Node fromNode, Node toNode, int distance) 
	{
		if (fromNode.getEdge(toNode)==null)
		{
			Edge edge = new Edge(fromNode, toNode, distance);
			fromNode.addAdjacentNode(toNode);
			fromNode.addAdjacentEdge(edge);			
		}
	}

	/********************************************************
	 * @param distance
	 * @return
	 *******************************************************/
	private Edge createDistanceHolder(int distance)
	{
		return new Edge(terminatorNode, terminatorNode,(int)distance);
	}

	/********************************************************
	 * @param label
	 * @return
	****************************************************** */
	public Node createNode(char label) 
	{
		label = Character.toUpperCase(label);

		Node node = new Node(label); // TBD should use Map??

		if (allNodes.contains(node))
		{
			int index = allNodes.lastIndexOf(node);
			node = allNodes.get(index); // TBD can be optimize via node.getId()
										// look up. better if data size is know.
		} 
		else 
		{
			allNodes.add(node);
		}

		return node;
	}
	
	/**********************************************************
	 * @param from
	 * @param end
	 * @param distance
	 * @param finalPath
	 *********************************************************/
	public void findPathLessThanDistance(char from, char end, int distance, Stack<Edge> finalPath)
	{
		Node startNode = createNode(from);
		Node endNode = createNode(end);
		Stack<Edge> currentPath = new Stack<Edge>();
		findPathLessThanDistance(startNode, endNode,  distance, currentPath, finalPath);
	}
	
	/********************************************************
	 * @param fromNode
	 * @param endNode
	 * @param distance
	 * @param distanceSoFar
	 * @param currentPath
	 * @param finalPath
	 *******************************************************/
	private void findPathLessThanDistance(Node fromNode, Node endNode, int distance, Stack<Edge> currentPath, Stack<Edge> finalPath)
	{
		for (Edge edge: fromNode.getAdjacentEdges())
		{
			if (edge.getTo().equals(endNode) ) 
			{
				pushAndSum(currentPath, edge);
				
				if (getDistance(currentPath) < distance)
				{
					finalPath.addAll(currentPath);
				
					findPathLessThanDistance(edge.getTo(), endNode,distance, currentPath, finalPath);
				}
				
				popAndSum(currentPath);
				return;
			} 
			else
			{
				pushAndSum(currentPath, edge);
				
				findPathLessThanDistance(edge.getTo(), endNode,distance, currentPath, finalPath);
					
				popAndSum(currentPath);
			}
		}	
		 return ;
	}	
	
	
	/**********************************************************
	 * @param from
	 * @param end
	 * @param exactStops
	 * @param finalPath
	 *********************************************************/
	public void findPathsWithExactStops(char from, char end, int exactStops, Stack<Edge> finalPath)
	{
		
		Node startNode = createNode(from);
		Node endNode = createNode(end);

		Stack<Edge> currentPath = new Stack<Edge>();
		
		findPathsWithExactStops( startNode,  endNode,  exactStops,  currentPath,  finalPath);
	}
	
	/********************************************************
	 * @param fromNode
	 * @param endNode
	 * @param exactStops
	 * @param currentPath
	 * @param finalPath
	 *******************************************************/
	private void findPathsWithExactStops(Node fromNode, Node endNode, int exactStops, Stack<Edge> currentPath, Stack<Edge> finalPath)
	{
		
		if (exactStops==0)
			return;
		
		for (Edge edge: fromNode.getAdjacentEdges())
		{
			if (edge.getTo().equals(endNode) &&  exactStops==1) 
			{
				currentPath.push(edge);
				
				finalPath.addAll(currentPath);
				
				currentPath.pop();
				finalPath.add(terminatorEdge); 
				
				return;
			} 
			else
			{
				currentPath.push(edge);
				
				findPathsWithExactStops(edge.getTo(), endNode,exactStops-1,currentPath, finalPath);
					
				currentPath.pop();
			}
		}	
		 return ;
	}	
	

	
	/********************************************************
	 * @param path
	 * @return
	 *******************************************************/
	private int getDistance(Stack<Edge> path)
	{
		
		if (path==null || path.size()==0)
			return 0;
		
		Edge sumEdge = path.peek();
		
		if (sumEdge.equals(terminatorEdge))
			return sumEdge.getDistance();
		
		throw new IllegalStateException("Incorrect terminating edge. ");
	}
	
	
	/********************************************************
	 * @param target
	 *******************************************************/
	private void popAndSum(Stack<Edge> target)
	{
		Edge sumEdge = target.pop();
		Edge toBeRemoved = target.pop();

		int distanceNow = sumEdge.getDistance() - toBeRemoved.getDistance();
		
		sumEdge = new Edge(terminatorNode, terminatorNode, distanceNow);
		
		target.push(sumEdge);
	}
	

	/********************************************************
	 * @param target
	 * @param newEdge
	 *******************************************************/
	private void pushAndSum(Stack<Edge> target, Edge newEdge)
	{
		if(target.isEmpty())
		{    
			target.push(newEdge);
			Edge sumEdge = new Edge(terminatorNode, terminatorNode, newEdge.getDistance());
			target.push(sumEdge);
		}
		else
		{
			
			Edge sumEdge = target.pop();
			target.push(newEdge);
			
			int distanceSoFar = newEdge.getDistance() + sumEdge.getDistance();
			
			sumEdge = new Edge(terminatorNode, terminatorNode, distanceSoFar);
			target.push(sumEdge);
		}
		
	}
	
	
	/********************************************************
	 * 
	 *******************************************************/
	public void resetVisted()
	{
		for (Node node: allNodes)
		{
			for (Edge edge: node.getAdjacentEdges())
				edge.setVisted(false);
			
		}
	}
	
	
	
	/********************************************************
	 * @param path
	 *******************************************************/
	public void resetVisted(Stack<Edge> path)
	{
		for (Edge edge: path)
		{
			edge.setVisted(false);
		}
	}
	
	
	/**********************************************************
	 * @param startNode
	 * @param endNode
	 * @param finalPath
	 *********************************************************/
	public void shortestPath(char start, char end, Stack<Edge> finalPath)
	{
		Node startNode = createNode(start);
		Node endNode = createNode(end);
		
		Stack<Edge> currentPath=new Stack<Edge>();
		resetVisted();
		shortestPath(startNode, endNode, currentPath, finalPath);
	}
	
	/********************************************************
	 * @param startNode
	 * @param endNode
	 * @param currentPath
	 * @param finalPath
	 *******************************************************/
	private void shortestPath(Node startNode, Node endNode, Stack<Edge> currentPath, Stack<Edge> finalPath)
	{				
		for (Edge edge: startNode.getAdjacentEdges())
		{
			if (edge.getTo().equals(endNode)) 
			{	
				currentPath.push(edge);
				
				int distanceSoFar = sumDistance(currentPath);
				
				if (finalPath.isEmpty() || (distanceSoFar < finalPath.lastElement().getDistance()))
				{
					finalPath.clear();
					finalPath.addAll(currentPath);
					//Add total distance of this path to a dummy so that we do not have to calculate it again
					Edge dummyEdge = createDistanceHolder(distanceSoFar);
					finalPath.push(dummyEdge);
				}
												
				currentPath.pop();
				
				return ;
			} 
			else
			{
				if (!edge.isVisted())
				{
					currentPath.push(edge);
					edge.setVisted(true);
				
					shortestPath(edge.getTo(), endNode,currentPath, finalPath);
				
					currentPath.pop();
				}
			}
		}	
		 return ;
	}
	
	/********************************************************
	 * @param nodes
	 * @return
	 * @throws NoSuchRouteException
	 *******************************************************/
	public int sumDistance(Node[] nodes) throws NoSuchRouteException 
	{

		long sum = 0;

		for (int i = 0; i < nodes.length - 1; i++) 
		{
			Node fromNode = nodes[i];
			Node toNode = nodes[i + 1];

			Edge edge = fromNode.getAdjacentEdge(toNode); 

			if (edge != null) 
			{
				sum = sum + edge.getDistance();
			} 
			else 
			{
				throw new NoSuchRouteException("NO SUCH ROUTE ");
			}
		}
		
		if (sum > Integer.MAX_VALUE)
			new IllegalStateException("Value is great then int max.");
			

		return (int)sum;
	}
	
	/********************************************************
	 * @param path
	 * @return
	 *******************************************************/
	public int sumDistance(Stack<Edge> path)
	{		
		long out=0;
	
		Iterator <Edge> itr = path.iterator();
		
		while (itr.hasNext())
		{		
			out = out + itr.next().getDistance();
		}
		
		if (out > Integer.MAX_VALUE)
			new IllegalStateException("Value of distance is great then int max.");
		
		return (int)out;
	}
	
	
	/********************************************************
	 * @param route
	 * @return
	 *******************************************************/
	public Node[] convertToNodes(String route) 
	{
		route = route.replace("-", "");
		char[] nodeLabels = route.toCharArray();

		Node[] out = new Node[nodeLabels.length];

		for (int i = 0; i < nodeLabels.length; i++) 
		{
			out[i] = createNode(nodeLabels[i]);
		}

		return out;
	}	
}
