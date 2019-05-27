
package blokus;

import org.testng.annotations.Test;

import blokus.controller.GameGraph;
import blokus.controller.GameTest;
import blokus.model.PlayerType;

/**
 * Main
 */
public class Main {

  @Test
  void caca() {

  }

  public static void main(String[] args) {
    // GameGraph gg = new GameGraph();
    // gg.game_graph();
    System.out.println("*****************************************************************************");
    GameTest gt = new GameTest();
    gt.game_test(PlayerType.AI, PlayerType.RANDOM_PIECE);
    System.out.println("*****************************************************************************");
    gt.results();
  }
}