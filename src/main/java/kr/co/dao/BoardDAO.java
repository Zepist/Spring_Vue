package kr.co.dao;

import java.util.List;

import kr.co.vo.BoardVO;

public interface BoardDAO {
	public List<BoardVO> selectList() throws Exception;
	public List<BoardVO> searchList(BoardVO boardVO) throws Exception;
	public BoardVO select(BoardVO vo) throws Exception;
	public void insert(BoardVO boardVO) throws Exception;
	public void update(BoardVO boardVO) throws Exception;
	public void delete(int bno) throws Exception;
	
}
