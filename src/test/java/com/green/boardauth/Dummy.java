package com.green.boardauth;


import net.datafaker.Faker;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.test.annotation.Rollback;

import java.util.Locale;

@MybatisTest
@AutoConfigureTestDatabase (replace = AutoConfigureTestDatabase.Replace.NONE)
//Test는 트랜잭션으로 작동하여 테스트가 끝나면 Rollback시킨다. 우리는 실제로 데이터를 insert
// 해야하기 때문에 rollback을 끈다.

@Rollback(false)
public class Dummy {
    @Autowired //DI
    protected SqlSessionFactory sqlSessionFactory;

    protected Faker koFaker = new Faker(new Locale("ko")); //한글
    protected Faker enFaker = new Faker(new Locale("en")); //영어
}
