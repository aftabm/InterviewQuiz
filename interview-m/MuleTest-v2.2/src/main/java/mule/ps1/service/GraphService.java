package mule.ps1.service;

import mule.ps1.dao.MapDb;
import mule.ps1.dao.model.Edge;
import mule.ps1.dao.model.Node;

/********************************************************
 * @author amahmood
 *
 *******************************************************/
public class GraphService 
{
	
	private static MapDb myMap =null;
	private static GraphEngine graphEngine = null;
	private static boolean debug = false;
	
	
	
	public GraphService()
	{
		myMap = new MapDb();
		graphEngine = new GraphEngine(myMap);
	}
	/********************************************************
	 * @param from
	 * @param to
	 * @param distance
	 *******************************************************/
	public void addEdge(char from, char to, int distance) 
	{
		myMap.addEdge(from, to, distance);
	}

	
	/********************************************************
	 * @param from
	 * @param to
	 * @param distance
	 *******************************************************/
	public String allPathsLessThanDistance(char from, char to, int distance) 
	{
				
		Edge[] finalPath = graphEngine.findPathsLessThanDistance(from, to, distance);
		
		String result = removeDebugInfo(finalPath);
		
		System.out.print("All paths from "+from+" to "+to+" with distance less than "+distance + " = ");
		
		if (debug)
		{
			printEdges(finalPath, false);
		}
		else
		{
			System.out.print(result);
		}
		
		System.out.println();
		
		return result;
	}	

	/********************************************************
	 * @param route
	 * @return
	 *******************************************************/
	public long caculateDistance(String route) 
	{
		if (!isValidFormat(route))
			throw new IllegalArgumentException("Required parameter is not in a valid format");
		
		long result = 0;
		
		try 
		{
			Node[] nodes = myMap.convertToNodes(route);
			result = graphEngine.calculateDistance(nodes);
			System.out.println("Distance " + route + " = " + result);
		} 
		catch (mule.ps1.service.NoSuchRouteException e) 
		{
			System.out.println("Distance " + route + " = " + e.getMessage());
		}

		return result;
	}

	/*********************************************************
	 * @param route
	 * @return
	 * *******************************************************/
	private boolean isValidFormat(String route)
	{
		char[] tokens = route.toCharArray();
		
		final int LETER_CHECK=1;
		final int SEPARATOR_CHECK=2;
		 int tokenType = LETER_CHECK;
		
		for (char token: tokens)
		{
			switch(tokenType)
			{
				case LETER_CHECK:
					if (!Character.isLetter(token))
						return false;
					tokenType = SEPARATOR_CHECK; 
					break;
				case SEPARATOR_CHECK:
					if (token!='-')
						return false;
					tokenType = LETER_CHECK; 
				break;
			}
		}
		
		return true;
	}
	
	/********************************************************
	 * @param path
	 * @return
	 *******************************************************/
	private static int countPaths(Edge[] path) 
	{
		int count = 0;
		
		for (Edge edge: path)
		{
			if (edge.equals(Edge.terminatorEdge))
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
	public int countPathsWithExactStop(char from, char to, int exactStop) 
	{
		
		System.out.print("Paths " + from + "-" + to + " exactStop "
				+ exactStop + " = ");
		
		Edge[] output = graphEngine.findPathsWithExactStops(from, to, exactStop);
		
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
	public int countPathsWithMaxStop(char from, char to, int maxStop) 
	{
		int result = 0;

		System.out.print("Paths " + from + "-" + to + " maxStop "
				+ maxStop + " = ");

		for (int i = 2; i <= maxStop; i++) 
		{			
			Edge[] output = graphEngine.findPathsWithExactStops(from, to,i);

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
	 * @param path
	 *******************************************************/
	private static void printEdges(Edge[] path, boolean removeTerminators) 
	{
		for (Edge edge: path)
		{
			if (edge.equals(Edge.terminatorEdge)  && removeTerminators)
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
	
	
	/*********************************************************
	 * @param path
	 * @return
	 ********************************************************/
	private static String removeDebugInfo(Edge[] path)
	{
		StringBuffer sb = new StringBuffer();
		
		if (path!=null && path.length>0)
		{
			Edge lastEdge=path[0];
		
			for(Edge edge: path)
			{
				if (edge.equals(Edge.terminatorEdge))
				{
					sb.append(lastEdge.getTo());
					sb.append(", ");
					continue;
				}
				else
					sb.append(edge.getFrom());
			
				lastEdge = edge;
			}
		}
		return sb.toString();
	}

	
	/**********************************************************
	 * @param newValue
	 *********************************************************/
	public void setDebug(boolean newValue) 
	{
		debug=newValue;
	}


	/********************************************************
	 * @param from
	 * @param to
	 * @return
	 *******************************************************/
	public long shortestDistance(char from, char to) 
	{
		Edge[] output = graphEngine.shortestPath(from, to);
		
		int result=0;
		
		if (output!=null && output.length > 0)			
			result = output[output.length-1].getDistance();
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
}
