
// in this case we are displaying a Binary Search tree  
// reference problem 4.38 of Weiss to compute tree node x,y positions

// input is a text file name that will form the Binary Search Tree

//     java DisplaySimpleTree textfile


import javax.sound.sampled.Line;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.*;
import javax.swing.JPopupMenu;

public class GUI extends JFrame {
	JScrollPane scrollpane;
	DisplayPanel panel;

	JMenuBar menuBar;
	JMenu menu; 

	public GUI(ArrayList<Vertex> x, HashMap<String,Vertex> y, ArrayList<Edge> edges) {
		panel = new DisplayPanel(x, y, edges);
		panel.setPreferredSize(new Dimension(300, 300));
		scrollpane = new JScrollPane(panel); 

		getContentPane().add(scrollpane, BorderLayout.CENTER);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		pack();  // cleans up the window panel

	}
}
class DisplayPanel extends JPanel {


	public ArrayList<Vertex> x; 
	public ArrayList<Edge> minTree; 
	public  HashMap y; 
	int xs;
	int ys;
	public static String source; 
	public static String dest; 
	public static Graphics gg; 
	public static ArrayList<Vertex> path; 
	public boolean regraph = false; 
	public static Vertex mySource; 
	public static Vertex destin; 

	public DisplayPanel(ArrayList<Vertex> a, HashMap<String,Vertex> b, ArrayList<Edge> mst) {
		this.x = a; // allows dispay routines to access the tree
		this.y = b; 
		this.minTree = mst; 
		setBackground(Color.white);
		setForeground(Color.black);
	}


	public void paintComponent(Graphics g) { 	

		g.setColor(getBackground()); //colors the window
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(getForeground()); //set color and fonts
		Font MyFont = new Font("SansSerif",Font.PLAIN,15);
		g.setFont(MyFont);
		xs=100;   //where to start printing on the panel
		ys=20;
		g.drawString("Kruskal's Algorithm & Minimum Spanning Tree \n",xs,ys);
		ys=ys+10;;
		int start=0;
		MyFont = new Font("SansSerif",Font.BOLD,10); //bigger font for tree
		g.setFont(MyFont);
		this.drawTree(g, this.x, this.minTree, this.y); // draw the tree  
		revalidate(); 

	}

	public void drawTree(Graphics g, ArrayList<Vertex> x, ArrayList<Edge> mst, HashMap<String,Vertex> hash) {//actually draws the tree

		int dx, dy, dx2, dy2;
		int SCREEN_WIDTH=300; //screen size for panel
		int SCREEN_HEIGHT=500;
		int XSCALE, YSCALE;  
		XSCALE=SCREEN_WIDTH;  //scale x by total nodes in tree
		YSCALE=SCREEN_HEIGHT; //scale y by tree height
		
		BinaryHeap<Edge> heaps = new BinaryHeap<Edge>(); 
		for(int i=0; i<mst.size(); i++)
		{
			Edge e = mst.get(i); 
			heaps.insert(e); 
		}

		for(int i=0; i<x.size(); i++) { // inorder traversal to draw each node

			Vertex p = x.get(i); 
			String s = x.get(i).cityName; 

			dx = x.get(i).xpos /3; // get x,y coords., and scale them 
			dy = x.get(i).ypos /3 ;
			dy = 500-dy;  
			g.drawString(s, dx, dy); 
			g.fillRect(dx, dy, 5, 5); 
		}


		for(int j=0; j<mst.size(); j++)
		{
			Edge e = heaps.deleteMin(); 
			Vertex v1 = hash.get(e.begin); 
			Vertex v2 = hash.get(e.end); 
			int x1 = v1.xpos / 3; 
			int y1 = v1.ypos / 3;
			y1 = 500 - y1; 

			int x2 = v2.xpos / 3; 
			int y2 = v2.ypos / 3;
			y2 = 500 - y2; 

			g.drawLine(x1,y1, x2, y2); 
		}
	}

}



