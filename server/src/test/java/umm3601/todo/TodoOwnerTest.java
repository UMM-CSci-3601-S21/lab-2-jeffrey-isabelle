package umm3601.todo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.HashMap;

import org.junit.jupiter.api.Test;

import umm3601.todos.Todo;
import umm3601.todos.tDatabase;

public class TodoOwnerTest {

  @Test
  public void testTodosByOwner() throws IOException {
    tDatabase db = new tDatabase("/todos.json");
    Todo[] allTodos = db.listTodos(new HashMap<>());

    Todo[] BlancheTodos = db.filterTodosByOwner(allTodos, "Blanche");
    assertEquals(43, BlancheTodos.length, "Incorrect length of 'Blanche' Todos");

    Todo[] FryTodos = db.filterTodosByOwner(allTodos, "Fry");
    assertEquals(61, FryTodos.length, "Incorrect length of 'Fry' Todos");

    Todo[] NicMcpheeTodos = db.filterTodosByOwner(allTodos, "Nic Mcphee");
    assertEquals(0, NicMcpheeTodos.length, "Incorrect length of 'Nic Mcphee' Todos");

}
}
