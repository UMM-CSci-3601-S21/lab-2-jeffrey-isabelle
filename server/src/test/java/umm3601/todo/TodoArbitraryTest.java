package umm3601.todo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.HashMap;

import org.junit.jupiter.api.Test;

import umm3601.todos.Todo;
import umm3601.todos.tDatabase;

public class TodoArbitraryTest {

  @Test
  public void testTodosByArbitrary() throws IOException {
    tDatabase db = new tDatabase("/todos.json");
    Todo[] allTodos = db.listTodos(new HashMap<>());

    Todo[] ArbTodos = db.filterTodosByOwner(allTodos, "Barry");
    ArbTodos = db.filterTodosByCategory(ArbTodos, "video games");
    ArbTodos = db.filterTodosByStatus(ArbTodos, false);
    ArbTodos = db.filterTodosByBody(ArbTodos, "Ad");
    assertEquals(1, ArbTodos.length, "Incorrect length");


  }

}
