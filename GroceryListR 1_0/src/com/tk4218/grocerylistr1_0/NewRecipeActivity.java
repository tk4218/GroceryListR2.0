package com.tk4218.grocerylistr1_0;

import java.io.OutputStream;
import java.util.ArrayList;

import com.tk4218.grocerylistr1_0.adapters.IngredientListAdapter;
import com.tk4218.grocerylistr1_0.adapters.ListExpander;
import com.tk4218.grocerylistr1_0.model.BitmapHandler;
import com.tk4218.grocerylistr1_0.model.Ingredient;
import com.tk4218.grocerylistr1_0.model.Recipe;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.ListView;
import android.widget.Spinner;
import android.support.v4.app.NavUtils;

public class NewRecipeActivity extends Activity {

	static final int REQUEST_CAPTURE_IMAGE = 1;
    private static final int SELECT_PICTURE = 2;
	String pathName;
	Recipe editRecipe;
	OutputStream out;
	int recipeIndex;
	ImageView image;
	Bitmap newImage = null;
	Boolean edit;
	ListView ingredientList;
	EditText ingredientName, recipeName, recipeDescription, servingSize, recipeInstructions;
	Spinner ingredientAmountSpinner, ingredientAmountSpinner2, 
	ingredientMeasurementSpinner, ingredientPreparationSpinner;
	String amount1, amount2, measurement, preparation;
	IngredientListAdapter ingredientListAdapter;
	ArrayList<Ingredient> ingredients;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_recipe);
		// Show the Up button in the action bar.
		setupActionBar();
		editRecipe = new Recipe();
		Intent intent = getIntent();
		try{
			editRecipe = (Recipe) intent.getSerializableExtra("recipe");
		}catch(Exception e){
			Log.d("DEBUG", "New Recipe");
		}
		edit = intent.getBooleanExtra("EditRecipe", false);
		recipeIndex = intent.getIntExtra("recipeBookCount",0); 
		intent.removeExtra("recipe");
		intent.removeExtra("EditRecipe");
		intent.removeExtra("recipeBookCount");
		Log.d("DEBUG", "Recipe Index: " + recipeIndex);
		image = (ImageView) findViewById(R.id.button_add_image);
		image.setScaleType(ScaleType.FIT_XY);
		if(edit){
			newImage = BitmapHandler.loadFromFile(editRecipe.getImageURL());
		}
		if(newImage == null){
			image.setImageResource(R.drawable.ic_default_image);
		}
		else{
			image.setImageBitmap(newImage);
		}
		registerForContextMenu(image);
		image.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				openContextMenu(image);				
			}
			
		});
		if(edit){
			recipeName = (EditText) findViewById(R.id.edit_recipe_name);
			recipeName.setText(editRecipe.getName());
			recipeDescription = (EditText) findViewById(R.id.edit_description);
			recipeDescription.setText(editRecipe.getDescription());
			recipeInstructions = (EditText) findViewById(R.id.edit_instructions);
			recipeInstructions.setText(editRecipe.getInstructions());
			servingSize = (EditText) findViewById(R.id.edit_serving_size);
			servingSize.setText(editRecipe.getServingSize()+"");
		}
		String[] amount = new String[21];
		for(int i = 0; i< 21; i++)
			amount[i] = ""+i;
		ingredientAmountSpinner = (Spinner) findViewById(R.id.spinner_ingredient_amount);
		ingredientAmountSpinner.setAdapter(new ArrayAdapter<String>(this,R.layout.new_ingredient_spinner_layout, amount));
		ingredientAmountSpinner.setOnItemSelectedListener(new OnItemSelectedListener(){
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, 
					int position, long id) {
				amount1 = (String)parent.getItemAtPosition(position);
			}
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {}
		});

		ingredientAmountSpinner2 = (Spinner) findViewById(R.id.spinner_ingredient_amount2);
		ingredientAmountSpinner2.setAdapter(new ArrayAdapter<String>(this,R.layout.new_ingredient_spinner_layout, 
				getResources().getStringArray(R.array.amount2array)));
		ingredientAmountSpinner2.setOnItemSelectedListener(new OnItemSelectedListener(){
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, 
					int position, long id) {
				amount2 = (String)parent.getItemAtPosition(position);
			}
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {}
		});
		
		ingredientMeasurementSpinner = (Spinner)findViewById(R.id.spinner_ingredient_measurement);
		ingredientMeasurementSpinner.setAdapter(new ArrayAdapter<String>(this,R.layout.new_ingredient_spinner_layout,
				getResources().getStringArray(R.array.recipe_measurements_array)));
		ingredientMeasurementSpinner.setOnItemSelectedListener(new OnItemSelectedListener(){
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, 
					int position, long id) {
				measurement = (String)parent.getItemAtPosition(position);
			}
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {}
		});
		
		ingredientPreparationSpinner = (Spinner) findViewById(R.id.spinner_ingredient_preparation);
		ingredientPreparationSpinner.setAdapter(new ArrayAdapter<String>(this, R.layout.new_ingredient_spinner_layout,
				getResources().getStringArray(R.array.ingredient_preparation_array)));
		ingredientPreparationSpinner.setOnItemSelectedListener(new OnItemSelectedListener(){
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, 
					int position, long id) {
				preparation = (String)parent.getItemAtPosition(position);
			}
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {}
		});
		ingredientName = (EditText) findViewById(R.id.edit_ingredient_name);
		ingredients = new ArrayList<Ingredient>();
		if(edit){
			ingredients = editRecipe.getIngredients();
		}
		ingredientList = (ListView) findViewById(R.id.list_add_ingredients);
		setIngredientList();
		
	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setTitle("Add New Recipe");

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.new_recipe, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		case R.id.action_save:
			Intent intent = new Intent(this, MainActivity.class);
			EditText recipeName = (EditText) findViewById(R.id.edit_recipe_name);
			EditText recipeDescription = (EditText) findViewById(R.id.edit_description);
			EditText servingSize = (EditText) findViewById(R.id.edit_serving_size);
			EditText instructions = (EditText) findViewById(R.id.edit_instructions);
			if(recipeName.getText().toString().equals(""))
				return true;
			if(servingSize.getText().toString().equals(""))
				servingSize.setText("1");
			Recipe newRecipe = new Recipe(recipeName.getText().toString(), 
										  Integer.parseInt(servingSize.getText().toString()),
										  recipeDescription.getText().toString(),
										  instructions.getText().toString(), 
										  ingredients);
			newRecipe.setImageURL(pathName);
			if(edit){
				newRecipe.setRecipeID(editRecipe.getRecipeID());
				newRecipe.setImageURL(editRecipe.getImageURL());
			}
			intent.putExtra("newRecipe", newRecipe);
			intent.putExtra("EditRecipe", edit);
			startActivity(intent);
			finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public void onBackPressed(){
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
		finish();
	}
    @Override  
    public void onCreateContextMenu(ContextMenu menu, View v,ContextMenuInfo menuInfo) {  
    	super.onCreateContextMenu(menu, v, menuInfo);  
    	menu.setHeaderTitle("Choose Action"); 
    	menu.add(0, 0, 0, "Take Picture");  
    	menu.add(0, 0, 0, "Choose From Gallery");
    }
    @Override  
    public boolean onContextItemSelected(MenuItem item) {  
    	if(item.getTitle().equals("Take Picture")){
    		pathName = Environment.getExternalStorageDirectory().toString() 
    				+ "/Pictures/GroceryListR/recipeImage_"+recipeIndex+".jpg";
			dispatchTakePictureIntent();
    		return true;
    	}
    	else if(item.getTitle().equals("Choose From Gallery")){
			Intent intent = new Intent();
			intent.setType("image/*");
			intent.setAction(Intent.ACTION_GET_CONTENT);
			startActivityForResult(Intent.createChooser(intent, "Select Image"), SELECT_PICTURE);
    		return true;
    	}
    	else
    		return false;
    }
    /**
     * helper to retrieve the path of an image URI
     */
    public String getPath(Uri uri) {
            // just some safety built in 
            if( uri == null ) {
                return null;
            }
            // try to retrieve the image from the media store first
            // this will only work for images selected from gallery
            String[] projection = { MediaStore.Images.Media.DATA };
            Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
            if( cursor != null ){
                int column_index = cursor
                .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                cursor.moveToFirst();
                String path = cursor.getString(column_index);
                cursor.close();
                return path;
            }
            // this is our fallback here
            return uri.getPath();
    }
 
	private void dispatchTakePictureIntent() {
	    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
	    // Ensure that there's a camera activity to handle the intent
	    if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
	            startActivityForResult(takePictureIntent, REQUEST_CAPTURE_IMAGE);
	    }
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	    if (requestCode == REQUEST_CAPTURE_IMAGE && resultCode == RESULT_OK) {
	        Bundle extras = data.getExtras();
	        Bitmap imageBitmap = (Bitmap) extras.get("data");
	        newImage = imageBitmap;
	        image.setImageBitmap(newImage);
	        image.setScaleType(ScaleType.FIT_XY);
	        Log.d("DEBUG", pathName);
	        BitmapHandler.saveBitmapToFile(pathName, imageBitmap);
	        if(edit)
	        	editRecipe.setImageURL(pathName);
	    }
        if (requestCode == SELECT_PICTURE && resultCode == RESULT_OK) {
            Uri selectedImageUri = data.getData();
            pathName = getPath(selectedImageUri);
            Log.d("DEBUG", pathName);
            image.setImageBitmap(BitmapHandler.loadFromFile(pathName));
	        image.setScaleType(ScaleType.FIT_XY);
	        if(edit)
	        	editRecipe.setImageURL(pathName);
        }
	}
	public void addNewIngredient(View view){
		if(ingredientName.getText().toString().equals("") || (amount1.equals("0") && amount2.equals("0/16")))
			return;
		String prep = "";
		if(!preparation.equals("Preparation"))
			prep = preparation;
		
		CheckBox optional = (CheckBox) findViewById(R.id.checkbox_ingredient_optional);
		Ingredient newIngredient = new Ingredient(ingredientName.getText().toString(),
				Integer.parseInt(amount1), amount2,  measurement, prep, optional.isChecked());
		ingredients.add(newIngredient);
		Log.d("DEBUG", ""+ingredients.size()+" "+ingredients.get(ingredients.size()-1).getName());
		//ingredients.get(ingredients.size()-1).setName(ingredientName.getText().toString());
		ingredientName.setText("");
		ingredientListAdapter.notifyDataSetChanged();
		ListExpander.getListViewSize(ingredientList);
	}
	
	public void setIngredientList(){
		ingredientListAdapter = new IngredientListAdapter(this, ingredients);
		ingredientList.setAdapter(ingredientListAdapter);
		ListExpander.getListViewSize(ingredientList);
	}
}
