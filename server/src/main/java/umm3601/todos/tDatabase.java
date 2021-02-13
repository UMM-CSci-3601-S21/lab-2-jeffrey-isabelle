package umm3601.todos;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import io.javalin.http.BadRequestResponse;

public class tDatabase {


  private Todo[] allTodo;

  public tDatabase(String todoDataFile) throws IOException {
    Gson gson = new Gson();
    InputStreamReader reader = new InputStreamReader(getClass().getResourceAsStream(todoDataFile));
    allTodo = gson.fromJson(reader, Todo[].class);
  }

  public int size() {
    return allTodo.length;
  }

  public Todo getTodo(String id) {
    return Arrays.stream(allTodo).filter(x -> x._id.equals(id)).findFirst().orElse(null);
  }

  public Todo[] listTodos(Map<String, List<String>> queryParams){
    Todo[] filteredTodo = allTodo;

    if (queryParams.containsKey("category")) {
      String targetCategory = queryParams.get("category").get(0);
      filteredTodo = filterTodosByCategory(filteredTodo, targetCategory);
    }
    return filteredTodo;
  }

  public Todo[] filterTodosByCategory(Todo[] todo, String targetCategory) {
    return Arrays.stream(todo).filter(x -> x.category.equals(targetCategory)).toArray(Todo[]::new);
  }

}
