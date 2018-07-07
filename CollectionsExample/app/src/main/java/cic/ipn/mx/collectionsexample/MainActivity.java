package cic.ipn.mx.collectionsexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cic.ipn.mx.collectionsexample.adapters.SongListViewAdapter;
import cic.ipn.mx.collectionsexample.model.SongModel;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView lvSongs = findViewById(R.id.lvCanciones);

        lvSongs.setAdapter(new SongListViewAdapter(this.generateDummyList(50)));

        lvSongs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                ListView lista = (ListView)parent;
                ListAdapter adapter = lista.getAdapter();

                SongModel presionada = (SongModel) adapter.getItem(position);
                Toast.makeText(
                        MainActivity.this,
                        "Presionó: " + presionada,
                        Toast.LENGTH_LONG)
                        .show();

            }
        });

    }

    private List<SongModel> generateDummyList(int count) {

        List<SongModel> list = new ArrayList<>(count);

        for (int x = 1; x <= count; x++) {

            SongModel song =
                    new SongModel(
                            "Title " + x,
                            "Artist " + x,
                            String.format("%d:%d", x, (x * x))
                    );

            list.add(song);

        }

        return list;
    }
}
