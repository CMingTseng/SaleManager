package tvcompany.salemanager.activity;

import android.app.SearchManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import tvcompany.salemanager.R;

public class SearchActivity extends AppCompatActivity {

	ArrayAdapter<String> myAdapter;
    ListView listView;
    String[] dataArray = new String[] {"Việt Nam","Nga", "Pakistan", "Bồ Đào Nha", "Đức", "Pháp","Mỹ","Bỉ","Anh"};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_results);
		
		
		//==========================

		listView = (ListView) findViewById(R.id.listview);
	    myAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dataArray);
	    listView.setAdapter(myAdapter);
	    listView.setTextFilterEnabled(true);
	    
	 listView.setOnItemClickListener(new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {

		System.out.println(arg2+" --postion");
		}
	});

	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_main, menu);

		 SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
	        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();

	            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
	            searchView.setIconifiedByDefault(false);
		searchView.setSearchableInfo(
				searchManager.getSearchableInfo(getComponentName()));
		searchView.setQueryHint("Type something...");
		View searchPlate = (View)searchView.findViewById(android.support.v7.appcompat.R.id.search_plate);
		if (searchPlate!=null) {
			searchPlate.setBackgroundColor(Color.WHITE);
			TextView searchText = (TextView) searchPlate.findViewById(android.support.v7.appcompat.R.id.search_src_text);
			if (searchText!=null) {
				searchText.setTextColor(Color.BLACK);
				searchText.setHintTextColor(Color.BLACK);
			}
		}
	        SearchView.OnQueryTextListener textChangeListener = new SearchView.OnQueryTextListener() 
	        {
	            @Override
	            public boolean onQueryTextChange(String newText) 
	            {
	                // this is your adapter that will be filtered
	                myAdapter.getFilter().filter(newText);
	                System.out.println("on text chnge text: "+newText);
	                return true;
	            }
	            @Override
	            public boolean onQueryTextSubmit(String query) 
	            {
	                // this is your adapter that will be filtered
	                myAdapter.getFilter().filter(query);
	                System.out.println("on query submit: "+query);
	                return true;
	            }
	        };
	        searchView.setOnQueryTextListener(textChangeListener);

	        return super.onCreateOptionsMenu(menu);

	}
}