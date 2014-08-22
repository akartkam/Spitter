<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div>
  <h2>Spittle for ${spitter.username}</h2>
  <script src="/js/jquery-2.1.1.min.js"></script>
  <script>
     $( "deleteSpittle_"${spittle.id} ).submit(function( event ) {
      if(confirm("Are you sure you want to delete this spittle?")) {
        return;
      }
    }
  </script>

  <table cellspacing="15" class="horizontalRuled">
    <c:forEach items="${spittleList}" var="spittle">
    <s:url value="/spitters/${spittle.spitter.username}" var="spitter_url" />
    <tr>
      <td>
          <img src="http://s3.amazonaws.com/spitterImages/${spittle.spitter.id}.jpg"
               width="48"
               border="0" 
               align="middle" 
               onError="this.src='<s:url value="/resources/images"/>/spitter_avatar.png';"/></td>
      <td><a href="${spitter_url}">${spittle.spitter.username}</a>
          <small><c:out value="${spittle.text}" /><br/><small>
          <fmt:formatDate value="${spittle.when}" pattern="hh:mma MMM d, yyyy" />
          </small></small>
      <s:url value="/spittles/${spittle.id}" var="spittle_url" />
      <sf:form method="delete" action="${spittle_url}" 
               name="deleteSpittle_${spittle.id}" 
               cssClass="deleteForm">
        <input type="submit" value="Delete"/>
      </sf:form>      
      </td>
    </tr>
    </c:forEach>
  </table>
</div>
