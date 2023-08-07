package com.hiaryabeer.receipts.Adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.hiaryabeer.receipts.Acitvits.DetailsVochers_report;
import com.hiaryabeer.receipts.R;
import com.hiaryabeer.receipts.models.AppDatabase;
import com.hiaryabeer.receipts.models.ReceiptMaster;

import java.util.List;

public class MasterAdapter extends RecyclerView.Adapter<MasterAdapter.ViewHolder>{
    List<ReceiptMaster> list;
    Context context;
  public   static long VOH_NUM,TransNo;
    AppDatabase mydatabase;
    public MasterAdapter(List<ReceiptMaster> list, Context context) {
        this.list = list;
        this.context = context;
        mydatabase=AppDatabase.getInstanceDatabase(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.masterrow, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.VochNum.setText(list.get(position).getVhfNo()+"");
      String customerName =mydatabase.customers_dao().getCustmByNumber(list.get(position).getCustomerId()) ;
   //     String customerName =  list.get(position).getCust_Name();
        if(customerName!=null)
        holder.CusName.setText(customerName+"");
else  holder.CusName.setText("");
        holder.TotalQty.setText(list.get(position).getTotalQty()+"");
        holder.Nettotal.setText(list.get(position).getNetTotal()+"");
        holder.   serial.setText(list.get(position).getVhfNo()+"");
        holder.  preview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VOH_NUM=list.get(holder.getAdapterPosition()).getVhfNo();

              try {
                  TransNo=Long.parseLong(list.get(holder.getAdapterPosition()).getTransNo());
              }catch (Exception e){

              }


                Log.e("AdapterVOH_NUM==",VOH_NUM+" ,"+TransNo);
                Intent intent=new Intent(context, DetailsVochers_report.class);
                Log.e("sentVohNu==",list.get(holder.getAdapterPosition()).getTransNo()+"");
                intent.putExtra("VOHNO",list.get(holder.getAdapterPosition()).getTransNo());
           context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView VochNum,CusName,TotalQty,Nettotal,serial;

     AppCompatButton preview;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            VochNum=itemView.findViewById(R.id.vochernum);
           preview=itemView.findViewById(R.id.preview);
             CusName=itemView.findViewById(R.id.customer);
            TotalQty=itemView.findViewById(R.id.net_sales);
            Nettotal=itemView.findViewById(R.id.totalqty);
            serial=itemView.findViewById(R.id.serial);
        }
    }
}
