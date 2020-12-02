# Reflection

__Java reflection is a way to look inside the structure of an object or a class.__ We can also use reflection to manipulate any object or class.

## Reflections with Methods using getClass, getMethods, getDeclaredMethods, invoke
```
  	Object obj = new JFrame();
  	Class<?> cls = obj.getClass();       
```

`cls` now stores the class information about the `JFrame` class.  (Note we could have created the `JFrame` object using just reflection, too!) We can get a list of all non-private methods of the `JFrame` class:
```
	cls.getMethods();
```
or all the methods (including the private ones) declared in the JFrame class:
```
	cls.getDeclaredMethods();
```
__We can get a specific method.__  For example, the `getTitle` method.  It has no parameters.  The declaration is

> `public Method getMethod(String name, Class<?>... parameters)`

Remember that the ... is shorthand for an array.  We can either pass in an array, or we can pass in the individual elements of the array.

So, there are two ways we can retrieve the `getTitle` method:
```
import java.lang.reflect.Method

Method m = cls.getMethod("getTitle", new Class[0]);
or
Method m = cls.getMethod("getTitle");
```

We can call the method to get the title of the `JFrame` using the invoke method of Method.  The invoke method takes both the object we are running the method on as well as the inputs to the method.
```
m.invoke(obj, new Object[0]);
or
m.invoke(obj)
```

Remember that `obj` is the `JFrame` object.  This states that we are calling the method stored in `m` on the object `obj` using the given parameters.

Now, let us try the `setTitle` method.  This takes a single String.  We can easily get the String class using the same technique we got the JFrame class.

```
m = cls.getMethod("setTitle", new Class[]{"Hi".getClass()});
or
m = cls.getMethod("setTitle", "Hi".getClass());
or use a field type notation
m = cls.getMethod("setTitle". String.class);
```
```
To call it:
m.invoke(obj, new Object[]{"The Window Title"});
or
m.invoke(obj, "The Window Title");
```

Now, try the `setVisible` method.  This takes a boolean, but boolean is not a class, it is a primitive!  Java has a special field with each wrapper class that gives the appropriate value to indicate that the parameter is a primitive.
```
m = cls.getMethod("setVisible", new Class[]{Boolean.TYPE});
or
m = cls.getMethod("setVisible", Boolean.TYPE);
```
and we can call it:
```
m.invoke(obj, new Object[]{new Boolean(true)});
or
m.invoke(obj, new Boolean(true));
or
m.invoke(obj, true);
```
thanks to the automatic wrapping and unwrapping of primitives.

Note that  `cls.getMethod("setVisible", new Boolean(false).getClass())` would not return a method because there is no `setVisible` method that takes `Boolean` as input.

## Reflections with Fields using getFields, getDeclaredFields, get, set
__Similarly we can access the fields of the class:__
* `cls.getFields() ` - returns an array containing all public fields info
* `cls.getDeclaredFields()` - returns an array containing info about all fields declared in this class, public or private

> `Field[] fields = cls.getDeclaredFields();`

__Now, given a field, we can access it:__
* `fields[0].get(obj)` - returns the value stored in obj's field
* `fields[0].set(obj, value)` - changes the value of obj's field to value

## Accessing private fields and methods
We can change the accessibility of any field or method in the class using the `setAccessible()` method.
__This is really useful for JUnit testing private methods.__

## Check the txt file for the full code transcript (DrJava examples)
