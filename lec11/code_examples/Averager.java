/** A class that averages a bunch of numbers entered by a user on the command line.
  * The code is not that practical since it is doing an integer average, and 
  *  but it is a good example of error handling since there are lots of exceptions
  *  that can occur.
  */
public class Averager {
  
  /**
   * Inputs strings represeting numbers, and returns the average
   * @param values an array of string representing numbers
   * @return the average of the input numbers
   */
  public static int average(String[] values) throws BadDataException {
    int sum = 0;                 // the total summed so far
    int count = 0;               // the number of values summed
    boolean badData = false;     // is there bad data in the values?           
    
    // add up each value into a sum
    for (int i = 0; i < values.length; i++) {
      try {
        sum += Integer.parseInt(values[i]);
        count++;
      }
      catch (NumberFormatException e) {
        // try again as a double
        try {
          sum += (int)(Double.parseDouble(values[i]) + 0.5);
          count++;
        }
        catch (NumberFormatException e2) {
          // it is not an int or double so skip this value
          badData = true;
        }
      }
    }
    
    // if there was bad values, use our exception to report that while still reporting the average
    if (badData) {
      throw new BadDataException(sum / count);
    }
    // otherwise, return the average
    return sum / count;
  }
  
  /**
   * Run the averager and print out the results.
   * @param args the command line arguments should be the numbers to be averaged
   */
  public static void main(String[] args) {
    // Call the average method.  There are two exceptions that can be returned,
    // a ArithmeticException for a divide by 0 or a BadDataException if a non number entered.
    try {
      int average = average(args);
      System.out.println("The integer average is " + average);
    }
    catch (ArithmeticException e) {
      System.out.println("You entered no numbers.  Please enter numbers.");
    }
    catch (BadDataException e) {
      System.out.println("You entered a non number.  The average of the numbers is " + e.getValue());
    }
  }
}
