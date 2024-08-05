package com.project.guitarshop.service.reply;

import com.project.guitarshop.dto.reply.ReplyRequest.*;
import com.project.guitarshop.dto.reply.ReplyResponse.*;
import com.project.guitarshop.entity.post.Post;
import com.project.guitarshop.entity.reply.Reply;
import com.project.guitarshop.entity.member.Member;
import com.project.guitarshop.exception.post.NotFoundPostException;
import com.project.guitarshop.exception.member.NotFoundMemberException;
import com.project.guitarshop.exception.reply.NotFoundReplyException;
import com.project.guitarshop.repository.post.PostRepository;
import com.project.guitarshop.repository.reply.ReplyRepository;
import com.project.guitarshop.repository.member.MemberRepository;
import com.project.guitarshop.util.SecurityUtils;
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

        Long memberId = SecurityUtils.getCurrentUserId();

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
    public ReplyUpdateResponse update(Long id, ReplyUpdateRequest request) {

        Reply reply = replyRepository.findById(id)
                .orElseThrow(() -> new NotFoundReplyException("해당 댓글을 찾을 수 없습니다."));

        reply.updateReply(request.reply());

        Reply save = replyRepository.save(reply);

        return new ReplyUpdateResponse(save);
    }

    @Transactional
    @Override
    public void delete(Long id) {

        Reply reply = replyRepository.findById(id)
                .orElseThrow(() -> new NotFoundReplyException("해당 댓글을 찾을 수 없습니다."));
        replyRepository.delete(reply);
    }
}
