package nju.wqy.web.vo;

public class MeasureVO {
	//非注释行
	private int loc;
	//总行数
	private int line;
	private int functions;
	private int classes;
	private int commentsLines;
	private double commentRate;
	private int codeSmell;
	private int bug;
	private int vulnerability;
	private int file;
	
	public int getLine() {
		return line;
	}
	public void setLine(int line) {
		this.line = line;
	}
	public int getFile() {
		return file;
	}
	public void setFile(int file) {
		this.file = file;
	}
	public int getCodeSmell() {
		return codeSmell;
	}
	public void setCodeSmell(int codeSmell) {
		this.codeSmell = codeSmell;
	}
	public int getBug() {
		return bug;
	}
	public void setBug(int bug) {
		this.bug = bug;
	}
	public int getVulnerability() {
		return vulnerability;
	}
	public void setVulnerability(int vulnerability) {
		this.vulnerability = vulnerability;
	}
	public int getLoc() {
		return loc;
	}
	public void setLoc(int loc) {
		this.loc = loc;
	}
	public int getFunctions() {
		return functions;
	}
	public void setFunctions(int functions) {
		this.functions = functions;
	}
	public int getClasses() {
		return classes;
	}
	public void setClasses(int classes) {
		this.classes = classes;
	}
	public int getCommentsLines() {
		return commentsLines;
	}
	public void setCommentsLines(int commentsLines) {
		this.commentsLines = commentsLines;
	}
	public double getCommentRate() {
		return commentRate;
	}
	public void setCommentRate(double commentRate) {
		this.commentRate = commentRate;
	}


}
