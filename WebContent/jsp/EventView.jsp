<%@ include file="../WEB-INF/jspf/header.jspf"%>
<div class="col-sm-4">
<%-- <c:forEach items="${events}" var="eventList"> --%>
	<h2>
		<c:out value="${event.name} " />
	</h2>
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
		<fmt:formatDate value="${event.dateEnd}"
			pattern="dd/MM/yyyy HH:mm" />
	</p>

	<c:if test="${userId != \"-1\"}">
		<a
			href="${pageContext.request.contextPath}/AddInscriptionEvent?eventId=${event.eventId}">
			<button type="button" class="btn btn-success">
				<span class="glyphicon glyphicon-ok"></span> S'inscrire à
				l'évênement
			</button>
		</a>
	</c:if>
	<c:if test="${userId == \"-1\"}">
		<button type="button" class="btn btn-success" onclick="showHideForm()">
			<span class="glyphicon glyphicon-ok"></span> S'inscrire à l'évênement
		</button>
	</c:if>
	<div id="inscriptionForm" style="display: none;">
		<fieldset>
			<legend>S'inscrire</legend>
			<p>
				<a href="${pageContext.request.contextPath}/login"
					style="cursor: pointer;">Déjà un compte ? Connectez vous !</a>
			</p>
			<form role="form"
				action="${pageContext.request.contextPath}/AddInscriptionEvent?eventId=${event.eventId}"
				method="post">
				<div class="form-group">
					<label for="inscriptionUserName">Nom</label> <input type="text"
						class="form-control" id="inscriptionUserName" name="inscription"
						required value="${inscription}">
				</div>

			</form>
		</fieldset>
	</div>
<%-- </c:forEach> --%>
</div>
<div class="col-sm-6">Liste des participants</div>

<script type="text/javascript">
	var showHideForm = function() {
		if ($('#inscriptionForm')[0].style.display == "none")
			$('#inscriptionForm')[0].style.display = "block";
		else
			$('#inscriptionForm')[0].style.display = "none";
	}
</script>

<%@ include file="../WEB-INF/jspf/footer.jspf"%>