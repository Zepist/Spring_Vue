package kr.co.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kr.co.service.BoardService;

/**
 * Handles requests for the application home page.
 */
@RestController // 모든 메소드에 자동으로 @ResponseBody가 적용된다.
//@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	BoardService service;
	
//	@GetMapping("/test")
//	public String test() throws Exception {
//		//BoardVO board = service.select(11);
//		String message = "나의 첫번째 REST 방식 메세지이다.";
//		
//		return message;
//	}
//
//	@GetMapping("/members")
//	public Map<String,Object> members() throws Exception {
//		//BoardVO board = service.select(11);
//		Map<String,Object> members = new HashMap<>();
//		
//		for (int i = 1; i <= 20; i++) {
//			Map<String, Object> member = new HashMap<>();
//			member.put("idx", i);
//			member.put("nickname", i + "길동");
//			member.put("height", i + 20);
//			member.put("weight", i + 30);
//			members.put(i + "번째", member);
//		}		
//		
//		return members;
//	}
//	@RequestMapping(value = "/list/{id}",method = RequestMethod.GET)
//	public Map<String,String> home(@PathVariable String id){
//		
//		Map<String,String> list = new HashMap<>();
//		
//		list.put("id",id);
//		list.put("pw", "password1234");
//		list.put("location", "BUSAN");
//		
//		return list;
//	}
//	
//	@RequestMapping(value = "/list",method = RequestMethod.POST)
//	public Map<String,String> home2(@RequestBody Map<String,String> list){
//		
//		// 내용이 잘 들어왔는지 테스트
//		String id = list.get("id");
//		String pw = list.get("pw");
//		System.out.println(id + " : " + pw);
//		
//		// 내용 변경해서 리턴
//		list.replace("id", id + "response");
//		list.replace("pw", pw + "response");
//		
//		return list;
//	}
	
}