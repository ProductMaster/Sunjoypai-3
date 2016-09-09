package com.taoists.common.util;

import java.text.DecimalFormat;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import org.apache.commons.collections.CollectionUtils;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

/**
 * @author rubys@vip.qq.com
 * @since 2012-6-10
 */
public class StringUtils {

	public static final String COMMA = ",";

	/**
     * URLEncode对特殊字符进行编码
     * @param url
     * @return
     */
    public static String URLEncode(String url) {
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < url.length(); i++) {
            switch (url.charAt(i)) {
                case ' ':
                    buf.append("%20");
                    break;
                case '+':
                    buf.append("%2b");
                    break;
                case '\'':
                    buf.append("%27");
                    break;
                case '/':
                    buf.append("%2F");
                    break;
                case '.':
                    buf.append("%2E");
                    break;
                case '<':
                    buf.append("%3C");
                    break;
                case '>':
                    buf.append("%3E");
                    break;
                case '#':
                    buf.append("%23");
                    break;
                case '%':
                    buf.append("%25");
                    break;
                case '&':
                    buf.append("%26");
                    break;
                case '{':
                    buf.append("%7B");
                    break;
                case '}':
                    buf.append("%7D");
                    break;
                case '\\':
                    buf.append("%5C");
                    break;
                case '^':
                    buf.append("%5E");
                    break;
                case '~':
                    buf.append("%73");
                    break;
                case '[':
                    buf.append("%5B");
                    break;
                case ']':
                    buf.append("%5D");
                    break;
                default:
                    buf.append(url.charAt(i));
                    break;
            }
        }
        return buf.toString();
    }
	
	public static List<String> stringTokenizer(String value) {
		List<String> results = Lists.newArrayList();
		StringTokenizer st = new StringTokenizer(value);
		while (st.hasMoreElements()) {
			String str = st.nextElement().toString();
			if (org.apache.commons.lang.StringUtils.isNotBlank(str)) {
				results.add(str);
			}
		}
		return results;
	}

	public static String collectionToString(Collection<Object> collection) {
		if (CollectionUtils.isEmpty(collection)) {
			return "";
		} else {
			StringBuilder builder = new StringBuilder();
			for (Object obj : collection) {
				if (builder.length() != 0) {
					builder.append(COMMA).append(String.valueOf(obj));
				} else {
					builder.append(String.valueOf(obj));
				}
			}
			return builder.toString();
		}
	}

	public static Set<Long> stringToSet(String str) {
		Set<Long> result = Sets.newHashSet();
		if (org.apache.commons.lang.StringUtils.isBlank(str)) {
			return result;
		}
		for (String value : str.split(COMMA)) {
			result.add(Long.valueOf(value));
		}
		return result;
	}

	public static Set<Long> stringToSet(String[] values) {
		Set<Long> result = Sets.newHashSet();
		if (values != null) {
			for (String value : values) {
				result.add(Long.valueOf(value));
			}
		}
		return result;
	}
	
	public static boolean isNoEmpty(String str){
		
		if(str!=null){
			if(str.length()>0){
				return true;
			}
		}
		return false;
	}
	
    public static boolean isEmpty(String str){
        
        if(str==null)  return true;
        if(str.trim().length()==0) return true;
        return false;
    }
	    
	    
    public static String FormatNumber2String(long length, long s){
        StringBuffer stringFormat=new StringBuffer();
        for(int i=0;i<length;i++){
            stringFormat.append("0");
        }
        DecimalFormat df = new DecimalFormat(stringFormat.toString());
        return df.format(s);
    }

}
