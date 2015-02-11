package org.figis.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SqlSessionFactoryProvider {

	String CONFIG_FILE = "../figis-properties/mybatis/mybatis-figis-config.xml";

	@Produces
	@ApplicationScoped
	public SqlSessionFactory produceFactory() {
		try {
			InputStream inputStream = new FileInputStream(CONFIG_FILE);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			return sqlSessionFactory;
		} catch (IOException e) {
			throw new RuntimeException(e.getCause());
		}
	}
}