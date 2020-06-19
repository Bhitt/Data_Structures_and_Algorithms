package Graph_Search_BFS_DFS;

import java.util.LinkedList;


public class GraphSearch {
	/*
		Purpose: utiize breadth-first search and depth-first search
		to traverse a directed graph
	*/

	public enum State {
		Unvisited, Visited, Visiting;
	} 

	public static void main(String a[])
	{
		Graph g = createNewGraph();
		Node[] n = g.getNodes();
		Node start = n[3];
		Node end = n[5];
		//mark nodes as unvisited
		markUnvisited(g);
		//DFS
		System.out.println("Depth-First Search: ");
		depthFirstSearch(start);
		System.out.println("\n");
		//mark nodes as unvisited again
		markUnvisited(g);
		//BFS
		System.out.println("Breadth-First Search: ");
		breadthFirstSearch(start);
		System.out.println("\n");
	}

	public static Graph createNewGraph()
	{

		/*



		  b	<-	a	<-	d  ->  e
			    |			   |
			    v 			   v
			    c	           f 



		*/

		Graph g = new Graph();        
		Node[] temp = new Node[6];

		temp[0] = new Node("a", 2);
		temp[1] = new Node("b", 0);
		temp[2] = new Node("c", 0);
		temp[3] = new Node("d", 2);
		temp[4] = new Node("e", 1);
		temp[5] = new Node("f", 0);

		temp[0].addAdjacent(temp[1]);
		temp[0].addAdjacent(temp[2]);
		temp[3].addAdjacent(temp[0]);
		temp[3].addAdjacent(temp[4]);
		temp[4].addAdjacent(temp[5]);
		for (int i = 0; i < 6; i++) {
			g.addNode(temp[i]);
		}
		return g;
	}

	public static void visit(Node n){
		System.out.print(n.getVertex()+" ");
	}

	public static void markUnvisited(Graph g){
		//mark all nodes as unvisited
		for(Node u: g.getNodes()){
			u.state = State.Unvisited;
		}
	}

	/*
		Depth-First Search
		-Visit a node A and then iterate through each of A's neighbors. When
		 visiting a node B that is a neighbor of A, we visit all of B's neighbors
		 before going on to A's other neighbors.

		-Typically used if we want to visit every node in the graph

		-This implementation only searches and prints each node, there is no target
	*/
	public static void depthFirstSearch(Node n){
		//check for null
		if(n == null) return;
		//visit the node first
		visit(n);
		//mark the node as visited to avoid infinite loops
		n.state = State.Visited;
		for(Node node : n.getAdjacent()){
			if(node.state == State.Unvisited){
				depthFirstSearch(node);
			}
		}
	}


	/*
		Breadth-First Search
		-Visit a node A and then visit each of A's neighbors before visiting
		 any of their neighbors.

		-Utilizes a queue instead of recursion

		-Usually used to find the shortest path

		-This implementation only searches and prints nodes without a target
	*/
	public static void breadthFirstSearch(Node root){
		//Create a Queue
		LinkedList<Node> q = new LinkedList<Node>();
		//Mark the first node as visited and add it to the queue
		root.state = State.Visiting;
		q.add(root);
		//process the search using the queue
		Node u;
		while(!q.isEmpty()){
			//remove a node from the queue
			u = q.removeFirst();
			if(u != null){
				//get all of its adjacent nodes (children)
				for(Node v : u.getAdjacent()){
					//if they have been visited already ignore, otherwise
					if(v.state == State.Unvisited){
						/*
							you would normally check for destination here, in our case
							we just add them to the queue to be visited
						*/
						v.state = State.Visiting;
						q.add(v);
					}
				}
				//mark the original node as visited
				visit(u);
				u.state = State.Visited;
			}
		}
	}
}