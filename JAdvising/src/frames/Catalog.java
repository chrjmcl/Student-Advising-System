package frames;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import classes.Courses;


import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/*
 * Frame that displays all of the courses and their information to the student
 * 
 * Allows the student the option to enroll in any course that they meet the requirements to
 */
@SuppressWarnings("serial")
public class Catalog extends JFrame {

	private JPanel contentPane;
	public static JTable table;
	private JTextField fldID;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Catalog frame = new Catalog();
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
	public Catalog() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1000, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 16, 964, 462);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setEnabled(false);
		
		fldID = new JTextField();
		fldID.setHorizontalAlignment(SwingConstants.CENTER);
		fldID.setBounds(664, 514, 100, 30);
		contentPane.add(fldID);
		fldID.setColumns(10);
		
		JButton btnEnroll = new JButton("Enroll");
		btnEnroll.addActionListener(new ActionListener() {
			//When the enroll button is clicked
			public void actionPerformed(ActionEvent arg0) {
				//Attempt to add the course to the students schedule
				Courses.addClass(fldID.getText());
				
			}
		});
		btnEnroll.setBounds(779, 514, 200, 30);
		contentPane.add(btnEnroll);
		
		JLabel lblId = new JLabel("ID");
		lblId.setHorizontalAlignment(SwingConstants.CENTER);
		lblId.setBounds(664, 494, 100, 20);
		contentPane.add(lblId);
	}
}
