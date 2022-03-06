/** 
 * @author Ma Yuanxin
 * @version CS2030S AY21/22 Semester 2
 */
public class Customer {
  private int customerId;
  private double arrivalTime;
  private double serviceTime;
  private double endTime;
  private static int lastId = 0;

  public Customer(double arrivalTime, double serviceTime, double endTime) {
    this.customerId = lastId;
    this.arrivalTime = arrivalTime;
    this.serviceTime = serviceTime;
    this.endTime = endTime;
    lastId += 1;
  }

  public double getServiceTime() {
    return serviceTime;
  }

  public int getId() {
    return customerId;
  }

  public void setEndTime(double endTime) {
    this.endTime = endTime;
  }

  @Override
  public String toString() {
    return String.format("C%d", this.customerId);

  }
}
