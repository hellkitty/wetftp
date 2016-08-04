package org.iptime.dinky.persistance;

import org.iptime.dinky.domain.Member;

public interface MemberDAO {

	public int registerMember(Member member);
	public void changePassword(Member member);
	public void removeMember(Member member);
	public void configMemberGrade(Member member);
	public Member getMemberInfo(Member member);
	
}
