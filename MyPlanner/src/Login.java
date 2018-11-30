import java.io.IOException;  
import java.io.PrintWriter;  

import javax.servlet.RequestDispatcher;  
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  

/**
 * @author Abdul Ghiasy
 * <br/>
 * <b>Note:</b><br/> Java file consisting of user's ability to login to their account
 */

@WebServlet("/login")
public class Login extends HttpServlet {  
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response)  
			throws ServletException, IOException {  

		response.setContentType("text/html");  
		PrintWriter out = response.getWriter();  

		String n=request.getParameter("username");  
		String p=request.getParameter("userpass");  

		if(n != "" && p != "" && LoginDao.validate(n, p)){
			RequestDispatcher rd=request.getRequestDispatcher("home");  
			rd.forward(request,response);  
		}  
		else{  
			out.print("Sorry username or password error");  
			RequestDispatcher rd=request.getRequestDispatcher("login.html");  
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

