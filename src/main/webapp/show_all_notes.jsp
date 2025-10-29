<!doctype html>
<%@page import="org.hibernate.internal.build.AllowSysOut"%>
<%@page import="java.io.File"%>
<%@page import="com.entities.Note"%>
<%@page import="java.util.List"%>
<%@page import="com.helper.FactoryProvider"%>
<%@page import="org.hibernate.query.Query"%>
<%@page import="org.hibernate.Session"%>

<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>NoteTaker: Show Notes</title>
    <%@ include file="all_css_js.jsp" %>
    
    <link rel="stylesheet" href="css/style.css">
  </head>
  <body>
  	<%@ include file="navbar.jsp" %>
  	
	<div class="container my-4 ">	
		<div class="container text-center"><h1 >All Notes</h1></div>
		
		<div class="row">
			
			
		<%
			Session s=FactoryProvider.getFactory().openSession();
			Query q=s.createQuery("from Note");
			List<Note> notes=q.list();
			
			for(Note note: notes)
			{
		%>
		
			<div class="col-md-6">
				<div class="card mt-2 text-center">
				  <img src="C:/Users/anupr/eclipse-workspace-enterprise-edition/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/NoteTaker/uploads/default.png" class="card-img-top" alt="Card Image">
				  <div class="card-body">
				    <h5 class="card-title"><%= note.getTitle() %></h5>
				    <h6 class="text-muted"><%= note.getAddedDate() %></h6>
				    <p class="card-text"><%= note.getContent() %></p>
				    <div class="container text-center">
				    	<a href="edit.jsp?note_id=<%= note.getId() %>" class="btn btn-primary">Update</a>
				    	<a href="DeleteServlet?note_id=<%= note.getId() %>" class="btn btn-danger">Delete</a>
				    </div>
				  </div>
				</div>
			</div>
			
		<%
			}
			s.close();
		%>
		</div>
	</div>
  </body>
</html>