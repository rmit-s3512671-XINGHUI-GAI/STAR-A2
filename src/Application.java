
public class Application {
	private String applicantNo;
	private String applicantName;
	private int state;
	public static final int STATE_TO_BE_APPOINTED=0;
	public static final int STATE_ADMITTED=2;
	
	Application(String no, String name){
		this.applicantName=name;
		this.applicantNo=no;
		state=0;
	}
	Application(String no)
	{
		this.applicantNo=no;
		this.state=0;
	}
	public void setState(int s){
		this.state=s;
	}
	public int getState()
	{
		return state;
	}
	public String getApplicantName()
	{
		return applicantName;
	}
	public String toString(){
		String s="Application: ";
		s+="aplicant No="+this.applicantNo+"  ";
		s+="applicant Name="+this.applicantName+"  ";
		s+="application state="+this.getState();
		return s;
	}
	
}
