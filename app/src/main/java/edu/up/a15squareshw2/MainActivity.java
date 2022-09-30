package edu.up.a15squareshw2;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private int boardWidth = 4;
    private int[] squares = new int[15+1];
    private int blank = 16;

    Button[] btnList;

    private boolean gameEnd = false;
    private int pos = 16;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView gameText = findViewById(R.id.gameTitle);

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

        btnList = new Button[]{btn1, btn2, btn3, btn4,
                btn5, btn6, btn7, btn8,
                btn9, btn10, btn11, btn12,
                btn13, btn14, btn15, btn16};

        for (int c = 0; c < btnList.length; c++) {
            Controller controller = new Controller(btnList[c]);
            btnList[c].setOnClickListener(controller);
        }

        // Set appearances
        for (int i = 0; i < btnList.length; i++) {
            btnList[i].setBackgroundColor(Color.parseColor("#000000"));
        }

        shuffle(btnList);

        if (isSolvable()) {
            Log.i("solve", "We can solve this?!!");
        } else {
            Log.i("solve", "We can't solve this...");
        }

        //int num = 0;
        //Log.i("mainActivity", "Adding text to first button.");
        //btn1.setText("" + num);
        //int output = Integer.parseInt(btn1.getText().toString());
        //btn1.setBackgroundColor(Color.parseColor("#FF00FF"));
    }


    // OTHER BASIC METHODS
    protected int setId(int a) {
        // Previous blank is now former position
        pos = blank;

        // Update blank to new position
        blank = a;

        return 0;
    }

    protected int getPos() {
        return blank;
    }

    private void setBlankPos(int i) {
        btnList[i].setText("");
        btnList[i].setBackgroundColor(Color.parseColor("#BBBBBB"));

    }




    // ESSENTIAL METHODS WE NEED!!!!
    protected void shuffle(Button[] btnList) {
        // shuffle values
        // assign to buttons
        //int gridSize = boardWidth * boardWidth - 1;
        int b = 1;
        for (int i = 0; i < squares.length-1; i++) {
            squares[i] = i+1;
        }

        Random rand = new Random();

        for (int i = 0; i < squares.length-1; i++) {
            int randIndex = rand.nextInt(squares.length-1);
            int val = squares[randIndex];
            squares[randIndex] = squares[i];
            squares[i] = val;
        }

        for (int i = 0; i < squares.length-1; i++) {
            btnList[i].setText("" + squares[i]);
            //Log.i("mainActivity", "Checked button ... " + ((i + 1) % squares.length) + " " + squares.length);
        }

        setBlankPos(15);
    }

    protected boolean isSolvable() {
        Log.i("mainActivity", "Start of isSolvable method in MainActivity.");
        int counted = 0;
        for (int i = 0; i < boardWidth*boardWidth; i++) {
            for (int j = 0; j < i; j++) {
                if (squares[j] > squares[i]) {
                    counted++;
                }
            }
        }

        if (counted % 2 == 0) {
            return true;
        } else {
            return false;
        }
    }



}