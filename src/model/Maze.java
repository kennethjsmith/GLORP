package model;

import java.util.Stack;

/**
 * The maze has all the rooms in a 2D array with a buffer around the border.
 * @author 
 *
 */
public class Maze {
	//connect all rooms in grid with blocked passages
	//DFS
	private Room[][] myMaze;
	final int WIDTH = 3;
	final int LENGTH = 3;
	final int BORDER_BUFFER = 2;
	
	public Maze() {
		myMaze = new Room[WIDTH+BORDER_BUFFER][LENGTH+BORDER_BUFFER];
		addRooms();
		connectRooms();
	}
	
	//TODO: room factory? start, win, item rooms etc
	public void addRooms() {
		for(int i = 1; i <= LENGTH; i++) {
			for(int j = 1; j <= WIDTH; j++) {
				myMaze[i][j] = new Room();	
			}
		}
	}
	
	public void connectRooms() {
		for (int i=1;i<=LENGTH;i++){
	        for (int j=1;j<=WIDTH;j++){
	            if (i<LENGTH)
	                myMaze[i][j].mySouthPassage.myNextRoom = myMaze[i+1][j];
	            if (i>1)
	            	myMaze[i][j].myNorthPassage.myNextRoom = myMaze[i-1][j];
	            if (j<9)
	            	myMaze[i][j].myEastPassage.myNextRoom = myMaze[i][j+1];
	            if (j>1)
	            	myMaze[i][j].myWestPassage.myNextRoom = myMaze[i][j-1];
	        }   
	    }
	}
	//TODO: make the grid into a maze
	/*
	//ref: https://en.wikipedia.org/wiki/Maze_generation_algorithm#Randomized_depth-first_search - iteratice implementation
	public void createPassages() {
		//Choose the initial cell, mark it as visited and push it to the stack
		Stack<Room> visitedRooms = new Stack<>();
		int inCurrentXCoordinate = 1;
		int inCurrentYCoordinate = 1;
		Room inCurrentRoom = myMaze[inCurrentYCoordinate][inCurrentXCoordinate];
		inCurrentRoom.setVisitedFlag(); 
		visitedRooms.push(inCurrentRoom);
		//While the stack is not empty
		while(!visitedRooms.isEmpty()) {
			//Pop a cell from the stack and make it a current cell
			inCurrentRoom = visitedRooms.pop();
			//If the current cell has any neighbors which have not been visited
			if(hasUnvisitedNeighborRoom(inCurrentXCoordinate, inCurrentYCoordinate)) {
				//Push the current cell to the stack
				visitedRooms.push(inCurrentRoom);
				//Choose one of the unvisited neighbors
				//Remove the wall between the current cell and the chosen cell
				
				
			}
		}
		
	}
	
	public boolean hasUnvisitedNeighborRoom(int theXCoordinate, int theYCoordinate) {
		Room inNorthNeighbor = myMaze[theYCoordinate-1][theXCoordinate];
		Room inSouthNeighbor = myMaze[theYCoordinate+1][theXCoordinate];
		Room inWestNeighbor = myMaze[theYCoordinate][theXCoordinate-1];
		Room inEastNeighbor = myMaze[theYCoordinate][theXCoordinate+1];

		if(inNorthNeighbor != null || !inNorthNeighbor.isVisited()) return true;
		else if (inSouthNeighbor != null || !inSouthNeighbor.isVisited()) return true;
		else if (inWestNeighbor != null || !inWestNeighbor.isVisited()) return true;
		else if (inEastNeighbor != null || !inEastNeighbor.isVisited()) return true;
		else return false;
	}
	*/
	
}
