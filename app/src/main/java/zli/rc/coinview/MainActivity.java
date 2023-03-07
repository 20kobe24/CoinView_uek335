package zli.rc.coinview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import zli.rc.coinview.model.CryptoModel;

public class MainActivity extends AppCompatActivity {
    private ArrayList<CryptoModel> cryptoModelArrayList;
    private CryptoAdapter cryptoAdapter;
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
        cryptoModelArrayList = new ArrayList<>();
        cryptoAdapter = new CryptoAdapter(cryptoModelArrayList, this);
        cryptosRV.setLayoutManager(new LinearLayoutManager(this));
        cryptosRV.setAdapter(cryptoAdapter);
        getCryptoData();

        searchInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                searchCryptos(s.toString());
            }
        });

    }

    private void searchCryptos(String crypto){
        ArrayList<CryptoModel> searchedList = new ArrayList<>();
        for (CryptoModel item : cryptoModelArrayList){
            if (item.getName().toLowerCase().contains(crypto.toLowerCase())){
                searchedList.add(item);
            }
        }
        if (searchedList.isEmpty()){
            Toast.makeText(this, "No Cryptos are found", Toast.LENGTH_SHORT).show();
        }else {
            cryptoAdapter.searchList(searchedList);
        }
    }

    private void getCryptoData() {
        loadingPB.setVisibility(View.VISIBLE);
        String url = "https://pro-api.coinmarketcap.com/v1/cryptocurrency/listings/latest";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                loadingPB.setVisibility(View.GONE);
                try {

                    JSONArray dataArray = response.getJSONArray("data");
                    for (int i = 0; i < dataArray.length(); i++) {
                        JSONObject dataObject = dataArray.getJSONObject(i);
                        String name = dataObject.getString("name");
                        String symbol = dataObject.getString("symbol");
                        JSONObject quote = dataObject.getJSONObject("quote");
                        JSONObject USD = quote.getJSONObject("USD");
                        double price = USD.getDouble("price");
                        cryptoModelArrayList.add(new CryptoModel(name,symbol,price));
                    }
                    cryptoAdapter.notifyDataSetChanged();
                }catch (JSONException e){
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this, "Failed to extract Coinmarket Datea....", Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loadingPB.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this,"Failed to get coinmarketcap data..", Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                //we use hashmap to pass our headers
                HashMap<String, String> headers = new HashMap<>();
                headers.put("X-CMC_PRO_API_KEY","6562ae80-1e3b-4fa6-9b2d-9db06c923763");
                return headers;
            }
        };
        requestQueue.add(jsonObjectRequest);

    }
}