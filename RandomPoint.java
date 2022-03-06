import java.util.Random;

// TODO


public class RandomPoint extends Point {
	private static long seed = 1;
	private static Random rn = new Random(1);
	

	public RandomPoint(int minX, int maxX, int minY, int maxY) {
		super((maxX - minX) * rn.nextDouble()
				, (maxY - minY) * rn.nextDouble());
		
	}
	public static void setSeed(long num) {
		RandomPoint.rn = new Random(num);
	}
	
}	
