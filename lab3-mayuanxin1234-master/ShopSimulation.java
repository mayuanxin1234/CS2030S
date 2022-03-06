import java.util.Scanner;

/**
 * This class implements a shop simulation.
 *
 * @author Ma Yuanxin
 * @version CS2030S AY21/22 Semester 2
 */ 
class ShopSimulation extends Simulation {
  /** 
   * The list of customer arrival events to populate
   * the simulation with.
   */
  private Event[] initEvents;
  private Shop s;

  /** 
   * Constructor for a shop simulation. 
   *
   * @param sc A scanner to read the parameters from.  The first
   *           integer scanned is the number of customers; followed
   *           by the number of service counters.  Next is a 
   *           sequence of (arrival time, service time) pair, each
   *           pair represents a customer.
   */
  public ShopSimulation(Scanner sc) {
    initEvents = new Event[sc.nextInt()];
    int numOfCounters = sc.nextInt();
    int lengthOfCounterQueue = sc.nextInt();
    int lengthOfEntranceQueue = sc.nextInt();
    s = new Shop(numOfCounters, lengthOfEntranceQueue, lengthOfCounterQueue);
    int i = 0;
    while (sc.hasNextDouble()) {
      double arrivalTime = sc.nextDouble();
      double serviceTime = sc.nextDouble();
      double endTime = arrivalTime;
      Customer c = new Customer(arrivalTime, serviceTime, endTime);
      initEvents[i] = new ArrivalEvent(c, s, arrivalTime);
      i += 1;
    }
  }



  /**
   * Retrieve an array of events to populate the 
   * simulator with.
   *
   * @return An array of events for the simulator.
   */
  @Override
  public Event[] getInitialEvents() {
    return initEvents;
  }
}
