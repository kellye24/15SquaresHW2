/**
 * Emma L. Kelly
 * Date started: 19 September 2022
 * Due date: 30 September 2022
 * Homework 2: 15 Squares Game
 *
 */

package edu.up.a15squareshw2;
import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import java.util.Timer;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private int boardWidth = 4;
    private int[] squares = new int[15+1];
    private int blank = 15;
    private int pos = 16;
    private boolean gameEnd = false;

    Button[] btnList = new Button[15];

    /**
     * Begin the game, create buttons, create the grid with random values,
     * and stylize buttons based on blank or not.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create game title / text
        TextView gameText = findViewById(R.id.gameTitle);

        // Create buttons
        Button btn1 = findViewById(R.id.btn1);
        Button btn2 = findViewById(R.id.btn2);
        Button btn3 = findViewById(R.id.btn3);
        Button btn4 = findViewById(R.id.btn4);
        Button btn5 = findViewById(R.id.btn5);
        Button btn6 = findViewById(R.id.btn6);
        Button btn7 = findViewById(R.id.btn7);
        Button btn8 = findViewById(R.id.btn8);
        Button btn9 = findViewById(R.id.btn9);
        Button btn10 = findViewById(R.id.btn10);
        Button btn11 = findViewById(R.id.btn11);
        Button btn12 = findViewById(R.id.btn12);
        Button btn13 = findViewById(R.id.btn13);
        Button btn14 = findViewById(R.id.btn14);
        Button btn15 = findViewById(R.id.btn15);
        Button btn16 = findViewById(R.id.btn16);

        // Create array of buttons
        this.btnList = new Button[]{btn1, btn2, btn3, btn4,
                btn5, btn6, btn7, btn8,
                btn9, btn10, btn11, btn12,
                btn13, btn14, btn15, btn16};

        // Creature empty controller object
        Controller controller = new Controller(btnList);
        // Set each button to have onClickListener
        // in the controller.
        for (int c = 0; c < btnList.length; c++) {
            //add button to contoller
            //controller = new Controller(btnList[c]);
            btnList[c].setOnClickListener(controller);
        }

        // Set appearances
        for (int i = 0; i < btnList.length; i++) {
            btnList[i].setBackgroundColor(Color.parseColor("#000000"));
        }

        // Set last square to blank
        btnList[15].setBackgroundColor(Color.parseColor("#BBBBBB"));
        btnList[15].setText("");

        /**
         * External Citation for randomize()
         * Date: 20 September 2022
         * Problem: Finding a way to continually check solvability till true.
         * Resource: https://www.geeksforgeeks.org/check-instance-15-puzzle-solvable/
         */
        // Continues randomizing the grid until it can be solved
        do {
            // Randomly assign values to each square in
            // the array "grid"
            this.squares = randomize();
            // Send this grid to the controller
            controller.setGrid(this.squares);
            // Send the list of buttons to controller
            controller.setButtons(this.btnList);

            // Not necessary (as of now), but send solvability
            // status to controller (T/F if solvable).
            controller.canBeSolved(isSolvable());
        } while (!isSolvable());

        // Storage comments for other things we can use for buttons
        //// Log.i("mainActivity", "Adding text to first button.");
        //// btn1.setText("" + 1);
        //// int output = Integer.parseInt(btn1.getText().toString());
        //// btn1.setBackgroundColor(Color.parseColor("#FF00FF"));
    }

    /**
     * External Citation for randomize()
     * Date: 20 September 2022
     * Problem: Finding a way to quickly randomize array.
     * Resource: https://stackoverflow.com/questions/2450954/how-to-randomize-shuffle-a-javascript-array
     */

    /**
     * Create grid, randomizes the values in it, assigns randomized
     * values to buttons visually, and sends back new randomized grid.
     *
     * @return grid
     */
    protected int[] randomize() {
        // Our "grid" of values
        int[] grid = new int[15];
        // Length of grid
        int length = squares.length - 1;

        int b = 1;
        // Assign numbers 1-15 in order to grid
        for (int i = 0; i < length; i++) {
            squares[i] = i+1;
        }

        // Create random object
        Random rand = new Random();

        // Randomize square array
        for (int i = 0; i < length; i++) {
            int randIndex = rand.nextInt(length);
            int val = squares[randIndex];
            squares[randIndex] = squares[i];
            squares[i] = val;
        }

        // Assign randomized array values to button visuals and grid array
        for (int i = 0; i < length; i++) {
            // Assign numbers from randomized array to buttons
            btnList[i].setText("" + squares[i]);
            // Store in other grid array
            grid[i] = squares[i];
        }
        // Return randomized array of numbers up to the second to last square
        return grid;
    }

    /**
     * External Citation for isSolvable()
     * Date: 22 September 2022
     * Problem: Finding a way to prove whether an array is solvable
     * Resource: http://mathworld.wolfram.com/15Puzzle.html
     * Resource 2: https://www.geeksforgeeks.org/check-instance-8-puzzle-solvable/
     * /

    /**
     * Checks if the current grid can be solved
     *
     * @return true if the grid is solvable and false otherwise
     */
    protected boolean isSolvable() {
        // Create counter for number of inversions
        // Inversions will determine whether grid is solvable
        int counted = 0;
        for (int i = 0; i < boardWidth*boardWidth-1; i++) {
            for (int j = 0; j < i; j++) {
                // Inversions are identified as any such situation
                // where the previous value is greater than the current.
                if (this.squares[j] > this.squares[i]) {
                    counted++;
                }
            }
        }

        // If the number of greater values is divisible by 2 it is solvable
        if (counted % 2 == 0) {
            Log.i("solve", "We can solve this!");
            return true;
        } else {
            Log.i("solve", "We can't solve this...");
            return false;
        }
    }


}