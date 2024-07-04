package com.project.guitarShop.service.reply;

import com.project.guitarShop.dto.reply.ReplyRequest.*;
import com.project.guitarShop.dto.reply.ReplyResponse.*;

public interface ReplyService {

    ReplyWriteResponse reply(ReplyWriteRequest request);

    ReplyUpdateResponse update(Long id, ReplyUpdateRequest request);

    void delete(Long id);
}
