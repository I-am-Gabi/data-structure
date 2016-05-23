package ads.lab8;

import ads.graph.DiGraph;
import ads.graph.UnDiGraph;
import junit.framework.TestCase;

/**
 * @version 08/05/16.
 */
public class CyclesTest extends TestCase {

    public void testHasCycleDiGraph() throws Exception {
        DiGraph a = new DiGraph(11);
        a.addEdge(0,9);
        a.addEdge(0,5);
        a.addEdge(1,3);
        a.addEdge(1,8);
        a.addEdge(2,6);
        a.addEdge(2,4);
        a.addEdge(5,9);
        a.addEdge(5,7);
        a.addEdge(7,9);
        System.out.println("a has cycle: " + Cycles.hasCycle(a));
        assertFalse(Cycles.hasCycle(a));

        DiGraph b = new DiGraph(9);
        b.addEdge(0,5);
        b.addEdge(0,8);
        b.addEdge(1,3);
        b.addEdge(1,7);
        b.addEdge(3,7);
        b.addEdge(2,4);
        b.addEdge(4,6);
        b.addEdge(6,2);
        assertTrue(Cycles.hasCycle(b));

        DiGraph c = new DiGraph(11);
        c.addEdge(0,9);
        c.addEdge(0,5);
        c.addEdge(1,3);
        c.addEdge(1,8);
        c.addEdge(2,6);
        c.addEdge(2,4);
        c.addEdge(5,7);
        c.addEdge(7,9);
        c.addEdge(9,5);
        assertTrue(Cycles.hasCycle(c));

        DiGraph d = new DiGraph(3);
        d.addEdge(0,1);
        d.addEdge(0,2);
        d.addEdge(1,2);
        assertFalse(Cycles.hasCycle(d));
    }

    public void testHasCycleUnDiGraph() throws Exception {
        UnDiGraph d = new UnDiGraph(11);
        d.addEdge(1,9);
        d.addEdge(1,5);
        d.addEdge(0,3);
        d.addEdge(0,8);
        d.addEdge(2,6);
        d.addEdge(2,4);
        d.addEdge(5,9);
        d.addEdge(5,7);
        d.addEdge(7,9);
        assertTrue(Cycles.hasCycle(d));

        UnDiGraph e = new UnDiGraph(10);
        e.addEdge(0,5);
        e.addEdge(0,8);
        e.addEdge(0,4);
        e.addEdge(1,7);
        e.addEdge(3,7);
        e.addEdge(2,4);
        e.addEdge(8,3);
        e.addEdge(6,9);
        assertFalse(Cycles.hasCycle(e));

        UnDiGraph f = new UnDiGraph(10);
        f.addEdge(0,3);
        f.addEdge(3,5);
        f.addEdge(5,6);
        f.addEdge(6,0);
        assertTrue(Cycles.hasCycle(f));
    }
}