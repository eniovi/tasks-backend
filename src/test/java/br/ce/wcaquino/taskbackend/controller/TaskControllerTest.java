package br.ce.wcaquino.taskbackend.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.ce.wcaquino.taskbackend.model.Task;
import br.ce.wcaquino.taskbackend.repo.TaskRepo;
import br.ce.wcaquino.taskbackend.utils.ValidationException;

public class TaskControllerTest {

	@Mock
	private TaskRepo taskRepo;

	@InjectMocks
	private TaskController controller;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void shouldNotSaveTaskWithoutDate() {
		final Task todo = new Task();
		todo.setTask("Descricao");

		try {
			controller.save(todo);
			fail("Shouldn't get here!");
		} catch (final ValidationException e) {
			assertEquals("Fill the due date", e.getMessage());
		}
	}

	@Test
	public void shouldNotSaveTaskWithoutDescription() {
		final Task todo = new Task();
		todo.setDueDate(LocalDate.now());

		try {
			controller.save(todo);
			fail("Shouldn't get here!");
		} catch (final ValidationException e) {
			assertEquals("Fill the task description", e.getMessage());
		}
	}

	@Test
	public void shouldNotSaveTaskWithPastDate() {
		final Task todo = new Task();
		todo.setTask("Descricao");
		todo.setDueDate(LocalDate.now().minusDays(1));

		try {
			controller.save(todo);
			fail("Shouldn't get here!");
		} catch (final ValidationException e) {
			assertEquals("Due date must not be in past", e.getMessage());
		}
	}

	@Test
	public void shouldSaveTaskWithSuccess() throws ValidationException {
		final Task todo = new Task();
		todo.setTask("Descricao");
		todo.setDueDate(LocalDate.now());
		controller.save(todo);

		Mockito.verify(taskRepo).save(todo);
	}

}
