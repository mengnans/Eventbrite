package com.eventbrite.mapper;

import org.apache.ibatis.annotations.Param;
import com.eventbrite.domain.Certificate;
public interface ICertificateMapper {
    void save(@Param("certificate")Certificate certificate);
    void search(@Param("certificate")Certificate certificate);
}
