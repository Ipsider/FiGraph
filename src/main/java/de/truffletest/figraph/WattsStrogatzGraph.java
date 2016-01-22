package main.java.de.truffletest.figraph;
import java.util.Random;

/**
 * @author PS, DT
 *
 */
public class WattsStrogatzGraph extends Graph<String> {

	/**
	 * @param n
	 * @param k
	 * @param p
	 */
	public WattsStrogatzGraph(int n, int k, double p) {
		if ((n <= 0) || (k <= 0) || (0 > p || p > 1)) {
			throw new IllegalArgumentException("Invalid value. Constraints are n > 0, k > 0 and 0 <= p <= 1.");
		}
		Random random = new Random();
		for (int i = 0; i < n; i++) {
			addNode(Integer.toString(i));
		}
		for (int i = 0; i < n; i++) {
			Node<String> nodeX = this.getNode(Integer.toString(i));
			for (int j = 1; j <= k; j++) {
				Node<String> nodeY = this.getNode(Integer.toString((i + j) % n));
				if( Math.random() > p ) {
					nodeX.addEdge(nodeY);
					nodeY.addEdge(nodeX);
				} else {
					int randomIndexY = random.nextInt(getAdjacencyMap().size());
					nodeY = this.getNode(Integer.toString(randomIndexY));
					nodeX.addEdge(nodeY);
					nodeY.addEdge(nodeX);
				}
			}
		}
	}
}
