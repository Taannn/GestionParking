<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Info Ticket</title>
	<link rel="icon" type="image/x-icon" href="/favicon.ico">
	<link rel="stylesheet" type="text/css" href="css/base.css" >
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
</head>
<body>
	<div class="parking">
		<div class="borne">
			<div class="screen">
				<h2>Informations ticket</h2>
				<p>numero : ${ticket.numTicket }</p>
				<p>date d'entree : ${ticket.dateEntree }</p>
				<h4>montant : ${ticket.montant() } euro</h4>
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
			</div>
		</div>
	</div>
</body>
</html>