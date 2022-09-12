package com.codemari.timezonefollowerrest.db;

import com.codemari.timezonefollowerrest.model.AppUser;
import com.codemari.timezonefollowerrest.dao.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class LoadDataBase {
    private final static Logger log = LoggerFactory.getLogger(LoadDataBase.class);

    @Bean
    CommandLineRunner initDataBase(UserRepository repository) {
        return args -> {
//            log.info("Preloading "
//                    + repository.save(new AppUser("Marina", "123@gmail.com", "New York", "NY", "US", "123456789", true)));
//            log.info("Preloading "
//                    + repository.save(new AppUser("John", "321@gmail.com", "London", "London", "UK", "223456789", true)));
        };
    }
}
