package main.java.de.truffletest.figraph;
/**
 * @author Philipp Seiter
 *
 * @param <T>
 */
public class Edge<T extends Comparable<T>> {

	private Node<T> nodeX;
	private Node<T> nodeY;
	private double weight;
	
	/**
	 * @param nodeX
	 * @param nodeY
	 */
	public Edge(Node<T> nodeX, Node<T> nodeY) {
		super();
		this.nodeX = nodeX;
		this.nodeY = nodeY;
		this.weight = 1;
	}
	
	/**
	 * @param nodeX
	 * @param nodeY
	 * @param weight
	 */
	public Edge(Node<T> nodeX, Node<T> nodeY, double weight) {
		super();
		this.nodeX = nodeX;
		this.nodeY = nodeY;
		this.weight = weight;
	}

	/**
	 * @return
	 */
	public Node<T> getNodeX() {
		return nodeX;
	}

	/**
	 * @param nodeX
	 */
	public void setNodeX(Node<T> nodeX) {
		this.nodeX = nodeX;
	}

	/**
	 * @return
	 */
	public Node<T> getNodeY() {
		return nodeY;
	}

	/**
	 * @param nodeY
	 */
	public void setNodeY(Node<T> nodeY) {
		this.nodeY = nodeY;
	}

	/**
	 * @return
	 */
	public double getWeight() {
		return weight;
	}

	/**
	 * @param weight
	 */
	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	
}
