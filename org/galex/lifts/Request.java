package org.galex.lifts;

public class Request {
  private Direction direction;
  private int inFloor;
  private int outFloor;
  private int load;

  public Request(int inFloor, int outFloor, int load) {
    this.inFloor = inFloor;
    this.outFloor = outFloor;
    this.load = load;
    if(inFloor > outFloor){
      direction = Direction.DOWN;
    }else{
      direction = Direction.UP;
    }
  }

  public Direction getDirection() {
    return direction;
  }

  public void setDirection(Direction direction) {
    this.direction = direction;
  }

  public int getInFloor() {
    return inFloor;
  }

  public void setInFloor(int inFloor) {
    this.inFloor = inFloor;
  }

  public int getOutFloor() {
    return outFloor;
  }

  public void setOutFloor(int outFloor) {
    this.outFloor = outFloor;
  }

  public int getLoad() {
    return load;
  }

  public void setLoad(int load) {
    this.load = load;
  }

  enum Direction{
    UP,
    DOWN
  }
}
