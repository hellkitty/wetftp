package org.iptime.dinky.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.iptime.dinky.domain.Member;
import org.iptime.dinky.domain.ReturnMsgObj;
import org.iptime.dinky.persistance.MemberDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	MemberDAO dao;
	
	@Override
	public ReturnMsgObj registerMember(Member member) {
		if(dao.checkMemberId(new Member(member.getMemberId()))!=0){
			return new ReturnMsgObj(false, "Register fail. Id already exist. Use another Id.");
		}
		if(!member.getMemberPw().equals(member.getMemberRePw())){
			return new ReturnMsgObj(false, "Register fail. Check your password. You must write same password at both input form.");
		}
		if(member.getMemberId()!=null){
			member.setMemberPw(makeSHA256(member.getMemberPw()));
			return dao.registerMember(member)==1 
					? new ReturnMsgObj(true, "Register success.") : new ReturnMsgObj(false, "Register fail. Try again.");
		}
		
		return new ReturnMsgObj(false, "Id is empty, write Id to use.");
	}

	@Override
	public void changePassword(Member member) {
		dao.changePassword(member);
	}

	@Override
	public void removeMember(Member member) {
		dao.removeMember(member);
	}

	@Override
	public void configMemberGrade(Member member) {
		dao.configMemberGrade(member);
	}

	@Override
	public Member memberLogin(Member member) {
		member.setMemberPw(makeSHA256(member.getMemberPw()));
		
		return dao.getMemberInfo(member);
	}

	
	public String makeSHA256(String str){
		String SHA = ""; 
		try{
			MessageDigest sh = MessageDigest.getInstance("SHA-256"); 
			sh.update(str.getBytes()); 
			byte byteData[] = sh.digest();
			StringBuffer sb = new StringBuffer(); 
			for(int i = 0 ; i < byteData.length ; i++){
				sb.append(Integer.toString((byteData[i]&0xff) + 0x100, 16).substring(1));
			}
			SHA = sb.toString();
			
		}catch(NoSuchAlgorithmException e){
			e.printStackTrace(); 
			SHA = null; 
		}
		return SHA;
	}
}
