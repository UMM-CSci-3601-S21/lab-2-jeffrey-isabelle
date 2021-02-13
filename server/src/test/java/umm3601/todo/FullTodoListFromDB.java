package umm3601.todo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.HashMap;

import org.junit.jupiter.api.Test;

import umm3601.todos.Todo;
import umm3601.todos.tDatabase;

/**
 * Tests umm3601.user.Database listUser functionality
 */
public class FullTodoListFromDB {

  @Test
  public void totalTodoCount() throws IOException {
    tDatabase db = new tDatabase("/todos.json");
    Todo[] allTodo = db.listTodos(new HashMap<>());
    assertEquals(db.size(), allTodo.length, "Incorrect total number of users");
  }

  @Test
  public void firstUserInFullList() throws IOException {
    tDatabase db = new tDatabase("/todos.json");
    Todo[] allTodo = db.listTodos(new HashMap<>());
    Todo firstTodo = allTodo[0];
    assertEquals("Blanche", firstTodo.owner, "Incorrect name");
    assertEquals("In sunt ex non tempor cillum commodo amet incididunt anim qui commodo quis. Cillum non labore ex sint esse.", firstTodo.body, "Incorrect age");
    assertEquals("software design", firstTodo.category, "Incorrect category");

  }
}
