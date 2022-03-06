/**
 * This class encapsulates an event in the shop
 * simulation.  Your task is to replace this
 * class with new classes, following proper OOP principles.
 *
 * @author Wei Tsang
 * @version CS2030S AY21/22 Semester 2
 */
abstract class ShopEvent extends Event {
  /** 
   * The id of a customer associated with this event.  
   * First customer has id 0. Next is 1, 2, etc. 
   */
  public int customerId;

  /** 
   * A tag to indicate what type of event this is.
   * Possible types are ARRIVAL, SERVICE_BEGIN, 
   * SERVICE_END and DEPARTURE.
   */
  public int eventType;

  /**
   * The service time of the customer associated
   * this event.  This field matters only if the
   * event type is ARRIVAL or SERVICE_BEGIN.
   */
  public double serviceTime;

  /**
   * An array to indicate if a service counter is
   * available.  available[i] is true if and only
   * if service counter i is available to serve.
   */
  public boolean[] available;

  /**
   * The id of the counter associated with this event.
   * This field only matters if the event type if 
   * SERVICE_BEGIN or SERVICE_END.
   */
  public int counterId;

  /*
   * Different types of events.  This should be 
   * implemented as enum, but we have not cover 
   * that yet.
   */
  public static final int ARRIVAL = 0;
  public static final int SERVICE_BEGIN = 1;
  public static final int SERVICE_END = 2;
  public static final int DEPARTURE = 3;

  /**
   * Constructor for a shop event.
   *
   * @param eventType The indicator for which type of 
   *                  event this is.
   * @param time       The time this event occurs.
   * @param customerId The customer associated with this
   *                   event.
   * @param serviceTime The time this customer takes 
   *                    for service.
   * @param counterId   The id of the counter associated with
   *                    this event.
   * @param available   The indicator of which counter is
   *                    available.
   */
  public ShopEvent(int eventType, double time, int customerId, 
      double serviceTime, int counterId, boolean[] available) {
    this(eventType, time, customerId);
    this.serviceTime = serviceTime;
    this.available = available;
    this.counterId = counterId;
  }

  /**
   * Constructor for a shop event.
   *
   * @param eventType The indicator for which type of 
   *                  event this is.
   * @param time       The time this event occurs.
   * @param customerId The customer associated with this
   *                   event.
   * @param serviceTime The time this customer takes 
   *                    for service.
   * @param available   The indicator of which counter is
   *                    available.
   */
  public ShopEvent(int eventType, double time, int customerId, 
      double serviceTime, boolean[] available) {
    this(eventType, time, customerId);
    this.serviceTime = serviceTime;
    this.available = available;
  }

  /**
   * Constructor for a shop event.
   *
   * @param eventType The indicator for which type of 
   *                  event this is.
   * @param time       The time this event occurs.
   * @param customerId The customer associated with this
   *                   event.
   */
  public ShopEvent(int eventType, double time, int customerId) {
    super(time);
    this.customerId = customerId;
    this.eventType = eventType;
  }


  /**
   * Returns the string representation of the event,
   * depending on the type of event.
   *
   * @return A string representing the event.
   */
  
  public abstract String toString();

  /**
   * The logic that the simulation should follow when simulating
   * this event.
   *
   * @return An array of new events to be simulated.
   */
  
  public abstract Event[] simulate();
}
