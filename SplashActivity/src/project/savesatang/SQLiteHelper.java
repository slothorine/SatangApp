package project.savesatang;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.view.View;

public class SQLiteHelper extends SQLiteOpenHelper {

	public SQLiteHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	public static final String TABLE_INCOME = "income";
	public static final String COLUMN_INID = "inid";
	public static final String COLUMN_INCATEGORYID = "incategoryid";
	public static final String COLUMN_INVALUE = "invalue";
	public static final String COLUMN_INDETAIL = "indetail";
	public static final String COLUMN_INDATE = "indate";
	public static final String COLUMN_INCATYPE = "incatype";
	public static final String COLUMN_INCACATEGORY = "incacategory";
	public static final String COLUMN_INCANAME = "incaname";
	
	public static final String TABLE_EXPENSE = "expense";
	public static final String COLUMN_EXID = "exid";
	public static final String COLUMN_EXCATEGORYID = "excategoryid";
	public static final String COLUMN_EXVALUE = "exvalue";
	public static final String COLUMN_EXDETAIL = "exdetail";
	public static final String COLUMN_EXDATE = "exdate";
	public static final String COLUMN_EXCATYPE = "excatype";
	public static final String COLUMN_EXCACATEGORY = "excacategory";
	public static final String COLUMN_EXCANAME = "excaname";

	public static final String TABLE_CATEGORY = "category";
	public static final String COLUMN_CAID = "caid";
	public static final String COLUMN_CATYPE = "catype";
	public static final String COLUMN_CACATEGORY = "cacategory";
	public static final String COLUMN_CANAME = "caname";
	
	public static final String TABLE_SUMMARY = "summary";
	public static final String COLUMN_SUMID = "sumid";
	public static final String COLUMN_SUMCATEGORYID = "sumcategoryid";
	public static final String COLUMN_SUMEXVALUE = "sumexvalue";
	public static final String COLUMN_SUMINVALUE = "suminvalue";
	public static final String COLUMN_SUMDATE = "sumdate";
	public static final String COLUMN_SUMCATYPE = "sumcatype";
	public static final String COLUMN_SUMCACATEGORY = "sumcacategory";
	public static final String COLUMN_SUMCANAME = "sumcaname";

	private static final String DATABASE_NAME = "satang.db";
	private static final int DATABASE_VERSION = 1;
	
	private static final String SUMMARY_TABLE_CREATE = "create table "
			+ TABLE_SUMMARY + "(" 
			+ COLUMN_SUMID + " integer primary key autoincrement, " 
			+ COLUMN_SUMEXVALUE + " numeric,"
			+ COLUMN_SUMINVALUE + " numeric," 
			+ COLUMN_SUMDATE + " datetime,"
			+ COLUMN_SUMCATYPE + " text,"
			+ COLUMN_SUMCACATEGORY + " text," 
			+ COLUMN_SUMCANAME + " text,"
			+ COLUMN_SUMCATEGORYID + " integer ,FOREIGN KEY ("
			+ COLUMN_SUMCATEGORYID + ") REFERENCES " + TABLE_CATEGORY + " ("
			+ COLUMN_SUMID + "));";
	
	private static final String CATEGORY_TABLE_CREATE = "create table "
			+ TABLE_CATEGORY + "(" + COLUMN_CAID
			+ " integer primary key autoincrement, " + COLUMN_CATYPE + " text,"
			+ COLUMN_CACATEGORY + " text," + COLUMN_CANAME + " text" + ");";

	private static final String INCOME_TABLE_CREATE = "create table "
			+ TABLE_INCOME + "(" 
			+ COLUMN_INID + " integer primary key autoincrement, " 
			+ COLUMN_INVALUE + " numeric," 
			+ COLUMN_INDETAIL + " text," 
			+ COLUMN_INDATE + " datetime,"
			+ COLUMN_INCATYPE + " text,"
			+ COLUMN_INCACATEGORY + " text," 
			+ COLUMN_INCANAME + " text,"
			+ COLUMN_INCATEGORYID + " integer ,FOREIGN KEY ("
			+ COLUMN_INCATEGORYID + ") REFERENCES " + TABLE_CATEGORY + " ("
			+ COLUMN_CAID + "));";

	private static final String EXPENSE_TABLE_CREATE = "create table "
			+ TABLE_EXPENSE + "(" 
			+ COLUMN_EXID + " integer primary key autoincrement, " 
			+ COLUMN_EXVALUE + " numeric," 
			+ COLUMN_EXDETAIL + " text," 
			+ COLUMN_EXDATE + " datetime ," 
			+ COLUMN_EXCATYPE + " text,"
			+ COLUMN_EXCACATEGORY + " text,"
			+ COLUMN_EXCANAME + " text,"
			+ COLUMN_EXCATEGORYID + " integer ,FOREIGN KEY ("
			+ COLUMN_EXCATEGORYID + ") REFERENCES " + TABLE_CATEGORY + " ("
			+ COLUMN_CAID + "));";

	private static final String DATABASE_1stDATA = "insert into category VALUES ( null, 'Income', 'Income', 'Net-Income');";
	private static final String DATABASE_2ndDATA = "insert into category VALUES ( null, 'Income', 'Income', 'Other');";
	private static final String DATABASE_3rdDATA = "insert into category VALUES ( null, 'Expense', 'Necessary-Expense', 'Food');";
	private static final String DATABASE_4thDATA = "insert into category VALUES ( null, 'Expense', 'Necessary-Expense', 'Rental');";
	private static final String DATABASE_5thDATA = "insert into category VALUES ( null, 'Expense', 'Necessary-Expense', 'Utilities');";
	private static final String DATABASE_6thDATA = "insert into category VALUES ( null, 'Expense', 'Necessary-Expense', 'Clothes');";
	private static final String DATABASE_7thDATA = "insert into category VALUES ( null, 'Expense', 'Necessary-Expense', 'Medical');";
	private static final String DATABASE_8thDATA = "insert into category VALUES ( null, 'Expense', 'Necessary-Expense', 'Fare/Fuel');";
	private static final String DATABASE_9thDATA = "insert into category VALUES ( null, 'Expense', 'Unnecessary-Expense', 'Lottery');";
	private static final String DATABASE_10thDATA = "insert into category VALUES ( null, 'Expense', 'Unnecessary-Expense', 'Cigarette/Alcoho');";
	private static final String DATABASE_11thDATA = "insert into category VALUES ( null, 'Expense', 'Unnecessary-Expense', 'Debt');";
	private static final String DATABASE_12thDATA = "insert into category VALUES ( null, 'Expense', 'Unnecessary-Expense', 'Interest Rates');";
	private static final String DATABASE_13thDATA = "insert into category VALUES ( null, 'Expense', 'Other-Expense', 'Parents care');";
	private static final String DATABASE_14thDATA = "insert into category VALUES ( null, 'Expense', 'Other-Expense', 'Household');";
	private static final String DATABASE_15thDATA = "insert into category VALUES ( null, 'Expense', 'Other-Expense', 'Relax/Travel');";
	private static final String DATABASE_16thDATA = "insert into category VALUES ( null, 'Expense', 'Other-Expense', 'Merit');";
	private static final String DATABASE_17thDATA = "insert into category VALUES ( null, 'Expense', 'Other-Expense', 'Other');";

	public SQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(INCOME_TABLE_CREATE);
		db.execSQL(EXPENSE_TABLE_CREATE);
		db.execSQL(CATEGORY_TABLE_CREATE);
		db.execSQL(SUMMARY_TABLE_CREATE);
		db.execSQL(DATABASE_1stDATA);
		db.execSQL(DATABASE_2ndDATA);
		db.execSQL(DATABASE_3rdDATA);
		db.execSQL(DATABASE_4thDATA);
		db.execSQL(DATABASE_5thDATA);
		db.execSQL(DATABASE_6thDATA);
		db.execSQL(DATABASE_7thDATA);
		db.execSQL(DATABASE_8thDATA);
		db.execSQL(DATABASE_9thDATA);
		db.execSQL(DATABASE_10thDATA);
		db.execSQL(DATABASE_11thDATA);
		db.execSQL(DATABASE_12thDATA);
		db.execSQL(DATABASE_13thDATA);
		db.execSQL(DATABASE_14thDATA);
		db.execSQL(DATABASE_15thDATA);
		db.execSQL(DATABASE_16thDATA);
		db.execSQL(DATABASE_17thDATA);
		Log.d("CREATE TABLE", "Create Table Successfully.");
		// TODO Auto-generated method stub

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(SQLiteHelper.class.getName(), "Upgrading database from version"
				+ oldVersion + " to " + newVersion
				+ ", which will destroy all old data");
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE" + INCOME_TABLE_CREATE + ";");
		db.execSQL("DROP TABLE" + EXPENSE_TABLE_CREATE + ";");
		db.execSQL("DROP TABLE" + CATEGORY_TABLE_CREATE + ";");
		db.execSQL("DROP TABLE" + SUMMARY_TABLE_CREATE + ";");
		onCreate(db);
	}

////////////////////////////////////////////////////////// Spinner	
   /* public List<String> getAllcategory(){
        List<String> category = new ArrayList<String>();
 
        // Select All Query
        String selectQuery = "SELECT cacategory FROM category WHERE(catype like " + "'รายจ่าย'" + ")";
 
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
 
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
            	category.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }
 
        // closing connection
        cursor.close();
        db.close();
 
        // returning
        return category;
    } */
	
	// Select All Data
	
	/////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////	
	/////////////////////////////////////////////////////////////////////	
	/////////////////////////////////////////////////////////////////////	
	/////////////////////////////////////////////////////////////////////	
	/////////////////////////////////////////////////////////////////////	
	/////////////////////////////////////////////////////////////////////	
	/////////////////////////////////////////////////////////////////////
	
	
	//Group Month Between
	
	/* SELECT strftime('%m',`exdate`) AS 'month' ,SUM(exvalue) As `Amount ` FROM expense 
	WHERE exdate BETWEEN DATE('2012-01-01')  AND 
	date('2012-01-01','+12 month')
	GROUP BY `Month`*/

	//Group Now
	/* SELECT strftime('%m',`exdate`) AS 'month' ,SUM(exvalue) As `Amount ` FROM expense 
	WHERE strftime('%Y', `exdate`) = strftime('%Y','now')
		GROUP BY `Month`*/
	
	
	/* SELECT strftime('%m',`exdate`) AS 'month' ,SUM(exvalue) As `Amount ` FROM expense 
	WHERE strftime('%Y-%m', `exdate`) = strftime('2012-07')
		GROUP BY `Month` */
	
	/*SELECT strftime('%m',`exdate`) AS 'month' ,SUM(exvalue) , strftime('%m',`indate`) AS 'month' ,SUM(invalue) FROM expense , income 
WHERE strftime('%Y', `exdate`) = strftime('%Y',date'now') 
AND  strftime('%Y', `indate`) = strftime('%Y',date'now') 
GROUP BY `Month`*/
	
	public Cursor SelectAllData() {
		// TODO Auto-generated method stub
		
		 try {
			 SQLiteDatabase db;
			 db = this.getReadableDatabase(); // Read Data
				
			 String strSQL = "SELECT caid As _id, * FROM category WHERE(catype like " + "'Income'" + ")";
			 Cursor cursor = db.rawQuery(strSQL, null);
			 
			 return cursor;
				
		 } catch (Exception e) {
		    return null;
		 }
	}  
	
	
	public Cursor SelectAllData2() {
		// TODO Auto-generated method stub
		
		 try {
			 SQLiteDatabase db;
			 db = this.getReadableDatabase(); // Read Data
				
			 String strSQL = "SELECT caid As _id, * FROM category WHERE(catype like " + "'Expense'" + ")";
			 Cursor cursor = db.rawQuery(strSQL, null);
			 
			 return cursor;
				
		 } catch (Exception e) {
		    return null;
		 }
	}
	
///////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////
//To Day
	public long SumIncomeDayMain() {
			// TODO Auto-generated method stub
		long result = 0;

		SQLiteDatabase db;
		db = this.getReadableDatabase(); // Read Data

		String strSQL = "SELECT SUM(invalue) FROM income WHERE strftime('%Y-%m-%d', `indate`) = strftime('%Y-%m-%d','now')";
		Cursor cursor = db.rawQuery(strSQL, null);

		if (cursor.moveToFirst()){
			result = cursor.getLong(0);
		}
		cursor.close();
		return result;
	}
	public long SumExpenseDayMain() {
		// TODO Auto-generated method stub
		long result = 0;

		SQLiteDatabase db;
		db = this.getReadableDatabase(); // Read Data

		String strSQL = "SELECT SUM(exvalue) FROM expense WHERE strftime('%Y-%m-%d', `exdate`) = strftime('%Y-%m-%d','now')";
		Cursor cursor = db.rawQuery(strSQL, null);

		if (cursor.moveToFirst()){
			result = cursor.getLong(0);

		}
		cursor.close();
		return result;
		}


	public long SumExpenseNeDayMain() {
		// TODO Auto-generated method stub
			long result = 0;
			SQLiteDatabase db;
			db = this.getReadableDatabase(); // Read Data
			
			String strSQL = "SELECT SUM(exvalue) FROM expense WHERE strftime('%Y-%m-%d', `exdate`) = strftime('%Y-%m-%d','now') AND (excacategory like " + "'Necessary-Expense'" + ")";
			Cursor cursor = db.rawQuery(strSQL, null);

			if (cursor.moveToFirst()){
				result = cursor.getLong(0);

			}
			cursor.close();
			return result;
	}
	public long SumExpenseUnDayMain() {
		// TODO Auto-generated method stub
		long result = 0;
		SQLiteDatabase db;
		db = this.getReadableDatabase(); // Read Data

		String strSQL = "SELECT SUM(exvalue) FROM expense WHERE strftime('%Y-%m-%d', `exdate`) = strftime('%Y-%m-%d','now') AND (excacategory like " + "'Unnecessary-Expense'" + ")";
		Cursor cursor = db.rawQuery(strSQL, null);

		if (cursor.moveToFirst()){
			result = cursor.getLong(0);

		}
		cursor.close();
		return result;
	}

	public long SumExpenseOtDayMain() {
		// TODO Auto-generated method stub
		long result = 0;
		SQLiteDatabase db;
		db = this.getReadableDatabase(); // Read Data

		String strSQL = "SELECT SUM(exvalue) FROM expense WHERE strftime('%Y-%m-%d', `exdate`) = strftime('%Y-%m-%d','now') AND (excacategory like " + "'Other-Expense'" + ")";
		Cursor cursor = db.rawQuery(strSQL, null);

		if (cursor.moveToFirst()){
			result = cursor.getLong(0);

		}
		cursor.close();
		return result;
	}
	
	public long SumIncome() {
		// TODO Auto-generated method stub
		long result = 0;
		
			 SQLiteDatabase db;
			 db = this.getReadableDatabase(); // Read Data
				
			 String strSQL = "SELECT SUM(invalue) FROM income";
			 Cursor cursor = db.rawQuery(strSQL, null);
			 
			 if (cursor.moveToFirst()){
				 result = cursor.getLong(0);
		
		 }
			cursor.close();
			return result;
	}
	
	public long SumExpense() {
		// TODO Auto-generated method stub
		long result = 0;
		
			 SQLiteDatabase db;
			 db = this.getReadableDatabase(); // Read Data
				
			 String strSQL = "SELECT SUM(exvalue) FROM expense";
			 Cursor cursor = db.rawQuery(strSQL, null);
			 
			 if (cursor.moveToFirst()){
				 result = cursor.getLong(0);
		
		 }
			cursor.close();
			return result;
	}
	
	///////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////
	// ALL by TYPE
	public long SumExpenseNe() {
		// TODO Auto-generated method stub
		long result = 0;
		
			 SQLiteDatabase db;
			 db = this.getReadableDatabase(); // Read Data
				
			 String strSQL = "SELECT SUM(exvalue) FROM expense WHERE(excacategory like " + "'Necessary-Expense'" + ")";
			 Cursor cursor = db.rawQuery(strSQL, null);
			 
			 if (cursor.moveToFirst()){
				 result = cursor.getLong(0);
		
		 }
			cursor.close();
			return result;
	}
	
	public long SumExpenseUn() {
		// TODO Auto-generated method stub
		long result = 0;
		
			 SQLiteDatabase db;
			 db = this.getReadableDatabase(); // Read Data
				
			 String strSQL = "SELECT SUM(exvalue) FROM expense WHERE(excacategory like " + "'Unnecessary-Expense'" + ")";
			 Cursor cursor = db.rawQuery(strSQL, null);
			 
			 if (cursor.moveToFirst()){
				 result = cursor.getLong(0);
		
		 }
			cursor.close();
			return result;
	}
	
	public long SumExpenseOt() {
		// TODO Auto-generated method stub
		long result = 0;
		
			 SQLiteDatabase db;
			 db = this.getReadableDatabase(); // Read Data
				
			 String strSQL = "SELECT SUM(exvalue) FROM expense WHERE(excacategory like " + "'Other-Expense'" + ")";
			 Cursor cursor = db.rawQuery(strSQL, null);
			 
			 if (cursor.moveToFirst()){
				 result = cursor.getLong(0);
		
		 }
			cursor.close();
			return result;
	}
	
	
	///////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////
	 // THIS MONTH
	
	public long SumIncomeNow() {
		// TODO Auto-generated method stub
		long result = 0;
		String yearmonth = TipsActivity.getDate();
		
		Log.d("tag","Sqlite"+ " " + yearmonth);
		if(yearmonth == null){
			Calendar calendar = Calendar.getInstance();
			int year = calendar.get(Calendar.YEAR);
			int month = calendar.get(Calendar.MONTH);
			if(month < 10){
				int month1 = month + 1 ;
				String month2 = "0"+month1;
				yearmonth = year + "-" + month2 + "-" + "01";;
			}
			else {
				int month1 = month + 1 ;
				yearmonth = year + "-" + month1 + "-" + "01";;
			}
		}
		else {
				yearmonth = yearmonth + "-" + "01";
			}
		
			 SQLiteDatabase db;
			 db = this.getReadableDatabase(); // Read Data
				
			 String strSQL = "SELECT SUM(invalue) AS sumincome FROM income WHERE strftime('%Y-%m', `indate`) = strftime('%Y-%m','" + yearmonth + "')";
			 Cursor cursor = db.rawQuery(strSQL, null);
			 
			 if (cursor.moveToFirst()){
				 result = cursor.getLong(0);
		 }
			cursor.close();
		
			return result;
	}
	
	public long SumExpenseNow() {
		// TODO Auto-generated method stub
		long result = 0;
		
		//TipsActivity month = new TipsActivity();
		String yearmonth = TipsActivity.getDate();
		
		Log.d("tag","Sqlite"+ " " + yearmonth);
		if(yearmonth == null){
			Calendar calendar = Calendar.getInstance();
			int year = calendar.get(Calendar.YEAR);
			int month = calendar.get(Calendar.MONTH);
			if(month < 10){
				int month1 = month + 1 ;
				String month2 = "0"+month1;
				yearmonth = year + "-" + month2 + "-" + "01";;
			}
			else {
				int month1 = month + 1 ;
				yearmonth = year + "-" + month1 + "-" + "01";;
			}
		}
		else {
			yearmonth = yearmonth + "-" + "01";
		}
			 SQLiteDatabase db;
			 db = this.getReadableDatabase(); // Read Data
				
			 String strSQL = "SELECT SUM(exvalue) FROM expense WHERE strftime('%Y-%m', `exdate`) = strftime('%Y-%m','" + yearmonth + "')";
			 Cursor cursor = db.rawQuery(strSQL, null);
			 
			 if (cursor.moveToFirst()){
				 result = cursor.getLong(0);
		
		 }
			cursor.close();
			return result;
	
	}
	public long SumExpenseNethis() {
		// TODO Auto-generated method stub
	long result = 0;
		
		//TipsActivity month = new TipsActivity();
		String yearmonth = TipsActivity.getDate();
		
		Log.d("tag","Sqlite"+ " " + yearmonth);
		if(yearmonth == null){
			Calendar calendar = Calendar.getInstance();
			int year = calendar.get(Calendar.YEAR);
			int month = calendar.get(Calendar.MONTH);
			if(month < 10){
				int month1 = month + 1 ;
				String month2 = "0"+month1;
				yearmonth = year + "-" + month2 + "-" + "01";;
			}
			else {
				int month1 = month + 1 ;
				yearmonth = year + "-" + month1 + "-" + "01";;
			}
		}
		else {
			yearmonth = yearmonth + "-" + "01";
		}
		
			 SQLiteDatabase db;
			 db = this.getReadableDatabase(); // Read Data
				
			 String strSQL = "SELECT SUM(exvalue) FROM expense WHERE strftime('%Y-%m', `exdate`) = strftime('%Y-%m','" + yearmonth + "') AND (excacategory like " + "'Necessary-Expense'" + ")";
			 Cursor cursor = db.rawQuery(strSQL, null);
			 
			 if (cursor.moveToFirst()){
				 result = cursor.getLong(0);
		
		 }
			cursor.close();
			return result;
	}
	
	public long SumExpenseUnthis() {
		// TODO Auto-generated method stub
	long result = 0;
		
		//TipsActivity month = new TipsActivity();
		String yearmonth = TipsActivity.getDate();
		
		Log.d("tag","Sqlite"+ " " + yearmonth);
		if(yearmonth == null){
			Calendar calendar = Calendar.getInstance();
			int year = calendar.get(Calendar.YEAR);
			int month = calendar.get(Calendar.MONTH);
			if(month < 10){
				int month1 = month + 1 ;
				String month2 = "0"+month1;
				yearmonth = year + "-" + month2 + "-" + "01";;
			}
			else {
				int month1 = month + 1 ;
				yearmonth = year + "-" + month1 + "-" + "01";;
			}
		}
		else {
			yearmonth = yearmonth + "-" + "01";
		}
			 SQLiteDatabase db;
			 db = this.getReadableDatabase(); // Read Data
				
			 String strSQL = "SELECT SUM(exvalue) FROM expense WHERE strftime('%Y-%m', `exdate`) = strftime('%Y-%m','" + yearmonth + "') AND (excacategory like " + "'Unnecessary-Expense'" + ")";
			 Cursor cursor = db.rawQuery(strSQL, null);
			 
			 if (cursor.moveToFirst()){
				 result = cursor.getLong(0);
		
		 }
			cursor.close();
			return result;
	}
	
	public long SumExpenseOtthis() {
		// TODO Auto-generated method stub
	long result = 0;
		
		//TipsActivity month = new TipsActivity();
		String yearmonth = TipsActivity.getDate();
		
		Log.d("tag","Sqlite"+ " " + yearmonth);
		if(yearmonth == null){
			Calendar calendar = Calendar.getInstance();
			int year = calendar.get(Calendar.YEAR);
			int month = calendar.get(Calendar.MONTH);
			if(month < 10){
				int month1 = month + 1 ;
				String month2 = "0"+month1;
				yearmonth = year + "-" + month2 + "-" + "01";;
			}
			else {
				int month1 = month + 1 ;
				yearmonth = year + "-" + month1 + "-" + "01";;
			}
		}
		else {
			yearmonth = yearmonth + "-" + "01";
		}
			 SQLiteDatabase db;
			 db = this.getReadableDatabase(); // Read Data
				
			 String strSQL = "SELECT SUM(exvalue) FROM expense WHERE strftime('%Y-%m', `exdate`) = strftime('%Y-%m','" + yearmonth + "') AND (excacategory like " + "'Other-Expense'" + ")";
			 Cursor cursor = db.rawQuery(strSQL, null);
			 
			 if (cursor.moveToFirst()){
				 result = cursor.getLong(0);
		
		 }
			cursor.close();
			return result;
	}
///////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////
//To Day
	public long SumIncomePick() {
		// TODO Auto-generated method stub
				long result = 0;
				String yearmonthdate1 = TipsActivity.getPick1();
				String yearmonthdate2 = TipsActivity.getPick2();
				if(yearmonthdate1 == null || yearmonthdate2 == null){
					Calendar calendar = Calendar.getInstance();
					int year = calendar.get(Calendar.YEAR);
					int month = calendar.get(Calendar.MONTH);
					int date = calendar.get(Calendar.DATE);
					if(month < 10){
						if(date < 10){	
							int month1 = month + 1 ;
							String month2 = "0"+month1;
							String date2 = "0"+date;
							yearmonthdate1 = year + "-" + month2 + "-" + date2;
							yearmonthdate2 = year + "-" + month2 + "-" + date2;
						}
						else if(date >=10){
							int month1 = month + 1 ;
							String month2 = "0"+month1;
							yearmonthdate1 = year + "-" + month2 + "-" + date;
							yearmonthdate2 = year + "-" + month2 + "-" + date;
						}
					}
					else if (month >= 10) {
						if(date < 10){
							int month1 = month + 1 ;
							String date2 = "0"+date;
							yearmonthdate1 = year + "-" + month1 + "-" + date2;
							yearmonthdate2 = year + "-" + month1 + "-" + date2;
						}
						
						else if(date >=10){
							int month1 = month + 1 ;
							yearmonthdate1 = year + "-" + month1 + "-" + date;
							yearmonthdate2 = year + "-" + month1 + "-" + date;
					}
				}
				}
			 SQLiteDatabase db;
			 db = this.getReadableDatabase(); // Read Data
			 String strSQL = "SELECT SUM(invalue) FROM income WHERE strftime('%Y-%m-%d', `indate`) BETWEEN strftime('%Y-%m-%d','" + yearmonthdate1 + "') AND strftime('%Y-%m-%d','" + yearmonthdate2 + "')";
			 Cursor cursor = db.rawQuery(strSQL, null);
			 
			 if (cursor.moveToFirst()){
				 result = cursor.getLong(0);
				 Log.d("tag", "pick" + result);
		 }
			cursor.close();
			return result;
	}
	
	public long SumExpensePick() {
		// TODO Auto-generated method stub
		long result = 0;
		String yearmonthdate1 = TipsActivity.getPick1();
		String yearmonthdate2 = TipsActivity.getPick2();
		if(yearmonthdate1 == null || yearmonthdate2 == null){
			Calendar calendar = Calendar.getInstance();
			int year = calendar.get(Calendar.YEAR);
			int month = calendar.get(Calendar.MONTH);
			int date = calendar.get(Calendar.DATE);
			if(month < 10){
				if(date < 10){	
					int month1 = month + 1 ;
					String month2 = "0"+month1;
					String date2 = "0"+date;
					yearmonthdate1 = year + "-" + month2 + "-" + date2;
					yearmonthdate2 = year + "-" + month2 + "-" + date2;
				}
				else if(date >=10){
					int month1 = month + 1 ;
					String month2 = "0"+month1;
					yearmonthdate1 = year + "-" + month2 + "-" + date;
					yearmonthdate2 = year + "-" + month2 + "-" + date;
				}
			}
			else if (month >= 10) {
				if(date < 10){
					int month1 = month + 1 ;
					String date2 = "0"+date;
					yearmonthdate1 = year + "-" + month1 + "-" + date2;
					yearmonthdate2 = year + "-" + month1 + "-" + date2;
				}
				
				else if(date >=10){
					int month1 = month + 1 ;
					yearmonthdate1 = year + "-" + month1 + "-" + date;
					yearmonthdate2 = year + "-" + month1 + "-" + date;
			}
		}
		}
			 SQLiteDatabase db;
			 db = this.getReadableDatabase(); // Read Data
				
			 String strSQL = "SELECT SUM(exvalue) FROM expense WHERE strftime('%Y-%m-%d', `exdate`) BETWEEN strftime('%Y-%m-%d','" + yearmonthdate1 + "') AND strftime('%Y-%m-%d','" + yearmonthdate2 + "')";
			 Cursor cursor = db.rawQuery(strSQL, null);
			 
			 if (cursor.moveToFirst()){
				 result = cursor.getLong(0);
		
		 }
			cursor.close();
			return result;
	}
	
	
	public long SumExpenseNePick() {
		// TODO Auto-generated method stub
		long result = 0;
		String yearmonthdate1 = TipsActivity.getPick1();
		String yearmonthdate2 = TipsActivity.getPick2();
		if(yearmonthdate1 == null || yearmonthdate2 == null){
			Calendar calendar = Calendar.getInstance();
			int year = calendar.get(Calendar.YEAR);
			int month = calendar.get(Calendar.MONTH);
			int date = calendar.get(Calendar.DATE);
			if(month < 10){
				if(date < 10){	
					int month1 = month + 1 ;
					String month2 = "0"+month1;
					String date2 = "0"+date;
					yearmonthdate1 = year + "-" + month2 + "-" + date2;
					yearmonthdate2 = year + "-" + month2 + "-" + date2;
				}
				else if(date >=10){
					int month1 = month + 1 ;
					String month2 = "0"+month1;
					yearmonthdate1 = year + "-" + month2 + "-" + date;
					yearmonthdate2 = year + "-" + month2 + "-" + date;
				}
			}
			else if (month >= 10) {
				if(date < 10){
					int month1 = month + 1 ;
					String date2 = "0"+date;
					yearmonthdate1 = year + "-" + month1 + "-" + date2;
					yearmonthdate2 = year + "-" + month1 + "-" + date2;
				}
				
				else if(date >=10){
					int month1 = month + 1 ;
					yearmonthdate1 = year + "-" + month1 + "-" + date;
					yearmonthdate2 = year + "-" + month1 + "-" + date;
			}
		}
		}
			 SQLiteDatabase db;
			 db = this.getReadableDatabase(); // Read Data
				
			 String strSQL = "SELECT SUM(exvalue) FROM expense WHERE strftime('%Y-%m-%d', `exdate`) BETWEEN strftime('%Y-%m-%d','" + yearmonthdate1 + "') AND strftime('%Y-%m-%d','" + yearmonthdate2 + "') AND (excacategory like " + "'Necessary-Expense'" + ")";
			 Cursor cursor = db.rawQuery(strSQL, null);
			 
			 if (cursor.moveToFirst()){
				 result = cursor.getLong(0);
		
		 }
			cursor.close();
			return result;
	}
	
	public long SumExpenseUnPick() {
		// TODO Auto-generated method stub
		long result = 0;
		String yearmonthdate1 = TipsActivity.getPick1();
		String yearmonthdate2 = TipsActivity.getPick2();
		if(yearmonthdate1 == null || yearmonthdate2 == null){
			Calendar calendar = Calendar.getInstance();
			int year = calendar.get(Calendar.YEAR);
			int month = calendar.get(Calendar.MONTH);
			int date = calendar.get(Calendar.DATE);
			if(month < 10){
				if(date < 10){	
					int month1 = month + 1 ;
					String month2 = "0"+month1;
					String date2 = "0"+date;
					yearmonthdate1 = year + "-" + month2 + "-" + date2;
					yearmonthdate2 = year + "-" + month2 + "-" + date2;
				}
				else if(date >=10){
					int month1 = month + 1 ;
					String month2 = "0"+month1;
					yearmonthdate1 = year + "-" + month2 + "-" + date;
					yearmonthdate2 = year + "-" + month2 + "-" + date;
				}
			}
			else if (month >= 10) {
				if(date < 10){
					int month1 = month + 1 ;
					String date2 = "0"+date;
					yearmonthdate1 = year + "-" + month1 + "-" + date2;
					yearmonthdate2 = year + "-" + month1 + "-" + date2;
				}
				
				else if(date >=10){
					int month1 = month + 1 ;
					yearmonthdate1 = year + "-" + month1 + "-" + date;
					yearmonthdate2 = year + "-" + month1 + "-" + date;
			}
		}
		}
			 SQLiteDatabase db;
			 db = this.getReadableDatabase(); // Read Data
				
			 String strSQL = "SELECT SUM(exvalue) FROM expense WHERE strftime('%Y-%m-%d', `exdate`) BETWEEN strftime('%Y-%m-%d','" + yearmonthdate1 + "') AND strftime('%Y-%m-%d','" + yearmonthdate2 + "') AND (excacategory like " + "'Unnecessary-Expense'" + ")";
			 Cursor cursor = db.rawQuery(strSQL, null);
			 
			 if (cursor.moveToFirst()){
				 result = cursor.getLong(0);
		
		 }
			cursor.close();
			return result;
	}
	
	public long SumExpenseOtPick() {
		// TODO Auto-generated method stub
		long result = 0;
		String yearmonthdate1 = TipsActivity.getPick1();
		String yearmonthdate2 = TipsActivity.getPick2();
		if(yearmonthdate1 == null || yearmonthdate2 == null){
			Calendar calendar = Calendar.getInstance();
			int year = calendar.get(Calendar.YEAR);
			int month = calendar.get(Calendar.MONTH);
			int date = calendar.get(Calendar.DATE);
			if(month < 10){
				if(date < 10){	
					int month1 = month + 1 ;
					String month2 = "0"+month1;
					String date2 = "0"+date;
					yearmonthdate1 = year + "-" + month2 + "-" + date2;
					yearmonthdate2 = year + "-" + month2 + "-" + date2;
				}
				else if(date >=10){
					int month1 = month + 1 ;
					String month2 = "0"+month1;
					yearmonthdate1 = year + "-" + month2 + "-" + date;
					yearmonthdate2 = year + "-" + month2 + "-" + date;
				}
			}
			else if (month >= 10) {
				if(date < 10){
					int month1 = month + 1 ;
					String date2 = "0"+date;
					yearmonthdate1 = year + "-" + month1 + "-" + date2;
					yearmonthdate2 = year + "-" + month1 + "-" + date2;
				}
				
				else if(date >=10){
					int month1 = month + 1 ;
					yearmonthdate1 = year + "-" + month1 + "-" + date;
					yearmonthdate2 = year + "-" + month1 + "-" + date;
			}
		}
		}
			 SQLiteDatabase db;
			 db = this.getReadableDatabase(); // Read Data
				
			 String strSQL = "SELECT SUM(exvalue) FROM expense WHERE strftime('%Y-%m-%d', `exdate`) BETWEEN strftime('%Y-%m-%d','" + yearmonthdate1 + "') AND strftime('%Y-%m-%d','" + yearmonthdate2 + "') AND (excacategory like " + "'Other-Expense'" + ")";
			 Cursor cursor = db.rawQuery(strSQL, null);
			 
			 if (cursor.moveToFirst()){
				 result = cursor.getLong(0);
		
		 }
			cursor.close();
			return result;
	}
	
///////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////
// To Day
	public long SumIncomeDay() {
		// TODO Auto-generated method stub
		long result = 0;
		String yearmonthdate = TipsActivity.getDay();
		if(yearmonthdate == null){
			Calendar calendar = Calendar.getInstance();
			int year = calendar.get(Calendar.YEAR);
			int month = calendar.get(Calendar.MONTH);
			int date = calendar.get(Calendar.DATE);
			if(month < 10){
				if(date < 10){	
					int month1 = month + 1 ;
					String month2 = "0"+month1;
					String date2 = "0"+date;
					yearmonthdate = year + "-" + month2 + "-" + date2;
				}
				else if(date >=10){
					int month1 = month + 1 ;
					String month2 = "0"+month1;
					yearmonthdate = year + "-" + month2 + "-" + date;
				}
			}
			else if (month >= 10) {
				if(date < 10){
					int month1 = month + 1 ;
					String date2 = "0"+date;
					yearmonthdate = year + "-" + month1 + "-" + date2;
				}
				
				else if(date >=10){
					int month1 = month + 1 ;
					yearmonthdate = year + "-" + month1 + "-" + date;
			}
		}
		}
			 SQLiteDatabase db;
			 db = this.getReadableDatabase(); // Read Data
				
			 String strSQL = "SELECT SUM(invalue) FROM income WHERE strftime('%Y-%m-%d', `indate`) = strftime('%Y-%m-%d','" + yearmonthdate + "')";
			 Cursor cursor = db.rawQuery(strSQL, null);
			 
			 if (cursor.moveToFirst()){
				 result = cursor.getLong(0);
		 }
			cursor.close();
			return result;
	}
	public long SumExpenseDay() {
		// TODO Auto-generated method stub
				long result = 0;
				String yearmonthdate = TipsActivity.getDay();
				if(yearmonthdate == null){
					Calendar calendar = Calendar.getInstance();
					int year = calendar.get(Calendar.YEAR);
					int month = calendar.get(Calendar.MONTH);
					int date = calendar.get(Calendar.DATE);
					if(month < 10){
						if(date < 10){	
							int month1 = month + 1 ;
							String month2 = "0"+month1;
							String date2 = "0"+date;
							yearmonthdate = year + "-" + month2 + "-" + date2;
						}
						else if(date >=10){
							int month1 = month + 1 ;
							String month2 = "0"+month1;
							yearmonthdate = year + "-" + month2 + "-" + date;
						}
					}
					else if (month >= 10) {
						if(date < 10){
							int month1 = month + 1 ;
							String date2 = "0"+date;
							yearmonthdate = year + "-" + month1 + "-" + date2;
						}
						
						else if(date >=10){
							int month1 = month + 1 ;
							yearmonthdate = year + "-" + month1 + "-" + date;
					}
				}
				}
			 SQLiteDatabase db;
			 db = this.getReadableDatabase(); // Read Data
				
			 String strSQL = "SELECT SUM(exvalue) FROM expense WHERE strftime('%Y-%m-%d', `exdate`) = strftime('%Y-%m-%d','" + yearmonthdate + "')";
			 Cursor cursor = db.rawQuery(strSQL, null);
			 
			 if (cursor.moveToFirst()){
				 result = cursor.getLong(0);
		
		 }
			cursor.close();
			return result;
	}
	
	
	public long SumExpenseNeDay() {
		// TODO Auto-generated method stub
				long result = 0;
				String yearmonthdate = TipsActivity.getDay();
				if(yearmonthdate == null){
					Calendar calendar = Calendar.getInstance();
					int year = calendar.get(Calendar.YEAR);
					int month = calendar.get(Calendar.MONTH);
					int date = calendar.get(Calendar.DATE);
					if(month < 10){
						if(date < 10){	
							int month1 = month + 1 ;
							String month2 = "0"+month1;
							String date2 = "0"+date;
							yearmonthdate = year + "-" + month2 + "-" + date2;
						}
						else if(date >=10){
							int month1 = month + 1 ;
							String month2 = "0"+month1;
							yearmonthdate = year + "-" + month2 + "-" + date;
						}
					}
					else if (month >= 10) {
						if(date < 10){
							int month1 = month + 1 ;
							String date2 = "0"+date;
							yearmonthdate = year + "-" + month1 + "-" + date2;
						}
						
						else if(date >=10){
							int month1 = month + 1 ;
							yearmonthdate = year + "-" + month1 + "-" + date;
					}
				}
				}
			 SQLiteDatabase db;
			 db = this.getReadableDatabase(); // Read Data
				
			 String strSQL = "SELECT SUM(exvalue) FROM expense WHERE strftime('%Y-%m-%d', `exdate`) = strftime('%Y-%m-%d','" + yearmonthdate + "') AND (excacategory like " + "'Necessary-Expense'" + ")";
			 Cursor cursor = db.rawQuery(strSQL, null);
			 
			 if (cursor.moveToFirst()){
				 result = cursor.getLong(0);
		
		 }
			cursor.close();
			return result;
	}
	public long SumExpenseUnDay() {
		// TODO Auto-generated method stub
				long result = 0;
				String yearmonthdate = TipsActivity.getDay();
				if(yearmonthdate == null){
					Calendar calendar = Calendar.getInstance();
					int year = calendar.get(Calendar.YEAR);
					int month = calendar.get(Calendar.MONTH);
					int date = calendar.get(Calendar.DATE);
					if(month < 10){
						if(date < 10){	
							int month1 = month + 1 ;
							String month2 = "0"+month1;
							String date2 = "0"+date;
							yearmonthdate = year + "-" + month2 + "-" + date2;
						}
						else if(date >=10){
							int month1 = month + 1 ;
							String month2 = "0"+month1;
							yearmonthdate = year + "-" + month2 + "-" + date;
						}
					}
					else if (month >= 10) {
						if(date < 10){
							int month1 = month + 1 ;
							String date2 = "0"+date;
							yearmonthdate = year + "-" + month1 + "-" + date2;
						}
						
						else if(date >=10){
							int month1 = month + 1 ;
							yearmonthdate = year + "-" + month1 + "-" + date;
					}
				}
				}
			 SQLiteDatabase db;
			 db = this.getReadableDatabase(); // Read Data
				
			 String strSQL = "SELECT SUM(exvalue) FROM expense WHERE strftime('%Y-%m-%d', `exdate`) = strftime('%Y-%m-%d','" + yearmonthdate + "') AND (excacategory like " + "'Unnecessary-Expense'" + ")";
			 Cursor cursor = db.rawQuery(strSQL, null);
			 
			 if (cursor.moveToFirst()){
				 result = cursor.getLong(0);
		
		 }
			cursor.close();
			return result;
	}
	
	public long SumExpenseOtDay() {
		// TODO Auto-generated method stub
				long result = 0;
				String yearmonthdate = TipsActivity.getDay();
				if(yearmonthdate == null){
					Calendar calendar = Calendar.getInstance();
					int year = calendar.get(Calendar.YEAR);
					int month = calendar.get(Calendar.MONTH);
					int date = calendar.get(Calendar.DATE);
					if(month < 10){
						if(date < 10){	
							int month1 = month + 1 ;
							String month2 = "0"+month1;
							String date2 = "0"+date;
							yearmonthdate = year + "-" + month2 + "-" + date2;
						}
						else if(date >=10){
							int month1 = month + 1 ;
							String month2 = "0"+month1;
							yearmonthdate = year + "-" + month2 + "-" + date;
						}
					}
					else if (month >= 10) {
						if(date < 10){
							int month1 = month + 1 ;
							String date2 = "0"+date;
							yearmonthdate = year + "-" + month1 + "-" + date2;
						}
						
						else if(date >=10){
							int month1 = month + 1 ;
							yearmonthdate = year + "-" + month1 + "-" + date;
					}
				}
				}
			 SQLiteDatabase db;
			 db = this.getReadableDatabase(); // Read Data
				
			 String strSQL = "SELECT SUM(exvalue) FROM expense WHERE strftime('%Y-%m-%d', `exdate`) = strftime('%Y-%m-%d','" + yearmonthdate + "') AND (excacategory like " + "'Other-Expense'" + ")";
			 Cursor cursor = db.rawQuery(strSQL, null);
			 
			 if (cursor.moveToFirst()){
				 result = cursor.getLong(0);
		
		 }
			cursor.close();
			return result;
	}
	
///////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////
//This Year
	
	
	public long SumIncomeYear() {
		// TODO Auto-generated method stub
		long result = 0;
		String yearmonthyear = TipsActivity.getYear();
		if(yearmonthyear == null){
			Calendar calendar = Calendar.getInstance();
			int year = calendar.get(Calendar.YEAR);
				yearmonthyear = year + "-" + "01" + "-" + "01";
		}
		Log.d("tag", "Sqlite year" + " " + yearmonthyear);
			 SQLiteDatabase db;
			 db = this.getReadableDatabase(); // Read Data
				
			 String strSQL = "SELECT SUM(invalue) AS sumincome FROM income WHERE strftime('%Y', `indate`) = strftime('%Y','"+ yearmonthyear +"')";
			 Cursor cursor = db.rawQuery(strSQL, null);
			 
			 if (cursor.moveToFirst()){
				 result = cursor.getLong(0);
		 }
			cursor.close();
		
			return result;
	}
	public long SumExpenseYear() {
		// TODO Auto-generated method stub
		long result = 0;
		String yearmonthyear = TipsActivity.getYear();
		if(yearmonthyear == null){
			Calendar calendar = Calendar.getInstance();
			int year = calendar.get(Calendar.YEAR);
				yearmonthyear = year + "-" + "01" + "-" + "01";
		}
			 SQLiteDatabase db;
			 db = this.getReadableDatabase(); // Read Data
				
			 String strSQL = "SELECT SUM(exvalue) FROM expense WHERE strftime('%Y', `exdate`) = strftime('%Y','" + yearmonthyear + "')";
			 Cursor cursor = db.rawQuery(strSQL, null);
			 
			 if (cursor.moveToFirst()){
				 result = cursor.getLong(0);
		
		 }
			cursor.close();
			return result;
	}
	
	
	public long SumExpenseNeYear() {
		// TODO Auto-generated method stub
		long result = 0;
		String yearmonthyear = TipsActivity.getYear();
		if(yearmonthyear == null){
			Calendar calendar = Calendar.getInstance();
			int year = calendar.get(Calendar.YEAR);
				yearmonthyear = year + "-" + "01" + "-" + "01";
		}
			 SQLiteDatabase db;
			 db = this.getReadableDatabase(); // Read Data
				
			 String strSQL = "SELECT SUM(exvalue) FROM expense WHERE strftime('%Y', `exdate`) = strftime('%Y','" + yearmonthyear + "') AND (excacategory like " + "'Necessary-Expense'" + ")";
			 Cursor cursor = db.rawQuery(strSQL, null);
			 
			 if (cursor.moveToFirst()){
				 result = cursor.getLong(0);
		
		 }
			cursor.close();
			return result;
	}
	public long SumExpenseUnYear() {
		// TODO Auto-generated method stub
		long result = 0;
		String yearmonthyear = TipsActivity.getYear();
		if(yearmonthyear == null){
			Calendar calendar = Calendar.getInstance();
			int year = calendar.get(Calendar.YEAR);
				yearmonthyear = year + "-" + "01" + "-" + "01";
		}
			 SQLiteDatabase db;
			 db = this.getReadableDatabase(); // Read Data
				
			 String strSQL = "SELECT SUM(exvalue) FROM expense WHERE strftime('%Y', `exdate`) = strftime('%Y','" + yearmonthyear + "') AND (excacategory like " + "'Unnecessary-Expense'" + ")";
			 Cursor cursor = db.rawQuery(strSQL, null);
			 
			 if (cursor.moveToFirst()){
				 result = cursor.getLong(0);
		
		 }
			cursor.close();
			return result;
	}
	
	public long SumExpenseOtYear() {
		// TODO Auto-generated method stub
		long result = 0;
		String yearmonthyear = TipsActivity.getYear();
		if(yearmonthyear == null){
			Calendar calendar = Calendar.getInstance();
			int year = calendar.get(Calendar.YEAR);
				yearmonthyear = year + "-" + "01" + "-" + "01";
		}
			 SQLiteDatabase db;
			 db = this.getReadableDatabase(); // Read Data
				
			 String strSQL = "SELECT SUM(exvalue) FROM expense WHERE strftime('%Y', `exdate`) = strftime('%Y','" + yearmonthyear + "') AND (excacategory like " + "'Other-Expense'" + ")";
			 Cursor cursor = db.rawQuery(strSQL, null);
			 
			 if (cursor.moveToFirst()){
				 result = cursor.getLong(0);
		
		 }
			cursor.close();
			return result;
	}
	
	
	
	
	
	/////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////
	//Line Chart
	public long SumExpense01() {
		// TODO Auto-generated method stub
		long result = 0;
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
			String yearmonth = year + "-" + "01" + "-" + "01";
			 SQLiteDatabase db;
			 db = this.getReadableDatabase(); // Read Data
			 String strSQL = "SELECT SUM(exvalue) As 'exp' FROM expense WHERE strftime('%Y-%m', `exdate`) = strftime('%Y-%m','" + yearmonth + "')";
			 Cursor cursor = db.rawQuery(strSQL, null);
			 Log.d("tag", "e" +" "+ cursor);
			 if (cursor.moveToFirst()){
				 result = cursor.getLong(0);
		
		 }
			cursor.close();
			return result;
	}
	public long SumExpense02() {
		// TODO Auto-generated method stub
		long result = 0;
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
			String yearmonth = year + "-" + "02" + "-" + "01";
			 SQLiteDatabase db;
			 db = this.getReadableDatabase(); // Read Data
			 String strSQL = "SELECT SUM(exvalue) As 'exp' FROM expense WHERE strftime('%Y-%m', `exdate`) = strftime('%Y-%m','" + yearmonth + "')";
			 Cursor cursor = db.rawQuery(strSQL, null);
			 Log.d("tag", "e" +" "+ cursor);
			 if (cursor.moveToFirst()){
				 result = cursor.getLong(0);
		
		 }
			cursor.close();
			return result;
	}
	public long SumExpense03() {
		// TODO Auto-generated method stub
		long result = 0;
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
			String yearmonth = year + "-" + "03" + "-" + "01";
			 SQLiteDatabase db;
			 db = this.getReadableDatabase(); // Read Data
			 String strSQL = "SELECT SUM(exvalue) As 'exp' FROM expense WHERE strftime('%Y-%m', `exdate`) = strftime('%Y-%m','" + yearmonth + "')";
			 Cursor cursor = db.rawQuery(strSQL, null);
			 Log.d("tag", "e" +" "+ cursor);
			 if (cursor.moveToFirst()){
				 result = cursor.getLong(0);
		
		 }
			cursor.close();
			return result;
	}
	public long SumExpense04() {
		// TODO Auto-generated method stub
		long result = 0;
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
			String yearmonth = year + "-" + "04" + "-" + "01";
			 SQLiteDatabase db;
			 db = this.getReadableDatabase(); // Read Data
			 String strSQL = "SELECT SUM(exvalue) As 'exp' FROM expense WHERE strftime('%Y-%m', `exdate`) = strftime('%Y-%m','" + yearmonth + "')";
			 Cursor cursor = db.rawQuery(strSQL, null);
			 Log.d("tag", "e" +" "+ cursor);
			 if (cursor.moveToFirst()){
				 result = cursor.getLong(0);
		
		 }
			cursor.close();
			return result;
	}
	public long SumExpense05() {
		// TODO Auto-generated method stub
		long result = 0;
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
			String yearmonth = year + "-" + "05" + "-" + "01";
			 SQLiteDatabase db;
			 db = this.getReadableDatabase(); // Read Data
			 String strSQL = "SELECT SUM(exvalue) As 'exp' FROM expense WHERE strftime('%Y-%m', `exdate`) = strftime('%Y-%m','" + yearmonth + "')";
			 Cursor cursor = db.rawQuery(strSQL, null);
			 Log.d("tag", "e" +" "+ cursor);
			 if (cursor.moveToFirst()){
				 result = cursor.getLong(0);
		
		 }
			cursor.close();
			return result;
	}
	public long SumExpense06() {
		// TODO Auto-generated method stub
		long result = 0;
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
			String yearmonth = year + "-" + "06" + "-" + "01";
			 SQLiteDatabase db;
			 db = this.getReadableDatabase(); // Read Data
			 String strSQL = "SELECT SUM(exvalue) As 'exp' FROM expense WHERE strftime('%Y-%m', `exdate`) = strftime('%Y-%m','" + yearmonth + "')";
			 Cursor cursor = db.rawQuery(strSQL, null);
			 Log.d("tag", "e" +" "+ cursor);
			 if (cursor.moveToFirst()){
				 result = cursor.getLong(0);
		
		 }
			cursor.close();
			return result;
	}
	public long SumExpense07() {
		// TODO Auto-generated method stub
		long result = 0;
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
			String yearmonth = year + "-" + "07" + "-" + "01";
			 SQLiteDatabase db;
			 db = this.getReadableDatabase(); // Read Data
			 String strSQL = "SELECT SUM(exvalue) As 'exp' FROM expense WHERE strftime('%Y-%m', `exdate`) = strftime('%Y-%m','" + yearmonth + "')";
			 Cursor cursor = db.rawQuery(strSQL, null);
			 Log.d("tag", "e" +" "+ cursor);
			 if (cursor.moveToFirst()){
				 result = cursor.getLong(0);
		
		 }
			cursor.close();
			return result;
	}
	public long SumExpense08() {
		// TODO Auto-generated method stub
		long result = 0;
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
			String yearmonth = year + "-" + "08" + "-" + "01";
			 SQLiteDatabase db;
			 db = this.getReadableDatabase(); // Read Data
			 String strSQL = "SELECT SUM(exvalue) As 'exp' FROM expense WHERE strftime('%Y-%m', `exdate`) = strftime('%Y-%m','" + yearmonth + "')";
			 Cursor cursor = db.rawQuery(strSQL, null);
			 Log.d("tag", "e" +" "+ cursor);
			 if (cursor.moveToFirst()){
				 result = cursor.getLong(0);
		
		 }
			cursor.close();
			return result;
	}
	public long SumExpense09() {
		// TODO Auto-generated method stub
		long result = 0;
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
			String yearmonth = year + "-" + "09" + "-" + "01";
			 SQLiteDatabase db;
			 db = this.getReadableDatabase(); // Read Data
			 String strSQL = "SELECT SUM(exvalue) As 'exp' FROM expense WHERE strftime('%Y-%m', `exdate`) = strftime('%Y-%m','" + yearmonth + "')";
			 Cursor cursor = db.rawQuery(strSQL, null);
			 Log.d("tag", "e" +" "+ cursor);
			 if (cursor.moveToFirst()){
				 result = cursor.getLong(0);
		
		 }
			cursor.close();
			return result;
	}
	public long SumExpense10() {
		// TODO Auto-generated method stub
		long result = 0;
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
			String yearmonth = year + "-" + "10" + "-" + "01";
			 SQLiteDatabase db;
			 db = this.getReadableDatabase(); // Read Data
			 String strSQL = "SELECT SUM(exvalue) As 'exp' FROM expense WHERE strftime('%Y-%m', `exdate`) = strftime('%Y-%m','" + yearmonth + "')";
			 Cursor cursor = db.rawQuery(strSQL, null);
			 Log.d("tag", "e" +" "+ cursor);
			 if (cursor.moveToFirst()){
				 result = cursor.getLong(0);
		
		 }
			cursor.close();
			return result;
	}
	public long SumExpense11() {
		// TODO Auto-generated method stub
		long result = 0;
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
			String yearmonth = year + "-" + "11" + "-" + "01";
			 SQLiteDatabase db;
			 db = this.getReadableDatabase(); // Read Data
			 String strSQL = "SELECT SUM(exvalue) As 'exp' FROM expense WHERE strftime('%Y-%m', `exdate`) = strftime('%Y-%m','" + yearmonth + "')";
			 Cursor cursor = db.rawQuery(strSQL, null);
			 Log.d("tag", "e" +" "+ cursor);
			 if (cursor.moveToFirst()){
				 result = cursor.getLong(0);
		
		 }
			cursor.close();
			return result;
	}
	public long SumExpense12() {
		// TODO Auto-generated method stub
		long result = 0;
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
			String yearmonth = year + "-" + "12" + "-" + "01";
			 SQLiteDatabase db;
			 db = this.getReadableDatabase(); // Read Data
			 String strSQL = "SELECT SUM(exvalue) As 'exp' FROM expense WHERE strftime('%Y-%m', `exdate`) = strftime('%Y-%m','" + yearmonth + "')";
			 Cursor cursor = db.rawQuery(strSQL, null);
			 Log.d("tag", "e" +" "+ cursor);
			 if (cursor.moveToFirst()){
				 result = cursor.getLong(0);
		
		 }
			cursor.close();
			return result;
	}

	public long SumIncome01() {
		// TODO Auto-generated method stub
		long result = 0;
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
			String yearmonth = year + "-" + "01" + "-" + "01";
			 SQLiteDatabase db;
			 db = this.getReadableDatabase(); // Read Data
				
			 String strSQL = "SELECT SUM(invalue) As `inc ` FROM income WHERE strftime('%Y-%m', `indate`) = strftime('%Y-%m','" + yearmonth + "')";
			 Cursor cursor = db.rawQuery(strSQL, null);
			 Log.d("tag", "income" +" "+ cursor);
			 
			 if (cursor.moveToFirst()){
				 result = cursor.getLong(0);
		
		 }
			cursor.close();
			return result;
	}
	
	public long SumIncome02() {
		// TODO Auto-generated method stub
		long result = 0;
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
			String yearmonth = year + "-" + "02" + "-" + "01";
			 SQLiteDatabase db;
			 db = this.getReadableDatabase(); // Read Data
				
			 String strSQL = "SELECT SUM(invalue) As `inc ` FROM income WHERE strftime('%Y-%m', `indate`) = strftime('%Y-%m','" + yearmonth + "')";
			 Cursor cursor = db.rawQuery(strSQL, null);
			 Log.d("tag", "income" +" "+ cursor);
			 
			 if (cursor.moveToFirst()){
				 result = cursor.getLong(0);
		
		 }
			cursor.close();
			return result;
	}
	public long SumIncome03() {
		// TODO Auto-generated method stub
		long result = 0;
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
			String yearmonth = year + "-" + "03" + "-" + "01";
			 SQLiteDatabase db;
			 db = this.getReadableDatabase(); // Read Data
				
			 String strSQL = "SELECT SUM(invalue) As `inc ` FROM income WHERE strftime('%Y-%m', `indate`) = strftime('%Y-%m','" + yearmonth + "')";
			 Cursor cursor = db.rawQuery(strSQL, null);
			 Log.d("tag", "income" +" "+ cursor);
			 
			 if (cursor.moveToFirst()){
				 result = cursor.getLong(0);
		
		 }
			cursor.close();
			return result;
	}
	public long SumIncome04() {
		// TODO Auto-generated method stub
		long result = 0;
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
			String yearmonth = year + "-" + "04" + "-" + "01";
			 SQLiteDatabase db;
			 db = this.getReadableDatabase(); // Read Data
				
			 String strSQL = "SELECT SUM(invalue) As `inc ` FROM income WHERE strftime('%Y-%m', `indate`) = strftime('%Y-%m','" + yearmonth + "')";
			 Cursor cursor = db.rawQuery(strSQL, null);
			 Log.d("tag", "income" +" "+ cursor);
			 
			 if (cursor.moveToFirst()){
				 result = cursor.getLong(0);
		
		 }
			cursor.close();
			return result;
	}
	public long SumIncome05() {
		// TODO Auto-generated method stub
		long result = 0;
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
			String yearmonth = year + "-" + "05" + "-" + "01";
			 SQLiteDatabase db;
			 db = this.getReadableDatabase(); // Read Data
				
			 String strSQL = "SELECT SUM(invalue) As `inc ` FROM income WHERE strftime('%Y-%m', `indate`) = strftime('%Y-%m','" + yearmonth + "')";
			 Cursor cursor = db.rawQuery(strSQL, null);
			 Log.d("tag", "income" +" "+ cursor);
			 
			 if (cursor.moveToFirst()){
				 result = cursor.getLong(0);
		
		 }
			cursor.close();
			return result;
	}
	public long SumIncome06() {
		// TODO Auto-generated method stub
		long result = 0;
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
			String yearmonth = year + "-" + "06" + "-" + "01";
			 SQLiteDatabase db;
			 db = this.getReadableDatabase(); // Read Data
				
			 String strSQL = "SELECT SUM(invalue) As `inc ` FROM income WHERE strftime('%Y-%m', `indate`) = strftime('%Y-%m','" + yearmonth + "')";
			 Cursor cursor = db.rawQuery(strSQL, null);
			 Log.d("tag", "income" +" "+ cursor);
			 
			 if (cursor.moveToFirst()){
				 result = cursor.getLong(0);
		
		 }
			cursor.close();
			return result;
	}
	public long SumIncome07() {
		// TODO Auto-generated method stub
		long result = 0;
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
			String yearmonth = year + "-" + "07" + "-" + "01";
			 SQLiteDatabase db;
			 db = this.getReadableDatabase(); // Read Data
				
			 String strSQL = "SELECT SUM(invalue) As `inc ` FROM income WHERE strftime('%Y-%m', `indate`) = strftime('%Y-%m','" + yearmonth + "')";
			 Cursor cursor = db.rawQuery(strSQL, null);
			 Log.d("tag", "income" +" "+ cursor);
			 
			 if (cursor.moveToFirst()){
				 result = cursor.getLong(0);
		
		 }
			cursor.close();
			return result;
	}
	public long SumIncome08() {
		// TODO Auto-generated method stub
		long result = 0;
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
			String yearmonth = year + "-" + "08" + "-" + "01";
			 SQLiteDatabase db;
			 db = this.getReadableDatabase(); // Read Data
				
			 String strSQL = "SELECT SUM(invalue) As `inc ` FROM income WHERE strftime('%Y-%m', `indate`) = strftime('%Y-%m','" + yearmonth + "')";
			 Cursor cursor = db.rawQuery(strSQL, null);
			 Log.d("tag", "income" +" "+ cursor);
			 
			 if (cursor.moveToFirst()){
				 result = cursor.getLong(0);
		
		 }
			cursor.close();
			return result;
	}
	public long SumIncome09() {
		// TODO Auto-generated method stub
		long result = 0;
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
			String yearmonth = year + "-" + "09" + "-" + "01";
			 SQLiteDatabase db;
			 db = this.getReadableDatabase(); // Read Data
				
			 String strSQL = "SELECT SUM(invalue) As `inc ` FROM income WHERE strftime('%Y-%m', `indate`) = strftime('%Y-%m','" + yearmonth + "')";
			 Cursor cursor = db.rawQuery(strSQL, null);
			 Log.d("tag", "income" +" "+ cursor);
			 
			 if (cursor.moveToFirst()){
				 result = cursor.getLong(0);
		
		 }
			cursor.close();
			return result;
	}
	public long SumIncome10() {
		// TODO Auto-generated method stub
		long result = 0;
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
			String yearmonth = year + "-" + "10" + "-" + "01";
			 SQLiteDatabase db;
			 db = this.getReadableDatabase(); // Read Data
				
			 String strSQL = "SELECT SUM(invalue) As `inc ` FROM income WHERE strftime('%Y-%m', `indate`) = strftime('%Y-%m','" + yearmonth + "')";
			 Cursor cursor = db.rawQuery(strSQL, null);
			 Log.d("tag", "income" +" "+ cursor);
			 
			 if (cursor.moveToFirst()){
				 result = cursor.getLong(0);
		
		 }
			cursor.close();
			return result;
	}
	public long SumIncome11() {
		// TODO Auto-generated method stub
		long result = 0;
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
			String yearmonth = year + "-" + "11" + "-" + "01";
			 SQLiteDatabase db;
			 db = this.getReadableDatabase(); // Read Data
				
			 String strSQL = "SELECT SUM(invalue) As `inc ` FROM income WHERE strftime('%Y-%m', `indate`) = strftime('%Y-%m','" + yearmonth + "')";
			 Cursor cursor = db.rawQuery(strSQL, null);
			 Log.d("tag", "income" +" "+ cursor);
			 
			 if (cursor.moveToFirst()){
				 result = cursor.getLong(0);
		
		 }
			cursor.close();
			return result;
	}
	public long SumIncome12() {
		// TODO Auto-generated method stub
		long result = 0;
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
			String yearmonth = year + "-" + "12" + "-" + "01";
			 SQLiteDatabase db;
			 db = this.getReadableDatabase(); // Read Data
				
			 String strSQL = "SELECT SUM(invalue) As `inc ` FROM income WHERE strftime('%Y-%m', `indate`) = strftime('%Y-%m','" + yearmonth + "')";
			 Cursor cursor = db.rawQuery(strSQL, null);
			 Log.d("tag", "income" +" "+ cursor);
			 
			 if (cursor.moveToFirst()){
				 result = cursor.getLong(0);
		
		 }
			cursor.close();
			return result;
	}
	
	
	// Show Top list Data
	public ArrayList<HashMap<String, String>> Selecttopex() {
		// TODO Auto-generated method stub
		
		 try {
			 
			 ArrayList<HashMap<String, String>> MyArrList = new ArrayList<HashMap<String, String>>();
			 HashMap<String, String> map;
			 
			 SQLiteDatabase db;
			 db = this.getReadableDatabase(); // Read Data
				
			 String strSQL = "SELECT excaname , Sum(exvalue) FROM expense Group by excaname Order by Sum(exvalue) Desc";
			 Cursor cursor = db.rawQuery(strSQL, null);
			 
			 	if(cursor != null)
			 	{
			 	    if (cursor.moveToFirst()) {
			 	        do {
			 	        	map = new HashMap<String, String>();
			 	        	
			 	        //	Log.d("tag","msg" + cursor.getString(1));
			 	        	
			 	        	long sum = Long.valueOf(cursor.getString(1));
			 	      		NumberFormat formatter = new DecimalFormat("###,###,###,###,###,###");
			 	      		String sum1 = formatter.format(sum);
				 	       
			 	      	//	Log.d("toplist","toplist" + " " + sum1);
			 	      		
			 	      		map.put("excaname", cursor.getString(0));
				 	        map.put("exvalue", sum1);
				 	        MyArrList.add(map);
			 	        } while (cursor.moveToNext());
			 	    }
			 	}
			 	cursor.close();
			 	db.close();
				return MyArrList;
				
		 } catch (Exception e) {
		    return null;
		 }

	}
	
	////////////////////////////////////////////////////// Between
	
	//SELECT SUM(invalue) FROM income WHERE strftime('%Y-%m', `indate`) BETWEEN strftime('%Y-%m-%d','2012-01-01')  AND strftime('%Y-%m-%d','2012-07-01')
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*SELECT strftime('%Y-%m',`exdate`) AS 'month' ,SUM(exvalue) As `Amount ` FROM expense 
	WHERE strftime('%Y', `exdate`) = strftime('%Y','now')
		GROUP BY `Month`*/
	
	
	
	
	
	
	/*public ArrayList<HashMap<String, String>> SumExpenseGmonth() {
		// TODO Auto-generated method stub
		
		 try {
			 
			 ArrayList<HashMap<String, String>> MyArrList = new ArrayList<HashMap<String, String>>();
			 HashMap<String, String> map;
			 
			 SQLiteDatabase db;
			 db = this.getReadableDatabase(); // Read Data
				
			 String strSQL = "SELECT strftime('%m',`exdate`) AS 'month' ,SUM(exvalue) As 'exp' FROM expense WHERE strftime('%Y', `exdate`) = strftime('%Y','now') GROUP BY `Month`";
			 String strSQL1 = "SELECT strftime('%m',`indate`) AS 'month' ,SUM(invalue) As `inc ` FROM income WHERE strftime('%Y', `indate`) = strftime('%Y','now') GROUP BY `Month`";
			// String strSQL2 = "SELECT SELECT * FROM income , expense WHERE strftime('%Y', `indate`) OR strftime('%Y', `exdate`) = strftime('%Y', 'now')";
			 Cursor cursor = db.rawQuery(strSQL, null);
			 Cursor cursor1 = db.rawQuery(strSQL1, null);
			 	if(cursor != null && cursor1 != null)
			 	{
			 	    if (cursor.moveToFirst() && cursor1.moveToFirst()) {
			 	        do {
			 	        	String exp = cursor.getString(1);
			 	        	String inc = cursor1.getString(1);
			 	        //	Log.d("tag", "msg" + cur);
			 	        //	Log.d("tag", "msg1" + cur1);
			 	    	   map = new HashMap<String, String>();

			 	    	   if(inc != null){
			 	    		   map.put("Month", cursor1.getString(0));
			 	    		   map.put("Expense", cursor.getString(1));
			 	    		   map.put("Income", cursor1.getString(1));
				 	    	  MyArrList.add(map);
			 	    	   }
			 	    	   
			 	    	   if (exp != null){
			 	    		   map.put("Month",  cursor.getString(0));
			 	    		   map.put("Expense", cursor.getString(1));
			 	    		   map.put("Income", cursor1.getString(1));
			 	    	   MyArrList.add(map);
			 	       }
			 	       } while (cursor.moveToNext() && cursor1.moveToNext())  ;
			 	    }
			 	}
			 	cursor1.close();
			 	cursor.close();
			 	db.close();
				return MyArrList;
				
		 } catch (Exception e) {
		    return null;
		 }*/

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/* public Cursor SumIncome() {
			
			 try {
				 SQLiteDatabase db;
				 db = this.getReadableDatabase(); // Read Data
		
		 Cursor cursor = db.rawQuery("SELECT SUM(invalue) as sum FROM income",null);
		 
		 return cursor;
			
			 } catch (Exception e) {
			    return null;
			 }

	 	} */
	
	
	
