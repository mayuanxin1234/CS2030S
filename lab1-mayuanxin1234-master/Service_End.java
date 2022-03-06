public class Service_End extends ShopEvent {
	public Service_End(int eventType, double time, int customerId, double serviceTime, int counterId,
				boolean[] available) {
		super(eventType, time, customerId,  serviceTime, counterId, available);
	}
	@Override	
	public String toString() {
		String str = "";
		str = String.format(": Customer %d service done (by Counter %d)",
				this.customerId, this.counterId);
		return String.format("%.3f", this.getTime()) + str;
	}
	@Override
	public Event[] simulate() {
		this.available[this.counterId] = true;
		return new Event[] {
			new Departure(ShopEvent.DEPARTURE, this.getTime(), this.customerId),
		};
	}
}
	


