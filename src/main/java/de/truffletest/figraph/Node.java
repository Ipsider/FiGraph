package main.java.de.truffletest.figraph;
import java.util.*;

/**
 * @author Philipp Seiter
 *
 * @param <T>				the type of the data held by the node
 */
public class Node<T extends Comparable<T>> {
	
	private T vertex;
	private List<Edge<T>> edges;
	
	// Dijkstra meta informations.
	private Node<T> parentNode;
	private boolean isVisited;
	private double distance;
		
	/**
	 * @param data			the data held by the node
	 */
	public Node(T data) {
		super();
		this.vertex = data;
		this.edges = new ArrayList<>();
	}
	
	/**
	 * @return				the data held by the node
	 */
	public T getVertex() {
		return vertex;
	}

	/**
	 * @param	data		the data held by the node
	 */
	public void setVertex(T vertex) {
		this.vertex = vertex;
	}
	
	/**
	 * @return				the edges pointing towards the neighbors of the node
	 */
	public List<Edge<T>> getEdges() {
		return edges;
	}

	/**
	 * @param	edges		the edges pointing towards the neighbors of the node
	 */
	public void setEdges(List<Edge<T>> edges) {
		this.edges = edges;
	}

	/**
	 * @return				the parent node of the node
	 */
	public Node<T> getParentNode() {
		return parentNode;
	}

	/**
	 * @param parentNode	the parent node of the node
	 */
	public void setParentNode(Node<T> parentNode) {
		this.parentNode = parentNode;
	}

	/**
	 * @return				indicates if the node was already visited by an algorithm
	 */
	public boolean isVisited() {
		return isVisited;
	}

	/**
	 * @param isVisited		indicates if the node was already visited by an algorithm
	 */
	public void setVisited(boolean isVisited) {
		this.isVisited = isVisited;
	}
	
	/**
	 * @return
	 */
	public double getDistance() {
		return distance;
	}

	/**
	 * @param distance
	 */
	public void setDistance(double distance) {
		this.distance = distance;
	}
	
	/**
	 * @param vertex
	 * @return
	 */
	public Edge<T> getEdgeTo(T vertex) {
		for (Edge<T> edge : this.edges) {
			if (edge.getNodeY().getVertex().compareTo(vertex) == 0) {
				return edge;
			}
		}
		throw new NullPointerException();
	}
	
	/**
	 * @param node
	 * @return
	 */
	public boolean addEdge(Node<T> node) {
//		if (hasEdge(node)) {
//            return false;
//        }
		Edge<T> edge = new Edge<>(this, node, 1);
        return edges.add(edge);	
	}
	
	/**
	 * @param node
	 * @param weight
	 * @return
	 */
	// TODO catch if edge already exists.
	public boolean addEdge(Node<T> node, int weight) {
//		if (hasEdge(node)) {
//            return false;
//        }
		Edge<T> edge = new Edge<>(this, node, weight);
        return edges.add(edge);	
	}
	
	/**
	 * @param node
	 * @return
	 */
	public boolean removeEdge(Node<T> node) {
		for (Edge<T> edge : edges) {
			if (edge.getNodeY() == node) {
				this.edges.remove(edge);
				return true;
			}
		}
		return false;
    }
	
	/**
	 * @param nodeY
	 * @return
	 */
	public boolean hasEdge(Node<T> nodeY) {
    	for (Edge<T> edge : this.edges) {
    		if (edge.getNodeX() == this && edge.getNodeY() == nodeY) {
    			return true;
    		}
    	}
    	return false;
	}
	
	/**
	 * @return
	 */
	public List<Node<T>> getNeighborNodes() {
		List<Node<T>> neighborNodes = new ArrayList<>(); 
		for (Edge<T> edge : edges) {
			neighborNodes.add(edge.getNodeY());
		}
		return neighborNodes;
	}
}
