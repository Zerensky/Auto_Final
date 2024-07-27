package Lesson1;

public class Main {

    public static void main(String[] args) {
        Player singlePickPlayer = new Player();
        Player rePickPlayer = new Player();
        int numberOfRounds = 1000;
        GamesMonty gamesMontySingle = new GamesMonty();
        GamesMonty gamesMontyRe = new GamesMonty();

        int winsSinglePick = gamesMontySingle.singlePickChoiceWinning(singlePickPlayer, numberOfRounds);
        int winsRePick = gamesMontyRe.rePickChoiceWinning(rePickPlayer, numberOfRounds);

        System.out.println("Количество побед без смены выбранной двери: "
                + (winsSinglePick * 100) / numberOfRounds + "%");
        System.out.println("Количество побед со сменой выбранной двери: "
                + (winsRePick * 100) / numberOfRounds + "%");
    }
}