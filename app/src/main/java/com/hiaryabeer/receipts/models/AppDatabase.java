package com.hiaryabeer.receipts.models;


import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.hiaryabeer.receipts.Interfaces.Customers_Dao;
import com.hiaryabeer.receipts.Interfaces.ItemMaster_ItemBalanceDao;
import com.hiaryabeer.receipts.Interfaces.ItemSwitchDao;
import com.hiaryabeer.receipts.Interfaces.ItemUnits_Dao;
import com.hiaryabeer.receipts.Interfaces.ItemsBalanceDao;
import com.hiaryabeer.receipts.Interfaces.Items_Dao;
import com.hiaryabeer.receipts.Interfaces.MaxVoucherDao;
import com.hiaryabeer.receipts.Interfaces.ReceiptDetails_Dao;
import com.hiaryabeer.receipts.Interfaces.ReceiptMaster_Dao;
import com.hiaryabeer.receipts.Interfaces.Users_Dao;

@Database(entities = {Items.class,ReceiptMaster.class,ReceiptDetails.class,Item_Unit_Details.class,CustomerInfo.class,User.class,ItemsBalance.class,ItemSwitch.class,MaxVoucher.class}, version =1)

public abstract class AppDatabase extends RoomDatabase {
    public abstract Items_Dao itemsDao();
    public abstract ItemUnits_Dao itemUnitsDao();
    public abstract Customers_Dao customers_dao();
    public abstract ReceiptMaster_Dao receiptMaster_dao();
    public abstract ReceiptDetails_Dao receiptDetails_dao();
    private static AppDatabase InstanceDatabase;
    public abstract Users_Dao usersDao();
    public abstract ItemsBalanceDao itemsBalanceDao();
    public abstract ItemMaster_ItemBalanceDao itemMaster_itemBalanceDao();
    public abstract ItemSwitchDao itemSwitchDao();
    public abstract MaxVoucherDao maxVoucherDao();
    public static String DatabaseName = "Receipts_Database";

    public static synchronized AppDatabase getInstanceDatabase(Context context) {

        if (InstanceDatabase == null) {

            InstanceDatabase = Room.databaseBuilder(context, AppDatabase.class, DatabaseName)
                    .allowMainThreadQueries()
                      .addMigrations()
                    .fallbackToDestructiveMigration()
                    .build();

        }

        return InstanceDatabase;

    }

}
