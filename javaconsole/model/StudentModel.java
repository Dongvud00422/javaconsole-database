/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaconsole.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaconsole.entity.Student;

/**
 *
 * @author dongvu
 */
public class StudentModel {

    public void insert(Student student) {
        try {
            Statement stt = DAO.getConnection().createStatement();
            StringBuffer sqlStringBuffer = new StringBuffer();
            sqlStringBuffer.append("INSERT INTO Student VALUE (");
            sqlStringBuffer.append(student.getId());
            sqlStringBuffer.append(",'");
            sqlStringBuffer.append(student.getName());
            sqlStringBuffer.append("','");
            sqlStringBuffer.append(student.getEmail());
            sqlStringBuffer.append("','");
            sqlStringBuffer.append(student.getClassName());
            sqlStringBuffer.append("','");
            sqlStringBuffer.append(student.getRollNumber());
            sqlStringBuffer.append("',");
            sqlStringBuffer.append(student.getStatus());
            sqlStringBuffer.append(");");
            stt.execute(sqlStringBuffer.toString());
        } catch (SQLException ex) {
            System.err.println("Loi trong qua trinh insert " + ex.getMessage());
        }
    }

    public ArrayList<Student> getListStudent() {
        ArrayList<Student> listStudent = new ArrayList<>();
        try {
            Statement stt = DAO.getConnection().createStatement();
            ResultSet rs = stt.executeQuery("SELECT * FROM Student;");
            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setEmail(rs.getString("email"));
                student.setClassName(rs.getString("class_name"));
                student.setRollNumber(rs.getString("roll_number"));
                student.setId(rs.getInt("id"));

                listStudent.add(student);
            }
        } catch (SQLException ex) {
            System.err.println("Loi trong qua trinh getList " + ex.getMessage());
        }
        return listStudent;
    }

    public void delete(Student student) {
        try {
            Statement stt = DAO.getConnection().createStatement();
            StringBuffer sqlStringBuffer = new StringBuffer();
            sqlStringBuffer.append("DELETE FROM Student WHERE id = ");
            sqlStringBuffer.append(student.getId());
            sqlStringBuffer.append(";");
            stt.execute(sqlStringBuffer.toString());

        } catch (SQLException ex) {
            System.err.println("Loi trong qua trinh xoa sinh vien " + ex.getMessage());
        }

    }

    public void update(Student newStudent) {
        try {
            Statement stt = DAO.getConnection().createStatement();
            StringBuffer sqlStringBuffer = new StringBuffer();
            sqlStringBuffer.append("UPDATE Student SET name='");
            sqlStringBuffer.append(newStudent.getName());
            sqlStringBuffer.append("', email='");
            sqlStringBuffer.append(newStudent.getEmail());
            sqlStringBuffer.append("', class_name='");
            sqlStringBuffer.append(newStudent.getClassName());
            sqlStringBuffer.append("', roll_number='");
            sqlStringBuffer.append(newStudent.getRollNumber());
            sqlStringBuffer.append("', status=");
            sqlStringBuffer.append(newStudent.getStatus());
            sqlStringBuffer.append(" WHERE id=");
            sqlStringBuffer.append(newStudent.getId());
            sqlStringBuffer.append(";");
            stt.execute(sqlStringBuffer.toString());
        } catch (SQLException ex) {
            System.err.println("Loi trong qua trinh update sinh vien " + ex.getMessage());
        }

    }

}
