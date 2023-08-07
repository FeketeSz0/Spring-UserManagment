<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
   <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

      <head>
         <title>Create account</title>
         <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
            integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
         <script src="https://www.google.com/recaptcha/api.js" async defer></script>

      </head>

      <body>
         <div class="container">
            <div class="d-flex justify-content-center align-items-center" style="height: 100vh;">
               <div class="col-md-6">
                  <div class="card text-center">
                     <div class="card-header">
                        <h2>Create account</h2>
                     </div>
                     <div class="card-body">
                        <form:form action="/register" method="post" modelAttribute="user">
                           <jsp:include page="form.jsp" />
                           <div class="form-group row mt-4">
                              <label for="g-recaptcha" class="col-sm-4 col-form-label"></label>
                              <div class="col-sm-6">
                                 <div class="g-recaptcha" data-sitekey="${siteKey}"></div>
                              </div>
                              <small class="text-danger">
                           </div>
                           <div class="button-group mt-4">
                              <button type="submit" class="btn btn-primary">Sign up</button>
                              <a href="/login" class="btn btn-secondary">Cancel</a>
                           </div>
                        </form:form>
                     </div>
                  </div>
               </div>
            </div>
         </div>
      </body>

      </html>