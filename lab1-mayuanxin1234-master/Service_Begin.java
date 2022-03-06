public class Service_Begin extends ShopEvent {

	public Service_Begin(int eventType, double time, int customerId, double serviceTime,
			int counterId, boolean[] available) {
		super(eventType, time, customerId, serviceTime, counterId, available);
	}

	@Override	
	public String toString() {
		String str = "";
		str = String.format(": Customer %d service begin (by Counter %d)",
				this.customerId, this.counterId);
		return String.format("%.3f", this.getTime()) + str;

	}
	@Override
	public Event[] simulate() {
		this.available[this.counterId] = false;
		double endTime = this.getTime() + this.serviceTime;
		return new Event[] {
			new Service_End(ShopEvent.SERVICE_END, endTime, this.customerId,
					this.serviceTime, this.counterId, this.available)
		};
	}
}



