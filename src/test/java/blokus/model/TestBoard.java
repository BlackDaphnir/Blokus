package blokus.model;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

import javafx.scene.paint.Color;

import static blokus.Tools.*;

import java.util.ArrayList;
import java.util.Iterator;

import blokus.model.Board;
import blokus.model.GameType;
import blokus.model.PColor;

/**
 * TestBoard
 */
public class TestBoard {

    ArrayList<Piece> pieces;

    @BeforeClass
    public void init() {
        pieces = loadFile("piecesLEFT_REVERTED");
    }

    @Test
    public void test_canAdd(GameType gt) { // Test for add/getAccCorners as well?
        // Try some coherente addings (each corner/ + a Piece bordering to an other but
        // with different colors)
        Board b = new Board(gt);
        for (int i = 0; i < 4; i++) {
            Iterator<Coord> it = b.getAccCorners(PColor.get((byte) i)).iterator();
            if (it.hasNext()) {
                assertTrue(b.canAdd(pieces.get(0), it.next(), PColor.get((byte) i)));
                b.add(pieces.get(0), it.next(), PColor.get((byte) i));
            }
        }
        switch (gt) {
        case BLOKUS:
            b.add(pieces.get(14), new Coord(1, b.getSize() - 2), b.get(new Coord(0, b.getSize() - 1)));
            b.add(pieces.get(14), new Coord(6, b.getSize() - 1), b.get(new Coord(0, b.getSize() - 1)));
            b.add(pieces.get(14), new Coord(15, b.getSize() - 3), b.get(new Coord(b.getSize() - 1, b.getSize() - 1)));
            // Try canAdd on a border of an other piece of a different color
            assertTrue(b.canAdd(pieces.get(14), new Coord(10, b.getSize() - 2),b.get(new Coord(b.getSize() - 1, b.getSize() - 1))));
            // Try canAdd on a border of an other piece of the same color
            assertFalse(b.canAdd(pieces.get(4), new Coord(7, b.getSize() - 2), b.get(new Coord(0, b.getSize() - 1))));
            // Try canAdd without color coherence
            assertFalse(b.canAdd(pieces.get(0), new Coord(14, b.getSize() - 4), b.get(new Coord(b.getSize() - 1, b.getSize() - 1))));
            // b.add(pieces.get(14), new Coord(10,b.getSize() -2), b.get(new
            // Coord(b.getSize() -1, b.getSize()-1))); possible test de add apres le canAdd
            // correspondant
            break;
        case DUO: 
            // Try canAdd on a border of an other piece of a different color
            assertTrue(b.canAdd(pieces.get(1), new Coord(2,3), b.get(new Coord(4,4))));
            // Try canAdd on a border of an other piece of the same color
            assertFalse(b.canAdd(pieces.get(1), new Coord(3,5), b.get(new Coord(4,4))));
            // Try canAdd without color coherence
            assertFalse(b.canAdd(pieces.get(0), new Coord(3,5), b.get(new Coord(b.getSize() - 5, b.getSize() - 5))));
            // b.add(pieces.get(1), new Coord(2,3), b.get(new
            // Coord(4,4))); possible test de add apres le canAdd
            // correspondant
            break;
        }
        // Try canAdd on a non-corner square
        b = new Board(gt);
        assertFalse(b.canAdd(pieces.get(0), new Coord(1, 1), PColor.get((byte) 0)));
        // Try canAdd on an occupied square
        switch (gt) {
        case BLOKUS:
            b.add(pieces.get(5), new Coord(0, 1), PColor.get((byte) 1));
            b.add(pieces.get(7), new Coord(2, 3), PColor.get((byte) 1));
            assertFalse(b.canAdd(pieces.get(0), new Coord(2, 2), PColor.get((byte) 1)));
            b.add(pieces.get(6), new Coord(b.getSize() - 4, 0), PColor.get((byte) 0));
            b.add(pieces.get(14), new Coord(11, 1), PColor.get((byte) 0));
            b.add(pieces.get(14), new Coord(6, 0), PColor.get((byte) 0));
            assertFalse(b.canAdd(pieces.get(4), new Coord(5, 1), PColor.get((byte) 0)));
            break;
        case DUO:
            b.add(pieces.get(4), new Coord(4,5), PColor.get((byte) 1));
            b.add(pieces.get(0), new Coord(b.getSize()-5,b.getSize()-5), PColor.get((byte) 0));
            assertFalse(b.canAdd(pieces.get(0), new Coord(5,5), PColor.get((byte) 1)));
            assertFalse(b.canAdd(pieces.get(4), new Coord(2,3), PColor.get((byte) 1)));
            break;
        }
        // TODO : Try canAdd with an exit of the board
    }
}
