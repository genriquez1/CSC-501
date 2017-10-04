
public class Vertex {
    
    String label;
    boolean wasVisited;
    int x;
    int y;
    
    public Vertex(String lab){
	label = lab;
	wasVisited = false;
	x = 0;
	y = 0;
    }
    public Vertex(String lab, int posx, int posy){
	label = lab;
	wasVisited = false;
	x = posx;
	y = posy;
    }
    public int getx(){
	return x;
    }
    public int gety(){
	return y;
    }
    

}
