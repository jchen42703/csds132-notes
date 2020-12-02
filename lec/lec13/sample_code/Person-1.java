import java.util.Comparator;

public class Person {
  private String name;
  private int age;
  
  public Person(String name, int age) {
    this.name = name;
    this.age = age;
  }
  
  public String getName() {
    return name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public int getAge() {
    return age;
  }
  
  public void setAge(int age) {
    this.age = age;
  }
  
  public static int ageDifference(Person person1, Person person2) {
    return person1.getAge() - person2.getAge();
  }
  
  public static Comparator<Person> getAgeComparator() {
    return Person::ageDifference;
  }
}