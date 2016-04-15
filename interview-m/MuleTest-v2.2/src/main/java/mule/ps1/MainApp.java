/**
 * 
 */
package mule.ps1;

import mule.ps1.service.GraphService;

/**
 * @author amahmood
 *
 */
public class MainApp 
{

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		GraphService graphService = new GraphService();
		
		if (args!=null && args.length > 0)
		{
			if (args[0].trim().equalsIgnoreCase("debug"))
				graphService.setDebug(true);	
		}
		
		/**********************************
		 * Seed Data
		/**********************************/
		graphService.addEdge('A', 'B', 5);
		graphService.addEdge('B', 'C', 4);
		graphService.addEdge('C', 'D', 8);
		graphService.addEdge('D', 'C', 8);
		graphService.addEdge('D', 'E', 6);
		graphService.addEdge('A', 'D', 5);
		graphService.addEdge('C', 'E', 2);
		graphService.addEdge('E', 'B', 3);
		graphService.addEdge('A', 'E', 7);
		/**********************************/		
				
		graphService.caculateDistance("A-B-C");
		graphService.caculateDistance("A-D");
		graphService.caculateDistance("A-D-C");
		graphService.caculateDistance("A-E-B-C-D");
		graphService.caculateDistance("A-E-D");
			
		graphService.countPathsWithMaxStop('C', 'C', 3);
		graphService.countPathsWithExactStop('A', 'C', 4);
		
		graphService.shortestDistance('A', 'C');
		graphService.shortestDistance('B', 'B');
		
		graphService.allPathsLessThanDistance('C', 'C', 28);

	}
	
	

	

}
