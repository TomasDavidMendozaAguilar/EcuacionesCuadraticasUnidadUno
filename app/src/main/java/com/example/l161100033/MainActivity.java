package com.example.l161100033;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText a = (EditText)findViewById(R.id.v1);
        EditText b = (EditText)findViewById(R.id.v2);
        EditText c = (EditText)findViewById(R.id.v3);
        Button   operacion = (Button)findViewById(R.id.btn);
        TextView valor_x1 = (TextView)findViewById(R.id.x1);
        TextView valor_x2 = (TextView)findViewById(R.id.x2);
        TextView dis = (TextView)findViewById(R.id.tvD);
        operacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                try {// para encapsular un error de ingreso de datos como String en los EditText
                    String xA = a.getText().toString();
                    String xB = b.getText().toString();
                    String xC = c.getText().toString();
                    //verificación de que los campos esten llenos

                    if (xA.isEmpty() || xB.isEmpty() || xC.isEmpty()) {
                        if (xA.isEmpty()) {
                            a.setError("Ingresar Valor");
                        }
                        if (xB.isEmpty()) {
                            b.setError("Ingresar Valor");
                        }
                        if (xC.isEmpty()) {
                            c.setError("Ingresar Valor");
                        }
                    } else {
                        double a1 = Double.parseDouble(xA);
                        double b1 = Double.parseDouble(xB);
                        double c1 = Double.parseDouble(xC);

                         //verificación El coeficiente a no puede ser igual a cero
                        if (a1 == 0) {
                            AlertDialog.Builder alerta = new AlertDialog.Builder(MainActivity.this);
                            alerta.setMessage("El coeficiente a no puede ser igual a cero").setNegativeButton("Aceptar", null).create().show();
                        } else{
                            double discriminante = Math.pow(b1, 2) - 4 * a1 * c1;
                            if (discriminante >= 0) {
                                if (discriminante == 0) {
                                    double x = -b1 / (2 * a1);
                                    Toast.makeText(MainActivity.this,"La raíz única es: "+x,Toast.LENGTH_LONG).show();

                                } else {
                                    double x1, x2;
                                    x1 = (-b1 + Math.sqrt(discriminante)) / (2 * a1);
                                    x2 = (-b1 - Math.sqrt(discriminante)) / (2 * a1);

                                    valor_x1.setText("X1: "+x1);
                                    valor_x2.setText("X1: "+x2);
                                    dis.setText("Discriminante: "+discriminante);
                                    Toast.makeText(MainActivity.this,"X1: "+x1 +"\n"+"X2: "+x2 ,Toast.LENGTH_LONG).show();

                                }
                            }else if(discriminante < 0){
                                AlertDialog.Builder alerta = new AlertDialog.Builder(MainActivity.this);
                                alerta.setMessage("La discriminante es menor que cero, como no existen las raíces de números negativos, la ecuación no tendrá soluciones.").setNegativeButton("Aceptar", null).create().show();
                                dis.setText("Discriminante: "+discriminante);
                            }

                        }

                    }
                }catch (Exception e){
                    //envio del mensaje que ocurrio un error
                    AlertDialog.Builder alerta = new AlertDialog.Builder(MainActivity.this);
                    alerta.setMessage("Haz ingresado un valor incorrecto (caractere, etc.)").setNegativeButton("Aceptar", null).create().show();

                }



            }
        });
    }
}