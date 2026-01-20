package com.servlets;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

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
 * Servlet implementation class SaveNoteServlet
 */
@WebServlet("/SaveNoteServlet")
@MultipartConfig(
	    fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
	    maxFileSize = 1024 * 1024 * 10,      // 10 MB
	    maxRequestSize = 1024 * 1024 * 50    // 50 MB
	)
public class SaveNoteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveNoteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title=null;
		String content=null;
		byte[] image=null;
		
		try {
			title = (String) request.getParameter("title");
			content = (String) request.getParameter("content");
						
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// Making the folder "uploads" where the images will be stored.
		
		String uploadPath=getServletContext().getRealPath("")+File.separator+"uploads";

		File uploadDir=new File(uploadPath);

		if(!uploadDir.exists())
		{
			uploadDir.mkdir();
		}
		
		// Fetching and saving the image in upload folder
		String submittedFileName=null;
		
		try {
			//
			Part filePart=request.getPart("image");

			// filePart.getSubmittedFileName() return ""(empty string) , not null if ther is not image provided.
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
		
		Note note = new Note();
		note.setTitle(title);
		note.setContent(content);
		note.setAddedDate(new Date());

		if(submittedFileName!=null && !submittedFileName.isEmpty())
		{
			note.setImage(submittedFileName);
		}else {
			note.setImage("default.svg");
		}
		
		Session openSession = FactoryProvider.getFactory().openSession();
		Transaction tx=openSession.beginTransaction();
		
		openSession.persist(note);
		
		tx.commit();
		openSession.close();		
		
		// Returning the result
		
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.println("<h1 style='align-item:center;'>Note added successfully!!!</h1>");
		writer.println("<h1 style='align-item:center;'><a href='show_all_notes.jsp'>View All Notes</a></h1>");
	}

}
