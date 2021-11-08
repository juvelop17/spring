package com.example.demo.web.infra;

import com.example.demo.domain.entity.BookOrder;
import com.example.demo.domain.entity.User;
import com.example.demo.domain.repository.UserRepository;
import com.example.demo.web.infra.exception.ExceptionCode;
import com.example.demo.web.infra.exception.ZeroBaseException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.swing.text.html.Option;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserArgumentResolver implements HandlerMethodArgumentResolver {

    private final String USER_HEADER = "X-USER-ID";
    private final UserRepository userRepository;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        // FIXME 조건 추가해보기

        return parameter.getParameterType().equals(User.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        // FIXME 유저 객체 조회하기
        return Optional.ofNullable(webRequest.getHeader(USER_HEADER))
                .map(Long::parseLong)
                .map(userRepository::findById)
                .orElseThrow(() -> new ZeroBaseException(ExceptionCode.INVALID_HEADER));

        // 안녕하세요. ArgumentResolver의 정확한 동작이 이해가 안되는데, 혹시 어떤자료를 참고하면 이해할 수 있을까요?
    }
}
