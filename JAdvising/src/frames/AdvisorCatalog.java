package frames;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/*
 * JFrame that displays all of the courses to the advisor
 * 
 * Allows the Advisor the option to create or delete a course
 */
@SuppressWarnings("serial")
public class AdvisorCatalog extends JFrame {

	private JPanel contentPane;
	public static JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdvisorCatalog frame = new AdvisorCatalog();
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
	public AdvisorCatalog() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1000, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 16, 964, 492);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setEnabled(false);
		
		AddCourse addC = new AddCourse();
		JButton btnAddCourse = new JButton("Add Course");
		btnAddCourse.addActionListener(new ActionListener() {
			//If the Add Course button is clicked
			public void actionPerformed(ActionEvent e) {
				//If AddCourse frame is not visible
				if(!addC.isVisible()) {
					//Set the AddCourse frame to visible
					addC.setVisible(true);
				}
			}
		});
		btnAddCourse.setBounds(829, 524, 150, 30);
		contentPane.add(btnAddCourse);
		
		DeleteCourse deleteC = new DeleteCourse();
		JButton btnDeleteCourse = new JButton("Delete Course");
		btnDeleteCourse.addActionListener(new ActionListener() {
			//If the Delete Course button is clicked
			public void actionPerformed(ActionEvent arg0) {
				//If the DeleteCourse frame is not visible
				if(!deleteC.isVisible()) {
					//Set the DeleteCourse frame to visible
					deleteC.setVisible(true);
				}
			}
		});
		btnDeleteCourse.setBounds(664, 524, 150, 30);
		contentPane.add(btnDeleteCourse);
	}
}
