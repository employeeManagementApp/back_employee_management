package com.javaadr.renderapi.integration;

import com.javaadr.renderapi.Entity.Project;
import com.javaadr.renderapi.Repository.ProjectRepository;
import com.javaadr.renderapi.Service.ProjectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ProjectIT {
    @Mock
    private ProjectRepository projectRepository;

    private ProjectService projectService;
    @BeforeEach
    public void setUp() {
        projectService = new ProjectService(projectRepository);
    }
    @Test
    public void testGetProjects() {

        Project project1 = new Project(1, "Project1", Time.valueOf("10:00:00"), new Timestamp(System.currentTimeMillis()), true);
        Project project2 = new Project(2, "Project2", Time.valueOf("20:00:00"), new Timestamp(System.currentTimeMillis()), false);


        when(projectRepository.findAll()).thenReturn(Arrays.asList(project1, project2));


        List<Project> projects = projectService.getProjects();


        assertThat(projects).isNotNull();
        assertThat(projects).hasSize(2);
        assertThat(projects.get(0).getName()).isEqualTo("Project1");
        assertThat(projects.get(1).getDuration()).isEqualTo(Time.valueOf("20:00:00"));

    }
    @Test
    public void testGetOneProject() {
        int projectId = 1;
        Project project = new Project(projectId, "Project1", Time.valueOf("10:00:00"), new Timestamp(System.currentTimeMillis()), true);

        when(projectRepository.findById(projectId)).thenReturn(Optional.of(project));

        Optional<Project> optionalProject = projectService.getOne(projectId);

        assertThat(optionalProject).isPresent();
        Project retrievedProject = optionalProject.get();
        assertThat(retrievedProject.getId()).isEqualTo(projectId);
        assertThat(retrievedProject.getName()).isEqualTo("Project1");
    }
}
