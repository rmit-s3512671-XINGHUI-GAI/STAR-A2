import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class CourseOffering{
    private Lecture lecture;
    private int expectedStNo;
    private Course course;
    private int year;
    private int semester;
    private int tutorialCount;
	private HashMap<String,Tutorial> tutorials;
			
	public CourseOffering(int expectedStNo, int year, int sem)
	{
	    this.expectedStNo = expectedStNo;	
	    this.year = year;
	    this.semester = sem;
	    String key = ""+year+":"+sem;
	    lecture = null;
	    tutorials=new HashMap<String,Tutorial>();
	    
	    this.tutorialCount=1;
	}
    public void assignLecture(int lectureDay, double lectureStartHr, double lectureDur, Venue venue) throws ClashException, PreExistException
    {	   
	    if (lecture != null) throw new PreExistException("Lecuture already exist");
	    lecture = new Lecture(lectureDay, lectureStartHr, lectureDur, venue);
    }
    
    public void assignTutorial(int tutorialDay, double tutorialStartHr, double tutorialDur, int num,Venue venue) throws ClashException, PreExistException
    {	
	    Tutorial t = new Tutorial(tutorialDay, tutorialStartHr, tutorialDur,num, venue);
	    if(checkCapacity(num))
	    {
	        String key = "T"+this.tutorialCount;
		     tutorialCount++;
	        this.tutorials.put(key, t) ;
	    }
	    else
	    {
	    	System.out.println("The number exceeds Expected number of the Course offering. Assign failed.");
	    	return;
	    }
    }
    
    public Lecture getLecture()
    {
	    return lecture;
    }
   
    public HashMap<String,Tutorial> getTutorialList()
    {
    	return this.tutorials;
    }
    public Tutorial getTutorial(String tCode)
    {
    	return tutorials.get(tCode);
    }
    public String toString()
    {      
	   String s = "";
	   if (course != null)
	   {   s = "Id = " + course.getId();
	       s += "\nName = " + course.getName();
	   }
       s += "\nYear = : " + year + " Semester : " + semester;
	   s += "\nExpected student number " + expectedStNo;
	   if (lecture != null)
		   s += "\n" + lecture.toString();
	   if(tutorials!=null)
		   s+="\n"+tutorials;
	   return s;
    }
	
	public void setCourse(Course c)
	{
		this.course=c;
	}
	public boolean checkCapacity(int n){
		int num=0;
		Set keySet = tutorials.keySet();
	    Iterator iterator = keySet.iterator();
	    while (iterator.hasNext()) {
	         String key = (String) iterator.next();
	         num+=tutorials.get(key).getCapacity();
	      }
	    if(num+n>this.expectedStNo)
	    {
	    	return false;
	    }
	    else{
	    	return true;
	    }
		
	}

   
}