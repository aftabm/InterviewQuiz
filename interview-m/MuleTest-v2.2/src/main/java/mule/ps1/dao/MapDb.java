package mule.ps1.dao;

import java.util.ArrayList;
import java.util.List;

import mule.ps1.dao.model.Edge;
import mule.ps1.dao.model.Node;

public class MapDb 
{
	private List<Node> allNodes = null;
	
	/***********************************************************
	 * 
	 **********************************************************/
	public MapDb()
	{
		
		allNodes = new ArrayList<Node>();
	}

	/**********************************************************
	 * @param from
	 * @param to
	 * @param distance
	 *********************************************************/
	public void addEdge(char from, char to, int distance) 
	{
		if (distance <=0)
			throw new IllegalArgumentException("One of the required parameters are null");
		
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
	
	
	/***********************************************************
	 * @param node
	 * @return
	 **********************************************************/
	public boolean contains(Node node) 
	{
		return allNodes.contains(node);
	}
	
	
	/*********************************************************
	 * @param nodeLabel
	 * @return
	 ********************************************************/
	public Node convertToNode(char nodeLabel)
	{
		nodeLabel = Character.toUpperCase(nodeLabel);

		Node node = new Node(nodeLabel); 
		
		if (!allNodes.contains(node))
			throw new IllegalArgumentException("Unable to find node with label = "+nodeLabel);// TBD should be checked exception
		
		int index = allNodes.lastIndexOf(node);
		node = allNodes.get(index);
		
		return  node;
	}
	
	
	/********************************************************
	 * @param route
	 * @return
	 *******************************************************/
	public Node[] convertToNodes(String route) 
	{
		//TBD: validate route using regular expression
		route = route.replace("-", "");
		char[] nodeLabels = route.toCharArray();

		Node[] out = new Node[nodeLabels.length];

		for (int i = 0; i < nodeLabels.length; i++) 
		{
			out[i] = createNode(nodeLabels[i]);
		}

		return out;
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
	
}
