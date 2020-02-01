package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class TaskMapperTest {

    @InjectMocks
    private TaskMapper taskMapper;

    @Test
    public void testMapToTask() {
        //Given
        TaskDto taskDto = new TaskDto(1L, "Task title", "Task content");

        //When
        Task task = taskMapper.mapToTask(taskDto);

        //Then
        assertEquals(1L, task.getId().longValue());
        assertEquals("Task title", task.getTitle());
        assertEquals("Task content", task.getContent());
    }

    @Test
    public void testMapToTaskDto() {
        //Given
        Task task = new Task(1L, "Task title", "Task content");

        //When
        TaskDto taskDto = taskMapper.mapToTaskDto(task);

        //Then
        assertEquals(1L, taskDto.getId().longValue());
        assertEquals("Task title", taskDto.getTitle());
        assertEquals("Task content", taskDto.getContent());
    }

    @Test
    public void testMapTpTaskDtoList() {
        //Given
        Task task = new Task(1L, "Task title", "Task content");
        Task task1 = new Task(2L, "Task title", "Task content");
        List<Task> tasks = new ArrayList<>();
        tasks.add(task);
        tasks.add(task1);

        //When
        List<TaskDto> taskDtos = taskMapper.mapToTaskDtoList(tasks);

        //Then
        assertEquals(2, taskDtos.size());
    }
}