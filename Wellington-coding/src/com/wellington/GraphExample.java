package com.wellington;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class GraphExample<T> {

	private Map<T, List<T>> map = new HashMap<>();
	
	private static Map<String, Integer> holdingValues = new HashMap<>();
	
	static {
		holdingValues.put("H1", 10);
		holdingValues.put("H2", 20);
		holdingValues.put("H3", 15);
		holdingValues.put("H4", 10);
	}
	
	public GraphExample() {
		// TODO Auto-generated constructor stub
	}
	

	public void addVertex(T s) {
		map.put(s, new LinkedList<T>());
	}

	public void addEdge(T source, T destination) {

		if (!map.containsKey(source))
			addVertex(source);

		if (!map.containsKey(destination))
			addVertex(destination);

		map.get(source).add(destination);
	}

	public void getVertexCount() {
		System.out.println("The graph has " + map.keySet().size() + " vertex");
	}

	public void getEdgesCount(boolean bidirection) {
		int count = 0;
		for (T v : map.keySet()) {
			count += map.get(v).size();
		}
		if (bidirection == true) {
			count = count / 2;
		}
		System.out.println("The graph has " + count + " edges.");
	}

	public void hasVertex(T s) {
		if (map.containsKey(s)) {
			System.out.println("The graph contains " + s + " as a vertex.");
		} else {
			System.out.println("The graph does not contain " + s + " as a vertex.");
		}
	}

	public void hasEdge(T s, T d) {
		if (map.get(s).contains(d)) {
			System.out.println("The graph has an edge between " + s + " and " + d + ".");
		} else {
			System.out.println("The graph has no edge between " + s + " and " + d + ".");
		}
	}

	public void findMarketValue(T s) {

		System.out.println(map.get(s));
		
		int sum=0;
		
		for(T values:map.get(s)) {
			
			if(!map.get(values).isEmpty()) {
				
				for(T t:map.get(values)) {
					sum=(int) (sum+100*Float.valueOf(holdingValues.get(t)));
				}
			}else {
			sum=(int) (sum+100*Float.valueOf(holdingValues.get(values)));
			}
		}
		System.out.println("new values :"+sum);

	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		for (T v : map.keySet()) {
			builder.append(v.toString() + ": ");
			for (T w : map.get(v)) {
				builder.append(w.toString() + " ");
			}
			builder.append("\n");
		}

		return (builder.toString());
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		GraphExample<String> g = new GraphExample<String>();

		g.addEdge("inv1", "F1");
		g.addEdge("inv2", "F2");
		g.addEdge("inv1", "F2");
		g.addEdge("inv2", "F3");
		g.addEdge("F1", "H2");
		g.addEdge("F1", "H1");
		g.addEdge("F1", "H4");
		g.addEdge("F3", "H1");
		g.addEdge("F3", "H4");

		g.addEdge("F2", "H1");
		g.addEdge("F2", "H3");

		// Printing the graph
		System.out.println("Graph:\n" + g.toString());

		// Gives the no of vertices in the graph.
		g.getVertexCount();

		// Gives the no of edges in the graph.
		g.getEdgesCount(true);

		g.findMarketValue("F1");

		// Tells whether the edge is present or not.
		g.hasEdge("inv1", "F1");

		// Tells whether vertex is present or not
		g.hasVertex("H1");

	}

}
