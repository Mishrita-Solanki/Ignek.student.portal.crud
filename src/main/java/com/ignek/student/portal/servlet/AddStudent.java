package com.ignek.student.portal.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ignek.student.portal.bean.Student;
import com.ignek.student.portal.dao.StudentDao;

@WebServlet("/addStudent")
public class AddStudent extends HttpServlet{

	protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
	    
		String name=httpServletRequest.getParameter("name");
		String email=httpServletRequest.getParameter("email");
		String phoneNumber=httpServletRequest.getParameter("phoneNumber");
		String birthDateString=httpServletRequest.getParameter("birthDate");
		String education=httpServletRequest.getParameter("education");
		
		Student student=new Student();
		student.setName(name);
		student.setEmail(email);
		student.setPhoneNumber(phoneNumber);
		student.setBirthDate(birthDateString);
		student.setEducation(education);
		
		int status=StudentDao.insert(student);
		
		if(status==1){
			String msg="Successfully Added";
			httpServletRequest.setAttribute("msg",msg);
			httpServletRequest.getRequestDispatcher("success.jsp").forward(httpServletRequest, httpServletResponse);
			httpServletResponse.sendRedirect("success.jsp");
		}
		else{
			httpServletResponse.sendRedirect("error.jsp");
		}
	}
}
