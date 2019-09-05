package vn.itplus.sqlite_test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class AddJob extends AppCompatActivity {
    Button btnLuu,btnVe;
    EditText edtNoiDung,edtTieuDe;
    MyDataBaseHelper myDataBaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_job);
        setTitle("Quản lý công việc");
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Job job = new Job();
                job.setTieude(edtTieuDe.getText().toString());
                job.setNoiDung(edtNoiDung.getText().toString());
                MyDataBaseHelper myDataBaseHelper = new MyDataBaseHelper(AddJob.this);
                myDataBaseHelper.addJob(job);

                Toast.makeText(AddJob.this, "Thêm thành công", Toast.LENGTH_LONG).show();
            }
        });
        btnVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddJob.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void addControls() {
        btnLuu      = findViewById(R.id.btnLuu);
        btnVe       = findViewById(R.id.btnVe);
        edtNoiDung  = findViewById(R.id.edtNoiDung);
        edtTieuDe   = findViewById(R.id.edtTieuDe);
    }
}
