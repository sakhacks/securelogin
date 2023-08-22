
<html>
<body>
        <h1>------ Registration Page------</h1>

       <form action="controller" method="post">

            <input type="hidden" name="serviceMethod" value="register">

           <label for="uname">User Name</label>
           <input type="text" placeholder="Enter User Name" name="uname" required><br>

           <label for="userId">Email ID</label>
           <input type="email" placeholder="Enter Email ID" name="userId" required><br>

           <label for="password">Password</label>
           <input type="password" placeholder="Enter Password" name="password" required><br>

           <label for="password">Repeat Password</label>
           <input type="password" placeholder="Repeat Password" name="Rpassword" required><br>

           <button type="submit">Register</button><br>


           <a href="index.jsp">Go Back to login page</a>

       </form>
</body>
<style>
body {
  background-color: #0303da;
}
input{
margin-top: 17px;
}
form {
         display: flex;
         flex-direction: column;
         align-items: center;
         background-color: #f9f9f8;
         color: black;
         top: 3%;
         position: relative;
         padding: 90px;
         border-radius: 20px;
         left: 34%;
         width: 250px;

}

label{
font-size: 30px;
}

h1 {
  color: #f9f9f8;
  margin-left: -50px;
  text-align: center;
  position:relative;
  top:5%;
}
</html>
