/*@author Ma Yuanxin */
public class Customer {
  private int customerId;
  private double arrivalTime;
  private double serviceTime;
  private double endTime;
  private Counter count;

  public Customer(int customerId, double arrivalTime, double serviceTime, double endTime) {
    this.customerId = customerId;
    this.arrivalTime = arrivalTime;
    this.serviceTime = serviceTime;
    this.endTime = endTime;
  }

  public void setCounter(Counter count) {
    this.count = count;
  }

  public void setArrivalTime(double time) {
    this.arrivalTime += time;
  }

  public int getCounterId() {
    return this.count.getCounterId();
  }

  public void setEndTime(double endTime) {
    this.endTime = endTime;
  }

  public double getEndTime() {
    return endTime;
  }

  public double getArrivalTime() {
    return arrivalTime;
  }

  public double getServiceTime() {
    return serviceTime;
  }

  public int getId() {
    return customerId;
  }

  @Override
  public String toString() {
    return String.format("C%d", this.customerId);

  }
}
