package data;

import java.sql.PreparedStatement;

import javax.swing.JOptionPane;

import classes.Courses;

/*
 * Allows us to edit the course database
 */
public class editCourseDB {	
	//The population of the course that the student enrolled in will be incremented by one
	//This will also test to see if this students enrollment has caused the class to become
	//full
	public static void updateCoursePopulation(String id) {
		try {
			//Create a query to use in a PreparedStatement
				//Will update the population of the specific course
			String query = "UPDATE courses SET current = current + 1  WHERE id = ?";
			//Execute the query with the associated database connection
			PreparedStatement pst = sqliteConnection.connCourse.prepareStatement(query);
			//id = id of the enrolled course
			pst.setString(1, id);
			//Update the database
			int update = pst.executeUpdate();
			
			//If an update occurred
			if (update > 0) {
				JOptionPane.showMessageDialog(null, "Updated " + id + " Current Population");
			}
			
			//Close the PreparedStatement
			pst.close();
			
			//Reload the table containing the students schedule
			//readCourseDB.populateCourseSchedule();
			
			//If the current population now is equal to the maximum
			if(Courses.currentPopulation + 1 >= Courses.maxPopulation) {
				//Close the course from enrollments
				fullCourse(id);
			}
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
	
	//The class that the student enrolled in is now full, set the open status of that
	//class to 0 (False)
	public static void fullCourse(String id) {
		try {
			//Create a query to use in a PreparedStatement
				//Will update the open status of the specific course
			String query = "UPDATE courses SET open = 0  WHERE id = ?";
			//Execute the query with the associated database connection
			PreparedStatement pst = sqliteConnection.connCourse.prepareStatement(query);
			//id = course that is now full
			pst.setString(1, id);
			//Update the database
			int update = pst.executeUpdate();
			
			//If an update occurred
			if (update > 0) {
				JOptionPane.showMessageDialog(null, "Updated " + id + " Open Status");
			}
			pst.close();
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
	
	//Update the information about the course that has just experienced
	//a dropped student
	public static void dropEditClass(String id) {
		try {
			//Create a query to use in a PreparedStatement
				//Will update the open status and population of the specific course
			String query = "UPDATE courses SET open = ? , current = ? WHERE id = ?";
			//Execute the query with the associated database connection
			PreparedStatement pst = sqliteConnection.connCourse.prepareStatement(query);
			//Check the status and obtain necessary information about the course
			readCourseDB.classStatus(id);
			//open = 1 (True)
				//This is always the case as a full class with a drop becomes open
				//And a open class with a drop stays open
			pst.setInt(1, 1);
			//current = one less than current population
			pst.setInt(2, Courses.currentPopulation-1);
			//id = course that had a drop
			pst.setString(3, id);
			//Update the database
			int update = pst.executeUpdate();
			
			//If an update occurred
			if (update > 0) {
				JOptionPane.showMessageDialog(null, "Updated " + id + " Open Status and Population");
			}
			
			//Close the PreparedStatement
			pst.close();
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
	
	//Create a brand new course
	public static void createCourse(String id, String name, String instructor, String days, String time, String location, String prereqs, Integer population, Integer credits) {
		try{
			//Create a query to use in a PreparedStatement
				//Will create a new row (course) into the course database
			String query = "INSERT INTO courses(ID,Name,Instructor,Days,Time,Location,Prereq,Open,Max,Current,Credits) Values(?,?,?,?,?,?,?,?,?,?,?)";
			//Execute the query with the associated database connection
			PreparedStatement pst = sqliteConnection.connCourse.prepareStatement(query);
			
			//Set all of information about this created course
			pst.setString(1, id);
			pst.setString(2, name);
			pst.setString(3, instructor);
			pst.setString(4, days);
			pst.setString(5, time);
			pst.setString(6, location);
			pst.setString(7, prereqs);
			pst.setInt(8, 1);
			pst.setInt(9, population);
			pst.setInt(10, 0);
			pst.setInt(11, credits);
			
			//Update the database
			int update = pst.executeUpdate();
			
			//If an updated occurred
			if(update > 0) {
				//Update the course table for the advisor
				readCourseDB.populateCoursesAdvisor();
				JOptionPane.showMessageDialog(null, "Course " + id + " has been created");
			}
			
			//Close the Prepared Statement
			pst.close();
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
	
	//Delete a Course
	public static void deleteCourse(String id) {
		try {
			//Create a query to use in a PreparedStatement
				//Will delete the course(id) from the course database
			String query = "DELETE FROM courses Where id=?";
			//Execute the query with the associated database connection
			PreparedStatement pst = sqliteConnection.connCourse.prepareStatement(query);
			//id = course to be deleted
			pst.setString(1,id);
			
			//Update the database
			int update = pst.executeUpdate();
			
			//If an update occurred
			if(update > 0) {
				//Update the course table for the advisor
				readCourseDB.populateCoursesAdvisor();
				JOptionPane.showMessageDialog(null, "Course " + id + " has been deleted");
			}
			
			//Close the Prepared Statement
			pst.close();
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
}
