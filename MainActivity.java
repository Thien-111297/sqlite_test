package vn.itplus.sqlite_test;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView lvCongViec;
    ArrayAdapter<Job>jobAdapter;
    List<Job>jobList;
    MyDataBaseHelper myDataBaseHelper;
    private static final int MY_REQUEST_CODE = 11;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDataBaseHelper = new MyDataBaseHelper(this);
        jobList = myDataBaseHelper.getAllJob();
        jobList.add(new Job("Lập trình android"));
        setTitle("Danh sách công việc");
        addControls();
        addEvents();
    }

    private void addEvents() {
        if(jobAdapter==null) {
            jobAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1,jobList);
            lvCongViec.setAdapter(jobAdapter);
        } else {
            jobAdapter.notifyDataSetChanged();
            lvCongViec.setSelection(jobAdapter.getCount()-1);

        }
        lvCongViec.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Job job = jobList.get(i);
                int result = myDataBaseHelper.deleteDataBase(job.getId());
                if(result>0){
                    jobList.clear();
                    jobList.addAll(myDataBaseHelper.getAllJob());
                    if(jobAdapter!=null){
                        jobAdapter.notifyDataSetChanged();
                    }
                }
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_item,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.icAdd:
                Intent intent = new Intent(MainActivity.this,AddJob.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void addControls() {
        lvCongViec = findViewById(R.id.lvCongViec);
    }
}
