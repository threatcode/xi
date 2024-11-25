package edu.dto.board;

import java.io.Serializable;

public class BoardDTO implements Serializable {
	
	private String mBoardId;
	private String mBoardName;
	private String mBoardUserId;

	  public String getBoardUserId() {
		return mBoardUserId;
	}

	public void setBoardUserId(String mBoardUserId) {
		this.mBoardUserId = mBoardUserId;
	}

	public String getBoardId() {
	    return mBoardId;
	  }

	  public void setBoardId(final String pBoardId) {
	    mBoardId = pBoardId;
	  }

	  public String getBoardName() {
	    return mBoardName;
	  }

	  public void setBoardName(final String pBoardName) {
	    mBoardName = pBoardName;
	  }

}
