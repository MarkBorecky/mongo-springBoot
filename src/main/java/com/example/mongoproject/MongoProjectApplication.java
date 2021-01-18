package com.example.mongoproject;

import com.example.mongoproject.model.Address;
import com.example.mongoproject.model.User;
import com.example.mongoproject.repo.AddressRepo;
import com.example.mongoproject.repo.User.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MongoProjectApplication implements CommandLineRunner {

    @Autowired
    UserRepo userRepo;

    @Autowired
    AddressRepo addressRepo;

    public static void main(String[] args) {
        SpringApplication.run(MongoProjectApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Address address = new Address("Lisboa", "xxxx");
        User user = new User("Test", address);
        addressRepo.save(address);
        userRepo.save(user);
    }
}
