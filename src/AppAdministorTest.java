import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class AppAdministorTest {

	App app;
	AppAdministor appadmin;
	AppStu appstu;
	ArrayList<String> pre1=new ArrayList<String>();
	
	@Before
	public void setUp(){
		app=new App();
		appadmin =new AppAdministor();
		appstu=new AppStu(new Student("s111","111", "Arther","Pendragon", "0614456234",pre1,new HashMap<String, Course>()));		
	}
	
	@Test
	/** @author s3511792*/
	/* System should prevent not allow a student  to be admitted twice. */
	public void testAdmitExemptionOnce() {
	    appstu.handleApplyForExemption(app.students);
	   
	    //Enter s111 twice
	    appadmin.handleGrantExemption(app.students);
	    appadmin.handleGrantExemption(app.students);
	    
	    assertEquals(appstu.student.getExemption().getState(),Exemption.STATE_ADMITTED);
	}

	@Test
	/** @author s3511792*/
    /*System should not allow students to enrol into a course offering without the necessary prerequisites*/
	public void CoursePreTest(){
		//try to enroll Net1
		appstu.handleEnroll(app.courses);
		assertTrue(appstu.student.getCourse().get("Net1")==null);
	}

}
