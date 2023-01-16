package com.example.Project393.Service;

import com.example.Project393.Model.Member;
import com.example.Project393.Repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    @Autowired
    MemberRepository memberRepository;

    public void saveMember(Member member){
        memberRepository.save(member);
    }

    public void deleteMember(Member member){
        memberRepository.delete(member);
    }

    public void deleteAll(){
        memberRepository.deleteAll();
    }

    public List<Member> readAll(){
        return memberRepository.findAll();
    }

    public Member readOne(int id){
        return memberRepository.findByID(id);
    }
}
