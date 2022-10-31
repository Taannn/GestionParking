<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Justificatif</title>
	<link rel="stylesheet" type="text/css" href="css/base.css" >
</head>
<body>
	<h1>Affichage justificatif</h1>
	<p>numero : ${justif.numTicket }</p>
	<p>date d'entree : ${justif.dateEntree }</p>
	<p>date de paiement : ${justif.datePaiement }</p>
	<p>montant : ${justif.montant } euro</p>
	<a href="index.html">Partir de la borne</a>
</body>
</html>