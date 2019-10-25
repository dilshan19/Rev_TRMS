package com.revature.pojo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class Reimbursement {
	private int id = 0;
	private String requestorEmail;
	private String location;
	private LocalDate date;
	private String type;
	private String description;
	private String format;
	private double originalAmount;
	private double tentativeAmount;
	private boolean isGradeUploaded = false;
	private boolean isDSApproved = false;
	private boolean isDHApproved = false;
	private boolean isBCApproved = false;
	private boolean isBCAltered = false;
	private String xfilePath;
	
	public Reimbursement() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Reimbursement(String requestorEmail, String location, String type, String description, String format,
			LocalDate date, double originalAmount, double tentativeAmount) {
		super();
		this.requestorEmail = requestorEmail;
		this.location = location;
		this.type = type;
		this.description = description;
		this.format = format;
		this.date = date;
		this.originalAmount = originalAmount;
		this.tentativeAmount = tentativeAmount;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
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
	public String getxFilePath() {
		return xfilePath;
	}

	public void setxFilePath(String xfilePath) {
		this.xfilePath = xfilePath;
	}
	@Override
	public String toString() {
		return "Reimbursement [requestorEmail=" + requestorEmail + ", location=" + location + ", type=" + type
				+ ", description=" + description + ", format=" + format + ", date=" + date + ", originalAmount="
				+ originalAmount + ", tentativeAmount=" + tentativeAmount + ", isDSApproved=" + isDSApproved
				+ ", isDHApproved=" + isDHApproved + ", isBCApproved=" + isBCApproved + ", isBCAltered=" + isBCAltered
				+ ", isGradeUploaded=" + isGradeUploaded + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((format == null) ? 0 : format.hashCode());
		result = prime * result + (isBCAltered ? 1231 : 1237);
		result = prime * result + (isBCApproved ? 1231 : 1237);
		result = prime * result + (isDHApproved ? 1231 : 1237);
		result = prime * result + (isDSApproved ? 1231 : 1237);
		result = prime * result + (isGradeUploaded ? 1231 : 1237);
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		long temp;
		temp = Double.doubleToLongBits(originalAmount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((requestorEmail == null) ? 0 : requestorEmail.hashCode());
		temp = Double.doubleToLongBits(tentativeAmount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reimbursement other = (Reimbursement) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (format == null) {
			if (other.format != null)
				return false;
		} else if (!format.equals(other.format))
			return false;
		if (isBCAltered != other.isBCAltered)
			return false;
		if (isBCApproved != other.isBCApproved)
			return false;
		if (isDHApproved != other.isDHApproved)
			return false;
		if (isDSApproved != other.isDSApproved)
			return false;
		if (isGradeUploaded != other.isGradeUploaded)
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (Double.doubleToLongBits(originalAmount) != Double.doubleToLongBits(other.originalAmount))
			return false;
		if (requestorEmail == null) {
			if (other.requestorEmail != null)
				return false;
		} else if (!requestorEmail.equals(other.requestorEmail))
			return false;
		if (Double.doubleToLongBits(tentativeAmount) != Double.doubleToLongBits(other.tentativeAmount))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
	

}
