package com.green.boardauth.appilcation.user;

import com.green.boardauth.appilcation.user.model.UserGetOneRes;
import com.green.boardauth.appilcation.user.model.UserSignInReq;
import com.green.boardauth.appilcation.user.model.UserSignUpReq;
import com.green.boardauth.configuration.model.JwtUser;
import com.green.boardauth.configuration.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.rmi.server.UID;

@Slf4j
@Service
@RequiredArgsConstructor

public class UserService {
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
//    private final JwtTokenProvider jwtTokenProvider;

    public int signUp(UserSignUpReq req) {
        String hashedPw = passwordEncoder.encode(req.getUpw());
        log.info("hashedPw: {}",hashedPw);
        req.setUpw(hashedPw);
        return userMapper.signUp(req);
    }

    public int signIn(UserSignInReq req) {
        UserGetOneRes res = userMapper.findByUid(req.getUid());
        log.info("res: {}", res);
        if(!passwordEncoder.matches(req.getUpw(), res.getUpw())) {
            return 0;
        }
        //로그인 성공 예전에는 at, rt를 fe에 전달 우리는 보안쿠키를 이용할거라 이건 ...
//        JwtUser jwtUser = new JwtUser(res.getId());
//        String accessToken = jwtTokenProvider.generateAccessToken(jwtUser);
//        String refreshToken = jwtTokenProvider.generateRefreshToken(jwtUser);


        return 1;
    }

}
