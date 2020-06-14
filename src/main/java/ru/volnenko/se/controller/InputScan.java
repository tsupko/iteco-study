package ru.volnenko.se.controller;

import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class InputScan {

  private final Scanner scanner = new Scanner(System.in);
  public String nextLine() {
    return scanner.nextLine();
  }

  public Integer nextInteger() {
    final String value = nextLine();
    if (value == null || value.isEmpty()) return null;
    try {
      return Integer.parseInt(value);
    } catch (Exception e) {
      return null;
    }
  }

}
