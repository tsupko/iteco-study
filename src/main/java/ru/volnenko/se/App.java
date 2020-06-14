package ru.volnenko.se;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.volnenko.se.config.Configuration;
import ru.volnenko.se.controller.Bootstrap;

public class App {

    public static void main(String[] args) {
      final ApplicationContext context = new AnnotationConfigApplicationContext(Configuration.class);
      final Bootstrap bootstrap = context.getBean(Bootstrap.class);
      bootstrap.start();
    }

}
