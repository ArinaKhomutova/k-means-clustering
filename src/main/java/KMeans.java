import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class KMeans {

  private List<Cluster> clusters = new ArrayList<>();
  private List<CustomPoint> points = new ArrayList<>();

  public List<Cluster> getClusters() {
    return clusters;
  }

  public void addPoint(CustomPoint customPoint) {
    points.add(customPoint);
  }

  public int getQuantityPoints() {
    return points.size();
  }

  public void clearKMeans() {
    points.clear();
    clusters.clear();
  }

  /**
   * Running the algorithm.
   *
   * @param quantity quantity clusters
   */
  public void start(int quantity) {
    generateClusters(quantity);

    do {
      for (Cluster cluster : clusters) {
        cluster.clearClusterPoints();
      }

      clustering();
    } while (next());
  }

  private void clustering() {
    for (CustomPoint point : points) {
      Cluster cluster = clusters.get(0);
      double min = point.distance(cluster.getCurrentCenter());

      for (int i = 1; i < clusters.size(); i++) {
        double distance = point.distance(clusters.get(i).getCurrentCenter());
        if (min > distance) {
          min = distance;
          cluster = clusters.get(i);
        }
      }

      cluster.addPoint(point);
    }
  }

  private boolean next() {
    boolean flag = false;

    for (Cluster cluster : clusters) {
      cluster.recalculateCenter();

      if (cluster.isMoved()) {
        flag = true;
      }
    }

    return flag;
  }

  private void generateClusters(int quantity) {
    for (int i = 0; i < quantity; i++) {
      clusters.add(new Cluster(getRandomPoint()));
    }
  }

  private CustomPoint getRandomPoint() {
    Random random = new Random();
    int index = random.nextInt(points.size());

    CustomPoint customPoint = points.get(index);
    points.remove(index);

    return customPoint;
  }
}