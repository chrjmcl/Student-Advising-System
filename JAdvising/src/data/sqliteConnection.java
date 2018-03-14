package data;

import java.sql.*;
import javax.swing.*;

/*
 * sqliteConnection allows us to get access to various database that the user is
 * trying to connect to.
 * 
 * Once connected, the database can be read and modified based on user interactions wit
 * the software.
 */
public class sqliteConnection {
	public static Connection connAccount = null;
	public static Connection connCourse = null;
	public static Connection connAppointment = null;
	
	//Connects the software to the account database
	public static Connection accountConnector() {
		try {
			Class.forName("org.sqlite.JDBC");
			//Create a Connection to the account database
			//Eclipse
			connAccount = DriverManager.getConnection("jdbc:sqlite:resources//databases//accounts.sqlite");
			//JAR
			//connAccount = DriverManager.getConnection("jdbc:sqlite::resource:accounts.sqlite");
			//Display a message notifying the user that they connected successfully
			JOptionPane.showMessageDialog(null, "Account Database Connection Successfull");
			//Return the Connection
			return connAccount;
		} catch(Exception e) {
			//Connection to the database was unsuccessful, display the error message
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
	}
	
	//Connects the software to the course database
	public static Connection courseConnector() {
		try {
			Class.forName("org.sqlite.JDBC");
			//Create a Connection to the account database
			//Eclipse
			connCourse = DriverManager.getConnection("jdbc:sqlite:resources//databases//courses.sqlite");
			//JAR
			//connCourse = DriverManager.getConnection("jdbc:sqlite::resource:courses.sqlite");
			//Display a message notifying the user that they connected successfully
			JOptionPane.showMessageDialog(null, "Course Database Connection Successfull");
			//Return the Connection
			return connCourse;
		} catch(Exception e) {
			//Connection to the database was unsuccessful, display the error message
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
	}
	
	//Connects the software to the appointment database
	public static Connection appointmentConnector() {
		try {
			Class.forName("org.sqlite.JDBC");
			//Create a Connection to the account database
			//Eclipse
			connAppointment = DriverManager.getConnection("jdbc:sqlite:resources//databases//appointments.sqlite");
			//JAR
			//connAppointment = DriverManager.getConnection("jdbc:sqlite::resource:appointments.sqlite");
			//Display a message notifying the user that they connected successfully
			JOptionPane.showMessageDialog(null, "Appointment Database Connection Successfull");
			//Return the Connection
			return connAppointment;
		} catch(Exception e) {
			//Connection to the database was unsuccessful, display the error message
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
	}
}
