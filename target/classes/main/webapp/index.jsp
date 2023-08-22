<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="com.loginPage.bean.User"%>


<!DOCTYPE html>
<%

     response.setHeader("Cache-Control","no-store");

      if(session.getAttribute("status")==null || session.getAttribute("status")=="Logout")
        {
            response.sendRedirect("login.jsp");
        }

%>

<html>
<body>
        <h1>------ WELCOME TO MAIN DASHBOARD ------</h1>

       <form id= "form1" action="controller" method="post">

            <input type='hidden' name="serviceMethod" value="logout" >
            <button  class="button button1" type="submit">logout</button>

       </form>

       <form class="details">

            <h2>Details</h2>

            <% User usr = (User)request.getAttribute("details"); %>
            <% if( usr != null)  { %>
                    <p class="para">User Name : <%=usr.getUserName()%></p>
                    <p class="para">Email Id : <%=usr.getEmailId()%></p>
            <% } %>

       </form>

       <div>
       <img id="img1"src="opentextimg.png" alt="opentext image">
       <img id="img2"src="cloudimagenew.png" alt="cloud image">
       <img id="img3"src="Ultimate-Cloud-3.png" alt="cloud image">

       </div>
</body>
<style>

    body {

            background-color: #0303da;
            background-image: url("nwbackgrd.jpg");

    }

    .para{

            font-size: x-large;
            font-family: cursive;
            padding-top: 38px;

    }

    input{

            margin-top: 17px;

    }

    .button {

            border: none;
            color: #f8f2f2;
            padding: 8px 22px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 25px;
            margin: 4px 2px;
            cursor: pointer;
            border-radius: 17%;
            background-color: red;
            font-weight: bolder;
    }

    .details{

              display: flex;
              flex-direction: column;
              align-items: center;
              background-color: #f9f9f8;
              color: #e42020;
              top: 22%;
              position: absolute;
              border-radius: 9px;
              right: 5%;
              width: 470px;
              height: 460px;
    }


    #img1{

                  position: absolute;
                  left: 2%;
                  top: 5%;
                  padding: 13px;
                  background-color: white;
                  width : 300px;
                  height : 50px
    }

    #img2{

                top: 58%;
                position: absolute;
                top: 36%;
                top: 19%;
                border-radius: 104px;
                height: 247px;
                width: 322px;


    }

    #img3{

                    position: absolute;
                    width: 374px;
                    height: 230px;
                    top: 60%;
                    left: 1%;
                    border-radius: 8px;

    }

    .button1{

            position: absolute;
            background-color: #ff0505;
            top: 5%;
            right: 2%;

    }

    label{

            font-size: 30px;
    }

    h1 {

             color: #f9f9f8;
             text-align: center;
             position: absolute;
             top: 3%;
             left: 43%;

    }
    h2{

             font-family: cursive;
             color: azure;
             background-color: #181717;
             padding: 18px;
             padding-left: 125px;
             padding-right: 125px;
             border-radius: 3px;

    }
</html>
