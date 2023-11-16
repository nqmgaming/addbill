package com.example.myapplication.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.myapplication.DbHelper;
import com.example.myapplication.Model.VoidProduct;

import java.util.ArrayList;


public class VoidDAO  {
    DbHelper dbHelper;
    SQLiteDatabase db;

    public VoidDAO(Context ct){
        dbHelper = new DbHelper( ct ) ;
        db = dbHelper.getWritableDatabase();
    }

    public ArrayList<VoidProduct> getList(){

        ArrayList<VoidProduct> listObj = new ArrayList<>();

        Cursor c = db.rawQuery("SELECT * FROM tbl_void ", null);
        if(c.getCount()>0){
            c.moveToFirst(); // đưa trỏ về đầu dòng
            do {
                VoidProduct obj = new VoidProduct();
                obj.setId_void(c.getInt(0));
                obj.setId_customer(c.getInt(1));
                obj.setId_staff(c.getInt(2));
                obj.setDate_buy(c.getString(3));
                obj.setStatus(c.getString(4));

                // cho đối tượng vào danh sách
                listObj.add( obj );

            }while (c.moveToNext());
        }else{
            Log.d("zzzzzzzzzzzz", "getList: Không có dữ liệu");
        }
        return  listObj;
    }


}
