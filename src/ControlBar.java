
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.Dimension;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JTabbedPane;
import javax.swing.JToggleButton;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.UIManager;
import javax.swing.JComboBox;
import javax.swing.SpinnerNumberModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class ControlBar {

	private JFrame frame;
	private JLabel lblWaiting;
	private JTextField tLock[];
	private JTextField pLock[];
	private JTextField waitAmount[];
	private JTextField trainSeats[];
	private JTextField trainTaken[];
	private JTextField currentStation[];
	private JLabel trainLabel[];
	private JLabel lblSeats;
	private JLabel lblTaken;
	private JSlider trainSpeed;
	private JSlider passengerSpeed;
	private JSlider passengerRate;
	private JSlider trainRate;
	private JTextArea console;
	private JComboBox deployNumber;
	private JComboBox returnNumber;
	private JSpinner deploySeats;
	private JSpinner arriveStation;
	private JSpinner departStation;
	private String trains[];
	private Color colors[];
	private JButton btnSemaphores;
	
	public ControlBar(int x, int y, String title) {
		initialize(x,y);
	}
	
	public void dispose() {
		frame.dispose();
	}
	
	public int getTrainSpeed() {
		return trainSpeed.getValue();
	}
	
	public int getPassengerSpeed() {
		return passengerSpeed.getValue();
	}
	
	public int getTrainRate() {
		return trainRate.getValue();
	}
	
	public int getPassengerRate() {
		return passengerRate.getValue();
	}
	
	public void updateConsole(String text) {
		console.append(text+"\n");
	}
	
	public void deployTrain(int trainNumber, int seats) {
		trainLabel[trainNumber].setEnabled(true);
		trainLabel[trainNumber].setBackground(colors[trainNumber]);
		trainLabel[trainNumber].setForeground(colors[trainNumber]);
		currentStation[trainNumber].setEnabled(true);
		currentStation[trainNumber].setBackground(Color.WHITE);
		trainSeats[trainNumber].setEnabled(true);
		trainSeats[trainNumber].setText(Integer.toString(seats));
		trainSeats[trainNumber].setBackground(Color.WHITE);
		trainTaken[trainNumber].setEnabled(true);
		trainTaken[trainNumber].setText("0");
		trainTaken[trainNumber].setBackground(Color.WHITE);
	}
	
	public void demobilizeTrain(int trainNumber) {
		trainLabel[trainNumber].setEnabled(false);
		trainLabel[trainNumber].setBackground(Color.GRAY);
		currentStation[trainNumber].setEnabled(false);
		currentStation[trainNumber].setBackground(Color.GRAY);
		currentStation[trainNumber].setText("0");
		trainSeats[trainNumber].setEnabled(false);
		trainSeats[trainNumber].setText("0");
		trainSeats[trainNumber].setBackground(Color.GRAY);
		trainTaken[trainNumber].setEnabled(false);
		trainTaken[trainNumber].setText("0");
		trainTaken[trainNumber].setBackground(Color.GRAY);
	}
	
	public void updateTrainTaken(int trainNumber, int taken) {
		trainTaken[trainNumber].setText(Integer.toString(taken));
	}
	
	public void updateTrainStation(int trainNumber, int station) {
		currentStation[trainNumber].setText(Integer.toString(station+1));
	}
	
	public void updateMonitorLock(int stationNumber, boolean isLocked) {
		if(isLocked) {
			pLock[stationNumber].setText("LOCKED");
			pLock[stationNumber].setBackground(Color.RED);
		}else {
			pLock[stationNumber].setText("FREE");
			pLock[stationNumber].setBackground(Color.GREEN);
		}

	}	
	
	public void updateStationLock(int stationNumber, boolean isLocked) {
		if(isLocked) {
			tLock[stationNumber].setText("LOCKED");
			tLock[stationNumber].setBackground(Color.RED);
		}else {
			tLock[stationNumber].setText("FREE");
			tLock[stationNumber].setBackground(Color.GREEN);
		}
	}

	public void updateWaitingAmount(int stationNumber, int amount) {
		waitAmount[stationNumber].setText(Integer.toString(amount));
	}	
	
	public void toMonitor() {
		frame.setTitle("Control Bar : Monitors");
	}
	
	public void toSemaphore() {
		frame.setTitle("Control Bar : Semaphores");
	}
	
	private void initialize(int x, int y) {
		frame = new JFrame();
		frame.setBounds(x, y, 318, 1000);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		tLock = new JTextField[8];
		pLock = new JTextField[8];
		waitAmount = new JTextField[8];
		trainSeats = new JTextField[16];
		trainTaken = new JTextField[16];
		currentStation = new JTextField[16];
		trainLabel = new JLabel[16];
		trains = new String[16];
		colors = new Color[16];
		
		for(int i = 0 ; i < 16 ; i++) {
			trains[i] = "Train "+(i+1);
		}
		
		colors[0] = Color.BLACK;
		colors[1] = Color.BLUE;
		colors[2] = Color.CYAN;
		colors[3] = Color.DARK_GRAY;
		colors[4] = Color.GRAY;
		colors[5] = Color.GREEN;
		colors[6] = Color.LIGHT_GRAY;
		colors[7] = Color.MAGENTA;
		colors[8] = Color.ORANGE;
		colors[9] = Color.PINK;
		colors[10] = Color.RED;
		colors[11] = Color.WHITE;
		colors[12] = Color.YELLOW;
		colors[13] = new Color(65, 105, 225);
		colors[14] = new Color(65, 105, 225);
		colors[15] = new Color(65, 105, 225);	
		
		JPanel panel = new JPanel();
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 303, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 922, Short.MAX_VALUE)
		);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "Stations", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(null, "Trains", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(tabbedPane, Alignment.LEADING)
						.addComponent(panel_4, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE)
						.addComponent(panel_3, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_4, GroupLayout.PREFERRED_SIZE, 262, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 336, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(26, Short.MAX_VALUE))
		);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		tabbedPane.addTab("Manual", null, panel_2, null);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new TitledBorder(null, "Passenger", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel panel_7 = new JPanel();
		panel_7.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Train", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_7, GroupLayout.PREFERRED_SIZE, 255, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_6, GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_6, GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_7, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		deployNumber = new JComboBox();
		deployNumber.setMaximumRowCount(16);

		deployNumber.setModel(new DefaultComboBoxModel(trains));
		
		returnNumber = new JComboBox();
		returnNumber.setModel(new DefaultComboBoxModel(trains));
		returnNumber.setMaximumRowCount(16);
		
		JButton btnDeploy = new JButton("Deploy");
		btnDeploy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CalTrain.getInstance().generateTrain(deployNumber.getSelectedIndex(), (int) deploySeats.getValue());
			}
		});
		
		JButton btnGet = new JButton("Demobilize");
		btnGet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CalTrain.getInstance().demobilizeTrain(returnNumber.getSelectedIndex());
			}
		});
		
		deploySeats = new JSpinner();
		deploySeats.setModel(new SpinnerNumberModel(1, 1, 99, 1));
		GroupLayout gl_panel_7 = new GroupLayout(panel_7);
		gl_panel_7.setHorizontalGroup(
			gl_panel_7.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_7.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_7.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_7.createSequentialGroup()
							.addComponent(deployNumber, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(deploySeats, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnDeploy))
						.addGroup(gl_panel_7.createSequentialGroup()
							.addComponent(returnNumber, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnGet)))
					.addContainerGap(29, Short.MAX_VALUE))
		);
		gl_panel_7.setVerticalGroup(
			gl_panel_7.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_7.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_7.createParallelGroup(Alignment.BASELINE)
						.addComponent(deployNumber, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnDeploy)
						.addComponent(deploySeats, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel_7.createParallelGroup(Alignment.BASELINE)
						.addComponent(returnNumber, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnGet))
					.addContainerGap(30, Short.MAX_VALUE))
		);
		panel_7.setLayout(gl_panel_7);
		
		JLabel lblNewLabel = new JLabel("Arriving Station:");
		
		JLabel lblDepartingStation = new JLabel("Departing Station:");
		
		JLabel lblType = new JLabel("Type:");
		
		arriveStation = new JSpinner();
		arriveStation.setModel(new SpinnerNumberModel(1, 1, 8, 1));
		
		departStation = new JSpinner();
		departStation.setModel(new SpinnerNumberModel(1, 1, 8, 1));
		
		JComboBox comboBox = new JComboBox();
		comboBox.setEnabled(false);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"2B", "A2", "21O", "Ronaldo"}));
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CalTrain.getInstance().generatePassenger(((int) arriveStation.getValue())-1, ((int) departStation.getValue())-1);
			}
		});
		GroupLayout gl_panel_6 = new GroupLayout(panel_6);
		gl_panel_6.setHorizontalGroup(
			gl_panel_6.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_6.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_6.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_6.createSequentialGroup()
							.addComponent(lblDepartingStation)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(departStation, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
							.addComponent(btnAdd)
							.addGap(27))
						.addGroup(gl_panel_6.createSequentialGroup()
							.addGroup(gl_panel_6.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_6.createSequentialGroup()
									.addComponent(lblNewLabel)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(arriveStation, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel_6.createSequentialGroup()
									.addComponent(lblType)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
							.addContainerGap(120, Short.MAX_VALUE))))
		);
		gl_panel_6.setVerticalGroup(
			gl_panel_6.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_6.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_6.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(arriveStation, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_6.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDepartingStation)
						.addComponent(departStation, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnAdd))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_6.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblType)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(27, Short.MAX_VALUE))
		);
		panel_6.setLayout(gl_panel_6);
		panel_2.setLayout(gl_panel_2);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		tabbedPane.addTab("Automatic", null, panel_1, null);
		
		JToggleButton tglbtnToggleAutomatic = new JToggleButton("Toggle Automatic");
		tglbtnToggleAutomatic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CalTrain.getInstance().toggleAutomatic();
				CalTrain.getInstance().automaticMode();
			}
		});
		
		passengerRate = new JSlider();
		passengerRate.setMaximum(2100);
		passengerRate.setValue(1100);
		passengerRate.setMinimum(100);
		
		JLabel lblPassengerRate = new JLabel("Passenger Rate");
		
		JLabel lblTrainRate = new JLabel("Train Rate");
		
		trainRate = new JSlider();
		trainRate.setMinimum(5);
		trainRate.setValue(55);
		trainRate.setMaximum(105);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(35)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(passengerRate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(trainRate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(40, Short.MAX_VALUE))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(95)
					.addComponent(lblPassengerRate)
					.addContainerGap(104, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
					.addContainerGap(108, Short.MAX_VALUE)
					.addComponent(lblTrainRate)
					.addGap(117))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(76)
					.addComponent(tglbtnToggleAutomatic)
					.addContainerGap(84, Short.MAX_VALUE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_panel_1.createSequentialGroup()
					.addGap(39)
					.addComponent(lblPassengerRate)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(passengerRate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(39)
					.addComponent(lblTrainRate)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(trainRate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(47)
					.addComponent(tglbtnToggleAutomatic)
					.addContainerGap(47, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new LineBorder(new Color(0, 0, 0)));
		tabbedPane.addTab("Settings", null, panel_5, null);
		
		trainSpeed = new JSlider();
		trainSpeed.setValue(3);
		trainSpeed.setMinimum(1);
		trainSpeed.setMaximum(5);
		
		JLabel lblTrainSpeed = new JLabel("Train Speed");
		
		JLabel lblPassengerSpeed = new JLabel("Passenger Speed");
		
		passengerSpeed = new JSlider();
		passengerSpeed.setValue(8);
		passengerSpeed.setMinimum(1);
		passengerSpeed.setMaximum(15);
		
		btnSemaphores = new JButton("Switch to Semaphores");
		btnSemaphores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!Interface.getInstance().isSemaphore()) {
					btnSemaphores.setText("Switch to Monitors");
					Interface.toSemaphore();
					CalTrain.refreshInstance();
				}else {
					btnSemaphores.setText("Switch to Semaphores");		
					Interface.toMonitor();
					CalTrain.refreshInstance();
				}
			}
		});
		GroupLayout gl_panel_5 = new GroupLayout(panel_5);
		gl_panel_5.setHorizontalGroup(
			gl_panel_5.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_5.createSequentialGroup()
					.addContainerGap(43, Short.MAX_VALUE)
					.addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_5.createSequentialGroup()
							.addGroup(gl_panel_5.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING)
									.addComponent(passengerSpeed, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(trainSpeed, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGroup(gl_panel_5.createSequentialGroup()
										.addGap(72)
										.addComponent(lblTrainSpeed)))
								.addGroup(gl_panel_5.createSequentialGroup()
									.addComponent(lblPassengerSpeed)
									.addGap(58)))
							.addGap(32))
						.addGroup(Alignment.TRAILING, gl_panel_5.createSequentialGroup()
							.addComponent(btnSemaphores)
							.addGap(67))))
		);
		gl_panel_5.setVerticalGroup(
			gl_panel_5.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_5.createSequentialGroup()
					.addGap(62)
					.addComponent(lblPassengerSpeed)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(passengerSpeed, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(27)
					.addComponent(lblTrainSpeed)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(trainSpeed, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(32)
					.addComponent(btnSemaphores)
					.addContainerGap(76, Short.MAX_VALUE))
		);
		panel_5.setLayout(gl_panel_5);
		
		JPanel panel_8 = new JPanel();
		panel_8.setBorder(new LineBorder(new Color(0, 0, 0)));
		tabbedPane.addTab("Log", null, panel_8, null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		GroupLayout gl_panel_8 = new GroupLayout(panel_8);
		gl_panel_8.setHorizontalGroup(
			gl_panel_8.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_8.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel_8.setVerticalGroup(
			gl_panel_8.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_8.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		console = new JTextArea();
		console.setEditable(false);
		scrollPane_1.setViewportView(console);
		panel_8.setLayout(gl_panel_8);
		
		lblSeats = new JLabel("Capacity");
		lblSeats.setFont(new Font("Serif", Font.PLAIN, 12));
		
		lblTaken = new JLabel("Taken");
		lblTaken.setFont(new Font("Serif", Font.PLAIN, 12));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		JPanel trainPanel = new JPanel();
		scrollPane.setViewportView(trainPanel);
		trainPanel.setLayout(new GridLayout(16, 4, 1, 1));
		
		JLabel lblStation_1 = new JLabel("Station");
		lblStation_1.setFont(new Font("Serif", Font.PLAIN, 12));
		GroupLayout gl_panel_4 = new GroupLayout(panel_4);
		gl_panel_4.setHorizontalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_4.createSequentialGroup()
					.addContainerGap(69, Short.MAX_VALUE)
					.addComponent(lblStation_1, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblSeats)
					.addGap(26)
					.addComponent(lblTaken, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
					.addGap(33))
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
		);
		gl_panel_4.setVerticalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addGroup(gl_panel_4.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblStation_1, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSeats)
						.addComponent(lblTaken))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 213, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel_4.setLayout(gl_panel_4);
		
		JLabel lblStatus = new JLabel("T-Lock");
		lblStatus.setFont(new Font("Serif", Font.PLAIN, 12));
		
		JLabel lblMonitorLock = new JLabel("P-Lock");
		lblMonitorLock.setFont(new Font("Serif", Font.PLAIN, 12));
		
		lblWaiting = new JLabel("Waiting");
		lblWaiting.setFont(new Font("Serif", Font.PLAIN, 12));
		
		JPanel statusPanel = new JPanel();
		statusPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		
				statusPanel.setLayout(new GridLayout(0, 4, 1, 1));
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addComponent(statusPanel, GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addGap(75)
					.addComponent(lblStatus)
					.addGap(26)
					.addComponent(lblMonitorLock)
					.addGap(26)
					.addComponent(lblWaiting)
					.addContainerGap(37, Short.MAX_VALUE))
		);
		gl_panel_3.setVerticalGroup(
			gl_panel_3.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblStatus)
						.addComponent(lblMonitorLock)
						.addComponent(lblWaiting))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(statusPanel, GroupLayout.PREFERRED_SIZE, 269, GroupLayout.PREFERRED_SIZE))
		);
		panel_3.setLayout(gl_panel_3);
		
		for(int i = 0 ; i < 16 ; i++) {
			trainLabel[i] = new JLabel(" Train "+(i+1)+":");
			trainLabel[i].setEnabled(false);
			trainPanel.add(trainLabel[i]);
			
			currentStation[i] = new JTextField();
			currentStation[i].setText("0");
			currentStation[i].setEditable(false);
			currentStation[i].setColumns(1);
			currentStation[i].setBackground(Color.GRAY);
			currentStation[i].setEnabled(false);
			trainPanel.add(currentStation[i]);			
			
			trainSeats[i] = new JTextField();
			trainSeats[i].setText("0");
			trainSeats[i].setEditable(false);
			trainSeats[i].setColumns(1);
			trainSeats[i].setBackground(Color.GRAY);
			trainSeats[i].setEnabled(false);
			trainPanel.add(trainSeats[i]);
			
			trainTaken[i] = new JTextField();
			trainTaken[i].setText("0");
			trainTaken[i].setEditable(false);
			trainTaken[i].setColumns(1);
			trainTaken[i].setBackground(Color.GRAY);
			trainTaken[i].setEnabled(false);
			trainPanel.add(trainTaken[i]);
		}
		
		for(int i = 0 ; i < 8 ; i++) {
			JLabel lblStation = new JLabel(" Station "+(i+1)+":");
			statusPanel.add(lblStation);
			
			tLock[i] = new JTextField();
			tLock[i].setBackground(Color.GREEN);
			tLock[i].setText("FREE");
			tLock[i].setEditable(false);
			tLock[i].setColumns(1);
			statusPanel.add(tLock[i]);
			
			pLock[i] = new JTextField();
			pLock[i].setText("FREE");
			pLock[i].setEditable(false);
			pLock[i].setColumns(1);
			pLock[i].setBackground(Color.GREEN);
			statusPanel.add(pLock[i]);
			
			waitAmount[i] = new JTextField();
			waitAmount[i].setText("0");
			waitAmount[i].setEditable(false);
			waitAmount[i].setColumns(1);
			waitAmount[i].setBackground(Color.WHITE);
			statusPanel.add(waitAmount[i]);
		}

		panel.setLayout(gl_panel);
		frame.getContentPane().setLayout(groupLayout);
		
		frame.setVisible(true);
	}
}
