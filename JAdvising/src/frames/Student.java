package frames;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import classes.StudentAccount;
import data.readAppointmentDB;
import data.readCourseDB;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

/*
 * Frame that acts as the main page for the student
 */
@SuppressWarnings("serial")
public class Student extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Student frame = new Student();
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
	public Student() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Catalog ctlg = new Catalog();
		JButton btnCourseCatalog = new JButton("Course Catalog");
		btnCourseCatalog.addActionListener(new ActionListener() {
			//If the Course Catalog button is clicked
			public void actionPerformed(ActionEvent arg0) {
				//If the Catalog frame is not visible
				if(!ctlg.isVisible()) {
					//Populate the table of courses
					readCourseDB.populateCourses();
					//Set the catalog frame to visible
					ctlg.setVisible(true);
				}
			}
		});
		btnCourseCatalog.setBounds(15, 164, 150, 30);
		contentPane.add(btnCourseCatalog);
		
		Schedule schdle = new Schedule();
		JButton btnSchedule = new JButton("Schedule");
		btnSchedule.addActionListener(new ActionListener() {
			//If the Schedule button is clicked
			public void actionPerformed(ActionEvent e) {
				//If the Schedule frame is not visible
				if(!schdle.isVisible()) {
					//Populate the table of the students enrolled courses
					readCourseDB.populateCourseSchedule();
					//Set the Schedule frame to visible
					schdle.setVisible(true);
				}
			}
		});
		btnSchedule.setBounds(180, 164, 150, 30);
		contentPane.add(btnSchedule);
		
		ManageAccount mngAcc = new ManageAccount();
		JButton btnManageAccount = new JButton("Manage Account");
		btnManageAccount.addActionListener(new ActionListener() {
			//If the Manage Account button is clicked
			public void actionPerformed(ActionEvent e) {
				//If the ManageAccount frame is not visible
				if(!mngAcc.isVisible()) {
					//Set the ManageAccount frame to visisble
					mngAcc.setVisible(true);
				}
			}
		});
		btnManageAccount.setBounds(364, 164, 150, 30);
		contentPane.add(btnManageAccount);
		
		JLabel lblName = new JLabel(StudentAccount.name);
		lblName.setFont(new Font("MV Boli", Font.PLAIN, 20));
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setBounds(15, 58, 664, 20);
		contentPane.add(lblName);
		
		JLabel lblID = new JLabel(String.valueOf(StudentAccount.id));
		lblID.setFont(new Font("MV Boli", Font.PLAIN, 12));
		lblID.setHorizontalAlignment(SwingConstants.CENTER);
		lblID.setBounds(15, 86, 664, 20);
		contentPane.add(lblID);
		
		AdvisingAppointment advAptmnt = new AdvisingAppointment();
		JButton btnAdvisorAppointment = new JButton("Advising");
		btnAdvisorAppointment.addActionListener(new ActionListener() {
			//If the Advising button is clicked
			public void actionPerformed(ActionEvent arg0) {
				//Is the AdvisingAppointment frame is not visible
				if(!advAptmnt.isVisible()) {
					//Populate the table of appointments
					readAppointmentDB.populateAppointments();
					//Set the AdvisingAppointment frame to visisble
					advAptmnt.setVisible(true);
				}
			}
		});
		btnAdvisorAppointment.setBounds(529, 164, 150, 30);
		contentPane.add(btnAdvisorAppointment);
		
		JLabel lblWelcome = new JLabel("Welcome");
		lblWelcome.setFont(new Font("MV Boli", Font.PLAIN, 22));
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setBounds(15, 16, 664, 20);
		contentPane.add(lblWelcome);
	}
}
