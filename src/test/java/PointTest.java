import junit.framework.TestCase;
import org.junit.Test;

public class PointTest {
    CustomPoint firstPoint = new CustomPoint(3, 6);
    CustomPoint secondPoint = new CustomPoint(6, 3);

    @Test
    public void pointTest() {
        double expected = 4.242640687119285;
        double actual = firstPoint.distance(secondPoint);

        TestCase.assertEquals(expected, actual);

        TestCase.assertFalse(firstPoint.equals(secondPoint));
    }
}
