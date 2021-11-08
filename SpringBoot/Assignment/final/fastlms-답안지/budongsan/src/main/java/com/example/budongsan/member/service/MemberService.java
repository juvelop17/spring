package com.example.budongsan.member.service;

import com.example.budongsan.member.dto.MemberDto;
import com.example.budongsan.member.model.MemberInput;
import com.example.budongsan.member.model.MemberParam;
import com.example.budongsan.model.ServiceResult;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface MemberService extends UserDetailsService {

    boolean register(MemberInput parameter);


    /**
     * 회원 목록 리턴(관리자에서만 사용 가능)
     */
    List<MemberDto> list(MemberParam parameter);

    /**
     * 회원 상세 정보
     */
    MemberDto detail(String userId);

    /**
     * 회원 상태 변경
     */
    boolean updateStatus(String userId, String userStatus);

    /**
     * 회원정보 수정
     */
    ServiceResult updateMember(MemberInput parameter);

    /**
     * 회원을 탈퇴시켜 주는 로직
     */
    ServiceResult withdraw(String userId, String password);

}
