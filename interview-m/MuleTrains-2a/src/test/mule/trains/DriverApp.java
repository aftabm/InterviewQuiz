/**
 * 
 */
package test.mule.trains;

import test.mule.trains.service.NoSuchRouteException;
import test.mule.trains.service.RouteService;

/**
 * @author amahmood
 *
 */
public class DriverApp {

	static RouteService  routeService = null;// TBD soft reference ????
	
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		routeService = new RouteService();
		
		routeService.addRoute("A", "B", 5);
		routeService.addRoute("B", "C", 4);
		routeService.addRoute("C", "D", 8);
		routeService.addRoute("D", "C", 8);
		routeService.addRoute("A", "D", 5);
		routeService.addRoute("C", "E", 2);
		routeService.addRoute("E", "B", 3);
		routeService.addRoute("A", "E", 7);
		
		
		findDistance("A-B-C");
		findDistance("A-D");
		findDistance("A-D-C");
		findDistance("A-E-B-C-D");
		findDistance("A-E-D");
		
		printRout("C", "C", 2);
	}
	
	private static void printRout (String from, String to, int distance)
	{
		System.out.println(routeService.getRoute(from, to, distance));
	}
	
	private static void findDistance(String route)
	{
		try 
		{
			System.out.println("Distance "+route +"= "+routeService.calculateDistance(route) );
		} 
		catch (NoSuchRouteException e) 
		{
			System.out.println("No such route " + route);
		}		
		
	}

}
