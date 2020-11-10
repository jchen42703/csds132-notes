/* The class that represents some measurement */
public class Measurement extends Object implements Comparable<Measurement> {
  
  // the quantity of this measurement
  private double quantity;
  
  /* a constructor that creates an instance with a given quantity */
  public Measurement(double quantity) {
    this.quantity = quantity;
  }
  
  /* get the quantity value */
  public double getQuantity() {
    return this.quantity;
  }
  
  /* change the quantity value */
  public void setQuantity(double quantity) {
    this.quantity = quantity;
  }
  
  /* returns < 0 if this measurements is smaller, = 0 if the same, and > 0 if larger than the input measurement */
  public int compareTo(Measurement m) {
    if (this.getQuantity() < m.getQuantity())
      return -1;
    else if (this.getQuantity() > m.getQuantity())
      return 1;
    else
      return 0;
  }
}