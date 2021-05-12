package org.galex.lifts;

import java.util.ArrayList;
import java.util.List;

public class LiftOperator {
  private List<Lift> liftList;

  public LiftOperator(int liftsNum, int maxFloor, int maxLoad) {
    liftList = new ArrayList<>();
    for (int i = 0; i < liftsNum; i++) {
      liftList.add(new Lift(i, maxFloor, maxLoad));
    }
  }
}
