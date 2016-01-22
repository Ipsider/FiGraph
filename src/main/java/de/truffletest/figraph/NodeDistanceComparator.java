package main.java.de.truffletest.figraph;
import java.util.*;

/**
 * @author PS, DT
 *
 */
public class NodeDistanceComparator<T> implements Comparator<Node> {
	
	@Override
	public int compare(Node nodeX, Node nodeY) {
		if (nodeX == null || nodeY == null) {
			throw new NullPointerException("compared nodes are null.");
		}
		if (nodeX.getDistance() < nodeY.getDistance())
        {
            return -1;
        }
		if (nodeX.getDistance() > nodeY.getDistance())
        {
            return 1;
        }
        return 0;
	}
}
