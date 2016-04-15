package mule.test.service;

import java.util.Iterator;
import java.util.Stack;

import mule.test.model.Edge;
import mule.test.model.Node;

/********************************************************
 * @author amahmood
 *
 *******************************************************/
public class GraphService 
{
	private static GraphEngine graphEngine = new GraphEngine();
	public static boolean debug = false;
	
	/********************************************************
	 * @param from
	 * @param to
	 * @param distance
	 *******************************************************/
	public static void addEdge(char from, char to, int distance) 
	{
		graphEngine.addEdge(from, to, distance);
	}

	
	/********************************************************
	 * @param path
	 * @return
	 *******************************************************/
	private static int countPaths(Stack<Edge> path) 
	{
		int count = 0;
		Iterator<Edge> itr = path.iterator();

		while (itr.hasNext()) 
		{
			if (itr.next().equals(GraphEngine.terminatorEdge))
				count++;
		}
		return count;
	}	

	/********************************************************
	 * @param from
	 * @param to
	 * @param exactStop
	 * @return
	 *******************************************************/
	public static int countPathsWithExactStop(char from, char to, int exactStop) 
	{
		Stack<Edge> output = new Stack<Edge>();
		
		System.out.print("Paths " + from + "-" + to + " exactStop "
				+ exactStop + " = ");
		
		graphEngine.findPathsWithExactStops(from, to, exactStop, output);
		
		if(debug)			
			printEdges(output, true);
		int result = countPaths(output);
		
		if (debug)	
			System.out.print("Total paths = ");
		
		System.out.println(result);
				
		return result;
	}

	
	
	/********************************************************
	 * @param from
	 * @param to
	 * @param maxStop
	 * @return
	 *******************************************************/
	public static int countPathsWithMaxStop(char from, char to, int maxStop) 
	{
		int result = 0;

		System.out.print("Paths " + from + "-" + to + " maxStop "
				+ maxStop + " = ");

		for (int i = 2; i <= maxStop; i++) 
		{
			Stack<Edge> output = new Stack<Edge>();
			
			graphEngine.findPathsWithExactStops(from, to,i, output);

			if (debug)
				printEdges(output, true);
			
			result = result + countPaths(output);
		}

		if (debug)
			System.out.print("Total paths = ");
		
		System.out.println(result);

		return result;
	}

	/********************************************************
	 * @param route
	 * @return
	 *******************************************************/
	public static long caculateDistance(String route) 
	{
		long result = 0;
		
		try 
		{
			Node[] nodes = graphEngine.convertToNodes(route);
			result = graphEngine.sumDistance(nodes);
			System.out.println("Distance " + route + " = " + result);
		} 
		catch (mule.test.service.NoSuchRouteException e) 
		{
			System.out.println("Distance " + route + " = " + e.getMessage());
		}

		return result;
	}

	/********************************************************
	 * @param path
	 *******************************************************/
	public static void printNodes(Stack<Node> path, boolean removeTerminators) 
	{
		Iterator<Node> itr = path.iterator();

		while (itr.hasNext()) 
		{
			Node node = itr.next();

			if (node.equals(GraphEngine.terminatorNode) && removeTerminators)
			{
				System.out.print(",");
			} 
			else 
			{
				System.out.print(node);
			}
			System.out.print(" ");
		}
	}
	

	/********************************************************
	 * @param path
	 *******************************************************/
	public static void printEdges(Stack<Edge> path, boolean removeTerminators) 
	{
		Iterator<Edge> itr = path.iterator();

		while (itr.hasNext()) 
		{
			Edge edge = itr.next();

			if (edge.equals(GraphEngine.terminatorEdge)  && removeTerminators)
			{
				System.out.print(",");
			} 
			else 
			{
				System.out.print(edge);
			}
			System.out.print(" ");
		}
	}	
	

	/********************************************************
	 * @param from
	 * @param to
	 * @return
	 *******************************************************/
	public static long shortestDistance(char from, char to) 
	{
		
		Stack<Edge> output=new Stack<Edge>();
		
		graphEngine.shortestPath(from, to,  output);
		
		int result=0;
		
		if (!output.isEmpty() && output.lastElement().equals(GraphEngine.terminatorEdge))			
			result = output.lastElement().getDistance();
		else
			throw new IllegalStateException("Unable to read distance from the path.");
		
		//System.out.println();
		System.out.print("Shortest distance between "+from +" to "+ to+" = "+result);	
		
		if (debug)
		{
			System.out.print(" via ");
			printEdges(output, true);
		}
		
		System.out.println();
		
		return result;
	}
	
 
	
	/********************************************************
	 * @param from
	 * @param to
	 * @param distance
	 *******************************************************/
	public static String allPathsLessThanDistance(char from, char to, int distance) 
	{
		
		Stack<Edge> output = new Stack<Edge>();
		
		graphEngine.findPathLessThanDistance(from, to, distance, output);
		
		String result = removeDebugInfo(output);
		
		System.out.print("All paths from "+from+" to "+to+" with distance less than "+distance + " = ");
		
		if (debug)
		{
			printEdges(output, false);
		}
		else
		{
			System.out.print(result);
		}
		
		System.out.println();
		
		return result;
	}

	
	/*********************************************************
	 * @param path
	 * @return
	 ********************************************************/
	private static String removeDebugInfo(Stack<Edge> path)
	{
		StringBuffer sb = new StringBuffer();
		
		Edge lastEdge=path.firstElement();
		
		for(Edge edge: path)
		{
			if (edge.equals(GraphEngine.terminatorEdge))
			{
				sb.append(lastEdge.getTo());
				sb.append(", ");
				continue;
			}
			else
				sb.append(edge.getFrom());
			
			lastEdge = edge;
		}
		
		return sb.toString();
	}
}
