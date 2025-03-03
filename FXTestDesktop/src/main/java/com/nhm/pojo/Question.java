/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhm.pojo;

import java.util.List;

/**
 *
 * @author admin
 */
public class Question {
    private String id;
    private String content;
    private int categoryId;
    private List<Choice> choices;

    public List<Choice> getChoices() {
        return choices;
    }

    public void setChoices(List<Choice> choices) {
        this.choices = choices;
    }
    public Question(String id, String content, int categoryId) {
        this.id = id;
        this.content = content;
        this.categoryId = categoryId;
    }

    public Question() {
    }

    @Override
    public String toString() {
        return "Question{" + "content=" + content + '}';
    }
    
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int category_id) {
        this.categoryId = category_id;
    }
}
