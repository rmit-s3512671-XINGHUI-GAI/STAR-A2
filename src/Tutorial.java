public class Tutorial extends Lesson {
	private int capacity;
	private int registerNum;

	public Tutorial(int tutDay, double tutStartHr, double tutDur, int cap,Venue venue)
			throws ClashException {
		super(tutDay, tutStartHr, tutDur, venue);
		this.capacity=cap;
	}

	public int getCapacity(){
		  return capacity;
	  }

	public boolean isAvailable() {
		if (capacity > registerNum) {
			return true;
		} else {
			return false;
		}
	}

	public void newRegister() {
		registerNum++;
	}

	public void newDeregister() {
		registerNum--;
	}
}
