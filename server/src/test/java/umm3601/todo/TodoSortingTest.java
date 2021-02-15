package umm3601.todo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.HashMap;

import org.junit.jupiter.api.Test;

import umm3601.todos.Todo;
import umm3601.todos.tDatabase;

public class TodoSortingTest {

  @Test
  public void testTodosBySorting() throws IOException {
    tDatabase db = new tDatabase("/todos.json");
    Todo[] allTodos = db.listTodos(new HashMap<>());

    Todo[] OwnerTodos = db.sortedTodos(allTodos, "owner");
    Todo firstTodoOwner = OwnerTodos[0];
    assertEquals("Barry", firstTodoOwner.owner, "Incorrect first todo owner");

    Todo[] BodyTodos = db.sortedTodos(allTodos, "body");
    Todo firstTodoBody = BodyTodos[0];
    assertEquals("Ad sint incididunt officia veniam incididunt. Voluptate exercitation eu aliqua laboris occaecat deserunt cupidatat velit nisi sunt mollit sint amet.", firstTodoBody.body, "Incorrect first todo body");

    Todo[] CatTodos = db.sortedTodos(allTodos, "category");
    Todo firstTodoCat = CatTodos[0];
    assertEquals("groceries", firstTodoCat.category, "Incorrect first todo category");

}
}
