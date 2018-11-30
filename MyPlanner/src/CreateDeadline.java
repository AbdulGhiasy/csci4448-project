import java.io.IOException;  
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  

/**
 * <b>Created by:</b><br/>Abdul Ghiasy<br/>
 * <br/>
 * <b>Note:</b><br/>This java file requests the entries entered by the user within the deadlines table, validates the format of the deadline column, and 
 * ultimately creates the deadline entry in the deadline table
 */

@WebServlet("/createdeadline")
public class CreateDeadline extends HttpServlet {  
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response)  
			throws ServletException, IOException {
		
		response.setContentType("text/html");  
		PrintWriter out = response.getWriter();  
		
		String u=request.getParameter("username");
		String n=request.getParameter("name");
		String d=request.getParameter("description");
		String t=request.getParameter("date");
		
		if(dateIsValid(t)) {		
			out.println("<h2>"+u+" created</h2><br/>");
			out.println("Deadline name: " + n + "<br/>");
			out.println("Deadline description: " + d + "<br/>");
			out.println("Deadline date: " + t + "<br/>");
	
			System.out.println("CreateDeadline: Creating deadline " + n + " for user " + u);
			
			out.println("<form action='home' method=\"post\">\n");
			out.println("<input type='hidden' name='username' value='"+u+"'>\n");
			out.println("<input type='submit' value='Continue' >\n");
			out.println("</form>");
			
			Deadline deadline = new Deadline(n, d, t);
			User.saveDeadline(u, deadline);
		} else {
			out.println("You entered a bad date, date should be formatted similar to the following:");
			out.println("2018/09/25");
			
			System.out.println("CreateDeadline: Creating deadline " + n + " for user " + u);
			
			out.println("<form action='createdeadlineform' method=\"post\">\n");
			out.println("<input type='hidden' name='username' value='"+u+"'>\n");
			out.println("<input type='submit' value='Go back!' >\n");
			out.println("</form>");
		}

		out.close();
	}

	private boolean dateIsValid(String t) {
	    try {
			new SimpleDateFormat("yyyy/MM/dd").parse(t);
		} catch (ParseException e) {
			return false;
		}  
		return true;
	}
}

