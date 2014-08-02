import java.util.ArrayList;

public class Edge implements Comparable<Edge>{
	String begin; 
	String end; 
	double distance; 
	
	public Edge(String begin, String dest, double dist)
	{
		this.begin = begin; 
		this.end = dest; 
		this.distance = dist; 
	}
	
	 @Override
	 public int compareTo(Edge other) {
		if (this.distance < other.distance) return -1; 
		if (this.distance > other.distance) return 1; 
		return 0;
	}
}
