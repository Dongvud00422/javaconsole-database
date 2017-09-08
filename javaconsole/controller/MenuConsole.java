/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaconsole.controller;

import java.util.Scanner;

/**
 *
 * @author dong
 */
public class MenuConsole {

    public static void main(String[] args) {
        MenuConsole menu = new MenuConsole();
        menu.createMenu();

    }

    public void createMenu() {
        loopMenu:
        for (;;) {
            System.out.println("=========Student Manager========");
            System.out.println("1. Student list.");
            System.out.println("2. Add student.");
            System.out.println("3. Edit student.");
            System.out.println("4. Delete student.");
            System.out.println("5. Exit.");
            System.out.println("===========================");
            System.out.println("Please enter your choice: ");
            Scanner scan = new Scanner(System.in);
            // Yêu cầu người dùng nhập chuỗi ký tự, gán giá trị người dùng nhập vào
            // ra biến kiểu chuỗi strChoice.
            String strChoice = scan.nextLine();
            // Kiểm tra dữ liệu người dùng nhập vào có là số hay không ?
            // Trong trường hợp không phải là số thì in ra thông báo cho người dùng và bắt đầu lại
            int choice;
            try {
                // Ép kiểu của biến strChoice về int.
                choice = Integer.parseInt(strChoice);
            } catch (java.lang.NumberFormatException e) {
                // Lưu log lỗi ở đây.
                System.err.println("Please enter a number.");
                continue;
            }
            StudentController studentController = new StudentController();
            switch (choice) {
                case 1:
                    studentController.getList();
                    break;
                case 2:
                    studentController.addStudent();
                    break;
                case 3:
                    studentController.editStudent();
                    break;
                case 4:
                    studentController.deleteStudent();
                    break;
                case 5:
                    break loopMenu;
                default:
                    System.err.println("Please enter number from 1 to 5.");
                    break;
            }

        }
    }
}