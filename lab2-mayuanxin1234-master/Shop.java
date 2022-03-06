/* @author Ma Yuanxin */
public class Shop {
  private Counter[] available;
  private Queue q;

  public Shop(int numOfCounters, Queue q) {
    this.available = new Counter[numOfCounters];
    this.q = q;
    for (int i = 0; i < numOfCounters; i++) {
      available[i] = new Counter(i, true);
    }
  }

  public Customer getNextCustomer() {
    return (Customer) q.deq();
  }

  public Queue getQ() {
    return this.q;
  }

  public Counter[] getShopArray() {
    return available;
  }
}
