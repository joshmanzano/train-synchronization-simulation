import java.awt.Color;
import java.awt.image.BufferedImage;

public class Interface {

	private static Interface window = null;
	StartStation t1;
	TrainStations t2;
	TrainStations t3;
	EndStation t4;
	ControlBar cB;
	boolean semaphoreMode;
	
	/* Graphical Info:
	 	left start: 356, 430
		right start: 420, -130
		left Station: 105
		right station: 90
		
		leftPassenger start: 85 240
		leftPassenger stop: 260 135
		
		leftPassenger idle: 278 130
		
		rightPassenger start: 464 240
		rightPassenger stop: 494 135
		
		rightPassenger idle: 495 131
	 */
	
	private Interface(boolean semaphoreMode) {
		this.semaphoreMode = semaphoreMode;
		initialize();
		startSimulation();
	}
	
	public static Interface getInstance() {
		return window;
	}
	
	public static void toSemaphore() {
		if(window == null) {
			window = new Interface(true);
		}else {
			window.disposeGUI();
			window = new Interface(true);			
		}
	}
	
	public static void toMonitor() {
		if(window == null) {
			window = new Interface(false);
		}else {
			window.disposeGUI();
			window = new Interface(false);		
		}	
	}
	
	public boolean isSemaphore() {
		return semaphoreMode;
	}
	
    public BufferedImage colorImage(BufferedImage raw, int hueValue) {
        int width = raw.getWidth();
        int height = raw.getHeight();
        float hue = hueValue/360.0f;
        
        BufferedImage processed = new BufferedImage(width, height, raw.getType());
        for(int i = 0 ; i < height ; i++) {
        	for(int j = 0 ; j < width ; j++) {
        		int RGB = raw.getRGB(j, i);
        		if((RGB >> 24) != 0x00) {
            		int R = (RGB >> 16) & 0xff; 
            		int G = (RGB >> 8) & 0xff;
            		int B = (RGB) & 0xff;
            		float HSV[] = new float[3];
            		Color.RGBtoHSB(R,G,B,HSV);
            		processed.setRGB(j, i, Color.getHSBColor(hue,HSV[1],HSV[2]).getRGB());	
        		}
        	}
        }
        
        return processed;
        
    }
	
	public void updateStationLock(int stationNumber, boolean isLocked) {
		cB.updateStationLock(stationNumber, isLocked);
	}
	
	public void updateMonitorLock(int stationNumber, boolean isLocked) {
		cB.updateMonitorLock(stationNumber, isLocked);
	}
	
	public void updateWaitingAmount(int stationNumber, int amount) {
		cB.updateWaitingAmount(stationNumber, amount);
	}
	
	public void setTotalPassenger(int totalPassengers) {
	}
	
	public void setWaitingPassenger(int waitingPassengers) {
	}
	
	public void setTotalTrain(int totalTrains) {
	}
	
	public synchronized void updateConsole(String text) {
		cB.updateConsole(text);
	}
	
	public void deployTrain(int trainNumber, int seats) {
		cB.deployTrain(trainNumber, seats);
	}
	
	public void demobilizeTrain(int trainNumber) {
		cB.demobilizeTrain(trainNumber);
	}	
	
	public void updateTrainTaken(int trainNumber, int taken) {
		cB.updateTrainTaken(trainNumber, taken);
	}
	
	public void updateTrainStation(int trainNumber, int station) {
		cB.updateTrainStation(trainNumber, station);
	}
	
	public void unloadPassenger(int stationNumber) {
		if(stationNumber == 0) {
			t1.unloadLeftPassenger();
		}else if(stationNumber == 1) {
			t2.unloadLeftPassenger();
		}else if(stationNumber == 2) {
			t3.unloadLeftPassenger();
		}else if(stationNumber == 3) {
			t4.unloadLeftPassenger();
		}else if(stationNumber == 4) {
			t4.unloadRightPassenger();
		}else if(stationNumber == 5) {
			t3.unloadRightPassenger();
		}else if(stationNumber == 6) {
			t2.unloadRightPassenger();
		}else if(stationNumber == 7) {
			t1.unloadRightPassenger();
		}	
	}
	
	public void addPassenger(int stationNumber) {
		if(stationNumber == 0) {
			t1.addLeftPassenger();
		}else if(stationNumber == 1) {
			t2.addLeftPassenger();
		}else if(stationNumber == 2) {
			t3.addLeftPassenger();
		}else if(stationNumber == 3) {
			t4.addLeftPassenger();
		}else if(stationNumber == 4) {
			t4.addRightPassenger();
		}else if(stationNumber == 5) {
			t3.addRightPassenger();
		}else if(stationNumber == 6) {
			t2.addRightPassenger();
		}else if(stationNumber == 7) {
			t1.addRightPassenger();
		}
	}
	
	public void removePassenger(int stationNumber) {
		if(stationNumber == 0) {
			t1.removeLeftPassenger();
		}else if(stationNumber == 1) {
			t2.removeLeftPassenger();
		}else if(stationNumber == 2) {
			t3.removeLeftPassenger();
		}else if(stationNumber == 3) {
			t4.removeLeftPassenger();
		}else if(stationNumber == 4) {
			t4.removeRightPassenger();
		}else if(stationNumber == 5) {
			t3.removeRightPassenger();
		}else if(stationNumber == 6) {
			t2.removeRightPassenger();
		}else if(stationNumber == 7) {
			t1.removeRightPassenger();
		}
	}
	
	public void addTrain(int stationNumber, int trainNumber) {
		if(stationNumber == 0) {
			t1.addLeftTrain(trainNumber);
		}else if(stationNumber == 1) {
			t2.addLeftTrain(trainNumber);
		}else if(stationNumber == 2) {
			t3.addLeftTrain(trainNumber);
		}else if(stationNumber == 3) {
			t4.addLeftTrain(trainNumber);
		}else if(stationNumber == 4) {
			t4.addRightTrain(trainNumber);
		}else if(stationNumber == 5) {
			t3.addRightTrain(trainNumber);
		}else if(stationNumber == 6) {
			t2.addRightTrain(trainNumber);
		}else if(stationNumber == 7) {
			t1.addRightTrain(trainNumber);
		}
	}
	
	public void removeTrain(int stationNumber) {
		if(stationNumber == 0) {
			t1.removeLeftTrain();
		}else if(stationNumber == 1) {
			t2.removeLeftTrain();
		}else if(stationNumber == 2) {
			t3.removeLeftTrain();
		}else if(stationNumber == 3) {
			t4.removeLeftTrain();
		}else if(stationNumber == 4) {
			t4.removeRightTrain();
		}else if(stationNumber == 5) {
			t3.removeRightTrain();
		}else if(stationNumber == 6) {
			t2.removeRightTrain();
		}else if(stationNumber == 7) {
			t1.removeRightTrain();
		}
	}	
	
	public void moveTrain(int stationNumber, int trainNumber) {
		if(stationNumber == 0) {
			t1.removeLeftTrain();
			t2.addLeftTrain(trainNumber);
		}else if(stationNumber == 1) {
			t2.removeLeftTrain();
			t3.addLeftTrain(trainNumber);
		}else if(stationNumber == 2) {
			t3.removeLeftTrain();
			t4.addLeftTrain(trainNumber);
		}else if(stationNumber == 3) {
			t4.removeLeftTrain();
			t4.addRightTrain(trainNumber);
		}else if(stationNumber == 4) {
			t4.removeRightTrain();
			t3.addRightTrain(trainNumber);
		}else if(stationNumber == 5) {
			t3.removeRightTrain();
			t2.addRightTrain(trainNumber);
		}else if(stationNumber == 6) {
			t2.removeRightTrain();
			t1.addRightTrain(trainNumber);
		}else if(stationNumber == 7) {
			t1.removeRightTrain();
			t1.addLeftTrain(trainNumber);
		}
	}
	
	public void refreshWaitingAmount() {

	}
	
	public void startSimulation() {
		
		Thread t = new Thread() {
			public void run() {
				CalTrain.getInstance();
			}
		};
		t.start();
		
	};
	
	public int getTrainSpeed() {
		return 6-cB.getTrainSpeed();
	}
	
	public int getPassengerSpeed() {
		return 16-cB.getPassengerSpeed();
	}
	
	public int getTrainRate() {
		return 105-cB.getTrainRate();
	}
	
	public int getPassengerRate() {
		return 2100-cB.getPassengerRate();
	}
	
	private void disposeGUI() {
		t1.dispose();
		t2.dispose();
		t3.dispose();
		t4.dispose();
		cB.dispose();
	}
	
	private void initialize() {
		t1 = new StartStation(0,465, "Train Station 1 and 2");
		t2 = new TrainStations(0,0, "Train Station 3 and 4");
		t3 = new TrainStations(847,465, "Train Station 5 and 6");
		t4 = new EndStation(847,0, "Train Station 7 and 8");
		cB = new ControlBar(1600,0,"Control Bar");
		if(semaphoreMode) {
			cB.toSemaphore();
		}else {
			cB.toMonitor();
		}
	}
		
}
