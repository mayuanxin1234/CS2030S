/** 
 * @author Ma Yuanxin
 * @version CS2030S AY21/22 Semester 2
 */
public class DepartureEvent extends ShopEvent {

  public DepartureEvent(Customer c, double time) {
    super(c, time);
  }

  @Override
  public String toString() {
    String str = "";
    str = String.format(": C%d departed", this.getCustomer().getId());
    return String.format("%.3f", this.getTime()) + str;
  }


  @Override
  public Event[] simulate() {
    return new Event[] {};
  }
}


