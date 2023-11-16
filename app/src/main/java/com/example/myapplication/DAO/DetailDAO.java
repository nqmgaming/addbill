package com.example.myapplication.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.myapplication.DbHelper;
import com.example.myapplication.Model.Detail;

import java.util.ArrayList;



public class DetailDAO {

    DbHelper dbHelper;
    SQLiteDatabase db;

    public DetailDAO(Context ct){
        dbHelper = new DbHelper( ct ) ;
        db = dbHelper.getWritableDatabase();
    }

    public ArrayList<Detail> getList(){

        ArrayList<Detail> listObj = new ArrayList<>();

        Cursor c = db.rawQuery("SELECT tbl_detail.*, name FROM tbl_detail INNER JOIN tbl_product ON tbl_product.id = tbl_detail.id_product", null);
        if(c.getCount()>0){
            c.moveToFirst(); // đưa trỏ về đầu dòng
            do {
                Detail obj = new Detail();
                obj.setId_void(c.getInt(0));
                obj.setId_product(c.getInt(1));
                obj.setQuantity(c.getInt(2));
                obj.setGia_mua(c.getInt(3));
                obj.setName( c.getString( 4));

                // cho đối tượng vào danh sách
                listObj.add( obj );

            }while (c.moveToNext());
        }else{
            Log.d("zzzzzzzzzzzz", "getList: Không có dữ liệu");
        }
        return  listObj;
    }

    public long insert(Detail obj){
        ContentValues values = new ContentValues();
        values.put("id_void",obj.getId_void());
        values.put("id_product",obj.getId_product());
        values.put("quantity",obj.getQuantity());
        values.put("gia_mua",obj.getGia_mua());
        return db.insert("tbl_detail",null,values);
    }

    public ArrayList<Detail> getListByIdVoid(int Id) {
        ArrayList<Detail> list = new ArrayList<>();
        String query = "SELECT tbl_detail.*, name FROM tbl_detail" +
                "  INNER JOIN tbl_product ON tbl_product.id = tbl_detail.id_product" +
                " WHERE tbl_detail.id_void= ?";

        Cursor c = db.rawQuery(query, new String[]{String.valueOf(Id)});
        if(c.getCount()>0){
            c.moveToFirst(); // đưa trỏ về đầu dòng
            do {
                Detail obj = new Detail();
                obj.setId_void(c.getInt(0));
                obj.setId_product(c.getInt(1));
                obj.setQuantity(c.getInt(2));
                obj.setGia_mua(c.getInt(3));
                obj.setName( c.getString( 4));

                // cho đối tượng vào danh sách
                list.add( obj );

            }while (c.moveToNext());
        }else{
            Log.d("zzzzzzzzzzzz", "getList: Không có dữ liệu");
        }
        return  list;


    }


}
