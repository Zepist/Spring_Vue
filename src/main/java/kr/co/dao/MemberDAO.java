package kr.co.dao;

import java.util.List;

import kr.co.vo.MemberVO;

public interface MemberDAO {
	public List<MemberVO> selectList() throws Exception;
	public List<MemberVO> searchList(MemberVO memberVO) throws Exception;
	public MemberVO select(int bno) throws Exception;
	public MemberVO loginInfo(MemberVO memberVO) throws Exception;
	public void insert(MemberVO memberVO) throws Exception;
	public void update(MemberVO memberVO) throws Exception;
	public void delete(int bno) throws Exception;
	
}
