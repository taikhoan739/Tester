/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhm.pojo;

/**
 *
 * @author admin
 */
public class Choice {
    private String id;
    private String content;
    private Boolean isCorrect;
    private String questionId;

    public Choice() {
    }

    public Choice(String id, String content, Boolean isCorrect, String questionId) {
        this.id = id;
        this.content = content;
        this.isCorrect = isCorrect;
        this.questionId = questionId;
    }

    @Override
    public String toString() {
        return this.content;
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

    public Boolean getIsCorrect() {
        return isCorrect;
    }

    public void setIsCorrect(Boolean isCorrect) {
        this.isCorrect = isCorrect;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }
    
}
