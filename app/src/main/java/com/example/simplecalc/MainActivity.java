package com.example.simplecalc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button un = null;
    Button deux = null;
    Button trois = null;
    Button quatre = null;
    Button cinq = null;
    Button six = null;
    Button sept = null;
    Button huit = null;
    Button neuf = null;
    Button zero = null;
    Button point = null;
    Button plus = null;
    Button moins = null;
    Button fois = null;
    Button divise = null;
    Button plusMoins = null;
    Button reset = null;
    Button M = null;
    Button MR = null;
    Button calculer = null;
    TextView equation;
    boolean checkNew = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        un = (Button) findViewById(R.id.un);
        un.setOnClickListener(this::ButtonClick);
        deux = (Button) findViewById(R.id.deux);
        deux.setOnClickListener(this::ButtonClick);
        trois = (Button) findViewById(R.id.trois);
        trois.setOnClickListener(this::ButtonClick);
        quatre = (Button) findViewById(R.id.quatre);
        quatre.setOnClickListener(this::ButtonClick);
        cinq = (Button) findViewById(R.id.cinq);
        cinq.setOnClickListener(this::ButtonClick);
        six = (Button) findViewById(R.id.six);
        six.setOnClickListener(this::ButtonClick);
        sept = (Button) findViewById(R.id.sept);
        sept.setOnClickListener(this::ButtonClick);
        huit = (Button) findViewById(R.id.huit);
        huit.setOnClickListener(this::ButtonClick);
        neuf = (Button) findViewById(R.id.neuf);
        neuf.setOnClickListener(this::ButtonClick);
        zero = (Button) findViewById(R.id.zero);
        zero.setOnClickListener(this::ButtonClick);
        point = (Button) findViewById(R.id.point);
        point.setOnClickListener(this::ButtonClick);
        plus = (Button) findViewById(R.id.plus);
        plus.setOnClickListener(this::ButtonClick);
        moins = (Button) findViewById(R.id.moins);
        moins.setOnClickListener(this::ButtonClick);
        fois = (Button) findViewById(R.id.fois);
        fois.setOnClickListener(this::ButtonClick);
        divise = (Button) findViewById(R.id.divise);
        divise.setOnClickListener(this::ButtonClick);
        reset = (Button) findViewById(R.id.reset);
        reset.setOnClickListener(this::ButtonClick);
        calculer = (Button) findViewById(R.id.calcul);
        calculer.setOnClickListener(calculerListener);
        equation = (TextView) findViewById(R.id.equation);


    }

    public void ButtonClick(View view){
        if(checkNew)
            equation.setText("");
        checkNew = false;
        String data = equation.getText().toString();
        switch(view.getId()){
            case R.id.un:
                data +="1";
                break;
            case R.id.deux:
                data +="2";
                break;
            case R.id.trois:
                data +="1";
                break;
            case R.id.quatre:
                data +="4";
                break;
            case R.id.cinq:
                data +="5";
                break;
            case R.id.six:
                data +="6";
                break;
            case R.id.sept:
                data +="7";
                break;
            case R.id.huit:
                data +="8";
                break;
            case R.id.neuf:
                data +="9";
                break;
            case R.id.zero:
                data +="0";
                break;
            case R.id.point:
                data +=".";
                break;
            case R.id.plus:
                data +="+";
                break;
            case R.id.moins:
                data +="-";
                break;
            case R.id.fois:
                data +="*";
                break;
            case R.id.divise:
                data +="/";
                break;
            case R.id.reset:
                data ="";
                break;
        }
        equation.setText(data);

    }


    private View.OnClickListener calculerListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            String e = equation.getText().toString();
            String res = "";
            final char[] operator = new char[e.length()];
            final double[] number = new double[e.length()];
            String stk = "";
            int j =0;
            for(int i = 0; i<e.length();i++){
                Character check = e.charAt(i);
                if(e != null && check != '+' && check != '*' && check != '-' && check != '/'){
                    stk = stk+check;
                }
                if (check == '+' || check == '*' || check == '-' || check == '/'){
                    operator[j]= check;

                    number[j] = Double.parseDouble(stk);

                    stk = "";
                    j++;
                }

            }
            number[j] = Double.parseDouble(stk);

            Character prevOp = null;

            int prevNumber = 0;
            res = res + number[0];


            for(int i = 0; i< j;i++){

                Character Op = operator[i];


                if(Op!=null){
                    if(Op=='+'){
                        double calc = Double.parseDouble(res)+number[i+1];
                        res = ""+calc;


                        prevNumber++;
                        prevOp = '+';
                    }
                    else if(Op=='-'){
                        double calc = Double.parseDouble(res)-number[i+1];
                        res = ""+calc;
                        prevNumber++;
                        prevOp = '-';
                    }
                    else if(Op=='*'){

                        if(prevOp != null) {
                            if(prevOp =='+'){
                                double calc = ((Double.parseDouble(res) - number[i])+(number[i]* number[i+1]));
                                res = "" + calc;

                            }
                            else if(prevOp=='-'){

                                double calc = ((Double.parseDouble(res) + number[i])-number[i+1]*number[i]);
                                res = "" + calc;
                            }
                            else{
                                double calc = (Double.parseDouble(res)*number[i+1]);
                                res = ""+calc;
                            }
                        }
                        else{
                            double calc = (Double.parseDouble(res)*number[i+1]);
                            res = ""+calc;


                        }
                        prevNumber++;
                        prevOp = '*';
                    }
                    else if (Op=='/'){
                        if(prevOp != null) {
                            if(prevOp =='+'&& prevOp != null){
                                double calc = (Double.parseDouble(res) - number[i-1]/number[i+1]);
                                res = "" + calc;
                            }
                            else if(prevOp=='-'&& prevOp != null){
                                double calc = (Double.parseDouble(res) + number[i]/number[i+1]);
                                res = "" + calc;
                            }
                            else {
                                double calc = (Double.parseDouble(res)/number[i+1]);
                                res = ""+calc;
                            }
                        }
                        else {
                            double calc = (Double.parseDouble(res)/number[i+1]);
                            res = ""+calc;
                        }
                        prevNumber++;
                        prevOp = '*';
                        if(number[i+1]==0) {
                            res = "Division par 0";
                        }

                    }

                }
                else{


                }
            }
            equation.setText(res);
            checkNew = true;
        }

    };


}