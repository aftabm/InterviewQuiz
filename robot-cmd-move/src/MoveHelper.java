import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class MoveHelper 
{
	Stack<Block>[] store=null;
	Map<Block, Block> roamingMap;

	public MoveHelper(int maxBlocks)
	{
		store = new Stack[maxBlocks];
		
		init();
	}
	
	
	private void init()
	{
		for (int i=0; i < store.length; i++)
		{
			Block block = Block.fromId(i);			
			Stack<Block> stack = new Stack<>();
			stack.push(block);
			store[i] = stack;
		}
		
		roamingMap = new HashMap<>();		
	}
	
	
	/*
	 * 1:1
	 * 2:2 5 4
	 * 3: 3
	 * 4:
	 * 5:
	 * 6: 6
	 * 7: 7
	 * */
	public static void main(String[] args) 
	{
			MoveHelper solution = new MoveHelper(10);
			System.out.print("INPUT> ");
			solution.print();
			
			solution.move(7, 1);
			System.out.print(" 7, 1> ");
			solution.print();
			
			solution.move(5, 1);
			System.out.print(" 5, 1> ");
			solution.print();
			
			solution.move(1, 6);
			System.out.print(" 1, 6> ");
			solution.print();
			
			solution.move(4, 3);
			System.out.print(" 4, 3> ");
			solution.print();
			
			solution.move(1, 4);
			System.out.print(" 1, 4> ");
			solution.print();
			
			solution.move(3, 1);
			System.out.print(" 3, 1> ");
			solution.print();
			
			solution.move(5, 2);
			System.out.print(" 5, 2> ");
			solution.print();
			
			solution.move(7, 5);
			System.out.print(" 7, 5> ");
			solution.print();
			
			solution.move(4, 5);
			System.out.print(" 4, 5> ");
			solution.print();

	}


	public void print() 
	{
		System.out.println(Arrays.deepToString(store));	
		//System.out.println(roamingMap);
		
	}


	public void move(int src, int dst) 
	{	
		if (src == dst)
			throw new java.lang.IllegalArgumentException("src and dst cannot be same. input was "+src);
		
		
		Block srcBlock = Block.fromName(src);
		Block dstBlock = Block.fromName(dst);
		
		
		Stack<Block>  dstStack=null;;		
		
		if(isRoaming(dstBlock) )
		{	
			Block roamingBlock  = getRoamingBlock(dstBlock);
			
			if(srcBlock.equals(roamingBlock))
			{
				System.out.println(" "+ src + ", "+ dst+"> Ignored Blockes are already stacked togather.");
				return;
			}
			
			dstStack = store[roamingBlock.id];
			unwind(dstStack, dstBlock);
			
			dstBlock = roamingBlock;
			
		}
		else					
			dstStack= store[dstBlock.id];
		
		
		//
		Stack<Block>  srcStack=null;
		
		if(isRoaming(srcBlock) )
		{
			Block roamingBlock  = getRoamingBlock(srcBlock);
			Stack<Block> roamingStack = store[roamingBlock.id]; 			
			remove(srcBlock, roamingStack);
			moveBack(srcBlock);			
		}
					
		srcStack = store[srcBlock.id];
		
		if (srcStack.isEmpty())
		{
			throw new java.lang.IllegalArgumentException("src stack is empty "+src+", "+dst);
		}

		unwind(srcStack, srcBlock);

		
		if (!srcBlock.equals(srcStack.peek()))
			throw new java.lang.IllegalStateException(" invalid state srcStack "+srcStack);
		
		dstStack.push(srcStack.pop());
		roamingMap.put(srcBlock, dstBlock);
		
	}
	
	private void remove(Block srcBlock, Stack<Block> roamingStack) 
	{
		Stack<Block> tempStack = new Stack<>();
		
		while(!srcBlock.equals(roamingStack.peek()))
		{
			tempStack.push(roamingStack.pop());
		}
		
		if ( !srcBlock.equals(roamingStack.pop()) || roamingStack.isEmpty() )
			throw new java.lang.IllegalStateException(" remove opeation corrupted stack for "+srcBlock);

		while(!tempStack.isEmpty())
			roamingStack.push(tempStack.pop());
		
	}


	private Block getRoamingBlock(Block block) 
	{
		if (!roamingMap.containsKey(block))
			return block;
			
		return roamingMap.get(block);
	}


	private void unwind(Stack<Block> stack, Block terminator) 
	{
		
		if (stack == null || stack.isEmpty())
			return;
		
		//TBD auto boxing cost ?????
		while (!terminator.equals(stack.peek()) )
		{
			Block topBlock = stack.pop();
			moveBack(topBlock);						
		}
	}


	private void moveBack(Block block) 
	{
		if (!store[block.id].isEmpty())
			throw new java.lang.IllegalStateException( block +" stack not empty. Contains " + store[block.id]);
		
		store[block.id].push(block);

		//TBD performance
		roamingMap.remove(block);
	}


	private boolean isRoaming(Block block) 
	{
		return roamingMap.containsKey(block);
	}

}

