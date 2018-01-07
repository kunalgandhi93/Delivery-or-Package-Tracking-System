package application;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;



public class TestDijkstraAlgorithm {

	private List<Vertex> nodes;
	private List<Edge> edges;

	public LinkedList<Vertex> testExcute(int source,int destination) {
		nodes = new ArrayList<Vertex>();
		edges = new ArrayList<Edge>();
		for (int i = 0; i < 48; i++) {
			Vertex location = new Vertex( i+"", "" + i);
			nodes.add(location);
		}

		addLane("Edge_0", 0, 1, 1);
		addLane("Edge_1", 1, 2, 1);
		addLane("Edge_2", 2, 3, 1);
		addLane("Edge_3", 3, 4, 1);
		addLane("Edge_4", 4, 5, 1);
		addLane("Edge_5", 5, 6, 1);
		addLane("Edge_6", 6, 7, 1);
		addLane("Edge_7", 7, 8, 1);
		addLane("Edge_8", 8, 9, 1);
		addLane("Edge_9", 9, 10, 1);
		addLane("Edge_10", 10, 11, 1);
		addLane("Edge_11", 11, 12, 1);
		addLane("Edge_12", 12, 13, 1);
		addLane("Edge_13", 13, 14, 1);
		addLane("Edge_14", 14, 15, 1);
		addLane("Edge_15", 15, 16, 1);
		addLane("Edge_16", 16, 17, 1);
		addLane("Edge_17", 17, 18, 1);
		addLane("Edge_18", 18, 19, 1);
		addLane("Edge_19", 19, 20, 1);
		addLane("Edge_20", 20, 21, 1);
		addLane("Edge_21", 21, 22, 1);
		addLane("Edge_22", 22, 23, 1);
		addLane("Edge_23", 23, 24, 1);
		

		addLane("Edge_24", 1, 0, 1);
		addLane("Edge_25", 2, 1, 1);
		addLane("Edge_26", 3, 2, 1);
		addLane("Edge_27", 4, 3, 1);
		addLane("Edge_28", 5, 4, 1);
		addLane("Edge_29", 6, 5, 1);
		addLane("Edge_30", 7, 6, 1);
		addLane("Edge_31", 8, 7, 1);
		addLane("Edge_32", 9, 8, 1);
		addLane("Edge_33", 10, 9, 1);
		addLane("Edge_34", 11, 10, 1);
		addLane("Edge_35", 12, 11, 1);
		addLane("Edge_36", 13, 12, 1);
		addLane("Edge_37", 14, 13, 1);
		addLane("Edge_38", 15, 14, 1);
		addLane("Edge_39", 16, 15, 1);
		addLane("Edge_40", 17, 16, 1);
		addLane("Edge_41", 18, 17, 1);
		addLane("Edge_42", 19, 18, 1);
		addLane("Edge_43", 20, 19, 1);
		addLane("Edge_44", 21, 20, 1);
		addLane("Edge_45", 22, 21, 1);
		addLane("Edge_46", 23, 22, 1);
		addLane("Edge_47", 24, 23, 1);
		// Lets check from location Loc_1 to Loc_10
		Graph graph = new Graph(nodes, edges);
		DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(graph);
		dijkstra.execute(nodes.get(source));
		LinkedList<Vertex> path = dijkstra.getPath(nodes.get(destination));

		

		return path;

	}

	private void addLane(String laneId, int sourceLocNo, int destLocNo,
			int duration) {
		Edge lane = new Edge(laneId,nodes.get(sourceLocNo), nodes.get(destLocNo), duration );
		edges.add(lane);
	}
}
