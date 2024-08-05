package com.project.guitarshop.board.service;

import com.project.guitarshop.board.dto.ReplyRequest.*;
import com.project.guitarshop.board.dto.ReplyResponse.*;

public interface ReplyService {

    ReplyWriteResponse reply(Long postId, ReplyWriteRequest request);

    ReplyUpdateResponse update(Long replyId, ReplyUpdateRequest request);

    void delete(Long replyId);
}
