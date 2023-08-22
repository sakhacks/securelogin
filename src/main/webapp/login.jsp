<html>
<%

 response.setHeader("Cache-Control","no-store");

%>
<body>

        <h1>------ LOGIN PORTAL------</h1>
        <div class="formoptions">
       <form id="form1" action="controller" method="post">


           <input type='hidden' name="serviceMethod" value="login" >

           <label for="userId">Email ID</label>
           <input type="email" placeholder="Enter Email ID" name="userId" required><br>

           <label for="password">Password</label>
           <input type="password" placeholder="Enter Password" name="password" required><br>


           <button type="submit">Login</button><br>

       </form>

       <form id="form2" action="registration.jsp">
             <div>New user?..click to register</div><br>
             <button type="submit">Register</button>

       </form>
       </div>
       <% if (request.getParameter("success") != null) { %>
                           <% if (request.getParameter("success").equals("1")) { %>
                                  <p class="success" style="color: #00ff3c;">You have successfully registered...!</p>
                           <% } else { %>
                                  <p class="success" style="color: #ff6969;">User already exists...!</p>
                           <% } %>
       <% } %>

       <% if (request.getParameter("error") != null) { %>

             <% if (request.getParameter("error").equals("NotPresent")) { %>
                    <p class="error" >You have not registered..No such user found....</p>
             <% } %>
            <% if (request.getParameter("error").equals("blocked")) { %>
                   <p class="error" style="left:37%" >Your account is blocked for 24 hours.. Please try again later....</p>
            <% } else { %>

                    <% if (request.getParameter("attempts").equals("1")) { %>
                            <p style="top:17%;" id = "invalid" >You will be blocked after 2 more fail attempts....</p>
                            <p class="error" >Invalid username or password...</p>

                    <% } else if(request.getParameter("attempts").equals("2")){ %>
                            <p class="error" >Invalid username or password..Attempts left = 1</p>

                    <% } %>
            <% } %>

       <% } %>
</body>

<style>
body {
  background-color: #0303da;
}
.error{
        color: red;
        position: absolute;
        left: 39%;
        top: 20%;
}
.success{

          color: green;
          position: absolute;
          left: 654px;
          top: 19%;
          font-weight: bold;

}
input{
margin-top: 17px;
}

#form1{
        align-items: center;
        display: flex;
        flex-direction: column;
}
.formoptions{
          display: flex;
          flex-direction: column;
          align-items: center;
          background-color: #f9f9f8;
          color: black;
          top: 15%;
          position: absolute;
          padding: 100px;
          border-radius: 20px;
          left: 35%;
          width: 250px;


}

label{
font-size: 30px;
}

h1 {
  color: #f9f9f8;
  margin-left: -10px;
  text-align: center;
  position:relative;
  top:5%;
}
#invalid{

            color: red;
            position: absolute;
            left: 39%;
            top: 23%;
}
</html>
