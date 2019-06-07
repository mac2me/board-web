package kr.codelab.demo.board.controller;

import kr.codelab.demo.board.model.Board;
import kr.codelab.demo.board.service.BoardService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private BoardService boardService;

//    @GetMapping("/list")
//    public String getBoardList(Model model) {
//        model.addAttribute("boardList", boardService.getBoardList());
//
//        return "board/list";
//    }

    @GetMapping("/list")
    public String getBoardList(@PageableDefault(size = 10) Pageable pageable, Model model) {
        System.out.println(boardService.getBoardPagedList(pageable).getNumber());
        model.addAttribute("boardList", boardService.getBoardPagedList(pageable));

        return "board/list";
    }

    @GetMapping("/{id}")
    public String getBoard(@PathVariable Long id, Model model) {
        model.addAttribute("board", boardService.getBoard(id));

        return "board/detail";
    }

    @GetMapping("/new")
    public String newBoard(@ModelAttribute Board board) {
        return "/board/new";
    }

    @PostMapping("/input")
    public String createBoard(@ModelAttribute Board board) {
        boardService.createBoard(board);

        return "redirect:/board/list";
    }

    @GetMapping("/{id}/edit")
    public String editBoard(@PathVariable Long id, Model model) {
        model.addAttribute("board", boardService.getBoard(id));

        return "board/edit";
    }

    @PostMapping("/update")
    public String updateBoard(@ModelAttribute Board board) {
        boardService.updateBoard(board);

        return "redirect:/board/list";
    }

    @GetMapping("/{id}/delete")
    public String deleteBoard(@PathVariable  Long id) {
        boardService.deleteBoard(id);

        return "redirect:/board/list";
    }
}
