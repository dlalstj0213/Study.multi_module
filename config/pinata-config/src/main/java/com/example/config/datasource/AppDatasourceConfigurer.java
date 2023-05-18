package com.example.config.datasource;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class AppDatasourceConfigurer {
    /* JPA 관련 설정 */
    @PersistenceContext(unitName = "primaryEntityManager")
    private EntityManager primaryEntityManager;

    @PersistenceContext(unitName = "secondEntityManager")
    private EntityManager secondEntityManager;

    /* QueryDsl 관련 설정 */
//    @Primary
//    @Bean
//    public JPAQueryFactory primaryQueryFactory() {
//        return new JPAQueryFactory(primaryEntityManager);
//    }
//
//    @Bean
//    @Qualifier("SecondQueryFactory")
//    public JPAQueryFactory rawDataQueryFactory() {
//        return new JPAQueryFactory(secondEntityManager);
//    }

//    @Autowired
//    public void setQueryFactory(@Qualifier("SecondQueryFactory") JPAQueryFactory queryFactory) {
//        this.queryFactory = queryFactory;
//    }
}