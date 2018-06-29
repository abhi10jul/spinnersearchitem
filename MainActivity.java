package abhiandroidknowledge.blogspot.com.spinnersearchitem;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ArrayList<String> items = new ArrayList<>();
    private SpinnerDialog spinnerDialog;
    private ListView listView;
    private TextView tvCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addItems();
        tvCity = (TextView) findViewById(R.id.txt_result);
        findViewById(R.id.et_spinner_city).setOnClickListener(this);
    }

    private void addItems() {
        items.add("Mumbai");
        items.add("Delhi");
        items.add("Bengaluru");
        items.add("Hyderabad");
        items.add("Ahmedabad");
        items.add("Chennai");
        items.add("Kolkata");
        items.add("Surat");
        items.add("Pune");
        items.add("Jaipur");
        items.add("Lucknow");
        items.add("Kanpur");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.et_spinner_city:
                showSpinnerDialog();
                break;

            default:
                break;
        }
    }

    private void showSpinnerDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(R.layout.search_dialog);
        builder.setCancelable(true);
        final AlertDialog alertDialog;
        alertDialog = builder.create();
        alertDialog.show();
        listView = (ListView) alertDialog.findViewById(R.id.list);
        final EditText searchCity = (EditText) alertDialog.findViewById(R.id.searchcity);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        findViewById(R.id.et_spinner_city).setOnClickListener(this);
        listView.setAdapter(adapter);
        TextView close = (TextView) alertDialog.findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                alertDialog.dismiss();
                Toast.makeText(MainActivity.this, adapter.getItem(position).toString(), Toast.LENGTH_SHORT).show();
                tvCity.setText(adapter.getItem(position).toString());
            }
        });

        searchCity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                adapter.getFilter().filter(searchCity.getText().toString().trim());
            }
        });

    }
}
