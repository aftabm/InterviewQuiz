package mule.test;

import static org.junit.Assert.*;

import mule.ps1.service.GraphService;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GraphServiceTest
{

	private GraphService graphService =null;
	
	@Before
	public void setUp() throws Exception 	
	{
		graphService = new GraphService();
		graphService.setDebug(false);
		graphService.addEdge('A', 'B', 5);
		graphService.addEdge('B', 'C', 4);
		graphService.addEdge('C', 'D', 8);
		graphService.addEdge('D', 'C', 8);
		graphService.addEdge('D', 'E', 6);
		graphService.addEdge('A', 'D', 5);
		graphService.addEdge('C', 'E', 2);
		graphService.addEdge('E', 'B', 3);
		graphService.addEdge('A', 'E', 7);
	}

	@After
	public void tearDown() throws Exception
	{
	}

	@Test
	public void testCountPathsWithExactStop() 
	{
		int ac =graphService.countPathsWithExactStop('A', 'C', 4);
		assertEquals(3, ac);
	}

	@Test
	public void testCountPathsWithMaxStop() 
	{
		int cc = graphService.countPathsWithMaxStop('C', 'C', 3);
		assertEquals(2, cc);
	}

	@Test
	public void testCaculateDistance()
	{
		long abc = graphService.caculateDistance("A-B-C");
		assertEquals(9l, abc);
		long ad = graphService.caculateDistance("A-D");
		assertEquals(5l, ad);
		long adc =graphService.caculateDistance("A-D-C");
		assertEquals(13l, adc);
		long aebcd = graphService.caculateDistance("A-E-B-C-D");
		assertEquals(22l, aebcd);
		long aed = graphService.caculateDistance("A-E-D");
		assertEquals(0l, aed);
	}

	@Test
	public void testShortestDistance() 
	{
		long ac =graphService.shortestDistance('A', 'C');
		assertEquals(9l, ac);
		
		long bb = graphService.shortestDistance('B', 'B');
		assertEquals(9l, bb);
	}

	@Test
	public void testAllPathsLessThanDistance()
	{
		String cc = graphService.allPathsLessThanDistance('C', 'C', 28);
		String expected = "CEBC, CEBCEBC, CEBCEBCEBC, CEBCDC, CDEBC, CDC, CDCEBC,";
		assertTrue(expected.equals(cc.trim()));
	}

}
