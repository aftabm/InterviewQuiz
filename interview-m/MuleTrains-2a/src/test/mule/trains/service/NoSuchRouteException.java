package test.mule.trains.service;

public class NoSuchRouteException extends Exception 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public NoSuchRouteException()
	{
		super();		
	}
	
	public NoSuchRouteException(String message)
	{
		super(message);		
	}
	

}
