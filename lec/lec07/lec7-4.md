# The `main` method
The `main` method is a special method of Java.  Every stand-alone program must have a main method.  __It is what the Java Virtual Machine calls to start your program.__

Each class may have one (and only one) main method.  The form is `public static void main(String[] args) {``

If you have a program that contains a class with a `main` method, you can run your program from the command line of your computer using the Java runtime environment (JRE):

`java DesiredClass`

the above can be followed by 0 or more input values that will be passed to your main method through the String array parameter. (Depending on your system, you may also have to set the `CLASSPATH` environment property to indicate the folder where the Java files are.)

`DesiredClass` must have a main method in it.
See the `MyFirstProgram` class we wrote in the lecture. For example, try `java MyFirstProgram` this is a test of the program.

We can also create a stand alone program by placing all the classes of the program in a `jar` file. (We will demonstrate this when we start building larger programs later in the second half of the course.)
* A `jar` file is just a collection of `Java .class` files, and when creating the `jar` file, we specify which class contains the main method that should be called when launching the program.
  * You can then run the jar file with `java -jar jarfile`
  * Most operating systems will let you run a jar file by just clicking on it.  

__Finally, what should you place into the main method to run your program?__
  * The main method is a static method.
  * The interactions pane is a static context (i.e. it acts like the body of a static method).
  * So, whatever you would have typed in the interactions pane to launch the program, place into the main method.
