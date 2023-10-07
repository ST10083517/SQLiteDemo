package com.example.sqlitedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    //References to View Controls in class
    Button btnViewAll , btnAdd;
    Switch swActiveCustomer;
    ListView lvCustomerList;

    EditText etName , etAge;

    DatabaseHelper  databaseHelper = new DatabaseHelper(MainActivity.this);
    ArrayAdapter customerArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    btnViewAll = findViewById(R.id.btnViewAll);
    btnAdd = findViewById(R.id.btnAdd);
        swActiveCustomer = findViewById(R.id.swSActiveCustomer);
    lvCustomerList = findViewById(R.id.lvCustomerList);
    etName  = findViewById(R.id.etName);
    etAge  = findViewById(R.id.etAge);


        setCustomerAdapter();


        btnAdd.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {

          Customer customerModel;

          try{

              customerModel = new Customer(-1,etName.getText().toString() , Integer.parseInt(etAge.getText().toString())
                  , swActiveCustomer.isChecked());

              Toast.makeText(MainActivity.this, customerModel.toString(), Toast.LENGTH_LONG).show();
          }

          catch(Exception e){
               customerModel = new Customer(-1,"No Name", 0 , false);
              Toast.makeText(MainActivity.this, customerModel.toString(), Toast.LENGTH_LONG).show();


          }


          boolean bSuccess = databaseHelper.addOne(customerModel);

          setCustomerAdapter();

          // Toast.makeText(MainActivity.this, "Success" + bSuccess, Toast.LENGTH_SHORT).show();


      }
  });
btnViewAll.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {


      setCustomerAdapter();


        lvCustomerList.setAdapter(customerArrayAdapter);

        //Toast.makeText(MainActivity.this, databaseRecords.toString(), Toast.LENGTH_LONG).show();
    }
});

lvCustomerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Customer clickedCustomerRecord = (Customer) parent.getItemAtPosition(position);
        databaseHelper.deleteCustomerRecord(clickedCustomerRecord);
        setCustomerAdapter();
        Toast.makeText(MainActivity.this, "Deleted a Customer", Toast.LENGTH_SHORT).show();
    }
});
    }

    public void setCustomerAdapter() {
        ArrayAdapter customerArrayAdapter  = new ArrayAdapter<Customer>(MainActivity.this,
                android.R.layout.simple_list_item_1, databaseHelper.getAllDatabaseRecords());
        lvCustomerList.setAdapter(customerArrayAdapter);
    }
}