<%@ include file="../WEB-INF/jspf/header.jspf"%>

<c:forEach items="${events}" var="eventList">
	<h2>
		<c:out value="${eventList.name} " />
	</h2>
</c:forEach>

<%@ include file="../WEB-INF/jspf/footer.jspf"%>