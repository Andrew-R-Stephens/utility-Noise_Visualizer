import java.util.ArrayList;

public class World {

	ArrayList<ArrayList<Chunk>> world = new ArrayList<ArrayList<Chunk>>();
			
	public void expandH() {
		world.add(new ArrayList<Chunk>());
	}
	
	public void expandW(Chunk c) {
		world.get(world.size()-1).add(c);
	}

	public ArrayList<ArrayList<Chunk>> getWorld(){
		return world;
	}
	
}

class Chunk {

	double data;
	
	public Chunk(double data) {
		this.data = data;
	}
	
	public double getData() {
		return data;
	}
	
}
