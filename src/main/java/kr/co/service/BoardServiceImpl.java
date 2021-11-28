package kr.co.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.dao.BoardDAO;
import kr.co.vo.BoardVO;

@Service("kr/co/dao/BoardService")
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardDAO dao;
	
	@Override
	public List<BoardVO> selectList() throws Exception {
		return dao.selectList();
	}	
	
	@Override
	public List<BoardVO> searchList(BoardVO boardVO) throws Exception {
		return dao.searchList(boardVO);
	}		
	
	@Override
	public BoardVO select(int bno) throws Exception {
		BoardVO vo = new BoardVO();
		vo.setBno(bno);
		
		vo.getParameter();
		
		return dao.select(vo);
	}	

	@Override
	public void insert(BoardVO boardVO) throws Exception {
		dao.insert(boardVO);
	}

	@Override
	public void update(BoardVO boardVO) throws Exception {
		dao.update(boardVO);
	}

	@Override
	public void delete(int bno) throws Exception {
		dao.delete(bno);
	}
	
}
