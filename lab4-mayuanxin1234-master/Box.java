/**
 * A generic box storing an item.
 * CS2030S Lab 4
 * AY21/22 Semester 2
 *
 * @author Ma Yuanxin (Group 14L)
 */
public class Box<T> {

  private final T boxContent;
  private static final Box<?> EMPTY_BOX = new Box<>(null);

  private Box(T obj) {
    this.boxContent = obj;
  }

  @Override
  public boolean equals(Object box1) {
    if (box1 == this) {
      return true;
    } 
    if (box1 instanceof Box<?>) {
      Box<?> b = (Box<?>) box1;
      if (this.boxContent == b.boxContent) {
        return true;
      }
      if (this.boxContent == null || b.boxContent == null) {
        return false;
      }
      return this.boxContent.equals(b.boxContent);
    }
    return false;
  }

  @Override
  public String toString() {
    String str1 = "[";
    String str2 = "]";
    if (this.boxContent == null) {
      return str1 + str2;
    }
    return str1 + this.boxContent + str2;
  }

  public static <S> Box<S> of(S obj) {
    if (obj == null) {
      return null;
    }
    return new Box<S>(obj); 
  }

  public static <U> Box<U> empty() {
    // EMPTY_BOX will always contain null,
    // hence type safe to cast it as Box<U>
    @SuppressWarnings("unchecked")
      Box<U> result  = (Box<U>) EMPTY_BOX; 
    return result;

  }

  public boolean isPresent() {
    if (this.boxContent != null) {
      return true;
    }
    return false;
  }

  public static <A> Box<A> ofNullable(A obj) {
    if (obj == null) {
      return empty();
    }
    return of(obj);
  }

  public Box<T> filter(BooleanCondition<? super T> bool) {
    if (this.boxContent == null) {
      return this;
    } else if (bool.test(this.boxContent)) {
      return this;
    } 
    return this.empty();
  }

  public <U> Box<U> map(Transformer<? super T, ? extends U> arg) {
    if (this.boxContent == null) {
      return this.empty();
    }
    U target = (U) arg.transform(this.boxContent);
    if (target == null) {
      return this.empty();
    }
    return this.of(target);
  }
}
