import java.util.Random;
import java.util.Scanner;

/*
 * generate nodes at random
 * get transmission range
 * determine whether graph is connected
 * if not connected state which components are connected
 * 
 * 
 * ask user for width x and length y
 * 
 * ask for transmission range
 * ** watch out for int division,  use floats or real numbers
 * 
 * determine if nodes are connected individually
 * 
 * determine if nodes are connected as a whole e.g. if the graph is connected (use bfs)
 * 
 * 
 * create adjacency list of graph and ask if it is connected using bfs or dfs
 * 
 * extra credit, visualize graph network
 */
public class SensorNetwork {
    static Random rnd = new Random();
    static int vertexCount = 0;

    public static void main(String []args){
	Scanner input = new Scanner(System.in);



	System.out.println("Enter width: ");
	int width = input.nextInt();
	System.out.println("Enter height: ");
	int height = input.nextInt();
	System.out.println("Enter number of nodes: ");
	int nVerts = input.nextInt();
	System.out.println("Enter transmission range: ");
	int range = input.nextInt();

	Graph g = new Graph(width,height,nVerts);

	


	for(int i = 0; i<nVerts; i++){
	    String s = "" + i;
	    g.addVertex(s, rnd.nextInt(width), rnd.nextInt(height));
	}
	for(int i = 0; i<g.vertexList.length; i++){
	    for(int j = 0; j< g.vertexList.length; j++){
		if(i!=j && isConnected(g.vertexList[i],g.vertexList[j], range) )
		    g.addEdge(i, j);
	    }
	}
	System.out.println("Transmission range: "+ range);
	g.DFSALL();
	g.printAdjList();
	g.printVertexList();
	

	



    }
   


public static Vertex generateNode(int width, int height){
    String s = "" + vertexCount++;
    int x = rnd.nextInt(width);
    int y = rnd.nextInt(height);
    Vertex v = new Vertex(s,x,y);
    return v;
}
public static boolean isConnected(Vertex v, Vertex w,int range){
    int dx = v.getx() - w.getx();
    int dy = v.gety() - w.gety();
    double distance = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy,2));

    return (range >= distance);
}

}




























































