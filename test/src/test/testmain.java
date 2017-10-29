package test;
import java.sql.*;

public class testmain {

	public static void main(String[] args) {
		Connection con = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		try {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		con = DriverManager.getConnection("jdbc:mysql://academic-mysql.cc.gatech.edu/cs4400_Team_86", "cs4400_Team_86","KpKnhohb");
		myStmt = con.createStatement();
		//myRs = myStmt.executeQuery("select * from Student");
		//myRs = myStmt.executeQuery("select * from Student where User_name = 'user1'");
		//System.out.println(myRs.next());
		//myRs.beforeFirst();
		
		//while (myRs.next()) {
			//System.out.println(myRs.getString("User_name") + ", " + myRs.getString("Password"));
		//}
		//Asdd xxx=new Asdd(con);
		//xxx.temp();
		Login loginpage = new Login(con);
		loginpage.show();
		if(!con.isClosed())
		System.out.println("Successfully connected to " +"MySQL server using TCP/IP...");
		} catch(Exception e) {
		System.err.println("Exception: " + e.getMessage());
		} 
		//finally {
		//try {
		//if(con == null)
		//con.close();
	//	} catch(SQLException e) {}
		//}
		

	}

}
