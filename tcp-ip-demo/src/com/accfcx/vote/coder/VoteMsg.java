package com.accfcx.vote.coder;

/**
 * @author accfcx
 * @desc
 **/
public class VoteMsg {

    private boolean isInquiry;

    private boolean isResponse;

    /**
     * 大小范围: 0-1000
     */
    private int candidateID;

    private Long voteCount;

    public VoteMsg(boolean isInquiry, boolean isResponse, int candidateID, long voteCount) {
        this.isInquiry = isInquiry;
        this.isResponse = isResponse;
        this.candidateID = candidateID;
        this.voteCount = voteCount;
    }


    public boolean isInquiry() {
        return isInquiry;
    }

    public void setInquiry(boolean inquiry) {
        isInquiry = inquiry;
    }

    public boolean isResponse() {
        return isResponse;
    }

    public void setResponse(boolean response) {
        isResponse = response;
    }

    public int getCandidateID() {
        return candidateID;
    }

    public void setCandidateID(int candidateID) {
        this.candidateID = candidateID;
    }

    public long getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(long voteCount) {
        this.voteCount = voteCount;
    }

    @Override
    public String toString() {
        return "VoteMsg{" +
                "isInquiry=" + isInquiry +
                ", isResponse=" + isResponse +
                ", candidateID=" + candidateID +
                ", voteCount=" + voteCount +
                '}';
    }
}
