package mule.test.model;

import java.util.ArrayList;
import java.util.List;

/********************************************************
 * @author amahmood
 *
 *******************************************************/
public class Edge 
{
	private Node from=null;
	private Node to=null;
	private int distance=0;
	private List<Edge> adjacentEdges=null;
	boolean hasVisted=false;
	
	/********************************************************
	 * @param from
	 * @param to
	 * @param distance
	 *******************************************************/
	public Edge (Node from, Node to, int distance)
	{
		this.from = from;
		this.to = to;
		this.distance = distance;
		
		adjacentEdges = new ArrayList<Edge>();
	}
	
	/********************************************************
	 * @param edge
	 *******************************************************/
	public void addNext(Edge edge) 
	{
		this.adjacentEdges.add(edge);
		
	}
	
	/******************************************************* 
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 *******************************************************/
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Edge other = (Edge) obj;
		if (from == null) {
			if (other.from != null)
				return false;
		} else if (!from.equals(other.from))
			return false;
		if (to == null) {
			if (other.to != null)
				return false;
		} else if (!to.equals(other.to))
			return false;
		return true;
	}
	
	/********************************************************
	 * @return
	 *******************************************************/
	public int getDistance() 
	{
	
		return this.distance;
	}

	/********************************************************
	 * @return
	 *******************************************************/
	public Node getFrom()
	{
		return this.from;
	}

	
	/********************************************************
	 * @return
	 *******************************************************/
	public String getKey()
	{
		
		return from.toString()+to.toString();
	}
	
	/********************************************************
	 * @return
	 *******************************************************/
	public Node getTo() 
	{
		return this.to;
	}
	
	/******************************************************* 
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 *******************************************************/
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((from == null) ? 0 : from.hashCode());
		result = prime * result + ((to == null) ? 0 : to.hashCode());
		return result;
	}
	
	/********************************************************
	 * @return
	 *******************************************************/
	public boolean isVisted()
	{
		return hasVisted;
	}
	
	/********************************************************
	 * @param newValue
	 *******************************************************/
	public void setVisted(boolean newValue)
	{
		hasVisted=newValue;
	}
	
	/******************************************************* 
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 *******************************************************/
	@Override
	public String toString() 
	{
		return from + "" +to +""+ distance;
	}
	
}
