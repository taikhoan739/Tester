/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import com.nhm.pojo.Category;
import com.nhm.services.CategoryService;
import com.nhm.services.QuestionService;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;


public class Tester {
//    @Test
    @Test
    public static void CheckQuestionMustInCategory() throws SQLException{
        
        QuestionService qs = new QuestionService();
        
        CategoryService cs = new CategoryService();
        List<Integer> idsCategory = new ArrayList<>();
        List<Category> categories =  cs.getCategories();
        
       
    }
}
