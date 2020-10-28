public class GenArray {
  
  public static <T> void replace(T[] list, T oldE, T newE) {
    for (int i = 0; i < list.length; i++) {
      if (list[i].equals(oldE))
        list[i] = newE;
    }
  }
}