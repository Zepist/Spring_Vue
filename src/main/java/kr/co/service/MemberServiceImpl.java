package kr.co.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.dao.MemberDAO;
import kr.co.vo.MemberVO;

@Service("kr/co/dao/MemberService")
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberDAO dao;
	
	@Override
	public List<MemberVO> selectList() throws Exception {
		return dao.selectList();
	}	
	
	@Override
	public List<MemberVO> searchList(MemberVO memberVO) throws Exception {
		return dao.searchList(memberVO);
	}		
	
	@Override
	public MemberVO select(int bno) throws Exception {
		return dao.select(bno);
	}	

	@Override
	public void insert(MemberVO memberVO) throws Exception {
		dao.insert(memberVO);
	}

	@Override
	public void update(MemberVO memberVO) throws Exception {
		dao.update(memberVO);
	}

	@Override
	public void delete(int bno) throws Exception {
		dao.delete(bno);
	}

	@Override
	public MemberVO loginInfo(MemberVO vo) throws Exception {
		
		MemberVO member = dao.loginInfo(vo);
		
		if(member.getUserid() < 0)
		{
			new Exception("회원이 없습니당");
		}
		
		return dao.loginInfo(vo);
	}
	
}
