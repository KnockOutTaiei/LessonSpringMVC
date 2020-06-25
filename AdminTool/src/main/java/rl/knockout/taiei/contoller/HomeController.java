//　一番大事なことは、コンピューター内部で何が起こっているのか予想しながら書くこと。
//　return "login"って書いてあるけど、これファイル名を参照してるんだろうなーとか
//　初期化はここで行ってるからこの時点でフィールドには1が入るなーとか




package rl.knockout.taiei.contoller;//　パッケージ名、勝手に変えてます。コンテキストパスがtaieiなんで、接続先は localhost:8080/taiei になります//http://www.cresc.co.jp/tech/java/Servlet_Tutorial/Lesson_70.htm とかいいすね。

//import文で*を使っても、eclipseの設定をすれば勝手に変更されない
//https://qiita.com/kimisyo/items/9f9a028f9b4fba9367d8
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.*;

import javax.validation.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import rl.knockout.taiei.model.*;
import rl.knockout.taiei.model.dao.JDBCDriver;

import static org.springframework.format.annotation.DateTimeFormat.ISO.*;

@Controller
@SessionAttributes(types = {History.class,User.class})//　セッションスコープ用
public class HomeController{
	
	private JDBCDriver jdbcDriver;//　データベース接続を担うJDBCDriverクラスのインスタンスを初期化
	{jdbcDriver = new JDBCDriver();}// 初期化子といって、コンストラクタより前に行う初期化処理を書く。もともとは、コンストラクタをいくつもいくつも作ったときに、その複数のコンストラクタで共通の処理を書きたいところから
	
	private static ApplicationContext app;//セッションスコープ用
	
	@RequestMapping(value = "/", method = RequestMethod.GET)//　直下、GETリクエスト用のメソッドです。URLを直接ブラウザの上のほうのあの窓に書いたときにもGET送信なのでこれが呼ばれます
	public String home(@ModelAttribute("user") User user, Model model) {
		user = new User();
		model.addAttribute(user);
		//userが指しているインスタンスを削除する。
		//　そもそも、user=new User(); という一文が内部で何をやっているかっていうと
		////new User()は
		////　User型でインスタンスを物理的に作る、いいかえれば、
		////　例えばUser型は、int型(32bit)が２つ、boolean(実装にもよるが最低1bit)が２つ、String型が１つなので、
		////　ここでString型を113bitと予想すると、　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　※Stringクラスのフィールドは５つあり、それぞれbyte[]型(配列なので8*n bit,とりあえず8bitと想定)、byte型(8bit)、int型(32bit)、long型(64bit)、boolean型(1bit)なので
		////　サイズは32*2+1*2+113= 179bit(とあとメソッドの分)。よって
		////　内部では、パソコン内部の電子装置179個に充電する、というのがこのnew User()の内部的な意味なんじゃないかと。
		////user=は
		////　その入れた電気信号のセットに管理IDを振って、その管理IDをuserというフィールドに入れる、ということかと。
		//　ってことはuserってインスタンスを直接持ってるんじゃなくて、インスタンスの管理IDを持っているだけなのだ
		//　授業の資料だと、参照渡しのところと同じことを言ってます。
		//　だからuserに新しい管理IDを入れてやれば、昔のインスタンスは電気的には残ってるけどもう参照できないから削除したことになるね、という発想。
		//　全て私個人の予想ではありますが…
		
		//　C++だと、まあ管理IDというか、そのインスタンスの先頭のアドレスなんだが、デバッグ時のidとかいうものを見る限り、JVMの場合はこれに近いのだろうか
		
		System.out.println("===ログイン画面===");
		debugWriteUser(user);
		
		
		//　デバッグライト(Debug Write)とは、デバッグ時に内部のフィールドの値を見たいときに、その値をコンソールなどに表示させること
		//　授業でやったように、デバッグで起動して「変数」タブや「Debug Shell」から見れるから本来は不要なのだが
		//　私の環境がどっかおかしくなったらしくデバッグ起動が出来なくなったので仕方なく古典的な方法をとってます
		return "login";//指定するのはURI。web.xmlに書いたprefix+これ+suffixになる
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(@ModelAttribute("user") User user, Model model) {
		//　上に同じ
		user = new User();
		model.addAttribute(user);
		
		System.out.println("===ログイン画面===");
		debugWriteUser(user);
		return "login";
	}
	
	@RequestMapping(value = "/menu", method = RequestMethod.GET)
	public String menu(@ModelAttribute("user") User user) {
		//privilege 英単語で「権限」
		//isPrivilegeLogin()がスタッフの権限、isPrivilegeLoginAdmin()が管理者権限
		if(user.isPrivilegeLogin())return "menu";
		else return "redirect:login";
	}
	
	//　どうやら@ModelAttributeを使うと、まず対応するメソッド（私の場合はsetupLoginForm()）に飛んでから、jspから値を取ってくるみたいな流れなんだろうな。
	@RequestMapping(value = "/menu", method = RequestMethod.POST, params="confirm")
	public String menuPost(@Validated @ModelAttribute LoginForm loginForm, BindingResult result, Model model, @ModelAttribute("user") User user) {
		//result.hasErrors()でバリデーションチェックでエラーが出たときの挙動を記述する
		if(result.hasErrors())return "login";

		if(jdbcDriver.canLogin(loginForm)) {
			jdbcDriver.createUser(loginForm.getStaff_id(), user);
			model.addAttribute("user",user);
			
			System.out.println("===ログイン成功===");
			debugWriteUser(user);
			return "menu";
		}else {
			return "login";
		}
	}
	
	@RequestMapping(value = "/historySearch", method = RequestMethod.GET)
	public String goHistorySearch(@ModelAttribute HistorySearchForm historySearchForm, @ModelAttribute("user") User user) {
		if(user.isPrivilegeLogin())return "historySearch";
		else return "redirect:login";
	}
	
	@RequestMapping(value = "/historySearch", method = RequestMethod.POST)
	public ModelAndView historySearch(@Validated @ModelAttribute HistorySearchForm historySearchForm, BindingResult result) {
		//Validation
		if(result.hasErrors())return new ModelAndView("historySearch");
		
		//DateKind setting
		//　イーナム・エニュムレータのいいところは「型を決めることにより、より安全に使えるということ」
		//　もし悪い人にハッキング（というかクラッキング）されたら勝手に値がいじられるStringと違って、値を限定することができるのでより安全
		DateKind dateKind;
		//contentEqualsにひとつなってたけど違いはこれから勉強します…間違えてました…はい。
		if(historySearchForm.getDateKindString().contentEquals("order_date"))dateKind=DateKind.ORDER;
		else if(historySearchForm.getDateKindString().equals("limit_date"))dateKind=DateKind.LIMIT;
		else if(historySearchForm.getDateKindString().equals("confirm_date"))dateKind=DateKind.CONFIRM;
		else dateKind=DateKind.NONE;
		
		//Date setting
		////from String to LocalDateTime like"2020/05/11 15:36:45"
		LocalDateTime begin = LocalDateTime.now();LocalDateTime end = LocalDateTime.now();
		if(dateKind!=DateKind.NONE) {
			//　データベースの日付の方をDATETIME型にしてしまったためその調整。
			DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			String beginString = historySearchForm.getBeginDate(); if(!isMatch(historySearchForm.getBeginDate(),".{1,}[:]{1,}"))beginString+=" 00:00:00";
			String endString   = historySearchForm.getEndDate();   if(!isMatch(historySearchForm.getEndDate(),  ".{1,}[:]{1,}"))endString  +=" 00:00:00";
			begin = LocalDateTime.parse(beginString,format);
			end = LocalDateTime.parse(endString,format);
		}
		
		//JDBC
		ArrayList<HistorySummary> historySummaries = new ArrayList<HistorySummary>();
		jdbcDriver.getHistorySummaries(historySearchForm.getAcc_id(),historySearchForm.getLogin_name(),historySearchForm.getOrder_status(),dateKind,begin,end,historySearchForm.getNowPage(),historySummaries);
		
		ModelAndView modelAndView = new ModelAndView("historySearch");
		modelAndView.addObject("historySummaries", historySummaries);
		return modelAndView;
	}
	
	@RequestMapping(value = "/history", method = RequestMethod.GET)
	public String historySearchResult(@RequestParam String order_id, Model model, @ModelAttribute("user") User user) {
		History history = new History();
		jdbcDriver.getHistory(order_id, history);
		model.addAttribute("history",history);
		if(user.isPrivilegeLogin())return "history";
		else return "redirect:login";
	}
	
	@RequestMapping(value = "/historyEdit", method = RequestMethod.GET)
	public String historyEdit(Model model, @ModelAttribute("history") History history, @ModelAttribute("user") User user) {
		model.addAttribute("history",history);
		if(user.isPrivilegeLogin())return "historyEdit";
		else return "redirect:login";
	}
	
	@RequestMapping(value = "/historyEdit", method = RequestMethod.POST, produces="text/plain;charset=UTF-8")
	public String historyEditModify(Model model, @Validated @ModelAttribute("history") History history, BindingResult result) {
		//Validation
		if(result.hasErrors())return "historyEdit";
		
		jdbcDriver.editHistory(history.getOrder_id(),history);
		model.addAttribute("history",history);
		return "historyEdit";
	}
	
	@RequestMapping(value = "/deleteHistory", method = RequestMethod.POST)
	public String deleteHistory(@ModelAttribute("history") History history) {
		jdbcDriver.deleteHistory(history);
		return "historyEdit";
	}
	
	@RequestMapping(value = "/administratorMenu", method = RequestMethod.POST)
	public String administratorMenu(@Validated LoginForm loginForm,BindingResult result, @ModelAttribute("user") User user) {
		if(result.hasErrors())return "menu";
		
		if(jdbcDriver.canLoginAdmin(loginForm,user)) {
			jdbcDriver.createUser(loginForm.getStaff_id(), user);
			System.out.println("===管理者ログイン成功===");
			debugWriteUser(user);
			return "administratorMenu";
		}else {
			return "menu";
		}
	}
	
	@RequestMapping(value = "/administratorMenu", method = RequestMethod.GET)
	public String toAdministratorMenu(@ModelAttribute("user") User user) {
		if(user.isPrivilegeLoginAdmin())return "administratorMenu";
		else return "redirect:menu";
	}
	
	@RequestMapping(value = "/staffManagement", method = RequestMethod.GET)
	public String staffManagement(Model model, @ModelAttribute("user") User user) {
		if(user.isPrivilegeLoginAdmin())return "staffManagement";
		else return "redirect:menu";
	}
	
	@RequestMapping(value = "/staffManagement", method = RequestMethod.POST)
	public String staffManagementPost(@ModelAttribute @Validated StaffSearchForm staffSearchForm, BindingResult result, Model model) {
		if (result.hasErrors()){return "staffManagement";}
		ArrayList<StaffSummary> staffSummaries = new ArrayList<StaffSummary>();
		jdbcDriver.getStaffSummaries(staffSearchForm.getStaff_name(), staffSearchForm.getStaff_roll_id(), staffSearchForm.getExperience(), staffSearchForm.getNowPage(),staffSummaries);
		model.addAttribute("staffSummaries",staffSummaries);
		return "staffManagement";
	}
	
	@RequestMapping(value = "/staffEdit", method = RequestMethod.GET)
	public String staffEdit(@RequestParam String staff_id, Model model, @ModelAttribute("user") User user) {
		Staff staff = new Staff();
		jdbcDriver.getStaff(Integer.parseInt(staff_id), staff);
		model.addAttribute("staff",staff);
		if(user.isPrivilegeLoginAdmin())return "staffEdit";
		else return "redirect:menu";
	}
	
	@RequestMapping(value = "/staffEdit", method = RequestMethod.POST)
	public String staffEditPost(@Validated @ModelAttribute Staff staff, BindingResult result, Model model) {
		if(result.hasErrors())return "staffEdit";
		
		jdbcDriver.editStaff(staff);
		jdbcDriver.getStaff(staff.getStaff_id(), staff);
		model.addAttribute("staff",staff);
		return "staffEdit";
	}
	
	@RequestMapping(value = "/deleteStaff", method = RequestMethod.POST)
	public String deleteStaff(@RequestParam String staff_id, Model model) {
		Staff staff = new Staff();
		jdbcDriver.getStaff(Integer.parseInt(staff_id), staff);
		jdbcDriver.deleteStaff(staff);
		model.addAttribute("staff",staff);
		return "staffEdit";
	}
	
	@RequestMapping(value = "/staffRegistration", method = RequestMethod.GET)
	public String staffRegistration(Model model, @ModelAttribute("user") User user) {
		Staff staff = new Staff();
		model.addAttribute("staff",staff);
		if(user.isPrivilegeLoginAdmin())return "staffRegistration";
		else return "redirect:menu";
	}
	
	@RequestMapping(value = "/staffRegistration", method = RequestMethod.POST)
	public String staffRegistrationPost(@Validated @ModelAttribute Staff staff, BindingResult result, Model model) {
		if(result.hasErrors())return "staffRegistration";
		
		jdbcDriver.registerStaff(staff);
		model.addAttribute("staff",staff);
		return "staffRegistration";
	}
	
	//for Regular Expression
	//Regular(正規の) + Expression(表現) = 正規表現
	//　プログラミング初心者って、以外と英語でつっかかってんのでは説
	private boolean isMatch(String textToValidate, String regexToUse){
        Pattern pattern = Pattern.compile(regexToUse);//X{n,m}	X, at least n but not more than m times
		Matcher matcher = pattern.matcher(textToValidate);
		return matcher.matches();
    }
	
	
	//for Session: See @SessionAttributes
	@ModelAttribute("history")
	public History putHistoryToSession(History history){//初期化どこで行ってる？
        return history;
    }
	@ModelAttribute("user")
	public User putUserToSession(User user){
        return user;
    }
	
	//for Validation
	@ModelAttribute
	public LoginForm setupLoginForm() {
		return new LoginForm();
	}
	@ModelAttribute
	public HistorySearchForm setupHistorySearchForm() {
		return new HistorySearchForm();
	}
	@ModelAttribute
	public StaffSearchForm setupStaffSearchForm() {
		return new StaffSearchForm();
	}
	
	//for DebugWrite
	private void debugWriteUser(User user){
		System.out.println(user.getStaff_id());
		System.out.println(user.getStaff_name());
		System.out.println(user.getStaff_roll_id());
		System.out.println("スタッフログイン権限："+user.isPrivilegeLogin());
		System.out.println("管理者ログイン権限："+user.isPrivilegeLoginAdmin());
	}
}
/*
 *@ModelAttributeを使えばフォーム情報を取得できるようだが、このために一つクラスを書かなきゃならない
 *スコープでいえばリクエスト、つまり送信のたびにいちいち切り替わるものにクラスを書いて設定しなきゃいけないなんてとても賢明な設計とは思えないな。
 *普通に変数(request.getAttribute("formContent"))として使えばいいやん
 *メモリの確保も破棄も処理が重くなっちまう
 *->@RequestParamができます！一個ずつリクエストスコープに入れられます！
 *
 *
 *
 *
 *ルートディレクトリはwebapp
 *https://qiita.com/5zm/items/89b7198cab74f2d0f4a1
 *
 *
 *
 *
 *セッションスコープの使い方
 *https://www.codeflow.site/ja/article/spring-mvc-session-attributesのセクション５
 *０．モデル用のクラスを作らなけれいけない
 *
 *１．そのモデルにアクセスするためのメソッドを書き、@ModelAttributeを付与
 *@ModelAttribute("user")
	public User putUserToSession(User user){
        return user;
    }
 *２．コントローラーに、このモデルはセッションスコープに置いてくれと頼む。@SessionAttributesで
 *@SessionAttributes(types = {History.class,User.class})
 *
 *３．サーブレットに相当するメソッドの引数に、@ModelAttribute 型 インスタンス名　を入れる
 *@RequestMapping(value = "/menu", method = RequestMethod.GET)
	public String menu(@ModelAttribute("user") User user, Model model) {
		if(user!=null)return "menu";
		else return "login";
	}
 *同じ型のインスタンスを複数入れようとするとどうなるのかは要検証->できない、使いまわしている的なことがどこかに書かれていた
 *
 *
 *
 *
 *バリデーションのやりかた
 *０．そのフォーム用のクラスを作る＋アノテーションでバリデーション付与
 *１．@ModelAttributeでそのクラスにアクセスするためのメソッドを作る（ここまでセッションと同じ）
 *２．サーブレットに相当するメソッドの引数に、@Validated 型 インスタンス, BindingResult resultと仮引数宣言（セッションの３）、このときBindingResultは直後でないといけない
 *３．jspの書き換え
    
    
 NotEmptyは数値には使えないらしい。
 https://javaee.github.io/javaee-spec/javadocs/javax/validation/constraints/NotEmpty.html
 The annotated element must not be null nor empty. Supported types are:
CharSequence (length of character sequence is evaluated)
Collection (collection size is evaluated)
Map (map size is evaluated)
Array (array length is evaluated)
 */