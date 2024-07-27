package Lesson1;

import java.util.Random;

public class Player {

    private Random random;

    public Player() {
        this.random = new Random();
    }

    public int choiceDoor() {
        return random.nextInt(3);
    }

    public int choiceNewDoor(int secondChoiceDoor, int firstChoiceDoor) {
        return 3 - secondChoiceDoor - firstChoiceDoor;
    }
}