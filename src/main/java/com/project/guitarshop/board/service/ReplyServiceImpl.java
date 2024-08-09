package com.project.guitarshop.board.service;

import com.project.guitarshop.board.dto.ReplyRequest.*;
import com.project.guitarshop.board.dto.ReplyResponse.*;
import com.project.guitarshop.board.entity.Post;
import com.project.guitarshop.board.entity.Reply;
import com.project.guitarshop.member.entity.Member;
import com.project.guitarshop.board.exception.NotFoundPostException;
import com.project.guitarshop.member.exception.NotFoundMemberException;
import com.project.guitarshop.board.exception.NotFoundReplyException;
import com.project.guitarshop.board.repository.PostRepository;
import com.project.guitarshop.board.repository.ReplyRepository;
import com.project.guitarshop.member.repository.MemberRepository;
import com.project.guitarshop.util.SecurityUtil;
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
    public ReplyWriteResponse reply(Long postId, ReplyWriteRequest request) {

        Long memberId = SecurityUtil.getCurrentUserId();

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundMemberException("해당 회원을 찾을 수 없습니다."));

        Post post = postRepository.findById(postId)
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
    public ReplyUpdateResponse update(Long replyId, ReplyUpdateRequest request) {

        Reply reply = replyRepository.findById(replyId)
                .orElseThrow(() -> new NotFoundReplyException("해당 댓글을 찾을 수 없습니다."));

        reply.updateReply(request.reply());

        return new ReplyUpdateResponse(reply);
    }

    @Transactional
    @Override
    public void delete(Long replyId) {

        Reply reply = replyRepository.findById(replyId)
                .orElseThrow(() -> new NotFoundReplyException("해당 댓글을 찾을 수 없습니다."));
        replyRepository.delete(reply);
    }
}
