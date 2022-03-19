package com.example.mioquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;


public class MainActivity extends AppCompatActivity {

    private TextView questionTV, questionNumberTV, Info;
    private Button option1Btn, option2Btn, option3Btn, option4Btn, Login;
    private ArrayList<QuizModal> quizModalArrayList;
    private EditText Name;
    private EditText Password;
    private int counter = 10;
    Random random;
    int currentScore = 0, questionAttempted = 1, currentPos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        questionTV = findViewById(R.id.idTVQuestion);
        questionNumberTV = findViewById(R.id.idTVQuestionAttempted);
        option1Btn = findViewById(R.id.idBtnOption1);
        option2Btn = findViewById(R.id.idBtnOption2);
        option3Btn = findViewById(R.id.idBtnOption3);
        option4Btn = findViewById(R.id.idBtnOption4);
        quizModalArrayList = new ArrayList<>();
        random = new Random();
        getQuizQuestion(quizModalArrayList);
        currentPos = random.nextInt(quizModalArrayList.size());
        setDataToViews(currentPos);

        option1Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quizModalArrayList.get(currentPos).getAnswer().trim().toLowerCase().equals(option1Btn.getText().toString().trim().toLowerCase())) {
                    currentScore++;
                }
                questionAttempted++;
                currentPos = random.nextInt(quizModalArrayList.size());
                setDataToViews(currentPos);
            }
        });

        option2Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (quizModalArrayList.get(currentPos).getAnswer().trim().toLowerCase().equals(option2Btn.getText().toString().trim().toLowerCase())) {
                    currentScore++;
                }
                questionAttempted++;
                currentPos = random.nextInt(quizModalArrayList.size());
                setDataToViews(currentPos);
            }
        });

        option3Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (quizModalArrayList.get(currentPos).getAnswer().trim().toLowerCase().equals(option3Btn.getText().toString().trim().toLowerCase())) {
                    currentScore++;
                }
                questionAttempted++;
                currentPos = random.nextInt(quizModalArrayList.size());
                setDataToViews(currentPos);
            }
        });

        option4Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (quizModalArrayList.get(currentPos).getAnswer().trim().toLowerCase().equals(option4Btn.getText().toString().trim().toLowerCase())) {
                    currentScore++;
                }
                questionAttempted++;
                currentPos = random.nextInt(quizModalArrayList.size());
                setDataToViews(currentPos);
            }
        });

        configureProfileButton();
        
    }

    private void configureProfileButton() {
        ImageButton profileButton = (ImageButton) findViewById(R.id.profBtn);
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                startActivity(new Intent(MainActivity.this, LoginPage.class));
            }
        });
    }


    private void showBottomSheet() {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(MainActivity.this);
        View bottomSheetView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.score_bottom_sheet, (LinearLayout) findViewById(R.id.idLLScore));
        TextView scoreTV = bottomSheetView.findViewById(R.id.idTVScore);
        Button restartQuizBtn = bottomSheetView.findViewById(R.id.idBtnRestart);
        scoreTV.setText("Your Score is \n" + currentScore + "/10");
        restartQuizBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentPos = random.nextInt(quizModalArrayList.size());
                setDataToViews(currentPos);
                questionAttempted = 1;
                currentScore = 0;
                bottomSheetDialog.dismiss();
            }
        });
        bottomSheetDialog.setCancelable(false);
        bottomSheetDialog.setContentView(bottomSheetView);
        bottomSheetDialog.show();
    }


    private void getQuizQuestion(ArrayList<QuizModal> quizModalArrayList) {
        quizModalArrayList.add(new QuizModal("How tall is the eiffel tower?", "100m", "200m", "300m", "500.", "300m"));
        quizModalArrayList.add(new QuizModal("How long is the chinese wall?", "21,196km", "32,399km", "10,000km", "8km", "21,196km"));
        quizModalArrayList.add(new QuizModal("Which is the deepest sea?", "Baikalsee", "Bodensee", "Zürichsee", "Atlantik", "Baikalsee"));
        quizModalArrayList.add(new QuizModal("Which is the most expensive car?", "Rolly-Royce Sweptail", "Koenigsegg CCXR", "Lamborghini Veneo", "Bugatti La Voiture Noire", "Bugatti La Voiture Noire"));
        quizModalArrayList.add(new QuizModal("How tall is the tallest human ever lived?", "2.3m", "2.41m", "2.51m", "2.55m", "2.51m"));
        quizModalArrayList.add(new QuizModal("Which is the sharpest chili on earth?", "Carolina Reaper", "Dragons Breath", "Habanero", "Thai Chili", "Carolina Reaper"));
        quizModalArrayList.add(new QuizModal("Which is the biggest crypto currency (Feb 2022)?", "BTC", "ETH", "USDT", "BNB", "BTC"));
        quizModalArrayList.add(new QuizModal("In which year Bill Gates was the richest man alive for the first time?", "2002", "2020", "2014", "2005", "2014"));
        quizModalArrayList.add(new QuizModal("How many billionaires where counted 2021?", "3000", "556", "785", "2755", "2755"));
        quizModalArrayList.add(new QuizModal("Will you be a billionaire once?", "Yes", "No", "Never", "Please...", "Yes"));
    }

    private void setDataToViews(int currentPos) {
        questionNumberTV.setText("Questions Attempted : " + questionAttempted + "/10");
        if (questionAttempted == 10) {
            showBottomSheet();
        } else {
            questionTV.setText(quizModalArrayList.get(currentPos).getQuestion());
            option1Btn.setText(quizModalArrayList.get(currentPos).getOption1());
            option2Btn.setText(quizModalArrayList.get(currentPos).getOption2());
            option3Btn.setText(quizModalArrayList.get(currentPos).getOption3());
            option4Btn.setText(quizModalArrayList.get(currentPos).getOption4());
        }
    }
}