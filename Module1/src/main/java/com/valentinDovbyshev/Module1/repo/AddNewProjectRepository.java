package com.valentinDovbyshev.Module1.repo;

import com.valentinDovbyshev.Module1.models.AddNewProject;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface AddNewProjectRepository extends CrudRepository<AddNewProject, Long> {
    List<AddNewProject> findAllByOrderByIdDesc();
}
