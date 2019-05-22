package blokus.model;

import java.util.ArrayList;

import javafx.scene.paint.Color;

/**
 * Player
 */
public class Player extends APlayer {

  public Player(Color color, ArrayList<Piece> pieces) {
    super(color, pieces);
  }

  public Player(Player p) {
    super(p);
  }

  @Override
  public APlayer copy() {
    return new Player(this);
  }
}
