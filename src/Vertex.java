import java.util.*;


public class Vertex implements Comparable<Vertex> {

	public ArrayList<String> adjacency = new ArrayList<String>(); 
	public double distance; 
	public int xpos;  
	public int ypos; 
	public boolean known;  
	public String cityName;
	public Vertex prev; 
	public ArrayList<Double> adjDist = new ArrayList<Double>(); 
	public ArrayList<Edge> adjEdge; 
	public int index; 
	
	
	public Vertex() {
		xpos = 0; 
		ypos = 0;  
		known = false; 
		distance = Double.POSITIVE_INFINITY; 
		prev = null; 
		adjEdge = new ArrayList<Edge>();
	}
	
	public int getIndex()
	{
		return index; 
	}
	
	/*private static class Edge {
		String city; 
		double distance; 
		
		public Edge(String dest, double dist)
		{
			this.city = dest; 
			this.distance = dist; 
		}
	}*/
	
	 @Override
	 public int compareTo(Vertex other) {
		if (this.distance < other.distance) return -1; 
		if (this.distance > other.distance) return 1; 
		return 0;
	}

}
