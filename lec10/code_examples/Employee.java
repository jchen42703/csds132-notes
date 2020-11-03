public class Employee extends Object implements Comparable<Employee> {
  private String name;

  public Employee(String name) {
    this.name = name;
  }
  
  public String getName() {
    return this.name;
  }
  /**
    * Compare this employee with the input employee and order them by name.
    * @return negative, 0, positive if this employee ordered before, the same as, or after the input employee
  */
  public int compareTo(Employee employee) {
    return this.getName().compareTo(employee.getName());
  }
}