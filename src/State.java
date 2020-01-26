import java.util.ArrayList;

/**
 * A state in the search represented by the (x,y) coordinates of the square and
 * the parent. In other words a (square,parent) pair where square is a Square,
 * parent is a State.
 * 
 * You should fill the getSuccessors(...) method of this class.
 * 
 */
public class State {

	private Square square;
	private State parent;

	// Maintain the gValue (the distance from start)
	// You may not need it for the BFS but you will
	// definitely need it for AStar
	private int gValue;

	// States are nodes in the search tree, therefore each has a depth.
	private int depth;

	/**
	 * @param square
	 *            current square
	 * @param parent
	 *            parent state
	 * @param gValue
	 *            total distance from start
	 */
	public State(Square square, State parent, int gValue, int depth) {
		this.square = square;
		this.parent = parent;
		this.gValue = gValue;
		this.depth = depth;
	}

	/**
	 * @param visited
	 *            explored[i][j] is true if (i,j) is already explored
	 * @param maze
	 *            initial maze to get find the neighbors
	 * @return all the successors of the current state
	 */
	public ArrayList<State> getSuccessors(boolean[][] explored, Maze maze) {
		// FILL THIS METHOD
		// X is vertical, Y is horizontal
		ArrayList <State> successors = new ArrayList <State>();
		// TODO check all four neighbors in left, down, right, up order
		// Left
		if( ( explored[getX()][getY()-1] == false) && (maze.getSquareValue(getX(), getY()-1) != '%')){
			Square leftsquare = new Square(getX(),getY()-1);
			State left = new State(leftsquare, this, this.gValue+1, this.depth+1);
			successors.add(left);
			//System.out.print("add left\n");
		}
		//Down
		if( (explored[getX()+1][getY()] == false) && (maze.getSquareValue(getX()+1, getY()) != '%')){
			Square downsquare = new Square(getX()+1, getY());
			State down = new State(downsquare, this, this.gValue+1, this.depth+1);
			successors.add(down);
			//System.out.print("add down\n");
		}
		//Right
		if((explored[getX()][getY()+1] == false) && (maze.getSquareValue(getX(),  getY()+1) != '%')){
			Square rightsquare = new Square(getX(),  getY()+1);
			State right = new State(rightsquare, this, this.gValue+1, this.depth+1);
			successors.add(right);
			//System.out.print("add right\n");
		}
		//Up
		if((explored[getX()-1][getY()] == false) && (maze.getSquareValue(getX()-1, getY()) != '%')){
			Square upsquare = new Square(getX()-1,getY());
			State up = new State(upsquare, this, this.gValue+1, this.depth+1);
			successors.add(up);
			//System.out.print("add up\n");
		}
		return successors;	
		// TODO remember that each successor's depth and gValue are
		// +1 of this object.
	}

	/**
	 * @return x coordinate of the current state
	 */
	public int getX() {
		return square.X;
	}

	/**
	 * @return y coordinate of the current state
	 */
	public int getY() {
		return square.Y;
	}

	/**
	 * @param maze initial maze
	 * @return true is the current state is a goal state
	 */
	public boolean isGoal(Maze maze) {
		if (square.X == maze.getGoalSquare().X
				&& square.Y == maze.getGoalSquare().Y)
			return true;

		return false;
	}

	/**
	 * @return the current state's square representation
	 */
	public Square getSquare() {
		return square;
	}

	/**
	 * @return parent of the current state
	 */
	public State getParent() {
		return parent;
	}

	/**
	 * You may not need g() value in the BFS but you will need it in A-star
	 * search.
	 * 
	 * @return g() value of the current state
	 */
	public int getGValue() {
		return gValue;
	}

	/**
	 * @return depth of the state (node)
	 */
	public int getDepth() {
		return depth;
	}
	public boolean equals(State other) {
		if(getSquare().equals(other.square)){
			return true;
		}
		else return false;
	   
	}
}
