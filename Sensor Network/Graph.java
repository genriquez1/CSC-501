import java.util.ArrayList;

public class Graph {
    private final int MAX_VERTS = 100;
    Vertex vertexList[];
    private int adjMat[][];
    private ArrayList<ArrayList<Integer>> adjList;
    public int nVerts;
    private int nEdges;
    private int MAX = Integer.MAX_VALUE;// effective infinity for Graph purpose
    private final static int INFINITY = Integer.MAX_VALUE;
    private QueueX q;
    private StackX stack;
    static int rows;
    static int cols;

    public Graph(){
	vertexList = new Vertex[MAX_VERTS];
	adjMat= new int [MAX_VERTS][MAX_VERTS];
	adjList = new ArrayList<ArrayList<Integer>>();
	for(int i = 0; i< MAX_VERTS; i++){
	    adjList.add(new ArrayList<Integer>());
	}
	nVerts = 0;
	nEdges = 0;
	for(int i = 0 ; i < MAX_VERTS; i++){	
	    for(int j = 0; j< MAX_VERTS; j++){
		adjMat[i][j] = 0;
	    }
	}
    }
    public Graph(int n){
	rows = n;
	cols = n;
	vertexList = new Vertex[n];
	adjMat= new int [n][n];
	adjList = new ArrayList<ArrayList<Integer>>();
	for(int i = 0; i< n; i++){
	    adjList.add(new ArrayList<Integer>());
	}
	nVerts = 0;
	nEdges = 0;
	for(int i = 0 ; i < n; i++){	
	    for(int j = 0; j< n; j++){
		adjMat[i][j] = 0;
	    }
	}
    }
    public Graph(int rows, int cols, int nodes){
	this.cols = cols;
	this.rows = rows;
	vertexList = new Vertex[nodes];
	adjMat = new int [nodes][nodes];
	adjList = new ArrayList<ArrayList<Integer>>();
	for(int i = 0; i< nodes; i++){
	    adjList.add(new ArrayList<Integer>());
	}
	nVerts = 0;
	nEdges = 0;
	for(int row = 0 ; row < nodes; row++){	
	    for(int col = 0; col< nodes; col++){
		adjMat[row][col] = 0;
	    }
	}

    }
    public void addVertex(String label, int x, int y){
	vertexList[nVerts++] = new Vertex(label,x,y);

    }
    public void addEdge(int start, int end){
	adjMat[start][end] = 1;
	adjMat[end][start] = 1;
	adjList.get(start).add(end);
	//adjList.get(end).add(start);
	
	
    }
    public void displayVertex(int v){
	System.out.print(vertexList[v].label + " ");
    }
    public int getAdjUnvisitedVertex(int v){
	for(int j = 0; j<nVerts; j++)
	    if(adjMat[v][j] == 1 && vertexList[j].wasVisited == false)
		return j;
	return -1;

    }
    public static boolean isConnected(Vertex v, Vertex w,int range){
	int dx = v.getx() - w.getx();
	int dy = v.gety() - w.gety();
	double distance = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy,2));

	return (range >= distance);
    }
    public boolean isConnected(){
	int visitCount = 0;
	stack = new StackX(nVerts);

	vertexList[0].wasVisited = true;
	visitCount++;
	displayVertex(0);
	stack.push(0);

	while( !stack.isEmpty() ){
	    int v = getAdjUnvisitedVertex( stack.peek() );
	    if(v == -1)
		stack.pop();
	    else{
		vertexList[v].wasVisited = true;
		visitCount++;
		displayVertex(v);
		stack.push(v);
	    }
	}
	System.out.println();
	return visitCount == nVerts;

    }
    public void DFS(){
	stack = new StackX(nVerts);

	vertexList[0].wasVisited = true;
	displayVertex(0);
	stack.push(0);

	while( !stack.isEmpty() ){
	    int v = getAdjUnvisitedVertex( stack.peek() );
	    if(v == -1)
		stack.pop();
	    else{
		vertexList[v].wasVisited = true;
		displayVertex(v);
		stack.push(v);
	    }
	}

	for(int j = 0; j<nVerts; j++)
	    vertexList[j].wasVisited = false;


    }
    public void DFSALL(){
	int visitCount = 0;
	stack = new StackX(nVerts);
	
	
	System.out.println("Connected Sets");
	for(int i = 0; i< vertexList.length; i++){
	    
	    if(vertexList[i].wasVisited == false){
		vertexList[i].wasVisited = true;
		visitCount++;
		displayVertex(i);
		stack.push(i);

		while( !stack.isEmpty() ){
		    int v = getAdjUnvisitedVertex( stack.peek() );
		    if( v == -1)
			stack.pop();
		    else{
			vertexList[v].wasVisited = true;
			visitCount++;
			displayVertex(v);
			stack.push(v);
		    }
		}
		System.out.println();
		if( i == 0 && visitCount == nVerts){
		    System.out.println("Graph is connected");
		}
		
	    }


	}
    }
    public void BFS(){
	q = new QueueX(nVerts);

	vertexList[0].wasVisited = true;
	displayVertex(0);
	q.insert(0);
	int v2;

	while( !q.isEmpty() ){
	    int v1 = q.remove();

	    while( (v2 = getAdjUnvisitedVertex(v1) ) != -1){
		vertexList[v2].wasVisited = true;
		displayVertex(v2);
		q.insert(v2);
	    }
	}

	for(int j = 0; j < nVerts; j++){
	    vertexList[j].wasVisited = false;
	}

    }
    public void printVertexList(){
	for(int i = 0; i< nVerts; i++){

	    System.out.println("Node" +vertexList[i].label + " X:" +vertexList[i].getx() + " Y: " +vertexList[i].gety());
	}
    }

    public void printAdjMat(){
	for(int i = 0; i < nVerts; i++){
	    for(int j = 0; j < nVerts; j++){
		if(adjMat[i][j] == 0)
		    System.out.print(" " + " ");
		else   
		    System.out.print(adjMat[i][j] + " " );

	    }
	    System.out.println();
	}
    }
    public void printAdjList(){
	System.out.println("Adjacency Lists: ");
	for(int i = 0; i<adjList.size(); i++){
	    System.out.print( "Node " + i + " : ");
	    for(int j = 0; j< adjList.get(i).size();j++){
		System.out.print(adjList.get(i).get(j) + " ");
	    }
	    System.out.println();
	}
    }
}
