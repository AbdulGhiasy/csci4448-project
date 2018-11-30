import java.io.IOException;  
import java.io.PrintWriter;  

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  

/**
 * <b>Created by:</b><br/>Abdul Ghiasy<br/>
 * <br/>
 * <b>Note:</b><br/>This java file allows the user to actually create the note entry they want into their planner
 */

@WebServlet("/createnoteform")
public class CreateNoteForm extends HttpServlet {  
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response)  
			throws ServletException, IOException {
		
		response.setContentType("text/html");  
		PrintWriter out = response.getWriter();
		
		out.println("<form action=\"createnote\" method=\"post\">");
		out.println("User:<input type=\"text\" name=\"username\" value=\""+request.getParameter("username")+"\" readonly><br/>");
		out.println("Name:<input type=\"text\" name=\"name\"/><br/>");
		out.println("Description:<input type=\"text\" name=\"description\"/><br/><br/>");  
		out.println("<input type=\"submit\" value=\"create\"/>");
		out.println("</form>");
		
		System.out.println("user is " + request.getParameter("username"));

		out.close();
	}
}

