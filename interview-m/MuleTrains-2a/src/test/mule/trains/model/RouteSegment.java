package test.mule.trains.model;


/**
 * @author amahmood
 * Time 12:00 12/4/2010
 *
 *Model object to store information of a route.
 *Although it is an open extension. But the future version may 
 *seal it. 
 *
 */
public class RouteSegment 
{

	private String from; // we can use use char too. more space efficient
	private String to;
	int     distance;
	
	public RouteSegment getNext() 
	{
		return next;
	}

	public void setNext(RouteSegment next) 
	{
		this.next = next;
	}

	public RouteSegment getPrevious() 
	{
		return previous;
	}


	public void setPrevious(RouteSegment previous) 
	{
		this.previous = previous;
	}


	private RouteSegment next;
	private RouteSegment previous;
	
	
	/**
	 * @param from
	 * @param to
	 * @param distance
	 * 
	 * Constructor to make it immutable object.
	 * This has a limitation work with 
	 * bean utils and serialization   
	 */
	public RouteSegment(String from, String to, int distance)
	{
		this.from = from;
		this.to = to;
		this.distance = distance;
	}

	public String getFrom() 
	{
		return from;
	}

	public String getTo() 
	{
		return to;
	}

	public int getDistance() 
	{
		return distance;
	}
	
	
	
}
