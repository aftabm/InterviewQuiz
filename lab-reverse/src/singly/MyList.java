package singly;

public class MyList
{
   public Node head=null;
   
   
   public void print()
   {
	   Node temp = head;
	   
	   while(temp!=null)
	   {
		   System.out.print(temp.data+", ");		   
		   temp = temp.next;		   
	   }
	   
	   System.out.println();
   }
   
   
   public void reverse()
   {	
	   Node current = head;
	   head = null;
	   
	   while(current!=null)
	   {
		   Node temp = current;
		   current = current.next;
		   temp.next = head;
		   head = temp;		   
	   }		   
   }
   
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		MyList list = new MyList();		
		list.head = new Node(1);		

		Node current = list.head;		
		
		current.next=new Node(2);
		current = current.next;
				
		current.next=new Node(3);
		current = current.next;
		
		current.next=new Node(4);
		current = current.next;
		
		current.next=new Node(5);
		current = current.next;		
		
		list.print();
		
		list.reverse();
		
		list.print();
		
	}
	
	
	private static class Node
	{
		Node next=null;
		int data=0;
		
		public Node(int data)
        {
	       this.data = data;
        }
	}
}
