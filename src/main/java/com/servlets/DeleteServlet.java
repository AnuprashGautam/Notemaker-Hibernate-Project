package com.servlets;

import java.io.IOException;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.entities.Note;
import com.helper.FactoryProvider;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DeleteServlet
 */
@jakarta.servlet.annotation.WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int noteId=Integer.parseInt(request.getParameter("note_id").trim());
		
		try {
			Session openSession = FactoryProvider.getFactory().openSession();
			Transaction tx=openSession.beginTransaction();
			
			Note note=openSession.get(Note.class, noteId);
			openSession.remove(note);
			
			tx.commit();
			openSession.close();
			
			response.sendRedirect("show_all_notes.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	

}
