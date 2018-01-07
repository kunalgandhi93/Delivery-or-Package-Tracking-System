package application;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Constants {

	public static String SHIP_FROM="FROM";
	public static String SHIP_TO="TO";
	public static String ID="Id";
	public static String LOCATION="LOCATION";
	public static String DATE="DATE";
	public static String TIME="TIME";
	public static String WEIGHT="WEIGHT";
	public static String PIECES="PIECES";
	public static ArrayList<String> list=new ArrayList<>();
	
	public static ArrayList<String> getList()
	{
		list.add("Northborough, MA");
		list.add("Edison, NJ");
		list.add("Pittsburgh, PA");
		list.add("Allentown, PABD Strap Endlinksth");
		list.add("Martinsburg, WV");
		list.add("Charlotte, NC");
		list.add("Atlanta, GA");
		list.add("Orlando, FL");
		list.add("Memphis, TN");
		list.add("Grove City, OH");
		list.add("Indianapolis, IN");
		list.add("Detroit, MI");
		list.add("New Berlin, WI");
		list.add("Minneapolis, MN");
		list.add("St. Louis, MO");
		list.add("Kansas, KS");
		list.add("Dallas, TX");
		list.add("Houston, TX");
		list.add("Denver, CO");
		list.add("Salt Lake City, UT");
		list.add("Pittsburgh, PA");
		list.add("Phoenix, AZ");
		list.add("Los Angeles, CA");
		list.add("Chino, CA");
		list.add("Sacramento, CA");
		list.add("Seattle, WA");	
		return list;
	}
	public String getDate_Time(Boolean val)
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		String[] dt = dateFormat.format(date).split(" ");
		if(val)
		{
			return dt[0];
		}
		else
		{
			return dt[1];
		}
	}
}