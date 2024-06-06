package com.project.guitarShop.service.comment;

import com.project.guitarShop.dto.comment.CommentRequest.*;
import com.project.guitarShop.dto.comment.CommentResponse.*;

public interface CommentService {

    CommentWriteResponse comment(CommentWriteRequest request);
}
