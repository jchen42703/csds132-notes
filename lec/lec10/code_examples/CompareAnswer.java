import java.util.LinkedList;
import java.util.NoSuchElementException;

public class CompareAnswer {
  
  public static <T extends Comparable<? super T>> T max(LinkedList<T> list) throws NoSuchElementException {
    if (list.size() == 0)
      throw new NoSuchElementException();
    
    T largestSoFar = list.getFirst();
    
    for (T element : list) {
      if (element.compareTo(largestSoFar) > 0)
        largestSoFar = element;
    }
    
    return largestSoFar;
  }
}