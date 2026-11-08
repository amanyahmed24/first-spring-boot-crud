package com.example.firstspringboot;

import jakarta.validation.constraints.NotBlank;

public class Stuff {
  private int id;

  @NotBlank(message = "name is required ")
  private String name;

  @NotBlank(message = "Position is required")
  private String position ;
  private double salary;

  public Stuff() {
  }

  public Stuff(int id, String name, String position, double salary) {
    this.id = id;
    this.name = name;
    this.position = position;
    this.salary = salary;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPosition() {
    return position;
  }

  public void setPosition(String position) {
    this.position = position;
  }

  public double getSalary() {
    return salary;
  }

  public void setSalary(double salary) {
    this.salary = salary;
  }
}
