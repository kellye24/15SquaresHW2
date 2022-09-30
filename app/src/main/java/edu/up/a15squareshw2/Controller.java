package edu.up.a15squareshw2;

import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Controller extends MainActivity implements View.OnClickListener {
    private int moves = 0;
    public int pos = 16;
    private int[] vals = new int[15];
    public int up, down, left, right;
    private int blankPos = 16;

    //Button[] btns = new Button[15];
    private int currId, nextId = R.id.btn16;
    MainActivity main = new MainActivity();

    public Controller(Button btn) {

    }

    @Override
    public void onClick(View view) {

        int id = 0;


        // Update to latest position
        pos = main.getPos();
        Log.i("solve", "Position is: " + pos);

        // Determine directions from this position that can be taken
        up = findPossible(pos, 1);
        down = findPossible(pos, 2);
        left = findPossible(pos, 3);
        right = findPossible(pos, 4);

        // Find the button position and send it back in main

        //btn12.setBackgroundColor(Color.parseColor("#BBBBBB"));
        //int prevVal = Integer.parseInt(btns[blankPos].getText().toString());
        //int newVal = Integer.parseInt(btns[pos].getText().toString());
        //btns[pos].setText("");
        //btns[blankPos].setText("" + pos);
        //btns[pos].setBackgroundColor(Color.parseColor("#000000"));
        //btns[blankPos].setBackgroundColor(Color.parseColor("#BBBBBB"));
        //blankPos = pos;
        //int output = Integer.parseInt(btn1.getText().toString());
        //btn1.setBackgroundColor(Color.parseColor("#FF00FF"));
        //Log.i("solve", "Position found -- " + pos + " ID: " + id);
        //int num = 0;
        //Log.i("mainActivity", "Adding text to first button.");
        //btn1.setText("" + num);
        //int output = Integer.parseInt(btn1.getText().toString());
        //btn1.setBackgroundColor(Color.parseColor("#FF00FF"));

        if (id != 0) {
            Log.i("solve", "ID: " + id + " Pos: " + pos);
        }
    }

    private int findPossible(int pos, int i) {
        int p = pos;
        boolean[] paths = new boolean[4];

        switch(i) {
            case 1:
                // Up
                if (p - 4 > 0) {
                    up = p - 4;
                    paths[0] = true;
                    //Log.i("solve", "FIRST Left: " + left + " Right: " + right + " Up: " + up + " Down: " + down);
                }
                return up;
            case 2:
                // Down
                if (p + 4 < 16) {
                    down = p + 4;
                    paths[1] = true;
                    //Log.i("solve", "FIRST Left: " + left + " Right: " + right + " Up: " + up + " Down: " + down);
                }
                return down;
            case 3:
                // Left
                if ((p - 1) % 4 != 0) {
                    left = p - 1;
                    paths[2] = true;
                    //Log.i("solve", "FIRST Left: " + left + " Right: " + right + " Up: " + up + " Down: " + down);
                }
                return left;
            case 4:
                // Right
                if (p % 4 != 0) {
                    right = p + 1;
                    paths[3] = true;
                    //Log.i("solve", "FIRST Left: " + left + " Right: " + right + " Up: " + up + " Down: " + down);
                }
                return right;
        }

        return 0;
    }

    public int setPos(int p) {
        p = pos;
        return p;
    }

    public int findBtn(View view, int id) {
        Log.i("solve", "SECOND Up: " + up + " Down: " + down + " Left: " + left + " Right: " + right);



        return id;
    }

    private void setID(int idSet) {
        nextId = idSet;
    }
}
