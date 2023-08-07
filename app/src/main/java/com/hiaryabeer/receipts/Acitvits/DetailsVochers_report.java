package com.hiaryabeer.receipts.Acitvits;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hiaryabeer.receipts.Adapters.DetailsAdapter;
import com.hiaryabeer.receipts.Adapters.MasterAdapter;
import com.hiaryabeer.receipts.R;
import com.hiaryabeer.receipts.models.AppDatabase;
import com.hiaryabeer.receipts.models.GeneralMethod;
import com.hiaryabeer.receipts.models.ReceiptDetails;
import com.hiaryabeer.receipts.models.ReceiptMaster;

import java.util.ArrayList;
import java.util.List;

public class DetailsVochers_report extends AppCompatActivity {
    RecyclerView.LayoutManager layoutManager;
    RecyclerView ordersDetalisRec;
    List<ReceiptDetails> detailsList = new ArrayList<>();
    AppDatabase mydatabase;
    TextView vochernum, Cus_name, date, total,tax,netsales;
   long VohNu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_vochers_report);
   //     try {
            init();
            Log.e("AdapterVOH_NUM==",MasterAdapter.VOH_NUM+" ,");
            detailsList=mydatabase.receiptDetails_dao().getOrdersByNumber2(MasterAdapter.VOH_NUM);
            Log.e("detailsList==",detailsList.size()+"");
            fillDataText();
            filladapter();
  //      }
//        catch (Exception e){
//            Log.e("Exception=",e.getMessage());
//        }

    }

    private void filladapter() {
        ordersDetalisRec.setAdapter(new DetailsAdapter(detailsList,DetailsVochers_report.this));
    }

    void init(){
        ordersDetalisRec = findViewById(R.id.ordersDetalisRec);
        total = findViewById(R.id.total);
        vochernum = findViewById(R.id.ORDERNO);
        netsales= findViewById(R.id.netsales);
        Cus_name = findViewById(R.id.Cus_name);
        tax= findViewById(R.id.tax);
        Log.e("VOHNO==", VohNu + "");
        mydatabase = AppDatabase.getInstanceDatabase(DetailsVochers_report.this);
        ordersDetalisRec = findViewById(R.id.ordersDetalisRec);
        ordersDetalisRec.setLayoutManager(new LinearLayoutManager(DetailsVochers_report.this));
        date = findViewById(R.id.date);


    }
  void  fillDataText(){
      total.setText("");
      vochernum.setText(detailsList.get(0).getVhfNo()+"");
      String customerName =mydatabase.customers_dao().getCustmByNumber(detailsList.get(0).getCustomerId()) ;

      Cus_name.setText(customerName+"");
      date    .setText(detailsList.get(0).getDate()+"");
     ReceiptMaster receiptMaster =mydatabase.receiptMaster_dao().getOrderByVOHNO2(detailsList.get(0).getVhfNo());
      netsales  .setText(GeneralMethod.convertToEnglish(String.format("%.3f", receiptMaster.getNetTotal())+""));
      tax .setText(GeneralMethod.convertToEnglish(String.format("%.3f",receiptMaster.getTax())+""));
    }
}