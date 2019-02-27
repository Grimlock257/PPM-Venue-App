package com.example.venue.apptest3.feature;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.inputmethodservice.ExtractEditText;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.SupportActionModeWrapper;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //int x = 0;
     Switch title_Toggle;
    //Switch SwitchSubmit;
    Button Defs;
    EditText BarSearch;
    Button Load2ndAct;
    String /*ExtractEditText*/ InputQuery;
    Intent GetLOD;
    String LOADERPath = "com.example.venue.apptest3.feature.Main2Activity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addListenerOnButtonClick();
        //GetLOD = new Intent(MainActivity.this, Main2Activity.class);
        //GetLOD.putExtra("Key", 1);
        //intent.putExtra("Extra_Session_Id", 1);


        //addListenerOnButtonClick2();
        //TextView. // Do not need to specify the String resource manually, Is handled entirely by the Activity Xml
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getSupportActionBar().hid e();
        BarSearch = (EditText) findViewById(R.id.BarSearch);
        InputMethodManager keys = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        keys.showSoftInput(BarSearch, InputMethodManager.SHOW_IMPLICIT);
        //InputQuery = toString().getChars(R.id.BarSearch);
       // InputQuery = toString(BarSearch.getText(GetLOD.); /?Attempting to send input text From SoftInput to Next Activity for Search Functionality;
        //GetLOD.putExtra("Key", String.valueOf(Toast.makeText(getApplicationContext(), InputQuery, Toast.LENGTH_SHORT)));
        //InputQuery = getText(R.id.BarSearch.);
        //InputQuery = getText(BarSearch.setText(InputMethodManager.
        //InputQuery = getText(BarSearch.setText();)
        //InputQuery = (() R.id.BarSearch)
        //InputQuery.extractText(Context.INPUT_METHOD_SERVICE.toString());
        InputQuery = Context.INPUT_METHOD_SERVICE; //Only Retuns InputSrevice But user Inputted String
       // InputQuery = keys.toString().toCharArray().;
        Toast.makeText(getApplicationContext(), "String: " + InputQuery, Toast.LENGTH_LONG).show();
       //NULL:-> MainActivity.this.startActivity(GetLOD);
        //keys.showSoftInput(BarSearch.getWindowToken(), 1)

    }
    @Deprecated //This Causes a NULLPointer
    public void getNewActivity(/*view*/)
    {
        InputQuery = BarSearch.getText().toString();
        GetLOD = new Intent(MainActivity.this, Main2Activity.class);
        GetLOD.putExtra(LOADERPath, InputQuery);
        this.startActivity(GetLOD);
    }



    public void addListenerOnButtonClick() {
        //final ToggleButton title_Toggle = (ToggleButton) findViewById(R.id.Title_Toggle);
        title_Toggle = (Switch) findViewById(R.id.Title_Toggle);
        //Button buttonSubmit = (Button) findViewById(R.id.Title_Toggle);
        //SwitchSubmit =  title_Toggle;
        //Performing action on button click

        //buttonSubmit.setOnClickListener(new View.OnClickListener() {
        Defs = (ToggleButton) findViewById(R.id.Title_Toggle2);
        Load2ndAct = (Button) findViewById(R.id.textView);
        Load2ndAct.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                if(Load2ndAct.isEnabled())
                {
                    //setContentView(R.layout.activity_main2);
                    //MainActivity.this.setContentView(R.layout.activity_main2);
                    //startActivity(R.layout.activity_main2, Load2ndAct.isEnabled(View));
                    //getNewActivvity(View);
                    InputQuery = BarSearch.getText().toString();
                    GetLOD = new Intent(view.getContext(), Main2Activity.class);
                    GetLOD.putExtra(LOADERPath, InputQuery);
                    view.getContext().startActivity(GetLOD);

                }
            }
        }
        );
        title_Toggle.setOnClickListener(new View.OnClickListener()
        {
        //SwitchSubmit.setChecked(false);
            //@Override
            public void onClick(View view)
            {
                //requestWindowFeature(Window.FEATURE_NO_TITLE);
                //requestWindowFeature(Window.FEATURE_ACTION_BAR);
                //ActionBar ab = getSupportActionBar();
                //ab.hide();
//               if (buttonSubmit.setOnClickListener() == 1)
                // {

                if (Defs.isEnabled())   //Lock Button Experiment failed as OnClockListener for the MenuTitle Toggle Sems to Ovreride This.
                    //may need a seperate listener for the Button as i verting the enablement erquirments permiantely diabled the Men_Toggle Functionality Completely, May need to be set as non-static?
                {
                    if(!title_Toggle.isChecked())
                    {
                        getSupportActionBar().hide();
//                    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                            WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
//                    setContentView(R.layout.activity_main);
                        //title_Toggle.setChecked(true);
                        //SwitchSubmit.setChecked(true);
                        StringBuilder result = new StringBuilder();
                        result.append("ToggleButton1 : ").append(title_Toggle.getTextOn());
                        //result.append("\nToggleButton2 : ").append(title_Toggle.getText());

                        Toast.makeText(getApplicationContext(), result.toString(), Toast.LENGTH_SHORT).show();
                        //title_Toggle.setChecked(true);
                        //x = 1;
                        //}
//                else
//               {
//                   getSupportActionBar().show();
//                   getWindow().setFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN,
//                           WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
//                   setContentView(R.layout.activity_main);
//                   x = 0;
//               }
                    }
                else
                    {
                        getSupportActionBar().show();
//                    getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
//                            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE); //enable full screen
//                    setContentView(R.layout.activity_main);
                        //title_Toggle.setChecked(false);
                        //SwitchSubmit.setChecked(false);
                        //SwitchSubmit.setChecked(true);

                        Toast.makeText(getApplicationContext(), ("ToggleButton1 : ") + title_Toggle.getTextOff(), Toast.LENGTH_LONG).show();
                    }
//                getWindow().setFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN,
//                        WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN); //enable full screen
//                setContentView(R.layout.activity_main);
                    //title_Toggle.setChecked(true);

                }
            }

        });





//    public void addListenerOnButtonClick2()
//    {
//        final ToggleButton toggleButton2 = (ToggleButton) findViewById(R.id.Title_Toggle2);
//        Button buttomSubmit2 = (Button) findViewById(R.id.Title_Toggle2);
//        buttomSubmit2.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View view) {
//                getSupportActionBar().show();
//                getWindow().setFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN,
//                        WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN); //enable full screen
//                setContentView(R.layout.activity_main);
//            }
//
//        });
        //@Override

    }
}
