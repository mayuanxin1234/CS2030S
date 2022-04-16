/**
 * A conditional statement that returns either true of false.
 * CS2030S Lab 4
 * AY21/22 Semester 2
 *
 * @author Ma Yuanxin (Group 14L)
 */

package cs2030s.fp;

public interface BooleanCondition<T> {

  boolean test(T obj);

}
