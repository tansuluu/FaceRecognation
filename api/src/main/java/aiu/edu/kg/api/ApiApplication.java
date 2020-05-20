package aiu.edu.kg.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

//@EntityScan({
//        "kg.nurtelecom.verificationsystem.subscriber.entity",
//        "kg.nurtelecom.verificationsystem.dictionary.entity",
//        "kg.nurtelecom.verificationsystem.tunduk.entity"
//})
//@EnableJpaRepositories({
//        "kg.nurtelecom.verificationsystem.subscriber.repository",
//        "kg.nurtelecom.verificationsystem.dictionary.repository",
//        "kg.nurtelecom.verificationsystem.tunduk.repository"
//})
//@ComponentScan({
//        "kg.nurtelecom.verificationsystem.api",
//        "kg.nurtelecom.verificationsystem.subscriber.service",
//        "kg.nurtelecom.verificationsystem.subscriber.exception",
//        "kg.nurtelecom.verificationsystem.dictionary.service",
//        "kg.nurtelecom.verificationsystem.subscriber.dao",
//        "kg.nurtelecom.verificationsystem.subscriber.component",
//        "kg.nurtelecom.verificationsystem.subscriber.stage",
//        "kg.nurtelecom.verificationsystem.subscriber.contract",
//        "kg.nurtelecom.verificationsystem.common.helper",
//        "kg.nurtelecom.verificationsystem.tunduk.service",
//        "kg.nurtelecom.verificationsystem.tunduk.config"
//})
@EnableCaching
@SpringBootApplication
@EnableScheduling
public class ApiApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ApiApplication.class);
    }

}


