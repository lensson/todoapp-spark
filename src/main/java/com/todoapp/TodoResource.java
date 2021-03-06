package com.todoapp;

import com.google.gson.Gson;

import spark.Request;
import spark.Response;
import spark.ResponseTransformer;
import spark.Route;

import java.util.HashMap;

import static spark.Spark.*;

/**
 * Created by shekhargulati on 09/06/14.
 */
public class TodoResource {

    private static final String API_CONTEXT = "/api/v1";

    private final TodoService todoService;

    public TodoResource(TodoService todoService) {
        this.todoService = todoService;
        setupEndpoints();
    }

    private void setupEndpoints() {
        post(API_CONTEXT + "/todos", "application/json", (request, response) -> {
            todoService.createNewTodo(request.body());
            response.status(201);
            return response;
        }, new JsonTransformer());

        get(API_CONTEXT + "/todos/:id", "application/json", (request, response)

                -> todoService.find(request.params(":id")), new JsonTransformer());

        get(API_CONTEXT + "/todos", "application/json", (request, response)

                -> todoService.findAll(), new JsonTransformer());

        put(API_CONTEXT + "/todos/:id", "application/json", (request, response)

                -> todoService.update(request.params(":id"), request.body()), new JsonTransformer());
        
        put(API_CONTEXT + "/todos/:id", "application/json", (request, response) 
        		-> todoService.update(request.params(":id"), request.body()),new JsonTransformer());
        
        delete(API_CONTEXT +  "/todos/:id", "application/json",(Route) (request,response) 
        		-> todoService.delete(request.params(":id")),new JsonTransformer());
    }



}
