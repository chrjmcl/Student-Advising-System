package frames;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import classes.Courses;
import data.readCourseDB;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

/*
 * Frame that acts as a form for the advisor to fill out
 */
@SuppressWarnings("serial")
public class DeleteCourse extends JFrame {

	private JPanel contentPane;
	private JTextField idFld;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteCourse frame = new DeleteCourse();
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
	public DeleteCourse() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblId = new JLabel("ID");
		lblId.setHorizontalAlignment(SwingConstants.CENTER);
		lblId.setBounds(15, 59, 398, 20);
		contentPane.add(lblId);
		
		idFld = new JTextField();
		idFld.setHorizontalAlignment(SwingConstants.CENTER);
		idFld.setBounds(15, 95, 398, 30);
		contentPane.add(idFld);
		idFld.setColumns(10);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			//When the Delete button is clicked
			public void actionPerformed(ActionEvent e) {
				//If the course has no one enrolled in it
				if(readCourseDB.emptyCourse(idFld.getText())) {
					//Delete the course
					Courses.deleteCourse(idFld.getText());
				}
				else {
					JOptionPane.showMessageDialog(null, idFld.getText() + " is not empty, cannot be deleted");
				}
			}
		});
		btnDelete.setBounds(15, 198, 398, 30);
		contentPane.add(btnDelete);
	}

}
