package ch.heg.ig.sda.ex3;

/**
 * @author maximili.jeannere
 */
public class TicTacToe {

    public static final int X = 1;
    public static final int O = -1;
    public static final int EMPTY = 0; //= Integer.MAX_VALUE;

    private int board[][] = new int[3][3];
    private int player;

    public TicTacToe(){
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                board[i][j] = EMPTY;
            }
        }

        player = X;
    }

    /** Place un X ou O à la position i,j */
    public void putMark(int i, int j) throws IllegalArgumentException {
        if ((i < 0) || (i > 2) || (j < 0) || (j > 2)){
            throw new IllegalArgumentException("Invalid board position");
        }

        if (board[i][j] != EMPTY)
            throw new IllegalArgumentException("Board position occupied");

        board[i][j] = player; // place la marque pour le joueur courant
        player = -player; // change de joueur
    }

    public boolean isWin(int mark) {
        return ( (board[0][0] + board[0][1] + board[0][2] == mark*3) // ligne 0
                || (board[1][0] + board[1][1] + board[1][2] == mark*3) // ligne 1
                || (board[2][0] + board[2][1] + board[2][2] == mark*3) // ligne 2
                || (board[0][0] + board[1][0] + board[2][0] == mark*3) // colonne 0
                || (board[0][1] + board[1][1] + board[2][1] == mark*3) // colonne 1
                || (board[0][2] + board[1][2] + board[2][2] == mark*3) // colonne 2
                || (board[0][0] + board[1][1] + board[2][2] == mark*3) // diagonale
                || (board[2][0] + board[1][1] + board[0][2] == mark*3) ); // colonne inverse
    }

    /**
     * Retourne le code du joueur gagnant, 0 pour indiquer une égalité (ou partie non terminée)
     *
     * @return
     */
    public int winner() {

        if (isWin(X))
            return(X);
        else if (isWin(O))
           return(O);
        else
            return(0);

    }

    /**
     * Retourne une chaine de caractères qui représentent l'état du plateau.
     * @return
     */
    public String toString() {

        StringBuilder sb = new StringBuilder();

        for (int i=0; i<3; i++) {

            for (int j=0; j<3; j++) {

                switch (board[i][j]) {
                    case X:
                        sb.append("X"); break;
                    case O:
                        sb.append("O"); break;
                    case EMPTY:
                        sb.append(" "); break;
                }

                if (j < 2) sb.append("|"); // limite (colonne)
            }

            if (i < 2) sb.append("\n-----\n");// limite (ligne)

        }

        return sb.toString();

    }

}
