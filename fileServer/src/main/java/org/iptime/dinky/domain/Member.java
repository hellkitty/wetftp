package org.iptime.dinky.domain;

import java.io.Serializable;

public class Member implements Serializable{

	private String memberId;
	private String memberPw;
	private String memberGrade;
	
	public Member() {
	}

	public Member(String memberId, String memberPw, String memberGrade) {
		super();
		this.memberId = memberId;
		this.memberPw = memberPw;
		this.memberGrade = memberGrade;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getMemberPw() {
		return memberPw;
	}

	public void setMemberPw(String memberPw) {
		this.memberPw = memberPw;
	}

	public String getMemberGrade() {
		return memberGrade;
	}

	public void setMemberGrade(String memberGrade) {
		this.memberGrade = memberGrade;
	}
	
	
}
