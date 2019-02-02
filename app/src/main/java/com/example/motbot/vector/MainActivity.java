package com.example.motbot.vector;

import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        Button add = (Button) findViewById(R.id.add);
        Button done = (Button) findViewById(R.id.done);
        EditText vector1 = (EditText) findViewById(R.id.vector);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout layout = (LinearLayout) findViewById(R.id.linearLayout);
                View newVector = inflater.inflate(R.layout.sublayout, layout, false); // Instantiate new View from sublayout.xml
                layout.addView(newVector, layout.getChildCount() - 2); // Add the new row before the add vector button.
            }
        });

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout layout = (LinearLayout)findViewById(R.id.linearLayout);
                int vectorCount = layout.getChildCount()-2;
                //TextView textView = (TextView)findViewById(R.id.textView);

                EditText x; String temp; int[] tempVector;
                int[][] vectors = new int[vectorCount][3];


                for (int i = 0; i < vectorCount; i++)
                {
                    x = (EditText)layout.getChildAt(i);
                    temp = x.getText().toString();
                    tempVector = convertInputToArray(temp);

                    for(int j = 0; j < 3; j++)
                    {
                        vectors[i][j] = tempVector[j];
                    }
                }
                //textView.setText(printArray(vectors));
                Intent moveOn = new Intent(getApplicationContext(), choice.class);
                moveOn.putExtra("vectors",vectors);
                startActivity(moveOn);
            }
        });

    }

    public static int[] convertInputToArray(String x) {
        //Preconditions: Receive a 3-int String, separated by commas
        //Postconditions: result will be returned, an array holding each integer
        int[] result = new int[3];
        String[] test = x.split(",", 3);
        for (int i = 0; i < 3; i++) {
            result[i] = Integer.parseInt(test[i]);
        }
        return result;
    }

    public String printArray(int[][] x)
    {
        String result = "";
        for(int i = 0; i < (x.length); i++)
        {
            result+="Vector " + (i+1) + " : <";
            for(int j = 0; j < 3; j++)
            {
                if(j!=2)
                    result+=x[i][j] + ", ";
                else
                    result+=x[i][j];
            }
            result+=">\n";
        }
        return result;
    }
}

