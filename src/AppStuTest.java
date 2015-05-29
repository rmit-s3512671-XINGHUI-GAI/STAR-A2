import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import java.io.InputStream;
import java.io.ByteArrayInputStream;


public class AppStuTest {
	App app;
	AppStu appstu;
	ArrayList<String> pre1=new ArrayList<String>();
	
	@Before
	public void setUp()
	{
		app = new App();
		appstu=new AppStu(new Student("s111","111", "Arther","Pendragon", "0614456234",pre1,new HashMap<String, Course>()));		
	}

	
	@Test
	/** @author s3511792*/
	/*System should prevent students from enrolling in more than 4 course offerings*/
	public void testCourseLimit(){
		String [] userInput ={ "P101\n","WP1\n","UI1\n","Math\n","Data1\n"};
		InputStream stdin = System.in;
		try
		{
			for (int i=0;i<userInput.length; i++)
			{
		        System.setIn(new ByteArrayInputStream(userInput[i].getBytes()));
		        appstu.handleEnroll(app.courses);
			}
		 }
		 finally
		 {
		     System.setIn(stdin);
		 }
	   assertEquals(appstu.student.getCourse().size(),4,0);
	}
}
