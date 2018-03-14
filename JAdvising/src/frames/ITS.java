package frames;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import classes.ITSStaff;
import data.readAccountDB;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/*
 * Frame that acts as the main page for ITS accounts 
 */
@SuppressWarnings("serial")
public class ITS extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ITS frame = new ITS();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ITS() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 700, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblWelcome = new JLabel("Welcome");
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setFont(new Font("MV Boli", Font.PLAIN, 22));
		lblWelcome.setBounds(0, 16, 694, 20);
		contentPane.add(lblWelcome);
		
		JLabel lblName = new JLabel(ITSStaff.name);
		lblName.setFont(new Font("MV Boli", Font.PLAIN, 20));
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setBounds(0, 52, 694, 20);
		contentPane.add(lblName);
		
		ManageITS mngITS = new ManageITS();
		JButton btnManage = new JButton("Manage");
		btnManage.addActionListener(new ActionListener() {
			//When the manage button is clicked
			public void actionPerformed(ActionEvent e) {
				//If the StudentAccounts frame is not visible
				if(!mngITS.isVisible()) {
					//Populate the table with student information
					readAccountDB.populateStudentsITS();
					//Set the StudentAccounts frame to visible 
					mngITS.setVisible(true);
				}
			}
		});
		btnManage.setBounds(81, 164, 200, 30);
		contentPane.add(btnManage);
		
		JButton btnTroubleshoot = new JButton("Troubleshoot");
		btnTroubleshoot.addActionListener(new ActionListener() {
			//If the Troubleshoot button is clicked
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Please check your support@syr.edu inbox");
			}
		});
		btnTroubleshoot.setBounds(418, 164, 200, 30);
		contentPane.add(btnTroubleshoot);
	}

}
