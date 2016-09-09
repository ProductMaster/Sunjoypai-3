package com.taoists.weixin.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
/**
 * xml解析工具类
 * @author Enjoy
 * @since 2011.12.17
 */
public class XMLHelper {
	DocumentBuilderFactory factory = null;   
    DocumentBuilder builder =null;
    Document document = null;
    Element element = null;
    
	public XMLHelper(String xmlStr){
		factory = DocumentBuilderFactory.newInstance();   
		try {
		builder = factory.newDocumentBuilder();
		document = builder.parse(new ByteArrayInputStream((xmlStr.getBytes("GBK"))));   
		element = document.getDocumentElement();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public XMLHelper(String xmlStr,String encode){
		factory = DocumentBuilderFactory.newInstance();   
		try {
		builder = factory.newDocumentBuilder();
		document = builder.parse(new ByteArrayInputStream((xmlStr.getBytes(encode))));   
		element = document.getDocumentElement();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 解析xml返回所需key下的所有元素的文本值(只有一个名字为key的元素)
	 * @param key element的标签名
	 * @return key元素的所有子集元素的文本值
	 */
	public Map<String,String> readXMLText(String key){
		Map<String,String> map = new HashMap<String,String>();
		NodeList recordNodes = element.getElementsByTagName(key);
		NodeList childNodes = null;
		 Node node = null;
		 node = recordNodes.item(0);
		 childNodes = node.getChildNodes();//得到子元素
		 map = new HashMap<String,String>();
		 for(int j=0;j<childNodes.getLength();j++){
			 if(childNodes.item(j).getNodeType()==Node.ELEMENT_NODE){//必须是element类型的
				 map.put(childNodes.item(j).getNodeName(), childNodes.item(j).getFirstChild() == null ? "" :childNodes.item(j).getFirstChild().getNodeValue());
			 }
		 }
		 return map;
	}
	/**
	 * 解析xml 获得最低
	 * @param key element的标签名
	 * @return key元素的所有子集元素的文本值
	 */
	public String readXMLByName(String key){
		NodeList recordNodes = element.getElementsByTagName(key);
		String xmlText=recordNodes.item(0).getFirstChild().getNodeValue();
		 return xmlText;
	}
	/**
	 * 解析xml返回所需xml下所有元素的列表(有多个名字为key的元素)
	 * @param key element的标签名
	 * @return key元素的所有子集元素的文本值集合
	 */
	public List<Map<String,String>> readXMLList(String key){
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		Map<String,String> map = null;
		
		NodeList recordNodes = element.getElementsByTagName(key);
		NodeList childNodes = null;
		 Node node = null;
		 for(int i=0;i<recordNodes.getLength();i++){
			 node = recordNodes.item(i);
			 childNodes = node.getChildNodes();//得到子元素
			 map = new HashMap<String,String>();
			 for(int j=0;j<childNodes.getLength();j++){
				 if(childNodes.item(j).getNodeType()==Node.ELEMENT_NODE){//必须是element类型的
					 map.put(childNodes.item(j).getNodeName(), childNodes.item(j).getFirstChild() == null ? "" :childNodes.item(j).getFirstChild().getNodeValue());
				 }
			 }
			 list.add(map);
		 }
		 return list;
	}
	public static void main(String[] arg){
		
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		sb.append("<add_order_request>");
		sb.append("<orderInfo>");
		sb.append("<sourceSite>");
		sb.append("11");
		sb.append("</sourceSite>");
		sb.append("<sourceSite2>");
		sb.append("113");
		sb.append("</sourceSite2>");
		sb.append("</orderInfo>");
		sb.append("</add_order_request>");
		XMLHelper h = new XMLHelper(sb.toString());
//		List<Map<String,String>> list = h.readXMLList("entity");
//		int i = 0;
//		for(Map<String,String> m : list){
//			i++;
//			for(String k : m.keySet()){
//				System.out.println(i+":"+k+"-"+m.get(k));
//			}
//		}
		Map<String,String> map = h.readXMLText("orderInfo");
		for(String k : map.keySet()){
			System.out.println(k+"-"+map.get(k));
		}
		
		
		String xml="<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+
		"<response>"+
		  "<a>1002</a>"+
		  "<b>身份信息错误</b>"+
		  "<c>true</c>"+
		 "</response>";
		XMLHelper xh=new XMLHelper(xml.toString(),"UTF-8");
		System.out.println(xh.readXMLByName("c"));
	}
}
