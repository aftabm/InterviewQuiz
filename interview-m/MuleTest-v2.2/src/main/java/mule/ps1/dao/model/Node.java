/**
 * 
 */
package mule.ps1.dao.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/********************************************************
 * @author amahmood
 *
 *******************************************************/
public class Node
{
	private char label;
	private boolean hasVisted;
	private int id;
	private String key;
	
	private List<Node> adjacentNodes=null;
	private Map<Node, Edge> adjacentEdges=null;
	
	public static final Node terminatorNode = new Node('0');	
	

	/***********************************************************
	 * @param label
	 **********************************************************/
	public Node(char label)
	{
		this.label = Character.toUpperCase(label);
		this.hasVisted=false;
		adjacentNodes = new ArrayList<Node>();
		this.id=((int)label)-'A';
		key = String.valueOf(this.label);
		
		adjacentEdges = new HashMap<Node, Edge>();
	}
	
	/********************************************************
	 * @param edge
	 *******************************************************/
	public void addAdjacentEdge(Edge edge)
	{
		adjacentEdges.put(edge.getTo(), edge);
	}
	
	/********************************************************
	 * @param node
	 *******************************************************/
	public void addAdjacentNode(Node node) 
	{
		this.adjacentNodes.add(node);
	}
	
	/*********************************************************
	 * @return
	 ********************************************************/
	public Node copy() 
	{	
		return new Node(label);
	}
	
	/*******************************************************
	* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 *******************************************************/
	@Override
	public boolean equals(Object obj) 
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Node other = (Node) obj;
		if (label != other.label)
			return false;
		return true;
	}
	
	/********************************************************
	 * @param toNode
	 * @return
	 *******************************************************/
	public Edge getAdjacentEdge(Node toNode)
	{
		return adjacentEdges.get(toNode);
	}
	
	/********************************************************
	 * @return
	 *******************************************************/
	public Collection<Edge> getAdjacentEdges()
	{
		return this.adjacentEdges.values(); //TBD return clone;
	}
	
	/********************************************************
	 * @return
	 *******************************************************/
	public List<Node> getAdjacentNodes() 
	{
		return adjacentNodes;
	}
	
	/********************************************************
	 * @param toNode
	 * @return
	 *******************************************************/
	public Edge getEdge(Node toNode) 
	{
		return adjacentEdges.get(toNode);
		
	}

	/********************************************************
	 * @return
	 *******************************************************/
	public int getId()
	{		
		return this.id;
	}
	
	/********************************************************
	 * @return
	 *******************************************************/
	public String getKey()
	{
		return this.key;
	}

	/********************************************************
	 * @return
	 *******************************************************/
	public char getLabel()
	{
		return label;
	}
	
	/********************************************************
	 * @return
	 *******************************************************/
	public List<Edge> getUnvistedEdges() 
	{
		List<Edge> edges = new ArrayList<Edge>();
		
		for (Edge edge: this.adjacentEdges.values())
		{
			if (!edge.isVisted())
				edges.add(edge);
		}
		
		return edges;
	}
	
	/********************************************************
	 * @return
	 *******************************************************/
	public List<Node> getUnvistedNodes()
	{	
		List<Node> nodes = new ArrayList<Node>();
		
		for (Node node : adjacentNodes)
		{
			if (!node.isVisted())
				nodes.add(node);
		}
		
		return nodes;
	}

	/* ******************************************************
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 *******************************************************/
	@Override
	public int hashCode() 
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + label;
		return result;
	}

	/********************************************************
	 * @param to
	 * @return
	 *******************************************************/
	public boolean isAdjacent(Node to)
	{
		return this.adjacentNodes.contains(to);
	}

	/********************************************************
	 * @return
	 *******************************************************/
	public boolean isVisted() 
	{
		return hasVisted;
	}

	/********************************************************
	 * @param hasVisted
	 *******************************************************/
	public final void setVisted(boolean hasVisted) 
	{
		this.hasVisted = hasVisted;
	}


	/******************************************************* 
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 *******************************************************/
	@Override
	public String toString() 
	{
		return String.valueOf(this.label);
	}
}
