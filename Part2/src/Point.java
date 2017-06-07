

import java.util.Random;

public class Point 
{
	Random rnd = new Random();
	int x,y;		// Coordinates of place, (x,y)
	int distance;	// Distance between Alice and place.
	int fee;		// Price of place.
	int firstIndex;	// To keep generating order.
	
	public Point(int a, int b) 
	{
		x = 1000 - rnd.nextInt(2000);
		y = 1000 - rnd.nextInt(2000);
		distance = findDistance(x,y,a,b);
		fee = rnd.nextInt(60);
		firstIndex = this.firstIndex;
	}
	
	public static int findDistance(int x1, int y1, int a, int b)	// Calculate distance between Alice and place.
	{
		int distance;
		distance = (int) Math.sqrt(Math.pow((x1-a), 2) + Math.pow((y1-b), 2));
		return distance;
	}


}
