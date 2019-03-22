package com.example.venue.apptest3.feature;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {
   // Button MainButton = findViewById(R.id.textView);
    Button Button2;
    TextView DisplaySearch;
    //String Test2 = "Oops, database Search Failed!";
    //String Test3 = getString(R.string.SearchFail);
    PopupWindow Show;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
//        if(MainButton.isEnabled())
//        {
//            setContentView(R.layout.activity_main2);
//        }
        //String sessionId = getIntent().getStringExtra("Extra_Session_Id");
        //addListenerOnButtonClick2();
        Intent intent = getIntent();
        //String value = intent.getStringExtra("Key");
        String getSearch = intent.getStringExtra("InputQuery");
        DisplaySearch = (TextView) findViewById(R.id.DisplaySearch);  //Could Getup Search to also use an offline logged AufillNots system to help aid Dyamic Venue Searching, but may require Caching Database Contents Over time.
        DisplaySearch.setText(getSearch);

        //Toast.makeText(getApplicationContext(), "String: " + getSearch, Toast.LENGTH_LONG).show();

        if(getSearch == null)
        {
            //Show.getContentView()R.id.BarSearch
            Toast.makeText(getApplicationContext(), getString(R.string.SearchFail), Toast.LENGTH_SHORT).show();
            DisplaySearch.setText(getString(R.string.SearchFail));
        }
    }

    public void addListenerOnButtonClick2() {
    Button2 = (Button) findViewById(R.id.button2);
    Button2.setOnClickListener(new View.OnClickListener()
    {

        public void onClick(View view)
        {
            if(Button2.isEnabled())
            {

//                setContentView(R.layout.activity_main);
//              startActivity(R.layout.activity_main, );
                Main2Activity.this.setContentView(R.layout.activity_main);

            }
        }
    });
    }
}
