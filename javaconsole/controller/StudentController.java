/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaconsole.controller;

import java.util.Scanner;
import javaconsole.entity.Student;
import javaconsole.model.StudentModel;

/**
 *
 * @author dong
 */
public class StudentController {

    // Get list.
    // Add.
    // Edit.
    // Delete.
    private StudentModel studentModel = new StudentModel();
    Scanner scan = new Scanner(System.in);

    // Hàm trả về danh sách sinh viên.
    public void getList() {
        System.out.println("\n");
        if (studentModel.getListStudent() == null || studentModel.getListStudent().isEmpty()) {
            System.err.println("List student is empty !!");
        } else {
            System.out.println("======== Student list ========");
            for (Student getStudent : studentModel.getListStudent()) {
                System.out.println("Id: " + getStudent.getId() + "\n"
                        + "Name: " + getStudent.getName() + "\n"
                        + "Email: " + getStudent.getEmail() + "\n"
                        + "--------------");
            }
            System.out.println("\n");
        }
    }

    // Thêm mới sinh viên, thực hiện việc lấy dữ liệu từ người dùng,
    // vaildate dữ liệu đó, tiến hành lưu thông tin.
    public void addStudent() {
        System.out.println("Please enter student infomation.");
        System.out.println("Please enter name: ");
        String name = scan.nextLine();
        System.out.println("Please enter email: ");
        String email = scan.nextLine();
        System.out.println("Please enter class name: ");
        String className = scan.nextLine();
        System.out.println("Please enter Roll Number: ");
        String rollNumber = scan.nextLine();
        System.out.println("Please enter status: ");
        int status = scan.nextInt();

        // Vaildate dữ liệu ở đây.
        Student student = new Student();
        student.setName(name);
        student.setEmail(email);
        student.setClassName(className);
        student.setRollNumber(rollNumber);
        student.setStatus(status);
        student.setId(12);
        // Lưu thông tin sinh viên vào db.
        studentModel.insert(student);
    }

    // Tìm kiếm sinh viên, kiểm tra sinh viên đó có tồn tại không.
    // sửa dữ liệu & tiến hành cập nhật.
    public void editStudent() {
        if (studentModel.getListStudent() == null || studentModel.getListStudent().isEmpty()) {
            System.err.println("List student is empty !!");
        } else {
            Student oldStudent = studentSearching();
            if (oldStudent != null) {
                // Sửa thông tin sinh viên.
                System.out.println("Editting......");
                System.out.println("Please enter new name (press Enter for skip): ");
                String newName = scan.nextLine();
                if (newName.isEmpty()) {
                    newName = oldStudent.getName();
                }
                System.out.println("Please enter new email (press Enter for skip): ");
                String newEmail = scan.nextLine();
                if (newEmail.isEmpty()) {
                    newEmail = oldStudent.getEmail();
                }
                System.out.println("Please enter new class name (press Enter for skip): ");
                String newClassName = scan.nextLine();
                if (newClassName.isEmpty()) {
                    newClassName = oldStudent.getClassName();
                }
                System.out.println("Please enter roll number (press Enter for skip): ");
                String newRollNumber = scan.nextLine();
                if (newRollNumber.isEmpty()) {
                    newRollNumber = oldStudent.getRollNumber();
                }

                Student newStudent = new Student();
                newStudent.setName(newName);
                newStudent.setEmail(newEmail);
                newStudent.setId(oldStudent.getId());
                newStudent.setClassName(newClassName);
                newStudent.setRollNumber(newRollNumber);

                studentModel.update(newStudent);
            } else {
                System.err.println("Student not found !!!");
            }
        }

    }

    public void deleteStudent() {
        if (studentModel.getListStudent() == null || studentModel.getListStudent().isEmpty()) {
            System.err.println("List student is empty !!");
        } else {
            Student result = studentSearching();
            if (result != null) {
                for (;;) {
                    System.out.print("Press [Y]es for delete or [N]o for cancel: ");
                    String lastCheck = scan.nextLine();
                    if (lastCheck.equalsIgnoreCase("y")) {
                        studentModel.delete(result);
                        break;
                    } else if (lastCheck.equalsIgnoreCase("n")) {
                        System.out.println("Canceled !!!");
                        break;
                    }
                    System.err.println("You must press Y or N. Please try again !!!");
                }
            } else {
                System.err.println("Student not found !!!");
            }

        }
    }

    private Student studentSearching() {
        Student result = null;
        System.out.println("Student searching......");
        System.out.println("Please enter Student's id, name or email :");
        String searchingKey = scan.nextLine();

        // Check dữ liệu người dùng nhập vào có trong db hay không.
        try {
            for (Student student : studentModel.getListStudent()) {
                if (student.getName().equalsIgnoreCase(searchingKey)) {
                    System.out.println("--------------" + "\n"
                            + "Student found: \n"
                            + "Id: " + student.getId() + "\n"
                            + "Name: " + student.getName() + "\n"
                            + "Email: " + student.getEmail() + "\n"
                            + "--------------");
                    result = student;
                    break;
                } else if (student.getEmail().equalsIgnoreCase(searchingKey)) {
                    System.out.println("--------------" + "\n"
                            + "Student found: \n"
                            + "Id: " + student.getId() + "\n"
                            + "Name: " + student.getName() + "\n"
                            + "Email: " + student.getEmail() + "\n"
                            + "--------------");
                    result = student;
                    break;
                } else if (student.getId() == Long.parseLong(searchingKey)) {
                    System.out.println("--------------" + "\n"
                            + "Student found: \n"
                            + "Id: " + student.getId() + "\n"
                            + "Name: " + student.getName() + "\n"
                            + "Email: " + student.getEmail() + "\n"
                            + "--------------");
                    result = student;
                    break;
                }
            }
        } catch (java.lang.NumberFormatException e) {
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public void exportToFile() {
        if (studentModel.getListStudent() == null) {
            System.out.println("List student is empty, nothing for export !!!");
        } else {
             
        }
    }

    public void importFromFile() {

    }
}
