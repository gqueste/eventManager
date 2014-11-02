<%@ include file="../WEB-INF/jspf/header.jspf"%>
<div class="register">
	<h1>Inscrivez vous</h1>
	<form class="form-horizontal" role="form"
		action="${pageContext.request.contextPath}/register" method="post">
		<input type="hidden" name="tentative" value="tent">
		<div class="form-group">
			<label for="inputEmail3" class="col-sm-3 control-label">Email</label>
			<div class="col-sm-9">
				<input type="email" class="form-control" name="mail" id="inputEmail"
					placeholder="Email" value="${mail}">
			</div>
		</div>
		<div class="form-group">
			<label for="inputName" class="col-sm-3 control-label">Nom</label>
			<div class="col-sm-9">
				<input type="text" class="form-control" name="name" id="inputName"
					placeholder="Nom" value="${name}">
			</div>
		</div>
		<div class="form-group">
			<label for="inputName" class="col-sm-3 control-label">Prénom</label>
			<div class="col-sm-9">
				<input type="text" class="form-control" name="surname"
					id="inputSurname" placeholder="Prénom" value="${surname}">
			</div>
		</div>
		<div class="form-group">
			<label for="inputName" class="col-sm-3 control-label">Entreprise</label>
			<div class="col-sm-9">
				<input type="text" class="form-control" name="company"
					id="inputCompany" placeholder="Entreprise" value="${company}">
			</div>
		</div>
		<div class="form-group">
			<label for="inputPassword3" class="col-sm-3 control-label">Password</label>
			<div class="col-sm-9">
				<input type="password" class="form-control" name="password"
					id="inputPassword" placeholder="Password" value="${password}">
			</div>
		</div>
		<div class="form-group">
			<label for="inputPassword4" class="col-sm-3 control-label">Confirmez
				le password</label>
			<div class="col-sm-9">
				<input type="password" class="form-control" name="confirm-password"
					id="confirmPassword" placeholder="Confirmez le password">
			</div>
		</div>
		<c:if test="${noError ne erreurs}">
			<div class="form-group">
				<div style="padding: 0px;" class="panel panel-danger col-sm-offset-3 col-sm-9">
					<div class="panel-heading">
						<h3 class="panel-title">Erreur</h3>
					</div>
					<div class="panel-body">${erreurs}</div>
				</div>
			</div>
		</c:if>
		<div class="form-group">
			<div class="col-sm-offset-3 col-sm-9">
				<button type="submit" class="btn btn-success">Créer le
					compte</button>
			</div>
		</div>
	</form>
</div>
<%@ include file="../WEB-INF/jspf/footer.jspf"%>