import java.util.List;

public class ListAnswer {
  
  public static void squareValuesKeepOdd(List<Integer> list) {
    list.removeIf(e -> e % 2 == 0);
    list.replaceAll(e -> e*e);
  }
}