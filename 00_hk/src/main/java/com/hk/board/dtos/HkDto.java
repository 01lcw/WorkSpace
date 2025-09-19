package com.hk.board.dtos;

import java.io.Serializable;
import java.util.Date;
import java.util.concurrent.ForkJoinWorkerThread;

import javax.swing.plaf.multi.MultiInternalFrameUI;

//DTO: 데이터를 저장해서 전달할 때 사용하는 객체
public class HkDto implements Serializable{
	private static final long serialVersionUID = 5066693606872502219L;
	
	//은닉화: 중요한 데이터는 멤버필드에 바로 접근 못하게 처리
	private int tseq;
	private String tid;
	private String ttitle;
	private String tcontent;
	private Date tregDate;
	private String delflag;
	public HkDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public HkDto(int tseq, String tid, String ttitle, String tcontent, Date tregDate, String delflag) {
		super();
		this.tseq = tseq;
		this.tid = tid;
		this.ttitle = ttitle;
		this.tcontent = tcontent;
		this.tregDate = tregDate;
		this.delflag = delflag;
	}
	
	
	public HkDto(String tid, String ttitle, String tcontent) {
		super();
		this.tid = tid;
		this.ttitle = ttitle;
		this.tcontent = tcontent;
	}
	
	
	public HkDto(int tseq, String ttitle, String tcontent) {
		super();
		this.tseq = tseq;
		this.ttitle = ttitle;
		this.tcontent = tcontent;
	}
	
	public HkDto(int tseq, String tid, String ttitle, String tcontent, Date tregDate) {
		super();
		this.tseq = tseq;
		this.tid = tid;
		this.ttitle = ttitle;
		this.tcontent = tcontent;
		this.tregDate = tregDate;
	}
	public int getTseq() {
		return tseq;
	}
	public void setTseq(int tseq) {
		this.tseq = tseq;
	}
	public String getTid() {
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}
	public String getTtitle() {
		return ttitle;
	}
	public void setTtitle(String ttitle) {
		this.ttitle = ttitle;
	}
	public String getTcontent() {
		return tcontent;
	}
	public void setTcontent(String tcontent) {
		this.tcontent = tcontent;
	}
	public Date getTregDate() {
		return tregDate;
	}
	public void setTregDate(Date tregDate) {
		this.tregDate = tregDate;
	}
	public String getDelflag() {
		return delflag;
	}
	public void setDelflag(String delflag) {
		this.delflag = delflag;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "HkDto [tseq=" + tseq + ", tid=" + tid + ", ttitle=" + ttitle + ", tcontent=" + tcontent + ", tregDate="
				+ tregDate + ", delflag=" + delflag + "]";
	}
	
	
}
