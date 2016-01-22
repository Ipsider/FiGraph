package main.java.de.truffletest.figraph;
import java.util.*;

/**
 * @author PS, DT
 *
 * @param <T>				the type of the data held by the node
 */
public class Graph<T extends Comparable<T>> {

	private LinkedHashMap<T, Node<T>> adjacencyMap;

	/**
	 * 
	 */
	public Graph() {
		super();
		this.adjacencyMap = new LinkedHashMap<>();
	}
	
	/**
	 * @param adjacencyMap	the adjacency list
	 */
	public Graph(LinkedHashMap<T, Node<T>> adjacencyMap) {
		super();
		if (adjacencyMap == null)
            this.adjacencyMap = new LinkedHashMap<>();
		this.adjacencyMap = adjacencyMap;
	}

	/**
	 * @return				the adjacency list
	 */
	public HashMap<T, Node<T>> getAdjacencyMap() {
		return adjacencyMap;
	}

	/**
	 * @param adjacencyMap	the adjacency list
	 */
	public void setAdjacencyMap(LinkedHashMap<T, Node<T>> adjacencyMap) {
		this.adjacencyMap = adjacencyMap;
	}

	/**
	 * @param vertex
	 * @return
	 */
	public boolean addNode(T vertex) {
        if (vertex == null) {
            throw new NullPointerException();
        } else if (this.adjacencyMap.containsKey(vertex)) {
        	return false;
        } else {
        	Node<T> newNode = new Node<T>(vertex);
        	this.adjacencyMap.put(vertex, newNode);
            return true;
        }
	}
	
	/**
	 * @param vertex
	 * @return
	 */
	// TODO remove edges from other nodes to the node containing this vertex.
	public boolean removeNode(T vertex) {
		if (!this.adjacencyMap.containsKey(vertex)) {
			return false;
		}
		this.adjacencyMap.remove(vertex);
		return true;
	}
	
	/**
	 * @param vertex
	 * @return
	 */
	public Node<T> getNode(T vertex) {
		return this.adjacencyMap.get(vertex);
	}
	
	/**
	 * @param vertexX		the node from which the edge points
	 * @param vertexY		the node towards the edge points
	 * @return
	 */
	public boolean addDirectedEdge(T vertexX, T vertexY) {
		if (!this.adjacencyMap.containsKey(vertexX) || !this.adjacencyMap.containsKey(vertexY))
			throw new IllegalArgumentException("Vertex argument does not exist in this graph.");
		Node<T> nodeX = getNode(vertexX);
		Node<T> nodeY = getNode(vertexY);
		nodeX.addEdge(nodeY, 1);
		return true;
	}
	
	/**
	 * @param vertexX
	 * @param vertexY
	 * @return
	 */
	public boolean addUndirectedEdge(T vertexX, T vertexY) {
		if (!this.adjacencyMap.containsKey(vertexX) || !this.adjacencyMap.containsKey(vertexY))
			throw new IllegalArgumentException("Vertex argument does not exist in this graph.");
		Node<T> nodeX = getNode(vertexX);
		Node<T> nodeY = getNode(vertexY);
		nodeX.addEdge(nodeY, 1);
		nodeY.addEdge(nodeX, 1);
		return true;
	}
	
	/**
	 * @param vertexX
	 * @param vertexY
	 * @param weight
	 * @return
	 */
	public boolean addUndirectedEdge(T vertexX, T vertexY, int weight) {
		if (!this.adjacencyMap.containsKey(vertexX) || !this.adjacencyMap.containsKey(vertexY))
			throw new IllegalArgumentException("Vertex does not exist in this graph.");
		Node<T> nodeX = getNode(vertexX);
		Node<T> nodeY = getNode(vertexY);
		nodeX.addEdge(nodeY, weight);
		nodeY.addEdge(nodeX, weight);
		return true;
	}
	
	/**
	 * @param vertexX
	 * @param vertexY
	 * @return
	 */
	public boolean removeDirectedEdge(T vertexX, T vertexY) {
		if (!this.adjacencyMap.containsKey(vertexX) || !this.adjacencyMap.containsKey(vertexY))
			return false;
			//throw new IllegalArgumentException("Vertex argument does not exist in this graph.");
		Node<T> nodeX = getNode(vertexX);
		Node<T> nodeY = getNode(vertexY);
		nodeX.removeEdge(nodeY);
		return true;
	}
		
	/**
	 * @param vertexX
	 * @param vertexY
	 * @return
	 */
	public boolean removeUndirectedEdge(T vertexX, T vertexY) {
		if (!this.adjacencyMap.containsKey(vertexX) || !this.adjacencyMap.containsKey(vertexY))
			return false;
		Node<T> nodeX = getNode(vertexX);
		Node<T> nodeY = getNode(vertexY);
		nodeX.removeEdge(nodeY);
		nodeY.removeEdge(nodeX);
		return true;
	}
	
	/**
	 * @param vertexX
	 * @param vertexY
	 * @return
	 */
	public boolean hasDirectedEdge(T vertexX, T vertexY) {
		if (!getAdjacencyMap().containsKey(vertexX) || !getAdjacencyMap().containsKey(vertexY)) {
			return false;
		}
		return getNode(vertexX).hasEdge(getNode(vertexY));
	}
	
	/**
	 * @param vertexX
	 * @param vertexY
	 * @return
	 */
	public boolean hasUndirectedEdge(T vertexX, T vertexY) {
		if (!getAdjacencyMap().containsKey(vertexX) || !getAdjacencyMap().containsKey(vertexY)) {
			return false;
		} else if (getNode(vertexX).hasEdge(getNode(vertexY)) && getNode(vertexY).hasEdge(getNode(vertexX))){
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 
	 */
	public void printGraph() {
		for (T vertexX : this.adjacencyMap.keySet()) {
			for (T vertexY : this.adjacencyMap.keySet()) {
				if (hasDirectedEdge(vertexX, vertexY)) {
					System.out.print(1 + " ");
				} else {
					System.out.print(0 + " ");
				}
			}
			System.out.print("\n");
		}
	}
	
	/**
	 * @param vertexX
	 * @param vertexY
	 * @return
	 */
	public List<T> dijkstra(T vertexX, T vertexY) {
		if (!adjacencyMap.containsKey(vertexX) || !adjacencyMap.containsKey(vertexY)) {
			throw new IllegalArgumentException("Graph does not contain vertex.");
		}
		
		Comparator<Node> comparator = new NodeDistanceComparator();
        PriorityQueue<Node<T>> queue = new PriorityQueue<Node<T>>(comparator);
		
		// reset the graph.
		List<Node<T>> nodes = new ArrayList<Node<T>>(adjacencyMap.values());
    	for (Node<T> node : nodes) {
    		node.setParentNode(null);
            node.setVisited(false);
            node.setDistance(Double.POSITIVE_INFINITY);
            //queue.add(node);
    	}
    	
    	Node<T> startNode = getNode(vertexX);
    	startNode.setDistance(0);
		queue.add(startNode);
		
		while (!queue.isEmpty()) {
			Node<T> thisNode = queue.remove();
			// if the node is reached, return its predecessors in reverse.
			if (thisNode.getVertex().compareTo(vertexY) == 0) {
				Node<T> thatNode = getNode(vertexY);
				List<T> path = new ArrayList<>();
				while (thatNode.getParentNode() != null) {
					path.add(thatNode.getVertex());
					thatNode = thatNode.getParentNode();
				}
				Collections.reverse(path);
				return path;
			}
			// core algorithm.
			List<Node<T>> neighborNodes = thisNode.getNeighborNodes();
			for (Node<T> neighborNode : neighborNodes) {
				Edge<T> edge = thisNode.getEdgeTo(neighborNode.getVertex());
				double alt = thisNode.getDistance() + edge.getWeight();
				if (alt < neighborNode.getDistance()) {
					neighborNode.setDistance(alt);
					neighborNode.setParentNode(thisNode);
					if (!queue.contains(neighborNode.getVertex())) {
						queue.add(neighborNode);
					}
				}
			}
		}
		return null;
	}
	
	/**
	 * @return
	 */
	public double calcAveragePathLength() {
		// TODO do less than 100 if graph is smaller.
		double averagePathLength = 0;
		double pathLengthSum = 0;
	    Random random = new Random();
		for (int i = 0; i < 100; i++) {
			int randomIndexX = random.nextInt(adjacencyMap.size());
			int randomIndexY = random.nextInt(adjacencyMap.size());
			while (randomIndexX == randomIndexY) {
				randomIndexY = random.nextInt(adjacencyMap.size());
			}
			List<T> vertices = new ArrayList<T>(adjacencyMap.keySet());
			T vertexA = vertices.get(randomIndexX);
			T vertexB = vertices.get(randomIndexY);
			List<T> shortestPath = dijkstra(vertexA, vertexB);
			if (shortestPath != null) {
				pathLengthSum += shortestPath.size();
			}
		}
		averagePathLength = pathLengthSum/100;
		return averagePathLength;
	}
	
	// TODO define for nodes with d(i) < 2. 0, 1 or undefined?
	/**
	 * @return
	 */
	public double calcClusteringCoefficient() {		
		List<Node<T>> nodes = new ArrayList<Node<T>>(adjacencyMap.values());
		int counter = 0;
		double sum = 0;
		double clusteringCoefficient;
		for (int i = 0; i < adjacencyMap.size(); i++) {
			
			Node<T> nodeX = nodes.get(i);
			List<Node<T>> neighborNodes = nodeX.getNeighborNodes();
			int numberOfNeighborNodes = neighborNodes.size();
			if (numberOfNeighborNodes >= 2) {
				counter++;
				int potentialNeighborPairs = (numberOfNeighborNodes -1) * numberOfNeighborNodes;
				int actualNeighborPairs = 0;
				double localClusteringCoefficient = 0;
				for (int j = 0; j < numberOfNeighborNodes; j++) {
					for (int k = 0; k < numberOfNeighborNodes; k++) {
						Node<T> nodeY = neighborNodes.get(j);
						Node<T> nodeZ = neighborNodes.get(k);
						if (nodeY.hasEdge(nodeZ) && nodeZ.hasEdge(nodeY)) {
							actualNeighborPairs++;
						}
					}
				}
				localClusteringCoefficient = (double) actualNeighborPairs / potentialNeighborPairs;
				sum += localClusteringCoefficient;
			}
		}
		clusteringCoefficient = sum / counter;
		return clusteringCoefficient;
	}
}
