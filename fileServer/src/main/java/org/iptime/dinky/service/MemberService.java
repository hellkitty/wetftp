package org.iptime.dinky.service;

import org.iptime.dinky.domain.Member;

public interface MemberService {

	public boolean registerMember(Member member);
	public void changePassword(Member member);
	public void removeMember(Member member);
	public void configMemberGrade(Member member);
	public Member memberLogin(Member member);
	
}
