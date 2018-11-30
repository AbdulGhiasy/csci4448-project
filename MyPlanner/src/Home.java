import java.io.IOException;  
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  

/**
 * @author Abdul Ghiasy
 * <br/>
 * <b>Note:</b><br/> This file consists of the user's main page
 */

@WebServlet("/home")
public class Home extends HttpServlet {  
	private static final long serialVersionUID = 1979596949857069668L;

	public void doPost(HttpServletRequest request, HttpServletResponse response)  
			throws ServletException, IOException {  

		response.setContentType("text/html");  
		PrintWriter out = response.getWriter();  

		String n=request.getParameter("username");
		out.print("Logged in as: "+n);
		User u = User.getUser(n);
		if(u != null) {
// NOTES =======================================================
			// header 2 for notes + small link to create new notes
			String notesHeader = "<h2>Notes</h2>";
			notesHeader = notesHeader + "<form action='createnoteform' method=\"post\">\n";
			notesHeader = notesHeader + "<input type='hidden' name='username' value='"+n+"'>\n";
			notesHeader = notesHeader + "<input type='submit' value='Create notes' >\n";
			notesHeader = notesHeader + "</form>";
			// print all notes
			// do for loop for notes to be entered into table

			// table header
			String tableHeader = "<table bgcolor=\"blue\"><tr><th>Name</th><th>Description</th></tr>";
			String table = "";
			// table
			for(int i=0; i<u.notes.size(); i++) {
				if((i % 2) == 0) {
					table = table + "<tr bgcolor=\"#FFFFFF\">";
				} else {
					table = table + "<tr bgcolor=\"#AAAAAA\">";
				}
				table = table + "<td>" + u.notes.get(i).getNoteName() + "</td>"+
						"<td>" + u.notes.get(i).getNoteDescription() + "</td>"+
						"</tr>";
			}

			// finished notes
			String notes = tableHeader + '\n' + table + "</table>";
			out.println(notesHeader);
			out.println(notes);
// DEADLINES ===================================================
			// header 2 for notes + small link to create new notes
			String deadlinesHeader = "<h2>Deadlines</h2>";
			deadlinesHeader = deadlinesHeader + "<form action='createdeadlineform' method=\"post\">\n";
			deadlinesHeader = deadlinesHeader + "<input type='hidden' name='username' value='"+n+"'>\n";
			deadlinesHeader = deadlinesHeader + "<input type='submit' value='Create deadline' >\n";
			deadlinesHeader = deadlinesHeader + "</form>";
			// print all notes
			// do for loop for notes to be entered into table

			// table header
			tableHeader = "<table bgcolor=\"red\"><tr><th style=\"text-align: left;\">Name</th><th>Description</th><th>Deadline<th></tr>";
			table = "";
			// table
			for(int i=0; i<u.deadlines.size(); i++) {
				if((i % 2) == 0) {
					table = table + "<tr bgcolor=\"#FFFFFF\">";
				} else {
					table = table + "<tr bgcolor=\"#AAAAAA\">";
				}
				table = table + "<td>" + u.deadlines.get(i).getDeadlineName() + "</td>"+
						"<td>" + u.deadlines.get(i).getDeadlineDescription() + "</td>"+
						"<td>" + u.deadlines.get(i).getDeadlineDate() + "</td>"+
						"</tr>";
			}

			// finished notes
			String deadlines = tableHeader + '\n' + table + "</table>";			
			out.println(deadlinesHeader);
			out.println(deadlines);

			out.close();  
		} 
	}

}  