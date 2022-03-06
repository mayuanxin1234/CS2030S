/** 
 * @author Ma Yuanxin
 * @version CS2030S AY21/22 Semester 2
 */
public class Counter implements Comparable<Counter> {

  private int counterId;
  private boolean available;
  private static int lastId = 0;
  private Queue<Customer> counterQ;

  public Counter(boolean available, int qSize) {
    this.counterId = lastId;
    this.available = available;
    lastId += 1;
    this.counterQ = new Queue<Customer>(qSize);
  }

  public boolean isAvailable() {
    return available;
  }

  public void setAvailable(boolean value) {
    this.available = value;
  }

  public int getCounterId() {
    return this.counterId;
  }

  public boolean getAvailable() {
    return this.available;
  } 

  public int queueLength() {
    return this.counterQ.length();
  }

  public Customer dequeue() {
    return (Customer) this.counterQ.deq();
  }

  public boolean isCounterQEmpty() {
    return this.counterQ.isEmpty();
  }

  public void insertToCounterQ(Customer customer) {
    this.counterQ.enq(customer);
  }

  public boolean isCounterQFull() {
    return this.counterQ.isFull();
  }

  public String getCounterQString() {
    return this.counterQ.toString();
  }
  
  @Override
  public int compareTo(Counter counter) {
    int currentLength = this.queueLength();
    int previousLength = counter.queueLength();
    if (currentLength > previousLength) {
      return 1;
    } else if (currentLength == previousLength) {
      return 0;
    } else {
      return -1;
    }
  }


  @Override
  public String toString() {
    return String.format("S%d", this.counterId);
  }
}
