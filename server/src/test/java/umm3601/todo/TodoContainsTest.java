package umm3601.todo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.HashMap;

import org.junit.jupiter.api.Test;

import umm3601.todos.Todo;
import umm3601.todos.tDatabase;

public class TodoContainsTest {


  @Test
  public void testTodosByContains() throws IOException {
    tDatabase db = new tDatabase("/todos.json");
    Todo[] allTodos = db.listTodos(new HashMap<>());

    Todo[] LaborumTodos = db.filterTodosByBody(allTodos, "Laborum");
    assertEquals(5, LaborumTodos.length, "Incorrect number of users with string in body: 'Laborum'");

    Todo[] magnaTodos = db.filterTodosByBody(allTodos, "sunt");
    assertEquals(85, magnaTodos.length, "Incorrect number of users with category: 'magna'");
  }

}
