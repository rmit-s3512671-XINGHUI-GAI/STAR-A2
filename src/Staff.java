public class Staff {
   private String eNo;
   private String name;
   private String pwd;
   private String position;
   public Staff(String eNo, String name,String pwd, String position)
   {
	   this.pwd=pwd;
	   this.eNo = eNo;
	   this.name = name;
	   this.position = position;   
   }
   public Staff(String eNo, String name,String pwd)
   {
	   this.pwd=pwd;
	   this.eNo = eNo;
	   this.name = name;
   }
   public String getName()
   {
	   return name;
   }
   public String getENo()
   {
	   return eNo;
   }
   public String getPwd()
   {
	   return this.pwd;
   }
   public String toString()
   {
	   return "eNo: " + eNo + " name: "+ name + "position: "+position; 
   }
}
