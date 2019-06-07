package kr.codelab.demo.board.service;

import kr.codelab.demo.board.model.Board;
import kr.codelab.demo.board.repository.BoardRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BoardService {

    private BoardRepository boardRepository;

    public List<Board> getBoardList() {
        return boardRepository.findAll();
    }

    public Page<Board> getBoardPagedList(Pageable pageable) {
        pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 :
                pageable.getPageNumber() - 1, pageable.getPageSize());

        return boardRepository.findAll(pageable);
    }

    public Board getBoard(Long id) {
        return boardRepository.findById(id).orElse(new Board());
    }

    public void createBoard(Board board) {
        board.setCurrentTime();
        boardRepository.save(board);
    }

    public void updateBoard(Board board) {
        Board savedBoard = boardRepository.findById(board.getId()).get();

        savedBoard.update(board);
        boardRepository.save(savedBoard);
    }

    public void deleteBoard(Long id) {
        boardRepository.deleteById(id);
    }
}
