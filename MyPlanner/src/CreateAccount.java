import java.io.IOException;  
import java.io.PrintWriter;  

import javax.servlet.RequestDispatcher;  
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  


/**
 * <b>Created by:</b><br/>Abdul Ghiasy<br/>
 * <br/>
 * <b>Note:</b><br/>This java file acts as a router from the login page to the user's home page, 
 * requires validation, and requires the text fields to NOT be empty
 */
@WebServlet("/newuser")
public class CreateAccount extends HttpServlet {  
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response)  
			throws ServletException, IOException {  

		response.setContentType("text/html");  
		PrintWriter out = response.getWriter();  

		String n=request.getParameter("username");
		String p=request.getParameter("userpass");
		String e=request.getParameter("email");

		if(n != "" && p != "" && e != "" && !LoginDao.userExists(n)){
			User.save(new User(e, n, p));
			System.out.println("CreateAccount: Created user " + n);
			RequestDispatcher rd=request.getRequestDispatcher("home");  
			rd.forward(request,response);  
		} else {
			out.print("Sorry username, password or email error, might be duplicate or empty fields...");  
			RequestDispatcher rd=request.getRequestDispatcher("newuser.html");  
			rd.include(request,response);
		}  

		out.close();  
	}  

	public static String getFullURL(HttpServletRequest request) {
		StringBuilder requestURL = new StringBuilder(request.getRequestURL().toString());
		String queryString = request.getQueryString();

		if (queryString == null) {
			return requestURL.toString();
		} else {
			return requestURL.append('?').append(queryString).toString();
		}
	}

}

