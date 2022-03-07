import java.util.concurrent.Semaphore;

public class Station {
	
	// MONITORS AND CONDITIONS
	int stationNumber;
	Train currentTrain;
	Lock trainLock;
	Monitor passengerMonitor;
	TrainCondition trainCondition;
	
	//SEMAPHORES
	int count;
	Semaphore trainMutex;
	Semaphore passengerMutex;
	Semaphore passengerWaiting;
	Semaphore lastPassenger;
	
	//Start
	public void station_init() {
		if(!Interface.getInstance().isSemaphore()) {
			(trainLock = new Lock()).lock_init();
			passengerMonitor = new Monitor(stationNumber);
			(trainCondition = new TrainCondition(stationNumber)).cond_init();			
		}else {
			trainMutex = new Semaphore(1);
			passengerMutex = new Semaphore(1);
			passengerWaiting = new Semaphore(0);
			lastPassenger = new Semaphore(0);
		}
	}
	
	//Train
	public int station_load_train(int count, int trainNumber) {
		if(!Interface.getInstance().isSemaphore()) {
			trainLock.lock_acquire();
			this.currentTrain = CalTrain.getInstance().getTrain(trainNumber);
			Interface.getInstance().updateStationLock(stationNumber, true);
			Interface.getInstance().addTrain(stationNumber, trainNumber);
			CalTrain.getInstance().getTrain(trainNumber).unloadTrain(stationNumber);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			count += CalTrain.getInstance().getTrain(trainNumber).unboardedSeats;
			CalTrain.getInstance().getTrain(trainNumber).unboardedSeats = 0;
			count = passengerMonitor.decrement(count);
			Interface.getInstance().updateTrainTaken(trainNumber, currentTrain.totalSeats-count);
			Interface.getInstance().removeTrain(stationNumber);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Interface.getInstance().updateStationLock(stationNumber, false);
			trainLock.lock_release();	
			return count;	
		}else {
			try {
				trainMutex.acquire();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.currentTrain = CalTrain.getInstance().getTrain(trainNumber);
			Interface.getInstance().updateStationLock(stationNumber, true);
			Interface.getInstance().addTrain(stationNumber, trainNumber);
			CalTrain.getInstance().getTrain(trainNumber).unloadTrain(stationNumber);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				passengerMutex.acquire();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			count += CalTrain.getInstance().getTrain(trainNumber).unboardedSeats;
			CalTrain.getInstance().getTrain(trainNumber).unboardedSeats = 0;
			passengerMutex.release();
			this.count = count;
			if(passengerWaiting.hasQueuedThreads() && count > 0) {
				passengerWaiting.release(count);
				try {
					lastPassenger.acquire();
				} catch (InterruptedException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				passengerWaiting.drainPermits();				
			}
			try {
				passengerMutex.acquire();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Interface.getInstance().updateTrainTaken(trainNumber, currentTrain.totalSeats-count);
			Interface.getInstance().removeTrain(stationNumber);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Interface.getInstance().updateStationLock(stationNumber, false);
			trainMutex.release();
			passengerMutex.release();
		}
		return this.count;		

	}	
	
	//Passenger
	public void station_wait_for_train() {
		if(!Interface.getInstance().isSemaphore()) {
			passengerMonitor.increment();
		}else {
			try {
				passengerMutex.acquire();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Interface.getInstance().updateMonitorLock(stationNumber, true);
			Interface.getInstance().addPassenger(stationNumber);
			passengerMutex.release();
			try {
				passengerWaiting.acquire();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				passengerMutex.acquire();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			count -= 1;
			Interface.getInstance().removePassenger(stationNumber);
			Interface.getInstance().updateMonitorLock(stationNumber, false);
			synchronized(this) {
				if(passengerWaiting.availablePermits() == 0 || !passengerWaiting.hasQueuedThreads()) {
					lastPassenger.release();
				}				
			}
			passengerMutex.release();
		}		
	}
	
	//Passenger
	public void station_on_board(int destNumber) {
		currentTrain.boardTrain(destNumber);	
	}
	
}
