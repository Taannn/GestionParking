package fr.usmb.m2isc.javaee.comptes.web;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.usmb.m2isc.javaee.comptes.ejb.Operation;
import fr.usmb.m2isc.javaee.comptes.jpa.Ticket;
import fr.usmb.m2isc.javaee.comptes.jpa.Paiement;

/**
 * Servlet implementation class PayerTicketServlet
 */
@WebServlet("/PayerTicketServlet")
public class PayerTicketServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private Operation ejb;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PayerTicketServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long numTicket = Long.parseLong(request.getParameter("numTicket"));
		Ticket tck = ejb.getTicket(numTicket);
		switch(request.getParameter("moyenPaiement")) {
			case "CB":
				tck = ejb.payerTicket(numTicket, Paiement.TypePaiement.CB);
				break;
			case "ES":
				tck = ejb.payerTicket(numTicket, Paiement.TypePaiement.Especes);
				break;
		}
		request.setAttribute("ticket", tck);
		request.getRequestDispatcher("/AfficherTicketPaiement.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
