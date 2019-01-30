package com.example.assign1;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    EditText eText;
    TextView remainView;
    TextView bullView;
    TextView cowView;
    TextView guessView;
    String[] name_list={"brick","novel","cargo","agent","child","ethic","magic","rails","hazel","table"};
    String word;
    int count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Made by Emir Can Marangoz!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void bottomClick(View view) {
        setContentView(R.layout.rule_page);
    }

    public void backClick(View view) {
        setContentView(R.layout.activity_main);
    }

    public void startClick(View view) {
        count=10;
        setContentView(R.layout.game_page);
        word=getRandom(name_list);
    }

    public void submitClick(View view) {


        eText = findViewById(R.id.editText3);
        String str = eText.getText().toString();
        if(str.length()!=5){
            Toast msg = Toast.makeText(getBaseContext(),"It must be 5 letter!",Toast.LENGTH_LONG);
            msg.show();
            eText.setText(null);
            return;

        }
        eText.setText(null);

        count--;
        guessView=findViewById(R.id.textView8);
        remainView=findViewById(R.id.textView7);
        bullView=findViewById(R.id.textView6);
        cowView=findViewById(R.id.textView5);
        Button resetButton = findViewById(R.id.retyButton);
        Button submitButton = findViewById(R.id.submitButton);
        Button backButton = findViewById(R.id.backButton);

        remainView.setText(count+" guess left");

        int bull = 0;
        int cow = 0;
        char[] guess  = str.toLowerCase().toCharArray();
        char[] answer = word.toLowerCase().toCharArray();
        for(int i = 0; i < answer.length; i++)
        {
            if (guess[i] == answer[i])
            {
                bull++;
            }
        }
        for(int i = 0; i < answer.length; i++)
        {
            for(int j=0; j < answer.length; j++){

                if(guess[i] == answer[j] && i!=j){
                    cow++;
                }

            }
        }
        cowView.setText("COW = " + cow);
        bullView.setText("BULL = " + bull);
        if(count>0) {
            if (bull == 5) {
                guessView.setText("Last Guess: " + str + "   Answer:" + word);
                submitButton.setClickable(false);
                backButton.setClickable(false);
                resetButton.setVisibility(View.VISIBLE); //To set visible
            } else {
                guessView.setText("Last Guess: " + str);
            }
        }
        else{
            guessView.setText("Answer:" + word +"  Bad Luck!");
            resetButton.setVisibility(View.VISIBLE);
        }

        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);

        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
    }
    public static String getRandom(String[] array) {
        int rnd = new Random().nextInt(array.length);
        return array[rnd];
    }

    public void againClick(View view) {
        count=10;
        setContentView(R.layout.game_page);
        word=getRandom(name_list);
    }
}
