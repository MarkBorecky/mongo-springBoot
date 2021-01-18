/* mbor1 created on 13.01.2021 
inside the package - com.example.mongoproject.repo.User */


package com.example.mongoproject.repo.User;

import com.example.mongoproject.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserRepoCustom {
    Page<User> custom(Pageable pageable);
}
