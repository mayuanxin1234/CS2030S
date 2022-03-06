public class Arrival extends ShopEvent {
	private int counter = -1;
      	
       public Arrival(int eventType, double time, int customerId, double serviceTime, boolean[] available) {
       		super(eventType, time, customerId, serviceTime, available);
       }		


	
	@Override	
	public String toString() {
		String str = "";
		str = String.format(": Customer %d arrives", this.customerId);
		 return String.format("%.3f", this.getTime()) + str;
		 
	}


	@Override
	public Event[] simulate() {
		for(int i =0; i < this.available.length; i+= 1) {
			if(this.available[i]) {
				counter = i;
				break;
			}
		}  
		if(counter == -1) {
			return new Event[] {
			       new Departure(ShopEvent.DEPARTURE ,this.getTime(), this.customerId)
			};	       
		} else {
			return new Event[] {
				new Service_Begin(ShopEvent.SERVICE_BEGIN, this.getTime(), 
						this.customerId,
						this.serviceTime, counter, this.available)
			};
		}
	
	} 
}

	
