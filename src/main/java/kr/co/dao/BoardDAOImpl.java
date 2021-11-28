package kr.co.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.co.vo.BoardVO;

@Repository("kr/co/dao/BoardDAO")
public class BoardDAOImpl implements BoardDAO {
	
	@Inject
	private SqlSession sqlSession;
	
	@Override
	public List<BoardVO> selectList() throws Exception {

		return sqlSession.selectList("boardMapper.selectList");
	}
	
	@Override
	public List<BoardVO> searchList(BoardVO boardVO) throws Exception {

		return sqlSession.selectList("boardMapper.searchList",boardVO);
	}

	@Override
	public BoardVO select(BoardVO boardVO) throws Exception {
		return sqlSession.selectOne("boardMapper.searchList",boardVO);
	}
	

	@Override
	public void insert(BoardVO boardVO) throws Exception {
		sqlSession.insert("boardMapper.insert",boardVO);
	}


	@Override
	public void update(BoardVO boardVO) throws Exception {
		sqlSession.update("boardMapper.update",boardVO);
	}

	@Override
	public void delete(int bno) throws Exception {
		sqlSession.delete("boardMapper.delete",bno);
	}

}
