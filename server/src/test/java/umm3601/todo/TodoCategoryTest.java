package umm3601.todo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.HashMap;

import org.junit.jupiter.api.Test;

import umm3601.todos.Todo;
import umm3601.todos.tDatabase;

public class TodoCategoryTest {

  @Test
  public void testTodosByCategory() throws IOException {
    tDatabase db = new tDatabase("/todos.json");
    Todo[] allTodos = db.listTodos(new HashMap<>());

    Todo[] videoGameTodos = db.filterTodosByCategory(allTodos, "video games");
    assertEquals(71, videoGameTodos.length, "Incorrect number of todos with category: 'video games'");

    Todo[] homeworkTodos = db.filterTodosByCategory(allTodos, "homework");
    assertEquals(79, homeworkTodos.length, "Incorrect number of todos with category: 'homework'");
  }

}
