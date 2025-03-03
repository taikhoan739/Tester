/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhm.services;

import com.nhm.pojo.Category;
import com.nhm.pojo.JdbcUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class CategoryService {
    public List<Category> getCategories() throws SQLException{
         List<Category> categories = new ArrayList<>();
        try(Connection conn = JdbcUtils.getConn()){
            PreparedStatement stm = conn.prepareCall("SELECT * FROM category");
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                Category c = new Category(rs.getInt("id"),rs.getString("name"));
                categories.add(c);
            }
        }
        return categories;
        
    }
}
