package schoolbookpannel;

import java.util.Scanner;

import schoolbookpannel.student.AddStudent;

import schoolbookpannel.student.AddStudentViewModel;

public class SchoolBookPannel {
	private static SchoolBookPannel schoolbookpannel;

	public static void main(String[] args) {

		schoolbookpannel = new SchoolBookPannel();
		schoolbookpannel.init();

	}

	private void init() {
		System.out.println();
		System.out.println("--------WELCOME TO SCHOOL MANAGEMENT SYSTEM--------");
		System.out.println(" 1.Admin\n 2.Student \n 3.exit");
		Scanner in = new Scanner(System.in);
		int choice = in.nextInt();
		switch (choice) {
		case 1:
			adminfun();
			break;
		case 2:
			userfun();
			break;
		case 3:
			System.out.println("Goodbye Have a nice day!");
			break;
		}
	}

	private void adminfun() {

		boolean running = true;

		while (running) {

			System.out.println(" 1.AddStudent\n 2.ViewStudent \n 3.UpdateStudent \n 4.DeleteStudent \n 5.exit");
			Scanner in = new Scanner(System.in);
			AddStudent student1 = new AddStudent();

			int choice = in.nextInt();
			switch (choice) {
			case 1:
				student1.addStudentInfo();
				break;
			case 2:
				student1.viewStudent ();
				break;

			case 3:
				student1.updateStudent();
				break;

			case 4:
				student1.deleteStudent();
				break;

			case 5:
				System.out.println("back to admin and Student!");
				schoolbookpannel.init();
				running = false;
				break;

			default:
				System.out.println("Invalid choice.");
				break;

			}
		}
	}

	private void userfun() {
		AddStudent student1 = new AddStudent();
		System.out.println("Student only Read the file:");
		student1.viewStudent();
		schoolbookpannel.init();

	}
}
