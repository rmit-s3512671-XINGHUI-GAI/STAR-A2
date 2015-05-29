//Controller
import java.util.*;

public class AppStu {
    Student student;
	private Scanner scan=new Scanner(System.in);
	
	AppStu(Student student)
	{
		this.student=student;
	}
	public void handleEnroll(HashMap<String,Course> courseList){
		Date nowDate= new Date(); 
		if(!nowDate.before(DateControll.enrollLine))
		{
			System.out.println("too late, last Enrol date has been passed..");
			hold();
			return;
		}
		System.out.println("Enter course id");
		String courseId=scan.nextLine();
		Course c=courseList.get(courseId);
		if(c==null)
		{
			System.out.println("The course does not exist..");
			return;
		}
	    if((student.getExemption()==null||student.getExemption().getState()!=Exemption.STATE_ADMITTED) &&!student.checkPreReq(c))
	    {
	    	System.out.println("do not meet prerequisite of this course..");
	    	//System.out.println(c.getPrereqs());
	    	System.out.println(student.getPre());
	    	return;
	    }
	    if(student.checkHave(c))
	    {
	    	System.out.println("The courses you have passed, could not be enrolled again..");
	    	hold();
	    	return;
	    }
	    if(student.getCourse().size()>=4){
	    	System.out.println("You have enrolled 4 courses..");
	    	hold();
	    	return;
	    }
	    student.enrollCourse(c);
	    
	}
	public void handleWithdraw(HashMap<String,Course> courseList){
		System.out.println("Enter course id");
		String courseId=scan.nextLine();
		Course c=courseList.get(courseId);
		if(c==null)
		{
			System.out.println("The course does not exist..");
			return;
		}
		Date nowDate= new Date(); 
		if(nowDate.before(DateControll.withdrawLine))
		{
			if(student.getCourse().get(courseId).getCourseOffering(DateControll.year, DateControll.sem).getTutorialList().size()!=0)
			{
				System.out.println("You have not withdrawn the tutorials");
				hold();
				return;
				
			}
				
			if(student.ifEnroll(c.getId()))
			{
				student.withDraw(c.getId());
			}
			else
			{
				System.out.println("You have not enrolled the course");
				hold();
				return;
			}
		}
		else
		{
			System.out.println("too late, last Withdraw date has been passed..");
			return;
		}
	}
	public void handleApplyForTutor(HashMap<String, Application> appList){
		if(appList.get(student.getSNo())!=null)
		{
			System.out.println("The application has been made.Please wait for your result..");
			hold();
			return;
		}
		System.out.println("Enter course id of which you want to apply for tutor qualification:");
		String courseId=scan.nextLine();
		Application app=student.applyForTutor();
		appList.put(student.getSNo(), app);
    }
	public void handleApplyForExemption(HashMap<String,Student> stuList){
		student.applyForExamption();
		stuList.put(student.getSNo(), student);
	}
	public void handleViewApplicationState(HashMap<String, Application> appList)
	{
		Application app= appList.get(student.getSNo());
		if(app==null)
		{
			System.out.println("no any application right now...");
			hold();
			return;
		}
		else
		{
			System.out.println(app.toString());
		}
	}
	
	
	public void handleRegisterTutorial(HashMap<String,Course> courseList){
		System.out.println("Enter course ID");
		String cId=scan.nextLine();
		Course course=courseList.get(cId);
		if(course==null)
		{
			System.out.println("Invalid course ID.");
			hold();
			return;
		}
		Course StuCourse=student.getCourse().get(cId);
		if(StuCourse==null)
		{
			System.out.println("You have not entrolled in the course.....");
			hold();
			return;
		}
		CourseOffering offer=course.getCourseOffering(DateControll.year, DateControll.sem);
		if(offer==null)
		{
			System.out.println("no course offering yet..");
			hold();
			return;
		}
		HashMap<String, Tutorial> tuList=offer.getTutorialList();
		if(tuList==null){
			System.out.println("no tutorials available yet..");
			hold();
			return;
		}
		System.out.println("Enter Tutorial id");
		String tId=scan.nextLine();
		Tutorial t= tuList.get(tId);
		if(t==null)
		{
			System.out.println("invalid Tutorial ID");
			hold();
			return;
		}
		HashMap<String, Tutorial> stuTuList=StuCourse.getCourseOffering(DateControll.year, DateControll.sem).getTutorialList();
		
		if(stuTuList.get(tId)!=null)
		{
			System.out.println("You have already registered "+tId);
			hold();
			return;	
		}
		if(!t.isAvailable())
		{
			System.out.println("There's no other room in the tutorial");
			hold();
			return;
		}
		t.newRegister();
		stuTuList.put(tId,t);
	}
	
	public void handleDeregisterTutorial(HashMap<String, Course> courseList)
	{
		System.out.println("Enter course ID");
		String cId=scan.nextLine();
		Course course=courseList.get(cId);
		if(course==null)
		{
			System.out.println("Invalid course ID.");
			hold();
			return;
		}
		Course StuCourse=student.getCourse().get(cId);
		if(StuCourse==null)
		{
			System.out.println("You have not entrolled in the course.....");
			hold();
			return;
		}
		CourseOffering offer=course.getCourseOffering(DateControll.year, DateControll.sem);
		if(offer==null)
		{
			System.out.println("no course offering yet..");
			hold();
			return;
		}
		HashMap<String, Tutorial> tuList=offer.getTutorialList();
		if(tuList==null){
			System.out.println("no tutorials available yet..");
			hold();
			return;
		}
		System.out.println("Enter Tutorial id");
		String tId=scan.nextLine();
		Tutorial t= tuList.get(tId);
		if(t==null)
		{
			System.out.println("invalid Tutorial ID");
			hold();
			return;
		}
		HashMap<String, Tutorial> stuTuList=StuCourse.getCourseOffering(DateControll.year, DateControll.sem).getTutorialList();
		
		if(stuTuList.get(tId)==null)
		{
			System.out.println("You havenot already registered "+tId);
			hold();
			return;	
		}
		t.newDeregister();
		stuTuList.remove(tId);
	}
	private void hold()
	   {
		   System.out.print("Press enter to continue");
		   scan.nextLine();
	   }
}