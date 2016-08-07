package org.iptime.dinky.service;

import org.iptime.dinky.domain.Member;
import org.iptime.dinky.domain.ReturnMsgObj;

public interface MemberService {

	public ReturnMsgObj registerMember(Member member);
	public void changePassword(Member member);
	public void removeMember(Member member);
	public void configMemberGrade(Member member);
	public Member memberLogin(Member member);
	
}
