package org.galex.lifts;

import java.util.Random;

public class Main {

  public static void main(String[] args){
    Lift lift = new Lift(0, 9, 9);

    Random random = new Random();

    Runnable requestGenerator = () -> {
      while (true){
        synchronized (lift.getRequestQueue()) {
          Request request = new Request(random.nextInt(9), random.nextInt(9), random.nextInt(3) + 1);
          System.out.println("Новый запрос для лифта " + lift.getId() + " " + request.getInFloor() + " " + request.getOutFloor() + " " + request.getLoad());
          lift.addRequest(request);
        }
        try {
          Thread.sleep(2000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    };

    Thread thread1 = new Thread(requestGenerator);
    Thread thread2 = new Thread(lift.getAction());

    thread1.start();
    thread2.start();
  }
}
