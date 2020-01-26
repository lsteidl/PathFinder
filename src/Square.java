/**
 * Represents a simple square.
 * 
 * You do not need to change this class.
 */
public class Square {
	final public int X;
	final public int Y;

	public Square(int x, int y) {
		this.X = x;
		this.Y = y;
	}

public boolean equals(Square other) {
	if((this.X == other.X) && (this.Y == other.Y)){
		return true;
	}
	else return false;
   
}
}