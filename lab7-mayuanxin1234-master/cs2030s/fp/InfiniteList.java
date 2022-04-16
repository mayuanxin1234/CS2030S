/**
 * CS2030S Lab 7.
 * AY21/22 Semester 2
 * 
 * @author Ma Yuanxin (Lab Group 14L)
 */

package cs2030s.fp;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class InfiniteList<T> {

  private final Lazy<Maybe<T>> head;
  private final Lazy<InfiniteList<T>> tail;

  /**
   * A private constructor that initialises an Empty list aka Sentinel.
   * 
   */
  private InfiniteList() { 
    this.head = null; 
    this.tail = null;
  }

  /**
   * A generate method that takes in a producer and returns a Lazy InfiniteList using the producer.
   *
   * @param producer A producer that generates a value
   *
   * @param <T> type T of InfiniteList
   * 
   * @return A Lazy InfiniteList with type T 
   */
  public static <T> InfiniteList<T> generate(Producer<T> producer) {
    // TODO
    return new InfiniteList<T>(Lazy.of(() -> Maybe.some(producer.produce())),
    Lazy.of(() -> InfiniteList.generate(producer)));
  }

  /**
   * An iterate method that takes in a seed as head,
   * and a transformer to produce the tail to produce an InfiniteList.
   * 
   * @param seed a seed that is the first value of the InfiniteList
   *
   * @param next a transformer that generates subsequent values using seed
   *
   * @param <T> a type T of InfiniteList
   *
   * @return A Lazy InfiniteList with type T
   */
  public static <T> InfiniteList<T> iterate(T seed, Transformer<T, T> next) {
    // TODO
    return new InfiniteList<T>(seed, () -> InfiniteList.iterate(next.transform(seed), next));
  }

  /** 
   * A private constructor that initialises the head and tail values.
   * 
   * @param head a head of type T
   *
   * @param tail a tail of type Producer of InfiniteList of type T
   *
   */
  private InfiniteList(T head, Producer<InfiniteList<T>> tail) {
    // TODO
    this.head = Lazy.of(Maybe.some(head));
    this.tail = Lazy.of(() -> tail.produce());
  }

  /** 
   * A private constructor that initialises the head anf tail values.
   *
   * @param head A head of type Lazy of Maybe of type T
   *
   * @param tail A tail of type Lazy of type InfiniteList of type T
   *
   */
  private InfiniteList(Lazy<Maybe<T>> head, Lazy<InfiniteList<T>> tail) {
    // TODO
    this.head = head;
    this.tail = tail;
  }
  
  /**
   * A method that returns the head with a value.
   *
   * @return value of type T
   *
   */
  public T head() {
    // TODO
    return this.head.get().orElseGet(() -> this.tail.get().head());
  }
  
  /**
   * A method that returns the tail of a list with a head value of not none.
   *
   * @return A InfiniteList of type T
   *
   */
  public InfiniteList<T> tail() {
    // TODO
    return this.head.get().map(x -> this.tail.get()).orElseGet(() -> this.tail.get().tail());
  }

  /** 
   * A map that takes in a transformer and returns an InfiniteList with all the values transformed.
   *
   * @param mapper A transformer that maps to the desired value
   *
   * @param <R> a type R that transformer maps from T to R
   *
   * @return A InfiniteList with values mapped to type R
   *
   */
  public <R> InfiniteList<R> map(Transformer<? super T, ? extends R> mapper) {
    // TODO
    return new InfiniteList<R>(Lazy.of(() -> Maybe.some(mapper.transform(this.head()))),
    Lazy.of(() -> this.tail().map(mapper)));
  }
  
  /** 
   * A filter that filters the InfiniteList with the given predicate.
   *
   * @param predicate A BooleanCondition to test the values
   *
   * @return A InfiniteList with all the values filtered out
   *
   */
  public InfiniteList<T> filter(BooleanCondition<? super T> predicate) {
    // TODO
    return new InfiniteList<>(Lazy.of(() -> predicate.test(this.head())
    ? Maybe.some(this.head()) : Maybe.none()), Lazy.of(() -> this.tail().filter(predicate)));
  }
  
  /**
   * A Sentinel or an EmptyList to mark the end of a list.
   *
   */
  private static class Sentinel extends InfiniteList<Object> {

    private static final Sentinel SENTINEL = new Sentinel();

    private Sentinel() {
      super();
    }

    @Override
    public Object head() throws NoSuchElementException {
      throw new NoSuchElementException();
    }

    @Override
    public InfiniteList<Object> tail() throws NoSuchElementException {
      throw new NoSuchElementException();
    }

    @Override
    public <R> InfiniteList<R> map(Transformer<? super Object, ? extends R> mapper) {
      return InfiniteList.sentinel();
    }

    @Override
    public InfiniteList<Object> filter(BooleanCondition<? super Object> predicate) {
      return InfiniteList.sentinel();
    }

    @Override
    public InfiniteList<Object> limit(long n) {
      return InfiniteList.sentinel();
    }

    @Override
    public InfiniteList<Object> takeWhile(BooleanCondition<? super Object> predicate) {
      return InfiniteList.sentinel();
    }

    @Override
    public <U> U reduce(U identity, Combiner<U, ? super Object, U> accumulator) {
      return identity;
    }
    
    @Override
    public boolean isSentinel() {
      return true;
    }

    @Override
    public String toString() {
      return "-";
    }
  }

  /**
   * A sentinel method to put a sentinel at the end of the InfiniteList to mark its end.
   * 
   * @param <T> a type T of InfiniteList
   *
   * @return InfiniteList of type t
   *
   */
  public static <T> InfiniteList<T> sentinel() {
    // TODO
    @SuppressWarnings("unchecked")
    InfiniteList<T> result = (InfiniteList<T>) Sentinel.SENTINEL;
    return result;
    
  }

  /** 
   * A limit method to take the n elements in an InfiniteList.
   *
   * @param n Number of elements to be taken from an InfiniteList
   *
   * @return A Lazy InfiniteList of first n items
   *
   */
  public InfiniteList<T> limit(long n) {
    // TODO
    // Limit should not change when Maybe.none.
    if (n <= 0) {
      return InfiniteList.sentinel();
    } 
    InfiniteList<T> list = new InfiniteList<>(this.head, Lazy.of(() -> 
          this.head.get().map(x -> this.tail.get().limit(n - 1))
          .orElseGet(() -> this.tail.get().limit(n))));
    return list;    
  }

  /**
   * A takeWhile method that produces an InfiniteList that satisfies the predicate.
   *
   * @param predicate A BooleanCondition that tests the elements
   *
   * @return A Lazy InfiniteList that contains the correct filtered elements
   *
   */
  public InfiniteList<T> takeWhile(BooleanCondition<? super T> predicate) {
    // TODO
    Lazy<Boolean> bool = Lazy.of(() -> this.head()).filter(predicate);
    return new InfiniteList<>(Lazy.of(() -> bool.get() ? Maybe.some(this.head()) : Maybe.none()), 
    Lazy.of(() -> bool.get() ? this.tail().takeWhile(predicate)
    : InfiniteList.sentinel()));

  }

  /**
   * A isSentinel method that returns if the InfiniteList is a sentinel.
   *
   * @return true or false if isSentinel
   *
   */
  public boolean isSentinel() {
    return false;
  }
  
  /**
   * A reduce method that takes in an identity as its start
   * and an accumulator to reduce the list to the identity type.
   *
   * @param identity A value of type U that is the initial value
   *
   * @param accumulator that accumulates the items into one value of type identity
   * 
   * @param <U> A type U that gets accumulated to
   *
   * @return a value of type U
   *
   */
  public <U> U reduce(U identity, Combiner<U, ? super T, U> accumulator) {
    // TODO
    U counter = identity;
    InfiniteList<T> list = this;
    while (!list.isSentinel()) {
      counter = accumulator.combine(counter, list.head());
      list = list.tail.get();
    }
    return counter;
  }

  /** 
   * A count method that counts the number of elements in a list.
   *
   * @return a long number
   *
   */
  public long count() {
    // TODO
    long counter = 0;
    List<T> list = this.toList();
    long size = list.size();
    return size;
  }

  /**
   * A toList method that converts the elements in a list to an ArrayList.
   *
   * @return An ArrayList that contains the InfiniteList values
   *
   */
  public List<T> toList() {
    // TODO
    List<T> result = new ArrayList<>();
    InfiniteList<T> list = this;
    while (!list.isSentinel()) {
      try {
        list.head.get().consumeWith(x -> result.add(x));
        list = list.tail.get();
      } catch (NoSuchElementException e) {
        break;
      }
    }
    return result;
  }
  
  /**
   * A toString method that prints the head and tail of the InfiniteList.
   *
   * @return String of the InfiniteList values
   *
   */
  @Override
  public String toString() {
    return "[" + this.head + " " + this.tail + "]";
  }  
}
