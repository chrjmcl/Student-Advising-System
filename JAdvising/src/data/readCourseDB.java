package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import classes.Courses;
import classes.StudentAccount;
import frames.AdvisorCatalog;
import frames.Catalog;
import frames.Schedule;
import net.proteanit.sql.DbUtils;

/*
 * Allows us to obtain information from the course database
 */
public class readCourseDB {
	//Populates the JTable in the frame Catalog.java to display all the available
	//courses that are in the course database
	public static void populateCourses() {
		try {
			//Create a query to use in a PreparedStatement
				//Grabs all information from the courses table
			String query = "select * from courses";
			//Execute the query with the associated database connection
			PreparedStatement pst = sqliteConnection.connCourse.prepareStatement(query);
			//Results of the PreparedStatement
			ResultSet rs = pst.executeQuery();
			//Populates the table with all of the information store in the ResultSet
			Catalog.table.setModel(DbUtils.resultSetToTableModel(rs));
			//Close the data reception from the database
			rs.close();
			pst.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
	
	//Populates the JTable in the frame Schedule.java to display all the courses
	//that the student is currently taking
	public static void populateCourseSchedule() {
		try {
			//Create a query to use in a PreparedStatement
				//Grabs all information from the courses table where id is specified
			StringBuilder queryBuilder = new StringBuilder("select * from courses where");
			//For each course in the students schedule
			for(int i=0; i < StudentAccount.courses.size(); i++) {
				//Add the following String
			    queryBuilder.append(" id = ? OR");
			}
			// remove the final " OR" from the query at the end
			queryBuilder.replace(queryBuilder.length() - 3, queryBuilder.length(), "");
			
			//Execute the query with the associated database connection
			PreparedStatement pst = sqliteConnection.connCourse.prepareStatement(queryBuilder.toString());
			//For each course in the students schedule
			for(int i = 1; i <= StudentAccount.courses.size(); i++) {
				//id = this specific indexed course
				pst.setString(i, StudentAccount.courses.get(i-1));
			}
			//Results of the PreparedStatement
			ResultSet rs = pst.executeQuery();
			//Populate the table with all of the information stored in the ResultSet
			Schedule.table.setModel(DbUtils.resultSetToTableModel(rs));
			//Close the data reception from the database
			rs.close();
			pst.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
	
	//Checks whether or not a class is full
	//If the class is full, return false
	//Otherwise, return true
	public static boolean classStatus (String id) {
		try {
			//Create a query to use in a PreparedStatement
				//Will obtian all information for a given id
			String query = "select * from courses where id=?";
			//Execute the query with the associated database connection
			PreparedStatement pst = sqliteConnection.connCourse.prepareStatement(query);
			//id = specified course id
			pst.setString(1, id);
			//Results of the PreparedStatement
			ResultSet rs = pst.executeQuery();
		
			while(rs.next()) {
				//If the course is open
				if(rs.getBoolean("Open")) {
					//Enrolled populated++
					Courses.currentPopulation = rs.getInt("current");
					//Obtain the max population for this course
					Courses.maxPopulation = rs.getInt("max");
					//Close the data reception from the database
					rs.close();
					pst.close();
					//Class is open
					return true;
				}
			}
			//Close the data reception from the database
			rs.close();
			pst.close();
			//Class is full
			return false;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
			return false;
		}
	}
	
	//Checks whether or not the student meets the prereqs of the course
	//If the student meets the requirements, return true
	//Otherwise, return false
	public static boolean prereqsMet (String id) {
		try {
			//Create a query to use in a PreparedStatement
				//Will obtain all information for a given id
			String query = "select * from courses where id=?";
			//Execute the query with the associated database connection
			PreparedStatement pst = sqliteConnection.connCourse.prepareStatement(query);
			pst.setString(1, id);
			//Results of the PreparedStatement
			ResultSet rs = pst.executeQuery();
		
			while(rs.next()) {
				//If there are no prereqs
				if(rs.getString("prereq").equals("")) {
					//You meet these requirements
					return true;
				}
				
				//Remove all spaces from the string
				String holder = rs.getString("prereq").replaceAll("\\s", "");
				//Split the string at each ","
				LinkedList<String> reqs = new LinkedList<String>(Arrays.asList(holder.split(",")));

				//For each prereq
				for(int i=0; i < reqs.size(); i++) {
					//If you have not completed one of them
					if(!StudentAccount.taken1.contains(reqs.get(i))){
						//Close the data reception from the database
						rs.close();
						pst.close();
						return false;
					}
				}
			}
			//Close the data reception from the database
			rs.close();
			pst.close();
			//You have completed all of the prereqs
			return true;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
			return false;
		}
	}
	
	//Checks if the student will go over 19 credits if they enroll in the course
	//If they will be <= 19 credits, return true
	//Otherwise, return false
	public static boolean checkCredits (String id) {
		try {
			//Create a query to use in a PreparedStatement
				//Will obtain all information for a given id
			String query = "select * from courses where id=?";
			//Execute the query with the associated database connection
			PreparedStatement pst = sqliteConnection.connCourse.prepareStatement(query);
			//id = courses id
			pst.setString(1, id);
			//Results of the PreparedStatement
			ResultSet rs = pst.executeQuery();
		
			while(rs.next()) {
				//Obtain the credits you receive from this course
				Courses.credits = rs.getInt("credits");
				//If you do not go above 19 credits
				if(StudentAccount.credits + Courses.credits <= 19) {
					//Close the data reception from the database
					rs.close();
					pst.close();
					return true;
				}
			}
			//Close the data reception from the database
			rs.close();
			pst.close();
			//You went above 19 credits, you cannot enroll
			return false;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
			return false;
		}
	}
	
	//Checks whether or not a student will have >= 12 credits after dropping the course
	//If they will have >= 12 credits, return true
	//Otherwise, return false
	public static boolean minCredits (String id) {
		try {
			//Create a query to use in a PreparedStatement
				//Will obtain all information for a given id
			String query = "select * from courses where id=?";
			//Execute the query with the associated database connection
			PreparedStatement pst = sqliteConnection.connCourse.prepareStatement(query);
			//id = courses id
			pst.setString(1, id);
			//Results of the PreparedStatement
			ResultSet rs = pst.executeQuery();
		
			while(rs.next()) {
				//Obtain the credits you receive from this course
				Courses.credits = rs.getInt("credits");
				//If you have 12 or more credits after the drop
				if(StudentAccount.credits - Courses.credits >= 12) {
					//Close the data reception from the database
					rs.close();
					pst.close();
					return true;
				}
			}
			//Close the data reception from the database
			rs.close();
			pst.close();
			//You went below 12 credits, you cannot drop this course
			return false;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
			return false;
		}
	}
	
	//Populates the JTable in the frame AdvisorCatalog.java to display all the courses
	//inside of the course database
	public static void populateCoursesAdvisor() {
		try {
			//Create a query to use in a PreparedStatement
				//Will obtain all information for all courses
			String query = "select * from courses";
			//Execute the query with the associated database connection
			PreparedStatement pst = sqliteConnection.connCourse.prepareStatement(query);
			//Results of the PreparedStatement
			ResultSet rs = pst.executeQuery();
			//Populate the table with the ResultSet
			AdvisorCatalog.table.setModel(DbUtils.resultSetToTableModel(rs));
			//Close the data reception from the database
			rs.close();
			pst.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
	
	//Checks whether or not a course is empty
	public static boolean emptyCourse(String id) {
		try {
			//Create a query to use in a PreparedStatement
				//Will obtain all information for a given id
			String query = "select * from courses where id = ?";
			//Execute the query with the associated database connection
			PreparedStatement pst = sqliteConnection.connCourse.prepareStatement(query);
			//id = id of this course
			pst.setString(1, id);
			//Results of the PreparedStatement
			ResultSet rs = pst.executeQuery();
			//If there are no students currently enrolled
			if(rs.getInt("current") == 0) {
				return true;
			}
			//Close the data reception from the database
			rs.close();
			pst.close();
			//Course is not empty
			return false;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
			return false;
		}
	}
}