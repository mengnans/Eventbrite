package com.eventbrite.mapper;

import com.eventbrite.domain.Certificate;
import com.eventbrite.factory.SqlFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class CertificateMapper implements ICertificateMapper {
    private static SqlSession session;
    public CertificateMapper(){
        SqlSessionFactory factory = SqlFactory.getSessionFactory();
        session = factory.openSession();
    }

    @Override
    public int save(Certificate certificate) {

        String statement = "com.eventbrite.mapping.certificateMapper.save";
        int result = session.insert(statement, certificate);
        session.commit();
        return result;
    }

    @Override
    public Certificate search(Certificate certificate) {
        String statement = "com.eventbrite.mapping.certificateMapper.search";
        Certificate result = session.selectOne(statement, certificate);
        return result;
    }

    @Override
    public int update(Certificate certificate) {
        String statement = "com.eventbrite.mapping.certificateMapper.update";
        int result = session.update(statement, certificate);
        session.commit();
        return result;
    }
}
