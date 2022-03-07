import java.util.ArrayList;
import java.util.Random;

public class CalTrain {
	
	private static CalTrain calTrain = null;
	private Station[] stations;
	private Train[] trains;
	private ArrayList<Integer> availableTrains;
	private int totalPassengers;
	private int waitingPassengers;
	private int totalTrains;
	private boolean isAuto = false;
	
	private CalTrain() {
		totalPassengers = 0;
		waitingPassengers = 0;
		totalTrains = 0;
		availableTrains = new ArrayList<>();
		Interface.getInstance().setTotalPassenger(totalPassengers);
		Interface.getInstance().setWaitingPassenger(waitingPassengers);
		Interface.getInstance().setTotalTrain(totalTrains);
		stations = new Station[8];
		for(int i = 0 ; i < 8 ; i++) {
			stations[i] = new Station();
			stations[i].stationNumber = i;
			stations[i].station_init();
		}
		trains = new Train[16];
		for(int i = 0 ; i < 16 ; i++) {
			availableTrains.add(i);
		}
	}
	
	public static CalTrain getInstance() {
		if(calTrain == null) {
			calTrain = new CalTrain();
		}
		return calTrain;
	}
	
	public static void refreshInstance() {
		calTrain = null;
		calTrain = new CalTrain();
	}
	
	public Train getTrain(int trainNumber) {
		return trains[trainNumber];
	}
	
	public void removeWaitingPassenger() {
		waitingPassengers -= 1;
		Interface.getInstance().setWaitingPassenger(waitingPassengers);
	}
	
	public void toggleAutomatic() {
		if(isAuto) {
			isAuto = false;
		}else {
			isAuto = true;
		}
	}
	
	public void automaticMode() {
		generatePassengers();
		generateTrains();
	}
	
	public void addAvailableTrain(int trainNumber) {
		availableTrains.add(trainNumber);
	}
	
	public void generatePassenger(int stationNumber, int destNumber) {
		Thread t = new Thread() {
			public void run() {
				new Passenger(stations, stationNumber, destNumber);
				totalPassengers += 1;
				waitingPassengers += 1;
				Interface.getInstance().setTotalPassenger(totalPassengers);
				Interface.getInstance().setWaitingPassenger(waitingPassengers);
			}
		};
		t.start();
	}
	
	public void generateTrain(int trainNumber, int totalSeats) {
		Thread t = new Thread() {
			public void run() {
				try {
					if(availableTrains.contains(trainNumber)) {
						trains[trainNumber] = new Train(stations,trainNumber,totalSeats);	
						availableTrains.remove(availableTrains.indexOf(trainNumber));
						totalTrains += 1;
						Interface.getInstance().setTotalTrain(totalTrains);
					}
				} catch (NullPointerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}					
			}
		};
		t.start();
	}
	
	public void generatePassengers() {
		Thread t = new Thread() {
			public void run() {
				Passenger p;
				while(isAuto) {
					p = new Passenger(stations);
					totalPassengers += 1;
					waitingPassengers += 1;
					Interface.getInstance().setTotalPassenger(totalPassengers);
					Interface.getInstance().setWaitingPassenger(waitingPassengers);
					try {
						Thread.sleep(Interface.getInstance().getPassengerRate());
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}			
				}
			}
		};
		t.start();
	}
	
	public void generateTrains() {
		Thread t = new Thread() {
			public void run() {
				while(isAuto) {
					try {
						if(availableTrains.get(0) != null) {
							trains[availableTrains.get(0)] = new Train(stations,availableTrains.get(0));	
							availableTrains.remove(0);
							totalTrains += 1;
							Interface.getInstance().setTotalTrain(totalTrains);
							try {
								Thread.sleep(Interface.getInstance().getTrainRate()*100);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					} catch (NullPointerException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		};
		t.start();
	}
	
	public void demobilizeTrain(int trainNumber) {
		try {
			trains[trainNumber].demobilize();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
