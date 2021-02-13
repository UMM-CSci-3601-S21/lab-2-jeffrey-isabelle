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



  //The main filter method

  public Todo[] listTodos(Map<String, List<String>> queryParams){
    Todo[] filteredTodo = allTodo;


    //Filter by category
    if (queryParams.containsKey("category")) {
      String targetCategory = queryParams.get("category").get(0);
      filteredTodo = filterTodosByCategory(filteredTodo, targetCategory);
    }

    //Filter by owner
    if (queryParams.containsKey("owner")) {
      String targetOwner = queryParams.get("owner").get(0);
      filteredTodo = filterTodosByOwner(filteredTodo, targetOwner);
    }

    //Filter by status
    if (queryParams.containsKey("status")) {
      boolean targetStatus = getBoolFromStatus(queryParams.get("status").get(0));
      filteredTodo = filterTodosByStatus(filteredTodo, targetStatus);
    }

    return filteredTodo;
  }



  // Filter methods

  public Todo[] filterTodosByCategory(Todo[] todo, String targetCategory) {
    return Arrays.stream(todo).filter(x -> x.category.equals(targetCategory)).toArray(Todo[]::new);
  }

  public Todo[] filterTodosByOwner(Todo[] todo, String targetOwner) {
    return Arrays.stream(todo).filter(x -> x.owner.equals(targetOwner)).toArray(Todo[]::new);
  }

  public Todo[] filterTodosByStatus(Todo[] todo, boolean targetStatus) {
    return Arrays.stream(todo).filter(x -> x.status == (targetStatus)).toArray(Todo[]::new);
  }


  //method for converting string to bool
  public boolean getBoolFromStatus(String str){
    boolean stat;

    if(str.equals("complete")){
      stat = true;
    }
    else{
      stat = false;
    }

    return stat;
  }
}
