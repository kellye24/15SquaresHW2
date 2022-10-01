/**
 * Emma L. Kelly
 * Date: 19 September 2022
 * Homework 2: 15 Squares Game
 *
 *
 */
package edu.up.a15squareshw2;

import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Controller extends MainActivity implements View.OnClickListener {

    // Variables for tracking positions and arrays for the grid
    private int pos = 0;
    private int blankPos = 16;
    private int clickedPos = 0;
    private int[] grid = new int[15];
    private Button[] btns = new Button[15];
    private boolean canBeSolved;
    private int counter = 0;

    // Construct controller characteristics passing button through
    public Controller(Button btn) {
        btns[counter] = btn;
        counter++;
        setButtons(btns);
    }

    @Override
    public void onClick(View view) {

        // Creating main object for reference
        MainActivity main = new MainActivity();

        // Find clicked button by int position
        clickedPos = findBtn(view);
        // If this button is in a valid position validPos will be true
        boolean validPos = findPossible(clickedPos, this.blankPos);
        // Report findings up to now
        Log.i("solve", "Position is: " + this.pos + " and is " + validPos + " Clicked: " + clickedPos);

        // If a valid position exists at this position
        // Then proceed with swapping the blank and current position values
        // to update to current position, updating squares.
        if (validPos == true && clickedPos != blankPos) {
            // Return button list to main
            createNewBlankNext(clickedPos, blankPos);

            // Properties and operations that can be done on a button for updating
            //btnList[clickedPos].setBackgroundColor(Color.parseColor("#BBBBBB"));
            //btn1.setText("" + num);
            //int output = Integer.parseInt(btn1.getText().toString());
            //btn1.setBackgroundColor(Color.parseColor("#FF00FF"));
        }
    }

    /**
     * Find whether the clicked position is a valid direction based on the
     * current blank position in up, down, left, and right directions.
     *
     * @param c
     * @param blank
     * @return true if clicked button is a valid path and false otherwise
     */
    private boolean findPossible(int c, int blank) {
        boolean valid = false;
        // Up
        if (c == blank - 4 && blank - 4 > 0) {
            valid = true;
        }
        // Down
        if (c == blank + 4 && blank + 4 < 16) {
            valid = true;
        }
        // Left
        if (c == (blank - 1) && (blank - 1) % 4 != 0) {
            valid = true;
        }
        // Right
        if (c == (blank + 1) && blank % 4 != 0) {
            valid = true;
        }

        Log.i("solve", "Blank: " + blank + " Valid: " + valid + " Pos: " + c);
        return valid;
    }

    /**
     * Determine which button was pressed based on id sent by view
     * and identify the button by that id.
     *
     * @param view
     * @return i, button id
     */
    public int findBtn(View view) {
        // Create list of buttons IDs
        int[] btnIds = {R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4,
                        R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8,
                        R.id.btn9, R.id.btn10, R.id.btn11, R.id.btn12,
                        R.id.btn13, R.id.btn14, R.id.btn15, R.id.btn16, };

        // Loop through IDs to find button clicked
        for (int i = 1; i < 16; i++) {
            if (view.getId() == btnIds[i-1]) {
                // Return clicked button ID
                return i;
            }
        }

        // If we somehow didn't click any buttons, return 0
        return 0;
    }

    /**
     * Updates global grid
     *
     * @param gridVals
     */
    public void setGrid(int[] gridVals) {
        this.grid = grid;
    }

    /**
     * Fetches global determination of whether grid can be solved
     *
     * @param solvable
     */
    public void canBeSolved(boolean solvable) {
        this.canBeSolved = solvable;
    }

    /**
     * Send sets button list to global button list
     *
     * @param btn
     */
    public void setButtons(Button[] btn) {
        btnList = btn;
    }
}
