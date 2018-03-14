JAdvising
	This folder contains the source code
	If you wish to run the source in an IDE such as Eclipse, run from /src/frames/Login.java
	
	Exporting to a JAR
		Eclipse
			File -> Export
			Java -> Runnable JAR file
			Chose an export desitination
			Finish
		
	If you wish to make changes to the source, and want to export it to a JAR. Make the following changes:
		/src/data/sqliteConnection.java
			Comment lines:
				24 : connAccount = DriverManager.getConnection("jdbc:sqlite:resources//databases//accounts.sqlite");
				44 : connCourse = DriverManager.getConnection("jdbc:sqlite:resources//databases//courses.sqlite");
				64 : connAppointment = DriverManager.getConnection("jdbc:sqlite:resources//databases//appointments.sqlite");
			Uncomment lines:
				26 : connAccount = DriverManager.getConnection("jdbc:sqlite::resource:accounts.sqlite");
				46 : connCourse = DriverManager.getConnection("jdbc:sqlite::resource:courses.sqlite");
				66 : connAppointment = DriverManager.getConnection("jdbc:sqlite::resource:appointments.sqlite");


JAdvisingJAR
	The following databases need to be in the same location as the JAR:
		accounts.sqlite
		courses.sqlite
		appointments.sqlite
	If not, the software will fail to connect to them, and you will not be able to get passed the login page.
		*This location can be changed if you edit lines 26, 46, and 66 to the correct path of the databases.

	To run this Jar, you will need java installed ( https://java.com/en/download/ )
	After installing Java, just click the Jar


How to log in:
	Student:
		Do not check any of the boxes on the bottom left

		Username: student
		Password: pass
		
		Username: Lestudent
		Password: cavaliers

		Username: msmarty
		Password: bceltics

		Username: dwade
		Password: miamiheat

		Username: dbooker
		Password: 3ptchamp

	Advisor
		Check the Advisor box on the bottom left

		Username: bsmith
		Password: smith123

		Username: bstevens
		Password: celtics

	ITS
		Check the ITS box on the bottom left

		Username: ITS
		Password: ITSSupport


Student Actions (From the student main page)
	View avaliable courses:
		Click the Course Catalog button

	View your schedule:
		Click the Schedule button

	View information about your account:
		Click the Manage Account button

	View avaliable advising appointments:
		Click the Advising button	

	Enroll in a course:
		Click on the Course Catalog button
		Search for the course you want to enroll in
		Type the id of the course you want to enroll in on the bottom right text field
		Click the enroll button

	Drop a course:
		Click on the Schedule button
		Type the id of the course you want to drop in on the bottom right text field
		Click the drop button

	Change your password:
		Click on the Manage Account button
		Type your new password in the field on the bottom
		Click the change button

	Schedule an advising appointment:
		Click the Advising button
		Search for the appointment that you want to schedule
		Type the id of the appointment on the bottom right text field
		Click the schedule button


Advisor Actions (From the advisor main page)
	View your scheduled advising appointments:
		Click on the Calendar button
		Scheduled appointments will be displayed in the table
	
	View the course catalog:
		Click the Course Catalog button

	Create a course:
		Click on the Course Catalog button
		Click the Add Course button
		Input the information pertaining to the course you are adding
		Click the Create button
	
	Delete a course:
		Click on the Course Catalog button
		Click the Delete Course button
		Input the course ID that you want to delete
		Click the Delete button

	View student accounts:
		Click the Student Accounts button


ITS Actions (From the ITS main page)
	Troubleshoot
		Click the troubleshoot button

	View student accounts
		Click the Manage button

	Change a students password
		Click the Manage button
		Enter the ID of the student that you wish to change the password of under the ID text
		Enter the new password for the account under the Password text
		Click the Change button
	
		


		
