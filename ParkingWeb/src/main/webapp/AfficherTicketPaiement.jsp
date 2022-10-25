<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Info Ticket</title>
	<link rel="stylesheet" type="text/css" href="css/base.css" >
</head>
<body>
	<h1>Affichage ticket</h1>
	<h2>Informations ticket :</h2>
	<p>numero : ${ticket.numTicket }</p>
	<p>date d'entrée : ${ticket.dateEntree }</p>
	<p>montant : ${ticket.montant() } €</p>
	<form action="PayerTicketServlet" method="post">
		<select id="moyenPaiement" name="moyenPaiement">
		    <option value="CB">CB</option>
		    <option value="ES">Espèces</option>
		</select>
		<input type="hidden" id="numTicket" name="numTicket" value="${ticket.numTicket }" />
		<input type="submit" value="payer">
	</form>
	<c:choose>
    <c:when test="${ticket.getPaiements().size() != 0 }">
       <form action="CreerJustificatifServlet" method="post">
			<input type="submit" value="Avoir un justificatif">
		</form>
        <br />
    </c:when>    
</c:choose>

	<a href="index.html">Partir de la borne</a>
</body>
</html>