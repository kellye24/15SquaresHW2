/**
 * Emma L. Kelly
 * Date: 19 September 2022
 * Homework 2: 15 Squares Game
 *
 *
 */
package edu.up.a15squareshw2;

import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Controller implements View.OnClickListener {

    // Variables for tracking positions and arrays for the grid
    private int pos = 0;
    private int blankPos = 16;
    private int clickedPos = 0;
    private int[] grid = new int[15];
    private Button[] btns;
    private boolean canBeSolved;
    private int counter = 0;
    // Create list of buttons IDs
    private int[] btnIds = {R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4,
            R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8,
            R.id.btn9, R.id.btn10, R.id.btn11, R.id.btn12,
            R.id.btn13, R.id.btn14, R.id.btn15, R.id.btn16, };


    // Construct controller characteristics passing button through
    public Controller(Button[] btns) {
        this.btns = btns;
        //counter++;
        //setButtons(btns);
    }

    @Override
    public void onClick(View view) {

        // Find clicked button by int position
        clickedPos = findBtn(view);
        // If this button is in a valid position validPos will be true
        boolean validPos = findPossible(clickedPos, this.blankPos);
        // Report findings up to now
        Log.i("solve", "Position is: " + clickedPos + " and is " + validPos + " Clicked: " + clickedPos);

        // If a valid position exists at this position
        // Then proceed with swapping the blank and current position values
        // to update to current position, updating squares.
        if (validPos == true && clickedPos != blankPos) {
            // Return button list to main
            createNewBlankNext(clickedPos);


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

        // Loop through IDs to find button clicked
        for (int i = 0; i < btnIds.length; i++) {
            if (view.getId() == btnIds[i]) {
                // Return clicked button ID
                return i+1;
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
        //btnList = btn;
    }

    /**
     * Set new blank position and update visuals
     *
     * @param clickedPos
     */
    public void createNewBlankNext(int clickedPos) {
        // Issue faced with updating buttons visually
        // The idea was that controller would send back the clickedPosition
        // and current blank position to the Main, and Main would
        // then update the buttons visually by background color and text.
        // Unfortunately, after many hours of attempting to find ways
        // around this, I couldn't find a solution, so the game
        // currently does not update positions as intended...

        String lastPos = (String) btns[clickedPos-1].getText();

        // Set clicked position to new blank text ""
        btns[clickedPos-1].setText("");
        btns[clickedPos-1].setBackgroundColor(Color.parseColor("#FFFFFF"));

        // Set blank position to non-blank position
        btns[blankPos-1].setText(""+ lastPos);

        int btnIndex = Integer.parseInt(""+btns[blankPos-1].getText())-1;

        if (blankPos-1 == btnIndex) {
            btns[blankPos-1].setBackgroundColor(Color.parseColor("#FF00FF"));
        } else {
            btns[blankPos - 1].setBackgroundColor(Color.parseColor("#000000"));
        }

        blankPos = clickedPos;

        // The intended code to update that is currently facing issues:

        // Setting up new blank button
        // Sets to gray background
        // btnList[clickedPos].setBackgroundColor(Color.parseColor("#BBBBBB"));
        // Sets text to nothing
        // btnList[clickedPos].setText("");

        // Setting up non-blank button
        // btnList[blankPos].setBackgroundColor(Color.parseColor("#000000"));
        // Sets text to clicked position's previous value
        // btnList[blankPos].setText("" + clickedPos);

        // Properties and operations that can be done on a button for updating
        //btnList[clickedPos].setBackgroundColor(Color.parseColor("#BBBBBB"));
        //btn1.setText("" + num);
        //int output = Integer.parseInt(btn1.getText().toString());
        //btn1.setBackgroundColor(Color.parseColor("#FF00FF"));

        // ++ update color (black, white, green)
    }
}
