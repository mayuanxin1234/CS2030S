/**
 * A boolean condition with an integer parameter y that can be 
 * apply to another integer x.  Returns true if x is divisible 
 * by y, false otherwise.
 * CS2030S Lab 4
 * AY21/22 Semester 2
 *
 * @author Ma Yuanxin  (Group 14L)
 */
public class DivisibleBy implements BooleanCondition<Integer> {

  public int value;

  public DivisibleBy(int x) {
    this.value = x;
  }

  @Override
  public boolean test(Integer number) {
    if (number % this.value == 0) {
      return true;
    } 
    return false;
  }
}


