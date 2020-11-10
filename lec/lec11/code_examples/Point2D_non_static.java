import java.util.Comparator;

/** Points in two dimensions */
public class Point2D {
  private double x;
  private double y;
  
  /**
   * Create a 2D point
   * @param x the x coordinate
   * @param y the y coordinate
   */
  public Point2D(double x, double y) {
    this.x = x;
    this.y = y;
  }
  
  /**
   * Get the x-coordinate of the point
   * @return the x-coordinate
   */
  public double getX() {
    return x;
  }
  
  /**
   * Get the y-coordinate of the point
   * @return the y-coordinate
   */
  public double getY() {
    return y;
  }
  
  /** 
   * Change the x-coordinate of the point
   * @param x the new x-coordinate
   */
  public void setX(double x) {
    this.x = x;
  }
  
  /**
   * Change the y-coordinate of the point
   * @param y the new y-coordinate
   */
  public void setY(double y) {
    this.y = y;
  }
  
  /**
   * Return a comparator that compares two points by Manhattan distance
   * @return a comparator for the points
   */
  public static Comparator<Point2D> compareByManhattanDistance() {
    return new CompareByManhattanDistance();
  }
  
  /** A class that is a Comparator for points, comparing them by Manhattan distance */
  private static class CompareByManhattanDistance implements Comparator<Point2D> {
    
    /** Compute the Manhattan distance for a point */
    private double manhattanDistance(Point2D point) {
      return Math.abs(point.getX()) + Math.abs(point.getY());
    }
    
    /**
     * Compares two points by Manhattan distance
     * @param point1 the first point
     * @param point2 the second point
     * @return <0, =0, >0, if the first point has less, equal, or greater Manhattan distance from the origin than the second point
     */
    public int compare(Point2D point1, Point2D point2) {
      double point1Dist = manhattanDistance(point1);
      double point2Dist = manhattanDistance(point2);
      
      if (point1Dist < point2Dist)
        return -1;
      else if (point1Dist > point2Dist)
        return 1;
      else
        return 0;
    }
  }
  
  /**
   * Return a comparator the compares two points by there Euclidean distance from this point.
   * @return a comparator for two points
   */
  public Comparator<Point2D> compareByDistanceFromHere() {
    return new CompareByDistanceFromHere();
  }
  
  /** A class that is a Comparator.  Compares two points by their Euclidean distance from a set point. */
  private class CompareByDistanceFromHere implements Comparator<Point2D> {
    
    /** Computes the Euclidean distance between two points */
    private double euclideanDistance(Point2D pointA, Point2D pointB) {
      return (pointA.getX() - pointB.getX()) * (pointA.getX() - pointB.getX()) +
             (pointA.getY() - pointB.getY()) * (pointA.getY() - pointB.getY());
    }
    
    /**
     * Compares two points by their Euclidean distance from a set point, "this" point
     * @param point1 the first point to compare
     * @param point2 the second point to compare
     * @return <0, =0, >0 if the first point is closer, the same distance, or further from "this" point by Euclidean distance.
     */
    public int compare(Point2D point1, Point2D point2) {
      double point1Dist = euclideanDistance(point1, Point2D.this);
      double point2Dist = euclideanDistance(point2, Point2D.this);
      
      if (point1Dist < point2Dist)
        return -1;
      else if (point1Dist > point2Dist)
        return 1;
      else
        return 0;
    }
  }
  
}