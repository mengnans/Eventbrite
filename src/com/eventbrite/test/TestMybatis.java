package com.eventbrite.test;

import com.eventbrite.domain.Certificate;
import com.eventbrite.factory.SqlFactory;
import com.eventbrite.mapper.CertificateMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;

public class TestMybatis {

    @Test
    public void test(){
        CertificateMapper mapper = new CertificateMapper();
        System.out.println(mapper.update(new Certificate(1,"4","hihihi")));
    }
}
