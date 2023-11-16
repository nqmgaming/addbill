package com.example.myapplication;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Adapter.VoidAdapter;
import com.example.myapplication.DAO.VoidDAO;
import com.example.myapplication.Model.VoidProduct;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    RecyclerView rc ;

    VoidDAO voidDAO;
    ArrayList<VoidProduct> listVoidProduct;
    VoidAdapter voidAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rc =findViewById(R.id.rc_void);
        voidDAO = new VoidDAO(this);
        listVoidProduct =voidDAO.getList();
        voidAdapter = new VoidAdapter(listVoidProduct,this);
        rc.setAdapter(voidAdapter);
    }
}