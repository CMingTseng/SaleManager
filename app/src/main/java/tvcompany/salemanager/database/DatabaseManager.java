package tvcompany.salemanager.database;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.os.Environment;
import android.util.Log;

import tvcompany.salemanager.model.Message;
import tvcompany.salemanager.model.User;


public class DatabaseManager {
	private SQLiteDatabase sqlDB;
	private String pathDB;
	private Context mContext;
	private static final String DB_NAME = "DomDom.sqlite";
	private static final String TAG = "DatabaseManager";
	private ContentValues contentVL = new ContentValues();

	public DatabaseManager(Context context) {
		Log.i(TAG, "DatabaseManager is created...");
		mContext = context;

		pathDB = Environment.getDataDirectory() + "/data/"
				+ mContext.getPackageName() + "/databases/";
		copyDB();
		getTablename();
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
	public void getTablename(){
		openDB();
		Cursor c = sqlDB.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null);

		if (c.moveToFirst()) {
			while ( !c.isAfterLast() ) {
				Log.i(TAG, "Table Name=> "+c.getString(0));

				c.moveToNext();
			}
		}
		c.close();
		closeDB();
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


	public void InserProfile(User user) {
		openDB();
		try {
			sqlDB.beginTransaction();
			String sql = "Insert into User(userName,password,createDate,email,phoneNumber,fullName,note,parent,image) values(?,?,?,?,?,?,?,?,?)";
			SQLiteStatement insert = sqlDB.compileStatement(sql);
			insert.bindString(1, user.getUserName());
			insert.bindString(2, user.getPassWord());
			insert.bindString(3, user.getCreateDate());
			insert.bindString(4, user.getEmail());
			insert.bindString(5, user.getPhoneNumber());
			insert.bindString(6, user.getFullName());
			insert.bindString(7, user.getNote());
			insert.bindString(8, user.getParent());
			String path= user.getImage();
			insert.bindString(9, path);

			insert.execute();
			sqlDB.setTransactionSuccessful();
			sqlDB.endTransaction();
			closeDB();
		}catch (Exception ex){
			String error= ex.toString();
		}
	}


	public User GetContact() {
		openDB();
		Cursor c = sqlDB.rawQuery("Select * from User", null);
		User item = new User();
		if (c == null)
			return null;
		c.moveToFirst();
		while (c.isAfterLast() == false) {

			item.setUserName(c.getString(c.getColumnIndex("userName")));
			item.setPassWord(c.getString(c.getColumnIndex("password")));
			item.setCreateDate(c.getString(c.getColumnIndex("createDate")));
			item.setEmail(c.getString(c.getColumnIndex("email")));
			item.setPhoneNumber(c.getString(c.getColumnIndex("phoneNumber")));
			item.setFullName(c.getString(c.getColumnIndex("fullName")));
			item.setNote(c.getString(c.getColumnIndex("note")));
			item.setParent(c.getString(c.getColumnIndex("parent")));
			item.setImage(c.getString(c.getColumnIndex("image")));
			item.setValid(true);
			item.setActive(true);

			c.moveToNext();
		}
		c.close();
		closeDB();
		return item;
	}
	public  String imageLink(String name){
		openDB();
		Cursor c = sqlDB.rawQuery("Select * from User where userName='"+name+"'", null);
		String result="";
		if (c == null)
			return null;
		c.moveToFirst();
		while (c.isAfterLast() == false) {
			result=c.getString(c.getColumnIndex("image"));
			c.moveToNext();
		}
		c.close();
		closeDB();
		return result;
	}
	//Read and write message
	public void InserMessage(Message message) {
		openDB();
		sqlDB.beginTransaction();
		String sql = "Insert into Message_Dom(id_UserSend,id_UserRecevice,data,dataType,idSort,date,typeAction) values(?,?,?,?,?,?,?)";
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
		Cursor c = sqlDB.rawQuery("Select * from Message_Dom WHERE (id_UserSend = '" + userSend + "' AND id_UserRecevice = '" + userRecevice +  "')" +
				"OR (id_UserSend = '" + userRecevice + "' AND id_UserRecevice = '" + userSend +  "')  ORDER BY idSort desc LIMIT 50", null);
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
