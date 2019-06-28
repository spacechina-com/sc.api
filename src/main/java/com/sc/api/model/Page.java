package com.sc.api.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Page implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2889679311053928773L;

	public static int PAGE_SIZE = 10;
	private int showCount; // 每页显示记录数
	private int totalPage; // 总页数
	private int totalResult; // 总记录数
	private int currentPage; // 当前页
	private int currentResult; // 当前记录起始索引
	private boolean entityOrField; // true:需要分页的地方，传入的参数就是Page实体；false:需要分页的地方，传入的参数所代表的实体拥有Page属性
	private String pageStr; // 最终页面显示的底部翻页导航，详细见：getPageStr();
	private String position = "right";
	private Pd pd = new Pd();
	private List<Pd> data = new ArrayList<Pd>();

	public Page() {
		this.showCount = PAGE_SIZE;
	}

	public Page(int pageSize) {
		this.showCount = pageSize;
	}

	public int getTotalPage() {
		if (totalResult % showCount == 0)
			totalPage = totalResult / showCount;
		else
			totalPage = totalResult / showCount + 1;
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getTotalResult() {
		return totalResult;
	}

	public void setTotalResult(int totalResult) {
		this.totalResult = totalResult;
	}

	public int getCurrentPage() {
		if (currentPage <= 0)
			currentPage = 1;
		if (currentPage > getTotalPage())
			currentPage = getTotalPage();
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public int getShowCount() {
		return showCount;
	}

	public void setShowCount(int showCount) {

		this.showCount = showCount;
	}

	public int getCurrentResult() {
		currentResult = (getCurrentPage() - 1) * getShowCount();
		if (currentResult < 0)
			currentResult = 0;
		return currentResult;
	}

	public void setCurrentResult(int currentResult) {
		this.currentResult = currentResult;
	}

	public boolean isEntityOrField() {
		return entityOrField;
	}

	public void setEntityOrField(boolean entityOrField) {
		this.entityOrField = entityOrField;
	}

	public Pd getPd() {
		return pd;
	}

	public void setPd(Pd pd) {
		this.pd = pd;
	}

	public List<Pd> getData() {
		return data;
	}

	public void setData(List<Pd> data) {
		this.data = data;
		this.pageStr = getPageStr();
	}

	public String getPageStr() {
		StringBuilder sb = new StringBuilder();
		sb.append("	<input id=\"page_currentPage\" type=\"hidden\" name=\"page_currentPage\" value=\"" + currentPage
				+ "\"/>\n");
		sb.append("	<input id=\"page_showCount\" type=\"hidden\" name=\"page_showCount\" value=\"" + PAGE_SIZE
				+ "\"/>\n");
		sb.append("<div>\n");
		if (getTotalResult() == 0) {
			sb.append("<a href='javascript:;' style='border:none;'>暂无查询数据,建议简化查询约束</a>\n");
			sb.append("</div>");
			return sb.toString();
		}

		sb.append("<a class='prev' href='javascript:goPage(\"1\");'>&lt;&lt;</a>\n");
		if (getCurrentPage() - 3 > 0) {
			sb.append("<a class='num' href='javascript:goPage(\"" + (getCurrentPage() - 3) + "\");'>"
					+ (getCurrentPage() - 3) + "</a>\n");
		}
		if (getCurrentPage() - 2 > 0) {
			sb.append("<a class='num' href='javascript:goPage(\"" + (getCurrentPage() - 2) + "\");'>"
					+ (getCurrentPage() - 2) + "</a>\n");
		}
		if (getCurrentPage() - 1 > 0) {
			sb.append("<a class='num' href='javascript:goPage(\"" + (getCurrentPage() - 1) + "\");'>"
					+ (getCurrentPage() - 1) + "</a>\n");
		}

		sb.append("<span class='current'>" + getCurrentPage() + "</span>\n");

		if (getCurrentPage() + 1 <= getTotalPage()) {
			sb.append("<a class='num' href='javascript:goPage(\"" + (getCurrentPage() + 1) + "\");'>"
					+ (getCurrentPage() + 1) + "</a>\n");
		}
		if (getCurrentPage() + 2 <= getTotalPage()) {
			sb.append("<a class='num' href='javascript:goPage(\"" + (getCurrentPage() + 2) + "\");'>"
					+ (getCurrentPage() + 2) + "</a>\n");
		}
		if (getCurrentPage() + 3 <= getTotalPage()) {
			sb.append("<a class='num' href='javascript:goPage(\"" + (getCurrentPage() + 3) + "\");'>"
					+ (getCurrentPage() + 3) + "</a>\n");
		}
		sb.append("<a class='next' href='javascript:goPage(\"" + getTotalPage() + "\");'>&gt;&gt;</a>\n");

		sb.append("<a href='javascript:;' style='border:none;'>总共:" + getTotalResult() + "条&nbsp;/&nbsp;共"
				+ getTotalPage() + "页</a>\n");

		sb.append("</div>");

		return sb.toString();
	}

	public void setPageStr(String pageStr) {
		this.pageStr = pageStr;
	}
}
