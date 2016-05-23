package ads.lab8;

import ads.graph.UnDiGraph;
import junit.framework.TestCase;

import java.util.Arrays;

/**
 * @version 08/05/16.
 */
public class ConnectedComponentsTest extends TestCase {

    public void testFind() throws Exception {
        UnDiGraph a = new UnDiGraph(11);
        a.addEdge(0,9);
        a.addEdge(0,5);
        a.addEdge(1,3);
        a.addEdge(1,8);
        a.addEdge(2,6);
        a.addEdge(2,4);
        a.addEdge(4,2);
        a.addEdge(5,9);
        a.addEdge(5,7);
        a.addEdge(7,9);
        int[] cc =  ConnectedComponents.find(a);
        int[] expected_output = { 1, 2, 3, 2, 3, 1, 3, 1, 2, 1, 4 };
        assertTrue(Arrays.equals(expected_output, cc));

        UnDiGraph b = new UnDiGraph(4);
        b.addEdge(1,2);
        cc =  ConnectedComponents.find(b);
        expected_output = new int[]{1, 2, 2, 3};
        assertTrue(Arrays.equals(expected_output, cc));

        UnDiGraph c = new UnDiGraph(11);
        c.addEdge(2,5);
        c.addEdge(2,1);
        c.addEdge(1,5);

        c.addEdge(0,6);
        c.addEdge(0,4);
        c.addEdge(0,3);
        c.addEdge(6,4);
        c.addEdge(4,3);

        c.addEdge(8,9);
        cc =  ConnectedComponents.find(c);
        expected_output = new int[]{1, 2, 2, 1, 1, 2, 1, 3, 4, 4, 5 };
        assertTrue(Arrays.equals(expected_output, cc));
    }
}