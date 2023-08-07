<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <title>Login Page</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>

<body>

  <div class="d-flex justify-content-center align-items-center" style="height: 100vh;">
    <div class="card text-center">
      <div class="card-header">
        <h2>Login</h2>

        <c:if test="${param.error == 'true'}">
            <p  class="alert alert-error"> Invalid username and password.</p>
        </c:if>
        <c:if test="${SessionScope.logoutSuccess}">
                    <p  class="alert alert-success"> You have been logged out.</p>
        </c:if>
         <c:if test="${sessionScope.register}">
               <p  class="alert alert-success"> Account is created.</p>
         </c:if>

      </div>
      <div class="card-body">

        <form action="login" method="post">
          <div class="form-group row">
            <label for="username">Username:</label>
            <input type="text" class="form-control" id="username" name="username"  placeholder="Enter Username"/>
          </div>
          <div class="form-group row">
            <label for="password">Password:</label>
            <input type="password" class="form-control" id="password" name="password" placeholder="Enter Password"/>
          </div>
          <button type="submit" class="btn btn-primary mt-4">Login</button>
        </form>
        <a href="/register">Dont have an account? SIGN UP</a>
      </div>
    </div>

  </div>
</body>

</html>