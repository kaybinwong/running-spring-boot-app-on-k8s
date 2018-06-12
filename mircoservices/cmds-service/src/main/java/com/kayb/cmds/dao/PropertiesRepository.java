package com.kayb.cmds.dao;

import com.kayb.cmds.domain.Properties;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author huangkaibin
 * @date 2018-05-29
 **/
public interface PropertiesRepository extends JpaRepository<Properties, Long> {

    /**
     *  根据手机查找客户信息
     */
    List<Properties> findByCustId(Long custId);
}
