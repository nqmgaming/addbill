package com.example.myapplication.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.myapplication.DbHelper;
import com.example.myapplication.Model.Product;

import java.util.ArrayList;



public class ProductDAO {

    DbHelper dbHelper;
    SQLiteDatabase db;

    public ProductDAO(Context ct){
        dbHelper = new DbHelper( ct ) ;
        db = dbHelper.getWritableDatabase();
    }

    public ArrayList<Product> getList(){

        ArrayList<Product> listObj = new ArrayList<>();

        Cursor c = db.rawQuery("SELECT * FROM tbl_product ", null);
        if(c.getCount()>0){
            c.moveToFirst(); // đưa trỏ về đầu dòng
            do {
                Product obj = new Product();
                obj.setId(c.getInt(0));
                obj.setName(c.getString(1));
                obj.setPrice(c.getInt(2));


                // cho đối tượng vào danh sách
                listObj.add( obj );

            }while (c.moveToNext());
        }else{
            Log.d("zzzzzzzzzzzz", "getList: Không có dữ liệu");
        }
        return  listObj;
    }
}
