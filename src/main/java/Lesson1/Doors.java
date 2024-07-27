package Lesson1;

import java.util.Random;

public class Doors {

    private Door[] doors;
    private int prizeDoorIndex;
    private Random random = new Random();

    public Doors() {
        doors = new Door[3];
        prizeDoorIndex = random.nextInt(3);
        for (int i = 0; i < 3; i++) {
            if (i == prizeDoorIndex) {
                doors[i] = new Door(true);
            } else {
                doors[i] = new Door(false);
            }
        }
    }

    public Door getDoor(int doorIndex) {
        return doors[doorIndex];
    }

    public int noPrizeDoor(int selectedDoorIndex) {
        int noPrizeDoorIndex = 0;
        while (selectedDoorIndex == noPrizeDoorIndex || prizeDoorIndex == noPrizeDoorIndex) {
            noPrizeDoorIndex = random.nextInt(3);
        }
        return noPrizeDoorIndex;
    }
}