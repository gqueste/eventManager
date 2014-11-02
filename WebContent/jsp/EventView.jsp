<%@ include file="../WEB-INF/jspf/header.jspf"%>
<div class="col-sm-5 eventLeftColumn">
	<h3>
		<c:out value="${event.name} " />
	</h3>
	<p>
		<span class="glyphicon glyphicon-user"></span> Evênement créé par
		<c:out value="${users.surname}" />
		<c:out value="${users.name}" />
		,
		<c:out value="${users.company}" />
	</p>
	<p>
		<span class="glyphicon glyphicon-map-marker"></span>
		<c:out value="${event.address}" />
	</p>
	<p>
		<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
		<span class="glyphicon glyphicon-time"></span> Du
		<fmt:formatDate value="${event.dateBeginning}"
			pattern="dd/MM/yyyy HH:mm" />
		au
		<fmt:formatDate value="${event.dateEnd}" pattern="dd/MM/yyyy HH:mm" />
	</p>
	<div class="blank"></div>
	<c:if test='${lastActionEvent=="addSuccess"}'>
		<div class="alert alert-success" role="alert">
			Ajout effectué avec succès !
			<button type="button" class="close" data-dismiss="alert">
				<span aria-hidden="true">&times;</span> <span class="sr-only">Close</span>
			</button>
		</div>
	</c:if>
	<c:if test='${lastActionEvent=="addFailedMail"}'>
		<div class="alert alert-warning" role="alert">
			Utilisateur avec cette adresse déjà inscrit à l'évênement !
			<button type="button" class="close" data-dismiss="alert">
				<span aria-hidden="true">&times;</span> <span class="sr-only">Close</span>
			</button>
		</div>
	</c:if>
	<c:if test='${lastActionEvent=="addFailedMissed"}'>
		<div class="alert alert-warning" role="alert">
			Tous les champs sont obligatoires.
			<button type="button" class="close" data-dismiss="alert">
				<span aria-hidden="true">&times;</span> <span class="sr-only">Close</span>
			</button>
		</div>
	</c:if>
	<c:if test='${lastActionEvent=="userDisconnect"}'>
		<div class="alert alert-warning" role="alert">
			Cette adresse mail correspond à un compte. Veuillez vous connecter.
			<button type="button" class="close" data-dismiss="alert">
				<span aria-hidden="true">&times;</span> <span class="sr-only">Close</span>
			</button>
		</div>
	</c:if>
	<c:if test="${userId != \"-1\"}">
		<a
			href="${pageContext.request.contextPath}/AddInscriptionEvent?eventId=${event.eventId}">
			<button style="margin: 20px 10px;" type="button"
				class="btn btn-success">
				<span class="glyphicon glyphicon-ok"></span> S'inscrire à
				l'évênement
			</button>
		</a>
	</c:if>
	<c:if test="${userId == \"-1\"}">
		<div id="inscriptionForm">
			<fieldset>
				<legend>Inscription à l'évênement</legend>
				<p>
					<a href="${pageContext.request.contextPath}/login"
						style="cursor: pointer;">Déjà un compte ? Connectez vous !</a>
				</p>
				<form role="form"
					action="${pageContext.request.contextPath}/AddInscriptionEvent?eventId=${event.eventId}"
					method="get">
					<div class="form-group">
						<label class="labelRegisterEvent" for="inscriptionUserName">Nom</label>
						<input type="text" class="form-control inputRegisterEvent"
							name="inscriptionUserName" required value="${name}">
					</div>
					<div class="form-group">
						<label class="labelRegisterEvent" for="inscriptionUserSurname">Prénom</label>
						<input type="text" class="form-control inputRegisterEvent"
							name="inscriptionUserSurname" required value="${surname}">
					</div>
					<div class="form-group">
						<label class="labelRegisterEvent" for="inscriptionUserMail">Mail</label>
						<input type="email" class="form-control inputRegisterEvent"
							name="inscriptionUserMail" required value="${mail}">
					</div>
					<div class="form-group">
						<label class="labelRegisterEvent" for="inscriptionUserSociete">Société</label>
						<input type="text" class="form-control inputRegisterEvent"
							name="inscriptionUserSociete" required value="${societe}">
					</div>
					<button type="submit"
						style="float: right; margin-bottom: 20px; margin-right: 35px;"
						class="btn btn-success">S'inscrire</button>
				</form>
			</fieldset>
		</div>
	</c:if>
</div>
<div class="col-sm-7 eventRightColumn">
	<h4>Liste des participants</h4>
	<c:if test="${not empty inscriptions}">
		<table class="table table-condensed">
			<c:forEach items="${inscriptions}" var="inscriptionsList">
				<tr>
					<td><c:out value="${inscriptionsList.name}" /></td>
					<td><c:out value="${inscriptionsList.surname}" /></td>
					<td><c:out value="${inscriptionsList.mail}" /></td>
					<td><c:out value="${inscriptionsList.company}" /></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
	<c:if test="${empty inscriptions}">
		<p>Aucun inscrit à cet évênement pour le moment.</p>
	</c:if>
</div>


<%@ include file="../WEB-INF/jspf/footer.jspf"%>