package com.project.guitarShop.service.reply;

import com.project.guitarShop.dto.reply.ReplyRequest.*;
import com.project.guitarShop.dto.reply.ReplyResponse.*;
import com.project.guitarShop.entity.post.Post;
import com.project.guitarShop.entity.reply.Reply;
import com.project.guitarShop.entity.member.Member;
import com.project.guitarShop.exception.post.NotFoundPostException;
import com.project.guitarShop.exception.member.NotFoundMemberException;
import com.project.guitarShop.exception.reply.NotFoundReplyException;
import com.project.guitarShop.repository.post.PostRepository;
import com.project.guitarShop.repository.reply.ReplyRepository;
import com.project.guitarShop.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReplyServiceImpl implements ReplyService {

    private final PostRepository postRepository;
    private final ReplyRepository replyRepository;
    private final MemberRepository memberRepository;

    @Transactional
    @Override
    public ReplyWriteResponse reply(ReplyWriteRequest request) {

        Member member = memberRepository.findById(request.memberId())
                .orElseThrow(() -> new NotFoundMemberException("해당 회원을 찾을 수 없습니다."));

        Post post = postRepository.findById(request.boardId())
                .orElseThrow(() -> new NotFoundPostException("해당 게시글을 찾을 수 없습니다."));

        Reply reply = Reply.builder()
                .reply(request.reply())
                .member(member)
                .post(post)
                .build();

        Reply save = replyRepository.save(reply);

        return new ReplyWriteResponse(save);
    }

    @Transactional
    @Override
    public ReplyUpdateResponse update(Long id, ReplyUpdateRequest request) {

        Reply reply = replyRepository.findById(id)
                .orElseThrow(() -> new NotFoundReplyException("해당 댓글을 찾을 수 없습니다."));

        reply.updateReply(request.reply());

        Reply save = replyRepository.save(reply);

        return new ReplyUpdateResponse(save);
    }
}
