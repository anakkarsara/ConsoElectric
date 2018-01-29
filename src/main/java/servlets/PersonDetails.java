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
import jpa.JpaTest;

@WebServlet(name="personInfo", urlPatterns={"/PersonInfo"})
public class PersonDetails extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("dev");
		EntityManager manager = factory.createEntityManager();
		JpaTest test = new JpaTest(manager);
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		response.setContentType("text/html");
		 PrintWriter out = response.getWriter();
		 out.println("<h2><a href=\"index.html\">Retour page d'acueil</a></h2>");
		 out.println("<table border = 2 cellpadding = \"10\" cellspacing = \"10\" align= \"center\">  <tr>  <th>Prenom</th> <th>Nom</th>  <th>Email</th> " );
         for (Person next : test.listPersons()) {
             out.println( " <tr>    <td>"+next.getFirstName()+"</td>   <td>"+next.getFamilyName()+"</td>   <td>"+next.getMail()+"</td> </tr>  " ) ;
         }
         out.println( "</table> " ) ;
 		tx.commit();
 		manager.close();
	}
	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("dev");
		EntityManager manager = factory.createEntityManager();
		JpaTest test = new JpaTest(manager);
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		response.setContentType("text/html");
		 PrintWriter out = response.getWriter();
		test.createPerson(request.getParameter("firstname"), request.getParameter("familyname") , request.getParameter("email"));
		 out.println("<h2><a href=\"index.html\">Retour page d'acueil</a></h2>");
         out.println("<table border = 2 cellpadding = \"10\" cellspacing = \"10\" align= \"center\">  <tr>  <th>Prenom</th> <th>Nom</th>  <th>Email</th> " );
         for (Person next : test.listPersons()) {
             out.println( " <tr>    <td>"+next.getFirstName()+"</td>   <td>"+next.getFamilyName()+"</td>   <td>"+next.getMail()+"</td> </tr>  " ) ;
         }
         out.println( "</table> " ) ;
 		tx.commit();
 		manager.close();
 		
		/*out.println("<HTML>\n<BODY>\n" +
				"<H1>List des personnes : </H1>\n" +
				"<UL>\n" +			
		" <LI>Firstname: "
				+ request.getParameter("firstname") + "\n" +
				" <LI>Familyname: "
				+ request.getParameter("familyname") + "\n" +
				" <LI>Email: "
				+ request.getParameter("email") + "\n" +
				"</UL>\n" +				
		"</BODY></HTML>");*/
     
		}
}