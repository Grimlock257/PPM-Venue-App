package com.venue3;



import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity
{

    //int x = 0;
    Switch title_Toggle;
    //Switch SwitchSubmit;
    Button Defs;
    EditText BarSearch;
    Button DisplayVenues;
    String /*ExtractEditText*/ InputQuery;
    Intent StartSearch;
    Intent SetBar;
    String LOADERPath = "com.example.venue.apptest3.feature.Main2Activity";
    String L2 = "com.example.venue.apptest3.feature.OptionsAct";
    public boolean SetActivityBar = true;
    Button Options;
    Button LoadFragments;
    Intent GetLOD;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addListenerOnButtonClick();

        //todo: Add Search Bard later

//        BarSearch = (EditText) findViewById(R.id.BarSearch);
//        InputMethodManager keys = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//        keys.showSoftInput(BarSearch, InputMethodManager.SHOW_IMPLICIT);




    }

    public void addListenerOnButtonClick()
    {
        title_Toggle = (Switch) findViewById(R.id.Title_Toggle);

        Defs = (ToggleButton) findViewById(R.id.Title_Toggle2);
        //Options = (Button) findViewById(R.id.Options);

        DisplayVenues = (Button) findViewById(R.id.textView);
        DisplayVenues.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                if(DisplayVenues.isEnabled())
                {
                    GetLOD = new Intent(view.getContext(), Main2Activity.class);
                    view.getContext().startActivity(GetLOD);
                }

            }
        });

        title_Toggle.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                if (Defs.isEnabled())
                {
                    if(!title_Toggle.isChecked())
                    {
                        getSupportActionBar().hide();
                        SetActivityBar = false;
                        StringBuilder result = new StringBuilder();
                        result.append("ToggleButton1 : ").append(title_Toggle.getTextOn());
                        Toast.makeText(getApplicationContext(), result.toString(), Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        getSupportActionBar().show();
                        SetActivityBar = true;
//

                        Toast.makeText(getApplicationContext(), ("ToggleButton1 : ") + title_Toggle.getTextOff(), Toast.LENGTH_LONG).show();
                    }
//

                }
            }

        });

    }



}



