package com.eventbrite.mapper;

import org.apache.ibatis.annotations.Param;
import com.eventbrite.domain.Certificate;
public interface ICertificateMapper {
    int save(@Param("certificate")Certificate certificate);
    Certificate search(@Param("certificate")Certificate certificate);
    int update(@Param("certificate")Certificate certificate);
}
