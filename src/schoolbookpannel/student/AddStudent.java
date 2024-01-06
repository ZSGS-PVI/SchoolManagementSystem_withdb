package schoolbookpannel.student;

import java.util.List;
import java.util.Scanner;

import schoolbookpannel.dto.Student;

public class AddStudent {
	// private static final String String = null;
	private AddStudentViewModel addStudentviewmodel;

	public AddStudent() {
		addStudentviewmodel = new AddStudentViewModel(this);
	}

	Scanner in = new Scanner(System.in);

	public void addStudentInfo() {
		Scanner in = new Scanner(System.in);
		int rollNo = 0;
		String name;
		String DOB;
		String mobileno;
		String email;

		while (true) {
			System.out.println("Enter the rollNo  (type '0' to stop adding names):");
			rollNo = in.nextInt();
			if (rollNo == 0) {
				break;
			}

			System.out.println("Enter  student name:");
			name = in.next();
			addStudentviewmodel.isValidName(name);

			System.out.println("Enter student DOB Like (\"yyyy-MM-dd\"): ");
			DOB = in.next();
			addStudentviewmodel.isValidDateOfBirth(DOB);
			System.out.println("Enter  student mobileno: ");
			mobileno = in.next();
			addStudentviewmodel.isValidMobileNo(mobileno);

			System.out.println("Enter student email  ");
			email = in.next();
			addStudentviewmodel.isValidEmail(email);

			Student student = new Student(rollNo, name, DOB, mobileno, email);
			addStudentviewmodel.validate(student);

		}
	}

	public void viewStudent() {
		addStudentviewmodel.viewUsers();
	}

	public void updateStudent() {

		System.out.println("Enter the Id to update:");
		int idTOUpdate = in.nextInt();
		addStudentviewmodel.update(idTOUpdate);
	}

	public void deleteStudent() {

		System.out.println("Enter the Id to delete:");
		int idTODelete = in.nextInt();

		addStudentviewmodel.delete(idTODelete);
	}

	public void onSuccess() {
		System.out.println("Inserted Successfully");

	}

	public void showError(String errorMessage) {
		System.out.println(errorMessage);
	}

	public void studentlist(Student student) {
		System.out.println("---------------------------");
		System.out.println("ID: " + student.getRollNo());
		System.out.println("Name: " + student.getName());
		System.out.println("DOB: " + student.getDOB());
		System.out.println("Mobile No: " + student.getMobileno());
		System.out.println("Email: " + student.getEmail());
		System.out.println("---------------------------");
	}

}
