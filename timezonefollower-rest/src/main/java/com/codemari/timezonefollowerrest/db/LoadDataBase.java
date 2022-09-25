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

import java.util.Optional;


@Configuration
public class LoadDataBase {
    private final static Logger log = LoggerFactory.getLogger(LoadDataBase.class);

    @Bean
    CommandLineRunner initDataBase(UserRepository repository, LocationRepository locationRepository,
                                   ContactRepository contactRepository, FavouriteLocationRepository favouriteLocationRepository) {
        return args -> {

            Optional<AppUser> appUser1 = repository.findByEmail("email@email.com");
            if (appUser1.isEmpty()) {
                AppUser user1 = new AppUser().setName("Marina").setEmail("email@email.com").setPhoneNumber("123123123").setIsActive(true);
                Optional<Location> user1Location = locationRepository.findByCityAndCountry("Vancouver", "Canada");
                user1Location.ifPresent(user1::setLocation);

                log.info("Preloading user 1 "
                        + repository.save(user1));
            }

            Optional<AppUser> appUser2 = repository.findByEmail("joe@email.com");
            if (appUser2.isEmpty()) {
                AppUser user2 = new AppUser().setName("Joe").setEmail("joe@email.com").setPhoneNumber("123123124").setIsActive(true);
                Optional<Location> user2Location = locationRepository.findByCityAndCountry("New York City", "United States");
                user2Location.ifPresent(user2::setLocation);
                log.info("Preloading user 2 "
                        + repository.save(user2));
            }

            Optional<AppUser> mainUser = repository.findByEmail("email@email.com");
            Optional<AppUser> contactUser = repository.findByEmail("joe@email.com");

            if (mainUser.isPresent() && contactUser.isPresent()) {
                Optional<Contact> contact1find = contactRepository.findByContactUserAndMainUser(contactUser.get().getId(), mainUser.get());

                if(contact1find.isEmpty()) {
                    Contact contact1 = new Contact().setContactUser(contactUser.get().getId()).setMainUser(mainUser.get());
                    log.info("Preloading contacts "
                            + contactRepository.save(contact1));
                }
            }


            Optional<AppUser> favLocationUser1 = repository.findByEmail("email@email.com");
            Optional<Location> favLocation1 = locationRepository.findByCityAndCountry("New York City", "United States");

            if(favLocationUser1.isPresent() && favLocation1.isPresent()) {
               Optional<FavouriteLocation> favouriteLocation1 = favouriteLocationRepository.findByLocationAndAppUser(favLocation1.get().getId(), favLocationUser1.get());

               if(favouriteLocation1.isEmpty()) {
                   log.info("Preloading favourite location "
                           + favouriteLocationRepository
                           .save(new FavouriteLocation().setLocation(favLocation1.get().getId()).setAppUser(favLocationUser1.get())));
               }
            }
        };
    }
}
