package org.iptime.dinky.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.iptime.dinky.domain.Member;
import org.iptime.dinky.persistance.MemberDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	MemberDAO dao;
	
	@Override
	public boolean registerMember(Member member) {
		member.setMemberPw(makeSHA256(member.getMemberPw()));
		return dao.registerMember(member)==1 ? true : false;
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
