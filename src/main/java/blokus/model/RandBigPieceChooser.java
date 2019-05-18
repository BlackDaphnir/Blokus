package blokus.model;

import java.util.List;
import java.util.Random;

public class RandBigPieceChooser implements PieceChooser {
    Random r = new Random();

    @Override
    public Piece pickPiece(List<Piece> availablePieces) {
        int completeWeight = 0;
        for (Piece p : availablePieces) {
            completeWeight += p.size();
        }
        int rand = r.nextInt(completeWeight);
        int countWeight = 0;
        for (Piece p : availablePieces) {
            countWeight += p.size();
            if (countWeight >= rand) {
                return p;
            }
        }
        throw new RuntimeException("should never be shown");
    }

    @Override
    public Placement pickPlacement(List<Placement> placements) {
        int completeWeight = 0;
        for (Placement p : placements) {
            completeWeight += p.piece.size();
        }
        int rand = r.nextInt(completeWeight);
        int countWeight = 0;
        for (Placement p : placements) {
            countWeight += p.piece.size();
            if (countWeight >= rand) {
                return p;
            }
        }
        throw new RuntimeException("should never be shown");
    }

}