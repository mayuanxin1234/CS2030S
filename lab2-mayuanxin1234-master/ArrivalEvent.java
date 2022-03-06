/* @author Ma Yuanxin */
public class ArrivalEvent extends ShopEvent {

  public ArrivalEvent(Customer c, Shop available, double time) {
    super(c, available, time);
  }

  @Override
  public String toString() {
    String str = "";
    str = String.format(": C%d arrived ", this.getCustomer().getId());
    String queue = this.getShop().getQ().toString();
    return String.format("%.3f", this.getTime()) + str + queue;
  }


  @Override
  public Event[] simulate() {
    String queue = this.getShop().getQ().toString();
    Counter[] array = this.getShop().getShopArray();
    if (this.getShop().getQ().isEmpty()) {
      for (int i = 0; i < array.length; i += 1) {
        Counter p = array[i];
        if (p.isAvailable()) {
          this.getCustomer().setCounter(p);
          return new Event[] {
            new ServiceBeginEvent(this.getCustomer(), this.getShop(), this.getTime(), p)
          };
        }
      } 
      return new Event[] {
        new QueueEvent(this.getCustomer(), this.getShop(), queue, this.getTime())
      };
    } else if (!this.getShop().getQ().isFull()) {
      return new Event[] {
        new QueueEvent(this.getCustomer(), this.getShop(), queue, this.getTime())
      };
    }
    return new Event[] {
      new DepartureEvent(this.getCustomer(), this.getTime())
    };

  } 
}


