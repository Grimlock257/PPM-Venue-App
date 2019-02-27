package com.example.venue.apptest3.feature;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main2Activity extends AppCompatActivity {
   // Button MainButton = findViewById(R.id.textView);
    Button Button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
//        if(MainButton.isEnabled())
//        {
//            setContentView(R.layout.activity_main2);
//        }
        String sessionId = getIntent().getStringExtra("Extra_Session_Id");
        //addListenerOnButtonClick2();
        Intent intent = getIntent();
        String value = intent.getStringExtra("Key");
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
