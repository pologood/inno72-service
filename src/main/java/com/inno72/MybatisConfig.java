package com.inno72;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import com.github.miemiedev.mybatis.paginator.OffsetLimitInterceptor;
import com.inno72.utils.page.PagePlugin;

@Configuration
public class MybatisConfig {

    @Autowired
    private DataSource dataSource;

    @Value(value = "${mybatis.type-handlers-package}")
    private String typeHandlersPackage;

    @Value(value = "${mybatis.mapper-locations}")
    private String xmlPath;

    @Bean
    public SqlSessionFactory sqlSessionFactory() {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setTypeHandlersPackage(typeHandlersPackage);

        Properties props = new Properties();
        props.setProperty("dialect", "mysql");
        props.setProperty("pageSqlId", "(ByPage|ForPage)");
        PagePlugin pagePlugin = new PagePlugin();
        pagePlugin.setProperties(props);

        OffsetLimitInterceptor offsetLimitInterceptor = new OffsetLimitInterceptor();
        Properties offsetLimitProps = new Properties();
        offsetLimitProps.setProperty("dialectClass", "com.github.miemiedev.mybatis.paginator.dialect.MySQLDialect");
        offsetLimitInterceptor.setProperties(offsetLimitProps);

        bean.setPlugins(new Interceptor[]{offsetLimitInterceptor, pagePlugin});

        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

        try {
            bean.setMapperLocations(resolver.getResources(xmlPath));
            return bean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}