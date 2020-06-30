package rl.knockout.taiei;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.web.servlet.ModelAndView;

import rl.knockout.taiei.contoller.*;
import rl.knockout.taiei.model.*;
import rl.knockout.taiei.model.dao.*;

import java.sql.*;
import java.util.ArrayList;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml")
@WebAppConfiguration
//https://blog.kakakikikeke.com/2015/07/spring-test-mockmvc.html
public class HomeControllerTest {

    private final HomeController homeController = new HomeController();
    
    //mockMvcというspringMVCの動作を再現するものを使う。なにこれ。
    //モック：テストコード入りスタブ
    //MockMvcは、そのうえ疑似的にリクエストを送るらしい
    //https://terasolunaorg.github.io/guideline/5.4.1.RELEASE/ja/UnitTest/ImplementsOfUnitTest/UsageOfLibraryForTest.html#mockmvc
    private MockMvc mockMvc;
    @Before
    public void setup() {
      mockMvc = MockMvcBuilders.standaloneSetup(homeController).alwaysDo(log()).build();
    }
    
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
    	
        assertThat(homeController.menu(user),equalTo("redirect:login"));
    }
    
    @Before
    public void setupBindingResult() {
    	//TODO:
    }
    
    @Test
    public void historySearchTest() throws Exception {//BindingResultがどこで初期化されているのかわからず、したがってテスト時にどう初期化すればよいのか
    	//Arrange
    	////stub
    	HistorySearchForm historySearchForm = homeController.setupHistorySearchForm();
    	historySearchForm.setAcc_id("temp");
    	historySearchForm.setLogin_name("Test Taste");
    	historySearchForm.setOrder_status("tetetetest");
    	historySearchForm.setDateKindString("confirm_date");
    	historySearchForm.setBeginDate("2020-09-07");
    	historySearchForm.setEndDate("1111-01-01");
    	historySearchForm.setNowPage(1);
    	
    	ModelAndView modelAndView = new ModelAndView("home");
    	
    	BindingResult bindingResult = new DataBinder(historySearchForm).getBindingResult();
    	
    	//Act
    	MvcResult result = mockMvc.perform(post("/historySearch").flashAttr("historySearchForm", historySearchForm).flashAttr("result", bindingResult)).andExpect(status().isOk()).andExpect(view().name("historySearch")).andReturn();//https://terasolunaorg.github.io/guideline/5.4.1.RELEASE/ja/UnitTest/ImplementsOfUnitTest/UsageOfLibraryForTest.html#mockmvc
    	ModelAndView actualResultModelAndView = result.getModelAndView();

    	//Assert
        assertThat(actualResultModelAndView.getModel().get("historySearchForm"), equalTo(modelAndView.getModel().get("historySearchForm")));
    }
}
/*
 * Assert  正しいと確認する・正しいと断言する
 * Assert that actual result is equal to model モデルと実際の結果が等しいことを確認せよ
 * 
 * @Autowired DIコンテナに登録  https://qiita.com/ryo2132/items/ec10116238e1e1f333a1
 * 単にnewするだけでなく、bean.xmlと同じように登録している？
 * 
 * 
 * 
 * 
 * 
 * http://nocturne-life-restoration.hatenablog.com/entry/2014/03/21/205221
 * SpringFramework4テストコードはServlet3.0が必要になった。
 * 公式ドキュメントの読みにくさよ
 * */