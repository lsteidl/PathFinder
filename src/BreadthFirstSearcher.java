import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Breadth-First Search (BFS)
 * 
 * You should fill the search() method of this class.
 */
public class BreadthFirstSearcher extends Searcher {

	/**
	 * Calls the parent class constructor.
	 * 
	 * @see Searcher
	 * @param maze initial maze.
	 */
	public BreadthFirstSearcher(Maze maze) {
		super(maze);
	}

	/**
	 * Main breadth first search algorithm.
	 * 
	 * @return true if the search finds a solution, false otherwise.
	 */
	public boolean search() {
		// FILL THIS METHOD

		// explored list is a 2D Boolean array that indicates if a state associated with a given position in the maze has already been explored.
		boolean[][] explored = new boolean[maze.getNoOfRows()][maze.getNoOfCols()];

		// ...
		for( int i = 0; i < maze.getNoOfRows(); i++){
			for( int j = 0; j < maze.getNoOfCols(); j++){
				explored[i][j] = false;
			}
		}

		// Queue implementing the Frontier list
		LinkedList<State> queue = new LinkedList<State>();
		State start = new State(maze.getPlayerSquare(), null,0,0);
		queue.add(start);
		while (!queue.isEmpty()) {
			//System.out.print("while\n");
			// TODO return true if find a solution
			
			if(queue.size() > maxSizeOfFrontier){
				maxSizeOfFrontier = queue.size();
			}
			State state = queue.pop();	
			noOfNodesExpanded++;
			explored[state.getX()][state.getY()] = true;
			//System.out.print("Test : X = " + state.getX() + " Y = " + state.getY() + "\n");
			
			if(state.getDepth() > maxDepthSearched){
				maxDepthSearched = state.getDepth();
			}
			if(state.isGoal(maze)){
				cost++;
				while(state.getParent().getParent() != null){
				cost++;
				maze.setOneSquare(state.getParent().getSquare(), '.');
				state = state.getParent();
				}
			//	System.out.print("GOAL FOUND\n");
				return true;
			}
			// TODO maintain the cost, noOfNodesExpanded (a.k.a. noOfNodesExplored),
			// maxDepthSearched, maxSizeOfFrontier during
			// the search
			
			// TODO update the maze if a solution found

			// use queue.pop() to pop the queue.
			// use queue.add(...) to add elements to queue
			ArrayList <State> successors = state.getSuccessors(explored, maze);
			boolean duplicate = false; 
			for( int i = 0; i < successors.size(); i++){
				duplicate = false;
				// check entire queue for duplicate
				for( int j = 0; j < queue.size(); j++){
					if((queue.get(j).getSquare()).equals((successors.get(i).getSquare()))){
						duplicate = true;
						continue;
					}
				}
				// add to queue if no duplicate found
				if(!duplicate) 
					queue.add(successors.get(i));
			}
		}

		// TODO return false if no solution
		return false;
	}
}
