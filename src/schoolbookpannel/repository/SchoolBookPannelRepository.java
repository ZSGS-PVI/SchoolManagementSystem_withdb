package schoolbookpannel.repository;

import java.io.FileWriter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Stack;

import com.google.gson.Gson;

import schoolbookpannel.dto.Student;
import schoolbookpannel.student.AddStudent;

public class SchoolBookPannelRepository {

	private static SchoolBookPannelRepository repository;
	 private static final String url = "jdbc:mysql://localhost:3306/schoolManagementconsole";
	    private static final String username = "Kalpana";
	    private static final String password = "Dev@2277";
		private List<Student> studentList;

	

	private SchoolBookPannelRepository() {
		 try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }

	}

	public static SchoolBookPannelRepository getInstanse() {
		if (repository == null) {
			repository = new SchoolBookPannelRepository();
		}
		return repository;
	}

	// 1.add
	public void insertStudent(Student Student) {
		try(Connection connection=DriverManager.getConnection(url,username,password)){
			String insertQuery="INSERT INTO Students(rollno,name,DOB,mobileno,email) VALUES(?,?,?,?,?)";
			try(PreparedStatement preparedStatement=connection.prepareStatement(insertQuery)){
				preparedStatement.setInt(1,Student.getRollNo());
				preparedStatement.setString(2,Student.getName());
				preparedStatement.setString(3,Student.getDOB());
				preparedStatement.setString(4,Student.getMobileno());
				preparedStatement.setString(5,Student.getEmail());
				
				int rowsAffected=preparedStatement.executeUpdate();
				if(rowsAffected>0) {
					System.out.println("student inserted successfully");
				}
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		//studentList.add(Student);
	
		//json();
		
	}

	// 2.view
	public List<Student> getStudents() {
		 List<Student> studentList = new ArrayList<Student>();
		 try(Connection connection=DriverManager.getConnection(url,username,password)){
			 String selectQuery="SELECT * FROM Students";
			 try(Statement statement=connection.createStatement();
					 ResultSet resultSet=statement.executeQuery(selectQuery)){
				 while(resultSet.next()) {
					 Student student=new Student(
						 resultSet.getInt("rollNo"),
							 resultSet.getString("name"),
							 resultSet.getString("DOB"),
							 resultSet.getString("mobileno"),
							 resultSet.getString("email")
							 );
//					 System.out.println(student.getName());
//						System.out.println(student.getRollNo());
//						System.out.println(student.getDOB());
//						System.out.println(student.getMobileno());
//						System.out.println(student.getEmail());
					 studentList.add(student);
				 }
			 }
		 }
		 catch(SQLException e) {
			 e.printStackTrace();
		 }
		
		return studentList;
	}

	 public void updateStudent(Student student) {
		    try (Connection connection = DriverManager.getConnection(url, username, password)) {
		    	String updateQuery = "UPDATE Students SET name = ?, DOB = ?, mobileno = ?, email = ? WHERE rollNo = ?";
		    	try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
		    	    preparedStatement.setString(1, student.getName());
		    	    preparedStatement.setString(2, student.getDOB());
		    	    preparedStatement.setString(3, student.getMobileno());
		    	    preparedStatement.setString(4, student.getEmail());
		    	    preparedStatement.setInt(5, student.getRollNo());
		    	    
		    	    // Remaining code remains the same


	                int rowsAffected = preparedStatement.executeUpdate();
	                if (rowsAffected > 0) {
	                    System.out.println("Student updated successfully");
	                } else {
	                    System.out.println("No student found with Roll No: " + student.getRollNo());
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	 

		public void setStudents(List<Student> updatedStudents) {
			this.studentList = updatedStudents;

		}
		public void deleteStudent(int rollNo) throws SQLException {
			try(Connection connection=DriverManager.getConnection(url,username,password)){
				String deleteQuery="DELETE FROM Students WHERE rollNo=?";
				try(PreparedStatement preparedStatement=connection.prepareStatement(deleteQuery)){
					preparedStatement.setInt(1, rollNo);
					int rowsAffected=preparedStatement.executeUpdate();
					if(rowsAffected>0) {
						System.out.println("Student deleted successfully");
					}
					else {
						System.out.println("No student found with Roll No: "+rollNo);
					}
				}
			}
			catch (SQLException e) {
	            e.printStackTrace();
	        }
		}
}
//	public static void json() {
//	Gson gson=new Gson();
//	String json=gson.toJson(studentList);
//	
//	String filePath="/home/rahul/eclipse-workspace/schoolbook/src/schoolbookpannel/data.json";
//	
//	try (FileWriter writer = new FileWriter(filePath)) {
//        
//        writer.write(json);
//      
//    } catch (IOException e) {
//        e.printStackTrace();
//    }
//	}


