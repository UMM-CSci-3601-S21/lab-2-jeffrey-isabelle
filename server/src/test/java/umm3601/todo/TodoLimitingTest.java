package umm3601.todo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.HashMap;

import org.junit.jupiter.api.Test;

import umm3601.todos.Todo;
import umm3601.todos.tDatabase;

public class TodoLimitingTest {

  @Test
  public void testTodosByLimit() throws IOException {
    tDatabase db = new tDatabase("/todos.json");
    Todo[] allTodos = db.listTodos(new HashMap<>());

    Todo[] limitTodosSeven = db.filterTodosByLimit(allTodos, 7);
    assertEquals(7, limitTodosSeven.length, "Incorrect length of Todos");

    Todo[] limitTodosTwentyOne = db.filterTodosByLimit(allTodos, 21);
    assertEquals(21, limitTodosTwentyOne.length, "Incorrect length of Todos");

}
}
