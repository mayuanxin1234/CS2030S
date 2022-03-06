/**
 * A transformer with a parameter k that takes in an object x 
 * and outputs the last k digits of the hash value of x.
 * CS2030S Lab 4
 * AY21/22 Semester 2
 *
 * @author Ma Yuanxin (Group 14L)
 */
public class LastDigitsOfHashCode implements Transformer<Object, Integer> {

  public int lastDigits;


  public LastDigitsOfHashCode(int k) {
    this.lastDigits = k;
  }

  @Override
  public Integer transform(Object t) {
    Integer hashCode = t.hashCode(); 
    Integer lastDigits = Math.abs(hashCode % (int) Math.pow(10, this.lastDigits));
    return lastDigits;
  }
}



