/** 
 * @author Ma Yuanxin
 * @version CS2030S AY21/22 Semester 2
 */
public class ServiceBeginEvent extends ShopEvent {
  private Counter s;

  public ServiceBeginEvent(Customer c, Shop shop, double time, Counter s) {
    super(c, shop, time);
    this.s = s;
    this.s.setAvailable(false);
  }

  @Override
  public String toString() {
    String str = ": ";
    String str1 = " service begin (by ";
    String str2 = " ";
    String str3 = ")";
    str = str + this.getCustomer().toString() + str1 + this.s.toString() +
      str2 + this.s.getCounterQString() + str3;
    return String.format("%.3f", this.getTime()) + str;
  }

  @Override
  public Event[] simulate() {
    double endTime = this.getCustomer().getServiceTime() + this.getTime();
    this.getCustomer().setEndTime(endTime);
    return new Event[] {
      new ServiceEndEvent(this.getCustomer(), this.getShop(), endTime, this.s)
    };
  }
}



