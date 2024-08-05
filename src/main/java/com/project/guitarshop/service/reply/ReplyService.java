package com.project.guitarshop.service.reply;

import com.project.guitarshop.dto.reply.ReplyRequest.*;
import com.project.guitarshop.dto.reply.ReplyResponse.*;

public interface ReplyService {

    ReplyWriteResponse reply(Long postId, ReplyWriteRequest request);

    ReplyUpdateResponse update(Long replyId, ReplyUpdateRequest request);

    void delete(Long replyId);
}
