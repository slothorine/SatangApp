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

public class IncomeDataSource {
	public static final String TAG = "SQLite Database";
	private SQLiteDatabase database;
	private SQLiteHelper dbHelper;
	private String[] allColumns = { SQLiteHelper.COLUMN_INID,
			SQLiteHelper.COLUMN_INCATEGORYID, SQLiteHelper.COLUMN_INVALUE,
			SQLiteHelper.COLUMN_INDETAIL, SQLiteHelper.COLUMN_INDATE,
			SQLiteHelper.COLUMN_INCATYPE, SQLiteHelper.COLUMN_INCACATEGORY,
			SQLiteHelper.COLUMN_INCANAME
			};

	public IncomeDataSource(Context context) {
		dbHelper = new SQLiteHelper(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	// //////////////// Insert /////////////////////
	public Income insertIncome(Income income) {
		ContentValues values = new ContentValues();
		values.put(SQLiteHelper.COLUMN_INCATEGORYID, income.getINCATEGORYID());
		values.put(SQLiteHelper.COLUMN_INVALUE, income.getINVALUE());
		values.put(SQLiteHelper.COLUMN_INDETAIL, income.getINDETAIL());
		values.put(SQLiteHelper.COLUMN_INDATE, income.getINDATE());
		values.put(SQLiteHelper.COLUMN_INCATYPE, income.getINCATYPE());
		values.put(SQLiteHelper.COLUMN_INCACATEGORY, income.getINCACATEGORY());
		values.put(SQLiteHelper.COLUMN_INCANAME, income.getINCANAME());
		long insertId = database
				.insert(SQLiteHelper.TABLE_INCOME, null, values);

		Cursor cursor = database.query(SQLiteHelper.TABLE_INCOME, allColumns,
				SQLiteHelper.COLUMN_INID + " = " + insertId, null, null, null,
				null);
		cursor.moveToFirst();
		return cursorToIncome(cursor);
	}

	// ///////////////// Delete ///////////////////
	public void deleteIncome(Income income) {
		long inid = income.getINID();
		Log.d(TAG, "Income deleted with id:" + inid);
		database.delete(SQLiteHelper.TABLE_INCOME, SQLiteHelper.COLUMN_INID
				+ " = " + income.getINID(), null);
	}

	// ///////////////// Update ////////////////////
	public void updateIncome(Income income) {
		ContentValues values = new ContentValues();
		values.put(SQLiteHelper.COLUMN_INCATEGORYID, income.getINCATEGORYID());
		values.put(SQLiteHelper.COLUMN_INVALUE, income.getINVALUE());
		values.put(SQLiteHelper.COLUMN_INDETAIL, income.getINDETAIL());
		values.put(SQLiteHelper.COLUMN_INDATE, income.getINDATE());
		values.put(SQLiteHelper.COLUMN_INCATYPE, income.getINCATYPE());
		values.put(SQLiteHelper.COLUMN_INCACATEGORY, income.getINCACATEGORY());
		values.put(SQLiteHelper.COLUMN_INCANAME, income.getINCANAME());
		database.update(SQLiteHelper.TABLE_INCOME, values,
				SQLiteHelper.COLUMN_INID + "=" + income.getINID(), null);
	}

	// ///////////////// LIST ///////////////////////
	public List<Income> getAllIncome() {
		List<Income> comments = new ArrayList<Income>();
		Cursor cursor = database.query(SQLiteHelper.TABLE_INCOME, allColumns,
				null, null, null, null, null);
		 Collections.reverse(comments);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Income comment = cursorToIncome(cursor);
			comments.add(0, comment);
			cursor.moveToNext();
		}
		cursor.close();
		return comments;
	}

	private Income cursorToIncome(Cursor cursor) {
		Income income = new Income();
		income.setINID(cursor.getLong(0));
		income.setINCATEGORYID(cursor.getLong(1));
		income.setINVALUE(cursor.getLong(2));
		income.setINDETAIL(cursor.getString(3));
		income.setINDATE(cursor.getString(4));
		income.setINCATYPE(cursor.getString(5));
		income.setINCACATEGORY(cursor.getString(6));
		income.setINCANAME(cursor.getString(7));
		return income;
	}
}
