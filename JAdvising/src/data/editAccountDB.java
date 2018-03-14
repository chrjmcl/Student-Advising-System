package data;

import java.sql.PreparedStatement;

import javax.swing.JOptionPane;

import classes.Courses;
import classes.StudentAccount;

/*
 * Allows us to edit the account database
 */
public class editAccountDB {
	
	//The account database will be edited to reflect their request to change their passsword
	public static void changePassword(String pwd) {
		try {
			//Create a query to use in a PreparedStatement
				//Will update the password for this specific student
			String query = "UPDATE students SET password = ?  WHERE username = ?";
			//Execute the query with the associated database connection
			PreparedStatement pst = sqliteConnection.connAccount.prepareStatement(query);
			//password = updated password
			pst.setString(1, pwd);
			//username = username of currently logged in student
			pst.setString(2, StudentAccount.username);
			//Update the database
			int update = pst.executeUpdate();
			
			//If an update occurred
			if (update > 0) {
				JOptionPane.showMessageDialog(null, "Password has been changed");
			}
			
			//Close the PreparedStatement
			pst.close();
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
	
	//The account database will be updated to depict the dropped course changes of the student
	public static void dropEditStudent(String id) {		
		try {
			//Create a query to use in a PreparedStatement
				//Will update the credits and courses for this specific student
			String query = "UPDATE students SET credits = ? , courses = ? WHERE username = ?";
			//Execute the query with the associated database connection
			PreparedStatement pst = sqliteConnection.connAccount.prepareStatement(query);
			//credits = students current credits
			pst.setInt(1, StudentAccount.credits);
			//courses = string of student's currently enrolled courses
			pst.setString(2, StudentAccount.coursesStr);
			//username = username of currently logged in student
			pst.setString(3, StudentAccount.username);
			//Update the database
			int update = pst.executeUpdate();
			
			//If an update occurred
			if (update > 0) {
				JOptionPane.showMessageDialog(null, "Your credits and courses have been updated");
			}
			
			//Close the PreparedStatement
			pst.close();
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
	
	//Based on the class that the student has selected to enroll in
	//their credits will be adjusted accordingly
	public static void updateCredits(String id) {
		try {
			//Create a query to use in a PreparedStatement
				//Will update the credits for this specific student
			String query = "UPDATE students SET credits = ? + credits WHERE username = ?";
			//Execute the query with the associated database connection
			PreparedStatement pst = sqliteConnection.connAccount.prepareStatement(query);
			//credits = students current credits
			pst.setInt(1, Courses.credits);
			//username = username of currently logged in student
			pst.setString(2, StudentAccount.username);
			//Update the database
			int update = pst.executeUpdate();
			
			//If an update occurred
			if (update > 0) {
				JOptionPane.showMessageDialog(null, "Your credits have been updated");
			}
			
			//Close the PreparedStatement
			pst.close();
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
	
	//The current courses of the student will be updated based on what
	//class they selected to enroll in
	public static void updateCurrentCourses(String id) {
		try {
			//Create a query to use in a PreparedStatement
				//Will update the list of courses for this specific student
			String query = "UPDATE students SET courses = ?  WHERE username = ?";
			//Execute the query with the associated database connection
			PreparedStatement pst = sqliteConnection.connAccount.prepareStatement(query);
			//courses = string of student's currently enrolled courses
			pst.setString(1, StudentAccount.coursesStr);
			//username = username of currently logged in student
			pst.setString(2, StudentAccount.username);
			//Update the database
			int update = pst.executeUpdate();
			
			//If an update occurred
			if (update > 0) {
				JOptionPane.showMessageDialog(null, "Your current courses have been updated");
			}
			
			//Close the PreparedStatement
			pst.close();
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
	
	//An ITS staff member is attempting to change the password of a student
	public static void changeStudentPassword(String id, String password) {
		try {
			//Create a query to use in a PreparedStatement
			String query = "Update students Set password = ? WHERE id = ?";
			//Execute the qury with the associated database connection
			PreparedStatement pst = sqliteConnection.connAccount.prepareStatement(query);
			//password = password input by ITS
			pst.setString(1, password);
			//id = students id input by ITS
			pst.setString(2, id);
			//Update the database
			int update = pst.executeUpdate();
			
			//If an update occurred
			if (update > 0) {
				JOptionPane.showMessageDialog(null, "Password for " + id + " has been changed");
			}
			
			//Close the PreparedStatement
			pst.close();
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
		
	}
}
