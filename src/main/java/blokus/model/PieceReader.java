package blokus.model;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * PieceReader
 */
public class PieceReader {
	BufferedInputStream bis;
	ArrayList<Coord> shape = new ArrayList<>();
	int no = 0;

	public PieceReader(InputStream is) {
		bis = new BufferedInputStream(is);
	}

	public Piece nextPiece() {
		Piece p = null;
		shape.clear();
		Coord c = new Coord();

		int red;
		try {
			while ((red = bis.read()) != -1 && !(red == '\n' && c.x == 0)) {
				if (red == '\n') {
					c.y++;
					c.x = 0;
				} else if (red == '*') {
					shape.add(new Coord(c));
					c.x++;
				} else if (red == '.') {
					c.x++;
				} else if (red == '#') {
					while ((red = bis.read()) != -1 && red != '\n') {
					}
				}
			}
			if (!shape.isEmpty()) {
				p = new Piece(no++, shape);
			}

		} catch (IOException e) {
			return null;
		}

		if (p != null) {
			Config.i().logger().info("load piece: ");
			Config.i().logger().info(p.toString());
		}

		return p;
	}

}
