import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;


public class Kruskal {

	public static ArrayList<Vertex> vertexSet = new ArrayList<Vertex>(); 
	public static ArrayList<Edge> totalEdgeSet = new ArrayList<Edge>(); 
	public static HashMap<String, Vertex> vertexHash = new HashMap<String, Vertex>(1000);
	public static int vertexCount = 0; 
	public static int edgeCount = 0; 
	public static BinaryHeap<Edge> pq;  
	public static DisjSets ds; 

	public Kruskal(BufferedReader cities) throws IOException{

		String line = cities.readLine(); 
		int count = 0; 
		while(line != null)
		{
			StringTokenizer st = new StringTokenizer(line);
			while (st.hasMoreTokens())
			{
				String city = st.nextToken(); //City Name 
				int x = Integer.parseInt(st.nextToken()); //x coord
				int y = Integer.parseInt(st.nextToken()); //y coord
				Vertex v = new Vertex(); 
				count++; 
				v.index = count; 
				v.cityName = city; 
				v.xpos = x; 
				v.ypos = y; 
				vertexHash.put(city, v); 
				vertexSet.add(v); 
				vertexCount++; 
			}

			line = cities.readLine(); 
		}
		

		DisjSets dj = new DisjSets(edgeCount); 

		for(int i=0; i<vertexSet.size(); i++)
		{
			Vertex ver1 = vertexSet.get(i); 

			for(int j=i+1; j<vertexSet.size(); j++)
			{
				Vertex v2 = vertexSet.get(j); 
				double x = Math.pow((ver1.xpos-v2.xpos), 2); 
				double y = Math.pow((ver1.ypos-v2.ypos), 2);
				double d = Math.sqrt((x+y)); 
				Edge e = new Edge(ver1.cityName, v2.cityName, d);
				totalEdgeSet.add(e); 
			}
		}
	}
	
	public static void testData() 
	{
		System.out.println("EdgesList Count: " + totalEdgeSet.size());
		System.out.println("VertexCount: " + vertexCount); 
		int edgeCount = (vertexCount * (vertexCount -1)) / 2; //expected edge count... (complete graph) 
		System.out.println("Expected EdgeCount (n*(n-1) / 2): " + edgeCount); 
	
		for(int i=0; i<vertexSet.size(); i++)
		{
			System.out.println(i + " " + vertexSet.get(i).cityName + " " +vertexSet.get(i).index);
			System.out.println("HashTest " + vertexHash.get(vertexSet.get(i).cityName).cityName + " " + vertexHash.get(vertexSet.get(i).cityName).index);
		}
		System.out.println("-------Edges--------");
		
		for(int i=0; i<totalEdgeSet.size()/3; i++)
		{
			System.out.println(totalEdgeSet.get(i).begin+ " " + totalEdgeSet.get(i).end+ " " + totalEdgeSet.get(i).distance); 
		}
		
	}
	
	
	public static ArrayList<Edge> kruskal(ArrayList<Edge> edges, int numVertices)
	{
		//mst represents the end result minimum spanning tree
		ArrayList<Edge> mst = new ArrayList<Edge>(); 
		
		
		ds = new DisjSets(numVertices+1); 
		
		Edge[] ed = new Edge[edges.size()]; 
		
		for(int i=0; i<edges.size(); i++)
			ed [i] = edges.get(i); 
		
		pq = new BinaryHeap<Edge>(ed); 
		
		int whileCount = 0; 
		while(mst.size() != numVertices -1)
		{
			whileCount++; 
			//System.out.println(whileCount); 
			Edge e = (Edge) pq.deleteMin(); 
			int uset = ds.find(vertexHash.get(e.begin).getIndex()); 
			int vset = ds.find(vertexHash.get(e.end).getIndex());
			
			if(uset != vset)
			{
				mst.add(e); 
				ds.union(uset, vset); 
			}
		}
		
		return mst; 
	}
	public static ArrayList<Vertex> getVertList()
	{
		return vertexSet;  
	}
	
	public static ArrayList<Edge> getEdgesList()
	{
		return totalEdgeSet;  
	}
	
	public static int getVertCount()
	{
		return vertexCount;  
	}
	
	public static HashMap<String, Vertex> getHash()
	{
		return vertexHash;  
	}

}
