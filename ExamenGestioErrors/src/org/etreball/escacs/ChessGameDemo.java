package org.etreball.escacs;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

import javax.swing.*;

/**
 * Cal programar la funcio move , que en realitat valida el moviment i l'hauria
 * de registrar, per ara només comprovarem que estem realitzant un moviment
 * vàlid per un peo (només avançar sense matar). La funcio move és cridada per
 * la funcio movePiece
 * @param <E>
 * 
 * 
 *
 */
public class ChessGameDemo<E> extends JFrame implements MouseListener,
		MouseMotionListener {
	JLayeredPane layeredPane;
	JPanel chessBoard;
	JLabel chessPiece;
	int xAdjustment;
	int yAdjustment;

	Map<JPanel, Integer[]> map = new HashMap<JPanel, Integer[]>();
	Container lastComponent;
	Integer[] lastPosition;

	public ChessGameDemo() {
		init();
	}


	/**
	 * Aquesta funció ha de validar que el moviment sigui correcte i en un futur
	 * guardar el moviment en BBDD la posicio esta representada per un vector de
	 * dues posicions {i,j} on i són les files i j les columnes
	 * 
	 * @param lastPosition
	 * @param newPosition
	 */
	private void move(Integer[] lastPosition, Integer[] newPosition) {
		List<E> mov = new ArrayList<E>();

	}

	/**
	 * Realitza el moviment físic i el moviment gràfic en funció de la valides del moviment
	 * @param parent
	 * @param newPostion
	 */
	private void movePiece(Container parent, Integer[] newPostion) {
		move(lastPosition, newPostion);
		graficalMove(parent, newPostion);
		// graficalUndoMove();
	}
	
	/**
	 * Funció que situa la peça a la última casella abans de produir-se el nou
	 * moviment
	 */
	private void graficalUndoMove() {
		lastComponent.add(chessPiece);
	}

	public void graficalMove(Container parent, Integer[] newPostion) {
		parent.add(chessPiece);
		lastComponent = parent;
		lastPosition = newPostion;
	}

	public void mousePressed(MouseEvent e) {
		chessPiece = null;
		Component c = chessBoard.findComponentAt(e.getX(), e.getY());

		if (c instanceof JPanel)
			return;

		Point parentLocation = c.getParent().getLocation();
		xAdjustment = parentLocation.x - e.getX();
		yAdjustment = parentLocation.y - e.getY();
		chessPiece = (JLabel) c;
		chessPiece.setLocation(e.getX() + xAdjustment, e.getY() + yAdjustment);
		chessPiece.setSize(chessPiece.getWidth(), chessPiece.getHeight());
		layeredPane.add(chessPiece, JLayeredPane.DRAG_LAYER);
	}

	// Move the chess piece around

	public void mouseDragged(MouseEvent me) {
		if (chessPiece == null)
			return;
		chessPiece
				.setLocation(me.getX() + xAdjustment, me.getY() + yAdjustment);

	}

	// Drop the chess piece back onto the chess board

	public void mouseReleased(MouseEvent e) {
		if (chessPiece == null)
			return;

		chessPiece.setVisible(false);
		Component c = chessBoard.findComponentAt(e.getX(), e.getY());
		Container parent;
		if (c instanceof JLabel) {
			parent = c.getParent();
			parent.remove(0);
		} else {
			parent = (Container) c;
		}
		Integer[] newPostion = map.get(parent);

		movePiece(parent, newPostion);
		chessPiece.setVisible(true);
	}

	public void mouseClicked(MouseEvent e) {
		//System.out.println(e.getLocationOnScreen());
				
	}

	public void mouseMoved(MouseEvent e) {
		
	}

	public void mouseEntered(MouseEvent e) {
	
	}

	public void mouseExited(MouseEvent e) {
		System.out.println(e.getX());
		System.out.println(e.getY());
	}

	private void init() {
		Dimension boardSize = new Dimension(600, 600);

		// Use a Layered Pane for this this application
		layeredPane = new JLayeredPane();
		getContentPane().add(layeredPane);
		layeredPane.setPreferredSize(boardSize);
		layeredPane.addMouseListener(this);
		layeredPane.addMouseMotionListener(this);

		// Add a chess board to the Layered Pane
		chessBoard = new JPanel();
		layeredPane.add(chessBoard, JLayeredPane.DEFAULT_LAYER);
		chessBoard.setLayout(new GridLayout(8, 8));
		chessBoard.setPreferredSize(boardSize);
		chessBoard.setBounds(0, 0, boardSize.width, boardSize.height);

		// Create the squares
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				JPanel square = new JPanel(new BorderLayout());
				map.put(square, new Integer[] { i, j });
				chessBoard.add(square);

				int row = ((i * 8 + j) / 8) % 2;
				if (row == 0)
					square.setBackground((i * 8 + j) % 2 == 0 ? Color.blue
							: Color.white);
				else
					square.setBackground((i * 8 + j) % 2 == 0 ? Color.white
							: Color.blue);
			}

		}

		// Add the piece to the board
		java.net.URL localizacion = ChessGameDemo.class.getResource("bp.png");
		JLabel piece = new JLabel(new ImageIcon(localizacion));
		JPanel panel = (JPanel) chessBoard.getComponent(0);
		panel.add(piece);
		lastComponent = panel;
		lastPosition = new Integer[] { 0, 0 };

	}

	public static void main(String[] args) {
		JFrame frame = new ChessGameDemo();
		frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setResizable(true);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}