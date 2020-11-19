package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Max;

@Entity(name = "UploadFile")
public class UploadFile extends Upload {

	// 10MB Max
	@Column
	@Max(10500000)
	private long size;

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}
}
