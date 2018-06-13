package com.codecool.eeserver;

import com.codecool.customannotations.WebRoute;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MyHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange t) throws IOException {
        Class router = Router.class;
        Method[] methods = router.getMethods();
        String response = "DEFAULT TEXT";
        for (Method method:methods) {
            Annotation[] annotations = method.getDeclaredAnnotations();
            for (Annotation annotation:annotations) {
                if (annotation instanceof WebRoute){
                    WebRoute webAnnotation = (WebRoute) annotation;
                    if (webAnnotation.route().equals(t.getRequestURI().toString())){
                        try{
                            System.out.println("invoking method...");
                            System.out.println(method.getName());
                            method.invoke(null, t);
                            System.out.println("set response");

                        } catch (IllegalAccessException | InvocationTargetException wut){
                            response = "An error occurred while processing your request" + wut.getClass().toString();
                        }
                    }
                }
            }
        }
        System.out.println(t.getRequestURI().getPath());
        //String response = "This is the response";
        t.sendResponseHeaders(200, response.length());
        OutputStream os = t.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
