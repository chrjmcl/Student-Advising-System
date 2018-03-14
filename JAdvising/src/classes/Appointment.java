package classes;

import javax.swing.JOptionPane;

import data.editAppointmentDB;
import data.readAppointmentDB;

/*
 * Manages the ability for students to schedule an appointment
 */
public class Appointment {
	//True if the user has requested an appointment, otherwise false
	public static boolean requested = false;
	
	//The student is attempting to request an appointment
	//if they meet the requirements, enroll them in that appointment slot
	public static void requestAppointment(String id) {
		//if the student has yet to request an appointment
		if(!requested) {
			//If the appointment slot is not already taken
			if(readAppointmentDB.checkStatus(id)) {
				//edit the appointment database to reflect that the student has enrolled
				editAppointmentDB.enrollAppointment(id);
				//reload the appointment table
				readAppointmentDB.populateAppointments();
				//student has now request an appointment
				requested=true;
			}
			else {
				JOptionPane.showMessageDialog(null, "Appointment " + id + " is full");
			}
		}
		else {
			JOptionPane.showMessageDialog(null, "You have already requested an appointment");
		}
	}
}
