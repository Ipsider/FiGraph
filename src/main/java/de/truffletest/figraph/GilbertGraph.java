package main.java.de.truffletest.figraph;

// TODO implement constructor with unspecific type.
/**
 * @author Philipp Seiter
 *
 */
public class GilbertGraph extends Graph<String>{

	/**
	 * @param n
	 * @param p
	 */
	public GilbertGraph(int n, double p) {
		if ((n <= 0) || (0 > p || p > 1)) {
			throw new IllegalArgumentException("Invalid value. Constraints are n > 0 and 0 <= p <= 1.");
		}
		for (int i = 0; i < n; i++) {
			addNode(Integer.toString(i));
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (i < j) {
					if( Math.random() < p ) {
						addUndirectedEdge(Integer.toString(i), Integer.toString(j));
					}
				}
			}
		}
	}
}
