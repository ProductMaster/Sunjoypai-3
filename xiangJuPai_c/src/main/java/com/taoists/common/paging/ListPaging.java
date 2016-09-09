/**
 * 
 */
package com.taoists.common.paging;

import java.util.ArrayList;
import java.util.List;

/** 
 * 类说明 
 * @author George 
 * @date 2015-1-4  
 */
public class ListPaging {

	/** 分页缺省显示行数 **/
	private static int DEFAULT_PAGE_SIZE = 5;
	
    private int pageNo = 1; 				// 当前页
    public  int totalPage = 0; 				// 总页数
    private int pageSize=5;					// 每页记录数
    private int totalCount = 0; 			// 总数据数
    
    private int pageStartRow = 0;			// 每页的起始数
    private int pageEndRow = 0; 			// 每页显示数据的终止数
    private boolean hasNextPage = false; 	// 是否有下一页
    private boolean hasPrevPage = false; 	// 是否有前一页
    
    private List list;

    /**
     * 构造函数，初始化集合以及每页的记录数
     * 
     */
    public ListPaging(List list, int pageSize) {
        init(list, pageSize);				
    }
    
    public ListPaging(List list){
    	init(list, DEFAULT_PAGE_SIZE);
    }
    
    /**
     * 初始化list，并告之该list每页的记录数
     * @param list
     * @param pageSize
     */
    public void init(List list, int pageSize) {
    	
        this.pageSize = pageSize;
        this.list = list;
        totalCount = list.size();

        hasPrevPage = false;
        if ((totalCount % pageSize) == 0) {
            totalPage = totalCount / pageSize;
        } else {
            totalPage = totalCount / pageSize + 1;
        }

        if (pageNo >= totalPage) {
            hasNextPage = false;
        } else {
            hasNextPage = true;
        }

        if (totalCount < pageSize) {
            this.pageStartRow = 0;
            this.pageEndRow = totalCount;
        } else {
            this.pageStartRow = 0;
            this.pageEndRow = pageSize;
        }
    }

    // 判断要不要分页
    public boolean isNext(){
        return list.size() > pageSize;
    }

    public void sethasPrevPage(boolean hasPrevPage) {
        this.hasPrevPage = hasPrevPage;
    }

    public String toString(int temp) {
        String str = Integer.toString(temp);
        return str;
    }

    public void description() {

        String description = "共有数据数:" + this.getTotalCount()
                + "共有页数: " + this.getTotalPage()
                + "当前页为:" + this.getPageNo()
                + " 是否有前一页: " + this.isHasPrevPage()
                + " 是否有下一页:" + this.isHasNextPage()
                + " 开始行数:" + this.getPageStartRow()
                + " 终止行数:" + this.getPageEndRow();

        System.out.println(description);
    }

    public List getNextPage() {
    	pageNo = pageNo + 1;

        disposePage();

        System.out.println("用户凋用的是第" + pageNo + "页");
        this.description();
        return getObjects(pageNo);
    }

    /**
     * 处理分页
     */
    private void disposePage() {

        if (pageNo == 0) {
        	pageNo = 1;
        }

        if ((pageNo - 1) > 0) {
            hasPrevPage = true;
        } else {
            hasPrevPage = false;
        }

        if (pageNo >= totalPage) {
            hasNextPage = false;
        } else {
            hasNextPage = true;
        }
    }

    public List getPreviousPage() {

    	pageNo = pageNo - 1;

        if ((pageNo - 1) > 0) {
            hasPrevPage = true;
        } else {
            hasPrevPage = false;
        }
        if (pageNo >= totalPage) {
            hasNextPage = false;
        } else {
            hasNextPage = true;
        }
        this.description();
        return getObjects(pageNo);
    }


    /**
     * 获取第几页的内容
     *
     * @param page
     * @return
     */
    public List getObjects(int pageNo) {
        if (pageNo == 0) {
            this.setPageNo(1);
        } else {
            this.setPageNo(pageNo);
        }
        this.disposePage();
        if (pageNo * pageSize < totalCount) {// 判断是否为最后一页
            pageEndRow = pageNo * pageSize;
            pageStartRow = pageEndRow - pageSize;
        } else {
            pageEndRow = totalCount;
            pageStartRow = pageSize * (totalPage - 1);
        }

        List objects = new ArrayList();
        if (!list.isEmpty()) {
            objects = list.subList(pageStartRow, pageEndRow);
        }
        //this.description();
        return objects;
    }

    public List getFistPage() {
        if (this.isNext()) {
            return list.subList(0, pageSize);
        } else {
            return list;
        }
    }

    public boolean isHasNextPage() {
        return hasNextPage;
    }

    public void setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageEndRow() {
        return pageEndRow;
    }

    public void setPageEndRow(int pageEndRow) {
        this.pageEndRow = pageEndRow;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageStartRow() {
        return pageStartRow;
    }

    public void setPageStartRow(int pageStartRow) {
        this.pageStartRow = pageStartRow;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public boolean isHasPrevPage() {
        return hasPrevPage;
    }

    public static void main(String[] args) {


        List<String> list = new ArrayList<String>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("e");
        list.add("f");
        list.add("g");
        list.add("h");
        list.add("h");
        list.add("i");
        list.add("j");
        list.add("k");
        list.add("l");
        list.add("m");
        ListPaging pm = new ListPaging(list, 3);//每页显示条数

        List sublist = pm.getObjects(2);//显示第几页
        for (int i = 0; i < sublist.size(); i++) {
            System.out.println(sublist.get(i));
        }
    }
}
