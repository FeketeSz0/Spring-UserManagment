<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
        <%@ taglib prefix="userTag" uri="jdbc.utils.UserTag" %>

            <head>
                <title>User List</title>
                <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
                    integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
                    crossorigin="anonymous" />
            </head>

            <body>
                <div class="container">
                    <jsp:include page="upbar.jsp" />
                    <div class="nav-item mt-5">
                        <h1>User editor</h1>
                        <a href="/adduser" class="btn btn-success">Add User</a>
                    </div>
                    <table class="table">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Login</th>
                                <th>Firstname</th>
                                <th>Lastname</th>
                                <th>Email</th>
                                <th>Age</th>
                                <th>Role</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:set var="users" value="${users}" />
                            <c:forEach var="user" items="${users}">
                                <userTag:user user="${user}" />
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </body>

            <script>
                function confirmDelete(userId) {
                    if (confirm("Are you sure you want to delete this user?")) {

                        $.ajax({
                            url: '/table/' + userId,
                            type: 'DELETE',
                            success: function () {
                                location.reload();
                            },
                            error: function () {
                                alert('Failed to delete user.');
                            }
                        });
                    }
                }
            </script>

            <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

            </html>