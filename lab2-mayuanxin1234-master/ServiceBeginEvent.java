/* @author Ma Yuanxin */
public class ServiceBeginEvent extends ShopEvent {
  private Counter s;

  public ServiceBeginEvent(Customer c, Shop available, double time, Counter s) {
    super(c, available, time);
    this.s = s;
    c.setCounter(s);
  }

  @Override
  public String toString() {
    String str = "";
    str = String.format(": C%d service begin (by S%d)",
                    this.getCustomer().getId(), this.s.getCounterId());
    return String.format("%.3f", this.getTime()) + str;
  }

  @Override
  public Event[] simulate() {
    Counter[] array = this.getShop().getShopArray();
    Counter a = array[this.getCustomer().getCounterId()];
    a.setAvailable(false);
    double endTime = this.getCustomer().getServiceTime() + this.getTime();
    this.getCustomer().setEndTime(endTime);
    return new Event[] {
      new ServiceEndEvent(this.getCustomer(), this.getShop(), endTime)
    };
  }
}



