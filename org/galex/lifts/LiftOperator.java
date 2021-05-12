package org.galex.lifts;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LiftOperator {
  private List<Lift> liftList;
  private final int liftsNum;
  private final int maxFloor;

  public LiftOperator(int liftsNum, int maxFloor, int maxLoad) {
    this.liftsNum = liftsNum;
    this.maxFloor = maxFloor;
    liftList = new ArrayList<>();
    for (int i = 0; i < liftsNum; i++) {
      liftList.add(new Lift(i, maxFloor, maxLoad));
    }
  }

  public void run(){
    Random random = new Random();
    Runnable requestGenerator = () -> {
      while (true){
        Lift currentLift = liftList.get(random.nextInt(liftsNum));
        synchronized (currentLift.getRequestQueue()) {
          Request request = new Request(random.nextInt(maxFloor), random.nextInt(maxFloor), random.nextInt(3) + 1);
          System.out.println("Новый запрос для лифта " + currentLift.getId() + " этаж " + request.getInFloor());
          currentLift.addRequest(request);
        }
        try {
          Thread.sleep(4000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    };

    Thread[] liftsThreads = new Thread[liftsNum];
    for (int i = 0; i < liftsNum; i++) {
      liftsThreads[i] = new Thread(liftList.get(i).getAction());
      liftsThreads[i].start();
    }
    Thread generator = new Thread(requestGenerator);
    generator.start();
  }
}
