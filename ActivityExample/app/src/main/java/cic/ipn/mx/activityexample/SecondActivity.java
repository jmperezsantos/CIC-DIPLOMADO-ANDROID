package cic.ipn.mx.activityexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Bundle extras = this.getIntent().getExtras();

        //Rescatamos valores de los extras!
        String theString = extras.getString("theString");
        int theInt = extras.getInt("theInteger");
        float theFloat =
                this.getIntent()
                        .getFloatExtra("theFloat", -1.0f);

        //Mostrar en la interfaz gráfica
        TextView tvString = findViewById(R.id.tvString);
        TextView tvInt = findViewById(R.id.tvInteger);
        TextView tvFloat = findViewById(R.id.tvFloat);

        tvString.setText(theString);
        //tvInt.setText("" + theInt);
        tvInt.setText(String.format("%d", theInt));

        //tvFloat.setText("" + theFloat);
        tvFloat.setText(String.format("%.2f", theFloat));

    }
}
