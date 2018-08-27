package com.namvova.namgiza_sqlite_sample.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.namvova.namgiza_sqlite_sample.Adapter.SinhVienAddapter;
import com.namvova.namgiza_sqlite_sample.Database.TODO_Sinhvien;
import com.namvova.namgiza_sqlite_sample.Model.SinhVien;
import com.namvova.namgiza_sqlite_sample.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText edit_name, edit_Class, edit_Condition;
    private Button btn_add, btn_select, btn_selectall, btn_update, btn_delete, btn_deleteall;
    private ListView listView;
    private SinhVienAddapter addapter;
    private List<SinhVien> listdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setWiget();
        listdata = new ArrayList<>();
        setAddapter();
        final TODO_Sinhvien todo_sinhvien = new TODO_Sinhvien(this);

        // XU LY BUTTON
        // ADD SINH VIEN
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                todo_sinhvien.addStudent(getInfoSinhVien());
                setAddapter();
                
            }
        });

        // SELECT all
        btn_selectall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setAddapter();
                listdata.clear();
                listdata.addAll(todo_sinhvien.selectAll());
                setAddapter();

            }
        });
        // SELECT BY ID
        btn_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listdata = new ArrayList<>();
                listdata.add(todo_sinhvien.selectByID(Integer.parseInt(edit_Condition.getText().toString())));
                edit_Condition.setText("");
                edit_Condition.isFocusable();
                setAddapter();
            }
        });

        // UPDATE
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(todo_sinhvien.upDate(getInfoSinhVien()) == 0){
                    Toast.makeText(getBaseContext(),"UPDATE SUSSES !",Toast.LENGTH_LONG).show();
                    listdata.addAll(todo_sinhvien.selectAll());
                    setAddapter();
                }
            }
        });
        // DELETE ALL
        btn_deleteall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(todo_sinhvien.delete() == 0){
                    Toast.makeText(getBaseContext(),"DELETE ALL SUSSES !",Toast.LENGTH_LONG).show();
                    listdata.addAll(todo_sinhvien.selectAll());
                    setAddapter();
                }
            }
        });
        // DELETE BY ID
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(todo_sinhvien.deleteByID(getInfoSinhVien()) == 0){
                    Toast.makeText(getBaseContext(),"DELETE  SUSSES !",Toast.LENGTH_LONG).show();
                    listdata.addAll(todo_sinhvien.selectAll());
                    setAddapter();
                }
            }
        });
    }


    /*
    setWIGET
    */
    private void setWiget() {
        edit_name = findViewById(R.id.edt_name);
        edit_Class = findViewById(R.id.edt_class);
        edit_Condition = findViewById(R.id.edt_condition);
        listView = findViewById(R.id.lv);
        btn_add = findViewById(R.id.add);
        btn_select = findViewById(R.id.selectone);
        btn_selectall = findViewById(R.id.selectall);
        btn_update = findViewById(R.id.update);
        btn_delete = findViewById(R.id.deleteone);
        btn_deleteall = findViewById(R.id.deleteall);
    }

    private void setAddapter() {
        if (addapter == null) {
            addapter = new SinhVienAddapter(this, R.layout.item_sinhvien, listdata);

        } else {
            addapter.notifyDataSetChanged();
        }
        listView.setAdapter(addapter);
    }
    private SinhVien getInfoSinhVien(){
        SinhVien sinhVien = new SinhVien(edit_name.toString(),edit_Class.toString());
        edit_name.setText("");
        edit_Class.setText("");
        edit_name.isFocusable();
        return sinhVien;
    }
}
