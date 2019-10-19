package com.merttoptas.nightmode;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btn;
    EditText edt, edt1;
    Switch mySwitch;
    TextView textview, tv1, tv2, tv3, tv4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // If the night mode is correct by default, it sets the Night Mode theme.
        if(AppCompatDelegate.getDefaultNightMode() ==AppCompatDelegate.MODE_NIGHT_YES){

            setTheme(R.style.NightMode);
        }else{
            setTheme(R.style.AppTheme);
        }
        setContentView(R.layout.activity_main);

        // items
        bindViews();

        //If NightMode is active, the Switch value returns true. Enabled to be active
        if(AppCompatDelegate.getDefaultNightMode() ==AppCompatDelegate.MODE_NIGHT_YES){
            mySwitch.setChecked(true);
        }

        mySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){

                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    //restart App
                    //Called intent with the restartApp method to be active.
                    restartApp();
                }else{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
            }
        });

    }
    private void restartApp(){
        //Go to the same class as Intent

        //With setFlags, the application is prevented from being a black screen.
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(i);
    }

    public void bindViews(){

        btn = findViewById(R.id.btn);
        edt1 =findViewById(R.id.editText);
        edt = findViewById(R.id.editText2);
        mySwitch = findViewById(R.id.mySwitch);
        textview = findViewById(R.id.textview);
        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        tv3 = findViewById(R.id.tv3);
        tv4 = findViewById(R.id.tv4);
    }
}
