/** We created an exception that allows us to report if there is bad data
  * while still letting us "return" the value of a computation
  */
public class BadDataException extends Exception {
 
  // this stores the value of some computation
  private double value;
  
  /**
   * Create an exception indication that there is a problem with the data
   * @param value  the value of the computation igonring the bad data
   */
  public BadDataException(double value) {
    this.value = value;
  }
  
  /**
   * Return the value of a computation ignoring bad data
   * @return the value of a computation
   */
  public double getValue() {
    return value;
  }
}