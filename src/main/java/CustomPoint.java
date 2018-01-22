public class CustomPoint {

  private int x;
  private int y;

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public CustomPoint(int x, int y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Distance to the point "customPoint".
   *
   * @param customPoint Up to this point.
   * @return Distance from current point to point "customPoint".
   */
  public double distance(CustomPoint customPoint) {
    return Math.sqrt(
        Math.pow((customPoint.x - x), 2) + Math.pow((customPoint.y - y), 2)
    );
  }

  /**
   * Compares the current point to the point "customPoint".
   *
   * @param customPoint With this point.
   * @return true if the position of the points is equal and false if not equal.
   */
  public boolean equals(CustomPoint customPoint) {
    return customPoint.x == x && customPoint.y == y;
  }

  public String toString() {
    return "(" + x + ": " + y + ")";
  }
}