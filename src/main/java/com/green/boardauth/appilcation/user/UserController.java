package com.green.boardauth.appilcation.user;


import com.green.boardauth.appilcation.user.model.UserSignInReq;
import com.green.boardauth.appilcation.user.model.UserSignInRes;
import com.green.boardauth.appilcation.user.model.UserSignUpReq;
import com.green.boardauth.configuration.model.JwtUser;
import com.green.boardauth.configuration.model.ResultResponse;
import com.green.boardauth.configuration.security.JwtTokenManager;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;
    private final JwtTokenManager jwtTokenManager;

    @PostMapping("/signup")
    public ResultResponse<Integer> userPost(@RequestBody UserSignUpReq req) {
        log.info("req: {}", req);
        int result = userService.signUp(req);
        return new ResultResponse<>("회원가입 성공", result);
    }

    @PostMapping("/signin")
    public ResultResponse<?> sinIn(HttpServletResponse res,  @RequestBody UserSignInReq req){
        log.info("req2: {}", req);
        UserSignInRes userSignInRes = userService.signIn(req);
        // 보안 쿠키 처리
        if(userSignInRes !=null ){
            JwtUser jwtUser = new JwtUser( userSignInRes.getSignedUserId());
            jwtTokenManager.issue(res,jwtUser);
        }
        return new ResultResponse<>(userSignInRes == null ? "로그인 실패": "로그인 성공",userSignInRes );
    }

}
