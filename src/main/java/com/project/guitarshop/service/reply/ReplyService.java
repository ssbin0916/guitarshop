package com.project.guitarshop.service.reply;

import com.project.guitarshop.dto.reply.ReplyRequest.*;
import com.project.guitarshop.dto.reply.ReplyResponse.*;

public interface ReplyService {

    ReplyWriteResponse reply(ReplyWriteRequest request);

    ReplyUpdateResponse update(Long id, ReplyUpdateRequest request);

    void delete(Long id);
}
