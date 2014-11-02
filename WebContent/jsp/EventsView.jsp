<%@ include file="../WEB-INF/jspf/header.jspf"%>
<script>
	$(function() {
		var pickerOpts = {
			dateFormat : "dd-mm-yy",
			firstDay : 1,
		};
		$("#debutEvent").datepicker(pickerOpts);
	});
	$(function() {
		var pickerOpts = {
			dateFormat : "dd-mm-yy",
			firstDay : 1,
		};
		$("#finEvent").datepicker(pickerOpts);
	});
	$(function() {
		jQuery('#datetimepicker').datetimepicker({
			datepicker : false,
			format : 'H:i'
		});
	});
	$(function() {
		jQuery('#datetimepicker2').datetimepicker({
			datepicker : false,
			format : 'H:i'
		});
	});
</script>
<div class="eventsView">
	<c:if test='${lastAction=="registerOK"}'>
		<div class="alert alert-success" role="alert">
			Bienvenue sur l'espace de gestion de vos événements !
			<button type="button" class="close" data-dismiss="alert">
				<span aria-hidden="true">&times;</span> <span class="sr-only">Close</span>
			</button>
		</div>
	</c:if>
	<c:if test='${lastAction=="connexionOK"}'>
		<div class="alert alert-success" role="alert">
			Vous êtes connecté !
			<button type="button" class="close" data-dismiss="alert">
				<span aria-hidden="true">&times;</span> <span class="sr-only">Close</span>
			</button>
		</div>
	</c:if>
	<c:if test='${lastAction=="publishSuccess"}'>
		<div class="alert alert-success" role="alert">
			Publication effectuée avec succès !
			<button type="button" class="close" data-dismiss="alert">
				<span aria-hidden="true">&times;</span> <span class="sr-only">Close</span>
			</button>
		</div>
	</c:if>
	<c:if test='${lastAction=="publishFailed"}'>
		<div class="alert alert-danger" role="alert">
			Publication echouée ! Vous n'avez pas les droits pour publier cet
			event !
			<button type="button" class="close" data-dismiss="alert">
				<span aria-hidden="true">&times;</span> <span class="sr-only">Close</span>
			</button>
		</div>
	</c:if>
	<c:if test='${lastAction=="deleteSuccess"}'>
		<div class="alert alert-success" role="alert">
			Suppression effectuée avec succès !
			<button type="button" class="close" data-dismiss="alert">
				<span aria-hidden="true">&times;</span> <span class="sr-only">Close</span>
			</button>
		</div>
	</c:if>
	<c:if test='${lastAction=="deleteFailed"}'>
		<div class="alert alert-danger" role="alert">
			Suppression echouée ! Vous n'avez pas les droits pour supprimer cet
			event !
			<button type="button" class="close" data-dismiss="alert">
				<span aria-hidden="true">&times;</span> <span class="sr-only">Close</span>
			</button>
		</div>
	</c:if>
	<c:if test='${lastAction=="addSuccess"}'>
		<div class="alert alert-success" role="alert">
			Ajout effectué avec succès !
			<button type="button" class="close" data-dismiss="alert">
				<span aria-hidden="true">&times;</span> <span class="sr-only">Close</span>
			</button>
		</div>
	</c:if>
	<c:if test='${lastAction=="addFailedBBE"}'>
		<div class="alert alert-danger" role="alert">
			Ajout echouée ! La date de fin de l'event est antérieur à sa date de
			début !
			<button type="button" class="close" data-dismiss="alert">
				<span aria-hidden="true">&times;</span> <span class="sr-only">Close</span>
			</button>
		</div>
	</c:if>
	<c:if test='${lastAction=="addFailedBEBT"}'>
		<div class="alert alert-danger" role="alert">
			Suppression echouée ! La date de début ou de fin de l'event est
			antérieur à maintenant !
			<button type="button" class="close" data-dismiss="alert">
				<span aria-hidden="true">&times;</span> <span class="sr-only">Close</span>
			</button>
		</div>
	</c:if>

	<h1>Page de gestions des events</h1>
	<ul class="nav nav-tabs" role="tablist">
		<li class="active"><a href="#participeIn" role="tab"
			data-toggle="tab">Event I participe in</a></li>
		<li><a href="#createdEvent" role="tab" data-toggle="tab">My
				created events</a></li>
		<li><a href="#createNew" role="tab" data-toggle="tab">Create
				a new event</a></li>
	</ul>

	<!-- Tab panes -->
	<div class="tab-content" style="background-color: #fff;">
		<div class="tab-pane active" id="participeIn">
			<br>
			<table class="table table-bordered">
				<tr>
					<td><u>Name</u></td>
					<td><u>Adress</u></td>
					<td><u>Url</u></td>
					<td><u>Beginning</u></td>
					<td><u>End</u></td>
				</tr>
				<c:forEach items="${listInscription}" var="inscription">
					<tr>
						<td><c:out value="${inscription.name} " /></td>
						<td><c:out value="${inscription.address} " /></td>
						<td><a href="event/${inscription.url}"><c:out
									value="event/${inscription.url} " /></a></td>
						<td><c:out value="${inscription.dateBeginning} " /></td>
						<td><c:out value="${inscription.dateEnd} " /></td>

					</tr>
				</c:forEach>
			</table>
		</div>
		<div class="tab-pane" id="createdEvent">
			<br>
			<table class="table table-bordered">
				<tr>
					<td><u>Name</u></td>
					<td><u>Adress</u></td>
					<td><u>Url</u></td>
					<td><u>Beginning</u></td>
					<td><u>End</u></td>
					<td><u>Options</u></td>
				</tr>
				<c:forEach items="${list}" var="eventList">
					<tr>
						<td><c:out value="${eventList.name} " /></td>
						<td><c:out value="${eventList.address} " /></td>
						<td><a href="event/${eventList.url}"><c:out
									value="event/${eventList.url} " /></a></td>
						<td><c:out value="${eventList.dateBeginning} " /></td>
						<td><c:out value="${eventList.dateEnd} " /></td>
						<td><c:if test="${eventList.published==0}">
								<a href="PublishEvent?id=${eventList.eventId}"><button
										type="button" class="btn btn-primary">Publier</button></a>
							</c:if> <a href="DeleteEvents?id=${eventList.eventId} "><button
									type="button" class="btn btn-danger">Supprimer</button></a></td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<div class="tab-pane" id="createNew">
			<h3>Description de l'événement</h3>
			<form role="form" action="${pageContext.request.contextPath}/events"
				method="post">
				<div class="form-group">
					<label class="labelEvents" for="nameEvent">Nom de l'événement</label> <input
						type="text" class="form-control inputEvents" id="nameEvent" name="event"
						required value="${event}"
						placeholder="Donnez-lui un titre court et significatif">
				</div>
				<div class="form-group">
					<label class="labelEvents" for="adressEvent">Adresse</label> <input type="text"
						class="form-control inputEvents" id="adressEvent" name="adress" required
						value="${adress}" placeholder="Précisez où il est organisé">
				</div>
				<div class="form-group">
					<label class="labelEvents" for="debutEvent">Debut Event</label> <input type="text"
						class="form-control" id="debutEvent" name="debut" required
						style="width: 150px; display: inline;" value="${debut}"
						placeholder="jj-mm-aaaa"> <input type="text"
						class="form-control inputEvents" id="datetimepicker" required name="debutH"
						style="width: 150px; display: inline;" value="${debut}"
						placeholder="hh-mm-ss">
				</div>
				<div class="form-group">
					<label class="labelEvents" for="finEvent">Fin Event</label> <input type="text"
						class="form-control" id="finEvent" name="fin" required
						style="width: 150px; display: inline; margin-left: 0;"
						value="${fin}" placeholder="jj-mm-aaaa"> <input
						type="text" class="form-control" id="datetimepicker2" required
						name="finH" style="width: 150px; display: inline;"
						value="${debut}" placeholder="hh-mm-ss">

				</div>
				<div style="margin-left: 15%;" class="checkbox">
					<label> <input type="checkbox" name="published" value="1">
						Publier
					</label>
				</div>
				<button style="margin-left: 15%;" type="submit" class="btn btn-primary">Créer
					l'événement</button>
			</form>
		</div>
	</div>
</div>

<script type="text/javascript">
<!--
	jQuery('#datetimepicker').datetimepicker();
//-->
</script>
<%@ include file="../WEB-INF/jspf/footer.jspf"%>
