package com.android.ruontime.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database {
	
	public static final String KEY_ROWID = "_id";
	public static final String KEY_MEETING = "meeting";
	public static final String KEY_PUNCTUALITY = "punctuality";
	
	private static final String DATABASE_NAME = "RuonTimedb";
	private static final String DATABASE_TABLE = "PunctualityTable";
	private static final int DATABASE_VERSION = 1;

	private DbHelper ourHelper;
	private final Context ourContext;
	private SQLiteDatabase ourDatabase;
	
	private static class DbHelper extends SQLiteOpenHelper{

		public DbHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			db.execSQL("CREATE TABLE " + DATABASE_TABLE + " (" +
					KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
					KEY_MEETING + " TEXT NOT NULL, " +
					KEY_PUNCTUALITY + " TEXT NOT NULL);"
			);	
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
			onCreate(db);
		}		
	}
	
	public Database(Context c){
		ourContext = c;
	}
	
	public Database open() throws SQLException{
		ourHelper = new DbHelper(ourContext);
		ourDatabase = ourHelper.getWritableDatabase();
		return this;
	}
	
	 public void close(){
		 ourHelper.close();
	 }

	public long createEntry(String meeting, String punctuality) {
		// TODO Auto-generated method stub
		ContentValues cv = new ContentValues();
		cv.put(KEY_MEETING, meeting);
		cv.put(KEY_PUNCTUALITY, punctuality);
		return ourDatabase.insert(DATABASE_TABLE, null, cv);
	}

	public String getData() {
		// TODO Auto-generated method stub
		String[] columns = new String[]{ KEY_ROWID, KEY_MEETING, KEY_PUNCTUALITY};
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, null, null, null, null, null);
		String result = "";
		
		int iRow = c.getColumnIndex(KEY_ROWID);
		int iMeeting = c.getColumnIndex(KEY_MEETING);
		int iPunctuality = c.getColumnIndex(KEY_PUNCTUALITY);
		
		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
			result = result + c.getString(iRow) + "             " + c.getString(iMeeting) + "            " + c.getString(iPunctuality) + "\n";
		}
		
		return result;
	}
	public String getMeeting(long l) throws SQLException{
		// TODO Auto-generated method stub
		String[] columns = new String[]{ KEY_ROWID, KEY_MEETING, KEY_PUNCTUALITY};
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, KEY_ROWID + "=" + l, null, null, null, null);
		if (c != null){
			c.moveToFirst();
			String meeting = c.getString(1);
			return meeting;
		}
		return null;
	}

	public String getPunctuality(long l) throws SQLException{
		// TODO Auto-generated method stub
		String[] columns = new String[]{ KEY_ROWID, KEY_MEETING, KEY_PUNCTUALITY};
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, KEY_ROWID + "=" + l, null, null, null, null);
		if (c != null){
			c.moveToFirst();
			String punctuality = c.getString(2);
			return punctuality;
		}
		return null;
	}
	public Long getTotalLate() throws SQLException{
		// TODO Auto-generated method stub
		String[] columns = new String[]{ KEY_ROWID, KEY_MEETING, KEY_PUNCTUALITY};
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, null, null, null, null, null);
		Long totalLate = 0L;
		for (c.moveToFirst();!c.isAfterLast();c.moveToNext()){
			if (Long.valueOf(c.getString(2))<0){
				totalLate+=Long.valueOf(c.getString(2));
			}	
		}
		return totalLate;
	}
	public Long getAverageLate() throws SQLException{
		// TODO Auto-generated method stub
		String[] columns = new String[]{ KEY_ROWID, KEY_MEETING, KEY_PUNCTUALITY};
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, null, null, null, null, null);
		Long totalLate = 0L;
		int i =0;
		for (c.moveToFirst();!c.isAfterLast();c.moveToNext()){
			if (Long.valueOf(c.getString(2))<0){
				totalLate+=Long.valueOf(c.getString(2));
				i++;
			}	
		}
		if (i!=0){
			return (Long)totalLate/i;
		}
		return 0L;
	}
	public Long getTotalEarly() throws SQLException{
		// TODO Auto-generated method stub
		String[] columns = new String[]{ KEY_ROWID, KEY_MEETING, KEY_PUNCTUALITY};
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, null, null, null, null, null);
		Long totalEarly = 0L;
		for (c.moveToFirst();!c.isAfterLast();c.moveToNext()){
			if (Long.valueOf(c.getString(2))>0){
				totalEarly+=Long.valueOf(c.getString(2));
			}	
		}
		return totalEarly;
	}
	public Long getAverageEarly() throws SQLException{
		// TODO Auto-generated method stub
				String[] columns = new String[]{ KEY_ROWID, KEY_MEETING, KEY_PUNCTUALITY};
				Cursor c = ourDatabase.query(DATABASE_TABLE, columns, null, null, null, null, null);
				Long totalEarly = 0L;
				int i =0;
				for (c.moveToFirst();!c.isAfterLast();c.moveToNext()){
					if (Long.valueOf(c.getString(2))>0){
						totalEarly+=Long.valueOf(c.getString(2));
						i++;
					}	
				}
				if (i!=0){
					return (Long)totalEarly/i;
				}
				return 0L;
	}
	public Long getAverage() throws SQLException{
		// TODO Auto-generated method stub
				String[] columns = new String[]{ KEY_ROWID, KEY_MEETING, KEY_PUNCTUALITY};
				Cursor c = ourDatabase.query(DATABASE_TABLE, columns, null, null, null, null, null);
				Long total = 0L;
				int i =0;
				for (c.moveToFirst();!c.isAfterLast();c.moveToNext()){
						total+=Long.valueOf(c.getString(2));
						i++;
				}
				if (i!=0){
					return (Long)total/i;
				}
				return 0L;
	}
	
	public void delete() throws SQLException{
		// TODO Auto-generated method stub
		ourDatabase.delete(DATABASE_TABLE, null, null);
	}
	 
	 
	
}
