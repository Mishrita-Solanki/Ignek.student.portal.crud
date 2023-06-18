package com.ignek.student.portal.dao;
import java.sql.*;

import java.util.ArrayList;
import java.util.List;
import com.ignek.student.portal.bean.Student;

public class StudentDao {
	
	//get connection
	public static Connection getConnection(){  
	    Connection con=null;  
	    try{  
	        Class.forName("com.mysql.cj.jdbc.Driver");  
	        con=DriverManager.getConnection("jdbc:mysql://localhost:3306/studentdb","root","root");  
	    }
	    catch(Exception e){System.out.println(e);}  
	    return con;  
	}
	
	//insert
	public static int insert(Student s)
	{
		int status=0;
		try
		{
			Connection con=getConnection();
			PreparedStatement pst=con.prepareStatement("insert into student(name,email,phone_number,birth_date,education) values(?,?,?,?,?)");		
			pst.setString(1, s.getName());
			pst.setString(2, s.getEmail());
			pst.setString(3, s.getPhoneNumber());
			String dobString=s.getBirthDate();
			//date
			java.sql.Date birthDate = java.sql.Date.valueOf(dobString);
			pst.setDate(4, birthDate);
			pst.setString(5, s.getEducation());
			
			status=pst.executeUpdate();
			if(status==1)
			{
				System.out.println("Inserted");
			}
			pst.close();
			con.close();
			return status;
			
		}
		catch (Exception e) {System.out.println(e);}
		return 0;
	}

	//delete
	public static int delete(int id)
	{
		int status=0;
		try
		{
			Connection con=getConnection();
			PreparedStatement pst=con.prepareStatement("delete from student where id=?");
			pst.setInt(1,id);
			
			status=pst.executeUpdate();
			if(status==1)
			{
				System.out.println("Deleted");
			}
			return status;	
		}
		catch (Exception e) {System.out.println(e);}
		return 0;	
	}
	
	//view
	public static List<Student> viewStudents()
	{
		 List<Student> students = new ArrayList<>();
		 try
		 {
			 Connection con=getConnection();
			 PreparedStatement pst=con.prepareStatement("select*from student");
			 ResultSet rs=pst.executeQuery();
			 
			 while(rs.next())
			 {
				 Student s=new Student();
				 s.setId(rs.getInt("id"));
				 s.setEmail(rs.getString("email"));
				 s.setName(rs.getString("name"));
				 s.setPhoneNumber(rs.getString("phone_number"));
				 //date
				 java.sql.Date birthDate = rs.getDate("birth_date");
				 String birthDateString = (birthDate != null) ? birthDate.toString() : null;
				 s.setBirthDate(birthDateString);
				 s.setEducation(rs.getString("education"));
				 students.add(s);
				 
			 }
			 rs.close();
				pst.close();
				con.close();
			 return students;
		 }
		 catch (Exception e) {System.out.println(e);}
		return null;
	}
	
	//getstudent
	public static Student getStudent(int id)
	{
		Student student=new Student();
		try {
			Connection con=getConnection();
			PreparedStatement pst=con.prepareStatement("select*from student where id=?");
			
			pst.setInt(1,id);
			ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
				student.setId(rs.getInt("id"));
				student.setName(rs.getString("name"));
				student.setPhoneNumber(rs.getString("phone_number"));
				student.setEducation(rs.getString("education"));
				student.setEmail(rs.getString("email"));
				//date
				java.sql.Date birthDate = rs.getDate("birth_date");
				String birthDateString = (birthDate != null) ? birthDate.toString() : null;
				student.setBirthDate(birthDateString);
			}
			return student;
			
		} catch (SQLException e) {e.printStackTrace();}
		return null;
	}
	
	//update
	public static int updateStudent(Student student)
	{
		int status=0;
		try {
			Connection con=getConnection();
			PreparedStatement pst=con.prepareStatement("update student set name=?,email=?,phone_number=?,education=?,birth_date=? where id=?");
			pst.setString(1, student.getName());
			pst.setString(2, student.getEmail());
			pst.setString(3, student.getPhoneNumber());
			pst.setString(4, student.getEducation());
			//date
			String birthDateString=student.getBirthDate();
			java.sql.Date birthDate = java.sql.Date.valueOf(birthDateString);
			pst.setDate(5, birthDate);
			pst.setInt(6, student.getId());
			status=pst.executeUpdate();
			return status;
		}
		catch (Exception e) {System.out.println(e);}
		return 0;
	}
}
