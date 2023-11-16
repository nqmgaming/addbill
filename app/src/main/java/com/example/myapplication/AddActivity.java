package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Adapter.SpinnerProductApdapter;
import com.example.myapplication.DAO.DetailDAO;
import com.example.myapplication.DAO.ProductDAO;
import com.example.myapplication.Model.Detail;
import com.example.myapplication.Model.Product;
import com.example.myapplication.Model.VoidProduct;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

public class AddActivity extends AppCompatActivity {
    TextView tv_id_void;
    Spinner spinner;
    TextInputLayout ed_gia_mua,ed_quantity;
    Button btnSave;

    ProductDAO productDAO;
    SpinnerProductApdapter spinnerProductApdapter;

    DetailDAO detailDAO;
    int idProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        tv_id_void =findViewById(R.id.tv_id_void);
        spinner =findViewById(R.id.spinner_product);
        ed_quantity= findViewById(R.id.ed_quantity);
        ed_gia_mua =findViewById(R.id.ed_gia_mua);
        btnSave =findViewById(R.id.btnSave);

        Bundle bundle = getIntent().getExtras();
        if(bundle==null){
            return;
        }

        VoidProduct objVoidProduct = (VoidProduct) bundle.get("void");
        tv_id_void.setText("ID hóa đơn: "+objVoidProduct.getId_void()+"");

        productDAO = new ProductDAO(this);
        List<Product> listPro = productDAO.getList();
        spinnerProductApdapter = new SpinnerProductApdapter(listPro,this);
        spinner.setAdapter(spinnerProductApdapter);

        detailDAO = new DetailDAO(this);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                idProduct = listPro.get(i).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int quantity = Integer.parseInt(ed_quantity.getEditText().getText().toString());
                int gia_mua = Integer.parseInt(ed_gia_mua.getEditText().getText().toString());

                Detail detail = new Detail(objVoidProduct.getId_void(),idProduct,quantity,gia_mua);
                long check = detailDAO.insert(detail);
                if(check>0){
                    Toast.makeText(AddActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(AddActivity.this, MainActivity.class));
                }else Toast.makeText(AddActivity.this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
            }
        });
    }
}