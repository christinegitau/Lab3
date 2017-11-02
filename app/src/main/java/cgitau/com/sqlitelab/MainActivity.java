package cgitau.com.sqlitelab;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        DatabaseHandler db = new DatabaseHandler(this);

        Log.d("Insert: ", "Inserting ..");
        db.addContact(new Contact("Ravi", "95262"));
        db.addContact(new Contact("Srinivias", "948562"));
        db.addContact(new Contact("Aukot", "95624"));
        db.addContact(new Contact("Dida", "925486"));

        db.addEmployee(new employee(1500, "Hospitality", "Basic"));
        db.addEmployee(new employee(2000, "Teaching", "Senior"));
        db.addEmployee(new employee(3000, "Accounting", "Intern"));

        Log.d("Reading: ", "Reading all contacts..");
        List<Contact> contacts = db.getAllContacts();
        List<employee> Employee = db.getAllEmployee();

        for (Contact cn : contacts){
            String log = "Id: "+cn.get_id()+" ,Name: " + cn.get_name() + " ,Phone Number: " +cn.get_phone_number();
            Log.d("Name: ", log);
        }

        for (employee em : Employee){
            String log1 = "Id: "+em.getId()+" ,Salary: " + em.getSalary() + " ,Department: " +em.getDepartment()
                    + " ,Rank: " +em.getRank();
            Log.d("Name: ", log1);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
