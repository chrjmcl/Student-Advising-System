package classes;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import data.readAccountDB;
import frames.ManageAccount;

/*
 * Stores information about the student that is currently logged in
 * 
 * Along with storing information, It is used to update locally referenced variables that
 * pertain to the student
 */
public class StudentAccount {
	public static String name;
	public static Integer id;
	public static Integer credits;
	public static String major;
	public static String username;
	public static String coursesStr;
	public static ArrayList<String> courses = new ArrayList<String>();
	public static ArrayList<String> taken1 = new ArrayList<String>(); 
	
	public void displayCourse() {
		//Called for in SRS/DD, but fits better elsewhere
	}
	
	//A Student has logged in, obtain their information
	public static void getStudent(String username) {
		//Read the account database to obtain information about the student
		readAccountDB.studentInfo(username);
	}
	
	//The Student has enrolled in a course, update their information accordingly
	public static void updateStudentAccountInfoEnroll(String id) {
		//Add the credits of the enrolled course
		credits = credits + Courses.credits;
		//Update JLabel on Manage Account to display the change in credits
		ManageAccount.updateCredits(credits);
		//Update the string containing the list of courses
		coursesStr = coursesStr + ", " + id;
		//Add the course to the ArrayList of enrolled courses
		courses.add(id);
	}
	
	//The Student has dropped a course, update their information accordingly
	public static void updateStudentAccountInfoDrop(String id) {
		//Remove the course from the ArrayList of enrolled Courses
		StudentAccount.courses.remove(StudentAccount.courses.indexOf(id));
		//Remove the credits of the dropped course
		StudentAccount.credits = StudentAccount.credits - Courses.credits; 
		
		//If the dropped course is in the middle of two classes
			//ex: Dropped Course = CIS123
				//CIS122, CIS123, CIS124  -->  CIS122, CIS124
		if(StudentAccount.coursesStr.contains(", "+ id + ", ")) {
			StudentAccount.coursesStr=StudentAccount.coursesStr.replace(id + ", ", "");
		}
		//If The dropped course is the first course in the list
			//ex: Dropped Course = CIS123
				//CIS123, CIS124, CIS 125 ---> CIS124, CIS125
		else if(StudentAccount.coursesStr.contains(id + ", ")) {
			StudentAccount.coursesStr=StudentAccount.coursesStr.replace(id + ", ", "");
		}
		//If the dropped course is the last course in the list
			//ex: Dropped Course = CIS123
				//CIS124, CIS125, CIS123 --> CIS124, CIS125
		else if(StudentAccount.coursesStr.contains(", " + id)) {
			StudentAccount.coursesStr=StudentAccount.coursesStr.replace(", " + id, "");
		}
		//If the dropped course is the only course in the list
			//ex: Dropped Course = CIS123
				//CIS123 --> ""
		else {
			JOptionPane.showMessageDialog(null, StudentAccount.coursesStr);
			StudentAccount.coursesStr=StudentAccount.coursesStr.replace(id, "");
			JOptionPane.showMessageDialog(null, StudentAccount.coursesStr);
		}
	}
}
