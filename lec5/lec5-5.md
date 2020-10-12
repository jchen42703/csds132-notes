# Strings and the `length()` and `charAt` methods

  Recall that String is a class that represents text.  A String object can not be changed once created.
  Some useful methods and operators:
  * `+`:  creates a new String that is the result of concatentating two Strings
  * `length()`:  returns the number of characters in the String
  * `charAt(5)`: returns the 6th character of the String.  (In Java, the first character is at index 0.)

  So,

  ```
    String s = "Hello"
    s.length()  =>  returns 5
    s.charAt(1) =>  returns 'e'
    "".length() =>  returns 0
    "Hello".charAt(0) =>  returns 'H'
    "".charAt(0) =>  Error
    "Hello".charAt("Hello".length())   =>  Error because it asks for the index 5 character in a string of only 5 characters.

    "Hello there".charAt(5)  =>  returns ' '
    ("hello" + "there").length()  =>  returns 10
```
