<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>NoteTaker: Home Page</title>
    <%@ include file="all_css_js.jsp" %>
    
    <link rel="stylesheet" href="css/style.css">
  </head>
  <body>
  	<%@ include file="navbar.jsp" %>
  	
	<div class="container my-4 d-flex flex-column align-items-center justify-content-center" style="height:80vh; background:linear-gradient(to top right, pink, skyblue);" >		
		<h1 class="display-3">Welcome to <span class="text-warning">Notemaker</span>!!!</h1>
		<p>This is an amazing website that I made for the learning purpose doing the CRUD operation using Hibernate.</p>
		<a class="btn btn-outline-primary" href="note.jsp">Explore</a>
	</div>
  </body>
</html>