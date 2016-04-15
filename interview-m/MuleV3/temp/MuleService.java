package mule.test.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;

import mule.test.model.Edge;
import mule.test.model.Node;

public class MuleService 
{

	private Map<String, Edge> myMap=null;
	private Map<Character, Node> myNodes = null;
	private Map<Node, Map<Node, Edge>> myMap2=null;
	private List<Node> myNodes2 = null;
	
	public MuleService()
	{
		myMap = new HashMap<String, Edge>();
		myNodes = new HashMap<Character, Node>();  
		myMap2 = new HashMap<Node, Map<Node, Edge>>();
		myNodes2 = new ArrayList<Node>();		
	}
	
	/******************************************************/
	public void addEdge(char from, char to, int distance)
	{
		
		Node fromNode = getNode(Character.toUpperCase(from));
		Node toNode = getNode(Character.toUpperCase(to));
		
		String edgeLookUpKey = fromNode.getKey()+toNode.getKey();
		
		if (!myMap.containsKey(edgeLookUpKey))
		{
			Edge edge = new Edge(fromNode, toNode, distance);
			
			fromNode.addAdjacentEdge(edge);
			
			myMap.put(edgeLookUpKey, edge);
			
			fromNode.addAdjacentNode(toNode);//TDB remove						
		}
		else
		{
			System.out.println("Wranning: edge already exists "+from+to+distance);
		}
	}
	
	
	/******************************************************/	
	public long calculateDistance(String route) throws NoSuchRouteException
	{
		Node[] nodes = convertToNodeList(route);
		
		long sum=0;
		
		for (int i=0; i < nodes.length-1; i++)
		{
			Node fromNode = nodes[i];
			Node toNode = nodes[i+1];
			
			String edgeLookUpKey = fromNode.getKey() + toNode.getKey();
			
			Edge edge = myMap.get(edgeLookUpKey);
			
			if (edge!=null)
			{
				sum = sum + edge.getDistance();
			}
			else
			{
				throw new NoSuchRouteException("NO SUCH ROUTE "+route);
			}
		}
		
		return sum;
	}
	
	
	
	/****************************************************/
	public void getPaths(char from, char to, int maxStop)
	{		
		Node fromNode = getNode(Character.toUpperCase(from));
		Node toNode = getNode(Character.toUpperCase(to));
		
		Queue<Edge> result = new ConcurrentLinkedQueue<Edge>();
		
		getRoute(fromNode, toNode, maxStop, result);

		
		
	}
	
	
	private String getRoute(Node fromNode, Node toNode, int maxStops, Queue<Edge> result)
	{
		
		if (maxStops == 0) return "";
		
		if (fromNode.isAdjacent(toNode))
		{
			
			System.out.println( "("+fromNode.getKey()+ "-" +toNode.getKey()+")");
			result.add(fromNode.getEdge(toNode));
			return "";
		}
		else
		{
			for (Node node: fromNode.getAdjacentNodes())
			{
				getRoute(node, toNode, maxStops-1, result);
			}
		}
		
		return "";
	}

	/******************************************************/	
	private Node[] convertToNodeList(String route)
	{		
		route = route.replace("-", "");
		char[] nodeLabels = route.toCharArray();
		
		Node[] out = new Node[nodeLabels.length];
		
		for (int i=0; i < nodeLabels.length; i++)
		{
			out[i] = new Node(nodeLabels[i]);
		}
		
		return out;
	}
	
	
	private Node getNode(char label)
	{
		if (!Character.isUpperCase(label))
			label = Character.toUpperCase(label);
		
		Node node= new Node(label); //TBD should use Map??
		
		if (myNodes2.contains(node))
		{
			int index = myNodes2.lastIndexOf(node);
			node = myNodes2.get(index); //TBD can be optimize via node.getId() look up.			
		}
		else
		{
			myNodes2.add(node);			
		}
		
		return node;
	}
	
	/*
	private List<Node> getAdjacentNodes(Node from)
	{
		
	}*/
}
