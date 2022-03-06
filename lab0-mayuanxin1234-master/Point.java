/**
 * CS2030S Lab 0: Point.java
 * Semester 2, 2021/22
 *
 * <p>The Point class encapsulates a point on a 2D plane.
 *
 * @author XXX
 */
class Point {
  // TODO
  public double x;
  public double y;
 	Point(double x, double y) {
	this.x = x;
	this.y = y;
	}

	public double getX(Point p1) {
		return p1.x;
	}

	public double getY(Point p1) {
		return p1.y;
	}
  
	public boolean contains(Point p, Point c, double r) {
		if(Math.pow(getX(p) - getX(c), 2) +
			       	Math.pow(getY(p) - getY(c), 2) <=
				Math.pow(r, 2)) {
			return true;
				} else {
					return false;
				}
	}

	
	@Override
	   public String toString() {
		if(this.x == (long) this.x) {
			if(this.y == (long) this.y) {
	return String.format("(%.1f, %.1f)", this.x, this.y);
			} else {
	return String.format("(%.1f, %s)", this.x, this.y);
		}
	   } else {
		   if(this.y == (long) this.y) {
	return String.format("(%s, %.1f)", this.x, this.y);
		   } else {
	return String.format("(%s, %s)", this.x, this.y);
		   }
	   }
	   }
    /*
	@Override
	public String toString() {
		return "(" + this.x + ", " + this.y + ")";
	}
	*/
    
}
