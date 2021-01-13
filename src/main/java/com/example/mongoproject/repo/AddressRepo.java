/* mbor1 created on 12.01.2021 
inside the package - com.example.mongoproject */


package com.example.mongoproject.repo;

import com.example.mongoproject.model.Address;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepo extends MongoRepository<Address, String> {
}
