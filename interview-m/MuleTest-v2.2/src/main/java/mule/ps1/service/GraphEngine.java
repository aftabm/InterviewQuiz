package mule.ps1.service;

import java.util.Iterator;
import java.util.Stack;

import mule.ps1.dao.MapDb;
import mule.ps1.dao.model.Edge;
import mule.ps1.dao.model.Node;

/********************************************************
 * @author amahmood
 *
 *******************************************************/
public class GraphEngine 
{
	private MapDb map=null;
	


	/********************************************************
	 * 
	 *******************************************************/
	public GraphEngine(MapDb newMap) 
	{
		this.map = newMap;
	}

	
	/********************************************************
	 * @param nodes
	 * @return
	 * @throws NoSuchRouteException
	 *******************************************************/
	public int calculateDistance(Node[] nodes) throws NoSuchRouteException 
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
	private int calculateDistance(Stack<Edge> path)
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
	

	
	/**********************************************************
	 * @param from
	 * @param end
	 * @param distance
	 * @param finalPath
	 *********************************************************/
	public Edge[] findPathsLessThanDistance(char from, char end, int distance)
	{
		if (distance <=0)
			throw new IllegalArgumentException("One of the required parameters are null");
		
		Node startNode = map.convertToNode(from);
		Node endNode = map.convertToNode(end);
		Stack<Edge> currentPath = new Stack<Edge>();
		Stack<Edge> finalPath = new Stack<Edge>();
		findPathLessThanDistance(startNode, endNode,  distance, currentPath, finalPath);
				
		Edge[] out = toEdgeArray(finalPath);
		
		return out;
	}
	
	
	/***********************************************************
	 * @param source
	 * @return
	 **********************************************************/
	private Edge[] toEdgeArray(Stack<Edge> source) 
	{
		Edge[] out = new Edge[source.size()];
		
		for (int i=0; i < source.size(); i++)
		{
			out[i]=(Edge)source.elementAt(i).copy();
		}
		
		return out;
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
				
				if (peekDistance(currentPath) < distance)
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
	public Edge[] findPathsWithExactStops(char from, char end, int exactStops)
	{
		if (exactStops <=0)
			throw new IllegalArgumentException("One of the required parameters are null");
		
		Node startNode = map.convertToNode(from);
		Node endNode = map.convertToNode(end);

		Stack<Edge> currentPath = new Stack<Edge>();
		Stack<Edge> finalPath = new Stack<Edge>();
		findPathsWithExactStops( startNode,  endNode,  exactStops,  currentPath,  finalPath);
		
		Edge[] out = toEdgeArray(finalPath);
		
		return out;
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
				finalPath.add(Edge.terminatorEdge); 
				
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
	private int peekDistance(Stack<Edge> path)
	{
		
		if (path==null || path.size()==0)
			return 0;
		
		Edge sumEdge = path.peek();
		
		if (sumEdge.equals(Edge.terminatorEdge))
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
		
		sumEdge = new Edge(Node.terminatorNode, Node.terminatorNode, distanceNow);
		
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
			Edge sumEdge = new Edge(Node.terminatorNode, Node.terminatorNode, newEdge.getDistance());
			target.push(sumEdge);
		}
		else
		{
			Edge sumEdge = target.pop();
			target.push(newEdge);
			
			int distanceSoFar = newEdge.getDistance() + sumEdge.getDistance();
			
			sumEdge = new Edge(Node.terminatorNode, Node.terminatorNode, distanceSoFar);
			target.push(sumEdge);
		}
		
	}
	
	
	/**********************************************************
	 * @param startNode
	 * @param endNode
	 * @param finalPath
	 *********************************************************/
	public Edge[] shortestPath(char start, char end)
	{
		Node startNode = map.convertToNode(start);
		Node endNode = map.convertToNode(end);
		
		Stack<Edge> currentPath=new Stack<Edge>();
		map.resetVisted();
		Stack<Edge> finalPath = new Stack<Edge>();
		
		shortestPath(startNode, endNode, currentPath, finalPath);
		
		Edge[] out = toEdgeArray(finalPath);
		
		return out;
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
				
				int distanceSoFar = calculateDistance(currentPath);
				
				if (finalPath.isEmpty() || (distanceSoFar < finalPath.lastElement().getDistance()))
				{
					finalPath.clear();
					finalPath.addAll(currentPath);
					//Add total distance of this path to a dummy so that we do not have to calculate it again
					Edge dummyEdge = Edge.createDummyEdge(distanceSoFar);
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
}
