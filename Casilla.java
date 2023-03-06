package puzzlezigzag;

public class Casilla {

	public int i;
	public int j;
	
	public Casilla (int i, int j) {
		this.i = i;
		this.j = j;
	}
	
	public String toString() {
		return i + "," + j;
	}
}