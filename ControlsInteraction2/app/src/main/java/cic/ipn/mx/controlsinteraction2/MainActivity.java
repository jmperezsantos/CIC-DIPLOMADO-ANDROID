package cic.ipn.mx.controlsinteraction2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView tvOpcion = findViewById(R.id.tvOpcion);
        RadioGroup rgRadioGroup = findViewById(R.id.radioGroup);
        rgRadioGroup.setOnCheckedChangeListener(
                new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int selectedId) {

                        switch (selectedId) {

                            case R.id.rb1:
                                tvOpcion.setText("Opción 1");
                                break;

                            case R.id.rb2:
                                tvOpcion.setText("Opción 2");
                                break;

                            case R.id.rb3:
                                tvOpcion.setText("Opción 3");
                                break;

                        }
                    }
                });

        final TextView tvProgress = findViewById(R.id.tvProgress);
        final ProgressBar progressBar = findViewById(R.id.pbProgress);
        SeekBar sbSlider = findViewById(R.id.sbSlider);
        sbSlider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar,
                                          int progress,
                                          boolean wasUser) {

                tvProgress.setText("Progreso: " + seekBar.getProgress());

                progressBar.setProgress(progress);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}
