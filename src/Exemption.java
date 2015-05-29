
public class Exemption {
	public static final int STATE_INITIAL=0;
	public static final int STATE_ADMITTED=1;
	public static final int STATE_REJECTED=2;
    
	private String stuNo;	
	private int state;
	
	Exemption(String no){
		stuNo=no;
		state=STATE_INITIAL;
	}
	public int getState()
	{
		return this.state;
	}
	public void setState(int s)
	{
		this.state=s;
	}
}
