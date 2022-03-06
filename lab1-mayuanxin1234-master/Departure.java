public class Departure extends ShopEvent {
	
	public Departure(int eventType, double time, int customerId) {
		super(eventType, time, customerId);
	}
	@Override	
	public String toString() {
		String str = "";
		str = String.format(": Customer %d departed", this.customerId);
		return String.format("%.3f", this.getTime()) + str;
	}

	
	@Override
	public Event[] simulate() {
		return new Event[] {};
	}	


}


