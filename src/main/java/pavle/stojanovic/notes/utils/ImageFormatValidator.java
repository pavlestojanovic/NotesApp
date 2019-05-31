package pavle.stojanovic.notes.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ImageFormatValidator {
	  
	public static final Pattern IMAGE_PATTERN = Pattern.compile("([^\\s]+(\\.(?i)(/bmp|jpg|gif|png))$)");
	  
	public static boolean validate(final String image){
	  
	    Matcher matcher = IMAGE_PATTERN.matcher(image);
	    return matcher.matches();
	  
	}

}
