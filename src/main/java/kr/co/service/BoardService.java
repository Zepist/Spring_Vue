package kr.co.service;

import java.util.List;

import kr.co.vo.BoardVO;

public interface BoardService {
	public List<BoardVO> selectList() throws Exception;
	
	public List<BoardVO> searchList(BoardVO boardVO) throws Exception;	
	
	public BoardVO select(int bno) throws Exception;
	
	public void insert(BoardVO boardVO) throws Exception;
	
	public void update(BoardVO boardVO) throws Exception;
	
	public void delete(int bno) throws Exception;
}
