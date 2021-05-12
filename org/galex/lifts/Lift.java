package org.galex.lifts;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Lift {
  private final int id;
  private final int maxFloor;
  private final int maxLoad;
  private int currentFloor;
  private int currentLoad;
  private List<Request> requestQueue;
  private int[] outNumbers;
  private Direction direction;
  private final Runnable action = this::move;

  public Lift(int id, int maxFloor, int maxLoad) {
    this.id = id;
    this.maxFloor = maxFloor;
    this.maxLoad = maxLoad;
    currentFloor = 0;
    currentLoad = 0;
    requestQueue = new ArrayList<>();
    outNumbers = new int[maxFloor];
    for (int i = 0; i < maxFloor; i++) {
      outNumbers[i] = 0;
    }
    direction = Direction.UP;
  }

  private void move() {
    while (true) {
      if (isUnloadOnFloor()) {
        currentLoad -= outNumbers[currentFloor];
        System.out.println("Лифт " + id + " на " + currentFloor
                + " этаже высаживает " + outNumbers[currentFloor] + " человек, " + currentLoad + " человек всего");
        outNumbers[currentFloor] = 0;
        try {
          Thread.sleep(4000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      synchronized (requestQueue) {
        if (isAnyRequest() || isAnyUnload()) {
          if (isAnyReqOnFloor()) {
            Iterator<Request> iterator = requestQueue.iterator();
            while (iterator.hasNext()) {
              Request request = iterator.next();
              if (request.getInFloor() == currentFloor && request.getLoad() + currentLoad <= maxLoad) {
                outNumbers[request.getOutFloor()] += request.getLoad();
                currentLoad += request.getLoad();
                System.out.println("Лифт " + id + " на " + currentFloor
                        + " этаже принимает " + request.getLoad() + " человек, " + currentLoad + " человек всего");
                iterator.remove();
              }
            }
          }
          if (!isAnyReqOnWay() && !isAnyUnloadOnWay()) {
            changeDirection();
            System.out.println("Лифт " + id + " сменил направление");
          }
          if (direction == Direction.UP && currentFloor < maxFloor - 1) {
            currentFloor++;
            System.out.println("Лифт " + id + " поднялся на " + currentFloor
                    + " этаж");
          } else if (direction == Direction.DOWN && currentFloor > 0) {
            currentFloor--;
            System.out.println("Лифт " + id + " опустился на " + currentFloor
                    + " этаж");
          }
        }
      }
      try {
        Thread.sleep(4000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  private void changeDirection() {
    if (direction == Direction.UP) {
      direction = Direction.DOWN;
    } else {
      direction = Direction.UP;
    }
  }

  private boolean isAnyReqOnWay() {
    boolean answer = false;
    for (Request request :
            requestQueue) {
      if (((request.getInFloor() > currentFloor
              && direction == Direction.UP)
              || (request.getInFloor() < currentFloor
              && direction == Direction.DOWN))
              && (currentLoad + request.getLoad() <= maxLoad)) {
        answer = true;
        break;
      }
    }
    return answer;
  }

  private boolean isAnyReqOnFloor() {
    boolean answer = false;
    for (Request request :
            requestQueue) {
      if (request.getInFloor() == currentFloor && currentLoad + request.getLoad() <= maxLoad) {
        answer = true;
        break;
      }
    }
    return answer;
  }

  private boolean isAnyUnloadOnWay() {
    boolean answer = false;
    if (direction == Direction.UP) {
      for (int i = currentFloor + 1; i < maxFloor; i++) {
        if (outNumbers[i] > 0) {
          answer = true;
          break;
        }
      }
    } else {
      for (int i = currentFloor - 1; i >= 0; i--) {
        if (outNumbers[i] > 0) {
          answer = true;
          break;
        }
      }
    }
    return answer;
  }

  private boolean isAnyUnload() {
    boolean answer = false;
    for (int i :
            outNumbers) {
      if (i > 0) {
        answer = true;
        break;
      }
    }
    return answer;
  }

  private boolean isUnloadOnFloor() {
    return (outNumbers[currentFloor] > 0);
  }

  public int getCurrentFloor() {
    return currentFloor;
  }

  public void setCurrentFloor(int currentFloor) {
    this.currentFloor = currentFloor;
  }

  public int getCurrentLoad() {
    return currentLoad;
  }

  public void setCurrentLoad(int currentLoad) {
    this.currentLoad = currentLoad;
  }

  public boolean isAnyRequest() {
    return !requestQueue.isEmpty();
  }

  public List<Request> getRequestQueue() {
    return requestQueue;
  }

  public void setRequestQueue(List<Request> requestQueue) {
    this.requestQueue = requestQueue;
  }

  public void addRequest(Request request) {
    this.requestQueue.add(request);
  }

  public Runnable getAction() {
    return action;
  }

  public int getId() {
    return id;
  }

  enum Direction {
    UP,
    DOWN
  }
}
