
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ChooseMenu {

	private JFrame frame;

	public ChooseMenu() {
		initialize();
	}

	public void setInvisible() {
		frame.dispose();
	}
	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btnSemaphores = new JButton("Semaphores");
		btnSemaphores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setInvisible();
				Interface.toSemaphore();
			}
		});
		btnSemaphores.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JButton btnMonitors = new JButton("Monitors");
		btnMonitors.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setInvisible();
				Interface.toMonitor();
			}
		});
		btnMonitors.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JLabel lblNewLabel = new JLabel("CalTrain II");
		lblNewLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 36));
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(81)
							.addComponent(btnSemaphores)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnMonitors))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(121)
							.addComponent(lblNewLabel)))
					.addContainerGap(113, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(36)
					.addComponent(lblNewLabel)
					.addGap(59)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSemaphores)
						.addComponent(btnMonitors))
					.addContainerGap(130, Short.MAX_VALUE))
		);
		frame.getContentPane().setLayout(groupLayout);
		frame.setVisible(true);
	}
}
