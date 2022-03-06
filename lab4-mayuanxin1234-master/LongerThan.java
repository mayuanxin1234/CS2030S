/**
 * A boolean condition with parameter x that can be applied to
 * a string.  Returns true if the string is longer than x; false
 * otherwise.
 * CS2030S Lab 4
 * AY21/22 Semester 2
 *
 * @author Ma Yuanxin (Group 14L)
 */

public class LongerThan implements BooleanCondition<String> {

  private int value;

  public LongerThan(int number) {
    this.value = number;
  }

  @Override
  public boolean test(String str) {
    if (str.length() >  this.value) {
      return true;
    }
    return false;
  }
}




