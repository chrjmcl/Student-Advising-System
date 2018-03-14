package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import classes.AdvisorAccount;
import frames.AdvisingAppointment;
import frames.AdvisorSchedule;
import net.proteanit.sql.DbUtils;

/*
 * Allows us to obtain information from the appointment database
 */
public class readAppointmentDB {
	
	//Obtain all the appointments in the database and display them in a
	//table
	public static void populateAppointments() {
		try {
			//Create a query to use in a PreparedStatement
				//Will find all appointments
			String query = "select * from appointments";
			//Execute the query with the associated database connection
			PreparedStatement pst = sqliteConnection.connAppointment.prepareStatement(query);
			//Results of the PreparedStatement
			ResultSet rs = pst.executeQuery();
			//Populate the table with this ResultSet
			AdvisingAppointment.table.setModel(DbUtils.resultSetToTableModel(rs));
			//Close the data reception from the database
			rs.close();
			pst.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
	
	//Checks whether or not this appointment is open
	public static boolean checkStatus(String id) {
		try {
			//Create a query to use in a PreparedStatement
				//Will find all information about the specific appointment
			String query = "select * from appointments where id=?";
			//Execute the query with the associated database connection
			PreparedStatement pst = sqliteConnection.connAppointment.prepareStatement(query);
			//id = id of the appointment the user is attempting to schedule
			pst.setString(1, id);
			//Results of the PreparedStatement
			ResultSet rs = pst.executeQuery();
			//If the appointment is not open
			if(rs.getInt("open")==0) {
				return false;
			}
			//Close the data reception from the database
			rs.close();
			pst.close();
			//The appointment is not taken
			return true;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
			return false;
		}
	}
	
	//Populates the table of appointments with appointments that have been scheduled,
	//and match the name of the currently logged in advisor
	public static void populateFilledAppointments() {
		try {
			//Create a query to use in a PreparedStatement
				//Will find all information about scheduled appointment for a specific advisor
			String query = "select * from appointments WHERE open=0 and advisor=?";
			//Execute the query with the associated database connection
			PreparedStatement pst = sqliteConnection.connAppointment.prepareStatement(query);
			//advisor = the currently logged in advisor
			pst.setString(1, AdvisorAccount.name);
			//Results of the PreparedStatement
			ResultSet rs = pst.executeQuery();
			//Populate the table with the ResultSet 
				//Only displays the scheduled appointment for the currently logged in advisor
				//Unscheduled appointments will not be visible
			AdvisorSchedule.table.setModel(DbUtils.resultSetToTableModel(rs));
			//Close the data reception from the database
			rs.close();
			pst.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
}
