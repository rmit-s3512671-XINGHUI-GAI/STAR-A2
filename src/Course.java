/*
 * Author ???
 * modified by s3511792 11th May 2015
 */

import java.util.*;
public class Course {
   private String courseId;
   private String name;
   private String objective;
   private ArrayList<Course> prereqs;
   private HashMap<String,CourseOffering> offerings;

   public Course(String courseId, String name, String objective)
   {
      this.courseId = courseId;
      this.name = name;
      this.objective = objective;	
      prereqs = new ArrayList<Course>();
      offerings = new HashMap<String,CourseOffering>();
   }
   
   public CourseOffering createCourseOffering(int expectedNumber, int year,int semester)throws PreExistException
   {
	  if (getCourseOffering(year,semester) != null)
	  	 throw new PreExistException("Offering Already Exists");
	  String key = ""+year+":"+semester;
	  CourseOffering offering = new CourseOffering(expectedNumber,year,semester);
	  offering.setCourse(this);
	  offerings.put(key,offering);
	  return offering;
   }
   

   
   public CourseOffering getCourseOffering(int year, int sem)
   {
	   String key = ""+year+":"+sem;
	   return offerings.get(key);
   }
   
   public void addPrereq(Course c)
   {
	   prereqs.add(c);	   
   }   
   public String toString()
   {
	   String s = 
	   "Id = " + courseId;
	   s += " Name = " + name;
	   s+= " Objective = " + objective;  
	   s+= " Number of prereqs = " + prereqs.size();
	   return s;
   }
   public String getId()
   {
	   return courseId;
   }
   public String getName()
   {
	   return name;
   }

// New code 11th May 2015 by Callen Devens
   public ArrayList<Course> getPrereqs()
   {
	   return this.prereqs;
   }
}