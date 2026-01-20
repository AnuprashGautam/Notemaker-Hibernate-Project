<!doctype html>
<%@page import="com.entities.Note"%>
<%@page import="org.hibernate.Session"%>
<%@page import="com.helper.FactoryProvider"%>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>NoteTaker: Home Page</title>
    <%@ include file="all_css_js.jsp" %>
    
    <link rel="stylesheet" href="./css/style.css">
  </head>
  <body>
  	<%@ include file="navbar.jsp" %>
  	
  	<%
  		int noteId=Integer.parseInt(request.getParameter("note_id").trim());
  	
  		Session openSession=FactoryProvider.getFactory().openSession();
  		
  		Note note=openSession.get(Note.class, noteId);
  		
  	%>
  	
  	<div class="container my-4 ">		
		<div class="row">
		
		<div class="card col-md-6 offset-md-3 mt-5" id="update-card">
			<div class="card-header text-center bg-transparent border-0" ><h1>Update Note</h1></div>
			<div class="card-body">
		
				<form action="UpdateServlet" method="post" enctype="multipart/form-data">
				<input type="hidden" value="<%=noteId%>" name="noteId">
				
				  <div class="form-group">
				    <label for="title">Note Title<span class="text-danger">*</span></label>
				    <input 
				    name="title"
				    type="text" 
				    class="form-control" 
				    id="title"
				    value="<%= note.getTitle() %>"
				    placeholder="Enter the note title" required>
				    
				  </div>
				  
				 <div class="form-group">
				    <label for="image">Image</label>
				    <input type="file" class="form-control-file" id="image" name="image">
				  </div>
								  
				  
				  <div class="form-group">
				    <label for="content">Content<span class="text-danger">*</span></label>
				    <textarea 
				    name="content"
				    class="form-control" id="content" rows="3"required><%=note.getContent() %></textarea>
				  </div>
				  
				  <div class="container text-center">
				  	<button type="submit" class="btn btn-success">Save</button>
				  </div>
				</form>
			
			</div>
		</div>
		
		</div>
	</div>
	
	<%
		openSession.close();
	%>
  </body>
</html>