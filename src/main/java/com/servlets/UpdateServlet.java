package com.servlets;

import java.io.File;
import java.io.IOException;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.entities.Note;
import com.helper.FactoryProvider;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

/**
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/UpdateServlet")
@MultipartConfig(
	    fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
	    maxFileSize = 1024 * 1024 * 10,      // 10 MB
	    maxRequestSize = 1024 * 1024 * 50    // 50 MB
	)
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public UpdateServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String updatedtitle=request.getParameter("title");
		String updatedContent=request.getParameter("content");
		int noteId=Integer.parseInt(request.getParameter("noteId").trim());
		
		
		// Making the folder "upload" where the images will be stored.
		
				String uploadPath=getServletContext().getRealPath("")+File.separator+"uploads";
				File uploadDir=new File(uploadPath);
				if(!uploadDir.exists())
				{
					uploadDir.mkdir();
				}
				
				// Fetching and saving the image in upload folder
				String submittedFileName=null;
				
				try {
					Part filePart=request.getPart("image");
					submittedFileName=filePart.getSubmittedFileName();
					
					if (submittedFileName != null && !submittedFileName.isEmpty())
					{
						String saveFilePath= uploadPath+File.separator+submittedFileName;
						
						filePart.write(saveFilePath);
						
						System.out.println(saveFilePath);
					}
					
				} catch (Exception e) {
					System.out.println("Image not got saved!!"+e.getMessage());
				}
	
  	
  		Session openSession=FactoryProvider.getFactory().openSession();
 		
  		Note note=openSession.get(Note.class, noteId);
  		
 		Transaction tx=openSession.beginTransaction();
 		
 		note.setTitle(updatedtitle);
 		note.setContent(updatedContent);
 		note.setImage(submittedFileName);
 		
 		openSession.persist(note);
 		
 		tx.commit();
		openSession.close();
  		
  		response.sendRedirect("show_all_notes.jsp");
	}

}
