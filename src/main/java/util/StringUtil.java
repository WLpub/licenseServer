package util;

public class StringUtil {
	public static boolean isNull(String str){
		if(str==null||str.isEmpty()||str.equals("")||str.length()==0){
			return true;
		}
		return false;
	}
}
