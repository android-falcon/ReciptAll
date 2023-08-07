package com.hiaryabeer.receipts.Interfaces;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.hiaryabeer.receipts.models.ReceiptDetails;

import java.util.List;

@Dao
public interface ReceiptDetails_Dao {
    @Query("SELECT * FROM ReceiptDetails_Table Where IS_Posted='0'")
    List<ReceiptDetails> getAllOrders();
    @Query("UPDATE ReceiptDetails_Table SET  IS_Posted='1' WHERE IS_Posted='0' AND VOUCHERTYPE= :VOUCHERTYPE")
    int  updateVoucherDetailsByType (String VOUCHERTYPE);



    @Query("SELECT * FROM ReceiptDetails_Table Where IS_Posted='0'")
    List<ReceiptDetails> getAllOrdersConfirm();



    @Query("SELECT * FROM ReceiptDetails_Table Where IS_Posted='0' AND VOUCHERTYPE= :VOUCHERTYPE")
    List<ReceiptDetails> getAllOrdersConfirmBytype(String VOUCHERTYPE );

    @Query("SELECT * FROM ReceiptDetails_Table Where vhfNo= :VHFNO and IS_Posted='0'")
    List<ReceiptDetails> getAllOrdersByNumber(int VHFNO );
    @Insert
    void insertAllOrders(ReceiptDetails  receiptDetails);
    @Insert
    void insertOrder(ReceiptDetails  receiptDetails);

    @Delete
    void deleteOrder(ReceiptDetails receiptDetails);

    @Query("UPDATE ReceiptDetails_Table SET  IS_Posted='1' WHERE IS_Posted='0'")
    int  updateVoucherDetails ();

    @Query("delete from ReceiptDetails_Table where vhfNo= :vohno")
    int deleteOrderByVOHNO(int vohno);

    @Query("SELECT * FROM ReceiptDetails_Table Where vhfNo= :VHFNO and VOUCHERTYPE= :type")
    List<ReceiptDetails> getOrdersByNumber(long VHFNO ,int type);

    @Query("select * from ReceiptDetails_Table where TransNo= :Tranno and vhfNo= :vohno")
    List<ReceiptDetails> getOrderByTransNo(long vohno,long Tranno);
    @Query("SELECT * FROM ReceiptDetails_Table Where vhfNo= :VHFNO")
    List<ReceiptDetails> getOrdersByNumber2(long VHFNO );

}
