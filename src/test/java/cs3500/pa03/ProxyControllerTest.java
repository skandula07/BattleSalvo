package cs3500.pa03;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import cs3500.pa03.controller.ProxyController;
import cs3500.pa03.mockets.BadMocket;
import cs3500.pa03.mockets.Mocket;
import cs3500.pa03.model.player.AiPlayer;
import cs3500.pa03.model.player.Player;
import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * A class to test the ProxyController class's methods
 */
public class ProxyControllerTest {
  private Mocket server;
  private final ObjectMapper mapper = new ObjectMapper();
  private static final JsonNode VOID_RESPONSE =
      new ObjectMapper().getNodeFactory().textNode("void");
  private ProxyController controller;
  private Player aiPlayer;

  /**
   * creates a Mocket, AiPlayer, and ProxyController for testing
   *
   * @throws IOException if an IOException occurs
   */
  @BeforeEach
  public void setUp() throws IOException {
    server = new Mocket();
    aiPlayer = new AiPlayer();
    controller = new ProxyController(server, aiPlayer);
  }

  /**
   * tests the delegateMessage method and the private handler methods
   */
  @Test
  public void testDelegateMessage() {
    assertThrows(IllegalArgumentException.class,
        () -> controller.delegateMessage(server.sendBadMessage()));
    controller.delegateMessage(server.sendJoinMessage());
    controller.delegateMessage(server.sendSetupMessage());
    assertEquals(4, controller.getFleet().getFleet().size());


    controller.delegateMessage(server.sendTakeShots());
    assertEquals(4, controller.getShots().getShotsFired().size());
    controller.delegateMessage(server.sendTakeShots());
    controller.delegateMessage(server.sendTakeShots());
    assertEquals(12, controller.getShots().getShotsFired().size());
    controller.delegateMessage(server.sendReportDamage());
    assertEquals(4, controller.getShots().getShotsReceived().size());
    controller.delegateMessage(server.sendsSuccessfulHits());
    assertEquals(4, controller.getShots().getShotsHitOpponent().size());

    controller.delegateMessage(server.sendEndGame());
    assertTrue(server.isClosed());
  }

  /**
   * tests the creation of a ProxyController with a BAD mocket
   */
  @Test
  public void testConstructor() {
    BadMocket b = new BadMocket();
    ProxyController p = new ProxyController(b, aiPlayer);
  }


  /**
   *  tests the makeBoard method
   */
  @Test
  public void testMakeBoard() {
    controller.makeBoard(6, 7);
    assertEquals(6, controller.getBoard().getHeight());
    assertEquals(7, controller.getBoard().getWidth());
  }
}
