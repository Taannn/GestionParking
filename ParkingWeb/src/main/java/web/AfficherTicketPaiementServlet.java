package web;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ejb.Operation;
import jpa.Ticket;

/**
 * Servlet implementation class AfficherTicketPaiementServlet
 */
@WebServlet("/AfficherTicketPaiementServlet")
public class AfficherTicketPaiementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB
	private Operation ejb;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AfficherTicketPaiementServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long numTicket = Long.parseLong(request.getParameter("numTicket"));
		Ticket tck = ejb.getTicket(numTicket);
		request.setAttribute("ticket", tck);
		request.setAttribute("aPaye", tck.getPaiements().size() != 0);
		request.getRequestDispatcher("/AfficherTicketPaiement.jsp").forward(request, response);	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
