package model;

import java.util.HashSet;

/**
 * Class Piece
 */
public class Piece {

  //
  // Fields
  //
  private HashSet<Coord> shape = new HashSet<>();

  //
  // Constructors
  //
  public Piece() {
  };

  public Piece(Piece p) {
    for (Coord c : p.shape) {
      shape.add(new Coord(c));
    }
  }

  //
  // Methods
  //

  /**
   * add a new point to form the shape
   * 
   * @param c
   */
  public void add(Coord c) {
    shape.add(c);
  }

  /**
   * return the shape with orientation and reverted applied
   */
  public HashSet<Coord> getShape() {
    return shape;
  }

  /**
   * 
   * @param c
   * @return the corners of the coord c considering the shape
   */
  public HashSet<Coord> getCorners(Coord c) {
    if (!shape.contains(c)) {
      throw new IllegalArgumentException("coord " + c + " isn't in piece");
    }
    HashSet<Coord> corn = new HashSet<>();
    for (DiagonalDirection dd : DiagonalDirection.values()) {
      if (!(shape.contains(c.add(dd.d1)) || shape.contains(c.add(dd.d2)))) {
        corn.add(c.add(dd));
      }
    }

    return corn;
  }

  /**
   * 
   * @return the corner where another piece can be put
   */
  public HashSet<Coord> getCorners() {
    HashSet<Coord> corn = new HashSet<>();
    for (Coord c : shape) {
      corn.addAll(getCorners(c));
    }
    return corn;
  }

  /**
   * @return true if there is no shape
   */
  public boolean isEmpty() {
    return shape.isEmpty();
  }

  public void translate(Coord c) {
    HashSet<Coord> nShape = new HashSet<>();
    for (Coord cT : shape) {
      nShape.add(cT.add_equal(c));
    }
    shape = nShape;
  }

  /**
   * put the piece at (0, 0) post: forall x, y in shape x >= 0, y >=0
   */
  public void normalize() {
    Coord min = new Coord();
    for (Coord c : shape) {
      if (c.x < min.x) {
        min.x = c.x;
      }
      if (c.y < min.y) {
        min.y = c.y;
      }
    }

    translate(min.sub());
  }

  public Coord computeSize() {
    Coord sz = new Coord();
    for (Coord c : shape) {
      if (c.x > sz.x) {
        sz.x = c.x;
      }
      if (c.y > sz.y) {
        sz.y = c.y;
      }
    }
    ++sz.x;
    ++sz.y;
    return sz;
  }

  //
  // Accessor methods
  //

  /**
   * rotate clockwise
   */
  public void right() {
    for (Coord c : shape) {
      int tempX = c.x;
      int tempY = c.y;
      c.x = -tempY;
      c.y = tempX;
      System.out.println("x=" + c.x + " y=" + c.y);
    }
    normalize();
  }

  /**
   * rotate counter-clockwise
   */
  public void left() {
    for (Coord c : shape) {
      int tempX = c.x;
      int tempY = c.y;
      c.x = tempY;
      c.y = -tempX;
      System.out.println("x=" + c.x + " y=" + c.y);
    }
    normalize();
  }

  /**
   * symmetry from y axis
   */
  public void revertY() {
    for (Coord c : shape) {
      c.y = -c.y;
      System.out.println("x=" + c.x + " y=" + c.y);
    }
    normalize();
  }

  /**
   * symmetry from x axis
   */
  public void revertX() {
    for (Coord c : shape) {
      c.x = -c.x;
      System.out.println("x=" + c.x + " y=" + c.y);
    }
    normalize();
  }

  //
  // Other methods
  //

  @Override
  public String toString() {
    String res = "\n";
    Coord sz = computeSize();
    char tab[][] = new char[sz.y + 2][sz.x + 2];
    for (int i = 0; i < tab.length; i++) {
      for (int j = 0; j < tab[i].length; j++) {
        tab[i][j] = ' ';
      }
    }
    for (Coord c : shape) {
      tab[c.y + 1][c.x + 1] = '█';
    }
    for (Coord c : getCorners()) {
      tab[c.y + 1][c.x + 1] = '*';
    }

    for (int i = 0; i < tab.length; i++) {
      for (int j = 0; j < tab[i].length; j++) {
        res += tab[i][j];
      }
      res += "\n";
    }

    return res;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Piece) {
      Piece p = (Piece) obj;
      return shape.equals(p.shape);
    }
    return false;
  }

}
