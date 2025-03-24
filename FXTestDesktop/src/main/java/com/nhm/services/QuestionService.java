/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhm.services;

import com.nhm.pojo.Choice;
import com.nhm.pojo.JdbcUtils;
import com.nhm.pojo.Question;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


/**
 *
 * @author admin
 */
public class QuestionService {
    
    public List<Question> getQuestions(int num, String kw) throws SQLException {
    List<Question> questions = new ArrayList<>();
    try (Connection conn = JdbcUtils.getConn()) {
        PreparedStatement stm;
        String sql;

      
        if (num == 0) {
            if (kw == null || kw.isEmpty()) {
                sql = "SELECT * FROM question";
                stm = conn.prepareStatement(sql);
            } else {
                sql = "SELECT * FROM question WHERE content LIKE CONCAT('%', ?, '%')";
                stm = conn.prepareStatement(sql);
                stm.setString(1, kw); 
            }
        } else {
            sql = "SELECT * FROM question ORDER BY RAND() LIMIT ?";
            stm = conn.prepareStatement(sql);
            stm.setInt(1, num); 
        }
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            Question question = new Question(
                rs.getString("id"),
                rs.getString("content"),
                rs.getInt("category_id")
            );
            questions.add(question);
        }
    }
    return questions;
}

    public List<Choice> getChoices(String questionId) throws SQLException{
         List<Choice> choices = new ArrayList<>();
        try(Connection conn = JdbcUtils.getConn()){
            PreparedStatement stm = conn.prepareCall("SELECT * FROM choice WHERE question_id=?");
            stm.setString(1, questionId);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                Choice c = new Choice(rs.getString("id"),rs.getString("content"),rs.getBoolean("is_correct"),rs.getString("question_id"));
                choices.add(c);
            }
        }
        return choices; 
    }
    
    public void addQuestion(Question q, List<Choice> choices) throws SQLException{
        try(Connection conn = JdbcUtils.getConn()){
            String sql = "INSERT INTO question(id,content,category_id) VALUE(?,?,?)";
            PreparedStatement stm = conn.prepareCall(sql);
            stm.setString( 1,q.getId());
            stm.setString(2,q.getContent());
            stm.setInt(3, q.getCategoryId());
            int r = stm.executeUpdate();
            if(r>0){
                for(var c:choices){
                sql = "INSERT INTO choice(id,content,is_correct,question_id) VALUE(?,?,?,?)";
                stm = conn.prepareCall(sql);
                stm.setString(1, c.getId());
                stm.setString(2,c.getContent());
                stm.setBoolean(3, c.getIsCorrect());
                stm.setString(4,q.getId());
                stm.executeUpdate();
             }
            }
            
        }
    }
}
