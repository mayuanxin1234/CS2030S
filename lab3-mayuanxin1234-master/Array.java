/**
 * The Array&lt;T&gt; for CS2030S 
 *
 * @author Ma Yuanxin
 * @version CS2030S AY21/22 Semester 2
 */
class Array<T extends Comparable<T>> { // TODO: Change to bounded type parameter
  private T[] array;

  Array(int size) {
    // TODO
    // The only way we can put an object into array is through
    // the method set() and we only put object of type T inside.
    // So it is safe to cast 'Comparable[]' to 'T[]' as 
    // Comparable is a subtype of T
    @SuppressWarnings("unchecked")
    T[] a = (T[]) new Comparable[size];
    this.array = a;
  }

  public void set(int index, T item) {
    // TODO
    this.array[index] = item;
  }

  public T get(int index) {
    // TODO
    return this.array[index];
  }

  public T min() {
    // TODO
    if (array.length == 0) {
      return null;
    } else {
      T minimum = this.array[0];
      for (T a : this.array) {
        if (a.compareTo(minimum) == -1) {
          minimum = a;
        }
      }
      return minimum;
    }
  }

  @Override
  public String toString() {
    StringBuilder s = new StringBuilder("[ ");
    for (int i = 0; i < array.length; i++) {
      s.append(i + ":" + array[i]);
      if (i != array.length - 1) {
        s.append(", ");
      }
    }
    return s.append(" ]").toString();
  }
}
