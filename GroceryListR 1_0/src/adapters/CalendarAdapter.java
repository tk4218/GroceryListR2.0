package adapters;

import model.BitmapHandler;
import model.CalendarDay;
import model.Recipe;
import model.RecipeBook;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;

import com.tk4218.grocerylistr1_0.R;

public class CalendarAdapter extends ArrayAdapter<Recipe>{
	Context context;
	RecipeBook calendarRecipes;
	CalendarDay dayOfWeek;
	public CalendarAdapter(Context context, RecipeBook calRecipes) {
		super(context, R.layout.calendar_list_item_layout);
		this.context = context;
		calendarRecipes = calRecipes;
	}
	
	@Override
	public int getCount(){
		return calendarRecipes.getRecipeBookSize();
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = convertView;
		if(convertView == null)
			view = inflater.inflate(R.layout.calendar_list_item_layout, null);
		TextView weekday = (TextView)view.findViewById(R.id.dayOfWeek);
		weekday.setText(CalendarDay.getWeekDay(position));
		
		TextView recipeName = (TextView)view.findViewById(R.id.calendarRecipeName);
		TextView recipeDescription = (TextView)view.findViewById(R.id.calendarRecipeDescription);
		ImageView recipeImage = (ImageView) view.findViewById(R.id.calendar_list_item_image);
		String pathName = calendarRecipes.getRecipe(position).getImageURL();
		Bitmap image = BitmapHandler.loadFromFile(pathName);
		if(image != null)
			recipeImage.setImageBitmap(image);
		else
			recipeImage.setImageResource(R.drawable.ic_default_image);
		recipeImage.setScaleType(ScaleType.FIT_XY);
		recipeName.setText(calendarRecipes.getRecipe(position).getName());
		recipeDescription.setText(calendarRecipes.getRecipe(position).getDescription());
		return view;
	}
}
