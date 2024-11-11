package com.shop.test;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisUtil {
    private static String config="mybatis/mybatis.xml";
    static Reader reader;
    static{
        try {
            reader= Resources.getResourceAsReader(config);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader);
    
    public static SqlSessionFactory getSqlSessionFactory(){
    	return factory;
    }
    public static SqlSession getSession() throws IOException{
           SqlSession session = factory.openSession();
            return session;
    }
}