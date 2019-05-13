package com.bluetao;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableSwagger2Doc
public class MemberApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(MemberApplication.class,args);
    }
}
