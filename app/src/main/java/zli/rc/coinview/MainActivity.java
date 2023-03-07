package zli.rc.coinview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ProgressBar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText searchInput;
    private RecyclerView cryptosRV;
    private ProgressBar loadingPB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchInput = findViewById(R.id.idEditSearch);
        cryptosRV = findViewById(R.id.idRVPriceCource);
        loadingPB = findViewById(R.id.idLoadingBar);

    }
}