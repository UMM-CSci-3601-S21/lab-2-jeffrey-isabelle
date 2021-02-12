package umm3601.todos;

import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;

public class TodoController{

  private tDatabase tdatabase;

  public TodoController(tDatabase tdatabase) {
    this.tdatabase = tdatabase;
  }


    public void getTodos(Context ctx) {
      Todo[] todos = tdatabase.listTodos(ctx.queryParamMap());
      ctx.json(todos);
  }



}
