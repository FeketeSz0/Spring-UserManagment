<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
   <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
      <!DOCTYPE html>
      <html lang="en">


      <head>
         <meta charset="UTF-8">
         <title>Edit User</title>
         <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
            integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
         <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
         <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
      </head>


      <body>
         <div class="form-group row">
            <label for="login" class="col-sm-4 col-form-label">Username:</label>
            <div class="col-sm-6">
               <form:input type="text" class="form-control" id="login" name="login" path="login"
                  readonly="${user.isEditMode}" placeholder="Enter username" required="true" />
               <form:errors path="login" cssClass="text-danger" />
            </div>
         </div>
         <div class="form-group row">
            <label for="password" class="col-sm-4 col-form-label">Password:</label>
            <div class="col-sm-6">
               <form:input type="password" class="form-control" id="password" path="password"
                  placeholder="Enter password" />
               <form:errors path="password" cssClass="text-danger" />
            </div>
         </div>
         <div class="form-group row">
            <label for="confirmPassword" class="col-sm-4 col-form-label">Confirm Password:</label>
            <div class="col-sm-6">
               <form:input type="password" class="form-control" id="confirmPassword" path="confirmPassword"
                  placeholder="Confirm password" />
               <form:errors path="confirmPassword" cssClass="text-danger" />
            </div>
         </div>
         <div class="form-group row">
            <label for="email" class="col-sm-4 col-form-label">Email:</label>
            <div class="col-sm-6">
               <form:input type="email" class="form-control" id="email" path="email" placeholder="Enter email"
                  required="true" />
               <form:errors path="email" cssClass="text-danger" />
            </div>
         </div>
         <div class="form-group row">
            <label for="firstName" class="col-sm-4 col-form-label">First Name:</label>
            <div class="col-sm-6">
               <form:input type="text" class="form-control" id="firstName" path="firstName"
                  placeholder="Enter first name" required="true" />
               <form:errors path="firstName" cssClass="text-danger" />
            </div>
         </div>
         <div class="form-group row">
            <label for="lastName" class="col-sm-4 col-form-label">Last Name:</label>
            <div class="col-sm-6">
               <form:input type="text" class="form-control" id="lastName" path="lastName" placeholder="Enter last name"
                  required="true" />
               <form:errors path="lastName" cssClass="text-danger" />
            </div>
         </div>
         <div class="form-group row">
            <label for="birthday" class="col-sm-4 col-form-label">Birthday:</label>
            <div class="col-sm-6">
               <form:input type="date" class="form-control" id="birthday" path="birthday" required="true" />
               <form:errors path="birthday" cssClass="text-danger" />
            </div>
         </div>
         <form:input type="hidden" name="isEditMode" path="isEditMode" />
      </body>


      </html>