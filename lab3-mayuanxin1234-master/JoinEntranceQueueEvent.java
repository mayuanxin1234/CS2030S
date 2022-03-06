/**
 * @author Ma Yuanxin (Group 14L)
 * @version CS2030S AY21/22 Semester 2
 **/
public class JoinEntranceQueueEvent extends ShopEvent {
  private String queue;

  public JoinEntranceQueueEvent(Customer c, Shop shop, String queue, double time) {
    super(c, shop, time);
    this.queue = queue;
  }

  @Override
  public String toString() {
    String str = ": ";
    String str1 = " joined shop queue ";
    str = str + this.getCustomer().toString() + str1;
    return String.format("%.3f", this.getTime()) + str + this.queue;
  }


  @Override
  public Event[] simulate() {
    this.getShop().insertToEntranceQ((Customer) this.getCustomer());
    return new Event[] {};

  }
}
