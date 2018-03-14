package data;

import java.sql.PreparedStatement;

import javax.swing.JOptionPane;

import classes.StudentAccount;

/*
 * Allows us to edit the appointment database
 */
public class editAppointmentDB {
	public static void enrollAppointment(String id) {
		try {
			//Create a query to use in a PreparedStatement
				//Will update the status of the appointment that has been selected
			String query = "UPDATE appointments SET open = ?, student = ? WHERE id = ?";
			//Execute the query with the associated database connection
			PreparedStatement pst = sqliteConnection.connAppointment.prepareStatement(query);
			//Open = 0 (False)
			pst.setInt(1, 0);
			//student = id of the student requesting the appointment
			pst.setString(2, String.valueOf(StudentAccount.id));
			//id = id of the appointment
			pst.setString(3, id);
			//Update the database
			int update = pst.executeUpdate();
			
			//If an update occurred
			if (update > 0) {
				JOptionPane.showMessageDialog(null, "An email has been sent to your school email, confirming your appointment");
			}
			
			//Close the PreparedStatement
			pst.close();
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
}
