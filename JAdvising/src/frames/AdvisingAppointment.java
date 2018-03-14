package frames;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import classes.Appointment;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/*
 * JFrame that allows the students to see available times to schedule an advising appointment
 */
@SuppressWarnings("serial")
public class AdvisingAppointment extends JFrame {

	private JPanel contentPane;
	public static JTable table;
	private JTextField idFld;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdvisingAppointment frame = new AdvisingAppointment();
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
	public AdvisingAppointment() {
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
		
		idFld = new JTextField();
		idFld.setHorizontalAlignment(SwingConstants.CENTER);
		idFld.setBounds(664, 514, 150, 30);
		contentPane.add(idFld);
		idFld.setColumns(10);
		
		JButton btnSchedule = new JButton("Schedule");
		//When the schedule button is pressed
		btnSchedule.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Attempt to schedule an appointment with the id input from the student
				Appointment.requestAppointment(idFld.getText());
			}
		});
		btnSchedule.setBounds(829, 514, 150, 30);
		contentPane.add(btnSchedule);
		
		JLabel lblId = new JLabel("ID");
		lblId.setHorizontalAlignment(SwingConstants.CENTER);
		lblId.setBounds(664, 492, 150, 20);
		contentPane.add(lblId);
		
	}
}
