package kr.co.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kr.co.service.BoardService;
import kr.co.vo.BoardVO;

/**
 * 게시판 관련 Controller
 */
@RestController
//@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping("/board")
public class BoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired
	BoardService service;
	
	// 게시판 목록 조회
	@RequestMapping(value = "/selectList", method = RequestMethod.GET)
	public List<BoardVO> selectList() throws Exception {
		logger.info("selectList");
		
		List<BoardVO> list = service.selectList();
		
		return list; // 몰라
	}	
	
	// 게시판 목록 검색
	@RequestMapping(value = "/searchList", method = RequestMethod.POST)
	public List<BoardVO> searchList(@RequestBody BoardVO boardVO) throws Exception {
		logger.info("searchList");
		
		boardVO.getParameter();
		
		List<BoardVO> list = service.searchList(boardVO);
		
		return list; // 몰라
	}		
	
	// 게시판 목록 조회
	@RequestMapping(value = "/select/{bno}", method = RequestMethod.GET)
	public BoardVO select(@PathVariable int bno) throws Exception {
		logger.info("selectBoard");
		
		BoardVO board = service.select(bno);
		
		return board;
	}		
	
	// 게시판 글 작성
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public void insert(@RequestBody BoardVO boardVO) throws Exception {
		logger.info("insertBoard");
		
		service.insert(boardVO);
	}
	
	// 게시판 글 수정
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public void update(@RequestBody BoardVO boardVO) throws Exception {
		logger.info("updateBoard");
		
		service.update(boardVO);
	}
	
	// 게시판 글 삭제
	@RequestMapping(value = "/delete/{bno}", method = RequestMethod.DELETE)
	public void delete(@PathVariable int bno) throws Exception {
		logger.info("deleteBoard");
		
		service.delete(bno);
	}	
	
}
