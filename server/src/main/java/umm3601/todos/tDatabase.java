package umm3601.todos;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
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



  //The main listing method
  @SuppressWarnings("all")
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

    //Filter by included word
    if (queryParams.containsKey("contains")) {
      String targetContains = queryParams.get("contains").get(0);
      filteredTodo = filterTodosByBody(filteredTodo, targetContains);
    }

    if (queryParams.containsKey("orderBy")) {
      String targetOrder = queryParams.get("orderBy").get(0);
      filteredTodo = sortedTodos(filteredTodo, targetOrder);
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

  public Todo[] filterTodosByBody(Todo[] todo, String targetBody) {
    return Arrays.stream(todo).filter(x -> x.body.contains(targetBody)).toArray(Todo[]::new);
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

  public Todo[] sortedTodos(Todo[] todos, String str){
    Todo[] sortedTodos = todos;

    switch(str) {

      case "owner":
        Arrays.sort(sortedTodos, new Comparator<Todo>() {
          @Override
          public int compare(Todo u1, Todo u2) {
            return u1.owner.compareTo(u2.owner);
        }
      });
      break;
      case "status":
        Arrays.sort(sortedTodos, new Comparator<Todo>() {
          @Override
          public int compare(Todo u1, Todo u2) {
            return Boolean.toString(u1.status).compareTo(Boolean.toString(u2.status));
      }
      });
      break;
      case "category":
        Arrays.sort(sortedTodos, new Comparator<Todo>() {
          @Override
          public int compare(Todo u1, Todo u2) {
            return u1.category.compareTo(u2.category);
      }
      });
      break;
      case "body":
        Arrays.sort(sortedTodos, new Comparator<Todo>() {
          @Override
          public int compare(Todo u1, Todo u2) {
            return u1.body.compareTo(u2.body);
      }
      });
      break;

      default:

      throw new BadRequestResponse("Not Valid.");

    }
    return sortedTodos;
  }
}
