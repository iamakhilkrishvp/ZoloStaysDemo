package zolo.in.zolostays.zolostaysdemo.Activity.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anujith47 on 21/07/17.
 */

public class ZoloDbHelper extends SQLiteOpenHelper{

    private static int databaseversion = 1;
    private static final String databasename = "zolo_db";
    private static final String userDetailsTable = "userDetailsTable";
    private static final String userId = "userId";
    private static final String userName = "userName";
    private static final String password = "password";
    private static final String userPhoneNumber = "userPhoneNumber";
    private static final String userEmailId = "userEmailId";


    private static final String createUserDetailsTable = "create table userDetailsTable(userId integer primary key autoincrement,"
            + "userName text,"
            + "password text,"
            + "userPhoneNumber text,"
            + "userEmailId text);";

    public ZoloDbHelper(Context context) {
        super(context, databasename, null, databaseversion);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createUserDetailsTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public long addUserDetailsToUserDetailsTable(User user) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(userName, user.getUsername());
        values.put(userPhoneNumber, user.getUserPhoneNumber());
        values.put(userEmailId, user.getUserEmailId());
        values.put(password, user.getPassword());

        long data = db.insert(userDetailsTable, null, values);

        db.close();
        return data;
    }
    public List<User> getUserDetailsFromUserDetailsTable() {

        SQLiteDatabase db = this.getReadableDatabase();
        List<User> userDetailsList = new ArrayList<User>();
        String getUserDetailsQuery = "SELECT  * FROM userDetailsTable ";
        Cursor cursor = db.rawQuery(getUserDetailsQuery, null);
        if (cursor != null)
            cursor.moveToFirst();

        if (!cursor.isAfterLast()) {
            do {

                User user = new User();
                user.setUserId(cursor.getInt(0));
                user.setUsername(cursor.getString(1));
                user.setPassword(cursor.getString(2));
                user.setUserPhoneNumber(cursor.getString(3));
                user.setUserEmailId(cursor.getString(4));

                userDetailsList.add(user);

            } while (cursor.moveToNext());
        }
        return userDetailsList;
    }
    public List<User> getUserDetailsFromUserDetailsTable(String userPhoneNumber) {

        SQLiteDatabase db = this.getReadableDatabase();
        List<User> userDetailsList = new ArrayList<User>();
        String getUserDetailsQuery = "SELECT  * FROM userDetailsTable WHERE userName = '"+
                userPhoneNumber+"'";
        Cursor cursor = db.rawQuery(getUserDetailsQuery, null);
        if (cursor != null)
            cursor.moveToFirst();

        if (!cursor.isAfterLast()) {
            do {

                User user = new User();
                user.setUserId(cursor.getInt(0));
                user.setUsername(cursor.getString(1));
                user.setPassword(cursor.getString(2));
                user.setUserPhoneNumber(cursor.getString(3));
                user.setUserEmailId(cursor.getString(4));

                userDetailsList.add(user);

            } while (cursor.moveToNext());
        }
        return userDetailsList;
    }
}
