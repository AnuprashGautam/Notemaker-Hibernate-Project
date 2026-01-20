<!doctype html>
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
  	
	<div class="container my-4 ">		
		<div class="row">
		
		<div class="card col-md-6 offset-md-3 mt-5" id="create-note-card">
			<div class="card-body">
				<div class="container text-center"><h1>Create Note</h1></div>
				<form action="SaveNoteServlet" method="post" enctype="multipart/form-data">
				  <div class="form-group">
				    <label for="title">Note Title<span class="text-danger">*</span></label>
				    <input 
				    name="title"
				    type="text" 
				    class="form-control" 
				    id="title"
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
				    class="form-control" id="content" rows="3" required></textarea>
				  </div>
				  
				  <div class="container text-center">
				  	<button type="submit" class="btn btn-primary">Add</button>
				  </div>
				</form>
			
			</div>
		</div>
		
		</div>
	</div>
  </body>
</html>