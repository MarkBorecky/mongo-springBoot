package com.example.mongoproject.resource;

import com.example.mongoproject.model.Address;
import com.example.mongoproject.model.User;
import com.example.mongoproject.repo.AddressRepo;
import com.example.mongoproject.repo.User.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AddressController {
    public static final Logger log = LoggerFactory.getLogger(AddressController.class);

    @Autowired
    AddressRepo addressRepo;

    @Autowired
    UserRepo userRepo;

    @GetMapping("/addresses")
    public List<Address> getAddresses() {
        return addressRepo.findAll();
    }

    @GetMapping("/users")
    public Page<User> getUsers() {
        log.debug("invoke -> '/api/users'");
        Pageable pageable = PageRequest.of(0,2, Sort.by("address.city"));
        return userRepo.custom(pageable);
    }

    @GetMapping("/test")
    public String hello() {
        return "Hello";
    }
}
