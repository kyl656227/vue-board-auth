package com.green.boardauth.appilcation.user;

import com.green.boardauth.appilcation.user.model.UserGetOneRes;
import com.green.boardauth.appilcation.user.model.UserSignUpReq;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    int signUp(UserSignUpReq req);

    UserGetOneRes findByUid(String uid);



}
