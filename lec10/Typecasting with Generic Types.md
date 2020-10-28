# Typecasting with Generic Types
* Any type with a generic type is a __parameterized type.__
  * i.e. `LinkedList` is parameterized type because `public class LinkedList<T>`
* The generic type is also know as the __type parameter.__
* When typecasting between parameterized types, the type parameter must match.
  * For example:
    * For `public class BetterList<A> extends LinkedList<A>`
    * can typecast `BetterList<GeometricFrame>` to `LinkedList<GeometricFrame>`
    * __cannot__ typecast `BetterList<GeometricFrame>` to `BetterList<JFrame>`
    * can typecast `BetterList<GeometricFrame>` to Object
    * __cannot__ typecast `BetterList<GeometricFrame>` to `BetterList<Object>`
* Be careful not to drop the generic type because Java won't catch it at compile time (so it will still run). We don't want that!
  * Will just get an Unchecked Warning
