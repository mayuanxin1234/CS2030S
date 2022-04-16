/**
 * CS2030S Lab 6.
 * AY 21/22 Semester 2
 *
 * @author Ma Yuanxin (Lab Group 14L)
 */

package cs2030s.fp;

public class Lazy<T> {

  /**
   * Lazy has a producer that can produce the content of supertype of T when called.
   */
  private Producer<? extends T> producer;
  
  /**
   * Lazy has a Maybe that can store values of type T.
   */
  private Maybe<T> value;

  /**
   * A private constructor to initialise lazy.
   * @param p is a producer that can produce values of type T when called
   */
  private Lazy(Producer<? extends T> p) {
    this.producer = p;
    this.value = Maybe.none();
  }

  /**
   * A private constructor to initialise lazy.
   * @param v is a type T that contains the value
   */
  private Lazy(T v) {
    this.value = Maybe.some(v);
  }

  /**
   * A of method that initialise the lazy with the correct value. 
   * @param v is a type T that contains the value
   * @param <T> is a type T that is in lazy
   * @return returns the correct lazy type
   */
  public static <T> Lazy<T> of(T v) {
    return new Lazy<>(v);
  }
  
  /** 
   * A of method that initialises the lazy with a producer.
   * @param s is a producer that can produce a value of subtype of T when needed
   * @param <T> is a type T that is in lazy
   * @return returns the correct lazy type
   */
  public static <T> Lazy<T> of(Producer<? extends T> s) {
    return new Lazy<>(s);
  }

  /**
   * A get method that returns the correct value of type T.
   * @return a variable of type T
   */
  public T get() {
    this.value = Maybe.some(value.orElseGet(producer));
    return this.value.orElseGet(producer);
  }

  /**
   * A toString method that prints out "?" if not evaluated or the value if it is readily available.
   * @return a string of the elements in Lazy
   */
  public String toString() {
    return value.map(t -> String.valueOf(t)).orElse("?");
  }

  /**
   * A map function that takes in a transformer and returns the mapped lazy.
   * @param trf a transformer
   * @param <S> a type S that is to be transformed to 
   * @return returns a transformed Lazy
   */
  public <S> Lazy<S> map(Transformer<? super T, ? extends S> trf) {
    return new Lazy<>(() -> trf.transform(this.get()));
  }

  /**
   * A flatMap that takes in a transformer and returns the mapped lazy correctly.
   * @param trf a transformer
   * @param <S> a type S that is to be transformed to 
   * @return returns a transformed Lazy
   */
  public <S> Lazy<S> flatMap(Transformer<? super T, ? extends Lazy<? extends S>> trf) {
    return new Lazy<>(() -> trf.transform(this.get()).get());
  }
  
  /**
   * A filter that takes in BooleanCondition and returns Lazy.
   * @param bc is a BooleanCondition
   * @return Lazy until get is called
   */
  public Lazy<Boolean> filter(BooleanCondition<? super T> bc) {
    return new Lazy<>(() -> bc.test(this.get()));
  }

  /**
   * An equals method that takes in an object and returns a boolean.
   * @param ob An object to be compared to
   * @return boolean true/false
   */

  @Override
  public boolean equals(Object ob) {
    if (ob instanceof Lazy<?>) {
      @SuppressWarnings("unchecked")
      Lazy<T> laze = (Lazy<T>) ob;
      return laze.get().equals(this.get());
    } else {
      return false;
    }
  }

  /** 
   * A combine method that takes in a Lazy and a combiner to produce another Lazy.
   * @param laze Lazy parameter 2
   * @param combiner combine method
   * @param <R> type R to be converted to
   * @param <S> type S of parameter 2
   * @return Lazy of type R
   */
  public <R, S> Lazy<R> combine(Lazy<S> laze, 
      Combiner<? super T, ? super S, ? extends R> combiner) {
    return new Lazy<R>(() -> combiner.combine(this.get(), laze.get()));
  }
}
