public class SudokuSolver
{
    public static void main(String[] args)
    {
        // score 51 - websudoku.com - medium difficulty - solved in 14 iterations using LAST CANDIDATE
        Sudoku easyToSolve = new Sudoku("036000820009500000800400007600100039003000500920006008500004002000002700071000450");

        // score 57 - websudoku.com - hard difficulty - my solver can't solve this yet - has some HIDDEN SINGLES
        Sudoku hardToSolve = new Sudoku("000007018094150700005600000106000000080070020000000904000003800008029140370400000");
        
       
        // Here are the methods you can use here:
        easyToSolve.printPuzzle(); //- prints the puzzle in a more readable form
        // solvePuzzle() - solves the puzzle
        // solvePuzzle(true) - shows you in detail how it solves the puzzle

        //easyToSolve.solvePuzzle(true);
        //hardToSolve.solvePuzzle(true);
    }
}