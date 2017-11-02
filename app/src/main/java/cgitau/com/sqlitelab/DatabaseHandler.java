package cgitau.com.sqlitelab;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cgita on 10/19/2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION =1;

    private static final String DATABASE_NAME = "contactsManager";

    private static final String TABLE_CONTACTS = "contacts";
    private static final String TABLE_EMPLOYEE = "employee";

    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PH_NO = "phone_number";

    private static final String KEY_ID2 = "_id";
    private static final String KEY_SALARY = "salary";
    private static final String KEY_DEPARTMENT = "department";
    private static final String KEY_RANK = "rank";



    public DatabaseHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //creating the contacts table
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_PH_NO + " TEXT" + ");";
        db.execSQL(CREATE_CONTACTS_TABLE);
        //creating the employee table
        String CREATE_EMPLOYEE_TABLE = "CREATE TABLE " + TABLE_EMPLOYEE + "(" + KEY_ID2 + " INTEGER PRIMARY KEY," + KEY_DEPARTMENT + " TEXT,"
                + KEY_RANK + " TEXT" + KEY_SALARY  + " INTEGER" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
        db.execSQL(CREATE_EMPLOYEE_TABLE);
    }

    //UPGRADING THE DATABASE
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_EMPLOYEE);

        onCreate(db);
    }

    public void addContact(Contact contact){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.get_name());
        values.put(KEY_PH_NO, contact.get_phone_number());

        db.insert(TABLE_CONTACTS, null, values);
        db.close();
    }

    public void addEmployee(employee Employee){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_SALARY, Employee.getSalary());
        values.put(KEY_DEPARTMENT, Employee.getDepartment());
        values.put(KEY_RANK, Employee.getRank());

        db.insert(TABLE_EMPLOYEE, null, values);
        db.close();
    }



    public Contact getContact(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CONTACTS, new String[] { KEY_ID,
        KEY_NAME, KEY_PH_NO }, KEY_ID + "=?", new String[] { String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        Contact contact = new Contact(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));
        //return contact
        return contact;
    }

    public List<Contact> getAllContacts(){
        List<Contact> contactList = new ArrayList<>();

        String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()){
            do{
                Contact contact = new Contact();
                contact.set_id(Integer.parseInt(cursor.getString(0)));
                contact.set_name(cursor.getString(1));
                contact.set_phone_number(cursor.getString(2));

                contactList.add(contact);
            }while(cursor.moveToNext());
        }
        return contactList;
    }
    //List employees details
    public List<employee> getAllEmployee(){
        List<employee> employeeList = new ArrayList<>();

        String selectQuery = "SELECT  * FROM " + TABLE_EMPLOYEE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()){
            do{
                employee Employee = new employee();
                Employee.setId(Integer.parseInt(cursor.getString(0)));
                Employee.setSalary(Integer.parseInt(cursor.getString(1)));
                Employee.setDepartment(cursor.getString(2));
                Employee.setRank(cursor.getString(2));

                employeeList.add(Employee);
            }while(cursor.moveToNext());
        }
        return employeeList;
    }

    public int getContactsCount(){
        String countQuery = "SELECT  * FROM " + TABLE_CONTACTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        return cursor.getCount();
    }

    public int updateContact(Contact contact){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.get_name());
        values.put(KEY_PH_NO, contact.get_phone_number());
        return db.update(TABLE_CONTACTS, values, KEY_ID + " = ?",
        new String [] { String.valueOf(contact.get_id())} );
    }

    public void deleteContact(Contact contact){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACTS, KEY_ID + " = ?",
                new String[] { String.valueOf(contact.get_id())});
        db.close();
    }
}
