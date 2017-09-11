package com.eventbrite.test;

import com.eventbrite.domain.Certificate;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.InputStream;

public class TestMybatis {

    @Test
    public void test(){
        String resource = "mybatis.xml";
        InputStream is = TestMybatis.class.getClassLoader().getResourceAsStream(resource);
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession session = sessionFactory.openSession();
        String statement = "com.eventbrite.mapping.certificateMapper.getUser";//映射sql的标识字符串
        //执行查询返回一个唯一user对象的sql
        Certificate certificate = session.selectOne(statement, 1);
        System.out.println(certificate);
    }
}
