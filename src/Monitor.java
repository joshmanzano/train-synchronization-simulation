public class Monitor {
	
	int i;
	int j;
	int stationNumber;
	Lock monitorLock;
	Condition cond;
	
	public Monitor(int stationNumber) {
		this.stationNumber = stationNumber;
		(monitorLock = new Lock()).lock_init();
		(cond = new Condition(stationNumber)).cond_init();
		i = 0;
		j = 0;
	}
	
	public void increment() {
		monitorLock.lock_acquire();
		Interface.getInstance().updateMonitorLock(stationNumber, true);
		Interface.getInstance().addPassenger(stationNumber);
		//Interface.getInstance().refreshWaitingAmount();
		i += 1;
		cond.cond_wait(monitorLock);
		Interface.getInstance().removePassenger(stationNumber);
		i -= 1;
		j -= 1;
		//CalTrain.getInstance().removeWaitingPassenger();
		//Interface.getInstance().refreshWaitingAmount();
		Interface.getInstance().updateMonitorLock(stationNumber, false);
		monitorLock.lock_release();
	}
	
	public int decrement(int count) {
		monitorLock.lock_acquire();
		j = count;
		Interface.getInstance().updateMonitorLock(stationNumber, true);
		while(i > 0 && j > 0) {
			cond.cond_signal(monitorLock);
			monitorLock.lock_acquire(); // DANGER ZONE
			Interface.getInstance().updateMonitorLock(stationNumber, true);
		}
		Interface.getInstance().updateMonitorLock(stationNumber, false);
		monitorLock.lock_release();
		return j;
	}
	
}
