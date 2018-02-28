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

import dao.HomeDao;
import entities.Home;
import entities.Person;
import jpa.DAO;

@WebServlet(name="homesInfo", urlPatterns={"/HomesInfo"})
public class HomeDetails extends HttpServlet {
	
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
		 out.println("<table border = 2 cellpadding = \"10\" cellspacing = \"10\" align= \"center\">  <tr>  <th>Maison</th> <th>Surface</th>  <th>Nb pieces</th> <th>Proprietaire</th>" );
         for (Home next : test.allHomes()) {
             out.println( " <tr>    <td>"+next.getMyHome()+"</td>   <td>"+next.getSurface()+"</td>   <td>"+next.getNumRooms()+"</td> <td>"+ next.getOwner().getFamilyName()+" "+ next.getOwner().getFirstName()+"</td></tr>  " ) ;
         }
         out.println( "</table> " ) ;
 		tx.commit();
 		manager.close();
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("dev");
		EntityManager manager = factory.createEntityManager();
		DAO test = new DAO(manager);
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		response.setContentType("text/html");
		 PrintWriter out = response.getWriter();
		 if(request.getParameter("home").isEmpty())
		test.createHome(request.getParameter("myhome"), Double.parseDouble(request.getParameter("surface")) , Integer.parseInt(request.getParameter("nbpce")), test.getPerson(Long.parseLong(request.getParameter("owner"))));
		 
		 else
		 {
			 HomeDao hd = new HomeDao();
			 hd.deleteById(Long.parseLong(request.getParameter("home")));
		 }
		 out.println("<h2><a href=\"index.html\">Retour page d'acueil</a></h2>");
         out.println("<table border = 2 cellpadding = \"10\" cellspacing = \"10\" align= \"center\">  <tr>  <th>Maison</th> <th>Surface</th>  <th>Nb pieces</th> <th>Proprietaire</th> " );
         for (Home next : test.allHomes()) {
             out.println( " <tr>    <td>"+next.getMyHome()+"</td>   <td>"+next.getSurface()+"</td>   <td>"+next.getNumRooms()+"</td> <td> "+ next.getOwner().getFamilyName()+" "+ next.getOwner().getFirstName()+"</td></tr>  " ) ;
         }
         out.println( "</table> " ) ;
 		tx.commit();
 		manager.close();
	}
}