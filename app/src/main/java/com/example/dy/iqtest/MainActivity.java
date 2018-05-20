package com.example.dy.iqtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

/**
 * IQ Test quiz app
 * @author ShyCoder
 * @authoruri https://shycoder.com
 */

public class MainActivity extends AppCompatActivity {
    private String mName;
    private int mPoints = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void displayQuestion(View v) {
        EditText name = findViewById(R.id.name);
        View welcomeContent = findViewById(R.id.welcome_content);
        View questionContent = findViewById(R.id.question_content);

        // set user's name
        mName = name.getText().toString();

        // show questions
        welcomeContent.setVisibility(View.GONE);
        questionContent.setVisibility(View.VISIBLE);
    }

    /**
     * calculate the points and display the result to user
     * @param v
     */
    public void getResult(View v) {
        Button submit = findViewById(R.id.submit);

        // disable submit button to prevent multiple submissions
        submit.setEnabled(false);

        // calculate point
        calculatePoint();

        Toast.makeText(this, getString(R.string.result_hint), Toast.LENGTH_SHORT).show();

        // display the result after 2sec
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                displayResult();
                mPoints = 0;
            }
        }, 2000);
    }

    /**
     * make the result disappear and reshow the questions
     * @param v
     */
    public void retakeQuiz(View v) {
        View resultContent = findViewById(R.id.result_container);
        View questionContent = findViewById(R.id.question_content);
        TextView points = findViewById(R.id.points);
        TextView resultText = findViewById(R.id.result_text);
        Button submit = findViewById(R.id.submit);

        resultContent.setVisibility(View.GONE);
        questionContent.setVisibility(View.VISIBLE);

        resultText.setText("");
        points.setText(getString(R.string.points));

        // enable button back
        submit.setEnabled(true);
    }

    /**
     * get custom result messages based on user's point
     */
    private void displayResult() {
        View resultContent = findViewById(R.id.result_container);
        View questionContent = findViewById(R.id.question_content);

        TextView resultText = findViewById(R.id.result_text);
        TextView points = findViewById(R.id.points);

        // make question disappear and show result
        questionContent.setVisibility(View.GONE);
        resultContent.setVisibility(View.VISIBLE);

        if (mPoints > 6) {

            // for geniuses
            resultText.append(getString(R.string.genius) + " " + mName + "!");
        } else if (mPoints > 4 && mPoints < 7) {

            // for average users
            resultText.append(getString(R.string.average) + " " + mName + "!");
        } else if (mPoints == 4) {

            // for users that are not bad
            resultText.append(getString(R.string.not_bad) + " " + mName + "!");
        } else {

            // for users that needs work
            resultText.append(getString(R.string.needs_work) + " " + mName + "!");
        }

        // show the points summary
        points.append(" " + mPoints + "/10");
    }

    /**
     * check if correct answers were picked and calculate user's points
     * @return int point
     */
    private int calculatePoint() {
        RadioButton ansOne = findViewById(R.id.right_ans_1);
        RadioButton ansTwo = findViewById(R.id.right_ans_2);
        RadioButton ansThree = findViewById(R.id.right_ans_3);

        CheckBox ansFour = findViewById(R.id.right_ans_4);
        CheckBox wrongOne = findViewById(R.id.wrong_1);
        CheckBox wrongTwo = findViewById(R.id.wrong_2);
        CheckBox wrongThree = findViewById(R.id.wrong_3);

        RadioButton ansFive = findViewById(R.id.right_ans_5);


        if (ansOne.isChecked()) {
            mPoints += 2;
        }

        if (ansTwo.isChecked()) {
            mPoints += 2;
        }

        if (ansThree.isChecked()) {
            mPoints += 2;
        }

        if (ansFour.isChecked()) {
            mPoints += 2;
        }

        if (ansFive.isChecked()) {
            mPoints += 2;
        }

        // make sure the question four is answered properly.
        // if one wrong answer is selected, deduct points by 1
        if (mPoints > 0) {
            if ((wrongOne).isChecked() || (wrongTwo).isChecked() || (wrongThree).isChecked()) {
                mPoints -= 1;
            }
        }

        return mPoints;
    }

}
