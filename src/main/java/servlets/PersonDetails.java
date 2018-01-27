package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="persondetails",
urlPatterns={"/PersonInfo"})
public class PersonDetails extends HttpServlet {
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<HTML>\n<BODY>\n" +
				"<H1>Recapitulatif des informations</H1>\n" +
				"<UL>\n" +			
		" <LI>Firstname: "
				+ request.getParameter("firstname") + "\n" +
				" <LI>Familyname: "
				+ request.getParameter("familyname") + "\n" +
				" <LI>Email: "
				+ request.getParameter("email") + "\n" +
				"</UL>\n" +				
		"</BODY></HTML>");
		}
}