package project.savesatang;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class ExpenseDataSource {
	public static final String TAG = "SQLite Database";
	private SQLiteDatabase database;
	private SQLiteHelper dbHelper;
	private String[] allColumns2 = { SQLiteHelper.COLUMN_EXID,
			SQLiteHelper.COLUMN_EXCATEGORYID, SQLiteHelper.COLUMN_EXVALUE,
			SQLiteHelper.COLUMN_EXDETAIL, SQLiteHelper.COLUMN_EXDATE,
			SQLiteHelper.COLUMN_EXCATYPE, SQLiteHelper.COLUMN_EXCACATEGORY,
			SQLiteHelper.COLUMN_EXCANAME
	};
	public ExpenseDataSource(Context context) {
		dbHelper = new SQLiteHelper(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}
	
	// //////////////// Insert /////////////////////
		public Expense insertExpense(Expense expense) {
			ContentValues values2 = new ContentValues();
			values2.put(SQLiteHelper.COLUMN_EXCATEGORYID, expense.getEXCATEGORYID());
			values2.put(SQLiteHelper.COLUMN_EXVALUE, expense.getEXVALUE());
			values2.put(SQLiteHelper.COLUMN_EXDETAIL, expense.getEXDETAIL());
			values2.put(SQLiteHelper.COLUMN_EXDATE, expense.getEXDATE());
			values2.put(SQLiteHelper.COLUMN_EXCATYPE, expense.getEXCATYPE());
			values2.put(SQLiteHelper.COLUMN_EXCACATEGORY, expense.getEXCACATEGORY());
			values2.put(SQLiteHelper.COLUMN_EXCANAME, expense.getEXCANAME());
			long insertId = database
					.insert(SQLiteHelper.TABLE_EXPENSE, null, values2);

			Cursor cursor = database.query(SQLiteHelper.TABLE_EXPENSE, allColumns2,
					SQLiteHelper.COLUMN_EXID + " = " + insertId, null, null, null,
					null);
			cursor.moveToFirst();
			return cursorToExpense(cursor);
		}
		
		// ///////////////// Delete ///////////////////
		public void deleteExpense(Expense expense) {
			long exid = expense.getEXID();
			Log.d(TAG, "Expense deleted with id:" + exid);
			database.delete(SQLiteHelper.TABLE_EXPENSE, SQLiteHelper.COLUMN_EXID
					+ " = " + expense.getEXID(), null);
		}
		
		public void updateExpense(Expense expense) {
			ContentValues values2 = new ContentValues();
			values2.put(SQLiteHelper.COLUMN_EXCATEGORYID, expense.getEXCATEGORYID());
			values2.put(SQLiteHelper.COLUMN_EXVALUE, expense.getEXVALUE());
			values2.put(SQLiteHelper.COLUMN_EXDETAIL, expense.getEXDETAIL());
			values2.put(SQLiteHelper.COLUMN_EXDATE, expense.getEXDATE());
			values2.put(SQLiteHelper.COLUMN_EXCATYPE, expense.getEXCATYPE());
			values2.put(SQLiteHelper.COLUMN_EXCACATEGORY, expense.getEXCACATEGORY());
			values2.put(SQLiteHelper.COLUMN_EXCANAME, expense.getEXCANAME());
			database.update(SQLiteHelper.TABLE_EXPENSE, values2,
					SQLiteHelper.COLUMN_EXID + "=" + expense.getEXID(), null);
			
		}
		
		// ///////////////// LIST ///////////////////////
		public List<Expense> getAllExpense() {
			List<Expense> comments = new ArrayList<Expense>();
			Cursor cursor = database.query(SQLiteHelper.TABLE_EXPENSE, allColumns2,
					null, null, null, null, null);
			Collections.reverse(comments);
			cursor.moveToFirst();
			while (!cursor.isAfterLast()) {
				Expense comment = cursorToExpense(cursor);
				comments.add(0, comment);
				cursor.moveToNext();
			}
			cursor.close();
			return comments;
		}

		private Expense cursorToExpense(Cursor cursor) {
			Expense expense = new Expense();
			expense.setEXID(cursor.getLong(0));
			expense.setEXCATEGORYID(cursor.getLong(1));
			expense.setEXVALUE(cursor.getLong(2));
			expense.setEXDETAIL(cursor.getString(3));
			expense.setEXDATE(cursor.getString(4));
			expense.setEXCATYPE(cursor.getString(5));
			expense.setEXCACATEGORY(cursor.getString(6));
			expense.setEXCANAME(cursor.getString(7));
			return expense;
		}
}
