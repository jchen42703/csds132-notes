import java.util.Optional;
import java.util.function.Function;

/** A Customer with Accounts that may be null.  An example of coding with Optional. */
public class Customer {
  // The accounts start out as null, wrapped in an Optional
  private Optional<Account> savings   = Optional.empty();
  private Optional<Account> checking  = Optional.empty();
  private Optional<Account> loan      = Optional.empty();
  
  /**
   * Return the savings account wrapped in an Optional
   * @return an Optional containing the savings account
   */
  public Optional<Account> getSavings() {
    return savings;
  }
  
  /**
   * Return the checking account wrapped in an Optional
   * @return an Optional containing the checking account
   */
  public Optional<Account> getChecking() {
    return checking;
  }
  
  /**
   * Return the loan account wrapped in an Optional
   * @return an Optional containing the loan account
   */
  public Optional<Account> getLoan() {
    return loan;
  }
  
  /**
   * Change the savings account
   * @param account the account to use for the savings account
   */
  public void setSavings(Account account) {
    savings = Optional.ofNullable(account);
  }
  
  /**
   * Change the checking account
   * @param account the account to use for the checking account
   */
  public void setChecking(Account account) {
    checking = Optional.ofNullable(account);
  }
  
  /**
   * Change the loan account
   * @param account the account to use for the loan account
   */
  public void setLoan(Account loan) {
    this.loan = Optional.ofNullable(loan);
  }
  
  /**
   * Return the total number of assets and liabilities for the customer: the accounts value minus the loan amount
   * @return the amount the customer has in their account minus the amount of their loan
   */
  public double getTotal() {
    double total = 0.0;
    
    total += getSavings().map (a -> a.getBalance()).orElse(0.0);
    total += getChecking().map(a -> a.getBalance()).orElse(0.0);
    total -= getLoan().map    (a -> a.getBalance()).orElse(0.0);
    
    return total;
  }
  
  public void applyInterest() {
    getSavings() .ifPresent(a -> a.deposit(a.getBalance() * (1 + a.getInterestRate())));
    getChecking().ifPresent(a -> a.deposit(a.getBalance() * (1 + a.getInterestRate())));
    getLoan()    .ifPresent(a -> a.deposit(a.getBalance() * (1 + a.getInterestRate())));
  }
                           
}
  
  