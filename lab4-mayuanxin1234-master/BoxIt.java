/**
 * Takes an item and return the item in a box.
 * CS2030S Lab 4
 * AY21/22 Semester 2
 *
 * @author Ma Yuanxin (Group 14L)
 */
public class BoxIt<T> implements Transformer<T, Box<T>> {

  @Override
  public Box<T> transform(T obj) {
    if (obj == null) {
      return Box.empty();
    } 
    return Box.of(obj);  
  }
}
