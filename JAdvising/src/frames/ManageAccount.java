package frames;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import classes.StudentAccount;
import data.editAccountDB;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Font;

/*
 * Frame that allows the student to see information about their account, as well as change their password
 */
@SuppressWarnings("serial")
public class ManageAccount extends JFrame {

	private JPanel contentPane;
	private JTextField fldPassword;
	static JLabel lblCreditsInfo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManageAccount frame = new ManageAccount();
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
	public ManageAccount() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1000, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsername.setBounds(15, 16, 964, 20);
		contentPane.add(lblUsername);
		
		JLabel lblMajor = new JLabel("Major");
		lblMajor.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblMajor.setHorizontalAlignment(SwingConstants.CENTER);
		lblMajor.setBounds(15, 90, 964, 20);
		contentPane.add(lblMajor);
		
		JLabel lblCredits = new JLabel("Credits");
		lblCredits.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblCredits.setHorizontalAlignment(SwingConstants.CENTER);
		lblCredits.setBounds(15, 179, 964, 20);
		contentPane.add(lblCredits);
		
		JLabel lblCompletedCourses = new JLabel("Completed Courses");
		lblCompletedCourses.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblCompletedCourses.setHorizontalAlignment(SwingConstants.CENTER);
		lblCompletedCourses.setBounds(15, 274, 964, 20);
		contentPane.add(lblCompletedCourses);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setBounds(15, 377, 964, 20);
		contentPane.add(lblPassword);
		
		fldPassword = new JTextField();
		fldPassword.setHorizontalAlignment(SwingConstants.CENTER);
		fldPassword.setBounds(429, 413, 150, 30);
		contentPane.add(fldPassword);
		fldPassword.setColumns(10);
		
		JButton btnChange = new JButton("Change");
		btnChange.addActionListener(new ActionListener() {
			//If the change button is clicked
			public void actionPerformed(ActionEvent arg0) {
				//Change the students password
				editAccountDB.changePassword(fldPassword.getText());
			}
		});
		btnChange.setBounds(411, 460, 200, 30);
		contentPane.add(btnChange);
		
		JLabel lblUsernameInfo = new JLabel(StudentAccount.username);
		lblUsernameInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsernameInfo.setBounds(15, 52, 964, 20);
		contentPane.add(lblUsernameInfo);
		
		JLabel lblMajorInfo = new JLabel(StudentAccount.major);
		lblMajorInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblMajorInfo.setBounds(15, 136, 964, 20);
		contentPane.add(lblMajorInfo);
		
		lblCreditsInfo = new JLabel(String.valueOf(StudentAccount.credits));
		lblCreditsInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblCreditsInfo.setBounds(15, 221, 964, 20);
		contentPane.add(lblCreditsInfo);
		
		String completed = "";
		for(int i = 0; i < StudentAccount.taken1.size(); i++) {
			completed = completed + " " + StudentAccount.taken1.get(i);
		}
		JLabel lblCompletedCourseInfo = new JLabel(completed);
		lblCompletedCourseInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblCompletedCourseInfo.setBounds(15, 310, 964, 20);
		contentPane.add(lblCompletedCourseInfo);
	}
	
	//Update the label displaying the amount of credits the student is taking
	public static void updateCredits(Integer credits) {
		lblCreditsInfo.setText(String.valueOf(StudentAccount.credits));
	}
}
