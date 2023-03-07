package zli.rc.coinview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import zli.rc.coinview.model.CryptoModel;

/*
   X-CMC_PRO_API_KEY
   6562ae80-1e3b-4fa6-9b2d-9db06c923763
 */


public class CryptoAdapter extends RecyclerView.Adapter<CryptoAdapter.CryptoViewholder> {
    private ArrayList<CryptoModel> cryptoModelArrayList;
    private Context context;
    private static DecimalFormat decimalFormat = new DecimalFormat("#.##");

    public CryptoAdapter(ArrayList<CryptoModel> cryptoModelArrayList, Context context) {
        this.cryptoModelArrayList = cryptoModelArrayList;
        this.context = context;
    }

    public void searchList(ArrayList<CryptoModel> searchedList){
        cryptoModelArrayList = searchedList;
        notifyDataSetChanged();
    }

    //implement layout file
    @NonNull
    @Override
    public CryptoAdapter.CryptoViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.crypto_item, parent, false);
        return new CryptoAdapter.CryptoViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CryptoAdapter.CryptoViewholder holder, int position) {
        CryptoModel cryptoModel = cryptoModelArrayList.get(position);
        holder.cryptoName.setText(cryptoModel.getName());
        holder.symbolCrypto.setText(cryptoModel.getSymbol());
        holder.priceCrypto.setText("$ " + decimalFormat.format(cryptoModel.getPrice()));
    }

    @Override
    public int getItemCount() {
        return cryptoModelArrayList.size();
    }


    public static class CryptoViewholder extends RecyclerView.ViewHolder{
        private TextView cryptoName, symbolCrypto, priceCrypto;

        public CryptoViewholder(@NonNull View itemView) {
            super(itemView);
            cryptoName = itemView.findViewById(R.id.idCurrencyName);
            symbolCrypto = itemView.findViewById(R.id.idCryptoSymbol);
            priceCrypto = itemView.findViewById(R.id.idCryptoPrice);
        }
    }
}
