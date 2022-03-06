/*@author Ma Yuanxin */
public class QueueEvent extends ShopEvent {
  private String queue;

  public QueueEvent(Customer c, Shop available, String queue, double time) {
    super(c, available, time);
    this.queue = queue;
  }

  @Override
  public String toString() {
    String str = "";
    str = String.format(": C%d joined queue ", this.getCustomer().getId());
    return String.format("%.3f", this.getTime()) + str + this.queue;
  }


  @Override
  public Event[] simulate() {
    this.getShop().getQ().enq((Customer) this.getCustomer());
    return new Event[] {};

  }
}
