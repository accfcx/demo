package com.accfcx.vote.service;

import com.accfcx.vote.coder.VoteMsg;

import java.util.HashMap;
import java.util.Map;

/**
 * @author accfcx
 * @desc
 **/
public class VoteService {
    /**
     * key: candidateID, value: votes
     */
    Map<Integer, Long> map = new HashMap<>();

    /**
     * Sever
     */
    public VoteMsg handleRequest(VoteMsg msg) {
        try{
            if (msg.isResponse()) {
                return msg;
            }

            msg.setResponse(true);

            int can = msg.getCandidateID();

            Long curVote = map.get(can);
            if (curVote == null) {
                curVote = 0L;
            }

            if (!msg.isInquiry()) {
                map.put(can, ++curVote);
            }

            msg.setVoteCount(curVote);

            return msg;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
