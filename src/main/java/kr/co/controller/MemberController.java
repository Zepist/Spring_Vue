package kr.co.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kr.co.service.MemberService;
import kr.co.vo.MemberVO;

/**
 * 게시판 관련 Controller
 */
@RestController
//@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping("/member")
public class MemberController {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	MemberService service;
	
	// 회원 목록 조회
	@RequestMapping(value = "/selectList", method = RequestMethod.GET)
	public List<MemberVO> selectList() throws Exception {
		logger.info("selectList");
		
		List<MemberVO> list = service.selectList();
		
		return list; 
	}	
	
	// 회원 목록 조회
	@RequestMapping(value = "/searchList", method = RequestMethod.POST)
	public List<MemberVO> searchList(@RequestBody MemberVO memberVO) throws Exception {
		logger.info("searchList");
		
		List<MemberVO> list = service.searchList(memberVO);
		
		return list; 
	}	
	
	// 회원 목록 조회
	@RequestMapping(value = "/loginInfo", method = RequestMethod.POST)
	public MemberVO loginInfo(@RequestBody MemberVO memberVO) throws Exception {
		logger.info("loginInfo");
		
		System.out.println("memberID :: " + memberVO.getId());
		System.out.println("memberPW :: " + memberVO.getPw());
		
		MemberVO member = service.loginInfo(memberVO);
		
		return member; 
	}		
	
	// 회원 상세 정보 조회
	@RequestMapping(value = "/select/{userid}", method = RequestMethod.GET)
	public MemberVO select(@PathVariable int userid) throws Exception {
		logger.info("selectBoard");
		
		MemberVO board = service.select(userid);
		
		return board;
	}		
	
	// 회원가입
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public void insert(@RequestBody MemberVO memberVO) throws Exception {
		logger.info("insertBoard");
		
		service.insert(memberVO);
	}
	
	// 회원정보 수정
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public void update(@RequestBody MemberVO memberVO) throws Exception {
		logger.info("updateBoard");
		
		service.update(memberVO);
	}
	
	// 회원탈퇴
	@RequestMapping(value = "/delete/{userid}", method = RequestMethod.DELETE)
	public void delete(@PathVariable int userid) throws Exception {
		logger.info("deleteBoard");
		
		service.delete(userid);
	}	
	
}
