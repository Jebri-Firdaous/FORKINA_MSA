package tn.esprit.taskmanagement;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
    Page<Task> findByStatus(Status status, Pageable pageable);

    Page<Task> findByDueDateBefore(LocalDateTime dueDate, Pageable pageable);

    Page<Task> findByDueDateAfter(LocalDateTime dueDate, Pageable pageable);

    Page<Task> findByStatusAndDueDateBefore(Status status, LocalDateTime dueDate, Pageable pageable);

    Page<Task> findByStatusAndDueDateAfter(Status status, LocalDateTime dueDate, Pageable pageable);

    Page<Task> findByDueDateBetween(LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);

    Page<Task> findByStatusAndDueDateBetween(Status status, LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);
}
