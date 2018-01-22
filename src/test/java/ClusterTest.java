import junit.framework.TestCase;
import org.junit.Test;

public class ClusterTest {
    Cluster cluster = new Cluster(new CustomPoint(5, 5));

    @Test
    public void clusterTest(){
        cluster.addPoint(new CustomPoint(2, 4));
        cluster.addPoint(new CustomPoint(5, 2));
        cluster.addPoint(new CustomPoint(1, 7));
        cluster.addPoint(new CustomPoint(3, 2));
        cluster.addPoint(new CustomPoint(5, 8));
        cluster.recalculateCenter();

        boolean actual = cluster.isMoved();

        TestCase.assertTrue(actual);
    }
}
