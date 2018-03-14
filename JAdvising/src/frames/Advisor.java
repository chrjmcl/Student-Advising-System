package frames;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import classes.AdvisorAccount;
import data.readAccountDB;
import data.readAppointmentDB;
import data.readCourseDB;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

/*
 * JFrame that acts as the main page for the advisor
 * 
 * Allows the advisor to view their schedule, the catalog, or information about students
 */
@SuppressWarnings("serial")
public class Advisor extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Advisor frame = new Advisor();
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
	public Advisor() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 251);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		AdvisorSchedule AdvSchdle = new AdvisorSchedule();
		JButton btnCalendar = new JButton("Calendar");
		btnCalendar.addActionListener(new ActionListener() {
			//When the Calendar button is clicked
			public void actionPerformed(ActionEvent arg0) {
				//If AdvisorSchedule Frame is not already opened
				if(!AdvSchdle.isVisible()) {
					//Populate the Table of scheduled appointments
					readAppointmentDB.populateFilledAppointments();
					//Set this AdvisorScedule Frame to visible
					AdvSchdle.setVisible(true);
				}
			}
		});
		btnCalendar.setBounds(15, 164, 200, 30);
		contentPane.add(btnCalendar);
		
		AdvisorCatalog AdvCatalog = new AdvisorCatalog();
		JButton btnCourseCatalog = new JButton("Course Catalog");
		btnCourseCatalog.addActionListener(new ActionListener() {
			//When the Course Catalog button is pressed
			public void actionPerformed(ActionEvent e) {
				//If the Advisor Catalog is not already visible
				if(!AdvCatalog.isVisible()) {
					//Populate the table with all the courses
					readCourseDB.populateCoursesAdvisor();
					//Set the AdvisorCatalog frame to visible
					AdvCatalog.setVisible(true);
				}
			}
		});
		btnCourseCatalog.setBounds(248, 164, 200, 30);
		contentPane.add(btnCourseCatalog);
		
		StudentAccounts SAccounts = new StudentAccounts();
		JButton btnStudentAccounts = new JButton("Student Accounts");
		btnStudentAccounts.addActionListener(new ActionListener() {
			//If the Student Accounts button is clicked
			public void actionPerformed(ActionEvent e) {
				//If the StudentAccounts frame is not already visible
				if(!SAccounts.isVisible()) {
					//Populate the table with all of the students
					readAccountDB.populateStudents();
					//Set the StudentAccounts frame to visible
					SAccounts.setVisible(true);
				}
			}
		});
		btnStudentAccounts.setBounds(479, 164, 200, 30);
		contentPane.add(btnStudentAccounts);
		
		JLabel lblWelcome = new JLabel("Welcome");
		lblWelcome.setFont(new Font("MV Boli", Font.PLAIN, 22));
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setBounds(15, 16, 664, 20);
		contentPane.add(lblWelcome);
		
		JLabel lblName = new JLabel(AdvisorAccount.name);
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setFont(new Font("MV Boli", Font.PLAIN, 20));
		lblName.setBounds(15, 52, 664, 20);
		contentPane.add(lblName);
	}
}
