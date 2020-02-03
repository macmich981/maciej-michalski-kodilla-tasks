package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DbServiceTest {

    @InjectMocks
    private DbService dbService;

    @Mock
    private TaskRepository taskRepository;

    @Test
    public void testGetAllTasks() {
        //Given
        Task task = new Task(1L, "test title", "test content");
        Task task1 = new Task(2L, "test title", "test content");
        List<Task> tasksStub = new ArrayList<>();
        tasksStub.add(task);
        tasksStub.add(task1);

        when(taskRepository.findAll()).thenReturn(tasksStub);

        //When
        List<Task> fetchedTaskList = dbService.getAllTasks();

        //Then
        assertEquals(2, fetchedTaskList.size());
    }

    @Test
    public void testGetTask() {
        //Given
        Task task = new Task(1L,"test title", "test content");

        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));

        //When
        Optional<Task> fetchedTaskById = dbService.getTask(1L);

        //Then
        assertTrue(fetchedTaskById.isPresent());
        assertEquals(task.getId(), fetchedTaskById.get().getId());
    }

    @Test
    public void testGetAllTasksWithEmptyList() {
        //Given
        when(taskRepository.findAll()).thenReturn(new ArrayList<>());

        //When
        List<Task> fetchedTaskList = dbService.getAllTasks();

        //Then
        assertEquals(0, fetchedTaskList.size());
    }

    @Test
    public void testSaveTask() {
        //Given
        Task task = new Task(1l, " test title", "test content");
        when(taskRepository.save(task)).thenReturn(task);

        //When
        Task testTask = dbService.saveTask(task);

        //Then
        assertEquals(task.getId(), testTask.getId());
        assertEquals(task.getTitle(), testTask.getTitle());
        assertEquals(task.getContent(), testTask.getContent());
    }

    @Test
    public void testDeleteTask() {
        //Given
        Task task = new Task(1l, " test title", "test content");
        when(taskRepository.save(task)).thenReturn(task);

        //When
        dbService.saveTask(task);
        int sizeAfterSave = dbService.getAllTasks().size();
        dbService.deleteTask(1L);
        int sizeAfterDelete = dbService.getAllTasks().size();

        //Then
        assertEquals(0, sizeAfterDelete - sizeAfterSave);
    }
}