import java.io.*;
import java.util.List;
import java.util.ArrayList;

class Point {
	
	public int x;
	public int y;

	public Point(int _x, int _y) {

		x = _x;
		y = _y;
	}

	public List<Point> findNeighbouringPoints(int max) {

		List<Point> neighbours = new ArrayList<Point>();

		for(int i=-1; i<=1; i++) {
			for(int j=-1; j<=1; j++) {

				if(this.x+i >= 0 && this.x+i < max && this.y+j >= 0 && this.y+j < max) {
					neighbours.add(new Point(this.x+i, this.y+j));
				}
			}
		}

		neighbours.remove(this);
		return neighbours;
	}

	public boolean equals(Object obj) {

		if(obj == null || obj.getClass() != this.getClass()) {
			return false;
		}

		Point pt = (Point)obj;
		if(pt.x == this.x && pt.y == this.y) {
			return true;
		}

		return false;
	}

	public String toString() {

		return "(" + this.x + ", " + this.y + ")";
	}
}