package tvcompany.salemanager.database;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

import tvcompany.salemanager.model.Contact;
import tvcompany.salemanager.model.Message;


public class DatabaseManager {
	private SQLiteDatabase sqlDB;
	private String pathDB;
	private Context mContext;
	private static final String DB_NAME = "SaleManager.sqlite";
	private static final String TAG = "DatabaseManager";
	private ContentValues contentVL = new ContentValues();

	public DatabaseManager(Context context) {
		Log.i(TAG, "DatabaseManager is created...");
		mContext = context;

		pathDB = Environment.getDataDirectory() + "/data/"
				+ mContext.getPackageName() + "/databases/";
		copyDB();
	}

	private void copyDB() {
		try {
			File file = new File(pathDB);
			file.mkdir();
			file = new File(pathDB + DB_NAME);
			if (file.exists()) {
				Log.i(TAG, "Database was exist...");
				return;
			}
			InputStream input = mContext.getAssets().open(DB_NAME);
			FileOutputStream output = new FileOutputStream(file);
			int len;
			byte b[] = new byte[1024];
			while ((len = input.read(b)) != -1) {
				output.write(b, 0, len);
			}
			output.close();
			input.close();
			Log.i(TAG, "copyDB is done...");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void openDB() {
		if (sqlDB == null || !sqlDB.isOpen())
			sqlDB = SQLiteDatabase.openDatabase(pathDB + DB_NAME, null,
					SQLiteDatabase.OPEN_READWRITE);
	}

	public void closeDB() {
		if (sqlDB != null && sqlDB.isOpen())
			sqlDB.close();
	}


	public void InserProfile(Contact contact) {
		openDB();
		sqlDB.beginTransaction();
		String sql = "Insert or Replace into Profile(s_ID,s_Name,s_Address,s_Phone,i_Image) values(?,?,?,?,?)";
		SQLiteStatement insert = sqlDB.compileStatement(sql);
		byte[] data = getBitmapAsByteArray(contact.getI_image()); // this is a function
		insert.bindString(1, "gandalf");
		insert.bindString(2, contact.getS_fullName());
		insert.bindString(3, "TVCompany");
		insert.bindString(3, contact.getS_Phone());
		insert.bindBlob(4, data);

		insert.execute();
		sqlDB.setTransactionSuccessful();
		sqlDB.endTransaction();
		closeDB();
	}


	public Contact GetContact() {
		openDB();
		Cursor c = sqlDB.rawQuery("Select * from Profile", null);
		Contact item = new Contact();
		if (c == null)
			return null;
		c.moveToFirst();
		while (c.isAfterLast() == false) {

			item.setS_name(c.getString(c.getColumnIndex("s_ID")));
			item.setS_fullName(c.getString(c.getColumnIndex("s_Name")));
			item.setS_Phone(c.getString(c.getColumnIndex("s_Phone")));
			byte[] imgByte = c.getBlob(c.getColumnIndex("i_Image"));
			item.setI_image(BitmapFactory.decodeByteArray(imgByte, 0, imgByte.length));
			c.moveToNext();
		}
		c.close();
		closeDB();
		return item;
	}

	//Read and write message
	public void InserMessage(Message message) {
		openDB();
		sqlDB.beginTransaction();
		String sql = "Insert into Message(id_UserSend,id_UserRecevice,data,dataType,idSort,date,typeAction) values(?,?,?,?,?,?,?)";
		SQLiteStatement insert = sqlDB.compileStatement(sql);
		insert.bindString(1, message.getUserSend());
		insert.bindString(2, message.getUserRecieve());
		insert.bindString(3, message.getData());
		insert.bindLong(4, message.getDataType());
		insert.bindLong(5, message.getIdSort());
		insert.bindString(6, message.getDate() +"");
		insert.bindLong(7, message.getTypeAction());
		insert.execute();
		sqlDB.setTransactionSuccessful();
		sqlDB.endTransaction();
		closeDB();
	}

	public List<Message> GetMessage(String userSend,String userRecevice) {
		List<Message> list = new ArrayList<>();
		openDB();
		//Cursor c = sqlDB.rawQuery("Select * from Message ", null);
		Cursor c = sqlDB.rawQuery("Select * from Message WHERE (id_UserSend = '" + userSend + "' AND id_UserRecevice = '" + userRecevice +  "')" +
				"OR (id_UserSend = '" + userRecevice + "' AND id_UserRecevice = '" + userSend +  "')  ORDER BY idSort LIMIT 50", null);
		//Cursor c = sqlDB.rawQuery("Select * from Message  ORDER BY idSort LIMIT 50", null);
		Message item ;
		if (c == null)
			return null;
		c.moveToFirst();
		while (c.isAfterLast() == false) {
			item = new Message();
			item.setUserSend(c.getString(c.getColumnIndex("id_UserSend")));
			item.setUserRecieve(c.getString(c.getColumnIndex("id_UserRecevice")));
			item.setData(c.getString(c.getColumnIndex("data")));
			item.setDataType(c.getInt(c.getColumnIndex("dataType")));
			item.setIdSort(c.getLong(c.getColumnIndex("idSort")));
			item.setDate(new java.util.Date());
			item.setTypeAction(c.getInt(c.getColumnIndex("typeAction")));
			list.add(item);
			c.moveToNext();
		}
		c.close();
		closeDB();
		return list;
	}

	public static byte[] getBitmapAsByteArray(Bitmap bitmap) {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		bitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
		return outputStream.toByteArray();
	}
}
