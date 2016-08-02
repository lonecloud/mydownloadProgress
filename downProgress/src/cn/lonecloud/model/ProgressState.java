package cn.lonecloud.model;

import java.io.Serializable;

public class ProgressState implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//文件名
	private String FileName;
	//文件大小
	private Long FileSize;
//	速度
	private double speeed;
	//状态0进行1成功2
	private int State;
	@Override
	public String toString() {
		return "ProgressState [FileName=" + FileName + ", FileSize=" + FileSize
				+ ", speeed=" + speeed + ", State=" + State + ", precent="
				+ precent + ", startTime=" + startTime + "]";
	}
	//上传的百分比
	private String precent;
	//上传时间
	private Long startTime;
	
	public ProgressState() {
		startTime=System.currentTimeMillis();
	}
	
	public ProgressState(String fileName, Long fileSize) {
		super();
		FileName = fileName;
		FileSize = fileSize;
		startTime=System.currentTimeMillis();
		precent="0";
		State=0;
	}

	public String getFileName() {
		return FileName;
	}
	public void setFileName(String fileName) {
		FileName = fileName;
	}
	public Long getFileSize() {
		return FileSize;
	}
	public void setFileSize(Long fileSize) {
		FileSize = fileSize;
	}
	public double getSpeeed() {
		return speeed;
	}
	public void setSpeeed(double speeed) {
		this.speeed = speeed;
	}
	public int getState() {
		return State;
	}
	public void setState(int state) {
		State = state;
	}
	public String getPrecent() {
		return precent;
	}
	public void setPrecent(String precent) {
		this.precent = precent;
	}
	public Long getStartTime() {
		return startTime;
	}
	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}
	
}
