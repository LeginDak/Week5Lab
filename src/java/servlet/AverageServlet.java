/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Legin
 */
public class AverageServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                
        //gets session object from request object
        HttpSession session = request.getSession();
        
        //reset button
        String action = request.getParameter("action");
        //if action is not null and equals reset  it will invalidate session
        if(action != null && action.equals("reset")) {
            session.invalidate();
            // make new session so it does not error
            session = request.getSession();
        }
        
        //store list of numbers in session
        ArrayList<Integer> numbers = (ArrayList <Integer>)session.getAttribute("numbers");
        //for first session when array list is null
        if (numbers == null) 
            numbers = new ArrayList();
        
        // add to list of numbers
        
        if (request.getParameter("number") != null){
            int number = Integer.parseInt(request.getParameter("number"));
            numbers.add(number);
            //update session to include new numbers
            session.setAttribute("numbers", numbers);
        
        numbers.add(number);
        }
        
        // calculate average 
        double average = 0.0;
        for(int number : numbers) {
            average += number;
        }
        if (numbers.size() > 0){
        average /= numbers.size();
        }
        
        request.setAttribute("average", average);
        
        
        //Display JSP
        getServletContext().getRequestDispatcher("/WEB-INF/average.jsp").forward(request, response);

    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
// nothing here
    }



}
