package Lesson1;

public class GamesMonty {

    public GamesMonty() {;
    }


    public int singlePickChoiceWinning(Player player, int numberOfRounds) {
        int wins = 0;
        for (int rounds = 0; rounds < numberOfRounds; rounds++) {
            Doors doors = new Doors();
            int choiceDoor = player.choiceDoor();
            if (doors.getDoor(choiceDoor).isPrizeDoor()) {
                wins +=1;
            }
        }
        return wins;
    }

    public int rePickChoiceWinning(Player player, int numberOfRounds) {
        int wins = 0;
        for (int rounds = 0; rounds < numberOfRounds; rounds++) {
            Doors doors = new Doors();
            int choiceDoor = player.choiceDoor();
            int noPrizeDoor = doors.noPrizeDoor(choiceDoor);
            int newSelect = player.choiceNewDoor(noPrizeDoor, choiceDoor);
            if (doors.getDoor(newSelect).isPrizeDoor()) {
                wins +=1;
            }
        }
        return wins;
    }
}