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
 * <b>Note:</b><br/>This java file requests the entries entered by the user within the notes table and 
 * ultimately creates the note entry in the note table
 */

@WebServlet("/createnote")
public class CreateNote extends HttpServlet {  
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response)  
			throws ServletException, IOException {
		
		response.setContentType("text/html");  
		PrintWriter out = response.getWriter();  
		
		String u=request.getParameter("username");
		String n=request.getParameter("name");
		String d=request.getParameter("description");
		
		out.println("<h2>"+u+" created</h2><br/>");
		out.println("Note name: " + n + "<br/>");
		out.println("Note description: " + d + "<br/>");

		System.out.println("CreateNote: Creating note " + n + " for user " + u);
		
		out.println("<form action='home' method=\"post\">\n");
		out.println("<input type='hidden' name='username' value='"+u+"'>\n");
		out.println("<input type='submit' value='Continue' >\n");
		out.println("</form>");
		
		Note note = new Note(n, d);
		User.saveNote(u, note);

		out.close();
	}
}

