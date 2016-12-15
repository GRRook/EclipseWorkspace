import java.util.Arrays;

public class Sudoku
{
    private byte[][] myPuzzle = new byte[][]
    {
        { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
        { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
        { 0, 0, 0, 0, 0, 0, 0, 0, 0 },

        { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
        { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
        { 0, 0, 0, 0, 0, 0, 0, 0, 0 },

        { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
        { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
        { 0, 0, 0, 0, 0, 0, 0, 0, 0 }
    };

    public Sudoku(String incomingPuzzle)
    {
        if ( incomingPuzzle.length() != 81 )
        {
            System.out.println("Invalid Sudoku puzzle syntax. Should be 81 numbers.");
            System.exit(1);
        }

        for ( int row = 0; row <= 8; row++ )
        {
            for ( int column = 0; column <= 8; column++ )
            {
                myPuzzle[row][column] = convertCharToByte(incomingPuzzle.charAt(row*9+column));
            }
        }

        if ( puzzleIsValid() == false )
        {
            System.out.println("Illegal puzzle.");
            System.exit(1);
        }
    }

    public void printPuzzle()
    {
        for ( byte y = 0; y <=8; y++ )
        {
            for ( byte x = 0; x <=8; x++ )
            {
                System.out.print(myPuzzle[y][x] + " ");
                if ( x == 2 || x == 5 )
                {
                    System.out.print("  ");
                }
            }
            System.out.println();
            if ( y == 2 || y == 5 || y == 8 )
            {
                System.out.println();
            }
        }
    }

    public void solvePuzzle()
    {
        solvePuzzle(false);
    }

    public void solvePuzzle(boolean showDetails)
    {
        // print puzzle in the console
        System.out.println("Unsolved puzzle:");
        printPuzzle();

        byte cyclesElapsed = 1;

        // iterate through each square in a 9x9 sudoku puzzle and try to solve the square
        while ( true )
        {
            byte squaresSolved = 0;

            // We need some way to pause execution if the puzzle either becomes solved or is discovered to be unsolvable.
            boolean somethingChanged = false;

            // check each square
            for ( byte square = 1; square <= 9; square++ )
            {
                // when we do our x-ray checks later we will need to know what row numbers and what column numbers to check
                byte[] coordinates = getSquareCoordinates(square, (byte) 1);
                byte checkThisRowFirst = coordinates[1];
                byte checkThisColumnFirst = coordinates[0];

                // make a boolean array to keep track of whether or not a coordinate in the square can be a solution for that numToCheck
                // true = possibly a solution, false = not a solution
                // once we are down to 1 true and 8 falses, we have solved that numToCheck and can update the box in myPuzzle
                boolean[] possiblyContainsNum = new boolean[] {true, true, true, true, true, true, true, true, true};

                // eliminate all occupied cells in the square
                // iterate through the 9 boxes in the square and plug it into the getSquareCoordinates method
                for ( byte i = 1; i <=9; i++ )
                {
                    byte[] coordinates2 = getSquareCoordinates(square, i);

                    if ( myPuzzle[coordinates2[1]][coordinates2[0]] != 0 )
                    {
                        possiblyContainsNum[i-1] = false;
                    }
                }

                // check each number 1 through 9
                // don't go box by box, go number by number
                for ( byte numToCheck = 1; numToCheck <= 9; numToCheck++ )
                {
                    // does the square contain this number already?
                    // if yes, skip. if no, try to solve
                    if ( squareContainsNumber(square, numToCheck) == false )
                    {
                        // I had possiblyContainsNum inside this loop for awhile, but then I realized it is
                        // more efficient to just compute it once and store it in memory. We'll make a new
                        // possiblyContainsNum for this numToCheck, copying the values of the original calculation
                        // (occupied squares) to start us off
                        boolean[] possiblyContainsNum2 = Arrays.copyOf(possiblyContainsNum, possiblyContainsNum.length);

                        // eliminate x-rays from rows and columns
                        // check the entire row or column (all 9 boxes), easier to code than 6 boxes

                        // row 1
                        if ( rowContainsNumber(checkThisRowFirst, numToCheck) == true )
                        {
                            possiblyContainsNum2[0] = false;
                            possiblyContainsNum2[1] = false;
                            possiblyContainsNum2[2] = false;
                        }

                        // row 2
                        if ( rowContainsNumber((byte) (checkThisRowFirst+1), numToCheck) == true )
                        {
                            possiblyContainsNum2[3] = false;
                            possiblyContainsNum2[4] = false;
                            possiblyContainsNum2[5] = false;
                        }

                        // row 3
                        if ( rowContainsNumber((byte) (checkThisRowFirst+2), numToCheck) == true )
                        {
                            possiblyContainsNum2[6] = false;
                            possiblyContainsNum2[7] = false;
                            possiblyContainsNum2[8] = false;
                        }

                        // column 1
                        if ( columnContainsNumber(checkThisColumnFirst, numToCheck) == true )
                        {
                            possiblyContainsNum2[0] = false;
                            possiblyContainsNum2[3] = false;
                            possiblyContainsNum2[6] = false;
                        }

                        // column 2
                        if ( columnContainsNumber((byte) (checkThisColumnFirst+1), numToCheck) == true )
                        {
                            possiblyContainsNum2[1] = false;
                            possiblyContainsNum2[4] = false;
                            possiblyContainsNum2[7] = false;
                        }

                        // column 3
                        if ( columnContainsNumber((byte) (checkThisColumnFirst+2), numToCheck) == true )
                        {
                            possiblyContainsNum2[2] = false;
                            possiblyContainsNum2[5] = false;
                            possiblyContainsNum2[8] = false;
                        }

                        // check possiblyContainsNum and see how many "true" cells there are
                        // if "true" cells == 1 then we have solved that numToCheck in that square
                        // go ahead and update myPuzzle[][]
                        byte counter = 0;
                        byte k = 0; // this will store the last value of j that was true
                        for ( byte j = 0; j <= 8; j++ )
                        {
                            if ( possiblyContainsNum2[j] == true )
                            {
                                counter++;
                                k = j;
                            }
                        }

                        if ( counter == 1 )
                        {
                            byte[] coordinates3 = getSquareCoordinates(square, (byte) (k+1));
                            myPuzzle[coordinates3[1]][coordinates3[0]] = numToCheck;
                            somethingChanged = true;
                            squaresSolved++;
                        }
                    }
                }
            }

            if ( showDetails == true )
            {
                System.out.println("Iteration " + cyclesElapsed + " complete. Solved " + squaresSolved + " squares.");
                printPuzzle();
            }
            cyclesElapsed++;

            // If no cells were solved during this iteration, that means that the puzzle is completely solved, is completely
            // unsolvable, or is unsolvable using just this technique. Time to exit the loop.
            if ( somethingChanged == false )
            {
                break;
            }
        }

        // do a quick check and make sure the puzzle is solved
        // if it isn't, throw an unsolvable puzzle error
        if ( puzzleIsSolved() == false )
        {
            System.out.println("Unsolvable using just the LAST CANDIDATE technique. More advacned techniques are needed.");
            System.out.println("Try plugging the 81 digit number into the sudoku solver at http://www.sudokuwiki.org/sudoku.htm");
            System.out.println();
            return;
        }

        // print puzzle in the console
        System.out.println("Solved puzzle:");
        printPuzzle();
    }

    private byte convertCharToByte(char f)
    {
        switch ( f )
        {
            case '0':
                return 0;
            case '1':
                return 1;           
            case '2':
                return 2;                   
            case '3':
                return 3;
            case '4':
                return 4;
            case '5':
                return 5;
            case '6':
                return 6;
            case '7':
                return 7;
            case '8':
                return 8;
            case '9':
                return 9;
            default:
                System.out.println("Invalid Sudoku puzzle syntax. Should be 81 numbers.");
                System.exit(1);
        }

        return 0; // the compiler made me put this, logically it should be unreachable
    }

    private boolean puzzleIsValid()
    {
        // 1) check each row and make sure there is only 1 number per row
        for ( byte y = 0; y <=8; y++ )
        {
            // we need a new oneThroughNine for each row, so we'll put this inside the "y" loop
            boolean[] oneThroughNine = new boolean[] {false, false, false, false, false, false, false, false, false};

            for ( byte x = 0; x <=8; x++ )
            {
                if ( myPuzzle[y][x] != 0 )
                {
                    if ( oneThroughNine[myPuzzle[y][x] - 1] == false )
                    {
                        oneThroughNine[myPuzzle[y][x] - 1] = true;
                    }
                    else
                    {
                        return(false);
                    }
                }
            }
        }

        // 2) check each column and make sure there is only 1 number per column
        for ( byte x = 0; x <=8; x++ )
        {
            // we need a new oneThroughNine for each column, so we'll put this inside the "y" loop
            boolean[] oneThroughNine = new boolean[] {false, false, false, false, false, false, false, false, false};

            for ( byte y = 0; y <=8; y++ )
            {
                if ( myPuzzle[y][x] != 0 )
                {
                    if ( oneThroughNine[myPuzzle[y][x] - 1] == false )
                    {
                        oneThroughNine[myPuzzle[y][x] - 1] = true;
                    }
                    else
                    {
                        return(false);
                    }
                }
            }
        }

        // 3) check each square and make sure there is only 1 number per square
        for ( byte square = 1; square <=9; square++ )
        {
            // we need a new oneThroughNine for each square, so we'll put this inside the "y" loop
            boolean[] oneThroughNine = new boolean[] {false, false, false, false, false, false, false, false, false};

            for ( byte i = 1; i <=9; i++ )
            {
                byte[] coordinates = getSquareCoordinates(square, i);

                if ( myPuzzle[coordinates[0]][coordinates[1]] != 0 )
                {
                    if ( oneThroughNine[myPuzzle[coordinates[0]][coordinates[1]] - 1] == false )
                    {
                        oneThroughNine[myPuzzle[coordinates[0]][coordinates[1]] - 1] = true;
                    }
                    else
                    {
                        return(false);
                    }
                }
            }
        }

        return(true);
    }

    private boolean puzzleIsSolved()
    {
        for ( byte y = 0; y <= 8; y++ )
        {
            for ( byte x = 0; x <= 8; x++ )
            {
                if ( myPuzzle[y][x] == 0 )
                {
                    return(false);
                }
            }
        }

        return(true);
    }

    private byte[] getSquareCoordinates(byte squareNumber, byte i)
    {
        byte[] startCoordinates = null;

        switch ( squareNumber )
        {
            case 1:
                startCoordinates = new byte[]{0,0};
                break;
            case 2:
                startCoordinates = new byte[]{3,0};
                break;
            case 3:
                startCoordinates = new byte[]{6,0};
                break;
            case 4:
                startCoordinates = new byte[]{0,3};
                break;
            case 5:
                startCoordinates = new byte[]{3,3};
                break;
            case 6:
                startCoordinates = new byte[]{6,3};
                break;
            case 7:
                startCoordinates = new byte[]{0,6};
                break;
            case 8:
                startCoordinates = new byte[]{3,6};
                break;
            case 9:
                startCoordinates = new byte[]{6,6};
                break;
        }

        byte[] finalCoordinates = null;

        // let's check the square in this order:
        //   1 2 3
        //   4 5 6
        //   7 8 9
        switch ( i )
        {
            case 1:
                finalCoordinates = new byte[] {startCoordinates[0], startCoordinates[1]};
                break;
            case 2:
                finalCoordinates = new byte[] {(byte) (startCoordinates[0]+1), startCoordinates[1]};
                break;
            case 3:
                finalCoordinates = new byte[] {(byte) (startCoordinates[0]+2), startCoordinates[1]};
                break;
            case 4:
                finalCoordinates = new byte[] {startCoordinates[0], (byte) (startCoordinates[1]+1)};
                break;
            case 5:
                finalCoordinates = new byte[] {(byte) (startCoordinates[0]+1), (byte) (startCoordinates[1]+1)};
                break;
            case 6:
                finalCoordinates = new byte[] {(byte) (startCoordinates[0]+2), (byte) (startCoordinates[1]+1)};
                break;
            case 7:
                finalCoordinates = new byte[] {startCoordinates[0], (byte) (startCoordinates[1]+2)};
                break;
            case 8:
                finalCoordinates = new byte[] {(byte) (startCoordinates[0]+1), (byte) (startCoordinates[1]+2)};
                break;
            case 9:
                finalCoordinates = new byte[] {(byte) (startCoordinates[0]+2), (byte) (startCoordinates[1]+2)};
                break;
        }

        return finalCoordinates;
    }

    private boolean squareContainsNumber(byte square, byte numToCheck)
    {
        for ( byte i = 1; i <= 9; i++ )
        {
            byte[] coordinates = getSquareCoordinates(square, i);

            if ( myPuzzle[coordinates[1]][coordinates[0]] == numToCheck )
            {
                return(true);
            }
        }

        return(false);
    }

    private boolean rowContainsNumber(byte rowToCheck, byte numToCheck)
    {
        for ( byte column = 0; column <= 8; column++)
        {
            if ( myPuzzle[rowToCheck][column] == numToCheck )
            {
                return(true);
            }
        }

        return(false);
    }

    private boolean columnContainsNumber(byte columnToCheck, byte numToCheck)
    {
        for ( byte row = 0; row <= 8; row++)
        {
            if ( myPuzzle[row][columnToCheck] == numToCheck )
            {
                return(true);
            }
        }

        return(false);
    }
}