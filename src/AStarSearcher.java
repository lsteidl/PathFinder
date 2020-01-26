import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * A* algorithm search
 * 
 * You should fill the search() method of this class.
 */
public class AStarSearcher extends Searcher {

	/**
	 * Calls the parent class constructor.
	 * 
	 * @see Searcher
	 * @param maze initial maze.
	 */
	public AStarSearcher(Maze maze) {
		super(maze);
	}

	/**
	 * Main a-star search algorithm.
	 * 
	 * @return true if the search finds a solution, false otherwise.
	 */
	public boolean search() {

		// FILL THIS METHOD

		// explored list is a Boolean array that indicates if a state associated with a given position in the maze has already been explored. 
		boolean[][] explored = new boolean[maze.getNoOfRows()][maze.getNoOfCols()];
		// ...
		for( int i = 0; i < maze.getNoOfRows(); i++){
			for( int j = 0; j < maze.getNoOfCols(); j++){
				explored[i][j] = false;
			}
		}

		PriorityQueue<StateFValuePair> frontier = new PriorityQueue<StateFValuePair>();

		// TODO initialize the root state and add
		// to frontier list
		// ...
		Square goal = maze.getGoalSquare();
		State start = new State(maze.getPlayerSquare(),null,0,0);
		int left = (int) Math.pow((start.getX()-goal.X), 2);
		int right = (int) Math.pow((start.getY()-goal.Y), 2);
		double hval = Math.sqrt(left + right);
		double startFValue = hval + start.getGValue();
		
		StateFValuePair snode = new StateFValuePair(start,startFValue);
		frontier.add(snode);

		while (!frontier.isEmpty()) {
			if(frontier.size() > maxSizeOfFrontier){
				maxSizeOfFrontier = frontier.size();
			}
			// TODO return true if a solution has been found
			State state = frontier.poll().getState();
			noOfNodesExpanded++;
			// mark node as explored
			explored[state.getX()][state.getY()] = true;
			// TODO maintain the cost, noOfNodesExpanded (a.k.a. noOfNodesExplored),
			// maxDepthSearched, maxSizeOfFrontier during
			// the search
			if(state.getDepth() > maxDepthSearched){
				maxDepthSearched = state.getDepth();
			}
			// TODO update the maze if a solution found
			if(state.isGoal(maze)){
				cost++;
				while(state.getParent().getParent() != null){
				cost++;
				maze.setOneSquare(state.getParent().getSquare(), '.');
				state = state.getParent();
				}
				//System.out.print("GOAL FOUND\n");
				return true;
			}

			// use frontier.poll() to extract the minimum stateFValuePair.
			// use frontier.add(...) to add stateFValue pairs
			ArrayList <State> successors = state.getSuccessors(explored, maze);
			boolean duplicate = false; 
			StateFValuePair remove = null;
			// iterate through each potential addition
			for( State s : successors){
				duplicate = false;
				// check entire frontier for duplicate states
						for( StateFValuePair p : frontier){
							//	if( p.getState().getSquare().equals(s.getSquare())){
								if(p.getState().equals(s)){
									// must compare gValues 
									if(s.getGValue() >= p.getState().getGValue()){
										//if g(new) >= g(old), don't add new
										duplicate = true;
										break;
									}
									else{
										//frontier.remove(p);
										remove = p;
										break;
									}
								}
						}	
				// add to queue if no duplicate found
				if(remove != null){
					frontier.remove(remove);
					remove = null;
				}
				if(!duplicate) {
					//Square goal = maze.getGoalSquare();
					left = (int) Math.pow((s.getX() - goal.X), 2);
					right = (int) Math.pow((s.getY() - goal.Y), 2);
					hval = Math.sqrt(left + right);
					double fvalue = hval + s.getGValue();
					StateFValuePair sfvp = new StateFValuePair(s,fvalue);
					frontier.add(sfvp);
				}
			} // end of outer loop
		} // end of while loop

		// TODO return false if no solution
		return false;
	}

}
