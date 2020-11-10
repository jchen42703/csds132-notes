import java.util.List;

public class CollectionAnswer {
  
  public static double sum(List<Double> list) {
    double total = 0.0;
    
    for (Double x : list) {
      total += x;
    }
    
    return total;
  }
}