package com.project.guitarShop.repository.board;

import com.project.guitarShop.entity.board.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
