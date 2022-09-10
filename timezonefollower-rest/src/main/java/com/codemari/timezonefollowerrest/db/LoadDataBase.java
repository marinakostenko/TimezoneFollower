package com.codemari.timezonefollowerrest.db;

import com.codemari.timezonefollowerrest.datamodels.AppUser;
import com.codemari.timezonefollowerrest.repositories.UserRepository;
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
            log.info("Preloading "
                    + repository.save(new AppUser("Marina", "US", "123456789",
                    "Canada/Pacific")));
            log.info("Preloading "
                    + repository.save(new AppUser("John", "UK", "223456789",
                    "Europe/London")));
        };
    }
}
