/** 
 * @author Ma Yuanxin
 * @version CS2030S AY21/22 Semester 2
 */
public class JoinCounterQueueEvent extends ShopEvent {
  private Counter counter;

  public JoinCounterQueueEvent(Customer c, Shop shop, double time) {
    super(c, shop, time);
    this.counter = this.getShop().minCounter();
  }

  @Override
  public String toString() {
    String str = ": ";
    String str1 = " joined counter queue ";
    String str2 = "(at ";
    String str3 = " ";
    String str4 = ")";
    str = str + this.getCustomer().toString() + str1 + str2 + this.counter.toString()
      + str3 + this.counter.getCounterQString() + str4;
    return String.format("%.3f", this.getTime()) + str;
  }


  @Override
  public Event[] simulate() {
    this.getShop().joinCounterQ(this.getCustomer());
    return new Event[] {};
  }
}
