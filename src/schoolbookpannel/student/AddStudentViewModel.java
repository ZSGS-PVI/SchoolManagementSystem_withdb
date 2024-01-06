package schoolbookpannel.student;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

import schoolbookpannel.dto.Student;
import schoolbookpannel.repository.SchoolBookPannelRepository;

public class AddStudentViewModel {

	private AddStudent addStudent;

	public AddStudentViewModel(AddStudent addStudent) {
		this.addStudent = addStudent;
	}

// 1.add student details

	public void validate(Student student) {

		SchoolBookPannelRepository.getInstanse().insertStudent(student);
		this.addStudent.onSuccess();

	}

// 2.view student details

	public void viewUsers() {
		List<Student> students = SchoolBookPannelRepository.getInstanse().getStudents();

		if (students.isEmpty()) {
			System.out.println("No students found.");
		} else {
			System.out.println("List of students:");
			for (Student student : students) {

				this.addStudent.studentlist(student);

			}
		}
	}

//3.Update the student details
	public void update(int idToUpdate) {

		List<Student> students = SchoolBookPannelRepository.getInstanse().getStudents();

		boolean found = false;
		for (Student student : students) {

			if (student.getRollNo() == (idToUpdate)) {

				found = true;

				Scanner in = new Scanner(System.in);

				System.out.println(" 1.Name\n 2.DOB \n 3.Mobileno\n 4.Email\n 5.Back");
				int choice = in.nextInt();
				in.nextLine();

				switch (choice) {

				case 1:
					System.out.println("Enter  new name: ");
					String newName = in.nextLine();
					student.setName(newName);
					SchoolBookPannelRepository.getInstanse().updateStudent(student);
					break;

				case 2:
					System.out.println("Enter   new DOB: ");
					String newDOB = in.nextLine();
					student.setDOB(newDOB);
					SchoolBookPannelRepository.getInstanse().updateStudent(student);
					break;

				case 3:
					System.out.println("Enter  new MobileNo: ");
					String newMobileNo = in.nextLine();
					student.setMobileno(newMobileNo);
					SchoolBookPannelRepository.getInstanse().updateStudent(student);
					break;
				case 4:
					System.out.println("Enter new email: ");
					String newEmail = in.nextLine();
					SchoolBookPannelRepository.getInstanse().updateStudent(student);
					student.setEmail(newEmail);
					
					break;

				default:
					System.out.println("Invalid choice.");
					break;

				}
            
			}
		}

		if (!found) {
			System.out.println("Student not found with ID: " + idToUpdate);
		}
	}

// 4.Delete the student Details
	public void delete(int idTODelete) {
		try {
			SchoolBookPannelRepository.getInstanse().deleteStudent(idTODelete);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		boolean found = false;
//
//		Iterator<Student> iterator = students.iterator();
//		while (iterator.hasNext()) {
//			Student student = iterator.next();
//			if (student.getRollNo() == idTODelete) {
//				iterator.remove();
//				found = true;
//				System.out.println("Student deleted successfully");
//				break;
//			}
//		}
//
//		if (!found) {
//			System.out.println("Student not found.");
//		}
//
//		SchoolBookPannelRepository.getInstanse().setStudents(students);
	}
	


	// student name validation
	public void isValidName(String name) {

		if (name.matches("^[a-zA-Z]+$")) {
		}

		else {
			System.err.println("only characters are allowed!");
			addStudent.addStudentInfo();
		}

	}

	// student DOB validation
	public void isValidDateOfBirth(String dateStr) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);

		try {
			Date date = dateFormat.parse(dateStr);

			Date currentDate = new Date();

			if (!date.after(currentDate) && date.before(currentDate)) {

			}
		} catch (ParseException e) {
			// ParseException will be thrown if the date format is invalid
			System.err.println("Invalid date of birth!");
			addStudent.addStudentInfo();

		}
	}

	// student Mobile number validation

	public void isValidMobileNo(String number) {

		if (number != null && !number.isEmpty()) {

			if (number.matches("^[0-9]{10}$")) {

			} else {
				System.err.println("Invalid mobile number! Only 10 digits are allowed.");
				addStudent.addStudentInfo();
			}
		} else {
			System.err.println("Mobile number cannot be empty or null!");
			addStudent.addStudentInfo();
		}
	}

	// student Email validation
	public void isValidEmail(String name) {

		if (name != null && !name.isEmpty()) {

			if (name.matches(
					"^[A-Za-z0-9-\\+]+(\\.[A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {

			}

			else {
				System.err.println("Invalid Email Id!");
				addStudent.addStudentInfo();
			}

		} else {
			System.err.println("Email Id cannot be empty or null!");
			addStudent.addStudentInfo();
		}

	}

}
