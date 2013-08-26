package com.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jdbc.query.QueryDslJdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.qdslsample.meta.Mytable;
import com.example.qdslsample.meta.QMytable;
import com.mysema.query.sql.SQLQuery;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class SimpleTest {

    private static Logger logger = LogManager.getLogger();

    @Autowired
    private QueryDslJdbcTemplate qdslTemplate;

    @Test
    public void queryDslSample01() {
        logger.info("******** START ********");
        SQLQuery query = qdslTemplate.newSqlQuery();
        QMytable qMytable = QMytable.mytable;
        query.from(qMytable).where(qMytable.id.eq(Long.parseLong("1")));
        Mytable mytable = qdslTemplate.queryForObject(query, qMytable);

        // SQL statement will be executed.
        // But I can not get the resultSet as Mytable instance !
        logger.info("******** {}", mytable.getMyarray().toString());
    }

}
