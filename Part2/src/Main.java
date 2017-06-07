

import java.util.*;


public class Main
{
	public static void main(String[] args) 
	{
		Scanner scn = new Scanner(System.in);		// Asks n.
		int n = getInput();			// Take n as an input.

		Random rnd = new Random();
		int a = 1000 - rnd.nextInt(2000);	// Creates coordinates of Alice, a and b randomly.
		int b = 1000 - rnd.nextInt(2000);	

		ArrayList<Point> places = new ArrayList<Point>();
		
		addPlaces(places,a,b);			// Creates 100 places randomly and add to ArrayList.
		
		BinaryHeapPoint bhp = new BinaryHeapPoint();

		addBinaryHeap(places, bhp);		// Adds places to Binary Heap.
		
		printOutput(bhp, n, scn);			// Prints places.
	}
	private static void addPlaces(ArrayList<Point> places,int a, int b)		// Creates 100 places randomly and add them into ArrayList.
	{
		for(int i = 0; i<100 ; i++)
		{
			Point abc = new Point(a, b);
			abc.firstIndex = (i+1);	
			places.add(abc);
		}
		
	}
	private static void addBinaryHeap(ArrayList<Point> places, BinaryHeapPoint bhp)		// Adds 100 places to Binary Heap.
	{
		for(int i = 0 ; i < 100 ; i++)
		{
			bhp.insert(places.get(i));
		}
	}
	private static void printOutput(BinaryHeapPoint bhp, int n, Scanner scn)				// Checks whether n is higher than 100 and prints places.
	{
		while(n > 100)		// Checks whether n is higher than 100
		{
//			throw new IndexOutOfBoundsException("There are 100 places.");
			System.out.println("There are 100 places. Please enter valid value again:");
			n = scn.nextInt(); 
		}
		if(n == 0)
		{
			System.out.println("You just typed 0.");
		}
		else		// Prints places.
		{
			System.out.println("The booster distances are found!");
			for(int i = 0 ; i < n ; i++)
			{
				System.out.println(i+1 + "th nearest distance calculated with the " + bhp.findMin().firstIndex  + "th generated coordinate is " + bhp.findMin().distance);
				System.out.println("Coordinates of touristic place is (" + bhp.findMin().x + "," + bhp.findMin().y +"), location fee is " +  bhp.findMin().fee + "\n");
				if(bhp.heap.size() == 1)
					System.out.println(" ");
				else
					bhp.deleteMin();
			}
		}
	}
    private static int getInput()
    {
		Scanner scn = new Scanner(System.in);
		System.out.println("Please type a number of places which you want to go.");
		return scn.nextInt();

    }
}

