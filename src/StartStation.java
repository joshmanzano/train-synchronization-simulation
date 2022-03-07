
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class StartStation {

	private JFrame frame;
	private JLayeredPane graphicalPanel;
	private JLabel leftTrain;
	private JLabel rightTrain;
	private ArrayList<JLabel> leftPassengers;
	private ArrayList<JLabel> rightPassengers;
	private Lock leftPassengerSpriteLock;
	private Lock rightPassengerSpriteLock;
	
	public StartStation(int x, int y, String title) {
		initialize(x,y,title);
		leftPassengers = new ArrayList<>();
		rightPassengers = new ArrayList<>();
		(leftPassengerSpriteLock = new Lock()).lock_init();
		(rightPassengerSpriteLock = new Lock()).lock_init();
	}
	
	/* Graphical Info:
 	left start: 356, 430
	right start: 420, -130
	left Station: 105
	right station: 90
	
	leftPassenger start: 85 240
	leftPassenger stop: 260 135
	
	leftPassenger idle: 278 130
	
	rightPassenger start: 646 240
	rightPassenger stop: 494 135
	
	rightPassenger idle: 495 131
 */
	
	public void dispose() {
		frame.dispose();
	}
	
	public void unloadLeftPassenger() {
		leftPassengerSpriteLock.lock_acquire();
		JLabel leftPassenger = new JLabel();
		leftPassenger.setIcon(new ImageIcon(Interface.class.getResource("/2bMoveLeft.png")));
		leftPassenger.setBounds(356, 135, 84, 73);
		graphicalPanel.setLayer(leftPassenger, -1);
		graphicalPanel.add(leftPassenger);
		while(leftPassenger.getX() != 85 || leftPassenger.getY() != 240) {
			if(leftPassenger.getX() > 85) {
				leftPassenger.setLocation(leftPassenger.getX()-1,leftPassenger.getY());
			}
			if(leftPassenger.getY() > 240) {
				leftPassenger.setLocation(leftPassenger.getX(),leftPassenger.getY()-1);
			}
			if(leftPassenger.getY() < 240) {
				leftPassenger.setLocation(leftPassenger.getX(),leftPassenger.getY()+1);
			}
			try {
				Thread.sleep(Interface.getInstance().getPassengerSpeed());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
//		leftPassenger.setLocation(278-(20*leftPassengers.size()), 130);
//		leftPassenger.setIcon(new ImageIcon(Interface.class.getResource("/A2Right.gif")));
//		leftPassengers.add(leftPassenger);
		graphicalPanel.remove(leftPassenger);
		graphicalPanel.repaint();
		leftPassengerSpriteLock.lock_release();		
	}
	
	public void unloadRightPassenger() {
		rightPassengerSpriteLock.lock_acquire();
		JLabel rightPassenger = new JLabel();
		rightPassenger.setIcon(new ImageIcon(Interface.class.getResource("/2bMoveRight.png")));
		rightPassenger.setBounds(420, 135, 84, 73);
		graphicalPanel.setLayer(rightPassenger, -1);
		graphicalPanel.add(rightPassenger);
		while(rightPassenger.getX() != 646 || rightPassenger.getY() != 240) {
			if(rightPassenger.getX() < 646) {
				rightPassenger.setLocation(rightPassenger.getX()+1,rightPassenger.getY());
			}
			if(rightPassenger.getY() > 240) {
				rightPassenger.setLocation(rightPassenger.getX(),rightPassenger.getY()-1);
			}
			if(rightPassenger.getY() < 240) {
				rightPassenger.setLocation(rightPassenger.getX(),rightPassenger.getY()+1);
			}
			try {
				Thread.sleep(Interface.getInstance().getPassengerSpeed());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
//		rightPassenger.setLocation(495+(20*rightPassengers.size()), 130);
//		rightPassenger.setIcon(new ImageIcon(Interface.class.getResource("/A2Left.gif")));
		graphicalPanel.remove(rightPassenger);
		graphicalPanel.repaint();
		rightPassengerSpriteLock.lock_release();	
	}
	
	public void addLeftPassenger() {
		leftPassengerSpriteLock.lock_acquire();
		JLabel leftPassenger = new JLabel();
		leftPassenger.setIcon(new ImageIcon(Interface.class.getResource("/2bMoveRight.png")));
		leftPassenger.setBounds(85, 240, 84, 73);
		graphicalPanel.setLayer(leftPassenger, -1);
		graphicalPanel.add(leftPassenger);
		while(leftPassenger.getX() != 260-(20*leftPassengers.size()) || leftPassenger.getY() != 135) {
			if(leftPassenger.getX() < 260-(20*leftPassengers.size())) {
				leftPassenger.setLocation(leftPassenger.getX()+1,leftPassenger.getY());
			}
			if(leftPassenger.getY() > 135) {
				leftPassenger.setLocation(leftPassenger.getX(),leftPassenger.getY()-1);
			}
			if(leftPassenger.getY() < 135) {
				leftPassenger.setLocation(leftPassenger.getX(),leftPassenger.getY()+1);
			}
			try {
				Thread.sleep(Interface.getInstance().getPassengerSpeed());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		leftPassenger.setLocation(278-(20*leftPassengers.size()), 130);
		leftPassenger.setIcon(new ImageIcon(Interface.class.getResource("/2bRight.gif")));
		leftPassengers.add(leftPassenger);
		graphicalPanel.repaint();
		leftPassengerSpriteLock.lock_release();
	}
	
	public void addRightPassenger() {
		rightPassengerSpriteLock.lock_acquire();
		JLabel rightPassenger = new JLabel();
		rightPassenger.setIcon(new ImageIcon(Interface.class.getResource("/2bMoveLeft.png")));
		rightPassenger.setBounds(646, 240, 84, 73);
		graphicalPanel.setLayer(rightPassenger, -1);
		graphicalPanel.add(rightPassenger);
		while(rightPassenger.getX() != 494+(20*rightPassengers.size()) || rightPassenger.getY() != 135) {
			if(rightPassenger.getX() > 494+(20*rightPassengers.size())) {
				rightPassenger.setLocation(rightPassenger.getX()-1,rightPassenger.getY());
			}
			if(rightPassenger.getY() > 135) {
				rightPassenger.setLocation(rightPassenger.getX(),rightPassenger.getY()-1);
			}
			if(rightPassenger.getY() < 135) {
				rightPassenger.setLocation(rightPassenger.getX(),rightPassenger.getY()+1);
			}
			try {
				Thread.sleep(Interface.getInstance().getPassengerSpeed());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		rightPassenger.setLocation(495+(20*rightPassengers.size()), 130);
		rightPassenger.setIcon(new ImageIcon(Interface.class.getResource("/2bLeft.gif")));
		graphicalPanel.repaint();
		rightPassengers.add(rightPassenger);
		rightPassengerSpriteLock.lock_release();
	}
	
	public void removeLeftPassenger() {
		leftPassengerSpriteLock.lock_acquire();
		JLabel leftPassenger = leftPassengers.get(0);
		leftPassengers.remove(0);
		leftPassengerSpriteLock.lock_release();
		while(leftPassenger.getX() != 356) {
			leftPassenger.setLocation(leftPassenger.getX()+1, leftPassenger.getY());
			try {
				Thread.sleep(Interface.getInstance().getPassengerSpeed());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		graphicalPanel.remove(leftPassenger);
	}
	
	public void removeRightPassenger() {
		rightPassengerSpriteLock.lock_acquire();
		JLabel rightPassenger = rightPassengers.get(0);
		rightPassengers.remove(0);
		rightPassengerSpriteLock.lock_release();
		while(rightPassenger.getX() != 420) {
			rightPassenger.setLocation(rightPassenger.getX()-1, rightPassenger.getY());
			try {
				Thread.sleep(Interface.getInstance().getPassengerSpeed());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		graphicalPanel.remove(rightPassenger);
	}	
	
	public void addLeftTrain(int trainNumber) {
		leftTrain = new JLabel();
		try {
			BufferedImage bi;
			bi = ImageIO.read((Interface.class.getResource("/train.png")));
			leftTrain.setIcon(new ImageIcon(Interface.getInstance().colorImage(bi,30*trainNumber)));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		leftTrain.setBounds(356, 430, 60, 136);
		graphicalPanel.add(leftTrain);
		double initSpeed = Interface.getInstance().getTrainSpeed();
//		int initDistance = leftTrain.getY() - 105;
//		int distance = initDistance;
		while(leftTrain.getY() > 105) {
			leftTrain.setLocation(leftTrain.getX(), leftTrain.getY()-1);
			try {
				Thread.sleep((long) initSpeed);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void removeLeftTrain() {
		double initSpeed = Interface.getInstance().getTrainSpeed();
		while(leftTrain.getY() > -130) {
//			if(initSpeed > 1) {
//				initSpeed -= 0.5;
//			}
			leftTrain.setLocation(leftTrain.getX(), leftTrain.getY()-1);
			try {
				Thread.sleep((long) initSpeed);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		leftTrain.setIcon(null);
		graphicalPanel.remove(leftTrain);
	}
	
	public void addRightTrain(int trainNumber) {
		rightTrain = new JLabel();
		try {
			BufferedImage bi;
			bi = ImageIO.read((Interface.class.getResource("/trainRev.png")));
			rightTrain.setIcon(new ImageIcon(Interface.getInstance().colorImage(bi,30*trainNumber)));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		rightTrain.setBounds(420, -130, 60, 136);
		graphicalPanel.add(rightTrain);
		double initSpeed = Interface.getInstance().getTrainSpeed();
//		int initDistance = 90 - rightTrain.getY();
//		int distance = initDistance;
		while(rightTrain.getY() < 90) {
//			if(initSpeed > 1 && distance > initDistance/2 && distance % 2 == 1) {
//				initSpeed -= 1;
//			}else{
//				if(initSpeed < 4) {
//					initSpeed += 1;
//				}
//			}
//			distance -= 1;
			rightTrain.setLocation(rightTrain.getX(), rightTrain.getY()+1);
			try {
				Thread.sleep((long) initSpeed);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void removeRightTrain() {
		double initSpeed = Interface.getInstance().getTrainSpeed();
		while(rightTrain.getY() < 430) {
//			if(initSpeed > 1) {
//				initSpeed -= 0.5;
//			}
			rightTrain.setLocation(rightTrain.getX(), rightTrain.getY()+1);
			try {
				Thread.sleep((long) initSpeed);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		rightTrain.setIcon(null);
		graphicalPanel.remove(rightTrain);
	}
	
	private void initialize(int x, int y, String title) {
		frame = new JFrame();
		frame.setBounds(x, y, 847, 465);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle(title);
		
		graphicalPanel = new JLayeredPane();
		
		JLabel background = new JLabel("");
		graphicalPanel.setLayer(background, -2);
		background.setIcon(new ImageIcon(StartStation.class.getResource("/quarterMap.png")));
		background.setBounds(0, -20, 832, 448);
		graphicalPanel.add(background);
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(graphicalPanel, GroupLayout.DEFAULT_SIZE, 1264, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(graphicalPanel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 682, Short.MAX_VALUE)
		);
		
		JLabel fountain1 = new JLabel("");
		fountain1.setIcon(new ImageIcon(StartStation.class.getResource("/fountain.gif")));
		fountain1.setBounds(255, 252, 63, 64);
		graphicalPanel.add(fountain1);
		
		JLabel fountain2 = new JLabel("");
		fountain2.setIcon(new ImageIcon(StartStation.class.getResource("/fountain.gif")));
		fountain2.setBounds(516, 252, 63, 64);
		graphicalPanel.add(fountain2);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(StartStation.class.getResource("/biggerEntrance.png")));
		lblNewLabel.setBounds(327, 0, 180, 49);
		graphicalPanel.add(lblNewLabel);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(StartStation.class.getResource("/trainStartChanger.png")));
		label.setBounds(344, 360, 144, 108);
		graphicalPanel.add(label);
		frame.getContentPane().setLayout(groupLayout);
		
		frame.setVisible(true);
	}
}
