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
import com.mysema.query.Tuple;
import com.mysema.query.sql.SQLQuery;
import com.mysema.query.types.MappingProjection;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class SimpleTest {

    private static Logger logger = LogManager.getLogger();

    @Autowired
    private QueryDslJdbcTemplate qdslTemplate;

    @Test
    public void queryDslSample01() {
        logger.info("******** queryDslSample01 START ********");
        SQLQuery query = qdslTemplate.newSqlQuery();
        QMytable qMytable = QMytable.mytable;
        query.from(qMytable).where(qMytable.id.eq(Long.parseLong("1")));
        Mytable mytable = qdslTemplate.queryForObject(query, qMytable);

        // SQL statement will be executed.
        // But I can not get the resultSet as Mytable instance !
        logger.info("******** queryDslSample01 END {}", mytable.getMyarray()
                .toString());
    }

    @Test
    public void queryDslSample02() {
        logger.info("******** START ********");
        SQLQuery query = qdslTemplate.newSqlQuery();
        QMytable qMytable = QMytable.mytable;
        query.from(qMytable).where(qMytable.id.eq(Long.parseLong("1")));
        Mytable mytable = qdslTemplate.queryForObject(query,
                new MappingMytableProjection(qMytable));
        logger.info("******** queryDslSample02 END {}", mytable.getMyarray()
                .toString());
    }

    private static class MappingMytableProjection extends
            MappingProjection<Mytable> {

        private static final long serialVersionUID = 1L;
        private static final QMytable qMytable = QMytable.mytable;

        public MappingMytableProjection(QMytable qMytable) {
            super(Mytable.class, qMytable.id, qMytable.myarray);
        }

        @Override
        protected Mytable map(Tuple tuple) {
            Mytable mytable = new Mytable();
            mytable.setId(tuple.get(qMytable.id));
            mytable.setMyarray(tuple.get(qMytable.myarray));
            return mytable;
        }

    }

}
