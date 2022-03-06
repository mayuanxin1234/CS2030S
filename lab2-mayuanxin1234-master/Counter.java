/* @author Ma Yuanxin */
public class Counter {

  private int counterId;
  private boolean available;

  public Counter(int id, boolean available) {
    this.counterId = id;
    this.available = available;
  }

  public int getCounterId() {
    return counterId;
  }

  public boolean isAvailable() {
    return available;
  }

  public void setAvailable(boolean value) {
    this.available = value;
  }

  @Override
  public String toString() {
    return String.format("S%d", this.counterId);
  }
}
