package org.iptime.dinky.persistance;

import org.apache.ibatis.session.SqlSession;
import org.iptime.dinky.domain.Member;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAOImpl implements MemberDAO {

	@Autowired
	SqlSession session;
	
	private static Logger logger = LoggerFactory.getLogger(MemberDAOImpl.class);
	
	private static final String namespace="org.iptime.dinky.mapper.MemberMapper";
	
	@Override
	public int registerMember(Member member) {
		return session.insert(namespace+".registerMember", member);
	}

	@Override
	public void changePassword(Member member) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeMember(Member member) {
		// TODO Auto-generated method stub

	}

	@Override
	public void configMemberGrade(Member member) {
		// TODO Auto-generated method stub

	}

	@Override
	public Member getMemberInfo(Member member) {
		Member returnMember = new Member();
		try {
			returnMember = session.selectOne(namespace+".getMemberInfo", member);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return returnMember;
	}

	@Override
	public int checkMemberId(Member member) {
		return session.selectOne(namespace+".checkMemberId", member);
	}

}
