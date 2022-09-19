package com.codemari.timezonefollowerrest.db;

import com.codemari.timezonefollowerrest.dao.ContactRepository;
import com.codemari.timezonefollowerrest.dao.FavouriteLocationRepository;
import com.codemari.timezonefollowerrest.dao.LocationRepository;
import com.codemari.timezonefollowerrest.model.AppUser;
import com.codemari.timezonefollowerrest.dao.UserRepository;
import com.codemari.timezonefollowerrest.model.Contact;
import com.codemari.timezonefollowerrest.model.FavouriteLocation;
import com.codemari.timezonefollowerrest.model.Location;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class LoadDataBase {
    private final static Logger log = LoggerFactory.getLogger(LoadDataBase.class);

    @Bean
    CommandLineRunner initDataBase(UserRepository repository, LocationRepository locationRepository,
                                   ContactRepository contactRepository, FavouriteLocationRepository favouriteLocationRepository) {
        return args -> {
            log.info("Preloading location "
                    + locationRepository.save(new Location().setCity("Vancouver").setCountry("Canada").setRegion("BC").setTimeZone("America/Vancouver")));
            log.info("Preloading location "
                    + locationRepository.save(new Location().setCity("New York").setCountry("US").setRegion("New York").setTimeZone("America/New_York")));
            log.info("Preloading user 1 "
                    + repository.save(new AppUser().setName("Marina").setEmail("email@email.com").setPhoneNumber("123123123")));
            log.info("Preloading user 2 "
                    + repository.save(new AppUser().setName("Joe").setEmail("joe@email.com").setPhoneNumber("123123124")));
            log.info("Preloading contacts "
                    + contactRepository.save(new Contact().setContactUser(repository.findByEmail("joe@email.com").getId()).setMainUser(repository.findByEmail("email@email.com"))));
            log.info("Preloading favourite location "
                    + favouriteLocationRepository
                    .save(new FavouriteLocation().setLocation(locationRepository.findByCityAndRegionAndCountry("New York", "New York", "US").getId())
                            .setAppUser(repository.findByEmail("email@email.com"))));
        };
    }
}
