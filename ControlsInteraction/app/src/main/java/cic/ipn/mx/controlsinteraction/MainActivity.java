package cic.ipn.mx.controlsinteraction;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView output = findViewById(R.id.miTextView);

        output.setText("Hola: Juan Manuel");

        final EditText input = findViewById(R.id.input);
        input.setText("Texto del EditText");

        Button boton = findViewById(R.id.boton);
        boton.setText("Este es un botón!");

        View.OnClickListener clickListener = new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Log.d("TAG", "Hola Mundo desde un botón!");

                String text = input.getText().toString();
                output.setText(text);

                input.setText(null);

            }
        };

        boton.setOnClickListener(clickListener);

        final Switch swSwitch = findViewById(R.id.swSwitch);
        swSwitch.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView,
                                                 boolean isChecked) {

                        if (isChecked) {
                            Log.e("TAG", "El Elemento Está: Prendido");
                        } else {
                            Log.e("TAG", "El Elemento Está: Apagado");
                        }

                    }
                });

        final CheckBox checkBox = findViewById(R.id.cbCheckbox);
        checkBox.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                swSwitch.setChecked(!isChecked);

                if (isChecked){
                    input.setEnabled(false);
                } else {
                    input.setEnabled(true);
                }

            }
        });

    }
}


