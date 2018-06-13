package com.codecool.eeserver;

import com.codecool.customannotations.WebRoute;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;

public class Router {
    @WebRoute(route = "/")
    public void index(HttpExchange t) {
        try {
            String response = "This is the index page";
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @WebRoute(route = "/faq")
    public void faq(HttpExchange t) {
        try {
            String response = "This is the index page";
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
