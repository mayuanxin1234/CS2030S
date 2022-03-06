/** 
 * @author Ma Yuanxin
 * @version CS2030S AY21/22 Semester 2
 */
public class ServiceEndEvent extends ShopEvent {
  private Counter s;

  public ServiceEndEvent(Customer c, Shop shop, double time, Counter s) {
    super(c, shop, time);
    this.s = s;
  }

  @Override
  public String toString() {
    String str = ": ";
    String str1 = " service done (by ";
    String str2 = " ";
    String str3 = ")";
    str = str + this.getCustomer().toString() + str1 + this.s.toString() +
      str2 + this.s.getCounterQString() + str3;
    return String.format("%.3f", this.getTime()) + str;
  }

  @Override
  public Event[] simulate() {
    this.getShop().removeCounter(this.s.getCounterId());
    if (!this.getShop().isEntranceQEmpty()) {
      if (!this.getShop().isCounterQEmpty(this.s)) {
        return new Event[] {
            new DepartureEvent(this.getCustomer(), this.getTime()),
            new ServiceBeginEvent(this.getShop().getNextCustomerInCounterQ(this.s),
                this.getShop(), this.getTime(), this.s),
            new JoinCounterQueueEvent(this.getShop().getNextCustomerInEntranceQ(),
                this.getShop(), 
                this.getTime())
        };
      }
      return new Event[] {
          new DepartureEvent(this.getCustomer(), this.getTime()),
          new ServiceBeginEvent(this.getShop().getNextCustomerInEntranceQ(), this.getShop(),
              this.getTime(), this.s)
      };
    } else if (!this.getShop().isCounterQEmpty(this.s)) {
      return new Event[] {
        new DepartureEvent(this.getCustomer(), this.getTime()),
        new ServiceBeginEvent(this.getShop().getNextCustomerInCounterQ(this.s),
                this.getShop(), this.getTime(), this.s)
      };
    } else {
      return new Event[] {
        new DepartureEvent(this.getCustomer(), this.getTime())
      };
    }
  }

}



