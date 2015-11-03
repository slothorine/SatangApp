package project.savesatang;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class SummaryDataSource {
	public static final String TAG = "SQLite Database";
	private SQLiteDatabase database;
	private SQLiteHelper dbHelper;
	private String[] allColumns3 = { SQLiteHelper.COLUMN_SUMID,
			SQLiteHelper.COLUMN_SUMCATEGORYID, SQLiteHelper.COLUMN_SUMINVALUE,
			SQLiteHelper.COLUMN_SUMEXVALUE, SQLiteHelper.COLUMN_SUMDATE,
			SQLiteHelper.COLUMN_SUMCATYPE, SQLiteHelper.COLUMN_SUMCACATEGORY,
			SQLiteHelper.COLUMN_SUMCANAME
			};

	public SummaryDataSource(Context context) {
		dbHelper = new SQLiteHelper(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	// //////////////// Insert /////////////////////
	public Summary insertSummary(Summary summary) {
		ContentValues values = new ContentValues();
		values.put(SQLiteHelper.COLUMN_SUMCATEGORYID, summary.getSUMCATEGORYID());
		values.put(SQLiteHelper.COLUMN_SUMINVALUE, summary.getSUMINVALUE());
		values.put(SQLiteHelper.COLUMN_SUMEXVALUE, summary.getSUMEXVALUE());
		values.put(SQLiteHelper.COLUMN_SUMDATE, summary.getSUMDATE());
		values.put(SQLiteHelper.COLUMN_SUMCATYPE, summary.getSUMCATYPE());
		values.put(SQLiteHelper.COLUMN_SUMCACATEGORY, summary.getSUMCACATEGORY());
		values.put(SQLiteHelper.COLUMN_SUMCANAME, summary.getSUMCANAME());
		long insertId = database
				.insert(SQLiteHelper.TABLE_INCOME, null, values);

		Cursor cursor = database.query(SQLiteHelper.TABLE_SUMMARY, allColumns3,
				SQLiteHelper.COLUMN_SUMID + " = " + insertId, null, null, null,
				null);
		cursor.moveToFirst();
		return cursorToSummary(cursor);
	}

	// ///////////////// Delete ///////////////////
	public void deleteIncome(Summary summary) {
		long sumid = summary.getSUMID();
		Log.d(TAG, "Income deleted with id:" + sumid);
		database.delete(SQLiteHelper.TABLE_SUMMARY, SQLiteHelper.COLUMN_SUMID
				+ " = " + summary.getSUMID(), null);
	}

	// ///////////////// Update ////////////////////
	public void updateSummary(Summary summary) {
		ContentValues values = new ContentValues();
		values.put(SQLiteHelper.COLUMN_SUMCATEGORYID, summary.getSUMCATEGORYID());
		values.put(SQLiteHelper.COLUMN_SUMINVALUE, summary.getSUMINVALUE());
		values.put(SQLiteHelper.COLUMN_SUMEXVALUE, summary.getSUMEXVALUE());
		values.put(SQLiteHelper.COLUMN_SUMDATE, summary.getSUMDATE());
		values.put(SQLiteHelper.COLUMN_SUMCATYPE, summary.getSUMCATYPE());
		values.put(SQLiteHelper.COLUMN_SUMCACATEGORY, summary.getSUMCACATEGORY());
		values.put(SQLiteHelper.COLUMN_SUMCANAME, summary.getSUMCANAME());
		database.update(SQLiteHelper.TABLE_SUMMARY, values,
				SQLiteHelper.COLUMN_SUMID + "=" + summary.getSUMID(), null);
	}

	/*// ///////////////// LIST ///////////////////////
	public List<Income> getAllIncome() {
		List<Income> comments = new ArrayList<Income>();
		Cursor cursor = database.query(SQLiteHelper.TABLE_INCOME, allColumns3,
				null, null, null, null, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Income comment = cursorToSummary(cursor);
			comments.add(comment);
			cursor.moveToNext();
		}
		cursor.close();
		return comments;
	}*/

	private Summary cursorToSummary(Cursor cursor) {
		Summary summary = new Summary();
		summary.setSUMID(cursor.getLong(0));
		summary.setSUMCATEGORYID(cursor.getLong(1));
		summary.setSUMINVALUE(cursor.getLong(2));
		summary.setSUMEXVALUE(cursor.getLong(3));
		summary.setSUMDATE(cursor.getString(4));
		summary.setSUMCATYPE(cursor.getString(5));
		summary.setSUMCACATEGORY(cursor.getString(6));
		summary.setSUMCANAME(cursor.getString(7));
		return summary;
	}
}
