package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import dao.HomeDao;
import dao.PersonDao;
import entities.Home;
import entities.Person;
import jpa.DAO;

@WebServlet(name="deleteHome", urlPatterns={"/DeleteHome"})
public class DeleteHome extends HttpServlet {

	private HomeDao hd = new HomeDao();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		 PrintWriter out = response.getWriter();
		 out.println("<h2><a href=\"index.html\">Retour page d'acueil</a></h2>");
		 out.println("<FORM Method=\"POST\" Action=\"/DeleteHome\">\r\n" + 
		 		"		<table border = 1 cellpadding = \"10\" align = \"center\">\r\n");
		 out.println("<td>Maisons</td><td><SELECT name=\"homeToDelete\" size=\"5\">");
		 for (Home p :hd.allHomes())
		 {
			 out.println("<option value = \""+ p.getId()+ "\">"+p.getMyHome());
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
			 HomeDao hd = new HomeDao();
			 hd.deleteById(Long.parseLong(request.getParameter("homeToDelete")));
		 out.println("<h2><a href=\"index.html\">Retour page d'acueil</a></h2>");
		 out.println("<table border = 2 cellpadding = \"10\" cellspacing = \"10\" align= \"center\">  <tr>  <th>Maison</th> <th>Surface</th>  <th>Nb pieces</th> <th>Proprietaire</th>" );
         DAO test = new DAO(manager);
         for (Home next : test.allHomes()) {
             out.println( " <tr>    <td>"+next.getMyHome()+"</td>   <td>"+next.getSurface()+"</td>   <td>"+next.getNumRooms()+"</td> <td>"+ next.getOwner().getFamilyName()+" "+ next.getOwner().getFirstName()+"</td></tr>  " ) ;
         }
         out.println( "</table> " );
 		manager.close();
	}
}
