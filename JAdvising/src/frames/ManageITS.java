package frames;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import data.editAccountDB;

import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class ManageITS extends JFrame {

	private JPanel contentPane;
	public static JTable table;
	private JTextField fldID;
	private JTextField fldPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManageITS frame = new ManageITS();
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
	public ManageITS() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1000, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 16, 964, 490);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setEnabled(false);
		
		JLabel lblId = new JLabel("ID");
		lblId.setHorizontalAlignment(SwingConstants.CENTER);
		lblId.setBounds(462, 542, 146, 20);
		contentPane.add(lblId);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setBounds(623, 542, 154, 20);
		contentPane.add(lblPassword);
		
		fldID = new JTextField();
		fldID.setHorizontalAlignment(SwingConstants.CENTER);
		fldID.setBounds(462, 564, 150, 30);
		contentPane.add(fldID);
		fldID.setColumns(10);
		
		fldPassword = new JTextField();
		fldPassword.setHorizontalAlignment(SwingConstants.CENTER);
		fldPassword.setBounds(627, 564, 150, 30);
		contentPane.add(fldPassword);
		fldPassword.setColumns(10);
		
		JButton btnChange = new JButton("Change");
		btnChange.addActionListener(new ActionListener() {
			//When the change button is clicked
			public void actionPerformed(ActionEvent arg0) {
				//Change the password of the student with ID input by user
				editAccountDB.changeStudentPassword(fldID.getText(), fldPassword.getText());
			}
		});
		btnChange.setBounds(829, 564, 150, 30);
		contentPane.add(btnChange);
	}
}
