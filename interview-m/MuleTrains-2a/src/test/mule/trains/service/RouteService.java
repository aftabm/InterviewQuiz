package test.mule.trains.service;

import java.util.HashMap;
import java.util.Map;

import test.mule.trains.model.RouteSegment;

public class RouteService 
{
	
	private Map<String, Map<String, RouteSegment> > fromRoutes; //from key -> to key ??? memory intensive ????
	private Map<String, Map<String, RouteSegment> > toRoutes; 
	
	
	
	public RouteService()
	{
		fromRoutes = new HashMap<String, Map<String, RouteSegment> >();
		toRoutes = new HashMap<String, Map<String, RouteSegment> >();
	}
	
	public void addRoute(String from, String to, int distance)
	{
		//TBD: check pre - conditions from/to not null, distance not zero.
		//and through throw illegal argument exception.
		
		RouteSegment routeSegment = new RouteSegment(from, to, distance);
		
		addFromRout(routeSegment);
		addToRoute(routeSegment);
		
	}
	
	public long calculateDistance(String route) throws NoSuchRouteException
	{
		//TBD: pre-condition check route is not null
		
		String[] stationNames =  convertToList(route);
		
		long distance=0;
		
		if (stationNames != null && stationNames.length > 0)
		{
			for (int index=0; index < stationNames.length-1; index++)
			{
				String fromStation=stationNames[index];
				String toStation=stationNames[index+1];//Note: possible out of bound exception
				
				RouteSegment from = getSegment(fromStation, toStation);
				
				if (from==null)
					throw new NoSuchRouteException(fromStation+"-"+toStation+" does not exists");
					
				distance = distance+from.getDistance();
			}
			
		}
		
		return distance;
	}
	
	public String getRoute(String from, String to, int maxStops)
	{
		String out=null;

		//optimization
		if (maxStops == 1)
		{
			return from+"-"+to;			
		}
				
		Map<String, RouteSegment> fromToMap = fromRoutes.get(from);
		Map<String, RouteSegment> toFromMap = toRoutes.get(to);
		
		if (maxStops == 2)
		{				
			
			
			//get key set
			

		}						
		
		return out;
		
	}
	
	private RouteSegment getSegment(String from, String to)
	{
		
		//tbd: precondition check from not null;
		
		RouteSegment out=null;
		
		Map<String, RouteSegment> fromToMap = fromRoutes.get(from);
		
		out = fromToMap.get(to);
								
		return out;		
	}
	
			
	
	private String[] convertToList(String route)
	{
		//TBD: pre condition checks null and contains delimiter -
		
		String[] out = new String[0];//to avoid null		
		out = route.split("-");
				
		return out;
		
	}
	
	
	
	private void addFromRout(RouteSegment fromRoute)
	{
		Map<String, RouteSegment> fromToMap;
		
		//get the list of routes starting from this from
		if (fromRoutes.containsKey(fromRoute.getFrom()))
		{			
			fromToMap = fromRoutes.get(fromRoute.getFrom());			
		}
		else
		{
			//create a new list of route for tis from
			fromToMap = new HashMap<String, RouteSegment>();
			fromRoutes.put(fromRoute.getFrom(), fromToMap);			
		}	
		
		fromToMap.put(fromRoute.getTo(), fromRoute);
		
	}
	
	
	private void addToRoute(RouteSegment toRoute)
	{
		Map<String, RouteSegment> toFromMap;
		
		//get the list of routes starting from this from
		if (toRoutes.containsKey(toRoute.getFrom()))
		{			
			toFromMap = toRoutes.get(toRoute.getFrom());			
		}
		else
		{
			//create a new list of route for tis from
			toFromMap = new HashMap<String, RouteSegment>();
			toRoutes.put(toRoute.getFrom(), toFromMap);			
		}	
		
		toFromMap.put(toRoute.getFrom(), toRoute);
		
	}
	
	
}
