import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;


public class Test {

	public static void main(String[] args) throws IOException
	{
		String city = args[0]; //Data Set File
		BufferedReader cities = new BufferedReader(new FileReader(new File(city)));
		
		Kruskal kru = new Kruskal(cities); 
		
		//kru.testData(); 
		
		//Run the Algorithm 
		ArrayList<Edge> mst = kru.kruskal(kru.getEdgesList(), kru.getVertCount());
		
		System.out.println("-----------------------------------"); 
		System.out.println("Edge Pairs in Minimum Spanning Tree"); 
		System.out.println("----------------------------------=");
		for(int i =0; i<mst.size(); i++)
		{
			int j = i+1; 
			System.out.println("("+ j + ") " +mst.get(i).begin + " & " + mst.get(i).end); 
		}
		
		//Display the MinTree
		GUI dt = new GUI(kru.getVertList(),kru.getHash(),mst);//get a display panel
		dt.setVisible(true);
		
	}
}
