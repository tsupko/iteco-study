package ru.volnenko.se.event;

public class CommandEvent {

  private String name;

  public CommandEvent(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
