package classes;

import javax.swing.JOptionPane;

import data.editAccountDB;
import data.editCourseDB;
import data.readCourseDB;
import frames.ManageAccount;

/*
 * Allows us to store information relating to a specific class
 * 
 * Along with this, it is used to manage the enrollment, dropping, creation and deletion of various courses.
 */
public class Courses {
	public static int credits;
	public static int currentPopulation;
	public static int maxPopulation;
	
	//The Student is attempting to enroll in a course
	//If they meet all the requirements enroll them in the course
	//If not, display a message stating why they could not enroll
	public static void addClass(String id) {
		//If the student is not already enrolled in this course
		if(!StudentAccount.courses.contains(id)) {
			//If the class is not full
			if(readCourseDB.classStatus(id)) {
				//If the student meets all of the prereqs
				if(readCourseDB.prereqsMet(id)) {
					//If the student does not go over 19 credits
					if(readCourseDB.checkCredits(id)) {
						//If you have not taken this course
						if(!StudentAccount.taken1.contains(id)) {
							//Update the students information to reflect this enrollment
							StudentAccount.updateStudentAccountInfoEnroll(id);
							//Update the account database to reflect this enrollment
							editAccountDB.updateCredits(id);
							editAccountDB.updateCurrentCourses(id);
							//Update the course database to reflect this enrollment
							editCourseDB.updateCoursePopulation(id);
							//Reload the table containing the courses
							readCourseDB.populateCourses();
							//Reload the table containing the students schedule
							readCourseDB.populateCourseSchedule();
							JOptionPane.showMessageDialog(null, "You have been enrolled in " + id);
						}
						else {
							JOptionPane.showMessageDialog(null, "You have already completed " + id);
						}
					}
					else {
						JOptionPane.showMessageDialog(null, "You cannot earn more than 19 credits per semester");
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "You do not meet the prereqs");
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "The class is full");
			}
		}
		else {
			JOptionPane.showMessageDialog(null, "You are already enrolled in " + id);
		}
	}
	
	
	//The Student is attempting to drop a class
	//If they meet the requirements drop the course
	//If not, display a message stating why they could not drop
	public static void dropClass(String id) {
		//If the student is enrolled in the course
		if(StudentAccount.courses.contains(id)) {
			//If the student will not go below 12 credits after dropping
			if(readCourseDB.minCredits(id)) {
				//Update the course database to reflect this drop
				editCourseDB.dropEditClass(id);
				//Update the students information to reflect this drop
				StudentAccount.updateStudentAccountInfoDrop(id);
				//Update the account database to reflect this drop
				editAccountDB.dropEditStudent(id);
				//Reload the table containing the courses
				readCourseDB.populateCourses();
				//Reload the table containing the students schedule
				readCourseDB.populateCourseSchedule();
				//Update the students credits in this login session
				ManageAccount.updateCredits(credits);
			}
			else {
				JOptionPane.showMessageDialog(null, "You cannot go below 12 credits");
			}
		}
		else {
			JOptionPane.showMessageDialog(null, "You are not enrolled in " + id);
		}
	}
	
	//The Advisor is attempting to delete a course
	public static void deleteCourse(String id) {
		//Update the course database to reflect this deletion
		editCourseDB.deleteCourse(id);
	}
	
	//The Advisor is attempting to create a course
	public static void createCourse(String id, String name, String instructor, String days, String time, String location, String prereqs, Integer population, Integer credits) {
		//Update the course database to reflect this creation
		editCourseDB.createCourse(id, name, instructor, days, time, location, prereqs, population, credits);
	}
}
