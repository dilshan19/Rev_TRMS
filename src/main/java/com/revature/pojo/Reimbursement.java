package com.revature.pojo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class Reimbursement {
	private String requestorEmail;
	private String location;
	private String type;
	private String description;
	private String format;
	private LocalDate date;
	private double originalAmount;
	private double tentativeAmount;
	private boolean isDSApproved = false;
	private boolean isDHApproved = false;
	private boolean isBCApproved = false;
	private boolean isBCAltered = false;
	private boolean isGradeUploaded = false;
	
	public String getRequestorEmail() {
		return requestorEmail;
	}
	public void setRequestorEmail(String requestorEmail) {
		this.requestorEmail = requestorEmail;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate localDate) {
		this.date = localDate;
	}
	public double getOriginalAmount() {
		return originalAmount;
	}
	public void setOriginalAmount(double originalAmount) {
		this.originalAmount = originalAmount;
	}
	public double getTentativeAmount() {
		return tentativeAmount;
	}
	public void setTentativeAmount(double tentativeAmount) {
		this.tentativeAmount = tentativeAmount;
	}
	public boolean isDSApproved() {
		return isDSApproved;
	}
	public void setDSApproved(boolean isDSApproved) {
		this.isDSApproved = isDSApproved;
	}
	public boolean isDHApproved() {
		return isDHApproved;
	}
	public void setDHApproved(boolean isDHApproved) {
		this.isDHApproved = isDHApproved;
	}
	public boolean isBCApproved() {
		return isBCApproved;
	}
	public void setBCApproved(boolean isBCApproved) {
		this.isBCApproved = isBCApproved;
	}
	public boolean isBCAltered() {
		return isBCAltered;
	}
	public void setBCAltered(boolean isBCAltered) {
		this.isBCAltered = isBCAltered;
	}
	public boolean getGradeUploaded() {
		return isGradeUploaded;
	}
	public void setGradeUploaded(boolean isGradeUploaded) {
		this.isGradeUploaded = isGradeUploaded;
	}
	@Override
	public String toString() {
		return "Reimbursement [requestorEmail=" + requestorEmail + ", location=" + location + ", type=" + type
				+ ", description=" + description + ", format=" + format + ", date=" + date + ", originalAmount="
				+ originalAmount + ", tentativeAmount=" + tentativeAmount + ", isDSApproved=" + isDSApproved
				+ ", isDHApproved=" + isDHApproved + ", isBCApproved=" + isBCApproved + ", isBCAltered=" + isBCAltered
				+ ", isGradeUploaded=" + isGradeUploaded + "]";
	}

}
