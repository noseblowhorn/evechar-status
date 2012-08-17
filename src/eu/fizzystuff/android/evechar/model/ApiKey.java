package eu.fizzystuff.android.evechar.model;

import java.io.Serializable;

public class ApiKey implements Serializable {
	private int RowId;
	private String KeyId;
	private String VCode;

	public int getRowId() {
		return RowId;
	}

	public void setRowId(int rowId) {
		RowId = rowId;
	}

	public String getKeyId() {
		return KeyId;
	}

	public void setKeyId(String keyId) {
		KeyId = keyId;
	}

	public String getVCode() {
		return VCode;
	}

	public void setVCode(String vCode) {
		VCode = vCode;
	}

	@Override
	public String toString() {
		return KeyId;
	}
	
	
}
