package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Person;
import jpa.DAO;

@WebServlet(name="addED", urlPatterns={"/AddED"})
public class AddElectronicDevice extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("dev");
		EntityManager manager = factory.createEntityManager();
		DAO test = new DAO(manager);
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		response.setContentType("text/html");
		 PrintWriter out = response.getWriter();
		 out.println("<h2><a href=\"index.html\">Retour page d'acueil</a></h2>");
		 out.println("<FORM Method=\"POST\" Action=\"/EdInfo\">\r\n" + 
		 		"		<table border = 1 cellpadding = \"10\" align = \"center\">\r\n" + 
		 		"		<tr> <td>Name : 	</td>	<td> <INPUT type=text size=20 name=name></td> </tr>\r\n" + 
		 		"		<tr> <td>Conso : </td>	<td>	<INPUT type=text size=20 name=conso></td> </tr>\r\n");
		 out.println("<td>Proprietaire</td><td><SELECT name=\"owner\" size=\"1\">");
		 for (Person p :test.listPersons())
		 {
			 out.println("<option value = \""+ p.getId()+ "\">"+p.getFirstName() + " " + p.getFamilyName()+"</option>");
		 }
		 		out.println("				</td></tr><tr ><td colspan = \"2\" align = \"right\"><INPUT type=submit value=Send></td>\r\n");
		 out.println(	"		</table>\r\n" + 
		 		"		</FORM>");
	}

}
