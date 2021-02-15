package umm3601.todo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.HashMap;

import org.junit.jupiter.api.Test;

import umm3601.todos.Todo;
import umm3601.todos.tDatabase;

public class TodoStatusTest {


  @Test
  public void testTodosByStatus() throws IOException {
    tDatabase db = new tDatabase("/todos.json");
    Todo[] allTodos = db.listTodos(new HashMap<>());

    Todo[] completeTodos = db.filterTodosByStatus(allTodos, true);
    assertEquals(143, completeTodos.length, "Incorrect number of todos that are complete");

    Todo[] incompleteTodos = db.filterTodosByStatus(allTodos, false);
    assertEquals(157, incompleteTodos.length, "Incorrect number of todos that are incomplete");
  }

}
