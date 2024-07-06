package com.project.guitarShop.service.post;

import com.project.guitarShop.dto.post.PostRequest.*;
import com.project.guitarShop.dto.post.PostResponse.*;
import com.project.guitarShop.entity.post.Post;
import com.project.guitarShop.entity.member.Member;
import com.project.guitarShop.exception.post.NotFoundPostException;
import com.project.guitarShop.exception.member.NotFoundMemberException;
import com.project.guitarShop.repository.post.PostRepository;
import com.project.guitarShop.repository.member.MemberRepository;
import com.project.guitarShop.repository.redis.RedisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final RedisRepository redisRepository;

    @Transactional
    @Override
    public PostWriteResponse write(PostWriteRequest request) {

        Member member = memberRepository.findById(request.memberId())
                .orElseThrow(() -> new NotFoundMemberException("해당 회원을 찾을 수 없습니다."));

        Post post = Post.builder()
                .title(request.title())
                .content(request.content())
                .member(member)
                .view(0)
                .build();

        Post save = postRepository.save(post);

        return new PostWriteResponse(save);
    }

    @Transactional
    @Override
    public Page<PostListResponse> list(Pageable pageable) {
        return postRepository.findAll(pageable).map(PostListResponse::new);
    }

    @Cacheable(value = "post", key = "#id")
    @Transactional
    @Override
    public PostReadResponse read(Long postId) {

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new NotFoundPostException("해당 게시글을 찾을 수 없습니다."));

        long increasesViewCount = redisRepository.incrementViewCount(postId);
        post.addViewCount((int) increasesViewCount);
        postRepository.save(post);
        return new PostReadResponse(post);
    }

    @Transactional
    @Override
    public PostUpdateResponse update(Long postId, PostUpdateRequest request) {

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new NotFoundPostException("해당 게시글을 찾을 수 없습니다."));

        post.updateBoard(request.title(), request.content());

        return new PostUpdateResponse(postRepository.save(post));
    }

    @Transactional
    @Override
    public void delete(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new NotFoundPostException("해당 게시글을 찾을 수 없습니다."));
        postRepository.delete(post);
    }

}
