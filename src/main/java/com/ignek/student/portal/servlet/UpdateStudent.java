package com.ignek.student.portal.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ignek.student.portal.bean.Student;
import com.ignek.student.portal.dao.StudentDao;

@WebServlet("/updateStudent")
public class UpdateStudent extends HttpServlet{
	
	protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
	
		int id=Integer.parseInt(httpServletRequest.getParameter("id"));
		String name=httpServletRequest.getParameter("name");
		String email=httpServletRequest.getParameter("email");
		String phoneNumber=httpServletRequest.getParameter("phoneNumber");		
		String education=httpServletRequest.getParameter("education");
		String birthDateString=httpServletRequest.getParameter("birthDate");

		Student student=new Student();
		student.setId(id);
		student.setName(name);
		student.setEmail(email);
		student.setPhoneNumber(phoneNumber);
		student.setEducation(education);
		student.setBirthDate(birthDateString);
		
		int status=StudentDao.updateStudent(student);
		System.out.println(status);
		if(status==1){
			String msg="Successfully Updated";
			httpServletRequest.setAttribute("msg",msg);
			httpServletRequest.getRequestDispatcher("success.jsp").forward(httpServletRequest, httpServletResponse);
		}
		else{
			httpServletResponse.sendRedirect("error.jsp");
		}
	}
}
