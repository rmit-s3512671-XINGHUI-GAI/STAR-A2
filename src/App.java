/* Start up code for SEF Student Management System 2015 semester 1.
 * This code should be used for your initial class diagram.
 * You are free to adapt or completely change the code and design for the
 * extended class diagram and implementation as long as your design allows 
 * for all the requirements and test cases.
 *  
 */

import java.util.*;
public class App 
{
   HashMap<String, Course> courses = new HashMap<String,Course>();
   HashMap<String, Venue> venues = new HashMap<String, Venue>();
   HashMap<String, Lecturer> lecturers = new HashMap<String, Lecturer>();
   HashMap<String, Student> students = new HashMap<String, Student>();
   HashMap<String, Tutor> tutors = new HashMap<String, Tutor>();
   HashMap<String,Application> applications = new HashMap<String, Application>();
   
   Scanner scan = new Scanner(System.in);
   
   Administor admin;

   public static void main(String[] args) 
   {   
	  App a = new App(); 
	  String options[] = {"Adminster login","Student login","Lecture login", "Tutor login","View Venue Timtable"};  	  
	  Menu m = new Menu("Main Menu", options);
	  int resp;
	  do {
		 if ((resp = m.getResponse()) == 0)
			break;
		 switch (resp)
		 {
		 case 1: a.administerOperation();
		      break;
		 case 2: a.studentOperation();
		      break;
		 case 3: 
			  a.lectureOperation();
		      break;
		 case 4: 
			  a.tutorOperation();
		      break;
		 case 5:
			 a.handleViewVenueTable();
		 }
	  }	 while (true); 
   }

   public App()
   {
	  initializeCourses();
	  initializeVenues();
	  initializeLecturers();
	  initializeStudents();
	  admin=new Administor("A01","Tony Stark", "01","12.11.10");
	  
   }
   
   public void administerOperation()
   {
	   System.out.println("Enter your password");
	   String pwd=scan.nextLine();
	   if(!pwd.equals(admin.getPwd()))
	   {
		   System.out.println(admin.getPwd());
		   System.out.println("incorrect password..");
		   hold();
		   return;
	   }
	   AppAdministor  AppAdmin=new AppAdministor();
	   String options[] = {"Add Course Offering","Add Lecture","Add Lecturer", "Add Tutorial","Appoint Applicant","Add Tutor","Grant Exemption"}; 
 	   Menu m = new Menu("Administor Menu", options);
 	   int resp;
 	   do {
 		   if ((resp = m.getResponse()) == 0)
 			  break;
 		   switch (resp)
 		   {
 		       case 1: AppAdmin.handleCourseCreateOffering(courses);
 		           break;
 		       case 2: AppAdmin.handleAddLecture(venues,courses);
 		           break;
 		       case 3: AppAdmin.handleAssignLecturer(courses, lecturers);
 		           break;
 		       case 4: AppAdmin.handleAssignTutorial(courses, venues);
 		           break;
 		       case 5: AppAdmin.handleAppointApplicant(applications,tutors);
 	               break;
 		      case 6:
 		    	  AppAdmin.handleAssignTutor(courses,tutors);
 			      break;
 		      case 7:
 			       AppAdmin.handleGrantExemption(students);
 			      break;
 		 }
 	  }	 while (true); 
   }
   public void studentOperation(){
	   System.out.println("enter your id:");
	   String stuId=scan.nextLine();
	   Student student=students.get(stuId);
	   if(student==null){
		   System.out.println("Invalid student ID..");
		   hold();
		   return;
	   }
	   System.out.println("Enter your password");
	   String pwd=scan.nextLine();
	   if(!pwd.equals(student.getPwd()))
	   {
		   System.out.println("incorrect password..");
		   hold();
		   return;
	   }
	   AppStu  AppStu=new AppStu(student);
	   String options[] = {"Apply for Tutor","Enroll online","Withdraw","Apply for Exemption","View Applications","Register Tutorial","Withdraw Tutorial"}; 
 	   Menu m = new Menu("Student Menu", options);
 	   int resp;
 	   do {
 		   if ((resp = m.getResponse()) == 0)
 			  break;
 		   switch (resp)
 		   {
 		       case 1: AppStu.handleApplyForTutor(applications);
 		           break;
 		       case 2: AppStu.handleEnroll(courses);
 		           break;
 		       case 3: AppStu.handleWithdraw(courses);
 		           break;
 		       case 4: AppStu.handleApplyForExemption(students);
 		           break;
 		      case 5: AppStu.handleViewApplicationState(applications);
		           break;
 		      case 6:AppStu.handleRegisterTutorial(courses);
 		         break;
 		      case 7:AppStu.handleDeregisterTutorial(courses);
 		         break;
 		 }
 	  }	 while (true); 
   }
   public void view(int n)
   {
	 if (n == 1)
		displayMap(courses);
	 else if (n==2)
	    displayMap(lecturers);
	 else
	    displayMap(venues);
   }		
	
   public void lectureOperation()
   {
	   System.out.println("enter your id:");
	   String leId=scan.nextLine();
	   Lecturer lecturer=getLecturer(leId);
	   if(lecturer==null){
		   System.out.println("Invalid lecturer ID..");
		   hold();
		   return;
	   }
	   System.out.println("Enter your password");
	   String pwd=scan.nextLine();
	   if(!pwd.equals(lecturer.getPwd()))
	   {
		   System.out.println("incorrect password..");
		   hold();
		   return;
	   }
	   String options[] = {"View TimeTable"}; 
 	   Menu m = new Menu("Lecturer Menu", options);
 	   int resp;
 	   do {
 		   if ((resp = m.getResponse()) == 0)
 			  break;
 		   switch (resp)
 		   {
 		       case 1: printLecturerTimetable(lecturer.getENo());
 		           break;
 		 }
 	  }	 while (true);
   }
   
   public void tutorOperation()
   {
	   System.out.println("enter your id:");
	   String tId=scan.nextLine();
	   Tutor tutor=getTutor(tId);
	   if(tutor==null){
		   System.out.println("Invalid tutor ID..");
		   hold();
		   return;
	   }
	   System.out.println("Enter your password");
	   String pwd=scan.nextLine();
	   if(!pwd.equals(tutor.getPwd()))
	   {
		   System.out.println("incorrect password..");
		   hold();
		   return;
	   }
	   String options[] = {"View TimeTable"}; 
 	   Menu m = new Menu("Tutor Menu", options);
 	   int resp;
 	   do {
 		   if ((resp = m.getResponse()) == 0)
 			  break;
 		   switch (resp)
 		   {
 		       case 1: printTutorTimetable(tutor.getENo());
 		           break;
 		 }
 	  }	 while (true);
   }
   
   public void displayMap(Map m)
   {
      Set keySet = m.keySet();
      Iterator iterator = keySet.iterator();
      while (iterator.hasNext()) {
         String key = (String) iterator.next();
         System.out.println(key + ":" + m.get(key) );
      }
      hold();
   }



   public CourseOffering createOffering(Course course, int expectedNum,int year,int semester) throws PreExistException
   {
 	  return course.createCourseOffering(expectedNum,year,semester);	   
   }
      
   private void hold()
   {
	   System.out.print("Press enter to continue");
	   scan.nextLine();
   }
   

   public void assignLecture(CourseOffering co, int day, double startHour, double duration, Venue venue) throws ClashException, PreExistException
   {
	   co.assignLecture(day, startHour, duration, venue);	   	   
   }

   public void printLecturerTimetable(String lecturerID)
   {
	  Lecturer lecturer = getLecturer(lecturerID);
	  if (lecturer == null)
	  {
		  System.out.println("No lecturer with this ID");
	     return;
	  }
	  ArrayList<Lecture> lectures = getLectures(lecturer);  
	  if (lectures == null)
    	  return;       
	  for (int i=0; i<lectures.size(); i++)
		 System.out.println(lectures.get(i));
   }
   
   public void printTutorTimetable(String tutorID)
   {
	  Tutor tutor= getTutor(tutorID);
	  if (tutor == null)
	  {
		  System.out.println("No tutor with this ID");
	     return;
	  }
	  ArrayList<Tutorial> tutorials = getTutorials(tutor);  
	  if (tutorials == null)
    	  return;       
	  for (int i=0; i<tutorials.size(); i++)
		 System.out.println(tutorials.get(i));
   }

   public void handleViewVenueTable(){
	   printVenueTimetable();
   }
   public void printVenueTimetable()
   {
	  System.out.print("Enter  Venue Location : ");
	  String location = scan.nextLine();
	  Venue venue = getVenue(location);
	  if (venue == null)
	  {
		 System.out.println("No Venue at this location");
	     return;
	  }
	  ArrayList<Lesson> lessons = getLessons(venue);  
	  if (lessons == null)
    	  return;       
	  for (int i=0; i<lessons.size(); i++)
		 System.out.println(lessons.get(i));
   }

   public Lecturer getLecturer(String eNo)
   {
	     return lecturers.get(eNo);     
   }
   public Tutor getTutor(String eNo)
   {
	   return tutors.get(eNo);
   }
   public Venue getVenue(String location)
   {
 	     return venues.get(location);     
   }
   
   
   public void initializeCourses()
   {
    	Course course1 = new Course("P101", "Programming 1", "Teach Basic Programming");
    	Course course2 = new Course("P102", "Programming 2", "Teach Intermediate Programming");
    	Course course3 = new Course("S101", "Software Engineering", "Teach UML and Modelling");
    	Course course4 = new Course("WP1", "Web Programming", "Teach Web Technologies");
    	Course course5 = new Course("UI1", "User Interface", "Teach UI Principles");
    	Course course6 = new Course("Math","Discret Maths","Teach Maths needed for CS");
    	Course course7 = new Course("Net1", "Networkins","Teach networking principles");
    	Course course8 = new Course("Data1", "DataBase Conception","Teach databse principles");


    	course3.addPrereq(course1);
        course2.addPrereq(course1);
        course7.addPrereq(course2);
        course7.addPrereq(course6);
    	
    	courses.put("P101",course1);
    	courses.put("P102",course2);
        courses.put("S101",course3);    	
        courses.put("WP1",course4);    	
        courses.put("UI1",course5);    	
        courses.put("Math",course6);
        courses.put("Net1",course7);
        courses.put("Data1",course8);
        
   }   

   public void initializeVenues()
   {
      venues.put("12.10.02",new Venue("12.10.02",120,"Lecture"));
      venues.put("12.10.03",new Venue("12.10.03",200,"Lecture"));
      venues.put("10.10.22",new Venue("10.10.22",36,"TuteLab"));
      venues.put("10.10.23",new Venue("10.10.23",36,"TuteLab"));
   }

   public void initializeLecturers()
   {
      lecturers.put("e44556",new Lecturer("e44556","Tim O'Connor","44556","Lecturer","14.13.12"));
      lecturers.put("e44321",new Lecturer("e44321","Richard Cooper","44321","Professor","14.13.12"));
      lecturers.put("e54321",new Lecturer("e54321","Jane Smith","54321","Lecturer","11.9.10"));
   }
   
   public void initializeStudents(){
	   ArrayList<String> pre1=new ArrayList<String>();
	   pre1.add("P101");
	   students.put("s123",new Student("s123","123","Callen","Devens","042398444",pre1,new HashMap<String,Course>()));
	   students.put("s111",new Student("s111","111", "Aurther","Pendragon", "0614456234",new  ArrayList<String>(),new HashMap<String, Course>()));	

   }
   public ArrayList <Lecture> getLectures(Lecturer lecturer)
   {
	  return lecturer.getLectures();  	   
   }
   public ArrayList <Tutorial> getTutorials(Tutor tutor)
   {
	  return tutor.getTutorials();  	   
   }

   public ArrayList <Lesson> getLessons(Venue venue)
   {
	  return venue.getLessons();  	   
   }
   public Course getCourse(String ID)
   {
	  return courses.get(ID);     
   }  

   public Lecture getLecture(CourseOffering offering)
   {
	   return offering.getLecture();
   }
   
  
}