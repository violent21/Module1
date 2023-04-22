package com.valentinDovbyshev.Module1.repo;

import com.valentinDovbyshev.Module1.models.AddNewTask;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface AddNewTaskRepository extends CrudRepository<AddNewTask, Long> {
    List<AddNewTask> findAllByOrderByIdDesc();
}
