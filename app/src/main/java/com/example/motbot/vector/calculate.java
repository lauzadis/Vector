package com.example.motbot.vector;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class calculate extends AppCompatActivity {
    public String vectorUno; public String vectorDos; public String choice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate);
        final int[][] vectors = (int[][]) getIntent().getExtras().get("vectors");
        final TextView text = (TextView) findViewById(R.id.vectorsText);
        Button calculate = (Button)findViewById(R.id.calculate);
        final Spinner vectorOne = (Spinner)findViewById(R.id.vectorOne);
        final Spinner vectorTwo = (Spinner)findViewById(R.id.vectorTwo);
        final Spinner method = (Spinner)findViewById(R.id.method);


        text.setText(printArray(vectors));
        List<String> vectorsSpinner = new ArrayList<String>();
        String x;

        for(int i = 0; i < vectors.length; i++)
        {
            x ="<";
            for(int j = 0; j < vectors[0].length; j++)
            {
                if(j!= (vectors[0].length-1))
                    x+=vectors[i][j] + ", ";
                else
                    x+=vectors[i][j];
            }
            x+=">";
            vectorsSpinner.add(x);
        }
        List<String> choicesArray = new ArrayList<String>();
        choicesArray.add("DOT");
        choicesArray.add("CROSS");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, vectorsSpinner);
        vectorOne.setAdapter(adapter);
        vectorTwo.setAdapter(adapter);
        ArrayAdapter<String> choiceAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, choicesArray);
        method.setAdapter(choiceAdapter);


       vectorOne.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               String choiceOne = vectorOne.getSelectedItem().toString();
               vectorUno = choiceOne;
           }
           @Override
           public void onNothingSelected(AdapterView<?> parent) {
           }
       });


       vectorTwo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               String choiceTwo = vectorTwo.getSelectedItem().toString();
               vectorDos = choiceTwo;
           }
           @Override
           public void onNothingSelected(AdapterView<?> parent) {
           }
       });

       method.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               String methodChoice = method.getSelectedItem().toString();
               choice = methodChoice;
           }
           @Override
           public void onNothingSelected(AdapterView<?> parent) {
           }
       });


        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int[] vecOne; int[] vecTwo;
                TextView result = findViewById(R.id.result);
                vecOne = convertInputToArray(vectorUno);
                vecTwo = convertInputToArray(vectorDos);

                if(choice.equals("CROSS"))
                {
                    int[] res = crossProduct(vecOne,vecTwo);
                    String print = "<";
                    for(int i = 0; i < res.length; i++)
                    {
                        if(i < 2)
                            print+=res[i]+", ";
                        else
                            print+=res[i];
                    }
                    print+=">";

                    result.setText("Cross Product of " + vectorUno + " and " + vectorDos + " = " + print);
                }
                else if(choice.equals("DOT"))
                {
                    int dot = dotProduct(vecOne, vecTwo);
                    result.setText("Dot Product of " + vectorUno + " and " + vectorDos+ " = " + dot);
                }
            }
        });
    }

    public String printArray(int[][] x) {
        String result = "";
        for (int i = 0; i < (x.length); i++) {
            result += "Vector " + (i + 1) + " : <";
            for (int j = 0; j < 3; j++) {
                if (j != 2)
                    result += x[i][j] + ", ";
                else
                    result += x[i][j];
            }
            result += ">\n";
        }
        return result;
    }

    public static int[] convertInputToArray(String x) {
        int[] result = new int[3];
        x = x.substring(1,x.length()-1);
        String[] temp = x.split(", ",3);
        for(int i = 0; i < 3; i++)
        {
            result[i] = Integer.parseInt(temp[i]);
        }
        return result;
    }

    public static int dotProduct(int[] one, int[] two)
    {
        int result = 0;
        for(int i = 0; i < 3; i++)
        {
            result+=(one[i]*two[i]);
        }
        return result;
    }

    public static int[] crossProduct(int[] one, int[] two)
    {
        int[] result = new int[3];
        int[][] i = new int [2][2];
        int[][] j = new int [2][2];
        int[][] k = new int [2][2];

        i[0][0] = one[1]; i[0][1] = one[2];
        i[1][0] = two[1]; i[1][1] = two[2];

        j[0][0] = one[0]; j[0][1] = one[2];
        j[1][0] = two[0]; j[1][1] = two[2];

        k[0][0] = one[0]; k[0][1] = one[1];
        k[1][0] = two[0]; k[1][1] = two[1];

        result[0] = determinant(i);
        result[1] = -1*determinant(j);
        result[2] = determinant(k);
        return result;
    }

    public static int determinant(int[][] arr)
    {
        return (arr[1][1] * arr[0][0])-(arr[0][1]*arr[1][0]);
    }
}
