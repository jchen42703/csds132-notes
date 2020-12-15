# Restricting Generics
For example, when declaring a generic:
```
T extends S < T can be any type S or narrower (S can be a generic)
T extends JFrame <- T can be any type JFrame or narrower
```
When specifying a generic with a wildcard:
```
? extends S (Must be S or narrower)
? extends JFrame (Must be JFrame or narrower)
? super JFrame (Must be JFrame or wider)
```

## Example
```
import java.util.LinkedList;
import javax.swing.JFrame;

public class ListStuff {
  /** display all the JFrames in a LinkedList */
  public static void displayFrames(LinkedList<JFrame> list) {
    for (JFrame frame: list) {
      frame.setVisible(true);
    }
  }
}
```
The problem with `displayFrames` is that it's not flexible. It won't work with child classes of `JFrame`, such as `GeometricFrame` (remember, typecasting is strict with generics)
* we can't do
```
public static <F> void displayFrames(LinkedList<F> list) {
```
because the compilter doesn't know if `F frame` will have the method `setVisible`
* __But we can do:__
```
public static <F extends JFrame> void displayFrames(LinkedList<F> list) {
  for (F frame: list) {...}
}
```
* __Or with a wildcard:__
```
public static void displayFrames(LinkedList<? extends JFrame> list) {
  for (JFrame frame: list) {...}
}
```
  * Here, we're checking that the type is `JFrame` or narrower.
