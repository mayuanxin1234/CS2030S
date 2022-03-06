/** 
 * @author Ma Yuanxin (Group 14L)
 * @version CS2030S AY21/22 Semester 2
 */
public class Shop {
  private Array<Counter> counters;
  private Queue<Customer> q;
  private Counter c;
  private int numOfCounters;

  public Shop(int numOfCounters, int entranceQIndex, int counterQIndex) {
    this.counters = new Array<Counter>(numOfCounters);
    this.numOfCounters = numOfCounters;
    this.q = new Queue<Customer>(entranceQIndex);
    for (int i = 0; i < numOfCounters; i++) {
      Counter counter = new Counter(true, counterQIndex);
      this.counters.set(i, counter);
    }
  }

  public boolean isNotFull() {
    for (int i = 0; i < numOfCounters; i++) {
      this.c = counters.get(i);
      if (c.isAvailable()) {
        return true;
      }
    }
    return false;
  }

  public Counter getCounter() {
    return this.c;
  }

  public String getEntranceQString() {
    return this.q.toString();
  }

  public String getCounterQString() {
    return this.c.getCounterQString();
  }


  public void removeCounter(int counterId) {
    Counter a = this.counters.get(counterId);
    a.setAvailable(true);
  }

  public boolean isEntranceQEmpty() {
    return this.q.isEmpty();
  }

  public boolean isEntranceQFull() {
    return this.q.isFull();
  }

  public void insertToEntranceQ(Customer customer) {
    this.q.enq(customer);
  }

  public Customer getNextCustomerInEntranceQ() {
    return (Customer) q.deq();
  }

  public Customer getNextCustomerInCounterQ(Counter counter) {
    return (Customer) counter.dequeue();
  }

  public boolean isCounterQEmpty(Counter counter) {
    return counter.isCounterQEmpty();
  }

  public Queue<Customer> getQ() {
    return this.q;
  }

  public boolean isAllCounterQEmpty() {
    boolean isEmpty = true;
    for (int i = 0; i < numOfCounters; i++) {
      if (!this.counters.get(i).isCounterQEmpty()) {
        isEmpty = false;
        break;
      }
    }
    return isEmpty;
  }

  public boolean isAllCounterQFull() {
    boolean isFull = false;
    for (int i = 0; i < numOfCounters; i++) {
      if (this.counters.get(i).isCounterQFull()) {
        isFull = true;
      } else {
        isFull = false;
      }
    }
    return isFull;
  }

  public Counter minCounter() {
    return this.counters.min();
  }

  public void joinCounterQ(Customer customer) {
    Counter counter = this.counters.min();
    if (!counter.isCounterQFull()) {
      counter.insertToCounterQ(customer);
    }
  }

  @Override
  public String toString() {
    return this.q.toString();
  }
}
