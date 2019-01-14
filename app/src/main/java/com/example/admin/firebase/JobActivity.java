package com.example.admin.firebase;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.firebase.model.Job;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;


public class JobActivity extends AppCompatActivity {
    private FirebaseAuth auth;
    private DatabaseReference myRef;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseDatabase ref;
    private String userID;
    ArrayList<String> a = new ArrayList<String>();
    ListView lv;

    ArrayList<Job> ds = new ArrayList<>();
    myadapter adapter;
    TextView tv1,tv2,tv3,tv4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job);
        myRef =FirebaseDatabase.getInstance().getReference("Job");
        auth = FirebaseAuth.getInstance();
        ref = FirebaseDatabase.getInstance();
        userID = auth.getCurrentUser().getUid();
        lv = findViewById(R.id.listview);
        //tv1 = findViewById(R.id.tv_ten);
        //tv2 = findViewById(R.id.tv_job);
        //tv3 = findViewById(R.id.tv_address);
        //tv4 = findViewById(R.id.tv_tel);



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("My App");
        setSupportActionBar(toolbar);




            myRef = ref.getReference().child("Job").child(userID);
            ValueEventListener valueEventListener = new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Job job = dataSnapshot.getValue(Job.class);
                    String username = job.getUsername();
                    String Job = job.getjob();
                    long tel = job.getPhone_number();
                    String address = job.getAddress();
                    Job job1 = new Job(userID, username, Job, address, tel);
                    //ds.add(job1);
                    //adapter = new myadapter(JobActivity.this, ds);
                    //lv.setAdapter(adapter);
                    /*if (job1!= null) {
                        ds.add(job1);
                        adapter = new myadapter(JobActivity.this, ds);
                        lv.setAdapter(adapter);

                    }*/
                    if (dataSnapshot.exists()) {
                        ds.add(job1);
                        adapter = new myadapter(JobActivity.this, ds);
                        lv.setAdapter(adapter);


                    }

                    else
                    {
                        Toast.makeText(JobActivity.this, "No Informations", Toast.LENGTH_SHORT).show();
                        }



                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            };
            myRef.addListenerForSingleValueEvent(valueEventListener);





    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inf =getMenuInflater();
        inf.inflate(R.menu.job, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        switch (id)
        {
            case R.id.Dialog :{
                thembangdialog();
                break;

            }
            case R.id.Back :{
                finish();
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }
    private void thembangdialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(JobActivity.this);
        LayoutInflater inf = getLayoutInflater();
        final View view = inf.inflate(R.layout.dialog,null);
        builder.setView(view);
        builder.setPositiveButton("them", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                EditText ed_ten =  view.findViewById(R.id.ed_ten);
                final EditText ed_congviec = view.findViewById(R.id.ed_congviec);
                EditText ed_address = view.findViewById(R.id.ed_address);
                EditText ed_Tel = view.findViewById(R.id.ed_tel);
                final Spinner sp =view.findViewById(R.id.spinner);
                Button bt = view.findViewById(R.id.button);
                a.add("tho may");
                a.add("tho lanh");
                a.add("tho dien");
                a.add("tho xay");
                /*CongViec cv = new CongViec(ed_noidung.getText().toString(),ed_thoigian.getText().toString());
                qlcv.themcongviec(cv);
                Capnhatgiaodien();*/
                ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(JobActivity.this,android.R.layout.simple_spinner_item,a);
                adapter1.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
                sp.setAdapter(adapter1);
                bt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        switch (sp.getSelectedItem().toString()){
                            case "Tho dien":
                                ed_congviec.setText("tho dien");
                                break;
                            case "Tho lanh" :
                                ed_congviec.setText("tho lanh");
                                break;
                            case "Tho may" :
                                ed_congviec.setText("tho may");
                                break;
                            case "tho xay":
                                ed_congviec.setText("tho xay");
                                break;


                        }
                    }
                });
                Job job = new Job(userID,ed_ten.getText().toString(),ed_congviec.getText().toString(),ed_address.getText().toString(),Integer.parseInt(ed_Tel.getText().toString()));

                if(!TextUtils.isEmpty(ed_ten.getText().toString()))
                {
                    myRef.child(userID).setValue(job);

                }else{
                    Toast.makeText(JobActivity.this, "You should enter a name", Toast.LENGTH_SHORT).show();
                }


            }
        });
        builder.setNegativeButton("huy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();

            }
        });

        builder.show();

    }
    public  void Laycongviec ()
    {
        myRef = ref.getReference().child("Job").child(userID);
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Job job = dataSnapshot.getValue(Job.class);
                String username = job.getUsername();
                String Job = job.getjob();
                long tel = job.getPhone_number();
                String address =job.getAddress();
                Job job1 = new Job(userID,username,Job,address,tel);
                ds.add(job1);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        };
        myRef.addListenerForSingleValueEvent(valueEventListener);
    }


}

