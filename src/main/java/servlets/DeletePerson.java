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

import dao.PersonDao;
import entities.Person;
import jpa.DAO;

@WebServlet(name="deletePerson", urlPatterns={"/DeletePerson"})
public class DeletePerson extends HttpServlet {
	PersonDao pd = new PersonDao();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		 PrintWriter out = response.getWriter();
		 out.println("<h2><a href=\"index.html\">Retour page d'acueil</a></h2>");
		 out.println("<FORM Method=\"POST\" Action=\"/DeletePerson\">\r\n" + 
		 		"		<table border = 1 cellpadding = \"10\" align = \"center\">\r\n");
		 out.println("<td>Proprietaire</td><td><SELECT name=\"personToDelete\" size=\"5\">");
		 for (Person p :pd.allPersons())
		 {
			 out.println("<option value = \""+ p.getId()+ "\">"+p.getFirstName() + " " + p.getFamilyName()+"</option>");
		 }
		 		out.println("				</td></tr><tr ><td colspan = \"2\" align = \"right\"><INPUT type=submit value=Send></td>\r\n");
		 out.println(	"		</table>\r\n" + 
		 		"		</FORM>");
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("dev");
		EntityManager manager = factory.createEntityManager();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		response.setContentType("text/html");
		 PrintWriter out = response.getWriter();
			 PersonDao pd = new PersonDao();
			 pd.deleteById(Long.parseLong(request.getParameter("personToDelete")));
			 tx.commit();
		 out.println("<h2><a href=\"index.html\">Retour page d'acueil</a></h2>");
         out.println("<table border = 2 cellpadding = \"10\" cellspacing = \"10\" align= \"center\">  <tr>  <th>Prenom</th> <th>Nom</th>  <th>Email</th> " );
         DAO test = new DAO(manager);
         for (Person next : test.listPersons()) {
             out.println( " <tr>    <td>"+next.getFirstName()+"</td>   <td>"+next.getFamilyName()+"</td>   <td>"+next.getMail()+"</td> </tr>  " ) ;
         }
         out.println( "</table> " ) ;
 		manager.close();
	}

}
