/**
 * The Transformer interface that can transform a type T
 * to type U.
 * CS2030S Lab 4
 * AY21/22 Semester 2
 *
 * @author Ma Yuanxin (Group 14L)
 */
public interface Transformer<T, U> {

  U transform(T arg);

}


