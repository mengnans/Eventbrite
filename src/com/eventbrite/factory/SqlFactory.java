package com.eventbrite.factory;

import com.eventbrite.test.TestMybatis;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

public class SqlFactory {
    private static SqlSessionFactory sessionFactory;
    public static SqlSessionFactory getSessionFactory() {
        if(sessionFactory == null){
            String resource = "mybatis.xml";
            InputStream is = TestMybatis.class.getClassLoader().getResourceAsStream(resource);
            sessionFactory = new SqlSessionFactoryBuilder().build(is);
        }
        return sessionFactory;
    }

}
