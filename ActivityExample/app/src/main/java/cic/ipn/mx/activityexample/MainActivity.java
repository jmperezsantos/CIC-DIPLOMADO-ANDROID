package cic.ipn.mx.activityexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnNavegar = this.findViewById(R.id.btnNavegar);
        btnNavegar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("TAG", "Botón presionado!");

                Intent intent =
                        new Intent(MainActivity.this,
                                SecondActivity.class);

                intent.putExtra("theString", "Hola Mundo");
                intent.putExtra("theInteger", 5);
                intent.putExtra("theFloat", 5.5f);

                startActivity(intent);

            }
        });

    }
}
