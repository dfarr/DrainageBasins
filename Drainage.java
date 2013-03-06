import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.Arrays;
import java.util.Collections;

class Drainage{

	public static void main(String[] args) {

		int S = 4;
		int map[][] = {{2, 3, 4, 5}, {8, 1, 2, 3}, {9, 9, 9, 4}, {3, 2, 4, 3}};

		//int S = 3;
		//int map[][] = {{1, 5, 2}, {2, 5, 7}, {3, 6, 9}};

		// Initialize the verified data structure
		List<List<Point>> verified = new ArrayList<List<Point>>();

		// locate all sinks
		for (int x=0; x<S; x++) {
			for (int y=0; y<S; y++) {
				
				boolean sink = true;
				Point point = new Point(x, y);
				for (Point pt : point.findNeighbouringPoints(S)) {
					if(map[pt.x][pt.y] < map[x][y]) {
						sink = false;
						break;
					}
				}

				if(sink) {
					List<Point> newSink = new ArrayList<Point>();
					newSink.add(new Point(x,y));
					verified.add(newSink);
				}
			}
		}

		// loop through the verified list
		for(List<Point> basin : verified) {

			// the other required data structures
			Map<Point, Integer> unverified = new HashMap<Point, Integer>();
			List<Point> denied = new ArrayList<Point>();
			List<Point> temp = new ArrayList<Point>();

			// take the first point (the sink)
			Point sink = basin.get(0);
			for(Point pt : sink.findNeighbouringPoints(S)) {
				unverified.put(pt, map[sink.x][sink.y]);
			}

			// loop through unverified
			while(!unverified.isEmpty()) {

				Set<Point> keys = unverified.keySet();
				for(Point point : keys) {

					boolean check = true;

					for(Point pt : point.findNeighbouringPoints(S)) {
						if(map[pt.x][pt.y] < unverified.get(point)) {
							check = false;
							denied.add(pt);
							break;
						}
					}

					if(check) {
						basin.add(point);
						temp.add(point);
					}
				}
				unverified.clear();

				// move all neighbours of points in temp to unverified
				// if they have not been checked already AND
				// the neighbouring point is higher than the point in temp
				for(Point point : temp) {
					for(Point pt : point.findNeighbouringPoints(S)) {

						if(!basin.contains(pt) && !denied.contains(point) && map[pt.x][pt.y] > map[point.x][point.y]) {
							unverified.put(pt, map[point.x][point.y]);
						}
					}
				}
				temp.clear();
			}
		}

		// count the number of points in each basin
		// and print in descending order
		Integer[] size = new Integer[verified.size()];
		for(int i=0; i<verified.size(); i++) {
			size[i] = verified.get(i).size();
		}

		Arrays.sort(size, Collections.reverseOrder());
		for(int i=0; i<size.length; i++) {
			System.out.print(size[i] + " ");
		}
		System.out.println();
	}
}