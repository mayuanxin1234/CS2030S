/* @author Ma Yuanxin */
public class ServiceEndEvent extends ShopEvent {
  public ServiceEndEvent(Customer c, Shop available, double time) {
    super(c, available, time);
  }

  @Override
  public String toString() {
    String str = "";
    str = String.format(": C%d service done (by S%d)", 
                    this.getCustomer().getId(), this.getCustomer().getCounterId());
    return String.format("%.3f", this.getTime()) + str;
  }

  @Override
  public Event[] simulate() {
    Counter[] array = this.getShop().getShopArray();
    Counter a = array[this.getCustomer().getCounterId()];
    a.setAvailable(true);
    if (!this.getShop().getQ().isEmpty()) {
      Customer nextInQ = this.getShop().getNextCustomer();
      return new Event[] {
          new DepartureEvent(this.getCustomer(), this.getTime()),
          new ServiceBeginEvent(nextInQ, this.getShop(), this.getTime(), a)
          };
    } else {
      return new Event[] {
          new DepartureEvent(this.getCustomer(), this.getTime())
          };
    }
  }
}



