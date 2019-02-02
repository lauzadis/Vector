package com.example.motbot.vector;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class choice extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);
        final int[][] vectors = (int[][])getIntent().getExtras().get("vectors");
        TextView text = (TextView)findViewById(R.id.vectorsText);
        text.setText(printArray(vectors));

        Button graph = (Button)findViewById(R.id.graph);
        Button calculate = (Button)findViewById(R.id.calculate);

        graph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent graphSwitch = new Intent(getApplicationContext(), graph.class);
                graphSwitch.putExtra("vectors",vectors);
                startActivity(graphSwitch);
            }
        });
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent calculateSwitch = new Intent(getApplicationContext(), calculate.class);
                calculateSwitch.putExtra("vectors",vectors);
                startActivity(calculateSwitch);
            }
        });
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
