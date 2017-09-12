/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaconsole.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javaconsole.entity.Student;

/**
 *
 * @author dongvu
 */
public class StudentModel {

    //pagination???
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

    public Student getById(int id) {
        try {
            PreparedStatement preStt = DAO.getConnection().prepareStatement("SELECT * FROM Student WHERE id=?;");
            preStt.setInt(1, id);
            ResultSet rs = preStt.executeQuery();
            if (rs.next()) {
                Student student = new Student();
                student.setName(rs.getString("name"));
                student.setEmail(rs.getString("email"));
                student.setClassName(rs.getString("class_name"));
                student.setRollNumber(rs.getString("roll_number"));
                student.setStatus(rs.getInt("status"));
                return student;
            }
        } catch (SQLException ex) {
            System.err.println("Loi trong qua trinh getById " + ex.getMessage());
        }
        return null;
    }

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

    public void insert2(Student student) {
        try {
            PreparedStatement preStt = DAO.getConnection().prepareStatement("INSERT INTO Student VALUES (?,?,?,?,?)");
            preStt.setString(1, student.getName());
            preStt.setString(2, student.getEmail());
            preStt.setString(3, student.getClassName());
            preStt.setString(4, student.getRollNumber());
            preStt.setInt(5, student.getStatus());

            preStt.execute();
            System.out.println("Insert thành công với prepareStatement");
        } catch (SQLException ex) {
            System.err.println("Loi trong qua trinh insert " + ex.getMessage());
        }
    }

    public void delete(Student student) {
        try {
            Statement stt = DAO.getConnection().createStatement();
            StringBuilder sqlStringBuilder = new StringBuilder();
            sqlStringBuilder.append("DELETE FROM Student WHERE id = ");
            sqlStringBuilder.append(student.getId());
            sqlStringBuilder.append(";");
            stt.execute(sqlStringBuilder.toString());

        } catch (SQLException ex) {
            System.err.println("Loi trong qua trinh xoa sinh vien " + ex.getMessage());
        }

    }

    public void update(Student newStudent) {
        try {
            Statement stt = DAO.getConnection().createStatement();
            StringBuilder sqlStringBuilder = new StringBuilder();
            sqlStringBuilder.append("UPDATE Student SET name='");
            sqlStringBuilder.append(newStudent.getName());
            sqlStringBuilder.append("', email='");
            sqlStringBuilder.append(newStudent.getEmail());
            sqlStringBuilder.append("', class_name='");
            sqlStringBuilder.append(newStudent.getClassName());
            sqlStringBuilder.append("', roll_number='");
            sqlStringBuilder.append(newStudent.getRollNumber());
            sqlStringBuilder.append("', status=");
            sqlStringBuilder.append(newStudent.getStatus());
            sqlStringBuilder.append(" WHERE id=");
            sqlStringBuilder.append(newStudent.getId());
            sqlStringBuilder.append(";");
            stt.execute(sqlStringBuilder.toString());
        } catch (SQLException ex) {
            System.err.println("Loi trong qua trinh update sinh vien " + ex.getMessage());
        }

    }

    public void updatePrepareStatement(Student newStudent) {
        try {
            PreparedStatement PreStt = DAO.getConnection().prepareStatement("UPDATE Student SET name=?, email=?, class_name=?, roll_number=?, status=? WHERE id=?;");
            PreStt.execute();
        } catch (SQLException ex) {
            System.err.println("Loi trong qua trinh update sinh vien " + ex.getMessage());
        }

    }

}
