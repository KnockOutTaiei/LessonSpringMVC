package rl.knockout.taiei;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.web.servlet.ModelAndView;

import rl.knockout.taiei.contoller.*;
import rl.knockout.taiei.model.*;
import rl.knockout.taiei.model.dao.*;

import java.sql.*;
public class HomeControllerTest {

    private final HomeController homeController = new HomeController();

    @Test
    public void goHome() {
    	User user= new User();
    	//Model model = new Model();
        assertEquals("home", homeController.home(user, null));
    }
    
    @Test
    public void goMenu() {
    	User user= new User();
    	user.setPrivilegeLogin(false);
    	
        assertEquals("login", homeController.menu(user));
    }
    
    @Before
    public void setupBindingResult() {
    	//TODO:
    }
    
    @Test
    public void historySearchTest() {//BindingResultがどこで初期化されているのかわからず、したがってテスト時にどう初期化すればよいのか
    	//Arrange
    	HistorySearchForm historySearchForm = homeController.setupHistorySearchForm();
    	historySearchForm.setAcc_id("temp");
    	historySearchForm.setLogin_name("Test Taste");
    	historySearchForm.setOrder_status("tetetetest");
    	historySearchForm.setDateKindString("confirm_date");
    	historySearchForm.setBeginDate("2020-09-07");
    	historySearchForm.setEndDate("1111-01-01");
    	historySearchForm.setNowPage(9);
    	DataBinder dataBinder = new DataBinder(historySearchForm);
    	ModelAndView modelAndView = new ModelAndView("historySearch");
    	
    	//Act
    	ModelAndView actalResult = homeController.historySearch(historySearchForm, dataBinder.getBindingResult());
    	
    	//Assert
        assertThat(actalResult, equalTo(modelAndView));
    }

}