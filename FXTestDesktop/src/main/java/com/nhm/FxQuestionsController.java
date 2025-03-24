/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.nhm;

import com.nhm.pojo.Category;
import com.nhm.pojo.Choice;
import com.nhm.pojo.Question;
import com.nhm.services.CategoryService;
import com.nhm.services.QuestionService;
import java.net.URL;
import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class FxQuestionsController implements Initializable {
    
    @FXML ComboBox categories;
    @FXML TableView<Question> questions;
    @FXML Button btnAddQuestion;
    @FXML TextField TitleQuestion;
    @FXML TextField txtSearch;
    @FXML RadioButton rdoA;
    @FXML RadioButton rdoB;
    @FXML RadioButton rdoC;
    @FXML RadioButton rdoD;
    @FXML TextField txtA;
    @FXML TextField txtB;
    @FXML TextField txtC;
    @FXML TextField txtD;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CategoryService c = new CategoryService();
        try {
            this.categories.setItems(FXCollections.observableList(c.getCategories()));
        } catch (SQLException ex) {
            Logger.getLogger(FxQuestionsController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        loadColumns();
         loadData(null);
         loadDataByCate(c);
         this.txtSearch.textProperty().addListener(e->{
             loadData(this.txtSearch.getText());
         });
        
    }   
    public void loadDataByCate(CategoryService c){
         this.categories.setOnAction(e -> {
            Category selectedCate = (Category) this.categories.getSelectionModel().getSelectedItem();
            if(selectedCate!=null){
                try {
                    List<Question> questions = c.getQuestionByCateId(selectedCate.getId());
                     this.questions.setItems(FXCollections.observableList(questions));
                } catch (SQLException ex) {
                    Logger.getLogger(FxQuestionsController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        });
    }
    public void loadData(String kw) {
        QuestionService q = new QuestionService();
        try {
            this.questions.setItems(FXCollections.observableList(q.getQuestions(0,kw)));
        } catch (SQLException ex) {
            Logger.getLogger(FxQuestionsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void loadColumns(){
        TableColumn colContent = new TableColumn("Nội dung câu hỏi");
        colContent.setPrefWidth(300);
        colContent.setCellValueFactory(new PropertyValueFactory("content"));
        
         TableColumn colCate = new TableColumn("Danh mục câu hỏi");
         colCate.setPrefWidth(150);
        colCate.setCellValueFactory(new PropertyValueFactory("categoryId"));
        this.questions.getColumns().addAll(colContent,colCate);
    }
    @FXML
    public void addQuestion(ActionEvent event){
            
        try {
            String text =  TitleQuestion.getText();
            QuestionService qs = new QuestionService();
            Category c= (Category) this.categories.getSelectionModel().getSelectedItem();
            Question q= new Question(UUID.randomUUID().toString(),TitleQuestion.getText(),c.getId());
            Choice c1 = new Choice(UUID.randomUUID().toString(),txtA.getText(),rdoA.isSelected(),q.getId());
            Choice c2 = new Choice(UUID.randomUUID().toString(),txtB.getText(),rdoB.isSelected(),q.getId());
            Choice c3 = new Choice(UUID.randomUUID().toString(),txtC.getText(),rdoC.isSelected(),q.getId());
            Choice c4 = new Choice(UUID.randomUUID().toString(),txtD.getText(),rdoD.isSelected(),q.getId());
            List<Choice> choices  = Arrays.asList(c1,c2,c3,c4);
            qs.addQuestion(q, choices);
            Alert a = new Alert(Alert.AlertType.INFORMATION, "Add Done", ButtonType.OK);
            a.show();
            loadData(null);
        } catch (SQLException ex) {
            Logger.getLogger(FxQuestionsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
