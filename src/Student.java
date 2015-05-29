import java.util.*;

public class Student {
	private String sNo;
	private String pwd;
	private String firstName;
	private String lastName;
	private String phone;
	
	private Exemption exemption;
    private ArrayList<String> preCourseList;
    private HashMap<String, Course> courseList;
    
    Student(String no,String pwd, String fn,String ln, String phone,ArrayList<String> pre,HashMap<String, Course> courses){
    	this.sNo=no;
    	this.pwd=pwd;
    	this.firstName=fn;
    	this.phone=phone;
    	this.lastName=ln;
    	this.preCourseList=pre;
    	this.courseList=courses;
    	this.exemption=null;
    }
    public void applyForExamption()
    {
    	this.exemption=new Exemption(sNo);
    }
    public Exemption getExemption(){
    	return this.exemption;
    }
    public ArrayList<String> getPre()
    {
    	return this.preCourseList;
    }
    public String getName()
    {
    	return this.firstName+" "+this.lastName;
    }
    public String getSNo()
    {
    	return this.sNo;
    }
    public String getPwd()
    {
    	return pwd;
    }
    public HashMap<String,Course> getCourse()
    {
    	return this.courseList;
    }
    
    /*check if the student meets prequirement of the course c
     * true:meet
     * false:does not  meet
     * */
    public boolean checkPreReq(Course c){
    	boolean flag=true;
    	ArrayList<Course> pre=c.getPrereqs();
    	for(int i=0;i<pre.size(); i++){
    		if(this.preCourseList.contains(pre.get(i).getId())){
    			flag=true;
    		}
    		else
    		{
    			flag=false;
    			break;
    		}
    		
    	}
    	return flag;
    }

/* check have
 * check if his student has ever learned some course
 * true:has
 * false:hasn't so that the course coule be enrolled by user.
 * */
    public boolean checkHave(Course c)
    {
    	if(this.preCourseList.contains(c.getId())){
    		return true;
    	}
    	else{
    		return false;
    	}
    }

    public void enrollCourse(Course c){
        this.addCourse(c);
    }
    
    public String toString(){
    	String s = "Id = " + sNo;
        s+= " Name = " + this.getName();
    	s+= "courseList:"+courseList ;  
        return s;
    }
    
    public void addCourse(Course c)
    {
    	if(courseList.get(c.getId())!=null){
    		System.out.println("You have enrolled the course.");
    	}
    	else{
    	    this.courseList.put(c.getId(), c);
    	    System.out.println("successfully enrolled!");
    	}
    }
    
    public boolean ifEnroll(String cId){
    	if(courseList.get(cId)!=null)
    	{
    		return true;
    	}
    	else
    	{
    		return false;
    	}
    	
    }
    public void withDraw(String cId)
    {
    	courseList.remove(cId);
    }
    
    public Application applyForTutor()
	{
		return new Application(this.sNo,this.getName());
	}
}
