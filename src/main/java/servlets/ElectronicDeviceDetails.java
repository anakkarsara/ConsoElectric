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

import dao.ElectronicDeviceDao;
import entities.ElectronicDevice;
import entities.Home;
import jpa.DAO;

@WebServlet(name="edInfo", urlPatterns={"/EdInfo"})
public class ElectronicDeviceDetails extends HttpServlet {
	
	ElectronicDeviceDao edd = new ElectronicDeviceDao();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		 PrintWriter out = response.getWriter();
		 out.println("<h2><a href=\"index.html\">Retour page d'acueil</a></h2>");
		 out.println("<table border = 2 cellpadding = \"10\" cellspacing = \"10\" align= \"center\">  <tr>  <th>Name</th> <th>Conso</th> <th>Proprietaire</th> </tr>" );
         for (ElectronicDevice next : edd.allEDs()) {
             out.println( " <tr>    <td>"+next.getEdName()+"</td>   <td>"+next.getConsoMoy()+"</td>   <td>"+ next.getOwner().getFamilyName()+" "+ next.getOwner().getFirstName()+"</td></tr>  " ) ;
         }
         out.println( "</table> " ) ;
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("dev");
		EntityManager manager = factory.createEntityManager();
		DAO test = new DAO(manager);
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		response.setContentType("text/html");
		 PrintWriter out = response.getWriter();
		edd.createED(request.getParameter("name"), Double.parseDouble(request.getParameter("conso")), test.getPerson(Long.parseLong(request.getParameter("owner"))));
		 out.println("<h2><a href=\"index.html\">Retour page d'acueil</a></h2>");
         out.println("<table border = 2 cellpadding = \"10\" cellspacing = \"10\" align= \"center\">  <tr>  <th>Name</th> <th>Conso</th> <th>Proprietaire</th> </tr>" );
         for (ElectronicDevice next : edd.allEDs()) {
             out.println( " <tr>  <td>"+next.getEdName()+"</td>   <td>"+next.getConsoMoy()+"</td>   <td>"+ next.getOwner().getFamilyName()+" "+ next.getOwner().getFirstName()+"</td>  </tr>  " ) ;
         }
         out.println( "</table> " ) ;
	}
	

}
