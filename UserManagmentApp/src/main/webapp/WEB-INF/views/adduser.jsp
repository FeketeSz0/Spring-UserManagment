<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
   <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

      <head>
         <title>Edit User</title>
         <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
            integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
      </head>

      <body>
         <div class="container">
            <jsp:include page="upbar.jsp" />
            <h2>Add User</h2>
            <form:form action="adduser" method="post" modelAttribute="user">
               <div class="container">
                  <jsp:include page="form.jsp" />
                  <div class="form-group row">
                     <label for="role" class="col-sm-4 col-form-label">Role:</label>
                     <div class="col-sm-6">
                        <form:select class="form-control" id="role" path="role">
                           <form:option value="User" label="User" />
                           <form:option value="Admin" label="Admin" />
                        </form:select>
                        <form:errors path="role" cssClass="text-danger" />
                     </div>
                  </div>
               </div>
               <div class="button-group">
                  <button type="submit" class="btn btn-primary">Add User</button>
                  <a href="/table" class="btn btn-secondary">Cancel</a>
               </div>

            </form:form>
         </div>
      </body>

      </html>