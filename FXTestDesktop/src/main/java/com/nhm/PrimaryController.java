package com.nhm;

import com.nhm.pojo.Choice;
import com.nhm.pojo.Question;
import com.nhm.services.QuestionService;
import java.io.IOException;
import java.net.URL;
import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;
import javafx.scene.text.Text;

public class PrimaryController implements Initializable{
    @FXML private Text textContent;
    @FXML private RadioButton roA;
    @FXML private RadioButton roB;
    @FXML private RadioButton roC;
    @FXML private RadioButton roD;
    @FXML private Text textA;
    @FXML private Text textB;
    @FXML private Text textC;
    @FXML private Text textD;
    @FXML private Button btnShow;
    private int currentIdx=0;
    private List<Question> questions;
    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
        
    }
    public void checkHandel(ActionEvent e) throws SQLException{
        List<RadioButton> rBChoices = new ArrayList<>();
        rBChoices.add(roA);
        rBChoices.add(roB);
        rBChoices.add(roC);
        rBChoices.add(roD);
        QuestionService qs = new QuestionService();
        Question question = this.questions.get(this.currentIdx);
        List<Choice> choices = qs.getChoices(question.getId());
        for(int i=0;i< rBChoices.size();i++){
            if(rBChoices.get(i).isSelected()){
                    if(choices.get(i).getIsCorrect()){
                         Alert a = new Alert(Alert.AlertType.INFORMATION, "Correct", ButtonType.OK);
                        a.show();
                    }
                    else{
                        Alert a = new Alert(Alert.AlertType.INFORMATION, "InCorrect", ButtonType.OK);
                        a.show();
                    }
                 
                 
            }
        }
       
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
           QuestionService qs = new QuestionService();
            this.questions= qs.getQuestions(3);
            loadQuestionToUI();
           
        } catch (SQLException ex) {
            Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void loadQuestionToUI() throws SQLException{
        QuestionService qs = new QuestionService();
        Question question = this.questions.get(this.currentIdx);
        textContent.setText(question.getContent());
        if(question.getChoices()==null){
            List<Choice> choices = qs.getChoices(question.getId());
            textA.setText(choices.get(0).toString());
            textB.setText(choices.get(1).toString());
            textC.setText(choices.get(2).toString());
            textD.setText(choices.get(3).toString());
        }
    }

    public void nextHandle() throws SQLException{
        if(this.currentIdx<this.questions.size()-1){
            this.currentIdx++;
        }
        loadQuestionToUI();
        
    }
}
