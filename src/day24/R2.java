package day24;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class R2 {

    public static int getNoOfBlackTiles() throws IOException {
        TreeMap<Integer, ArrayList<Integer>> blackTilesInit = R1.getBlackTiles();
        int minRow = blackTilesInit.firstKey();
        int maxRow = blackTilesInit.lastKey();
        int minCol = Integer.MAX_VALUE;
        int maxCol = Integer.MIN_VALUE;        
        for (List<Integer> valuesForKeyRow : blackTilesInit.values()) {
            // sort values
            if (!valuesForKeyRow.isEmpty()) {
                Collections.sort(valuesForKeyRow);
                int smallestColForKeyRow = valuesForKeyRow.get(0);
                int largestColForKeyRow = valuesForKeyRow.get(valuesForKeyRow.size() - 1);
                minCol = Math.min(smallestColForKeyRow, minCol);
                maxCol = Math.max(largestColForKeyRow, maxCol);
            }
        }
        // create array; add + 1 to include zero value; add 2 cols and 2 rows on either side
        boolean[][] blackTilesOld = new boolean[Math.abs(minRow) + maxRow + 5][Math.abs(minCol) + maxCol + 5]; 
        // copy values from map to array
        for (int i = minRow; i <= maxRow; i++) {
            for (int j = minCol; j <= maxCol; j++) {
                if (blackTilesInit.get(Integer.valueOf(i)).contains(Integer.valueOf(j))) {
                    blackTilesOld[i + Math.abs(minRow) + 2][j + Math.abs(minCol) + 2] = true;
                }
            }
        }
        for (int iterations = 0; iterations < 100; iterations++) {
            boolean[][] blackTilesNew = new boolean[blackTilesOld.length][blackTilesOld[0].length];
            // copy old array to new
            for (int i = 0; i < blackTilesOld.length; i++) {
                for (int j = 0; j < blackTilesOld[0].length; j++) {
                    blackTilesNew[i][j] = blackTilesOld[i][j];
                }
            }
            for (int row = 0; row < blackTilesOld.length; row++) {
                // even rows (in blackTilesInit(R1)!) can only contain even columns, odd rows can only contain odd columns
                /** To have the code work for the example input, change 0 and 1 in the following
                 *  line, because example input has 7 rows (first being -3 in blackTilesInit == odd)
                 *  and puzzle input has 37 rows (first being -18 in blackTilesInit = even).
                 */
                int colForThisRow = row % 2 == 0 ? 0 : 1;
                for (int col = colForThisRow; col < blackTilesOld[0].length; col += 2) {
                    boolean isTileBlack = blackTilesOld[row][col];
                    int noOfAdjBlacks = getNoOfAdjacentBlackTiles(blackTilesOld, row, col);
                    if ( isTileBlack) {
                        if (noOfAdjBlacks == 0 || noOfAdjBlacks > 2) {
                            blackTilesNew[row][col] = false;
                        }
                    } else {
                        if (noOfAdjBlacks == 2) {
                            blackTilesNew[row][col] = true;
                        }
                    }
                }
            }            
            // create new array; again add 2 cols and 2 rows on either side
            boolean[][] extendedArray = new boolean[blackTilesNew.length + 4][blackTilesNew[0].length + 4]; 
            // copy values from blackTilesNew to extendedArray
            for (int i = 0; i < blackTilesNew.length; i++) {
                for (int j = 0; j < blackTilesNew[0].length; j++) {
                    extendedArray[i + 2][j + 2] = blackTilesNew[i][j];
                }
            }
            blackTilesOld = extendedArray;
        }        
        return countNumberOfBlackTiles(blackTilesOld);
    }
    
    public static void printArray(boolean[][] blackTiles) {
        for (boolean[] array : blackTiles) {
            System.out.println(Arrays.toString(array));
        }
    }
    
    public static int countNumberOfBlackTiles(boolean[][] blackTiles) {
        int noOfBlackTiles = 0;
        for (boolean[] row : blackTiles) {
            for (boolean colValForRow : row) {
                if (colValForRow) {
                    noOfBlackTiles++;
                }
            }
        }
        return noOfBlackTiles;
    }

    public static int getNoOfAdjacentBlackTiles(boolean[][] blackTiles, int row, int col) {
        int adjBlacks = 0;
        int rows = blackTiles.length;
        int cols = blackTiles[0].length;
        if (row - 1 >= 0 && col - 1 >= 0) {
            adjBlacks = blackTiles[row - 1][col - 1] ? adjBlacks + 1 : adjBlacks; // nw
        } 
        if (row - 1 >= 0 && col + 1 < cols) {
            adjBlacks = blackTiles[row - 1][col + 1] ? adjBlacks + 1 : adjBlacks; // ne
        }
        if (col - 2 >= 0) {
            adjBlacks = blackTiles[row][col - 2] ? adjBlacks + 1 : adjBlacks; // w
        }
        if (col + 2 < cols) {
            adjBlacks = blackTiles[row][col + 2] ? adjBlacks + 1 : adjBlacks; // e
        }
        if (row + 1 < rows && col - 1 >= 0) {
            adjBlacks = blackTiles[row + 1][col - 1] ? adjBlacks + 1 : adjBlacks; // sw
        }
        if (row + 1 < rows && col + 1 < cols) {
            adjBlacks = blackTiles[row + 1][col + 1] ? adjBlacks + 1 : adjBlacks; // se
        }
        return adjBlacks;
    }
    
    public static void main(String[] args) {
        try {
            System.out.println(getNoOfBlackTiles());
        } catch (IOException e) {
            System.out.println(e);
        }

    }

}
