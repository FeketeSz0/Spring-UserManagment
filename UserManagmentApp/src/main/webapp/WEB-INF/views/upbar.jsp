<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

  <head>
    <meta charset="UTF-8">
    <title>Edit User</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
  </head>

  <body>
    <nav class="navbar navbar-expand-lg navbar-light bg-light d-flex justify-content-between align-item-center">
      <div class="left-section">
      </div>
      <div class="right-section">
        <ul class="navbar-nav">
          <li class="nav-item">
            <p class="nav-link fw-bold">${fullName}</p>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="/logout">(logout)</a>
          </li>
        </ul>

    </nav>
  </body>

  </html>