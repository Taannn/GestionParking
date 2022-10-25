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
	<a href="index.html">Partir de la borne</a>
</body>
</html>