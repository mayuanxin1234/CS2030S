/**
 * This class encapsulates an event in the shop
 * simulation.  Your task is to replace this
 * class with new classes, following proper OOP principles.
 *
 * @author Ma Yuanxin
 * @version CS2030S AY21/22 Semester 2
 */
abstract class ShopEvent extends Event {

  private double time;

  private Shop available;

  private Customer c;

  private String queueString;

  public Shop getShop() {
    return this.available;
  }

  public Customer getCustomer() {
    return this.c;
  }
  /*
   * Constructor for a shop event.
   */

  public ShopEvent(Customer c, Shop available, double time) {
    super(time);
    this.available = available;
    this.c = c;
    this.time = time;    
  }
  /*
   * Constructor for a shop event.
   */

  public ShopEvent(Customer c, double time) {
    super(time);
    this.c = c;
    this.time = time;
  }

  public double getTime() {
    return time;
  }

  /*
   * Returns the string representation of the event,
   * depending on the type of event.
   *
   * @return A string representing the event.
   */

  public abstract String toString();

  /*
   * The logic that the simulation should follow when simulating
   * this event.
   *
   * @return An array of new events to be simulated.
   */

  public abstract Event[] simulate();
}
