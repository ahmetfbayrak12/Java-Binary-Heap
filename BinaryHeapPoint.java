package fouryy12;

import java.util.ArrayList;

public class BinaryHeapPoint 
{
	private final int d = 2;
	ArrayList<Point> heap;

	public BinaryHeapPoint() 
	{
		heap = new ArrayList<Point>();
	}
	protected int parent(int i)				//Find parent. 
	{
		return (i - 1)/2;
	}
	private int kthChild(int i, int k) 		// Find children.
	{
		return d * i + k;
	}    
	protected void insert(Point x)
	{
		heap.add(x);
		if (x.distance < 200)
			heapifyUpPrice(heap.indexOf(x));
		else
			heapifyUpDistance(heap.indexOf(x));
	}
	protected void deleteMin()				
	{
		Point deletedOne = heap.get(0);
		heap.set(0, heap.get(heap.size() -1));
		heap.set(heap.size()-1, deletedOne);
		heap.remove(heap.size()-1);
		if (heap.get(0).distance < 200)
			heapifyDownPrice(0);
		else
			heapifyDownDistance(0);
	}
	protected Point findMin()
	{         
		return heap.get(0);
	}

	/*	Up function for objects which their distances are less than 200.	*/
	private void heapifyUpPrice(int i)
	{
		Point temp = heap.get(i);
		while(heap.get(parent(i)).distance > 200 && i > 0)		// Checks parent's distance is higher than 200 then exchange them.
		{
			heap.set(i, heap.get(parent(i)));
			i = parent(i);
		}
		while(heap.get(parent(i)).fee > temp.fee && i > 0)		// Checks parent's price is higher than itself then exchange them.
		{
			heap.set(i, heap.get(parent(i)));
			i = parent(i);
		}

		heap.set(i, temp);
	}

	/*	Up function for objects which their distances are higher than 200.	*/
	private void heapifyUpDistance(int i)
	{
		Point temp = heap.get(i);
		while(heap.get(parent(i)).distance > temp.distance && i > 0 && heap.get(parent(i)).distance > 200)		// Checks parent's distance is higher than 200 and parent's distance is higher then itself then exchange them.
		{
			heap.set(i, heap.get(parent(i)));
			i = parent(i);
		}

		heap.set(i, temp);
	}

	/*	Down function for objects which their distances are less than 200.	*/
	private void heapifyDownPrice(int j)
	{
		int child = minChildPrice(j);		// Find best child.
		Point temp = heap.get(j);
		while(kthChild(j, 1) < heap.size() && 200 > heap.get(child).distance)		// Checks best child's distance is less than 200.
		{
			child = minChildPrice(j);
			if(heap.get(child).fee < temp.fee)		// Checks best child's price is less than itself then exchange them.
			{
				heap.set(j, heap.get(child));
			}
			else
				break;
			j = child;
		}
		heap.set(j, temp);
	}

	/*	Down function for object which their distances are higher than 200.	*/
	private void heapifyDownDistance(int j)
	{
		int child;
		Point temp = heap.get(j);
		while(kthChild(j, 1) < heap.size())
		{
			child = minChildDistance(j);
			if (heap.get(child).distance < 200)	// Checks best child's distance is less than 200 then exchange them.
			{
				heap.set(j, heap.get(child));			
			}
			else if(heap.get(child).distance < temp.distance)	// Checks best child's distance is less than itself then exchange them.
			{
				heap.set(j, heap.get(child));
			}
			else
				break;

			j = child;
		}
		heap.set(j, temp);
	}

	/*	Find best child function for object which their distance are less than 200.	*/
	private int minChildPrice(int ind)
	{
		int bestChild = kthChild(ind, 1);
		int k = 2;
		int pos = kthChild(ind, k);
		while ((k <= d) && (pos < heap.size())) 
		{
			if (heap.get(pos).fee < heap.get(bestChild).fee) 		// Checks which child has priority by looking their prices.
				bestChild = pos;
			pos = kthChild(ind, k++);
		}    
		return bestChild;
	}

	/*	Find best child function for object which their distance are higher than 200.	*/
	private int minChildDistance(int ind)
	{
		int bestChild = kthChild(ind, 1);
		int k = 2;
		int pos = kthChild(ind, k);
		while ((k <= d) && (pos < heap.size())) 
		{
			if(heap.get(pos).distance < 200 && heap.get(bestChild).distance < 200)		// Checks two of them's distance are less than 200.
			{
				if(heap.get(pos).fee < heap.get(bestChild).fee)
					bestChild = pos;
			}
			else if (heap.get(pos).distance < 200)			// Check one of them's distance is less than 200.
				bestChild = pos;

			else if (heap.get(pos).distance < heap.get(bestChild).distance) 		// Check which child has priority by looking distance.
				bestChild = pos;
			pos = kthChild(ind, k++);

		}  
		return bestChild;
	}
}
