import java.util.*;
public class AppAdministor {
      private Scanner scan=new Scanner(System.in);
      private int year=DateControll.year;
      private int semester=DateControll.sem;
  	  
      public void handleCourseCreateOffering(HashMap<String,Course>courseList)
      {
   	      System.out.print("Enter  Course ID : ");
   	      String courseID = scan.nextLine();
   	      Course course = courseList.get(courseID);
    
     	  CourseOffering co;
          if (course == null)
          {
              System.out.println("Course ID is invalid");
              hold();
          }
          else 
          {
       	      System.out.print("Enter Expected Number : ");
       	      int expectedNum = scan.nextInt();
       	      scan.nextLine();
       	  try {
       		  //put new offering to course
       	      createCourseOffering(course,expectedNum,year,semester);
       	      System.out.println(course.toString());
       	  }
       	      catch (PreExistException pe) { System.out.println(pe); hold(); }    	  
          }
      }
      
      public void handleAddLecture(HashMap<String,Venue> venueList,HashMap<String,Course> courseList)
      {
   	      System.out.print("Enter  Course ID : ");
   	      String courseID = scan.nextLine();
          CourseOffering courseOffering = getCourseOffering(courseList,courseID,year,semester);
          if ( courseOffering == null)
          {  
       	      System.out.println("No course offering yet");
       	      hold();
       	      return;
          }
         	   
   	      System.out.print("Enter  Venue Location : ");
   	      String location = scan.nextLine();
          Venue venue = getVenue(venueList,location);
          if ( venue == null)
          {       
       	     System.out.println("No such venue");
       	     hold();
       	     return;
          }
   	      if(venue.getPurpose()!="Lecture"){
   	    	  System.out.println("not a venue for lecture..");
   	    	  hold();
   	    	  return;
   	      }
   	      System.out.print("Enter  Day of Lecture : ");
   	      int day = scan.nextInt();
   	   
   	      System.out.print("Enter  Start Hour : ");
   	      double startHour = scan.nextInt();
    
   	      System.out.print("Enter  Duration : ");
   	      double duration = scan.nextInt();
   	      scan.nextLine();
   	      try {
               courseOffering.assignLecture(day, startHour, duration, venue);
               System.out.println(courseOffering);
   	      }
   	      catch (ClashException ce)
   	      {
   	          System.out.println(ce);	   
   	      }
   	      catch (PreExistException pe)
   	      {
   	          System.out.println(pe);	   
   	      }
   	 }
     public void handleAssignLecturer(HashMap<String,Course> courseList,HashMap<String, Lecturer> lecturerList)
     {
   	     System.out.print("Enter  Course ID : ");
   	     String courseID = scan.nextLine();
         CourseOffering courseOffering = getCourseOffering(courseList,courseID,year,semester);
         if ( courseOffering == null)
         {       
       	     System.out.println("No course offering yet");
       	     hold();
       	     return;
         }
         Lecture lecture = courseOffering.getLecture();
         if ( lecture == null)
         {       
       	     System.out.println("No lecture assigned to this course offering yet ");
       	     hold();
       	     return;
         }     
   	     System.out.print("Enter  Lecturer ID : ");
   	     String lecID = scan.nextLine();
   	     Lecturer lecturer = getLecturer(lecturerList,lecID);
   	     if (lecturer == null)
   	     {
       	     System.out.println("No lecturer with such ID ");
       	     hold();
       	     return;       
   	     }
   	     try {
   		     assignLecturer(lecture,lecturer);
         }
         catch (ClashException ce) { System.out.println(ce); }	
   	     catch (PreExistException pe) {System.out.println(pe); }
     }
     
     public void handleAssignTutorial(HashMap<String,Course> courseList, HashMap<String,Venue> venueList){
    	 System.out.print("Enter  Course ID : ");
  	     String courseID = scan.nextLine();
         CourseOffering courseOffering = getCourseOffering(courseList,courseID,year,semester);        
         if ( courseOffering == null)
         {       
      	     System.out.println("No course offering yet");
      	     hold();
      	     return;
         }
        	   
  	     System.out.print("Enter  Venue Location : ");
  	     String location = scan.nextLine();
         Venue venue = getVenue(venueList,location);
         if ( venue == null)
         {       
      	     System.out.println("No such venue");
      	     hold();
      	     return;
         }
         
         System.out.print("Enter  Expected number : ");
  	     int num = scan.nextInt();
         if ( num>venue.getCapacity())
         {       
      	     System.out.println("No enough room for this number");
      	     hold();
      	     return;
         }
         if(venue.getPurpose()!="TuteLab"){
  	    	  System.out.println("not a venue for tutorial..");
  	    	  hold();
  	    	  return;
  	      }
  	   
  	      System.out.print("Enter  Day of Tutorial : ");
  	      int day = scan.nextInt();
  	   
  	      System.out.print("Enter  Start Hour : ");
  	      double startHour = scan.nextInt();
   
  	      System.out.print("Enter  Duration : ");
  	      double duration = scan.nextInt();
  	      scan.nextLine();
  	      try {
               courseOffering.assignTutorial(day, startHour, duration, num, venue);
               System.out.println(courseOffering);
  	      }
  	      catch (ClashException ce)
  	      {
  	          System.out.println(ce);	   
  	      }
  	      catch (PreExistException pe)
  	      {
  	          System.out.println(pe);	   
  	      }
     }
     public void handleAppointApplicant(HashMap<String,Application> appList,HashMap<String, Tutor> tutorList){
    	 System.out.println("===========Application List===========");
    	 displayAppList(appList);
    	 System.out.println("Enter applicant id");
    	 String appId=scan.nextLine();
    	 Application app=appList.get(appId);
    	 if(app==null)
    	 {
    		 System.out.println("not in the applicant list");
    		 hold();
    		 return;
    	 }
    	 if(app.getState()!=Application.STATE_TO_BE_APPOINTED)
    	 {
    		 System.out.println("The application could not be admitted yet or");
    		 System.out.println("You have admitted the application..");
    		 hold();
    		 return;
    	 }
    	 Tutor t=new Tutor("t"+Tutor.getTutorNum(), app.getApplicantName(), "tutorpass");
    	 tutorList.put(t.getENo(), t);
    	 app.setState(Application.STATE_ADMITTED);
    	 
     }
	 public void handleAssignTutor(HashMap<String,Course> courseList,HashMap<String, Tutor> tutorList)
	 {
		 System.out.print("Enter  Course ID : ");
   	     String courseID = scan.nextLine();
         CourseOffering courseOffering = getCourseOffering(courseList,courseID,year,semester);
         if ( courseOffering == null)
         {       
       	     System.out.println("No course offering yet");
       	     hold();
       	     return;
         }
         
         System.out.print("Enter  Tutorial Code: ");
   	     String tCode = scan.nextLine();
         Tutorial t = courseOffering.getTutorial(tCode);
         if ( t == null)
         {       
       	     System.out.println("No this tutorial yet");
       	     hold();
       	     return;
         }
   	     System.out.print("Enter  Tutor ID : ");
   	     String tutorID = scan.nextLine();
   	     Tutor tutor =tutorList.get(tutorID);
   	     if (tutor == null)
   	     {
       	     System.out.println("No tutor with such ID ");
       	     hold();
       	     return;       
   	     }
   	     try {
   		     assignTutor(t,tutor);
         }
         catch (ClashException ce) { System.out.println(ce); }	
   	     catch (PreExistException pe) {System.out.println(pe); }
	 }
	 
	 public void handleGrantExemption(HashMap<String,Student> stuList){
		 System.out.println("Enter student ID:");
		 String stuID=scan.nextLine();
		 Student stu=stuList.get(stuID);
		 if(stu==null)
		 {
			 System.out.println("Invalid student ID");
			 hold();
			 return;
		 }
		 Exemption exem=stu.getExemption();
		 if(exem==null)
		 {
			 System.out.println("The student did not apply for examption");
			 hold();
			 return;
		 }
		 if(exem.getState()!=Exemption.STATE_INITIAL)
		 {
			 System.out.println("Grant exemption failed this examption might have been");
			 System.out.println("Admitted or Rejected");
			 hold();
			 return;
		 }
		 exem.setState(Exemption.STATE_ADMITTED);
	 }
	 
      
     public CourseOffering getCourseOffering(HashMap<String,Course> courseList, String ID, int year, int sem)
     {
   	     Course c = courseList.get(ID);
   	     if (c == null) 
   		   return null;
   	     return c.getCourseOffering(year, semester);	
     }
     public Venue getVenue(HashMap<String,Venue> venueList,String location)
     {
   	     return venueList.get(location);     
     }
     
     private void hold()
     {
   	      System.out.print("Press enter to continue");
    	  scan.nextLine();
     }
     public CourseOffering createCourseOffering(Course course, int expectedNum,int year,int semester) throws PreExistException
     {
   	     return course.createCourseOffering(expectedNum,year,semester);	   
     }
     public Lecturer getLecturer(HashMap<String,Lecturer>lecturerList,String eNo)
     {
  	     return lecturerList.get(eNo);     
     }
     public void assignLecturer(Lecture lecture, Lecturer lecturer) throws ClashException, PreExistException
     {
  	     lecturer.assign(lecture);	   	   
     }
     
     public void assignTutor(Tutorial tutorial,Tutor tutor) throws ClashException, PreExistException
     {
  	     tutor.assign(tutorial);	   	   
     }
     public void displayAppList(HashMap<String,Application> appList)
     {
        Set keySet = appList.keySet();
        Iterator iterator = keySet.iterator();
        while (iterator.hasNext()) {
           String key = (String) iterator.next();
           System.out.println(appList.get(key).toString());
           System.out.println();
        }
        hold();
     }
     
}

