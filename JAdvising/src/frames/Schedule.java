package frames;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import classes.Courses;
import classes.StudentAccount;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

/*
 * Frame that displays the students schedule
 */
@SuppressWarnings("serial")
public class Schedule extends JFrame {

	private JPanel contentPane;
	public static JTable table;
	private JButton btnDrop;
	private JTextField fldID;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Schedule frame = new Schedule();
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
	public Schedule() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1001, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 16, 965, 458);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setEnabled(false);
		
		fldID = new JTextField();
		fldID.setHorizontalAlignment(SwingConstants.CENTER);
		fldID.setBounds(665, 514, 100, 30);
		contentPane.add(fldID);
		fldID.setColumns(10);
		
		btnDrop = new JButton("Drop");
		btnDrop.addActionListener(new ActionListener() {
			//If the drop button is clicked
			public void actionPerformed(ActionEvent arg0) {
				//If the student is currently enrolled in that course
				if(StudentAccount.courses.contains(fldID.getText())){
					//Attempt to drop the course
					Courses.dropClass(fldID.getText());
				}
				else {
					JOptionPane.showMessageDialog(null, "You are not enrolled in " + fldID.getText());
				}
			}
		});
		btnDrop.setBounds(780, 514, 200, 30);
		contentPane.add(btnDrop);
		
		JLabel lblId = new JLabel("ID");
		lblId.setHorizontalAlignment(SwingConstants.CENTER);
		lblId.setBounds(665, 490, 100, 20);
		contentPane.add(lblId);
	}
}
