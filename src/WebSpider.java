import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.Map.Entry;
import java.util.regex.*;

public class WebSpider {

	public static void main(String[] args) {
		HashMap<String, Boolean> urls = new HashMap<>();
		
		urls.put("https://www.wikipedia.org/", false);
		
		Set<Map.Entry<String,Boolean>> s = urls.entrySet();
		Iterator<Entry<String, Boolean>> iter = s.iterator();
		
		int i = 0;
		do{
			while(iter.hasNext()){
				Map.Entry<String, Boolean> e = iter.next();
				i = 0;
				if(!e.getValue()){
					try{
						URL url = new URL(e.getKey());
						BufferedReader rdr = new BufferedReader(new InputStreamReader(url.openStream()));
						String line = rdr.readLine();
						while(line != null){
							Pattern website = Pattern.compile("<a.*href=\"(http:.*?)\"");
							Matcher matcher = website.matcher(line);
							if (matcher.find())
								urls.put(matcher.group(1), false);
						}
					}catch(Exception ex){}
				}
				else
					i++;
			}
			s= urls.entrySet();
			iter = s.iterator();
		}
		while(i <100);
		
	}

}
