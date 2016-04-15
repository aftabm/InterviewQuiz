/**
 * 
 */
package mule.test;

import mule.test.service.GraphService;

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
		
		/**********************************
		 * Seed Data
		/**********************************/
		GraphService.addEdge('A', 'B', 5);
		GraphService.addEdge('B', 'C', 4);
		GraphService.addEdge('C', 'D', 8);
		GraphService.addEdge('D', 'C', 8);
		GraphService.addEdge('D', 'E', 6);
		GraphService.addEdge('A', 'D', 5);
		GraphService.addEdge('C', 'E', 2);
		GraphService.addEdge('E', 'B', 3);
		GraphService.addEdge('A', 'E', 7);
		/**********************************/		
				
		GraphService.caculateDistance("A-B-C");
		GraphService.caculateDistance("A-D");
		GraphService.caculateDistance("A-D-C");
		GraphService.caculateDistance("A-E-B-C-D");
		GraphService.caculateDistance("A-E-D");
			
		GraphService.countPathsWithMaxStop('C', 'C', 3);
		GraphService.countPathsWithExactStop('A', 'C', 4);
		
		GraphService.shortestDistance('A', 'C');
		GraphService.shortestDistance('B', 'B');
		
		GraphService.allPathsLessThanDistance('C', 'C', 28);

	}
	
	

	

}
