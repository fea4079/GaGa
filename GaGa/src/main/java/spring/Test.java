package spring;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class Test {


	@GetMapping("/")
	public String index() {
		
		System.out.println("test");
		return "loginView";
		
	}

	
}
