package com.valentinDovbyshev.Module1.repo;

import com.valentinDovbyshev.Module1.models.AddNewUser;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AddNewUserRepository extends CrudRepository<AddNewUser, Long> {
    List<AddNewUser> findAllByOrderByIdDesc();
}
