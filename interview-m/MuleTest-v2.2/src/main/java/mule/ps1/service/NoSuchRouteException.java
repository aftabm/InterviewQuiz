package mule.ps1.service;

/********************************************************
 * @author amahmood
 *
 *******************************************************/
public class NoSuchRouteException extends Exception 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/********************************************************
	 * 
	 *******************************************************/
	public NoSuchRouteException()
	{
		super();		
	}
	
	/********************************************************
	 * @param message
	 *******************************************************/
	public NoSuchRouteException(String message)
	{
		super(message);		
	}
	

}
