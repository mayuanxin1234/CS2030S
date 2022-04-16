/**
 * CS2030S Lab 5.
 * AY21/22 Semester 2
 *
 * @author Ma Yuanxin (Lab Group 14L)
 */

package cs2030s.fp;

import java.util.NoSuchElementException;

public abstract class Maybe<T> {

  private static final Maybe<?> EMPTY = new None();

  private static final class None extends Maybe<Object> {

    @Override
    public String toString() {
      return "[]";
    }

    @Override
    public boolean equals(Object maybe) {
      if (this == maybe) {
        return true;
      } 
      if (maybe instanceof None) {
        return true;
      }
      return false;
    }

    @Override
    protected Object get() throws NoSuchElementException {
      throw new NoSuchElementException("No Such Element!");
    }

    @Override
    public Maybe<Object> filter(BooleanCondition<? super Object> bc) {
      return Maybe.none();
    }

    @Override
    public <U> Maybe<U> map(Transformer<? super Object, ? extends U> tf) {
      return Maybe.none();
    }

    @Override
    public <U> Maybe<U> flatMap(Transformer<? super Object, ? extends Maybe<? extends U>> tf) {
      return Maybe.none();
    }

    @Override
    public Object orElse(Object value) {
      return value;
    }

    @Override 
    public Object orElseGet(Producer<? extends Object> produce) {
      return produce.produce();
    }
  }

  private static final class Some<T> extends Maybe<T> {

    private T something;

    private Some(T t) {
      this.something = t;
    }

    @Override
    public String toString() {
      return "[" + this.something + "]";
    }

    @Override
    public boolean equals(Object maybe) {
      if (maybe instanceof Some<?>) {
        Some<?> some = (Some<?>) maybe;
        if (this.something == null) {
          return some.something == null;
        }
        return this.something.equals(some.something);
      } 
      return false;
    }

    @Override
    protected T get() {
      return this.something;
    }

    @Override
    public Maybe<T> filter(BooleanCondition<? super T> bc) {
      if (this.something == null) {
        return this;
      }
      if (bc.test(this.something)) {
        return this;
      }
      return Maybe.none();
    }

    @Override 
    public <U> Maybe<U> map(Transformer<? super T, ? extends U> tf) {
      U target = tf.transform(this.something);
      if (target == null) {
        return Maybe.some(target);
      }
      return Maybe.of(target);
    }

    @Override 
    public <U> Maybe<U> flatMap(Transformer<? super T, ? extends Maybe<? extends U>> tf) {
      @SuppressWarnings("unchecked")
      Maybe<U> result = (Maybe<U>) tf.transform(this.something);
      return result;
    }

    @Override
    public T orElse(T value) {
      return this.something;
    }

    @Override
    public T orElseGet(Producer<? extends T> produce) {
      return this.something;
    }
  }

  public static <T> Maybe<T> none() {
    @SuppressWarnings("unchecked")
    Maybe<T> result = (Maybe<T>) EMPTY;     
    return result;
  }

  public static <T> Maybe<T> some(T t) {
    return new Some<T>(t);
  }

  public static <T> Maybe<T> of(T t) {
    if (t == null) {
      return Maybe.none();
    }
    return Maybe.some(t);
  }

  protected abstract T get();

  public abstract Maybe<T> filter(BooleanCondition<? super T> bc);

  public abstract <U> Maybe<U> map(Transformer<? super T, ? extends U> tf);

  public abstract <U> Maybe<U> flatMap(Transformer<? super T, ? extends Maybe<? extends U>> tf);

  public abstract T orElse(T value);

  public abstract T orElseGet(Producer<? extends T> produce);

}



