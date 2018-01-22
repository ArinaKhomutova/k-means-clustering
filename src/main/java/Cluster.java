import java.util.ArrayList;
import java.util.List;

public class Cluster {

  private CustomPoint lastCenter;
  private CustomPoint currentCenter;
  private List<CustomPoint> clusterPoints;

  public List<CustomPoint> getClusterPoints() {
    return clusterPoints;
  }

  public CustomPoint getCurrentCenter() {
    return currentCenter;
  }

  /**
   * Create a new cluster at the point.
   *
   * @param customPoint Cluster center.
   */
  public Cluster(CustomPoint customPoint) {
    lastCenter = customPoint;
    currentCenter = customPoint;
    clusterPoints = new ArrayList<>();
  }

  /**
   * Add a point to the cluster.
   *
   * @param customPoint Added point.
   */
  public void addPoint(CustomPoint customPoint) {
    clusterPoints.add(customPoint);
  }

  /**
   * Clear cluster points.
   */
  public void clearClusterPoints() {
    clusterPoints.clear();
  }

  /**
   * Was the center of the cluster moving.
   *
   * @return true if the cluster is moved and false if the cluster is not moved.
   */
  public boolean isMoved() {
    return !currentCenter.equals(lastCenter);
  }

  /**
   * Recalculates the cluster center.
   */
  public void recalculateCenter() {
    int size = clusterPoints.size();

    if (size == 0) {
      return;
    }

    int sumX = 0;
    int sumY = 0;

    for (CustomPoint point : clusterPoints) {
      sumX += point.getX();
      sumY += point.getY();
    }

    int newX = sumX / size;
    int newY = sumY / size;

    lastCenter = currentCenter;
    currentCenter = new CustomPoint(newX, newY);
  }
}