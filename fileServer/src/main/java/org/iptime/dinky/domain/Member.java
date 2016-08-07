package org.iptime.dinky.domain;

import java.io.Serializable;

public class Member implements Serializable{

	private String memberId;
	private String memberPw;
	private String memberRePw;
	private int memberGrade;
	
	public Member() {
	}
	
	public Member(String memberId) {
		super();
		this.memberId = memberId;
	}
	
	public Member(String memberId, String memberPw, int memberGrade) {
		super();
		this.memberId = memberId;
		this.memberPw = memberPw;
		this.memberGrade = memberGrade;
	}

	public Member(String memberId, String memberPw, String memberRePw,
			int memberGrade) {
		super();
		this.memberId = memberId;
		this.memberPw = memberPw;
		this.memberRePw = memberRePw;
		this.memberGrade = memberGrade;
	}

	public String getMemberRePw() {
		return memberRePw;
	}

	public void setMemberRePw(String memberRePw) {
		this.memberRePw = memberRePw;
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

	public int getMemberGrade() {
		return memberGrade;
	}

	public void setMemberGrade(int memberGrade) {
		this.memberGrade = memberGrade;
	}

	@Override
	public String toString() {
		return "Member [memberId=" + memberId + ", memberPw=" + memberPw
				+ ", memberRePw=" + memberRePw + ", memberGrade=" + memberGrade
				+ "]";
	}

}
