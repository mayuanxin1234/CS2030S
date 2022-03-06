/** 
 * @author Ma Yuanxin
 * @version CS2030S AY21/22 Semester 2
 */
public class ArrivalEvent extends ShopEvent {

  public ArrivalEvent(Customer c, Shop shop, double time) {
    super(c, shop, time);
  }

  @Override
  public String toString() {
    String str = ": ";
    String str1 = " arrived ";
    str = str + this.getCustomer().toString() + str1;
    String queue = this.getShop().toString();
    return String.format("%.3f", this.getTime()) + str + queue;
  }

  @Override
  public Event[] simulate() {
    String shopQueue = this.getShop().getEntranceQString();
    if (this.getShop().isEntranceQEmpty()) {
      if (this.getShop().isAllCounterQEmpty()) {
        if (this.getShop().isNotFull()) {
          return new Event[] {
            new ServiceBeginEvent(this.getCustomer(), this.getShop(), this.getTime(),
                this.getShop().getCounter())
          }; 
        } else if (!this.getShop().isAllCounterQFull()) {
          return new Event[] {
            new JoinCounterQueueEvent(this.getCustomer(), this.getShop(), this.getTime())
          };
        } else if (!this.getShop().isEntranceQFull()) {
          return new Event[] {
            new JoinEntranceQueueEvent(this.getCustomer(), this.getShop(),
            shopQueue, this.getTime())
          };
        } else {
          System.out.println("HI");
          return new Event[] {
            new DepartureEvent(this.getCustomer(), this.getTime())
          };
        }
      } else if (this.getShop().isAllCounterQFull() && !this.getShop().isEntranceQFull()) {
        return new Event[] {
          new JoinEntranceQueueEvent(this.getCustomer(), this.getShop(), shopQueue, this.getTime())
        };
      } else if (!this.getShop().isAllCounterQFull()) {
        return new Event[] {
          new JoinCounterQueueEvent(this.getCustomer(), this.getShop(), this.getTime())
        };        
      } else {
        return new Event[] {
          new DepartureEvent(this.getCustomer(), this.getTime())
        };          
      }
    } else if (this.getShop().isEntranceQFull()) {
      return new Event[] {
        new DepartureEvent(this.getCustomer(), this.getTime())
      };      
    } else {
      return new Event[] {
        new JoinEntranceQueueEvent(this.getCustomer(), this.getShop(), shopQueue, this.getTime())
      };
    }
  }
}


