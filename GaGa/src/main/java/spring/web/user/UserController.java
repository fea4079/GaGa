package spring.web.user;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import spring.common.Page;
import spring.common.Search;
import spring.domain.User;
import spring.service.user.KakaoService;
import spring.service.user.NaverService;
import spring.service.user.UserService;




//==> ȸ������ Controller
@Controller
@RequestMapping("/user/*")
public class UserController {
	
	///Field
	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;
	//setter Method ���� ����
		
	public UserController(){
		System.out.println(this.getClass());
	}
	
	@Value("${pageUnit}")
	int pageUnit;
	@Value("${pageSize}")
	int pageSize;
	
	@Autowired
	private KakaoService ks;
	@Autowired
	private NaverService ns;
	
	
	@RequestMapping( value="addUser", method=RequestMethod.GET )
	public String addUser() throws Exception{
	
		System.out.println("/user/addUser : GET");
		
		return "redirect:/user/addUserView.jsp";
	}
	
	@RequestMapping( value="addUser", method=RequestMethod.POST )
	public String addUser( @ModelAttribute("user") User user ) throws Exception {

		System.out.println("/user/addUser : POST");
		//Business Logic
		userService.addUser(user);
		
		return "redirect:/user/loginView.jsp";
	}
	

	@RequestMapping( value="getUser", method=RequestMethod.GET )
	public String getUser( @RequestParam("userId") String userId , Model model ) throws Exception {
		
		System.out.println("/user/getUser : GET");
		//Business Logic
		User user = userService.getUser(userId);
		// Model �� View ����
		model.addAttribute("user", user);
		
		return "forward:/user/getUser.jsp";
	}
	

	@RequestMapping( value="updateUser", method=RequestMethod.GET )
	public String updateUser( @RequestParam("userId") String userId , Model model ) throws Exception{

		System.out.println("/user/updateUser : GET");
		//Business Logic
		User user = userService.getUser(userId);
		// Model �� View ����
		model.addAttribute("user", user);
		
		return "forward:/user/updateUser.jsp";
	}

	@RequestMapping( value="updateUser", method=RequestMethod.POST )
	public String updateUser( @ModelAttribute("user") User user , Model model , HttpSession session) throws Exception{

		System.out.println("/user/updateUser : POST");
		//Business Logic
		userService.updateUser(user);
		
		String sessionId=((User)session.getAttribute("user")).getUserId();
		if(sessionId.equals(user.getUserId())){
			session.setAttribute("user", user);
		}
		
		return "redirect:/user/getUser?userId="+user.getUserId();
	}
	
	
	@RequestMapping( value="login", method=RequestMethod.GET )
	public String login() throws Exception{
		
		System.out.println("/user/logon : GET");

		return "redirect:/user/loginView.jsp";
	}
	
	@RequestMapping( value="login", method=RequestMethod.POST )
	public String login(@ModelAttribute("user") User user , HttpSession session ) throws Exception{
		
		System.out.println("/user/login : POST");
		//Business Logic
		User dbUser=userService.getUser(user.getUserId());
		
		if( user.getPassword().equals(dbUser.getPassword())){
			session.setAttribute("user", dbUser);
		}
		
		return "redirect:/index.jsp";
	}
		
	
	@RequestMapping( value="logout", method=RequestMethod.GET )
	public String logout(HttpSession session ) throws Exception{
		
		System.out.println("/user/logout : POST");
		
		session.invalidate();
		
		return "redirect:/index.jsp";
	}
	
	
	@RequestMapping( value="checkDuplication", method=RequestMethod.POST )
	public String checkDuplication( @RequestParam("userId") String userId , Model model ) throws Exception{
		
		System.out.println("/user/checkDuplication : POST");
		//Business Logic
		boolean result=userService.checkDuplication(userId);
		// Model �� View ����
		model.addAttribute("result", new Boolean(result));
		model.addAttribute("userId", userId);

		return "forward:/user/checkDuplication.jsp";
	}

	
	@RequestMapping( value="listUser" )
	public String listUser( @ModelAttribute("search") Search search , Model model , HttpServletRequest request) throws Exception{
		
		System.out.println("/user/listUser : GET / POST");
		
		if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);
		
		// Business logic ����
		Map<String , Object> map=userService.getUserList(search);
		
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		System.out.println(resultPage);
		
		// Model �� View ����
		model.addAttribute("list", map.get("list"));
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search);
		
		return "forward:/user/listUser.jsp";
	}
	
	@RequestMapping(value="kakaoLogin", method=RequestMethod.GET)
	public String kakaoLogin(@RequestParam(value = "code", required = false) String code, HttpSession session) throws Exception {
		System.out.println("UserController.java kakao#1111##" + code);
        
		
		String access_Token = ks.getAccessToken(code);
		System.out.println("UserController.java kakao#2222#access_Token#### : " + access_Token);
		
		HashMap<String, Object> userInfo = ks.getUserInfo(access_Token);
		System.out.println("UserController.java kakao#3333#access_Token#### : " + access_Token);
		System.out.println("UserController.java kakao#4444#nickname#### : " + userInfo.get("nickname"));
		System.out.println("UserController.java kakao#5555#email#### : " + userInfo.get("email"));
        
		User dbUser=ks.getKakaoUser((String)userInfo.get("id"));
		System.out.println("6666 userȮ��"+dbUser);
		
		session.setAttribute("user", dbUser);
		
        
		return "redirect:/index.jsp";
    	}
	@RequestMapping(value="naverLogin", method=RequestMethod.GET)
	public String naverLogin(@RequestParam(value = "code", required = false) String code, HttpSession session) throws Exception {
		System.out.println("UserController.java naver#1111#code" + code);
        
		
		String access_Token = ns.getAccessToken(code);
		System.out.println("UserController.java naver#2222#access_Token#### : " + access_Token);
		
		Map<String, Object> userInfo = ns.getUserInfo(access_Token);
		System.out.println("UserController.java naver#3333#access_Token#### : " + access_Token);
		System.out.println("UserController.java naver#4444#nickname#### : " + userInfo.get("nickname"));
		System.out.println("UserController.java naver#5555#email#### : " + userInfo.get("email"));
        
		User dbUser=ns.getNaverUser((String)userInfo.get("id"));
		System.out.println("UserController.java naver#6666#user"+dbUser);
		
		session.setAttribute("user", dbUser);
		
        
		return "redirect:/index.jsp";
    	}
	
}