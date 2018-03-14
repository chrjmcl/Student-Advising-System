package frames;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import data.readAccountDB;
import data.sqliteConnection;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JCheckBox;

/*
 * Login page for all users
 */
public class Login {
	private JFrame frame;
	private JTextField fldUsername;
	private JPasswordField fldPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
		//Connect to the databases
			//Account Database
		sqliteConnection.accountConnector();
			//Course Database
		sqliteConnection.courseConnector();
			//Appointment Database
		sqliteConnection.appointmentConnector();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 700, 376);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setHorizontalAlignment(SwingConstants.TRAILING);
		lblUsername.setBounds(160, 165, 79, 20);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setHorizontalAlignment(SwingConstants.TRAILING);
		lblPassword.setBounds(160, 209, 79, 20);
		frame.getContentPane().add(lblPassword);
		
		fldUsername = new JTextField();
		fldUsername.setBounds(247, 160, 200, 30);
		frame.getContentPane().add(fldUsername);
		fldUsername.setColumns(10);
		
		fldPassword = new JPasswordField();
		fldPassword.setBounds(247, 204, 200, 30);
		frame.getContentPane().add(fldPassword);


		JCheckBox chckbxAdvisor = new JCheckBox("Advisor");
		chckbxAdvisor.setBounds(11, 295, 139, 29);
		frame.getContentPane().add(chckbxAdvisor);
		
		JCheckBox chckbxITS = new JCheckBox("ITS");
		chckbxITS.setBounds(11, 270, 139, 29);
		frame.getContentPane().add(chckbxITS);
		
		JButton btnLogin = new JButton("Login");
		//When a user clicks the button "Login", do the following:
		btnLogin.addActionListener(new ActionListener() {
			//When the Login button is clicked
			public void actionPerformed(ActionEvent arg0) {
				//If the advisor and ITS checkbox is not clicked
				if (!chckbxAdvisor.isSelected() && !chckbxITS.isSelected()) {
					//Attempt to sign in as a student
					readAccountDB.verifyLoginStudent(fldUsername.getText(), String.valueOf(fldPassword.getPassword()), frame);
				}
				//If only the advisor checkbox is clicked
				else if (chckbxAdvisor.isSelected() && !chckbxITS.isSelected()){
					//Attempt to sign in as an advisor
					readAccountDB.verifyLoginAdvisor(fldUsername.getText(),String.valueOf(fldPassword.getPassword()), frame);
				}
				//If only the ITS checkbox is clicked
				else if(!chckbxAdvisor.isSelected() && chckbxITS.isSelected()) {
					//Attempt to sign in as ITS
					readAccountDB.verifyLoginITS(fldUsername.getText(), String.valueOf(fldPassword.getPassword()), frame);
				}
				//If both checkboxes are clicked
				else {
					JOptionPane.showMessageDialog(null, "Both check boxes are select, please unselect one or both");
				}
			}
		});
		btnLogin.setBounds(247, 294, 200, 30);
		frame.getContentPane().add(btnLogin);
		
		JButton btnHelp = new JButton("Help");
		btnHelp.addActionListener(new ActionListener() {
			//If the help button is clicked
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Please contact support@syr.edu regarding your issue");
			}
		});
		btnHelp.setBounds(604, 294, 75, 30);
		frame.getContentPane().add(btnHelp);
		
		JLabel lblJadvising = new JLabel("JAdvising");
		lblJadvising.setFont(new Font("MV Boli", Font.BOLD, 30));
		lblJadvising.setBounds(265, 33, 155, 111);
		frame.getContentPane().add(lblJadvising);
	}
}
