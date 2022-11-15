package web;

import java.io.IOException;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ejb.Operation;
import jpa.Ticket;

/**
 * Servlet implementation class SortirParkingServlet
 */
@WebServlet("/SortirParkingServlet")
public class SortirParkingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private Operation ejb;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SortirParkingServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long numTicket = Long.parseLong(request.getParameter("numTicket"));
		Ticket tck = ejb.getTicket(numTicket);
		try {
			ejb.validerSortie(numTicket);
			request.setAttribute("operation", "Vous avez réussi à sortir du parking, bravo");
	    } catch (EJBException e) {
	    	request.setAttribute("operation", e.getMessage());
	    }
		request.getRequestDispatcher("/SortirParking.jsp").forward(request, response);	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
