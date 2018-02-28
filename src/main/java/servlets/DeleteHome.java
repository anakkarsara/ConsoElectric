package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.HomeDao;
import entities.Home;
import entities.Person;

@WebServlet(name="deleteHome", urlPatterns={"/DeleteHome"})
public class DeleteHome extends HttpServlet {

	HomeDao hd = new HomeDao();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		 PrintWriter out = response.getWriter();
		 out.println("<h2><a href=\"index.html\">Retour page d'acueil</a></h2>");
		 out.println("<FORM Method=\"POST\" Action=\"/PersonInfo\">\r\n" + 
		 		"		<table border = 1 cellpadding = \"10\" align = \"center\">\r\n");
		 out.println("<td>Proprietaire</td><td><SELECT name=\"home\" size=\"5\">");
		 for (Home p :hd.allHomes())
		 {
			 out.println("<option value = \""+ p.getId()+ "\">"+p.getMyHome());
		 }
		 		out.println("				</td></tr><tr ><td colspan = \"2\" align = \"right\"><INPUT type=submit value=Send></td>\r\n");
		 out.println(	"		</table>\r\n" + 
		 		"		</FORM>");
	}
}
