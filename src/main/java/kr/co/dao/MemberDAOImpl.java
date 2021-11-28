package kr.co.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.co.vo.MemberVO;

@Repository("kr/co/dao/MemberDAO")
public class MemberDAOImpl implements MemberDAO {
	
	@Inject
	private SqlSession sqlSession;
	
	@Override
	public List<MemberVO> selectList() throws Exception {

		return sqlSession.selectList("memberMapper.selectList");
	}
	
	@Override
	public List<MemberVO> searchList(MemberVO memberVO) throws Exception {

		return sqlSession.selectList("memberMapper.searchList",memberVO);
	}

	@Override
	public MemberVO select(int bno) throws Exception {
		return sqlSession.selectOne("memberMapper.select",bno);
	}
	

	@Override
	public void insert(MemberVO memberVO) throws Exception {
		sqlSession.insert("memberMapper.insert",memberVO);
	}


	@Override
	public void update(MemberVO memberVO) throws Exception {
		sqlSession.update("memberMapper.update",memberVO);
	}

	@Override
	public void delete(int bno) throws Exception {
		sqlSession.delete("memberMapper.delete",bno);
	}

	@Override
	public MemberVO loginInfo(MemberVO memberVO) throws Exception {
		return sqlSession.selectOne("memberMapper.loginInfo", memberVO);
	}

}
