package frames;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import classes.Courses;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;


/*
 * JFrame that allows the advisor to easily create a new course
 */
@SuppressWarnings("serial")
public class AddCourse extends JFrame {

	private JPanel contentPane;
	private JTextField idFld;
	private JTextField nameFld;
	private JTextField instructorFld;
	private JTextField daysFld;
	private JTextField timeFld;
	private JTextField locationFld;
	private JTextField prereqsFld;
	private JTextField populationFld;
	private JTextField creditsFld;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddCourse frame = new AddCourse();
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
	public AddCourse() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblId = new JLabel("ID");
		lblId.setHorizontalAlignment(SwingConstants.CENTER);
		lblId.setBounds(15, 28, 414, 20);
		contentPane.add(lblId);
		
		idFld = new JTextField();
		idFld.setHorizontalAlignment(SwingConstants.CENTER);
		idFld.setBounds(15, 52, 414, 26);
		contentPane.add(idFld);
		idFld.setColumns(10);
		
		JLabel lblName = new JLabel("Name");
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setBounds(15, 94, 414, 20);
		contentPane.add(lblName);
		
		nameFld = new JTextField();
		nameFld.setHorizontalAlignment(SwingConstants.CENTER);
		nameFld.setBounds(15, 118, 414, 26);
		contentPane.add(nameFld);
		nameFld.setColumns(10);
		
		JLabel lblInstructor = new JLabel("Instructor");
		lblInstructor.setHorizontalAlignment(SwingConstants.CENTER);
		lblInstructor.setBounds(15, 172, 414, 20);
		contentPane.add(lblInstructor);
		
		instructorFld = new JTextField();
		instructorFld.setHorizontalAlignment(SwingConstants.CENTER);
		instructorFld.setBounds(15, 199, 414, 26);
		contentPane.add(instructorFld);
		instructorFld.setColumns(10);
		
		JLabel lblDays = new JLabel("Days");
		lblDays.setHorizontalAlignment(SwingConstants.CENTER);
		lblDays.setBounds(15, 248, 414, 20);
		contentPane.add(lblDays);
		
		daysFld = new JTextField();
		daysFld.setHorizontalAlignment(SwingConstants.CENTER);
		daysFld.setBounds(15, 273, 414, 26);
		contentPane.add(daysFld);
		daysFld.setColumns(10);
		
		JLabel lblTime = new JLabel("Time");
		lblTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblTime.setBounds(15, 315, 414, 20);
		contentPane.add(lblTime);
		
		timeFld = new JTextField();
		timeFld.setHorizontalAlignment(SwingConstants.CENTER);
		timeFld.setBounds(15, 339, 414, 26);
		contentPane.add(timeFld);
		timeFld.setColumns(10);
		
		JLabel lblLocation = new JLabel("Location");
		lblLocation.setHorizontalAlignment(SwingConstants.CENTER);
		lblLocation.setBounds(15, 382, 414, 20);
		contentPane.add(lblLocation);
		
		locationFld = new JTextField();
		locationFld.setHorizontalAlignment(SwingConstants.CENTER);
		locationFld.setBounds(15, 408, 414, 26);
		contentPane.add(locationFld);
		locationFld.setColumns(10);
		
		JLabel lblPrereqs = new JLabel("Prereqs");
		lblPrereqs.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrereqs.setBounds(15, 453, 414, 20);
		contentPane.add(lblPrereqs);
		
		prereqsFld = new JTextField();
		prereqsFld.setHorizontalAlignment(SwingConstants.CENTER);
		prereqsFld.setBounds(15, 477, 414, 26);
		contentPane.add(prereqsFld);
		prereqsFld.setColumns(10);
		
		JLabel lblMaxPopulation = new JLabel("Max Population");
		lblMaxPopulation.setHorizontalAlignment(SwingConstants.CENTER);
		lblMaxPopulation.setBounds(15, 521, 414, 20);
		contentPane.add(lblMaxPopulation);
		
		populationFld = new JTextField();
		populationFld.setHorizontalAlignment(SwingConstants.CENTER);
		populationFld.setBounds(15, 546, 414, 26);
		contentPane.add(populationFld);
		populationFld.setColumns(10);
		
		JLabel lblCredits = new JLabel("Credits");
		lblCredits.setHorizontalAlignment(SwingConstants.CENTER);
		lblCredits.setBounds(15, 588, 414, 20);
		contentPane.add(lblCredits);
		
		creditsFld = new JTextField();
		creditsFld.setHorizontalAlignment(SwingConstants.CENTER);
		creditsFld.setBounds(15, 612, 414, 26);
		contentPane.add(creditsFld);
		creditsFld.setColumns(10);
		
		JButton btnCreate = new JButton("Create");
		//When the create button is clicked, perform the following
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Create the course with the parameters input by the advisor
				Courses.createCourse(idFld.getText(), nameFld.getText(), instructorFld.getText(), 
						daysFld.getText(), timeFld.getText(), locationFld.getText(), prereqsFld.getText(), 
						Integer.parseInt(populationFld.getText()), Integer.parseInt(creditsFld.getText()));
			}
		});
		btnCreate.setBounds(15, 714, 414, 30);
		contentPane.add(btnCreate);
	}
}
