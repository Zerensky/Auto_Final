package Lesson1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GamesMontyTest {

    Player singlePickPlayer;
    Player rePickPlayer;


    @Test
    void withPrizeDoor() {
        Door withPrize = new Door(true);
        assertTrue(withPrize.isPrizeDoor());
    }


    @Test
    void withoutPrizeDoor() {
        Door withoutPrize = new Door(false);
        assertFalse(withoutPrize.isPrizeDoor());
    }


    @Test
    void selectNewDoorReturnsExpectedIndex() {
        Player player = new Player();
        int newChoice = player.choiceNewDoor(0, 1);
        Assertions.assertEquals(newChoice, 2);
    }


    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2})
    void getNoPrizeDoorIndex() {
        Doors doors = new Doors();
        int selectDoorIndex = 1;
        int noPrizeDoorIndex = doors.noPrizeDoor(selectDoorIndex);
        Assertions.assertNotEquals(selectDoorIndex, noPrizeDoorIndex);
    }


    @RepeatedTest(10)
    void numberOfWinsWithoutChangingChoice() {
        singlePickPlayer = new Player();
        GamesMonty gamesMontySingle = new GamesMonty();
        int winsSinglePick = gamesMontySingle.singlePickChoiceWinning(singlePickPlayer, 1000);
        assertTrue(winsSinglePick  < 400);
    }


    @RepeatedTest(10)
    void numberOfWinsWithChangingChoice() {
        rePickPlayer = new Player();
        GamesMonty gamesMontyRe = new GamesMonty();
        int winsRePick = gamesMontyRe.rePickChoiceWinning(rePickPlayer, 1000);
        assertTrue(winsRePick > 600);
    }
}