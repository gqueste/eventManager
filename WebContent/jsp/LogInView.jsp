<%@ include file="../WEB-INF/jspf/header.jspf"%>
<h1>Connectez-vous !</h1>
<form class="form-horizontal" role="form" action="${pageContext.request.contextPath}/login" method="post">
<input type="hidden" name="tentative" value="tent">
  <div class="form-group">
    <label for="inputEmail3" class="col-sm-2 control-label">Email</label>
    <div class="col-sm-10">
      <input type="email" class="form-control" name="mail" id="inputEmail" placeholder="Email">
    </div>
  </div>
  <div class="form-group">
    <label for="inputPassword3" class="col-sm-2 control-label">Password</label>
    <div class="col-sm-10">
      <input type="password" class="form-control" name="password" id="inputPassword" placeholder="Password">
    </div>
  </div>
  <c:if test="${noError ne erreurs}">
  	<div class="form-group">
  		<div class="col-sm-offset-2 col-sm-10">
  			<p class="bg-danger">${erreurs}</p>
  		</div>
  	</div>
  </c:if>
  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
      <button type="submit" class="btn btn-default">Se connecter</button>
    </div>
  </div>
  <div class="form-group">
  	<div class="col-sm-offset-2 col-sm-10">
  		<a href="${pageContext.request.contextPath}/register" style="cursor: pointer;">Je n'ai pas encore de compte.</a>
  	</div>
  </div>
</form>
<%@ include file="../WEB-INF/jspf/footer.jspf"%>