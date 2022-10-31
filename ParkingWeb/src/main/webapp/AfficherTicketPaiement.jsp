<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Info Ticket</title>
	<link rel="stylesheet" type="text/css" href="css/base.css" >
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
</head>
<body>

	<h1>Affichage ticket</h1>
	<h2>Informations ticket :</h2>
	<p>numero : ${ticket.numTicket }</p>
	<p>date d'entree : ${ticket.dateEntree }</p>
	<p>montant : ${ticket.montant() } euro</p>
	<form action="PayerTicketServlet" method="post">
		<select id="moyenPaiement" name="moyenPaiement">
		    <option value="CB">CB</option>
		    <option value="Especes">Especes</option>
		</select>
		<input type="hidden" id="numTicket" name="numTicket" value="${ticket.numTicket }" />
		<input type="submit" value="payer">
	</form>
	
	<c:if test='${aPaye}' >
		<form action="CreerJustificatifServlet" method="post">
			<input type="hidden" id="numTicket" name="numTicket" value="${ticket.numTicket }" />
			<input type="submit" value="Avoir un justificatif">
		</form>
	</c:if>

	<a href="index.html">Partir de la borne</a>
</body>
</html>