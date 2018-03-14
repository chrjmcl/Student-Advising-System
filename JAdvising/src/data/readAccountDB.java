package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import classes.AdvisorAccount;
import classes.ITSStaff;
import classes.StudentAccount;
import frames.Advisor;
import frames.ITS;
import frames.ManageITS;
import frames.Student;
import frames.StudentAccounts;
import net.proteanit.sql.DbUtils;

/*
 * Allows us to obtain information from the account database
 */
public class readAccountDB {
	//Function verifies that the information supplied by the user is valid
	//If it is valid, it will dispose of the login frame and bring you to
	//the student home page
	public static void verifyLoginStudent(String username, String password, JFrame frame) {
		try {
			//Create a query for the username and password
			String query = " select * from students where username=? and password=? ";
			//Create a PreparedStatement so we can receive data from the database
			PreparedStatement pst = sqliteConnection.connAccount.prepareStatement(query);
			//username = username input by user
			pst.setString(1, username);
			//password = password input by user
			pst.setString(2,  password);
			
			//Set of all the information in the database
			ResultSet rs = pst.executeQuery();
			
			//Count the occurences of username and password on the same row
			int count = 0;
			while(rs.next()) {
				count++;
			}
			
			//Information provided is valid
			if(count == 1) {
				//Removes the login frame
				frame.dispose();
				
				//Reads the database to find out various info about the student
				StudentAccount.getStudent(username);
				
				//Display the student frame
				Student stdnt = new Student();
				stdnt.setVisible(true);
			}
			//Multiple of the same account exists in the database
			else if (count > 1) {
				JOptionPane.showMessageDialog(null, "Duplicate Username and Password");
			}
			//User does not exist in the database or password is incorrect
			else {
				JOptionPane.showMessageDialog(null, "Username and/or Password is not correct");
			}
			
			//Close the data reception from the database
			rs.close();
			pst.close();
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
	
	
	//Function verifies that the information supplied by the user is valid
	//If it is valid, it will dispose of the login frame and bring you to
	//the advisor home page
	public static void verifyLoginAdvisor(String username, String password, JFrame frame) {
		try {
			//Create a query for the username and password
			String query = " select * from advisors where username=? and password=? ";
			//Create a PreparedStatement so we can receive data from the database
			PreparedStatement pst = sqliteConnection.connAccount.prepareStatement(query);
			//username = username input by user
			pst.setString(1, username);
			//password = password input by user
			pst.setString(2,  password);
			
			//Set of all the information in the database
			ResultSet rs = pst.executeQuery();
			
			//Count the occurences of username and password on the same row
			int count = 0;
			while(rs.next()) {
				count++;
			}
			
			//Information provided is valid
			if(count == 1) {
				//Removes the login frame
				frame.dispose();
				
				//Reads the database to find out various info about the advisor
				advisorInfo(username);
				
				//Display the advisor frame
				Advisor adv = new Advisor();
				adv.setVisible(true);
			}
			//Multiple of the same account exists in the database
			else if (count > 1) {
				JOptionPane.showMessageDialog(null, "Duplicate Username and Password");
			}
			//User does not exist in the database or password is incorrect
			else {
				JOptionPane.showMessageDialog(null, "Username and/or Password is not correct");
			}
			//Close the data reception from the database
			rs.close();
			pst.close();
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
	
	public static void verifyLoginITS(String username, String password, JFrame frame) {
		try {
			//Create a query for the username and password
			String query = " select * from its where username=? and password=? ";
			//Create a PreparedStatement so we can receive data from the database
			PreparedStatement pst = sqliteConnection.connAccount.prepareStatement(query);
			//username = username input by user
			pst.setString(1, username);
			//password = password input by user
			pst.setString(2,  password);
			
			//Set of all the information in the database
			ResultSet rs = pst.executeQuery();
			
			//Count the occurences of username and password on the same row
			int count = 0;
			while(rs.next()) {
				count++;
			}
			
			//Information provided is valid
			if(count == 1) {
				//Removes the login frame
				frame.dispose();
				
				//Reads the database to find out various info about the student
				itsInfo(username);
				
				//Display the ITS frame
				ITS its = new ITS();
				its.setVisible(true);
			}
			//Multiple of the same account exists in the database
			else if (count > 1) {
				JOptionPane.showMessageDialog(null, "Duplicate Username and Password");
			}
			//User does not exist in the database or password is incorrect
			else {
				JOptionPane.showMessageDialog(null, "Username and/or Password is not correct");
			}
			
			//Close the data reception from the database
			rs.close();
			pst.close();
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
	
	//As the user logs in, the information about the current user is fetched from the
	//database and stored under the package classes, in the file StudentAccount.java
	public static void studentInfo(String username) {
		//Store the username of the student
		StudentAccount.username = username;
		
		try {
			//Create a query to use in a PreparedStatement
				//Will find all the information relating to this username
			String query = " select * from students where username=?";
			//Execute the query with the associated database connection
			PreparedStatement pst = sqliteConnection.connAccount.prepareStatement(query);
			//username = username of currently logged in student
			pst.setString(1, username);
			//Results of the PreparedStatement
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				//Store all of the information that is in the ResultSet
				StudentAccount.name = rs.getString("name");
				StudentAccount.id = rs.getInt("id");
				StudentAccount.major = rs.getString("major");
				StudentAccount.coursesStr = rs.getString("courses");
				
				//Removes all of the spaces
				String holder = rs.getString("courses").replaceAll("\\s", "");
				//Splits the list into an array at each ","
				String[] holderArr = holder.split(",");	
				
				//Removes all of the spaces
				holder = rs.getString("taken").replaceAll("\\s", "");
				//Splits the list into array at each ","
				String[] takenArr = holder.split(",");
				
				StudentAccount.credits = rs.getInt("credits");
				
				//Store all currently enrolled courses into the ArrayList
				for(int i=0; i<holderArr.length; i++) {
					StudentAccount.courses.add(holderArr[i]);
				}
				//Store all completed courses into the ArrayList
				for(int i=0; i<takenArr.length; i++) {
					StudentAccount.taken1.add(takenArr[i]);
				}
			}
			
			//Close the data reception from the database
			rs.close();
			pst.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
	
	
	//As the user logs in, the information about the current user is fetched from the
	//database and stored under the package classes, in the file AdvisorAccount.java
	public static void advisorInfo(String username) {
		try {
			//Create a query to use in a PreparedStatement
				//Will find all the information relating to this username
			String query = "select * from advisors where username=?";
			//Execute the query with the associated database connection
			PreparedStatement pst = sqliteConnection.connAccount.prepareStatement(query);
			//username = username of currently logged in user
			pst.setString(1, username);
			//Results of the PreparedStatement
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				//Store all of the information about this user
				AdvisorAccount.name = rs.getString("name");
				AdvisorAccount.address = rs.getString("address");
				AdvisorAccount.email = rs.getString("email");
				AdvisorAccount.phone = rs.getString("phone");
				AdvisorAccount.department = rs.getString("department");
			}
			
			//Close the data reception from the database
			rs.close();
			pst.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
	
	//As the user logs in, the information about the current user is fetched from the
	//database and stored under the package classes, in the file ITSStaff.java
	public static void itsInfo(String username) {
		try {
			//Create a query to use in a PreparedStatement
				//Will find all the information relating to this username
			String query = "select * from its where username=?";
			//Execute the query with the associated database connection
			PreparedStatement pst = sqliteConnection.connAccount.prepareStatement(query);
			//username = username of currently logged in user
			pst.setString(1, username);
			//Results of the PreparedStatement
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				//Store all of the information about this user
				ITSStaff.name = rs.getString("name");
				ITSStaff.address = rs.getString("address");
				ITSStaff.email = rs.getString("email");
				ITSStaff.phone = rs.getString("phone");
				ITSStaff.department = rs.getString("department");
			}
			
			//Close the data reception from the database
			rs.close();
			pst.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
	
	//Populates the JTable in the frame StudentAccounts.java to display the
	//students that are in the account database
	public static void populateStudents() {
		try {
			//Create a query to use in a PreparedStatement
				//Will get all of the selected information from the student database
				//Passwords will not be obtained/displayed
			String query = "select name,id,major,courses,taken,credits from students";
			//Execute the query with the associated database connection
			PreparedStatement pst = sqliteConnection.connAccount.prepareStatement(query);
			//Results of the PreparedStatement
			ResultSet rs = pst.executeQuery();
			//Populate the table with this ResultSet
			StudentAccounts.table.setModel(DbUtils.resultSetToTableModel(rs));
			//Close the data reception from the database
			rs.close();
			pst.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
	
	//Populates the JTable in the frame ManageITS.java to display the
	//students that are in the account database
	public static void populateStudentsITS() {
		try {
			//Create a query to use in a PreparedStatement
				//Will get all of the selected information from the student database
				//Passwords will not be obtained/displayed
			String query = "select name,id,major,courses,taken,credits from students";
			//Execute the query with the associated database connection
			PreparedStatement pst = sqliteConnection.connAccount.prepareStatement(query);
			//Results of the PreparedStatement
			ResultSet rs = pst.executeQuery();
			//Populate the table with this ResultSet
			ManageITS.table.setModel(DbUtils.resultSetToTableModel(rs));
			//Close the data reception from the database
			rs.close();
			pst.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
	
	
}
