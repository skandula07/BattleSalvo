package cs3500.pa03;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa03.json.EndGameReceiveJson;
import cs3500.pa03.model.gameresult.GameResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * tests the EndGameRecieveJson record
 */
public class EndGameReceiveJsonTest {

  private EndGameReceiveJson egrj1;
  private String result1;
  private String reason1;

  private EndGameReceiveJson egrj2;
  private String result2;
  private String reason2;

  private EndGameReceiveJson egrj3;
  private String result3;
  private String reason3;

  /**
   * instantiates the EndGameReceiveJson
   */
  @BeforeEach
  public void setUp() {
    result1 = "WIN";
    reason1 = "you rock";
    egrj1 = new EndGameReceiveJson(result1, reason1);

    result2 = "LOSE";
    reason2 = "you lost";
    egrj2 = new EndGameReceiveJson(result2, reason2);

    result3 = "DRAW";
    reason3 = "there was a draw";
    egrj3 = new EndGameReceiveJson(result3, reason3);
  }

  /**
   * tests the method result()
   */
  @Test
  public void testResult() {
    assertEquals("WIN", egrj1.result());

    assertEquals("LOSE", egrj2.result());

    assertEquals("DRAW", egrj3.result());

  }

  /**
   * tests the method reason()
   */
  @Test
  public void testReason() {
    assertEquals("you rock", egrj1.reason());
    assertEquals("you lost", egrj2.reason());
    assertEquals("there was a draw", egrj3.reason());
  }

  /**
   * tests the method getResult()
   */
  @Test
  public void testGetResult() {
    assertEquals(GameResult.WIN, egrj1.getResult());
    assertEquals(GameResult.LOSS, egrj2.getResult());
    assertEquals(GameResult.DRAW, egrj3.getResult());
  }


}